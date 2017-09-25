package BusinessLogic.Entities;

/**
 * Created by zom on 14.09.2017.
 */
public enum UserRole {
    ADMIN("PC user"),
    DISPATCHER("PC user"),
    CAPTAIN("Flight deck"),
    FIRST_OFFICER("Flight deck"),
    SECOND_OFFICER("Flight deck"),
    PURSER("Cabin"),
    FLIGHT_ATTENDANT("Cabin"),
    FLIGHT_MEDIC("Cabin");

    private String position;

    UserRole(String position){
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
