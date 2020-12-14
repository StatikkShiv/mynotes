package com.liyun.DesignPattern.mediator;

//具体的同事类
public class Alarm extends Colleague {

	//构造器
	public Alarm(Mediator mediator, String name) {
		super(mediator, name);
		//在创建Alarm同事对象时，将自己放入到ConcreteMediator对象中[集合]
		mediator.Register(name, this);
	}

	public void SendAlarm(int stateChange) {
		SendMessage(stateChange);
	}

	@Override
	public void SendMessage(int stateChange) {
		//调用中介者对象的getMessge()
		this.GetMediator().GetMessage(stateChange, this.name);
	}

}
