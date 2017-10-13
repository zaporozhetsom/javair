package controller.command.factory;

import controller.command.*;
import controller.command.impl.user.*;
import controller.command.impl.admin.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class CommandFactory {
    private static volatile CommandFactory instance;
    private final Map<String, Command> commands;
    private static final Logger LOGGER = Logger.getLogger(CommandFactory.class.getName());

    public static CommandFactory getInstance() {
        CommandFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (CommandFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CommandFactory();
                }
            }
        }
        return localInstance;
    }


    private CommandFactory() {
        commands = new HashMap<>();
        commands.put("/index.html", new LoginCommand());
        commands.put("/admin/editUser", new UserCommand());
        commands.put("/register", new RegistrationCommand());
        commands.put("/", new LoginCommand());
        commands.put("/login", new LoginCommand());
        commands.put("/flights", new FlightsCommand());
        commands.put("/logout", new LogoutCommand());

    }


    public Command getCommand(String uri) {
        if (uri.startsWith("/flights")) {
            return commands.get("/flights");
        }

        Command command = commands.get(uri);
        LOGGER.debug("uri = " + uri);
        LOGGER.debug("command = " + command.getClass());
        return command;
    }
}
