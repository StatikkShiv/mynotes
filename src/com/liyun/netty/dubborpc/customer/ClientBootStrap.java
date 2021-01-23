package com.liyun.netty.dubborpc.customer;

import com.liyun.netty.dubborpc.netty.NettyClient;
import com.liyun.netty.dubborpc.publicinterface.HelloService;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-11 16:24
 */
public class ClientBootStrap {

    //这里定义协议头
    public static final String providerName = "netty#rpc#";

    public static void main(String[] args) throws InterruptedException {
        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);

        for(int i=0;i<10;i++){
            Thread.sleep(10*1000);
            //通过代理对象调用服务提供者的方法（服务）
            String res = service.hello("你好 dubbo");
            System.out.println("调用的结果 res= "+res);
        }

    }
}
