package com.noeasy.money.model;

public class HelloWroldBean {
	
	private String somebody;

	public String sayHello() {
		return getSomebody() + "Hello World";
	}

	public String getSomebody() {
		return somebody;
	}

	public void setSomebody(String somebody) {
		this.somebody = somebody;
	}
	
	
}
