package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

@Component
@WebServlet("/quitLogIn")
public class QuitLogIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        System.out.println("Logout Session ID: " + session.getId());
        System.out.println("Logout Creation Time: " + new Date(session.getCreationTime()));
        System.out.println("Logout Last Accessed Time: " + new Date(session.getLastAccessedTime()));
        out.println();
        out.println();
        out. println();
        //we set cookie of username to 0，which means delete it straightaway
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // this means current username already logged in
                    System.out.println("Quit Log In");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        response.sendRedirect(request.getContextPath()+"/");
    }

}
