package com.liyun.DesignPattern.bridge;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-15 15:05
 */
public class Xiaomi implements Brand {
    @Override
    public void open() {
        System.out.println(" 小米手机开机 ");
    }

    @Override
    public void close() {
        System.out.println(" 小米手机关机 ");
    }

    @Override
    public void call() {
        System.out.println(" 小米手机打电话 ");
    }
}
