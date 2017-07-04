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


@WebServlet(value = "/login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response,
                       HttpSession session) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nick = request.getParameter(StringUtils.NICK);
        String email = request.getParameter(StringUtils.EMAIL);

        User user = new User();
        user.setNick(nick);
        user.setEmail(email);

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (!userService.userExists(user)){
                user.setDate(new Date(System.currentTimeMillis()));
                userService.saveUser(user);
                User userNew = userService.getUserByEmail(user.getEmail());
                session.setAttribute("user", userNew);
                response.sendRedirect("/");
            }
        }catch (SQLException e){
            request.setAttribute("wrong", ErrorString.LOGIN_ERR);
            request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request,response);
        }
    }
}
