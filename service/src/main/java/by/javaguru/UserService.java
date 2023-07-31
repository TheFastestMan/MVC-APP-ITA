package by.javaguru;

import by.javaguru.dao.UserDao;
import by.javaguru.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao;

    public UserService() throws IOException {
        userDao = new UserDao();
    }

    public Optional<UserDto> getUser(long id) {
        return userDao.findById(id).map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()));
    }

    public List<UserDto> getAllUsers() {
        return userDao.findAllUsers().stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public void updateUser(long id, String name, String email) throws IOException {
        userDao.update(id, new User(id, name, email));
    }
}
