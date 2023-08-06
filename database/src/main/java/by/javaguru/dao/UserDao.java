package by.javaguru.dao;

import by.javaguru.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private User user;
    private List<User> users;
    private final ObjectMapper objectMapper;
    private static final String USER_JSON_PATH = "/Users/rail/IdeaProjects/SkillSpace_Enterprise/1JAKARTA_MAVEN_TOMCAT" +
            "/MVC-APP-ITA/web/src/main/resources/users.json";

    public UserDao() throws IOException {
        objectMapper = new ObjectMapper();
        users = loadData();
    }

    public Optional<User> findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst();
    }

    public void update(long id, User updatedUser) throws IOException {
        User user = findById(id).orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            saveData();
        }
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }

    private void saveData() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(USER_JSON_PATH), users);
    }

    public void createNewUser(User user) throws IOException {
        users.add(user);
        saveData();
    }

    private List<User> loadData() throws IOException {
        File file = new File(USER_JSON_PATH);
        if (file.exists()) {
            return new ArrayList<>(Arrays.asList(objectMapper.readValue(file, User[].class)));
        } else {
            return new ArrayList<>();
        }
    }

}
