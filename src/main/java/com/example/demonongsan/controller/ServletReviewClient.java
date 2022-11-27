package com.example.demonongsan.controller;

import com.example.demonongsan.model.Review;
import com.example.demonongsan.service.ReviewService;
import com.example.demonongsan.service.impl.ReviewServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(name = "ServletReviewClient", value = "/ServletReviewClient")
public class ServletReviewClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ReviewService reviewService = new ReviewServicesImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/ServletProductDetail?id=" +req.getParameter("id"));
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        System.out.println("thử: " + id);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String content = req.getParameter("content");
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(date);
        Review review = new Review();
        review.setName(name);
        review.setEmail(email);
        review.setProduct_id(id);
        review.setContent(content);
        review.setCreated(today);
        System.out.println("thử tostring: " + review);
        reviewService.insert(review);
        resp.sendRedirect(req.getContextPath() + "/ServletProductDetail?id=" + id);
    }
}
