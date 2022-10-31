package com.beedbclient.custmoer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author yh
 * @date 2022/9/25 下午4:57
 */
public class MqFactory {

    public static Connection GetConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//MQ采用工厂模式来完成连接的创建
        //2.在工厂对象中设置连接信息(ip,port,virtualhost,username,password)
        factory.setHost("120.48.61.75");//设置MQ安装的服务器ip地址
        factory.setPort(5673);//设置端口号
        //MQ通过用户来管理
        factory.setUsername("guest");//设置用户名称
        factory.setPassword("guest");//设置用户密码
        //3.通过工厂对象获取连接
        return factory.newConnection();
    }
}
