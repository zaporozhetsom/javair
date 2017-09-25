package Commands;

/**
 * Created by zom on 19.09.2017.
 */
public class CreateCommandsFactory implements CommandFactory {
    @Override
    public Command getAircraftCommands() {
        return new CreateAircraftCommand();
    }

    @Override
    public Command getAirportCommands() {
        return null;
    }
}
