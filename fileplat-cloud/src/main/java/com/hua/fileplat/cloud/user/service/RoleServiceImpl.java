package com.hua.fileplat.cloud.user.service;

import com.hua.fileplat.cloud.user.dao.RoleDao;
import com.hua.fileplat.cloud.user.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
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

    @Autowired
    public JmsTemplate jmsTemplate;

    /**
     * 嵌套回滚  https://www.cnblogs.com/duanxz/p/4746892.html
     * @return
     */
    @Override
    @Transactional
    public boolean transactionTest1() {
        List<RoleDto> roleDtoList=selectRole();
        RoleDto roleDto= roleDtoList.get(0);
        /*try{
            transactionTest2(roleDto);
        }catch (Exception e){
        }finally {
            System.out.println(roleDto.getId());
        }*/
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("hahha1sssssss");
        stringBuffer.append(new Date().getTime());
        roleDto.setName(stringBuffer.toString());
        roleDao.updateRoleDto(roleDto);
        return false;
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


    @Override
    @Transactional
    public boolean jmsTransactionTest() {
        MessageCreator messageCreator=new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message=session.createMessage();
                message.setStringProperty("hello","hello,Jms");
                return message;
            }
        };
        jmsTemplate.send("dynamicDestinationResolver",messageCreator);
        //transactionTest();
        return false;
    }
}
