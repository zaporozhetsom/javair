package controller.command.factory;

import controller.command.*;
import controller.command.impl.user.AircraftCommand;
import controller.command.impl.admin.UserCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zom on 19.09.2017.
 */
public class CommandFactory {
    private static volatile CommandFactory instance;
    private final Map<String, Command> commands;

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
        commands.put("/index.html", new AircraftCommand());
        commands.put("/admin/editUser", new UserCommand());
    }


    public Command getCommand(String uri) {
            return commands.get(uri);
    }
}
