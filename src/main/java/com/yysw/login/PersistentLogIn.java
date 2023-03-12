package com.yysw.login;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Component
@WebServlet("/submit-login")
//@Controller
public class PersistentLogIn extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public PersistentLogIn() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("passwd");

        PrintWriter out = response.getWriter();
        if("123".equals(username)){
            if("123".equals(password)){
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60);//let's say the cookie is valid in two minutes
                response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in


//                response.sendRedirect(request.getContextPath()+"/");
                response.sendRedirect("/");

                System.out.println("successful2");
            }else {
                out.write("<html"
                        +"<head><script type='text/javascript'> alert('Wrong 123 Password!');location='login.html';</script></head>"
                        + "<body></body></html>");
                return;
            }
            //check if customer logs in successfully
        }else {
            out.write("<html>"
                    + "<head><script type='text/javascript'> alert('Wrong Admin Username!');location='login.html';</script></head>"
                    + "<body></body></html>");
            return;
        }

        //check if admin logs in successfully
//        if("admin".equals(username)){
//            if("admin".equals(password)){
//                Cookie cookie = new Cookie("username", username);
//                cookie.setMaxAge(60);//let's say the cookie is valid in two minutes
//                response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in
//
//
////                response.sendRedirect(request.getContextPath()+"/");
//                response.sendRedirect("/");
//
//                System.out.println("successful2");
//            }else {
//                out.write("<html"
//                +"<head><script type='text/javascript'> alert('Wrong Admin Password!');location='login.html';</script></head>"
//                + "<body></body></html>");
//                return;
//            }
//        //check if customer logs in successfully
//        }else if("pig".equals(username)){
//            if("pig".equals(password)){
//                Cookie cookie = new Cookie("username", username);
//                cookie.setMaxAge(120);//let's say the cookie is valid in two hours
//                response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in
//
////                response.sendRedirect(request.getContextPath()+"/");
//                response.sendRedirect("/");
//
//                System.out.println("successful2");
//            }else {
//                out.write("<html"
//                        +"<head><script type='text/javascript'> alert('Wrong Customer Password!');location='login.html';</script></head>"
//                        + "<body></body></html>");
//                return;
//            }
//        }
//        else {
//            out.write("<html>"
//                    + "<head><script type='text/javascript'> alert('Wrong Admin Username!');location='login.html';</script></head>"
//                    + "<body></body></html>");
//            return;
//        }
    }

}
