package com.bjpowernode.activemq.controller;

import com.bjpowernode.activemq.mq.consumer.queue.ConsumerService;
import com.bjpowernode.activemq.mq.producer.queue.QueueSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 *
 */
@Controller
@RequestMapping("/mq")
public class ActiveMqController {

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private ConsumerService consumerService;
    @Resource
    private Destination destination;

    private Logger logger=Logger.getLogger(ActiveMqController.class);
    @RequestMapping(value = "/test01",method = RequestMethod.POST)
    public @ResponseBody Object test01(){
        System.out.println("国庆节快乐");
        logger.info("我爱你祖国");
        return "hello activemq";
    }

    //发送队列
    @RequestMapping(value = "/SendMessage",method = RequestMethod.POST)
    public @ResponseBody Object sendQueue(@RequestParam("msg") String msg){

        //消息发送到指定的queue
        queueSender.sendMessage(msg);

        return "success";
    }

    @RequestMapping("/ReceiveMessage")
    public @ResponseBody Object receive(){
        logger.info(Thread.currentThread().getName()+"------------receive from jms Start");
        TextMessage tm = consumerService.receive(destination);
        logger.info(Thread.currentThread().getName()+"------------receive from jms End");
        return tm;
    }

}
