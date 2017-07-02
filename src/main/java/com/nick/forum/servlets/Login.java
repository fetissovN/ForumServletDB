package com.nick.forum.servlets;

import com.nick.forum.entity.User;
import com.nick.forum.service.user.UserServiceImpl;
import com.nick.forum.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet(value = "/login")
public class Login extends HttpServlet {

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

        UserServiceImpl userService = new UserServiceImpl();
//        if (!userService.userExists(user)){
            user.setDate(new Date(System.currentTimeMillis()));
           userService.saveUser(user);
//        }
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,response);

    }

}
