package com.bjpowernode.activemq.clientTest;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class Client {
    static ExecutorService executorService = new ThreadPoolExecutor(2,
            10,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    public static HttpClient httpClient=new HttpClient();
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        for(int j = 0; j < 1; j++){
            List<Integer> list = new ArrayList();
            for(int i = 0; i < 10; i++){
                list.add(i);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(final Integer integer : list){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName()+"="+integer);
                            System.out.println(Thread.currentThread().getName()+"---Send message-"+count.getAndIncrement());
                            PostMethod post = new PostMethod("http://127.0.0.1:8080/springactivemq-02-p2p/mq/test01");
                            //post.addParameter("msg", "Hello world!");
                            httpClient.executeMethod(post);
                            System.out.println(Thread.currentThread().getName()+"---Send message Success-"+count.getAndIncrement());

                            Thread.sleep(5000);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
            }
            //不能写在这里会抛异常
            //executorService.shutdown();
        }
        //应该写在最后这里
        executorService.shutdown();
    }

}


