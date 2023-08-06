package ru.rail;

import by.javaguru.entity.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
    private ProductService productService;

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
        // For this example, no session attributes are used.
        req.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int quantityInt = Integer.parseInt(req.getParameter("quantity"));
        productService.addNewProduct(new Product(name, quantityInt));
        req.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(req, resp);

    }
}