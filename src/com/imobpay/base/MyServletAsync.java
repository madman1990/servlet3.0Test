package com.imobpay.base;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(value = "/filterSyn", asyncSupported = true)
public class MyServletAsync extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("进入Servlet的时间：" + new Date() + ".");
        out.flush();
        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
        /**
         * 除此之外，Servlet 3.0 还为异步处理提供了一个监听器，使用 AsyncListener 接口表示。它可以监控如下四种事件：
         异步线程开始时，调用 AsyncListener 的 onStartAsync(AsyncEvent event) 方法；
         异步线程出错时，调用 AsyncListener 的 onError(AsyncEvent event) 方法；
         异步线程执行超时，则调用 AsyncListener 的 onTimeout(AsyncEvent event) 方法；
         异步执行完毕时，调用 AsyncListener 的 onComplete(AsyncEvent event) 方法；
         */
        AsyncContext ctx = req.startAsync();
        ctx.addListener(new AsyncListener() {

            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                System.out.println("完成的方法...");
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                System.out.println("超时的方法...");

            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                System.out.println("异常的方法...");

            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                System.out.println("开始的方法...");

            }
        });
        new Thread(new Executor(ctx)).start();
        out.println("结束Servlet的时间：" + new Date() + ".");
        out.flush();
    }
}

class Executor implements Runnable {
    private AsyncContext ctx = null;

    public Executor(AsyncContext ctx) {
        this.ctx = ctx;
    }

    public void run() {
        try {
            //等待十秒钟，以模拟业务方法的执行
            Thread.sleep(10000);
            PrintWriter out = ctx.getResponse().getWriter();
            out.println("业务处理完毕的时间：" + new Date() + ".");
            out.flush();
            ctx.complete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
