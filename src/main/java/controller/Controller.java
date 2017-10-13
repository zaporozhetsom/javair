package controller;

import controller.command.*;
import controller.command.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zom on 02.10.2017.
 */


@WebServlet(urlPatterns = {"/index.html", "/javair/*"})
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet. request = " + req);
        executeCommand(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doPost. request = " + req);
        executeCommand(req, resp);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(15 * 60); //interval in seconds

        String path = request.getPathInfo();
        Command command = CommandFactory.getInstance().getCommand(path);

        LOGGER.debug("request.getPathInfo = " + path);
        LOGGER.debug("command = " + command);

        if (command != null) {
            String jspView = command.execute(request);
            request.getRequestDispatcher("/WEB-INF" + jspView).forward(request, response);
        } else {
            String page = CommandHelper.getInstance().setErrorPage("404", "Not found", request);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }


}
