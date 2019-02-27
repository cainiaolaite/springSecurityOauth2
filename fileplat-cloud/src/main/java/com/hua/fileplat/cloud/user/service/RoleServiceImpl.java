package com.hua.fileplat.cloud.user.service;

import com.hua.fileplat.cloud.user.dao.RoleDao;
import com.hua.fileplat.cloud.user.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 角色服务
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleDto> selectRole() {
        return roleDao.selectRole();
    }

    /**
     * 嵌套回滚  https://www.cnblogs.com/duanxz/p/4746892.html
     * @return
     */
    @Override
    @Transactional
    public boolean transactionTest() {
        List<RoleDto> roleDtoList=selectRole();
        RoleDto roleDto= roleDtoList.get(0);
        /*try{
            transactionTest2(roleDto);
        }catch (Exception e){
        }finally {
            System.out.println(roleDto.getId());
        }*/
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("hahha");
        stringBuffer.append(new Date().getTime());
        roleDto.setName(stringBuffer.toString());
        roleDao.updateRoleDto(roleDto);
        int i=2/0;
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean transactionTest2(RoleDto roleDto){
        roleDto.setId("888888");
        int i=2/0;
        return false;
    }
}
