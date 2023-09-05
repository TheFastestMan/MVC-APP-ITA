package by.javaguru;

import by.javaguru.dao.UserDao;
import by.javaguru.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;

    public UserService() throws IOException {
        userDao = new UserDao();
    }

    public void updateUser(User user) throws IOException {
        userDao.update(user);
    }


    public void addNewUser(String name, Integer age, String email, String login, String password) throws IOException {
        List<User> users = userDao.findAll();
        Long maxId = users.stream().map(User::getId).max(Long::compareTo).orElse(0L);
        User user = new User(maxId + 1, name, email, age, login, password);

        userDao.save(user);
    }

    public Optional<UserDto> getUserById(Long id) {
        return userDao.findById(id)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(),
                        user.getAge(), user.getLogin(), user.getPassword()));
    }

}