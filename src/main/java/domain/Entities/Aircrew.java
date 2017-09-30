package domain.Entities;

import domain.Entity;
import domain.util.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Aircrew cannot be modified if aircrewConfirmed is set to true
 */
public class Aircrew implements Entity {
    private Integer id;
    private Flight flight;
    private boolean aircrewConfirmed;
    private User captain;
    private User firstOfficer;
    private User secondOfficer;
    private User purser;
    private User flightMedic;
    private static final int flightAttendantsCount = 6;
    private List<User> flightAttendants = new ArrayList<>(flightAttendantsCount);

    public static class Builder {
        private Integer id;
        private Flight flight;
        private User captain;
        private User firstOfficer;
        private User secondOfficer;
        private User purser;
        private User flightMedic;
        private List<User> flightAttendants = new ArrayList<>(flightAttendantsCount);

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Builder flight(Flight value) {
            this.flight = value;
            return this;
        }

        public Builder captain(User value) {
            if (isProperUserRole(value, UserRole.CAPTAIN)) {
                this.captain = value;
            }
            return this;
        }

        public Builder firstOfficer(User value) {
            if (isProperUserRole(value, UserRole.FIRST_OFFICER)) {
                this.firstOfficer = value;
            }
            return this;
        }

        public Builder secondOfficer(User value) {
            if (isProperUserRole(value, UserRole.SECOND_OFFICER)) {
                this.secondOfficer = value;
            }
            return this;
        }

        public Builder purser(User value) {
            if (isProperUserRole(value, UserRole.PURSER)) {
                this.purser = value;
            }
            return this;
        }

        public Builder flightMedic(User value) {
            if (isProperUserRole(value, UserRole.FLIGHT_MEDIC)) {
                this.flightMedic = value;
            }
            return this;
        }

        public Builder flightAttendant(User value) {
            if ((this.flightAttendants.size() < flightAttendantsCount) && (isProperUserRole(value, UserRole.FLIGHT_ATTENDANT))) {
                this.flightAttendants.add(value);
            }
            return this;
        }

        public Aircrew build() {
            return new Aircrew(this);
        }

    }

    private Aircrew(Builder builder) {
        aircrewConfirmed = false;
        this.id = builder.id;
        this.flight = builder.flight;
        this.captain = builder.captain;
        this.firstOfficer = builder.firstOfficer;
        this.secondOfficer = builder.secondOfficer;
        this.purser = builder.purser;
        this.flightMedic = builder.flightMedic;
        this.flightAttendants = builder.flightAttendants;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public Flight getFlight() {
        return flight;
    }


    public boolean isAircrewConfirmed() {
        return aircrewConfirmed;
    }


    public User getCaptain() {
        return captain;
    }


    public User getFirstOfficer() {
        return firstOfficer;
    }

    public User getSecondOfficer() {
        return secondOfficer;
    }


    public User getPurser() {
        return purser;
    }


    public User getFlightMedic() {
        return flightMedic;
    }


    public List<User> getFlightAttendants() {
        return flightAttendants;
    }

    public void changeCrewMember(User member, UserRole role) {
        if (!isAircrewConfirmed() && role.equals(member.getRole())) {
            switch (role) {
                case CAPTAIN:
                    captain = member;
                    break;
                case FIRST_OFFICER:
                    firstOfficer = member;
                    break;
                case SECOND_OFFICER:
                    secondOfficer = member;
                    break;
                case PURSER:
                    purser = member;
                    break;
                case FLIGHT_MEDIC:
                    flightMedic = member;
                    break;
            }
        }
    }


    public void changeFlightAttendant(User oldFlightAttendant, User newFlightAttendant) {
        if (!isAircrewConfirmed()) {
            for (User flightAttendant :
                    flightAttendants) {
                if (oldFlightAttendant.equals(flightAttendant)) {
                    flightAttendants.remove(flightAttendant);
                    flightAttendants.add(newFlightAttendant);
                    return;
                }
            }
        }
        //todo throw exception NoSuchUser

    }

    public void setAircrewConfirmed(boolean aircrewConfirmed) {
        if (!this.aircrewConfirmed) {
            this.aircrewConfirmed = aircrewConfirmed;
        }
    }

    private static boolean isProperUserRole(User user, UserRole neededUserRole) {
        return user.getRole().equals(neededUserRole);
    }

}
