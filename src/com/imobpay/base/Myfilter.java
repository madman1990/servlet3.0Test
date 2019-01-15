package com.imobpay.base;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, asyncSupported = true, filterName = "madmanFilter", initParams = {@WebInitParam(name = "name", value = "madman"), @WebInitParam(name = "age", value = "25")})
public class Myfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String name = filterConfig.getInitParameter("name");
        String age = filterConfig.getInitParameter("age");
        System.out.println("name :" + name + ",age=" + age);
        System.out.println("过滤器初始化的时候...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("我正在努力过滤....");
        String contextPath = servletRequest.getServletContext().getServletContextName();
        String realPath = servletRequest.getServletContext().getRealPath("");
        System.out.println("contextPath:" + contextPath + ",realPath:" + realPath);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器快要死啦...");
    }
}
