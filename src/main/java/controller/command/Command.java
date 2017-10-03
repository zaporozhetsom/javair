package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zom on 19.09.2017.
 */
public interface Command {
    String execute(HttpServletRequest request);
}
