package persistence.dao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zom on 03.10.2017.
 */
public final class SQLQueries {
    private static volatile SQLQueries instance;

    /**
     * maps for containing certain queries
     */
    private static final Map<String, String> SELECT_ALL = new HashMap<>();
    private static final Map<String, String> SELECT_COUNT = new HashMap<>();
    private static final Map<String, String> CREATE = new HashMap<>();
    private static final Map<String, String> UPDATE = new HashMap<>();
    private static final Map<String, String> DELETE_BY_ID = new HashMap<>();


    /**
     * Names of tables in DB
     */
    public static final String TABLE_NAME_USER = "user";
    public static final String TABLE_NAME_FLIGHT = "flight";
    public static final String TABLE_NAME_AIRCRAFT = "aircraft";
    public static final String TABLE_NAME_AIRCREW = "aircrew";
    public static final String TABLE_NAME_AIRPORT = "airport";


    /**
     * 'SELECT ALL' queries
     */
    private static final String SELECT_ALL_USER = "SELECT u.id, first_name, last_name, login, password, approved, " +
            "role.role_name FROM user u INNER JOIN user_roles role ON (role.id = u.role_id) ";
    private static final String SELECT_ALL_FLIGHT = "SELECT f.id, ac.reg_id, amf.name, am.model, am.capacity, ft.name, \n" +
            "f.fw_depart_date, f.fw_arriv_date, f.bw_depart_date, f.bw_arriv_date,\n" +
            "acr.id, u.first_name, u.last_name, \n" +
            "acr2.id, u2.first_name, u2.last_name,\n" +
            "ap.city, ap.IATACode,\n" +
            "apb.city, apb.IATACode \n" +
            "\n" +
            "FROM flight f \n" +
            "JOIN aircraft ac ON (f.aircraft_id = ac.id) \n" +
            "JOIN aircraft_model am ON (ac.model = am.id) \n" +
            "JOIN aircraft_manufacturer amf ON (am.manufacturer_id = amf.id)\n" +
            "JOIN flight_type ft ON (f.flight_type_id = ft.id)\n" +
            "JOIN aircrew acr ON (f.aircrew_id = acr.id)\n" +
            "JOIN aircrew acr2 ON (f.backup_aircrew_id = acr2.id)\n" +
            "JOIN user u ON (acr.captain_id = u.id)\n" +
            "JOIN user u2 ON (acr2.captain_id = u2.id)\n" +
            "JOIN airport ap ON (f.dest_airport_id = ap.id)\n" +
            "JOIN airport apb ON (apb.base_airport = 1) ";
    private static final String SELECT_ALL_AIRCRAFT = "";
    private static final String SELECT_ALL_AIRCREW = "";
    private static final String SELECT_ALL_AIRPORT = "";

    /**
     * 'SELECT COUNT' queries
     */
    private static final String SELECT_COUNT_USER = "SELECT count(*) FROM user";
    private static final String SELECT_COUNT_FLIGHT = "SELECT count(*) FROM flight";
    private static final String SELECT_COUNT_AIRCRAFT = "SELECT count(*) FROM aircraft";
    private static final String SELECT_COUNT_AIRCREW = "SELECT count(*) FROM aircrew";
    private static final String SELECT_COUNT_AIRPORT = "SELECT count(*) FROM airport";

