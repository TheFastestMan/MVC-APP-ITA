package by.javaguru;

import by.javaguru.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private ObjectMapper objectMapper;

    public UserServlet() throws IOException {
        userService = new UserService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "getAll":
                    resp.setContentType("application/json");
                    objectMapper.writeValue(resp.getWriter(), userService.getAllUsers());
                    break;
                case "get":
                    long id = Long.parseLong(req.getParameter("id"));
                    Optional<UserDto> user = userService.getUser(id);
                    if (user.isPresent()) {
                        resp.setContentType("application/json");
                        objectMapper.writeValue(resp.getWriter(), user.get());
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                    }
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String newName = req.getParameter("name");
            String newEmail = req.getParameter("email");
            User updatedUser = new User(id, newName, newEmail);
            userService.updateUser(id, newName, newEmail);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
        }
    }
}
