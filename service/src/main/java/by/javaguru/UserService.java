package by.javaguru;

import by.javaguru.dao.UserDao;
import by.javaguru.entity.User;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDao userDao;
    private ModelMapper modelMapper = new ModelMapper();

    public UserService() throws IOException {
        userDao = new UserDao();
    }

    public boolean updateUser(UserDto userDto) throws IOException {
        User user = convertUserDtoToUser(userDto);
        return userDao.update(user);
    }
    public User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
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

    public Optional<UserDto> getUserByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password)
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(),
                        user.getAge(), user.getLogin(), user.getPassword()));
    }

}