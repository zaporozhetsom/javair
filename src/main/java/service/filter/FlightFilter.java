package service.filter;

import org.apache.log4j.Logger;

import java.sql.Timestamp;

/**
 * Created by zom on 11.10.2017.
 */
public class FlightFilter {
    private final int flightType;
    private final int airport;
    private final int minCapacity;
    private final Timestamp fwDepartDateTime;
    private final String aircraftRegId;
    private final int aircraftModel;

    private static final Logger log = Logger.getLogger(FlightFilter.class);


    public static class Builder {
        private int flightType;
        private int airport;
        private int minCapacity;
        private Timestamp fwDepartDateTime;
        private String aircraftRegId;
        private int aircraftModel;
        final Logger log = Logger.getLogger(Builder.class.getClass());

        public Builder() {
        }

        public Builder flightType(int value) {
            this.flightType = value;
            log.debug("flightType(value) = " + value);
            return this;
        }

        public Builder airport(int value) {
            this.airport = value;
            log.debug("airport(value) = " + value);
            return this;
        }

        public Builder minCapacity(int value) {
            this.minCapacity = value;
            log.debug("minCapacity(value) = " + value);
            return this;
        }

        public Builder fwDepartDateTime(Timestamp value) {
            this.fwDepartDateTime = value;
            log.debug("fwDepartDateTime(value) = " + value);
            return this;
        }

        public Builder aircraftRegId(String value) {
            this.aircraftRegId = value;
            log.debug("aircraftRegId(value) = " + value);
            return this;
        }

        public Builder aircraftModel(int value) {
            this.aircraftModel = value;
            log.debug("aircraftModel(value) = " + value);
            return this;
        }

        public FlightFilter build() {
            log.debug("build User");
            return new FlightFilter(this);
        }
    }

    private FlightFilter(Builder builder) {
        this.flightType = builder.flightType;
        this.airport = builder.airport;
        this.minCapacity = builder.minCapacity;
        this.fwDepartDateTime = builder.fwDepartDateTime;
        this.aircraftRegId = builder.aircraftRegId;
        this.aircraftModel = builder.aircraftModel;
    }


    public int getFlightType() {
        return flightType;
    }

    public int getAirport() {
        return airport;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public Timestamp getFwDepartDateTime() {
        return fwDepartDateTime;
    }

    public String getAircraftRegId() {
        return aircraftRegId;
    }

    public int getAircraftModel() {
        return aircraftModel;
    }

    public boolean isEmpty() {
        return ((flightType == 0)
                && (airport == 0)
                && (minCapacity == 0)
                && (fwDepartDateTime == null)
                && (aircraftRegId == null)
                && (aircraftModel == 0));
    }

    public String getQuery() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder query = new StringBuilder(" WHERE ");
        boolean isEmpty = true;
        if (flightType != 0) {
            query.append("f.flight_type_id = ").append(flightType).append(" AND ");
            isEmpty = false;
        }
        if (airport != 0) {
            query.append("f.dest_airport_id = ").append(airport).append(" AND ");
            isEmpty = false;
        }
        if (minCapacity != 0) {
            query.append("am.capacity >= ").append(minCapacity).append(" AND ");
            isEmpty = false;
        }
        if (fwDepartDateTime != null) {
            query.append("f.fw_depart_date >= ").append(fwDepartDateTime).append(" AND ");
            isEmpty = false;
        }
        if (aircraftRegId != null) {
            query.append("ac.reg_id = ").append(aircraftRegId).append(" AND ");
            isEmpty = false;
        }
        if (aircraftModel != 0) {
            query.append("am.id = ").append(aircraftModel).append(" AND ");
            isEmpty = false;
        }
        log.debug("SQL query before cutting: " + query);
        String subQuery;
        if (isEmpty) {
            subQuery = query.substring(0, query.length() - 6);
        } else {
            subQuery = query.substring(0, query.length() - 5);
        }
        log.debug("SQL query after cutting: " + subQuery);
        return subQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightFilter that = (FlightFilter) o;

        if (flightType != that.flightType) return false;
        if (airport != that.airport) return false;
        if (minCapacity != that.minCapacity) return false;
        if (fwDepartDateTime != null ? !fwDepartDateTime.equals(that.fwDepartDateTime) : that.fwDepartDateTime != null)
            return false;
        if (aircraftRegId != null ? !aircraftRegId.equals(that.aircraftRegId) : that.aircraftRegId != null)
            return false;
        return aircraftModel != that.aircraftModel;
    }

    @Override
    public int hashCode() {
        int result = flightType;
        result = 31 * result + airport;
        result = 31 * result + minCapacity;
        result = 31 * result + (fwDepartDateTime != null ? fwDepartDateTime.hashCode() : 0);
        result = 31 * result + (aircraftRegId != null ? aircraftRegId.hashCode() : 0);
        result = 31 * result + aircraftModel;
        return result;
    }

    @Override
    public String toString() {
        return "FlightFilter{" +
                "flightType=" + flightType +
                ", airport=" + airport +
                ", minCapacity=" + minCapacity +
                ", fwDepartDateTime=" + fwDepartDateTime +
                ", aircraftRegId='" + aircraftRegId + '\'' +
                ", aircraftModel='" + aircraftModel + '\'' +
                '}';
    }

}
