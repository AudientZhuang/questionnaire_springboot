package com.zqu.yiban.questionnaire.intercepter;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginHandleIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession.getAttribute("s_no") != null && httpSession.getAttribute("s_name") != null) {
            return true;
        }
        String url = httpServletRequest.getRequestURI();

        if (url.indexOf("login") > 0 || url.indexOf("changePsd") > 0) {
            return true;
        }

        String basePath = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + 80 + "/";
        httpServletResponse.sendRedirect(basePath);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}