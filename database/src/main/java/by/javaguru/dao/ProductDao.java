package by.javaguru.dao;

import by.javaguru.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private List<Product> productList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String PRODUCT_PATH = "/Users/rail/IdeaProjects/SkillSpace_Enterprise/1JAKARTA_MAVEN_TOMCAT/MVC-APP-ITA" +
            "/web/src/main/resources/product.json";
    private long currentId = 0;

    public ProductDao() throws IOException {
        File file = getFile();
        FileInputStream fis = new FileInputStream(file);

        if (file.length() != 0) {
            this.productList = objectMapper.readValue(fis, new TypeReference<List<Product>>() {
            });
        }
    }

    public List<Product> getAllProducts() {
        return this.productList;
    }

    private File getFile() throws IOException {
        File file = new File(PRODUCT_PATH);
        if (!file.exists()) {
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
            }
        }
        return file;
    }

    public void createNewProduct(Product product) throws IOException {
        currentId++;
        product.setId(currentId);
        productList.add(product);
        saveProduct();
    }

    public void saveProduct() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(PRODUCT_PATH), productList);
    }

    public boolean deleteProduct(String name) throws IOException {
        System.out.println("Before Deletion: " + productList);
        boolean isDeleted = productList.removeIf(product -> product.getName().equals(name));
        if (isDeleted) {
            saveProduct();
        }
        System.out.println("After Deletion: " + productList);
        return isDeleted;
    }
}