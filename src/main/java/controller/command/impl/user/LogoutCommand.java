package controller.command.impl.user;

import controller.command.Command;
import util.Local;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zom on 13.10.2017.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("role") != null && session.getAttribute("login") != null) {
            String language = session.getAttribute("language").toString();
            session.invalidate();
            HttpSession sessionNext = request.getSession();
            sessionNext.setAttribute("language", language);
            request.setAttribute("title", Local.LOGIN_HEADER);
            request.setAttribute("headerText", Local.LOGIN_HEADER);
        }
        return "/views/login.jsp";
    }
}