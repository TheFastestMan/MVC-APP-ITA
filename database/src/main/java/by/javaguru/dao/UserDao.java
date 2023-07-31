package by.javaguru.dao;

import by.javaguru.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private List<User> users;
    private final ObjectMapper objectMapper;
    private static final String USER_JSON_PATH = "/Users/rail/IdeaProjects/SkillSpace_Enterprise" +
            "/1_JAKARTA:MAVEN:TOMCAT/MVC-APP-ITA/web/src/main/resources/users.json";

    public UserDao() throws IOException {
        objectMapper = new ObjectMapper();
        users = new ArrayList<>();
        users.add(new User(1L, "Alex", "Alex_Alex@mail.ru"));
        users.add(new User(2L, "Brine", "Brine_Brine@mail.ru"));
        users.add(new User(3L, "Cortney", "Cortney_Cortney@mail.ru"));
        users.add(new User(4L, "Dwight","Dwight_Dwight@mail.ru"));
        users.add(new User(5L, "Leo","Loe_Leot@mail.ru"));
        saveData();
    }

    public Optional<User> findById(long id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
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
}
