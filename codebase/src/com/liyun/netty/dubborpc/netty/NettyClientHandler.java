package com.liyun.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2021-01-10 21:08
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;//上下文
    private String result;//返回的结果
    private String para;//客户端调用方法时传入的参数

    //1.与服务器的连接创建后，就会被调用,这个方法是第一个被调用的
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive 被调用");
        context = ctx;//因为我在其它方法会使用到ctx
    }

    //4.收到服务器的数据后，调用方法
    //唤醒等待的
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead 被调用");
       result = msg.toString();
       notify();//唤醒等待的线程
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }

    //3(等待4执行完再执行5).被代理对象调用，发送数据给服务器，等待被唤醒 -> wait ->等待被唤醒(channelRead) ->返回结果
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call 被调用");
        context.writeAndFlush(para);
        //进行wait
        wait();//等待channelRead 方法获取到服务器的结果后，唤醒
        System.out.println("call 被调用2");
        return result;//服务方返回的结果
    }

    //2.
    void setPara(String para){
        System.out.println("setPara 被调用");this.para = para;
    }
}