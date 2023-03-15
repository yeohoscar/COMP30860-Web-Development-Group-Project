package com.yysw.login;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@WebServlet("/quitLogIn")
public class QuitLogIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        boolean alreadyLogIn = false;
        if(session != null) {
            PrintWriter out = response.getWriter();
            System.out.println("Logout Session ID: " + session.getId());
            System.out.println("Logout Creation Time: " + new Date(session.getCreationTime()));
            System.out.println("Logout Last Accessed Time: " + new Date(session.getLastAccessedTime()));
            out.println();
            out.println();
            out.println();
            //we set cookie of username to 0，which means delete it straightaway
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println("cookie name = "+cookie.getName());
                    if (cookie.getName().equals("sessionId")) { // this means current username already logged in
                        alreadyLogIn = true;
                        System.out.println("Quit Log In\n");
                        cookie.setMaxAge(0);
                        session.invalidate();
                        response.addCookie(cookie);
                    }
                }
            }
            System.out.println();
            if(alreadyLogIn){
                response.sendRedirect(request.getContextPath() + "/");
            }else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
