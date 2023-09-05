package by.javaguru;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserService userService;

    public void init() throws ServletException {
        try {
            userService = new UserService();
        } catch (IOException e) {
            throw new ServletException("Failed to initialize UserService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer age = Integer.valueOf(req.getParameter("age"));
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && userService.getUserById(id).isEmpty()) {
            userService.addNewUser(name, age, email, login, password);
            resp.sendRedirect("/menu.html");
        } else {
            resp.sendRedirect("/util.html");
        }
    }
}
