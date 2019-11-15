package com.liyun.DesignPattern.factory.factorymethod.pizzastore.pizza;

/**
 * @description:
 * @author: xiaoliyu
 * @date: 2019-10-07 12:07
 */
public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦的胡椒披萨");
        System.out.println("伦敦的胡椒披萨 准备原材料");
    }
}
