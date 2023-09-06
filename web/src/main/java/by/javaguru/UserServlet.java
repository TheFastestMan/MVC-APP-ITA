package by.javaguru;

import by.javaguru.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateServlet")
public class UserServlet extends HttpServlet {
    private UserService userService;

    public UserServlet() throws IOException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Parse request parameters
        String userId = req.getParameter("id");
        String updatedUsername = req.getParameter("name");
        String updatedEmail = req.getParameter("email");
        String updatedAge = req.getParameter("age");
        String updatedLogin = req.getParameter("login");
        String updatedPassword = req.getParameter("password");


        // Create an ItemDTO
        UserDto userDto = new UserDto();
        userDto.setId(Long.valueOf(userId));
        userDto.setName(updatedUsername);
        userDto.setEmail(updatedEmail);
        userDto.setAge(Integer.parseInt(updatedAge));
        userDto.setLogin(updatedLogin);
        userDto.setPassword(updatedPassword);


        // Use the service to update the item
        UserService userService = new UserService();
        boolean success = userService.updateUser(userDto);

        if (success) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Item updated successfully.");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Item update failed.");
        }
    }
}