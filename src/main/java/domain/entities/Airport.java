package domain.entities;

import domain.Entity;

/**
 * Created by zom on 07.09.2017.
 */
public class Airport implements Entity {
    private Long id;
    private Integer distanceToBaseAirport;
    private String city;
    private String IATACode;
    private static boolean baseAirportDefined;
    private boolean baseAirport;

    public static class Builder {
        private Long id;
        private Integer distanceToBaseAirport;
        private String city;
        private String IATACode;
        private boolean baseAirport;

        public Builder id(Long value) {
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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (!id.equals(airport.id)) return false;
        return IATACode.equals(airport.IATACode);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + IATACode.hashCode();
        return result;
    }
}
