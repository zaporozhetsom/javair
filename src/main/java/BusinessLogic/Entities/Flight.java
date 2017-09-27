package BusinessLogic.Entities;

import BusinessLogic.util.FlightType;

import java.sql.Timestamp;

/**
 * Created by zom on 07.09.2017.
 */
public class Flight {
    private Integer flightId;
    private Aircrew aircrew;
    private Aircrew backupAircrew;
    private Airport destinationAirport;
    private FlightType flightType;
    private Timestamp forwardDepartureDateTime;
    private Timestamp forwardArrivalDateTime;
    private Timestamp backwardDepartureDateTime;
    private Timestamp backwardArrivalDateTime;

    public Flight() {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Aircrew getAircrew() {
        return aircrew;
    }

    public void setAircrew(Aircrew aircrew) {
        this.aircrew = aircrew;
    }

    public Aircrew getBackupAircrew() {
        return backupAircrew;
    }

    public void setBackupAircrew(Aircrew backupAircrew) {
        this.backupAircrew = backupAircrew;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public Timestamp getForwardDepartureDateTime() {
        return forwardDepartureDateTime;
    }

    public void setForwardDepartureDateTime(Timestamp forwardDepartureDateTime) {
        this.forwardDepartureDateTime = forwardDepartureDateTime;
    }

    public Timestamp getForwardArrivalDateTime() {
        return forwardArrivalDateTime;
    }

    public void setForwardArrivalDateTime(Timestamp forwardArrivalDateTime) {
        this.forwardArrivalDateTime = forwardArrivalDateTime;
    }

    public Timestamp getBackwardDepartureDateTime() {
        return backwardDepartureDateTime;
    }

    public void setBackwardDepartureDateTime(Timestamp backwardDepartureDateTime) {
        this.backwardDepartureDateTime = backwardDepartureDateTime;
    }

    public Timestamp getBackwardArrivalDateTime() {
        return backwardArrivalDateTime;
    }

    public void setBackwardArrivalDateTime(Timestamp backwardArrivalDateTime) {
        this.backwardArrivalDateTime = backwardArrivalDateTime;
    }
}
