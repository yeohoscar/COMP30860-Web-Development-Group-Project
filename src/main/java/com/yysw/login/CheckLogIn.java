package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Component
@WebServlet("/stayLogIn")
public class CheckLogIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("successful1");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        boolean flag = false;
        PrintWriter out = response.getWriter();

        // 判断cookie是否有username，如果有代表登陆过
        Cookie[] cookies = request.getCookies();
//        System.out.println("the number of cookies = "+cookies.length);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                System.out.println("cookie name: "+ URLDecoder.decode(cookie.getName(), "utf-8"));
//                System.out.println("tttttthe username in cookie is ="+URLDecoder.decode(cookie.getName(), "utf-8"));
//                System.out.println("username is ="+cookie.getName());
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // 表明已经登陆过了，就直接跳转了
//                    System.out.println("the username in cookie is ="+URLDecoder.decode(cookie.getName(), "utf-8"));
                    System.out.println("Already Log in");
                    flag = true;
                }
            }
        }

        if(flag) {
            response.sendRedirect(request.getContextPath() + "/");
        }else {
            System.out.println("PLease Log in");
            out.write("<html>"
                    + "<head><script type='text/javascript'> alert('没有登陆过，请登录!');location='login.html';</script></head>"
                    + "<body></body></html>");
//                        response.sendRedirect(request.getContextPath()+"/");

        }
    }
}
