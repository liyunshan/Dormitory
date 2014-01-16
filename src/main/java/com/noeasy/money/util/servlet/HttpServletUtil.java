/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author acer
 */
public class HttpServletUtil {

    public final static String METHOD_POST = "POST";
    private static final Log logger = LogFactory.getLog(HttpServletUtil.class);

    public static boolean isPost(HttpServletRequest request) {
        if (METHOD_POST.equals(request.getMethod())) {
            return true;
        }
        return false;
    }

    public static void populateWithJSON(HttpServletResponse response, String jsonStr) {
        if (null != jsonStr) {
            PrintWriter out = null;
            try {
                // NOTE: proper JSON content type
                response.setContentType("application/json;charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                out = response.getWriter();
                out.write(jsonStr);
            } catch (IOException e) {
                logger.error(e, e);
            } finally {
                if (null != out) {
                    out.close();
                }
            }
        }
    }

    public static void populateWithXMl(HttpServletResponse response, String xmlStr) {
        if (null != xmlStr) {
            PrintWriter out = null;
            try {
                response.setContentType("application/xml");
                out = response.getWriter();
                out.print(xmlStr);
            } catch (IOException e) {
                logger.error(e, e);
            } finally {
                if (null != out) {
                    out.close();
                }
            }
        }

    }

    public static String getDomain(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        String domain = requestURL.substring(0, requestURL.length() - requestURI.length());
        return domain;
    }

    public static String getWebsite(HttpServletRequest request) {
        String website = getDomain(request);
        String applicationName = request.getContextPath() + "/";
        website += applicationName;
        return website;
    }
}
