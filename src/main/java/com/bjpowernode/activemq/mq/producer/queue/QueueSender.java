package com.bjpowernode.activemq.mq.producer.queue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 *
 */
@Service()
public class QueueSender {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 发送一条消息到指定的队列（目标）
     */
    public void sendMessage(Destination destination, final String msg) {
        System.out.println(Thread.currentThread().getName()+" 指定队列"
                + destination.toString()+ "发送消息------>"+msg);
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向默认队列发送消息
     */
    public void sendMessage(final String msg) {
        String destination = jmsTemplate.getDefaultDestinationName();
        System.out.println(Thread.currentThread().getName() + " 默认队列"
                + destination + "发送消息======>" + msg);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
