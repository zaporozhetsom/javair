package controller.command.impl.admin;

import controller.command.*;
import domain.entities.User;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import persistence.dao.util.SQLQueries;
import service.factory.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zom on 03.10.2017.
 */
public class UserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UserCommand.class.getName());
    private final String TABLE = SQLQueries.TABLE_NAME_USER;

    @Override
    public String execute(HttpServletRequest request) {
        return doGet(request);
    }

    private String doGet(HttpServletRequest request) {
        List<User> users;
        try {
            users = ServiceFactoryImpl.getInstance().getUserService().getAll(TABLE);
            LOGGER.info("users = " + users);
            request.setAttribute("users", users);
        } catch (PersistenceException e) {
            return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
        }
//        request.setAttribute("title", Localization.HEADER);
//        request.setAttribute("headerText", Localization.HEADER);
        return "/views/admin/editUser.jsp";
    }

}
