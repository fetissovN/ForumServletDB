package com.nick.forum.servlets;

import com.nick.forum.entity.Message;
import com.nick.forum.entity.User;
import com.nick.forum.service.message.MessageServiceImpl;
import com.nick.forum.utils.ErrorString;
import com.nick.forum.utils.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "/")
public class Home extends HttpServlet {

    private MessageServiceImpl messageService;
    public void init(){
        messageService = MessageServiceImpl.getInstance();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user==null){
                response.sendRedirect("/login");
            }else {
                request.setCharacterEncoding("UTF-8");
                String messageStr = request.getParameter(StringUtils.MESSAGE);
                if (StringUtils.isNotEmpty(messageStr)){
                    if (StringUtils.lettersLimit(50,messageStr)){
                        Message message = new Message();
                        message.setMessage(messageStr);
                        message.setUserId(user.getId());

                        Date date = new Date(System.currentTimeMillis());

                        Timestamp timestamp = new Timestamp(date.getTime());

                        message.setMessage_date(timestamp);
                        try {
                            messageService.save(message);
                            response.sendRedirect("/");

                        } catch (SQLException e) {
                            request.setAttribute("wrong", ErrorString.SQL_ERR);
                        }
                    }else {
                        request.setAttribute("wrong", ErrorString.TOO_LONG);
                    }
                }else {
                    request.setAttribute("wrong", ErrorString.EMPTY_STR);
                }
            }
            if (request.getAttribute("wrong")!=null){
                request.setAttribute("list",messageService.getListAll());
                request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request,response);
            }
        } catch (ServletException | IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> list = null;
        try {
            list = messageService.getListAll();
            list.sort(Comparator.reverseOrder());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("list",list);

        request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request,response);
    }
}
