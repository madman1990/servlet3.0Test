package com.imobpay.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = {"/uploadFile"}, asyncSupported = true)
public class MyServletUploadFile extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("image");
        System.out.println(part.getContentType());
        System.out.println(part.getSubmittedFileName());
        System.out.println(part.getSize());
        System.out.println(part.getName());
        part.write("d:/madman.jpg");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        System.out.println("name :" + name + ",age=" + age);
        resp.getWriter().write("WebServlet test success ~~");
    }
}
