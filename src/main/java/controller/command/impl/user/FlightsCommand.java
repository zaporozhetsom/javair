package controller.command.impl.user;

import controller.command.Command;
import controller.command.CommandHelper;
import domain.entities.Aircraft;
import domain.entities.Airport;
import domain.entities.Flight;
import domain.util.FlightType;
import exception.PersistenceException;
import org.apache.log4j.Logger;
import service.factory.ServiceFactoryImpl;
import service.filter.FlightFilter;
import util.Local;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class FlightsCommand implements Command {
    private static final Logger log = Logger.getLogger(FlightsCommand.class);

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
//            List<FlightType> types = ServiceFactoryImpl.getInstance().getFlightTypeService().getAll();
//            request.setAttribute("flight-types", types);
//
//            List<Airport> airports = ServiceFactoryImpl.getInstance().getAirportService().getAll();
//            request.setAttribute("airports", airports);
//
//            List<Aircraft> aircrafts = ServiceFactoryImpl.getInstance().getAircraftService().getAll();
//            request.setAttribute("airports", airports);


            List<Flight> flights;
            int lastPage;
            int itemsCount;
            int itemsPerPage = ServiceFactoryImpl.getInstance().getFlightService().getItemsPerPage();

            if (filter == null || filter.isEmpty()) {
                flights = ServiceFactoryImpl.getInstance().getFlightService().getPart(page);
                itemsCount = ServiceFactoryImpl.getInstance().getFlightService().getCount();

            } else {
                flights = ServiceFactoryImpl.getInstance().getFlightService().getPart(page, filter);
                itemsCount = ServiceFactoryImpl.getInstance().getFlightService().getCount(filter);
            }
            lastPage = (int) Math.ceil((double) itemsCount / itemsPerPage);

            if (flights != null && !flights.isEmpty()) {
                request.setAttribute("currentPage", page);
                request.setAttribute("lastPage", lastPage);
                request.setAttribute("flights", flights);
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
        String aircraftRegId = request.getParameter("aircraftRegId");
        String aircraftModel = request.getParameter("aircraftModel");

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
        String aircraftRegIdFilter = null;
        if (aircraftRegId != null) {
            aircraftRegIdFilter = aircraftRegId;
        }
        int aircraftModelFilter = 0;
        if (aircraftModel != null) {
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