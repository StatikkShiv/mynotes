package com.liyun.artofjavaconcurrency.code4_11;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNofiy {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock){
                //当条件不满足时，继续wait,同时释放了lock的锁
              while(flag){
                  try{
                      System.out.println(Thread.currentThread()+" flag是true 等待@ "+new SimpleDateFormat("HH:mm::ss").format(new Date()));
                      lock.wait();
                  }catch (InterruptedException e){

                  }
              }
              //条件满足时，完成工作
                System.out.println(Thread.currentThread()+" flag是false 执行@ "+new SimpleDateFormat("HH:mm::ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock){
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁
                //知道当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread()+" 获取到锁 notify休眠@ "+new SimpleDateFormat("HH:mm::ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //再次加锁
            synchronized(lock){
                System.out.println(Thread.currentThread()+" 再次拿到锁休眠& "+new SimpleDateFormat("HH:mm::ss").format(new Date()));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
