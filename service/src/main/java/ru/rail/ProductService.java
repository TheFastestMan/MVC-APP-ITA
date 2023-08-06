package ru.rail;

import by.javaguru.dao.ProductDao;
import by.javaguru.entity.Product;

import java.io.IOException;
import java.util.List;

public class ProductService {
    private ProductDao productDao;
    private Product product;

    public ProductService() throws IOException {
        productDao = new ProductDao();
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public void addNewProduct(Product product) throws IOException {
        productDao.createNewProduct(product);
    }

    public boolean deleteProductByName(String productName) throws IOException {
        return productDao.deleteProduct(productName);
    }
}