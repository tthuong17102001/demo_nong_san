package com.example.demonongsan.controller;

import com.example.demonongsan.model.Item;
import com.example.demonongsan.model.Order;
import com.example.demonongsan.model.Product;
import com.example.demonongsan.service.ProductService;
import com.example.demonongsan.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet(name = "ServletDeleteProductInCart", value = "/ServletDeleteProductInCart")
public class ServletDeleteProductInCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productservice = new ProductServiceImpl();
    DecimalFormat df = new DecimalFormat("#.000");
    DecimalFormat df1 = new DecimalFormat("#.0");

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        HttpSession session = req.getSession(true);
        Product product = productservice.get(Integer.parseInt(id));
        Order order = (Order) session.getAttribute("order");
        List<Item> listItems = order.getItems();
        for(Item item: listItems)
        {
            if(Integer.parseInt(item.getProduct().getId()) == Integer.parseInt(product.getId()))
            {
                order.setSumPrice(order.getSumPrice() - item.getPrice());
                listItems.remove(item);
                break;
            }
        }
        order.setItems(listItems);
        session.setAttribute("order", order);
        resp.sendRedirect(req.getContextPath() + "/ServletCart");
        if(order.getSumPrice() == 0)
        {
            session.setAttribute("sumprice", "0");
        } else {
            session.setAttribute("sumprice", df.format(order.getSumPrice()));
        }

    }
}
