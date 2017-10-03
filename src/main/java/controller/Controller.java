package controller;

import controller.command.*;
import controller.command.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zom on 02.10.2017.
 */


@WebServlet(urlPatterns = {"/index.html", "/app/*"})
@MultipartConfig
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeCommand(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeCommand(req, resp);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        Command command = CommandFactory.getInstance().getCommand(path);
        if (command != null) {
            String jspView = CommandFactory.getInstance().getCommand(path).execute(request);
            request.getRequestDispatcher("/WEB-INF" + jspView).forward(request, response);
        } else {
            String page = CommandUtil.getInstance().setErrorPage("404", "Not found", request);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
