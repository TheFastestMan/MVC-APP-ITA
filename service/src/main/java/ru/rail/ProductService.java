package ru.rail;

import by.javaguru.dao.ProductDao;
import by.javaguru.entity.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductDao productDao;

    public ProductService() throws IOException {
        productDao = new ProductDao();
    }

    public void addNewProduct(Product product) throws IOException {
        productDao.save(product);
    }

    public boolean deleteProductByName(String productName) throws IOException {
        return productDao.delete(productName);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll().stream().map(product -> new Product(product.getName(), product.getQuantity())
        ).collect(Collectors.toList());
    }
}