package com.hua.shiro;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.util.ByteSource.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcRealm extends AuthorizingRealm {
    protected static final String DEFAULT_AUTHENTICATION_QUERY = "select password from users where username = ?";
    protected static final String DEFAULT_SALTED_AUTHENTICATION_QUERY = "select password, password_salt from users where username = ?";
    protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";
    protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from roles_permissions where role_name = ?";
    private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);
    protected DataSource dataSource;
    protected String authenticationQuery = "select password from users where username = ?";
    protected String userRolesQuery = "select role_name from user_roles where username = ?";
    protected String permissionsQuery = "select permission from roles_permissions where role_name = ?";
    protected boolean permissionsLookupEnabled = false;
    protected JdbcRealm.SaltStyle saltStyle;

    public JdbcRealm() {
        this.saltStyle = JdbcRealm.SaltStyle.NO_SALT;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setAuthenticationQuery(String authenticationQuery) {
        this.authenticationQuery = authenticationQuery;
    }

    public void setUserRolesQuery(String userRolesQuery) {
        this.userRolesQuery = userRolesQuery;
    }

    public void setPermissionsQuery(String permissionsQuery) {
        this.permissionsQuery = permissionsQuery;
    }

    public void setPermissionsLookupEnabled(boolean permissionsLookupEnabled) {
        this.permissionsLookupEnabled = permissionsLookupEnabled;
    }

    public void setSaltStyle(JdbcRealm.SaltStyle saltStyle) {
        this.saltStyle = saltStyle;
        if (saltStyle == JdbcRealm.SaltStyle.COLUMN && this.authenticationQuery.equals("select password from users where username = ?")) {
            this.authenticationQuery = "select password, password_salt from users where username = ?";
        }

    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        } else {
            Connection conn = null;
            SimpleAuthenticationInfo info = null;

            try {
                String salt;
                try {
                    conn = this.dataSource.getConnection();
                    String password = null;
                    salt = null;
                    switch(this.saltStyle) {
                        case NO_SALT:
                            password = this.getPasswordForUser(conn, username)[0];
                            break;
                        case CRYPT:
                            throw new ConfigurationException("Not implemented yet");
                        case COLUMN:
                            String[] queryResults = this.getPasswordForUser(conn, username);
                            password = queryResults[0];
                            salt = queryResults[1];
                            break;
                        case EXTERNAL:
                            password = this.getPasswordForUser(conn, username)[0];
                            salt = this.getSaltForUser(username);
                    }

                    if (password == null) {
                        throw new UnknownAccountException("No account found for user [" + username + "]");
                    }

                    info = new SimpleAuthenticationInfo(username, password.toCharArray(), this.getName());
                    if (salt != null) {
                        info.setCredentialsSalt(Util.bytes(salt));
                    }
                } catch (SQLException var12) {
                    salt = "There was a SQL error while authenticating user [" + username + "]";
                    if (log.isErrorEnabled()) {
                        log.error(salt, var12);
                    }

                    throw new AuthenticationException(salt, var12);
                }
            } finally {
                JdbcUtils.closeConnection(conn);
            }

            return info;
        }
    }

    private String[] getPasswordForUser(Connection conn, String username) throws SQLException {
        boolean returningSeparatedSalt = false;
        String[] result;
        switch(this.saltStyle) {
            case NO_SALT:
            case CRYPT:
            case EXTERNAL:
                result = new String[1];
                break;
            case COLUMN:
            default:
                result = new String[2];
                returningSeparatedSalt = true;
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(this.authenticationQuery);
            ps.setString(1, username);
            rs = ps.executeQuery();

            for(boolean foundResult = false; rs.next(); foundResult = true) {
                if (foundResult) {
                    throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
                }

                result[0] = rs.getString(1);
                if (returningSeparatedSalt) {
                    result[1] = rs.getString(2);
                }
            }
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }

        return result;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        } else {
            String username = (String)this.getAvailablePrincipal(principals);
            Connection conn = null;
            Set<String> roleNames = null;
            Set permissions = null;

            try {
                conn = this.dataSource.getConnection();
                roleNames = this.getRoleNamesForUser(conn, username);
                if (this.permissionsLookupEnabled) {
                    permissions = this.getPermissions(conn, username, roleNames);
                }
            } catch (SQLException var11) {
                String message = "There was a SQL error while authorizing user [" + username + "]";
                if (log.isErrorEnabled()) {
                    log.error(message, var11);
                }

                throw new AuthorizationException(message, var11);
            } finally {
                JdbcUtils.closeConnection(conn);
            }

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
            info.setStringPermissions(permissions);
            return info;
        }
    }

    protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashSet roleNames = new LinkedHashSet();

        try {
            ps = conn.prepareStatement(this.userRolesQuery);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while(rs.next()) {
                String roleName = rs.getString(1);
                if (roleName != null) {
                    roleNames.add(roleName);
                } else if (log.isWarnEnabled()) {
                    log.warn("Null role name found while retrieving role names for user [" + username + "]");
                }
            }
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }

        return roleNames;
    }

    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) throws SQLException {
        PreparedStatement ps = null;
        LinkedHashSet permissions = new LinkedHashSet();

        try {
            ps = conn.prepareStatement(this.permissionsQuery);
            Iterator var6 = roleNames.iterator();

            while(var6.hasNext()) {
                String roleName = (String)var6.next();
                ps.setString(1, roleName);
                ResultSet rs = null;

                try {
                    rs = ps.executeQuery();

                    while(rs.next()) {
                        String permissionString = rs.getString(1);
                        permissions.add(permissionString);
                    }
                } finally {
                    JdbcUtils.closeResultSet(rs);
                }
            }
        } finally {
            JdbcUtils.closeStatement(ps);
        }

        return permissions;
    }

    protected String getSaltForUser(String username) {
        return username;
    }

    public static enum SaltStyle {
        NO_SALT,
        CRYPT,
        COLUMN,
        EXTERNAL;

        private SaltStyle() {
        }
    }
}
