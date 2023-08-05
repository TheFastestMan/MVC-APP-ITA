package by.javaguru;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private ObjectMapper objectMapper;

    public UserServlet() throws IOException {
        userService = new UserService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserDto user = (UserDto) req.getSession().getAttribute("user");
        user.setName(name);
        user.setEmail(email);
        user.setAge(Integer.parseInt(age));
        user.setLogin(login);
        user.setPassword(password);
        userService.updateUser(user.getId(), name, email, Integer.parseInt(age), login, password);

    }
}