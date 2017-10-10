package controller.command.impl.user;

import controller.command.Command;
import controller.command.CommandHelper;
import domain.entities.User;
import domain.util.UserRole;
import exception.PersistenceException;
import persistence.dao.util.SQLQueries;
import service.factory.ServiceFactory;
import service.factory.ServiceFactoryImpl;
import util.Local;
import org.apache.log4j.Logger;
import util.Validation;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zom on 09.10.2017.
 */
public class RegistrationCommand implements Command {
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    private String name;
    private String lastname;
    private String role;
    private String login;
    private String password1;
    private String password2;

    @Override
    public String execute(HttpServletRequest request) {
        if ("POST".equals(request.getMethod())) {
            name = request.getParameter("name");
            lastname = request.getParameter("lastname");
            login = request.getParameter("login");
            role = request.getParameter("role");
            password1 = request.getParameter("password1");
            password2 = request.getParameter("password2");
            log.debug("name " + name +
                    ", lastname " + lastname +
                    ", login " + login +
                    ", password1 " + password1 +
                    ", password2 " + password2 +
                    ", role " + role
            );
            if (!Validation.isPasswordValid(password1)) {
                return redirectWithError(Local.PASSWORD_WEAK, request);
            }
            if (!password1.equals(password2)) {
                return redirectWithError(Local.PASSWORD_MISMATCH, request);
            }
//            if (!Validation.isLoginValid(login)) {
//                login = "";
//                return redirectWithError(Local.INVALID_LOGIN, request);
//            }
            else {
                String password;
                try {
                    password = User.hashPassword(password1);
                } catch (NoSuchAlgorithmException e) {
                    log.error("Hashing password exception", e);
                    return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
                }
                User user = new User.Builder()
                        .firstName(name)
                        .lastName(lastname)
                        .login(login)
                        .password(password)
                        .role(UserRole.valueOf(role))
                        .build();
                boolean isUserExists;
                try {
                    isUserExists = ServiceFactoryImpl.getInstance().getUserService().isUserExists(login);
                } catch (PersistenceException e) {
                    return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
                }
                if (isUserExists) {
                    login = "";
                    return redirectWithError(Local.INVALID_LOGIN, request);
                }
                try {
                    ServiceFactoryImpl.getInstance().getUserService().create(user, SQLQueries.TABLE_NAME_USER);

                } catch (PersistenceException e) {
                    return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
                }
                request.setAttribute("redirect", "true");
            }
        }
        request.setAttribute("title", Local.REGISTRATION_HEADER);
        request.setAttribute("headerText", Local.REGISTRATION_HEADER);
        request.setAttribute("headerText", "header");
        return "/views/user/register.jsp";
    }

    private String redirectWithError(String error, HttpServletRequest request) {
        request.setAttribute("error", error);
        request.setAttribute("title", Local.REGISTRATION_HEADER);
        request.setAttribute("headerText", Local.REGISTRATION_HEADER);
        request.setAttribute("name", name);
        request.setAttribute("lastname", lastname);
        request.setAttribute("login", login);
        request.setAttribute("role", role);
        return "/views/user/register.jsp";
    }
}
