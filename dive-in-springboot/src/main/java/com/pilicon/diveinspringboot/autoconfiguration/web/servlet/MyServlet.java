package com.pilicon.diveinspringboot.autoconfiguration.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/my/servlet",asyncSupported = true)
public class MyServlet extends HttpServlet {

    //异步的servlet的演示
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(()->{
            try {
                resp.getWriter().println("Hello World");
                //如果不加这个,会阻塞住(触发完成)
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
