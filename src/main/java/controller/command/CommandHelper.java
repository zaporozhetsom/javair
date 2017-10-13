package controller.command;

import org.apache.log4j.Logger;
import util.Local;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zom on 03.10.2017.
 */
public class CommandHelper {

    private static final Logger LOGGER = Logger.getLogger(CommandHelper.class);
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

    public int convertParameterToInt(String num) {
        int result = 0;
        if (num != null) {
            try {
                result = Integer.parseInt(num);
            } catch (NumberFormatException e) {
                LOGGER.error("convertParameterToInt(String num): Illegal argument num=" + num, e);
            }
        }
        return result;
    }


}
