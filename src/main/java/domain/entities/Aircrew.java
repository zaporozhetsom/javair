package domain.entities;

import domain.Entity;
import domain.util.UserRole;
import exception.IllegalEntityStateException;
import exception.NoSuchUserException;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import service.factory.ServiceFactoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Aircrew cannot be modified if aircrewConfirmed is set to true
 */
public class Aircrew implements Entity {
    private Long id;
    private boolean aircrewConfirmed;
    private Long captainUserId;
    private Long firstOfficerUserId;
    private Long secondOfficerUserId;
    private Long purserUserId;
    private Long flightMedicUserId;
    private static final int FLIGHT_ATTENDANTS_COUNT = 6;
    private List<Long> flightAttendantsUserIds = new ArrayList<>(FLIGHT_ATTENDANTS_COUNT);

    final Logger log = Logger.getLogger(Aircrew.class.getClass());

    public static class Builder {
        private Long id;
        private Long captainUserId;
        private Long firstOfficerUserId;
        private Long secondOfficerUserId;
        private Long purserUserId;
        private Long flightMedicUserId;
        private List<Long> flightAttendantsUserIds = new ArrayList<>(FLIGHT_ATTENDANTS_COUNT);

        public Builder id(Long value) {
            this.id = value;
            return this;
        }


        public Builder captain(Long value) throws PersistenceException {
            if (isProperUserRole(value, UserRole.CAPTAIN)) {
                this.captainUserId = value;
            }
            return this;
        }

        public Builder firstOfficer(Long value) throws PersistenceException {
            if (isProperUserRole(value, UserRole.FIRST_OFFICER)) {
                this.firstOfficerUserId = value;
            }
            return this;
        }

        public Builder secondOfficer(Long value) throws PersistenceException {
            if (isProperUserRole(value, UserRole.SECOND_OFFICER)) {
                this.secondOfficerUserId = value;
            }
            return this;
        }

        public Builder purser(Long value) throws PersistenceException {
            if (isProperUserRole(value, UserRole.PURSER)) {
                this.purserUserId = value;
            }
            return this;
        }

        public Builder flightMedic(Long value) throws PersistenceException {
            if (isProperUserRole(value, UserRole.FLIGHT_MEDIC)) {
                this.flightMedicUserId = value;
            }
            return this;
        }

        public Builder flightAttendant(Long value) throws PersistenceException {
            if ((this.flightAttendantsUserIds.size() < FLIGHT_ATTENDANTS_COUNT) && (isProperUserRole(value, UserRole.FLIGHT_ATTENDANT))) {
                this.flightAttendantsUserIds.add(value);
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
        this.captainUserId = builder.captainUserId;
        this.firstOfficerUserId = builder.firstOfficerUserId;
        this.secondOfficerUserId = builder.secondOfficerUserId;
        this.purserUserId = builder.purserUserId;
        this.flightMedicUserId = builder.flightMedicUserId;
        this.flightAttendantsUserIds = builder.flightAttendantsUserIds;
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


    public boolean isAircrewConfirmed() {
        return aircrewConfirmed;
    }


    public Long getCaptainUserId() {
        return captainUserId;
    }


    public Long getFirstOfficerUserId() {
        return firstOfficerUserId;
    }

    public Long getSecondOfficerUserId() {
        return secondOfficerUserId;
    }


    public Long getPurserUserId() {
        return purserUserId;
    }


    public Long getFlightMedicUserId() {
        return flightMedicUserId;
    }


    public List<Long> getFlightAttendantsUserIds() {
        return flightAttendantsUserIds;
    }

    public void changeCrewMember(Long userId, UserRole role) throws PersistenceException {
        User user = getUserById(userId);
        if (!isAircrewConfirmed() && role.equals(user.getRole())) {
            switch (role) {
                case CAPTAIN:
                    captainUserId = userId;
                    break;
                case FIRST_OFFICER:
                    firstOfficerUserId = userId;
                    break;
                case SECOND_OFFICER:
                    secondOfficerUserId = userId;
                    break;
                case PURSER:
                    purserUserId = userId;
                    break;
                case FLIGHT_MEDIC:
                    flightMedicUserId = userId;
                    break;
            }
        }
    }

    private static User getUserById(Long userId) throws PersistenceException {
        return ServiceFactoryImpl.getInstance().getUserService().getById(userId);
    }


    public void changeFlightAttendant(Long oldFlightAttendantId, Long newFlightAttendantId) throws NoSuchUserException, IllegalEntityStateException {
        if (!isAircrewConfirmed()) {
            for (Long flightAttendant :
                    flightAttendantsUserIds) {
                if (oldFlightAttendantId.equals(flightAttendant)) {
                    flightAttendantsUserIds.remove(flightAttendant);
                    flightAttendantsUserIds.add(newFlightAttendantId);
                    return;
                }
            }
            NoSuchUserException ex = new NoSuchUserException("There is no such user(userId = " + oldFlightAttendantId + ") in flight attendants list");
            log.error(ex);
            throw ex;
        } else {
            IllegalEntityStateException ex = new IllegalEntityStateException("Aircrew #" + id + " is confirmed. You cannot change composition of the team");
            log.error(ex);
            throw ex;
        }

    }

    public void setAircrewConfirmed(boolean aircrewConfirmed) {
        if (!this.aircrewConfirmed) {
            this.aircrewConfirmed = aircrewConfirmed;
        }
    }

    private static boolean isProperUserRole(Long userId, UserRole neededUserRole) throws PersistenceException {
        User user = getUserById(userId);
        return user.getRole().equals(neededUserRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aircrew aircrew = (Aircrew) o;

        return id.equals(aircrew.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
