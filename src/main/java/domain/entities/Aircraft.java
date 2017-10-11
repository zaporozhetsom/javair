package domain.entities;

import domain.Entity;

/**
 * Created by zom on 14.09.2017.
 */
public class Aircraft implements Entity {
    private Long id;
    private String registrationIdentifier;
    private Long aircraftManufacturerId;
    private String model;
    private Integer capacity;
    private Long aircraftLocationAirportId;

    public static class Builder {
        private Long id;
        private String registrationIdentifier;
        private Long aircraftManufacturerId;
        private String model;
        private Integer capacity;
        private Long aircraftLocationAirportId;

        public Builder registrationIdentifier(String value) {
            this.registrationIdentifier = value;
            return this;
        }

        public Builder manufacturer(Long value) {
            this.aircraftManufacturerId = value;
            return this;
        }

        public Builder model(String value) {
            this.model = value;
            return this;
        }

        public Builder capacity(Integer value) {
            this.capacity = value;
            return this;
        }

        public Builder aircraftLocation(Long value) {
            this.aircraftLocationAirportId = value;
            return this;
        }

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Aircraft build() {
            return new Aircraft(this);
        }
    }

    private Aircraft(Builder builder) {
        this.id = builder.id;
        this.registrationIdentifier = builder.registrationIdentifier;
        this.aircraftManufacturerId = builder.aircraftManufacturerId;
        this.model = builder.model;
        this.capacity = builder.capacity;
        this.aircraftLocationAirportId = builder.aircraftLocationAirportId;
    }

    public Long getAircraftLocationAirportId() {
        return aircraftLocationAirportId;
    }


    public void setAircraftLocationAirportId(Long aircraftLocationAirportId) {
        this.aircraftLocationAirportId = aircraftLocationAirportId;
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


    public String getRegistrationIdentifier() {
        return registrationIdentifier;
    }


    public Long getAircraftManufacturerId() {
        return aircraftManufacturerId;
    }


    public String getModel() {
        return model;
    }


    public Integer getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircraft aircraft = (Aircraft) o;

        if (!id.equals(aircraft.id)) return false;
        if (!registrationIdentifier.equals(aircraft.registrationIdentifier)) return false;
        if (!aircraftManufacturerId.equals(aircraft.aircraftManufacturerId)) return false;
        return model.equals(aircraft.model);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + registrationIdentifier.hashCode();
        result = 31 * result + aircraftManufacturerId.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
