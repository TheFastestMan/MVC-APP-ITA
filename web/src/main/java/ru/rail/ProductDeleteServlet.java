package ru.rail;

import by.javaguru.dao.ProductDao;
import by.javaguru.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/productDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
    private ProductService productService;
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        try {
            productService = new ProductService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/productDelete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String productName = req.getParameter("productName");
            boolean isDeleted = productService.deleteProductByName(productName);

            if (isDeleted) {
                req.setAttribute("successMessage", "Product deleted successfully.");
            } else {
                req.setAttribute("errorMessage", "Product not found.");
            }
            doGet(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Failed to delete product. Internal error.");
        }
    }
}