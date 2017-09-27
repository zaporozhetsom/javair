package BusinessLogic.Entities;

import BusinessLogic.util.AircraftManufacturer;

/**
 * Created by zom on 14.09.2017.
 */
public class Aircraft {
    private Integer aircraftId;
    private String  registrationIdentifier;
    private AircraftManufacturer manufacturer;
    private String model;
    private Integer flightDeckMembersCount;
    private Integer cabinMembersCount;
    private Integer capacity;
    private Airport aircraftLocation;

    public Airport getAircraftLocation() {
        return aircraftLocation;
    }

    public void setAircraftLocation(Airport aircraftLocation) {
        this.aircraftLocation = aircraftLocation;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getRegistrationIdentifier() {
        return registrationIdentifier;
    }

    public void setRegistrationIdentifier(String registrationIdentifier) {
        this.registrationIdentifier = registrationIdentifier;
    }

    public AircraftManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(AircraftManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getFlightDeckMembersCount() {
        return flightDeckMembersCount;
    }

    public void setFlightDeckMembersCount(Integer flightDeckMembersCount) {
        this.flightDeckMembersCount = flightDeckMembersCount;
    }

    public Integer getCabinMembersCount() {
        return cabinMembersCount;
    }

    public void setCabinMembersCount(Integer cabinMembersCount) {
        this.cabinMembersCount = cabinMembersCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
