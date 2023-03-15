package com.yysw.login;

import com.yysw.user.User;
import com.yysw.user.UserRepository;
import com.yysw.user.customer.Customer;
import com.yysw.user.owner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
@WebServlet("/submit-login")
//@Controller
public class PersistentLogIn extends HttpServlet {
    @Autowired
    private UserRepository userRepository;
    private static final long serialVersionUID = 1L;
    public PersistentLogIn() {
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.setMaxInactiveInterval(-1);
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        System.out.println("current username that wants to login = " + username);

        //obtain acc from database
        User repoUser = userRepository.findByUsernameAndPasswd(username, password);

        PrintWriter out = response.getWriter();
        System.out.println("Persist Session ID: " + session.getId());
        System.out.println("Persist Creation Time: " + new Date(session.getCreationTime()));
        System.out.println("Persist Last Accessed Time: " + new Date(session.getLastAccessedTime()));

        //说明还没有用户登录
        if(session.getAttribute("user") == null) {
            session.setAttribute("user", repoUser);
            if (repoUser != null) {
                if (repoUser instanceof Customer) {
                    if (repoUser.getUsername().equals(username)) {
                        if (repoUser.getPasswd().equals(password)) {
                            Cookie cookie = new Cookie("sessionId", sessionId);
                            cookie.setMaxAge(-1);//let's say the cookie is valid in two minutes
                            response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in
                            response.sendRedirect("/");
                        } else {
                            out.write("<html"
                                    + "<head><script type='text/javascript'> alert('Wrong Customer Password!');location='login';</script></head>"
                                    + "<body></body></html>");
                        }
                        //check if customer logs in successfully
                    } else {
                        out.write("<html>"
                                + "<head><script type='text/javascript'> alert('Wrong Customer Username!');location='login';</script></head>"
                                + "<body></body></html>");
                    }
                } else if (repoUser instanceof Owner) {
                    if (repoUser.getUsername().equals(username)) {
                        if (repoUser.getPasswd().equals(password)) {
                            Cookie cookie = new Cookie("sessionId", sessionId);
                            cookie.setMaxAge(-1);//let's say the cookie is valid in two minutes
                            response.addCookie(cookie);//server return this cookie to browser so that it can be checked next time when user log in
                            response.sendRedirect("/successLogIn");
                        } else {
                            out.write("<html"
                                    + "<head><script type='text/javascript'> alert('Wrong Admin Password!');location='login';</script></head>"
                                    + "<body></body></html>");
                        }
                        //check if customer logs in successfully
                    } else {
                        out.write("<html>"
                                + "<head><script type='text/javascript'> alert('Wrong Admin Username!');location='login';</script></head>"
                                + "<body></body></html>");
                    }
                }
            }
            else {
                out.write("<html>"
                        + "<head><script type='text/javascript'> alert('Account Does Not Exist!');location='login';</script></head>"
                        + "<body></body></html>");
            }
        }
        //否则, 已经有一个账户登录了
        else {
            response.sendRedirect("/logInOccupied");
        }
    }

}