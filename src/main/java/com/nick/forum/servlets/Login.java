package com.nick.forum.servlets;

import com.nick.forum.entity.User;
import com.nick.forum.service.user.UserServiceImpl;
import com.nick.forum.utils.ErrorString;
import com.nick.forum.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;


@WebServlet(value = "/login")
public class Login extends HttpServlet {
    private UserServiceImpl userService;

    public void init(){
        userService = UserServiceImpl.getInstance();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nick = request.getParameter(StringUtils.NICK);
        String email = request.getParameter(StringUtils.EMAIL);

        User user = new User();
        user.setNick(nick);
        user.setEmail(email);

        try {
            if (!userService.userExists(user)){
                Date date = new Date(System.currentTimeMillis());
                Timestamp timestamp = new Timestamp(date.getTime());
                user.setDate(timestamp);
                userService.saveUser(user);
                User userNew = userService.getUserByEmail(user.getEmail());
                HttpSession session = request.getSession();
                session.setAttribute("user", userNew);
                session.setMaxInactiveInterval(30*60);
                response.sendRedirect("/");
            }
        }catch (SQLException e){
            request.setAttribute("wrong", ErrorString.LOGIN_ERR);
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,response);
        }
    }
}
