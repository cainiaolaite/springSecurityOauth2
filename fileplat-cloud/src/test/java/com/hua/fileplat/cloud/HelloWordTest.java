package com.hua.fileplat.cloud;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.FileWriter;
import java.io.IOException;

public class HelloWordTest {
    @Test
    public void inputHelloWord(){
        //Assert.assertEquals("HelloWord",new HelloWord().input());
    }

    @Test
    public void springAopTest()throws Exception{
        /*ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        UserService userService=(UserService)applicationContext.getBean("userServiceImpl");
        userService.add();
        //DataSourceTransactionManager dataSourceTransactionManager=(DataSourceTransactionManager)applicationContext.getBean("transactionManager");
        RoleService roleService=(RoleService)applicationContext.getBean("roleServiceImpl");
        //List<RoleDto> roleList= roleService.selectRole();
        roleService.transactionTest();
        roleService.selectRole();*/

        /*ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://192.168.31.233:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue=session.createQueue("test");
        TextMessage textMessage=session.createTextMessage();
        textMessage.setText("122312311");
        MessageProducer messageProducer=session.createProducer(queue);
        messageProducer.send(textMessage);
        MessageConsumer messageConsumer=session.createConsumer(queue);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        messageConsumer.receive();*/


        /*Topic topic = session.createTopic("receive");
        MessageConsumer consumer1 = session.createConsumer(topic);
        consumer1.receive(600000);
        consumer1.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                System.out.println("收到一条消息: 开始收听消息");
                TextMessage tm = (TextMessage) message;
                try {

                    fw.append(tm.getText()+"\r\n");
                    System.out.println(tm.getText());
                    fw.flush();

                    //System.out.println("Received message: " + tm.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        });*/
    /*session.close();
    connection.stop();
    connection.close();*/
    }
}
