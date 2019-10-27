package pl.sda.tweeter.login;

import pl.sda.tweeter.exception.IncorrectLoginOrPassword;
import pl.sda.tweeter.persistance.entities.TbUser;
import pl.sda.tweeter.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        TbUser tbUser = null;
        try {
            tbUser = userService.getTbUser(login, password);
            userService.getTbUser(login, password);
        } catch (IncorrectLoginOrPassword e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", tbUser);
        response.sendRedirect("index.jsp");
    }
}
