/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.noeasy.money.model.HelloWroldBean;
import com.noeasy.money.service.IHelloWorldService;

/**
 * 
 * @author acer
 */
@Controller
public class HomepageController {

	Log logger = LogFactory.getLog(HomepageController.class);

	IHelloWorldService helloWorldService;

	SessionLocaleResolver localeResolver;

	@Resource(name = "localeResolver")
	public void setLocaleResolver(SessionLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	@Resource(name = "helloWorldService")
	public void setHelloWorldService(IHelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/home.html")
	public String indexPage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		HelloWroldBean bean = this.helloWorldService.getBean();
		model.addAttribute("intro", bean.sayHello());
		return "index";
	}

}
