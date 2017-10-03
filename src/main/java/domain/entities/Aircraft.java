package domain.entities;

import domain.Entity;
import domain.util.AircraftManufacturer;

/**
 * Created by zom on 14.09.2017.
 */
public class Aircraft implements Entity {
    private Integer id;
    private String registrationIdentifier;
    private AircraftManufacturer manufacturer;
    private String model;
    private Integer capacity;
    private Airport aircraftLocation;

    public static class Builder{
        private Integer id;
        private String registrationIdentifier;
        private AircraftManufacturer manufacturer;
        private String model;
        private Integer capacity;
        private Airport aircraftLocation;

        public Builder registrationIdentifier(String value) {
            this.registrationIdentifier = value;
            return this;
        }

        public Builder manufacturer(AircraftManufacturer value) {
            this.manufacturer = value;
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

        public Builder aircraftLocation(Airport value) {
            this.aircraftLocation = value;
            return this;
        }

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Aircraft build(){
            return new Aircraft(this);
        }
    }

    private Aircraft(Builder builder) {
        this.id = builder.id;
        this.registrationIdentifier = builder.registrationIdentifier;
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.capacity = builder.capacity;
        this.aircraftLocation = builder.aircraftLocation;
    }

    public Airport getAircraftLocation() {
        return aircraftLocation;
    }


    public void setAircraftLocation(Airport aircraftLocation) {
        this.aircraftLocation = aircraftLocation;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public String getRegistrationIdentifier() {
        return registrationIdentifier;
    }


    public AircraftManufacturer getManufacturer() {
        return manufacturer;
    }


    public String getModel() {
        return model;
    }


    public Integer getCapacity() {
        return capacity;
    }

}
