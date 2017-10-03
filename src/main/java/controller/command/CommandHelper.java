package controller.command;

import local.Local;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zom on 03.10.2017.
 */
public class CommandHelper {

    private static volatile CommandHelper instance;

    public static CommandHelper getInstance() {
        CommandHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandHelper();
                }
            }
        }
        return localInstance;
    }


    private CommandHelper() {
    }


    public String setErrorPage(String errorMessage, HttpServletRequest req) {
        req.setAttribute("error", 500);
        req.setAttribute("message", errorMessage);
        req.setAttribute("title", Local.ERROR_HEADER);
        req.setAttribute("headerText", Local.ERROR_HEADER);
        return "/WEB-INF/views/utility/error.jsp";
    }

    public String setErrorPage(String errorCode, String errorMessage, HttpServletRequest req) {
        req.setAttribute("error", errorCode);
        req.setAttribute("message", errorMessage);
        req.setAttribute("title", Local.ERROR_HEADER);
        req.setAttribute("headerText", Local.ERROR_HEADER);
        return "/WEB-INF/views/utility/error.jsp";
    }

}
