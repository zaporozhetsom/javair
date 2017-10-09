package domain.entities;

import domain.Entity;
import domain.util.FlightType;

import java.sql.Timestamp;

/**
 * Created by zom on 07.09.2017.
 */
public class Flight implements Entity {
    private Long id;
    private Long aircraftId;
    private Long aircrewId;
    private Long backupAircrewId;
    private Long destinationAirportId;
    private FlightType flightType;
    private Timestamp forwardDepartureDateTime;
    private Timestamp forwardArrivalDateTime;
    private Timestamp backwardDepartureDateTime;
    private Timestamp backwardArrivalDateTime;

    public static class Builder {
        private Long id;
        private Long aircraftId;
        private Long aircrewId;
        private Long backupAircrewId;
        private Long destinationAirportId;
        private FlightType flightType;
        private Timestamp forwardDepartureDateTime;
        private Timestamp forwardArrivalDateTime;
        private Timestamp backwardDepartureDateTime;
        private Timestamp backwardArrivalDateTime;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder aircraftId(Long aircraftId) {
            this.aircraftId = aircraftId;
            return this;
        }

        public Builder aircrew(Long aircrewId) {
            this.aircrewId = aircrewId;
            return this;
        }

        public Builder backupAircrew(Long aircrewId) {
            this.backupAircrewId = aircrewId;
            return this;
        }

        public Builder destinationAirport(Long destinationAirportId) {
            this.destinationAirportId = destinationAirportId;
            return this;
        }

        public Builder flightType(FlightType value) {
            this.flightType = value;
            return this;
        }

        public Builder forwardDepartureDateTime(Timestamp value) {
            this.forwardDepartureDateTime = value;
            return this;
        }

        public Builder forwardArrivalDateTime(Timestamp value) {
            this.forwardArrivalDateTime = value;
            return this;
        }

        public Builder backwardDepartureDateTime(Timestamp value) {
            this.backwardDepartureDateTime = value;
            return this;
        }

        public Builder backwardArrivalDateTime(Timestamp value) {
            this.backwardArrivalDateTime = value;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }

    private Flight(Builder builder) {
        this.id = builder.id;
        this.aircraftId = builder.aircraftId;
        this.aircrewId = builder.aircrewId;
        this.backupAircrewId = builder.backupAircrewId;
        this.destinationAirportId = builder.destinationAirportId;
        this.flightType = builder.flightType;
        this.forwardDepartureDateTime = builder.forwardDepartureDateTime;
        this.forwardArrivalDateTime = builder.forwardArrivalDateTime;
        this.backwardDepartureDateTime = builder.backwardDepartureDateTime;
        this.backwardArrivalDateTime = builder.backwardArrivalDateTime;
    }


    @Override
    public Long getId() {
        return id;
    }


    public Long getAircraftId() {
        return aircraftId;
    }

    public Long getAircrewId() {
        return aircrewId;
    }


    public Long getBackupAircrewId() {
        return backupAircrewId;
    }


    public Long getDestinationAirportId() {
        return destinationAirportId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (!id.equals(flight.id)) return false;
        return destinationAirportId.equals(flight.destinationAirportId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + destinationAirportId.hashCode();
        return result;
    }
}
