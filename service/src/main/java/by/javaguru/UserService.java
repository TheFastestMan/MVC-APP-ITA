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

    public void updateUser(long id, String name, String email, int age, String login, String password) throws IOException {
        userDao.update(id, new User(id, name, email, age, login, password));
    }


    public void addNewUser(String name, String age, String email, String login, String password) throws IOException {
        List<User> users = userDao.findAllUsers();
        Long maxId = users.stream().map(User::getId).max(Long::compareTo).orElse(0L);
        User user = new User(maxId + 1, name, email, Integer.parseInt(age), login, password);

        userDao.createNewUser(user);
    }

    public Optional<UserDto> getUserByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(),
                        user.getAge(), user.getLogin(), user.getPassword()));
    }

}