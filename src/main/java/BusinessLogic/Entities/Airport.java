package BusinessLogic.Entities;

/**
 * Created by zom on 07.09.2017.
 */
public class Airport {
    private Integer distanceToBaseAirport;
    private String city;
    private String IATACode;
    private int airportId;
    private static boolean baseAirportDefined;
    private boolean baseAirport;

    public Airport() {
    }

    public Integer getDistanceToBaseAirport() {
        return distanceToBaseAirport;
    }

    public void setDistanceToBaseAirport(Integer distanceToBaseAirport) {
        this.distanceToBaseAirport = distanceToBaseAirport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }


    public boolean isBaseAirport() {
        return baseAirport;
    }

    public void setBaseAirport(boolean baseAirport) {
        if(baseAirportDefined){
            this.baseAirport = false;
            //todo exception
        }
        this.baseAirport = baseAirport;
    }

    public static boolean isBaseAirportDefined() {
        return baseAirportDefined;
    }
}
