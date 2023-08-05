package by.javaguru;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class AuthenticationServlet extends HttpServlet {
    UserService userService;

    public void init() throws ServletException {
        try {
            userService = new UserService();
        } catch (IOException e) {
            throw new ServletException("Failed to initialize UserService", e.getCause());
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        System.out.println("Hello from doPost");
//
//        if (login != null && password != null && userService.getUserByLoginAndPassword(login, password).isPresent()) {
//            System.out.println("Hello from doPost IF");
//            resp.sendRedirect("/menu.html");
//
//        } else {
//            System.out.println("Hello from doPost ELSE");
//            resp.sendRedirect("/index.html");
//        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<UserDto> userOptional = userService.getUserByLoginAndPassword(login, password);

        if (login != null && password != null && userOptional.isPresent()) {
            UserDto user = userOptional.get();
            req.getSession().setAttribute("user", user); // Сохранение информации о пользователе в сессии
            resp.sendRedirect("/menu.html");
        } else {
            resp.sendRedirect("/index.html");
        }
    }


}


