package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;

@Component
@WebServlet("/stayLogIn")
public class CheckLogIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("successful1");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        boolean alreadyLogIn = false;
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        System.out.println("Check Session ID: " + session.getId());
        System.out.println("Check Creation Time: " + new Date(session.getCreationTime()));
        System.out.println("Check Last Accessed Time: " + new Date(session.getLastAccessedTime()));
        out.println();
        out.println();
        out. println();

        // 判断cookie是否有username，如果有代表登陆过
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // 表明已经登陆过了，就直接跳转了
                    System.out.println("Already Log in");
                    alreadyLogIn = true;
                }
            }
        }

        if(alreadyLogIn) {
            response.sendRedirect(request.getContextPath() + "/successLogIn");
        }else {
            System.out.println("PLease Log in");
            response.sendRedirect(request.getContextPath() + "/logInAgain");
            out.write("<html>"
                    + "<head><script type='text/javascript'> alert('Never Login, please login!');location='login.html';</script></head>"
                    + "<body></body></html>");
//                        response.sendRedirect(request.getContextPath()+"/");

        }
    }
}