    /**
     * 'CREATE' queries
     */
    private static final String CREATE_USER = "INSERT INTO user (first_name, last_name,login,password,role_id)\n" +
            "VALUES (?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "(SELECT id \n" +
            "FROM user_roles\n" +
            "WHERE role_name = ?))";
    private static final String CREATE_FLIGHT = "";
    private static final String CREATE_AIRCRAFT = "";
    private static final String CREATE_AIRCREW = "";
    private static final String CREATE_AIRPORT = "";

    /**
     * 'UPDATE' queries
     */
    private static final String UPDATE_USER = "";
    private static final String UPDATE_FLIGHT = "";
    private static final String UPDATE_AIRCRAFT = "";
    private static final String UPDATE_AIRCREW = "";
    private static final String UPDATE_AIRPORT = "";

    /**
     * 'DELETE' queries
     */
    private static final String DELETE_BY_ID_USER = "";
    private static final String DELETE_BY_ID_FLIGHT = "";
    private static final String DELETE_BY_ID_AIRCRAFT = "";
    private static final String DELETE_BY_ID_AIRCREW = "";
    private static final String DELETE_BY_ID_AIRPORT = "";


    private SQLQueries() {
        initMapSelectAll();
        initMapCreate();
        initMapDelete();
        initMapSelectCount();
        initMapUpdate();
    }

    public String getSelectAllQuery(String tableName) {
        return SELECT_ALL.get(tableName);
    }

    public String getSelectCountQuery(String tableName) {
        return SELECT_COUNT.get(tableName);
    }

    public String getCreateQuery(String tableName) {
        return CREATE.get(tableName);
    }

    public String getUpdateQuery(String tableName) {
        return UPDATE.get(tableName);
    }

    public String getDeleteByIdQuery(String tableName) {
        return DELETE_BY_ID.get(tableName);
    }


    private void initMapSelectAll() {
        SELECT_ALL.put(TABLE_NAME_USER, SELECT_ALL_USER);
        SELECT_ALL.put(TABLE_NAME_FLIGHT, SELECT_ALL_FLIGHT);
        SELECT_ALL.put(TABLE_NAME_AIRCRAFT, SELECT_ALL_AIRCRAFT);
        SELECT_ALL.put(TABLE_NAME_AIRCREW, SELECT_ALL_AIRCREW);
        SELECT_ALL.put(TABLE_NAME_AIRPORT, SELECT_ALL_AIRPORT);
    }

    private void initMapSelectCount() {
        SELECT_COUNT.put(TABLE_NAME_USER, SELECT_COUNT_USER);
        SELECT_COUNT.put(TABLE_NAME_FLIGHT, SELECT_COUNT_FLIGHT);
        SELECT_COUNT.put(TABLE_NAME_AIRCRAFT, SELECT_COUNT_AIRCRAFT);
        SELECT_COUNT.put(TABLE_NAME_AIRCREW, SELECT_COUNT_AIRCREW);
        SELECT_COUNT.put(TABLE_NAME_AIRPORT, SELECT_COUNT_AIRPORT);
    }

    private void initMapCreate() {
        CREATE.put(TABLE_NAME_USER, CREATE_USER);
        CREATE.put(TABLE_NAME_FLIGHT, CREATE_FLIGHT);
        CREATE.put(TABLE_NAME_AIRCRAFT, CREATE_AIRCRAFT);
        CREATE.put(TABLE_NAME_AIRCREW, CREATE_AIRCREW);
        CREATE.put(TABLE_NAME_AIRPORT, CREATE_AIRPORT);
    }

    private void initMapUpdate() {
        UPDATE.put(TABLE_NAME_USER, UPDATE_USER);
        UPDATE.put(TABLE_NAME_FLIGHT, UPDATE_FLIGHT);
        UPDATE.put(TABLE_NAME_AIRCRAFT, UPDATE_AIRCRAFT);
        UPDATE.put(TABLE_NAME_AIRCREW, UPDATE_AIRCREW);
        UPDATE.put(TABLE_NAME_AIRPORT, UPDATE_AIRPORT);
    }

    private void initMapDelete() {
        DELETE_BY_ID.put(TABLE_NAME_USER, DELETE_BY_ID_USER);
        DELETE_BY_ID.put(TABLE_NAME_FLIGHT, DELETE_BY_ID_FLIGHT);
        DELETE_BY_ID.put(TABLE_NAME_AIRCRAFT, DELETE_BY_ID_AIRCRAFT);
        DELETE_BY_ID.put(TABLE_NAME_AIRCREW, DELETE_BY_ID_AIRCREW);
        DELETE_BY_ID.put(TABLE_NAME_AIRPORT, DELETE_BY_ID_AIRPORT);
    }


    public static SQLQueries getInstance() {
        SQLQueries localInstance = instance;
        if (localInstance == null) {
            synchronized (SQLQueries.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SQLQueries();
                }
            }
        }
        return localInstance;
    }

}
