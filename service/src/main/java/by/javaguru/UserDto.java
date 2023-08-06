package by.javaguru;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

//todo the validation UserDTO
public class UserDto {
    private long id;
     @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should bee between 2 and 30 characters")
    private String name;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
    @NotEmpty(message = "Login should not be empty")
    @Size(min = 5, max = 30, message = "Login should be between 5 and 30 characters")
    private String login;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, max = 50, message = "Password should be between 8 and 50 characters")
    private String password;

    public UserDto() {
    }

    public UserDto(long id, String name, String email, int age, String login, String password) {
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
        if (!(o instanceof UserDto userDto)) return false;
        return getId() == userDto.getId() && getAge() == userDto.getAge() && Objects.equals(getName(),
                userDto.getName()) && Objects.equals(getEmail(), userDto.getEmail()) && Objects.equals(getLogin(),
                userDto.getLogin()) && Objects.equals(getPassword(), userDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getAge(), getLogin(), getPassword());
    }
}
