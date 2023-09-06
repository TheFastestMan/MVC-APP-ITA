package by.javaguru;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String login;

    private String password;

}