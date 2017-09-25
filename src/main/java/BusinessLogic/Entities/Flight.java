package BusinessLogic.Entities;

import BusinessLogic.util.FlightType;

/**
 * Created by zom on 07.09.2017.
 */
public class Flight {
    private Integer flightId;
    private Aircrew aircrew;
    private Aircrew backupAircrew;
    private Airport departureAirport;
    private Airport destinationAirport;
    private FlightType type;
    private int durationInHours; //what about minutes?


}
