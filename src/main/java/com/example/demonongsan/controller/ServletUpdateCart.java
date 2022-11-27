package com.example.demonongsan.controller;

import com.example.demonongsan.model.Item;
import com.example.demonongsan.model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet(name = "ServletUpdateCart", value = "/ServletUpdateCart")
public class ServletUpdateCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DecimalFormat df = new DecimalFormat("#.000");

    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/ServletCart");
        dispatcher.forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        order.setSumPrice(0);
        for(Item item: listItems)
        {
            item.setQty(Integer.parseInt(req.getParameter(item.getProduct().getId())));
            item.setPrice((Double.parseDouble(item.getProduct().getPrice()) - Double.parseDouble(item.getProduct().getPrice())*(Double.parseDouble(item.getProduct().getDiscount())/100))*Double.parseDouble(req.getParameter(item.getProduct().getId())));
            order.setSumPrice(order.getSumPrice() + item.getPrice());
        }
        order.setItems(listItems);
        session.setAttribute("order", order);
        session.setAttribute("sumprice", df.format(order.getSumPrice()));
        resp.sendRedirect(req.getContextPath() + "/ServletCart");
    }
}
