package com.example.demonongsan.controller;

import com.example.demonongsan.model.Boardnew;
import com.example.demonongsan.service.BoardnewService;
import com.example.demonongsan.service.impl.BoardnewServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletBoardnewListClient", value = "/ServletBoardnewListClient")
public class ServletBoardnewListClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BoardnewService boardnewService = new BoardnewServicesImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Boardnew> boardnewList = boardnewService.getAll();
        request.setAttribute("boardnewlist", boardnewList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/blog-archive.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
