package com.hua.fileplat.cloud.user.service.impl;

import com.hua.base.entity.Result;
import com.hua.fileplat.cloud.login.controller.LoginController;
import com.hua.fileplat.cloud.user.entity.User;
import com.hua.fileplat.cloud.user.dao.UserDao;
import com.hua.fileplat.cloud.user.service.UserService;
import com.hua.fileplat.cloud.user.vo.LoginUserVo;
import com.hua.fileplat.cloud.user.vo.SessionUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 登陆
     * @param loginUserVo    session
     * @param bindingResult  验证结果
     * @param session spring 会话
     * @return
     */
    @Override
    public Result login(LoginUserVo loginUserVo, BindingResult bindingResult, Session session) {
        Result result=new Result();
        if(bindingResult.hasErrors()){
            result.setResult(false);
            String[] stringArray=bindingResult.getSuppressedFields();
            for(String item:stringArray){
                logger.info(session.getId()+" 验证失败:"+item);
            }
            result.setMessage(stringArray[0]);
        }else{
            User user=loginUserVo.toUser();
            List<User> userList=userDao.queryAll(user);
            if(CollectionUtils.isEmpty(userList)){
                result.setResult(false);
                result.setMessage("用户名或密码错误");
            }else{
                result.setResult(true);
                user=userList.get(0);
                SessionUserVo sessionUserVo=new SessionUserVo(user);
                session.setAttribute(SessionUserVo.SESSION_USER_VO,sessionUserVo);
                //TODO 权限日后加载
                result.setResult(true);
                result.setMessage("登陆成功");
            }
        }
        if(result.isResult()){
            logger.info(session.getId()+" 登陆成功");
        }else{
            logger.info(session.getId()+" 登陆失败，原因："+result.getMessage());
        }
        return null;
    }

}