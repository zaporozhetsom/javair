package domain.Entities;

import domain.util.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zom on 07.09.2017.
 */
public class Aircrew {
    private int aircrewId;
    private Flight flight;
    private boolean aircrewConfirmed;
    private User captain;
    private User firstOfficer;
    private User secondOfficer;
    private User purser;
    private User flightMedic;
    private static final int flightAttendantsCount = 6;
    private List<User> flightAttendants = new ArrayList<>(flightAttendantsCount);

    public Aircrew() {
        aircrewConfirmed = false;
    }

    public int getAircrewId() {
        return aircrewId;
    }

    public void setAircrewId(int aircrewId) {
        this.aircrewId = aircrewId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isAircrewConfirmed() {
        return aircrewConfirmed;
    }

    public void setAircrewConfirmed(boolean aircrewConfirmed) {
        this.aircrewConfirmed = aircrewConfirmed;
    }

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (isProperUserRole(captain, UserRole.CAPTAIN)) {
            this.captain = captain;
        } else {
            //todo what should i do in this case?
        }
    }

    public User getFirstOfficer() {
        return firstOfficer;
    }

    public void setFirstOfficer(User firstOfficer) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (isProperUserRole(firstOfficer, UserRole.FIRST_OFFICER)) {
            this.firstOfficer = firstOfficer;
        } else {
            //todo what should i do in this case?
        }
    }

    public User getSecondOfficer() {
        return secondOfficer;
    }

    public void setSecondOfficer(User secondOfficer) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (isProperUserRole(secondOfficer, UserRole.SECOND_OFFICER)) {
            this.secondOfficer = secondOfficer;
        } else {
            //todo what should i do in this case?
        }
    }

    public User getPurser() {
        return purser;
    }

    public void setPurser(User purser) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (isProperUserRole(purser, UserRole.PURSER)) {
            this.purser = purser;
        } else {
            //todo what should i do in this case?
        }
    }

    public User getFlightMedic() {
        return flightMedic;
    }

    public void setFlightMedic(User flightMedic) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (isProperUserRole(flightMedic, UserRole.FLIGHT_MEDIC)) {
            this.flightMedic = flightMedic;
        } else {
            //todo what should i do in this case?
        }
    }

    public List<User> getFlightAttendants() {
        return flightAttendants;
    }

    public void addFlightAttendant(User flightAttendant) {
        if (isAircrewConfirmed()) {
            //todo throw exception
        }
        if (flightAttendants.size() < flightAttendantsCount) {
            if (isProperUserRole(flightAttendant, UserRole.FLIGHT_ATTENDANT)) {
                this.flightAttendants.add(flightAttendant);
            } else {
                //todo what should i do in this case? exceptions?
            }
        } else {
            //todo what should i do in this case? exceptions?
        }
    }

    public void changeFlightAttendant(User oldFlightAttendant, User newFlightAttendant) {
        if (isAircrewConfirmed()) {
            //todo throw exception Aircrew is confirmed
        }
        for (User flightAttendant :
                flightAttendants) {
            if (oldFlightAttendant.equals(flightAttendant)){
                flightAttendants.remove(flightAttendant);
                flightAttendants.add(newFlightAttendant);
                return;
            }
        }
        //todo throw exception NoSuchUser

    }

    private boolean isProperUserRole(User user, UserRole neededUserRole) {
        return user.getRole().equals(neededUserRole);
    }

}
