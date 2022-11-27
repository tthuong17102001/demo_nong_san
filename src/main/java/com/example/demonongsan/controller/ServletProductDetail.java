package com.example.demonongsan.controller;

import com.example.demonongsan.model.Catalog;
import com.example.demonongsan.model.Product;
import com.example.demonongsan.model.Review;
import com.example.demonongsan.service.CategoryService;
import com.example.demonongsan.service.ProductService;
import com.example.demonongsan.service.ReviewService;
import com.example.demonongsan.service.impl.CategoryServicesImpl;
import com.example.demonongsan.service.impl.ProductServiceImpl;
import com.example.demonongsan.service.impl.ReviewServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletProductDetail", value = "/ServletProductDetail")
public class ServletProductDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServicesImpl();
    ProductService productService = new ProductServiceImpl();
    ReviewService reviewService = new ReviewServicesImpl();
    DecimalFormat df = new DecimalFormat("#.000");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product detail_product = productService.get(Integer.parseInt(id));
        req.setAttribute("detail_product", detail_product);

        List<Catalog> name_cate_of_product = cateService.getCateByProduct(Integer.parseInt(id));
        req.setAttribute("name_cate_of_product", name_cate_of_product);

        String idCate = detail_product.getCatalog_id();

        List<Product> productListCate = productService.getProductById(Integer.parseInt(idCate));

        req.setAttribute("productById", productListCate);

        List<Review> reviewById = reviewService.getReviewById(Integer.parseInt(id));
        req.setAttribute("reviewbyid", reviewById);

        List<Product> productList = productService.getAll();
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

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/product-detail.jsp");
        dispatcher.forward(req, resp);
    }
}
