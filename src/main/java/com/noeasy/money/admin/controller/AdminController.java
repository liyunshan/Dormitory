/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author acer
 */
@Controller
public class AdminController {
    @RequestMapping(value = "/admin/home.html")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "admin/home";
    }
     @RequestMapping(value = "/admin/merchandise.html")
    public String merchandise(HttpServletRequest request, HttpServletResponse response) {
        return "admin/merchandise";
    }
}
