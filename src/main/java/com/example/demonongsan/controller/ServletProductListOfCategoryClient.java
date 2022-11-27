package com.example.demonongsan.controller;

import com.example.demonongsan.model.Catalog;
import com.example.demonongsan.model.Product;
import com.example.demonongsan.service.CategoryService;
import com.example.demonongsan.service.ProductService;
import com.example.demonongsan.service.impl.CategoryServicesImpl;
import com.example.demonongsan.service.impl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletProductListOfCategoryClient", value = "/ServletProductListOfCategoryClient")
public class ServletProductListOfCategoryClient extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();
    DecimalFormat df = new DecimalFormat("#.000");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        String id = req.getParameter("id");
        List<Product> productList = productService.getProductById(Integer.parseInt(id));
        req.setAttribute("productlist", productList);
        //Giá giảm
        List<Product> productsList1 = new ArrayList<Product>();
        for(Product product: productList)
        {
            Product product1 = productService.get(Integer.parseInt(product.getId()));
            product1.setPrice(String.valueOf(df.format(Double.parseDouble(product.getPrice()) * (1 - (Double.parseDouble(product.getDiscount())/100)))));
            productsList1.add(product1);

        }

        req.setAttribute("productlist1", productsList1);

        // Product bán chạy
        List<Product> product_banchay= productService.getProductById(6);
        req.setAttribute("product_banchay", product_banchay);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product.jsp");
        dispatcher.forward(req, resp);
    }
}
