/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author acer
 */
public class HTMLEscapeRequestWrapper extends HttpServletRequestWrapper  {
    public HTMLEscapeRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.escapeHtml(this.getRequest().getParameter(name));
    }
}
