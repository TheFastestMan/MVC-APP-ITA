package by.javaguru.entity;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String email;
    private int age;
    private String login;
    private String password;

    public User() {
    }

    public User(long id, String name, String email, int age, String login, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && getAge() == user.getAge() && Objects.equals(getName(),
                user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getLogin(),
                user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getAge(), getLogin(), getPassword());
    }
}
