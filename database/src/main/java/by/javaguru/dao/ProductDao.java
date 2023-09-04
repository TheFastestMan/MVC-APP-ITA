package by.javaguru.dao;

import by.javaguru.entity.Product;
import by.javaguru.exception.DaoException;
import by.javaguru.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final String FIND_ALL_SQL = """
            SELECT name, quantity FROM products;
            """;
    private static final String SAVE_SQL = """
            INSERT INTO products (name, quantity)
            VALUES(?,?);
            """;
    private static final String DELETE_SQL = """
            DELETE FROM products WHERE
            name = ?;
            """;

    public List<Product> findAll() {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<Product> seats = new ArrayList<>();

            var result = prepareStatement.executeQuery();
            while (result.next())
                seats.add(new Product(
                                result.getString("name"),
                                result.getInt("quantity")
                        )
                );
            return seats;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Product save(Product product) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(SAVE_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, product.getName());
            prepareStatement.setInt(2, product.getQuantity());

            prepareStatement.executeUpdate();
            var keys = prepareStatement.getGeneratedKeys();
            if (keys.next())
                product.setId(keys.getLong("id"));
            return product;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(String name) {

        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(DELETE_SQL)) {
            prepareStatement.setString(1, name);

            return prepareStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
// deleteProduct, saveProduct, createNewProduct