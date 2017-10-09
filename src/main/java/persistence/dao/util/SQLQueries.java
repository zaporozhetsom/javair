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
    private static final String SELECT_ALL_USER = "SELECT u.id, first_name, last_name, login, role.role_name, password " +
            "FROM user u INNER JOIN user_roles role ON (role.id = u.role_id)";
    private static final String SELECT_ALL_FLIGHT = "";
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
    private static final String CREATE_USER = "";
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
