package Commands;

/**
 * Created by zom on 19.09.2017.
 */
public interface CommandFactory {

    Command getAircraftCommands();

    Command getAirportCommands();
}
