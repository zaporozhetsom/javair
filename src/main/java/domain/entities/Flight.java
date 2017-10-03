package domain.entities;

import domain.Entity;
import domain.util.FlightType;

import java.sql.Timestamp;

/**
 * Created by zom on 07.09.2017.
 */
public class Flight implements Entity{
    private Integer id;
    private Aircrew aircrew;
    private Aircrew backupAircrew;
    private Airport destinationAirport;
    private FlightType flightType;
    private Timestamp forwardDepartureDateTime;
    private Timestamp forwardArrivalDateTime;
    private Timestamp backwardDepartureDateTime;
    private Timestamp backwardArrivalDateTime;

    public static class Builder{
        private Integer id;
        private Aircrew aircrew;
        private Aircrew backupAircrew;
        private Airport destinationAirport;
        private FlightType flightType;
        private Timestamp forwardDepartureDateTime;
        private Timestamp forwardArrivalDateTime;
        private Timestamp backwardDepartureDateTime;
        private Timestamp backwardArrivalDateTime;

        public Builder() {
        }

        public Builder id (Integer id){
            this.id = id;
            return this;
        }

        public Builder aircrew (Aircrew aircrew){
            this.aircrew = aircrew;
            return this;
        }

        public Builder backupAircrew (Aircrew aircrew){
            this.backupAircrew = aircrew;
            return this;
        }

        public Builder destinationAirport (Airport destinationAirport){
            this.destinationAirport = destinationAirport;
            return this;
        }

        public Builder flightType (FlightType value){
            this.flightType = value;
            return this;
        }

        public Builder forwardDepartureDateTime (Timestamp value){
            this.forwardDepartureDateTime = value;
            return this;
        }

        public Builder forwardArrivalDateTime (Timestamp value){
            this.forwardArrivalDateTime = value;
            return this;
        }

        public Builder backwardDepartureDateTime (Timestamp value){
            this.backwardDepartureDateTime = value;
            return this;
        }

        public Builder backwardArrivalDateTime (Timestamp value){
            this.backwardArrivalDateTime = value;
            return this;
        }

        public Flight build () {
            return new Flight(this);
        }
    }

    private Flight(Builder builder) {
        this.id = builder.id;
        this.aircrew = builder.aircrew;
        this.backupAircrew = builder.backupAircrew;
        this.destinationAirport = builder.destinationAirport;
        this.flightType = builder.flightType;
        this.forwardDepartureDateTime = builder.forwardDepartureDateTime;
        this.forwardArrivalDateTime = builder.forwardArrivalDateTime;
        this.backwardDepartureDateTime = builder.backwardDepartureDateTime;
        this.backwardArrivalDateTime = builder.backwardArrivalDateTime;
    }


    @Override
    public Integer getId() {
        return id;
    }


    public Aircrew getAircrew() {
        return aircrew;
    }


    public Aircrew getBackupAircrew() {
        return backupAircrew;
    }


    public Airport getDestinationAirport() {
        return destinationAirport;
    }


    public FlightType getFlightType() {
        return flightType;
    }


    public Timestamp getForwardDepartureDateTime() {
        return forwardDepartureDateTime;
    }


    public Timestamp getForwardArrivalDateTime() {
        return forwardArrivalDateTime;
    }


    public Timestamp getBackwardDepartureDateTime() {
        return backwardDepartureDateTime;
    }


    public Timestamp getBackwardArrivalDateTime() {
        return backwardArrivalDateTime;
    }

}
