//package controller.command;
//
///**
// * Created by zom on 02.10.2017.
// */
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//public class EditUserCommand implements Command {
//    @Override
//    public String execute(HttpServletRequest request) {
//        if ("GET".equals(request.getMethod())) {
//            return executeGet(request);
//        }
//        if ("POST".equals(request.getMethod())) {
//            return executePost(request);
//        }
//        return CommandUtil.getInstance().setErrorPage("500", "Unknown method", request);
//    }
//
//    private String executeGet(HttpServletRequest request) {
//        List<User> users;
//        try {
//            users = ServiceFactoryJDBCImpl.getInstance().getUserService().getAll();
//            request.setAttribute("users", users);
//        } catch (PersistenceException e) {
//            return CommandUtil.getInstance().setErrorPage(e.getMessage(), request);
//        }
//        request.setAttribute("title", Localization.CARS_HEADER);
//        request.setAttribute("headerText", Localization.CARS_HEADER);
//        return "/view/admin/editUser.jsp";
//    }
//
//    private String executePost(HttpServletRequest request) {
//        String id = request.getParameter("userId");
//        int userId = CommandUtil.getInstance().parseInteger(id);
//        if (userId != 0) {
//            try {
//                ServiceFactoryJDBCImpl.getInstance().getUserService().setAdmin(userId);
//            } catch (PersistenceException e) {
//                return CommandUtil.getInstance().setErrorPage(e.getMessage(), request);
//            }
//        }
//        return executeGet(request);
//    }
//}
