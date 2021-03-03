package com.lyf.base.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by liyf on 2020-03-01
 */
public class BaseController implements Serializable {

    protected ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();
    protected ThreadLocal<HttpServletResponse> response = new ThreadLocal<>();
    protected ThreadLocal<HttpSession> session = new ThreadLocal<>();

    public HttpServletRequest getRequest() {
        return request.get();
    }

    public void setRequest(HttpServletRequest request) {
        this.request.set(request);
    }

    public HttpServletResponse getResponse() {
        return response.get();
    }

    public void setResponse(HttpServletResponse response) {
        this.response.set(response);
    }

    public HttpSession getSession() {
        return session.get();
    }

    public void setSession(HttpSession session) {
        this.session.set(session);
    }


    /**
     * 初始化
     */
    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        this.setRequest(request);
        this.setResponse(response);
        this.setSession(request.getSession());
    }
}
