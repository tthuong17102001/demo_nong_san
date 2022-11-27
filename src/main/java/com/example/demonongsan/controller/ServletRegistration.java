package com.example.demonongsan.controller;

import com.example.demonongsan.dao.impl.RegisterDao;
import com.example.demonongsan.jdbc.connectDB;
import com.example.demonongsan.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletRegistration", value = "/ServletRegistration")
public class ServletRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/client/register.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String created = request.getParameter("created");

        User user = new User(username, password, email, phone, name, created);

        RegisterDao register = new RegisterDao(connectDB.getConnect());
        if (register.RegisterUser(user)) // On success, you can display a message to user on Home page
        {
            request.setAttribute("Message", "Bạn đã tạo tài khoàn thành công. Mời bạn đăng nhập <a href='ServletLogin'>tại đây!</a>");
            RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
            RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
            rd.forward(request, response);
        }
    }
}
