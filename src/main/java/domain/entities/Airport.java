package domain.entities;

import domain.Entity;

/**
 * Created by zom on 07.09.2017.
 */
public class Airport implements Entity {
    private Integer id;
    private Integer distanceToBaseAirport;
    private String city;
    private String IATACode;
    private static boolean baseAirportDefined;
    private boolean baseAirport;

    public static class Builder {
        private Integer id;
        private Integer distanceToBaseAirport;
        private String city;
        private String IATACode;
        //        private static boolean baseAirportDefined;
        private boolean baseAirport;

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Builder distanceToBaseAirport(Integer value) {
            this.distanceToBaseAirport = value;
            return this;
        }

        public Builder city(String value) {
            this.city = value;
            return this;
        }

        public Builder IATACode(String value) {
            this.IATACode = value;
            return this;
        }


        public Builder baseAirport(boolean value) {
            baseAirport = false;
            if (!Airport.baseAirportDefined) {
                this.baseAirport = value;
                Airport.baseAirportDefined = value;
            }
            return this;
        }

        public Airport build() {
            return new Airport(this);
        }


    }

    private Airport(Builder builder) {
        this.id = builder.id;
        this.distanceToBaseAirport = builder.distanceToBaseAirport;
        this.city = builder.city;
        this.IATACode = builder.IATACode;
        this.baseAirport = builder.baseAirport;

    }

    public Integer getDistanceToBaseAirport() {
        return distanceToBaseAirport;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public String getCity() {
        return city;
    }


    public String getIATACode() {
        return IATACode;
    }


    public boolean isBaseAirport() {
        return baseAirport;
    }

    public void setBaseAirport(boolean baseAirport) {
        if (baseAirportDefined) {
            this.baseAirport = false;
            //todo exception
        }
        this.baseAirport = baseAirport;
    }

    public static boolean isBaseAirportDefined() {
        return baseAirportDefined;
    }
}
