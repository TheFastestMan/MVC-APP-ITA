package by.javaguru.dao;

import by.javaguru.entity.User;
import by.javaguru.exception.DaoException;
import by.javaguru.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private static final String SAVE_SQL = """
            INSERT INTO users (  
              username, email, age, login, password
            ) 
            values 
            (?,?,?,?,?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
             SET username = ?, email = ?, age = ?, login = ?, password = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, username, email, age, login, password FROM users
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?;
            """;


    public boolean update(User user) {

        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {

            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setInt(3, user.getAge());
            prepareStatement.setString(4, user.getLogin());
            prepareStatement.setString(5, user.getPassword());

            prepareStatement.setLong(6, user.getId());

            return prepareStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public List<User> findAll() {

        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            List<User> users = new ArrayList<>();

            var result = prepareStatement.executeQuery();

            while (result.next())
                users.add(buildUser(result));
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static User buildUser(ResultSet result) throws SQLException {
        return new User(result.getLong("id"),
                result.getString("username"),
                result.getString("email"),
                result.getInt("age"),
                result.getString("login"),
                result.getString("password"));
    }


    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            prepareStatement.setLong(1, id);

            User user = null;
            var result = prepareStatement.executeQuery();

            while (result.next()) {
                user = buildUser(result);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    public User save(User user) {

        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(SAVE_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setString(1, user.getName());
            prepareStatement.setString(2, user.getEmail());
            prepareStatement.setInt(3, user.getAge());
            prepareStatement.setString(4, user.getLogin());
            prepareStatement.setString(5, user.getPassword());

            prepareStatement.executeUpdate();

            var keys = prepareStatement.getGeneratedKeys();

            if (keys.next())
                user.setId(keys.getLong("id"));
            return user;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
//