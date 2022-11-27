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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletAddtoCart", value = "/ServletAddtoCart")
public class ServletAddtoCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();
    DecimalFormat df = new DecimalFormat("#.000");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int n= 0;
        int qty = 1;
        String id;
        if(request.getParameter("product-id")!=null) {
            id = request.getParameter("product-id");
            Product product = productService.get(Integer.parseInt(id));;
            if(product != null) {
                if(request.getParameter("qty")!=null) {
                    qty = Integer.parseInt(request.getParameter("qty"));
                }

                HttpSession session = request.getSession();
                if(session.getAttribute("order") == null) {
                    Order order = new Order();
                    List<Item> listItems = new ArrayList<Item>();
                    Item item = new Item();
                    item.setQty(qty);
                    item.setProduct(product);
                    item.setPrice(Double.parseDouble(product.getPrice()) - Double.parseDouble(product.getPrice())*(Double.parseDouble(product.getDiscount())/100));
                    order.setSumPrice(0);
                    order.setSumPrice(order.getSumPrice() + item.getPrice());
                    listItems.add(item);
                    order.setItems(listItems);
                    n = listItems.size();
                    session.setAttribute("length_order",n);
                    session.setAttribute("order", order);
                    session.setAttribute("sumprice", df.format(order.getSumPrice()));
                } else {
                    Order order = (Order) session.getAttribute("order");
                    List<Item> listItems = order.getItems();
                    boolean check = false;
                    for(Item item : listItems) {
                        if(Integer.parseInt(item.getProduct().getId()) == Integer.parseInt(product.getId())) {
                            item.setQty(item.getQty() + qty);
                            order.setSumPrice(order.getSumPrice() + Double.parseDouble(item.getProduct().getPrice()) - Double.parseDouble(item.getProduct().getPrice())*(Double.parseDouble(item.getProduct().getDiscount())/100));
                            item.setPrice(item.getPrice() + (Double.parseDouble(item.getProduct().getPrice()) - Double.parseDouble(item.getProduct().getPrice())*(Double.parseDouble(item.getProduct().getDiscount())/100)));
                            check = true;
                        }
                    }
                    if(check == false) {
                        Item item = new Item();
                        item.setQty(qty);
                        item.setProduct(product);
                        item.setPrice(Double.parseDouble(product.getPrice()) - Double.parseDouble(item.getProduct().getPrice())*(Double.parseDouble(item.getProduct().getDiscount())/100));
                        order.setSumPrice(order.getSumPrice() + Double.parseDouble(item.getProduct().getPrice()) - Double.parseDouble(item.getProduct().getPrice())*(Double.parseDouble(item.getProduct().getDiscount())/100));
                        listItems.add(item);
                    }
                    n = listItems.size();
                    session.setAttribute("length_order",n);
                    session.setAttribute("order", order);
                    session.setAttribute("sumprice", df.format(order.getSumPrice()));
                }
            }
            response.sendRedirect(request.getContextPath() + "/ServletProductListClient");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
