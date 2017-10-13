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
    private final int aircraftRegId;
    private final int aircraftModel;

    private static final Logger LOGGER = Logger.getLogger(FlightFilter.class);


    public static class Builder {
        private int flightType;
        private int airport;
        private int minCapacity;
        private Timestamp fwDepartDateTime;
        private int aircraftRegId;
        private int aircraftModel;

        public Builder() {
        }

        public Builder flightType(int value) {
            this.flightType = value;
            LOGGER.debug("flightType(value) = " + value);
            return this;
        }

        public Builder airport(int value) {
            this.airport = value;
            LOGGER.debug("airport(value) = " + value);
            return this;
        }

        public Builder minCapacity(int value) {
            this.minCapacity = value;
            LOGGER.debug("minCapacity(value) = " + value);
            return this;
        }

        public Builder fwDepartDateTime(Timestamp value) {
            this.fwDepartDateTime = value;
            LOGGER.debug("fwDepartDateTime(value) = " + value);
            return this;
        }

        public Builder aircraftRegId(int value) {
            this.aircraftRegId = value;
            LOGGER.debug("aircraftRegId(value) = " + value);
            return this;
        }

        public Builder aircraftModel(int value) {
            this.aircraftModel = value;
            LOGGER.debug("aircraftModel(value) = " + value);
            return this;
        }

        public FlightFilter build() {
            LOGGER.debug("build filter");
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

    public int getAircraftRegId() {
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
                && (aircraftRegId == 0)
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
        if (aircraftRegId != 0) {
            query.append("ac.id = ").append(aircraftRegId).append(" AND ");
            isEmpty = false;
        }
        if (aircraftModel != 0) {
            query.append("am.id = ").append(aircraftModel).append(" AND ");
            isEmpty = false;
        }
        LOGGER.debug("SQL query before cutting: " + query);
        String subQuery;
        if (isEmpty) {
            subQuery = query.substring(0, query.length() - 6);
        } else {
            subQuery = query.substring(0, query.length() - 5);
        }
        LOGGER.debug("SQL query after cutting: " + subQuery);
        return subQuery + " ORDER BY f.id";
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
        if (aircraftRegId != that.aircraftRegId) return false;
        return aircraftModel != that.aircraftModel;
    }

    @Override
    public int hashCode() {
        int result = flightType;
        result = 31 * result + airport;
        result = 31 * result + minCapacity;
        result = 31 * result + (fwDepartDateTime != null ? fwDepartDateTime.hashCode() : 0);
        result = 31 * result + aircraftRegId;
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
