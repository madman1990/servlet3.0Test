package com.imobpay.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/hello", "/madman"}, initParams = {@WebInitParam(name = "name", value = "madman"), @WebInitParam(name = "age", value = "25")})
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println("name :" + name + ",age=" + age );
        resp.getWriter().write("WebServlet test success ~~");
    }
}
