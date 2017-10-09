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
    final Logger log = Logger.getLogger(getClass());
    private final String TABLE = SQLQueries.TABLE_NAME_USER;

    @Override
    public String execute(HttpServletRequest request) {
        return doGet(request);
    }

    private String doGet(HttpServletRequest request) {
        List<User> users;
        try {
            users = ServiceFactoryImpl.getInstance().getUserService().getAll(TABLE);
            log.info("users = " + users);
            request.setAttribute("users", users);
        } catch (PersistenceException e) {
            return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
        }
//        request.setAttribute("title", Localization.CARS_HEADER);
//        request.setAttribute("headerText", Localization.CARS_HEADER);
        return "/views/admin/editUser.jsp";
    }

}
