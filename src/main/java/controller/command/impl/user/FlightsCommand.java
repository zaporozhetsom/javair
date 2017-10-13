package controller.command.impl.user;

import controller.command.Command;
import controller.command.CommandHelper;
import domain.entities.Aircraft;
import domain.entities.Airport;
import domain.entities.Dummy;
import domain.entities.Flight;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import persistence.dao.util.SQLQueries;
import service.factory.ServiceFactoryImpl;
import service.filter.FlightFilter;
import util.Local;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class FlightsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FlightsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equals(request.getMethod())) {
            return doGet(request);
        } else {
            request.setAttribute("error", 500);
            request.setAttribute("message", "Server error");
            return "/views/utility/error.jsp";
        }
    }

    private String doGet(HttpServletRequest request) {
        String pageString = request.getParameter("page");
        int page = CommandHelper.getInstance().convertParameterToInt(pageString);
        if (page == 0) {
            page = 1;
        }

        FlightFilter filter = createFilters(request);

        try {

            List<Airport> airports = ServiceFactoryImpl.getInstance().getAirportService().getAll(SQLQueries.TABLE_NAME_AIRPORT);
            request.setAttribute("airports", airports);

            List<Aircraft> aircrafts = ServiceFactoryImpl.getInstance().getAircraftService().getAll(SQLQueries.TABLE_NAME_AIRCRAFT);
            request.setAttribute("aircrafts", aircrafts);

            List<Dummy> models = ServiceFactoryImpl.getInstance().getAircraftService().getAllModels(SQLQueries.TABLE_NAME_AIRCRAFT);
            request.setAttribute("models", models);

            List<Dummy> manufacturers = ServiceFactoryImpl.getInstance().getAircraftService().getAllManufacturers(SQLQueries.TABLE_NAME_AIRCRAFT);
            request.setAttribute("manufacturers", manufacturers);

            HttpSession session = request.getSession();
            session.setAttribute("airports", airports);

            List<Flight> flights;
            int lastPage;
            int itemsCount;
            String tableName = SQLQueries.TABLE_NAME_FLIGHT;
            int itemsPerPage = ServiceFactoryImpl.getInstance().getFlightService().getItemsPerPage(tableName);

            if (filter == null || filter.isEmpty()) {
                flights = ServiceFactoryImpl.getInstance().getFlightService().getPart(page,tableName);
                itemsCount = ServiceFactoryImpl.getInstance().getFlightService().getCount(tableName);

            } else {
                flights = ServiceFactoryImpl.getInstance().getFlightService().getPart(page, filter);
                itemsCount = ServiceFactoryImpl.getInstance().getFlightService().getCount(filter);
            }
            lastPage = (int) Math.ceil((double) itemsCount / itemsPerPage);

            if (flights != null && !flights.isEmpty()) {
                request.setAttribute("currentPage", page);
                request.setAttribute("lastPage", lastPage);
                request.setAttribute("flights", flights);
//                if(filter.getAirport()!=0){
//                    request.setAttribute("airport", filter.getAirport());
//                }
//                if(filter.getAircraftRegId()!=0){
//                    request.setAttribute("aircraft", filter.getAircraftRegId());
//                }
//                if(filter.getAircraftModel()!=0){
//                    request.setAttribute("model", filter.getAircraftModel());
//                }
            } else {
                request.setAttribute("error", "No flights found");
            }
        } catch (PersistenceException e) {
            return CommandHelper.getInstance().setErrorPage(e.getMessage(), request);
        }
        request.setAttribute("title", Local.FLIGHTS_HEADER);
        request.setAttribute("headerText", Local.FLIGHTS_HEADER);
        return "/views/user/flights.jsp";
    }

    private FlightFilter createFilters(HttpServletRequest request) {

        String flightType = request.getParameter("flight-type");
        String airport = request.getParameter("airport");
        String minCapacity = request.getParameter("minCapacity");
        String fwDepartDateTime = request.getParameter("fwDepartDateTime");
        String aircraftRegId = request.getParameter("aircraft");
        String aircraftModel = request.getParameter("model");

        int flightTypeFilter = 0;
        if (!"all".equals(flightType) && flightType != null) {
            flightTypeFilter = CommandHelper.getInstance().convertParameterToInt(flightType);
        }
        int airportFilter = 0;
        if (!"all".equals(airport) && airport != null) {
            airportFilter = CommandHelper.getInstance().convertParameterToInt(airport);
        }
        int minCapacityFilter = 0;
        if (minCapacity != null) {
            minCapacityFilter = CommandHelper.getInstance().convertParameterToInt(minCapacity);
        }
        Timestamp fwDepartDateTimeFilter = null;
        if (fwDepartDateTime != null) {
            fwDepartDateTimeFilter = Timestamp.valueOf(fwDepartDateTime);
        }
        int aircraftRegIdFilter = 0;
        if (!"all".equals(aircraftRegId) && aircraftRegId != null) {
            aircraftRegIdFilter = CommandHelper.getInstance().convertParameterToInt(aircraftRegId);
        }
        int aircraftModelFilter = 0;
        if (!"all".equals(aircraftModel) && aircraftModel != null) {
            aircraftModelFilter = CommandHelper.getInstance().convertParameterToInt(aircraftModel);
        }

        FlightFilter filter = new FlightFilter.Builder()
                .flightType(flightTypeFilter)
                .airport(airportFilter)
                .minCapacity(minCapacityFilter)
                .fwDepartDateTime(fwDepartDateTimeFilter)
                .aircraftRegId(aircraftRegIdFilter)
                .aircraftModel(aircraftModelFilter)
                .build();

        HttpSession session = request.getSession();
        session.setAttribute("flightFilter", filter);
        return filter;
    }


}