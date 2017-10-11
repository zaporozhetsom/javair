package controller.command.impl.user;


import controller.command.Command;
import controller.command.CommandHelper;
import domain.entities.User;
import exception.PersistenceException;
import service.factory.ServiceFactoryImpl;
import util.Local;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if ("POST".equals(request.getMethod())) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String hashedPassword = null;
            try {
                hashedPassword = User.hashPassword(password);
            } catch (NoSuchAlgorithmException e) {
                return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
            }
            try {
                User user = ServiceFactoryImpl.getInstance().getUserService().getUserByLoginPassword(login, hashedPassword);
                if (user == null) {
                    request.setAttribute("error", Local.AUTH_ERROR);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("login", user.getLogin());
                    session.setAttribute("userId", user.getId());
                    request.setAttribute("redirect", "true");
                }
            } catch (PersistenceException e) {
                return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
            }
        }
        request.setAttribute("title", Local.LOGIN_HEADER);
        request.setAttribute("headerText", Local.LOGIN_HEADER);
        return "/views/login.jsp";
    }
}
