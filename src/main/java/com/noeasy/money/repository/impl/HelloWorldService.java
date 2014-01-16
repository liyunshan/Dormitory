package com.noeasy.money.repository.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.noeasy.money.model.HelloWroldBean;
import com.noeasy.money.model.User;
import com.noeasy.money.service.IHelloWorldService;

@Service(value = "helloWorldService")
public class HelloWorldService implements IHelloWorldService{

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;
	
	public HelloWroldBean getBean() {
		User user = (User)getSqlSession().selectOne("com.noeasy.money.model.User.selectUser", 1);
		HelloWroldBean bean = new HelloWroldBean();
		bean.setSomebody(user.getName());
		return bean;
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
}
