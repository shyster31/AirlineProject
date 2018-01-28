package airlineProject.airline;

import airlineProject.airline.aircraft.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Airline implements IAirline {

    private final List<Aircraft> airplaneList;
    private final String NAME;
    private static final String DB_NAME = "AIRCRAFTS";
    private static final String DB_URL = "jdbc:h2:mem:aircraftDB";
    private static final String LOGIN = "airplanes";
    private static final String PASSWORD = "";

    public Airline(String NAME) {
        this(new ArrayList<Aircraft>(), NAME);

    }

    public Airline(List<Aircraft> airplaneList, String NAME) {
        this.airplaneList = airplaneList;
        this.NAME = NAME;

        try {
            Class.forName("org.h2.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        createTable();
    }

    private static ResultSet getResultSetFromDB(Connection connectionWithDB, String action) throws SQLException {
        Statement statement = connectionWithDB.createStatement();
        ResultSet resultSet = statement.executeQuery(action);
        return resultSet;
    }

    private static void setDataToDB(Connection connectionWithDB, String action) throws SQLException {
        Statement statement = connectionWithDB.createStatement();
        statement.execute(action);
    }

    private static void createTable() {
        try {
            setDataToDB(createConnectionWithDB(), "CREATE TABLE AIRCRAFTS(\n"
                    + "NAME VARCHAR(20), \n"
                    + "CAPACITY INT,\n"
                    + "FLIGHT_RANGE INT, \n"
                    + "CARRYING_CAPACITY INT,\n"
                    + "FUEL_CONSUMPTION FLOAT\n"
                    + ");");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static List<Aircraft> getAirplanesFromDB() {
        List<Aircraft> airplanes = new ArrayList<>();

        try (ResultSet resultSet = getResultSetFromDB(createConnectionWithDB(), "SELECT * FROM " + DB_NAME + ";");) {
            while (resultSet.next()) {
                airplanes.add(new Plane.Builder(resultSet.getString("NAME"))
                        .capacity(resultSet.getInt("CAPACITY"))
                        .flightRange(resultSet.getInt("FLIGHT_RANGE"))
                        .carryingCapacity(resultSet.getInt("CARRYING_CAPACITY"))
                        .fuelConsumption(resultSet.getFloat("FUEL_CONSUMPTION")).build());
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return airplanes;
    }

    private static Connection createConnectionWithDB() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }

    @Override
    public int getAmountOfAirplanes() {
        return airplaneList.size();
    }

    @Override
    public void addAirplane(Aircraft newAirpnale) {
        try {
            setDataToDB(createConnectionWithDB(), new StringBuilder()
                    .append("INSERT INTO AIRCRAFTS (NAME ,CAPACITY ,FLIGHT_RANGE ,CARRYING_CAPACITY ,FUEL_CONSUMPTION) VALUES ('")
                    .append(newAirpnale.getName()).append("',")
                    .append(newAirpnale.getCapacity()).append(',')
                    .append(newAirpnale.getFlightRange()).append(',')
                    .append(newAirpnale.getCarryingCapacity()).append(',')
                    .append(newAirpnale.getFuelConsumption()).append(");").toString());
            airplaneList.add(newAirpnale);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Aircraft findAircraftByName(String name) {
        for (Aircraft aircraft : airplaneList) {
            if (aircraft.getName().equals(name)) {
                return aircraft;
            }
        }
        return null;
    }

    @Override
    public void removeAirplane(String name) {

        try {
            setDataToDB(createConnectionWithDB(), new StringBuilder()
                    .append("DELETE FROM ").append(DB_NAME)
                    .append(" WHERE NAME = '")
                    .append(name).append("';").toString());
            airplaneList.remove(findAircraftByName(name));
        } catch (SQLException ex) {
        }
    }

    @Override
    public void removeAirplane(int index) {
        removeAirplane(airplaneList.get(index).getName());
    }

    public String getNAME() {
        return NAME;
    }

    private int calculateTotal(AirplaneCapacity neededCapacity) {
        AtomicInteger totalCapacity = new AtomicInteger(0);
        AtomicInteger airplaneIndex = new AtomicInteger(airplaneList.size());
        ExecutorService pool = Executors.newFixedThreadPool(4);

        while (airplaneIndex.get() > 0) {
            pool.submit(new calculateTotalCapacityTask(airplaneIndex, totalCapacity, neededCapacity));
        }

        pool.shutdown();
        return totalCapacity.get();
    }

    @Override
    public int calculateTotalCapacity() {
        return calculateTotal(AirplaneCapacity.CAPACITY);
    }

    @Override
    public int calculateTotalCarryingCapacity() {
        return calculateTotal(AirplaneCapacity.CARRYING_CAPACITY);
    }

    private class calculateTotalCapacityTask implements Runnable {

        AtomicInteger airplaneIndex;
        AtomicInteger totalCapacity;
        AirplaneCapacity neededCapacity;

        public calculateTotalCapacityTask(AtomicInteger airplaneIndex, AtomicInteger totalCapacity, AirplaneCapacity neededCapacity) {
            this.airplaneIndex = airplaneIndex;
            this.totalCapacity = totalCapacity;
            this.neededCapacity = neededCapacity;
        }

        @Override
        public void run() {
            switch (neededCapacity) {
                case CAPACITY:
                    totalCapacity.addAndGet(airplaneList.get(airplaneIndex.getAndDecrement()).getCapacity());
                    break;
                case CARRYING_CAPACITY:
                    totalCapacity.addAndGet(airplaneList.get(airplaneIndex.getAndDecrement()).getCarryingCapacity());
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Airline ")
                .append(NAME).append(" has following airplanes:\n")
                .append(airplaneList).toString();
    }

    @Override
    public void sortByFlightRange() {
        Collections.sort(airplaneList, (Aircraft o1, Aircraft o2) -> {
            return o1.getFlightRange() - o2.getFlightRange();
        });
    }

    @Override
    public List<Aircraft> findAirplane(int minFuelConsumption, int maxFuelConsumption) {

        List<Aircraft> findedAirplane = new ArrayList<>();
        AtomicInteger airplaneIndex = new AtomicInteger(airplaneList.size());
        ExecutorService pool = Executors.newFixedThreadPool(4);

        while (airplaneIndex.get() > 0) {
            pool.submit(() -> {
                int index = airplaneIndex.getAndDecrement();

                if (airplaneList.get(index).getFuelConsumption() >= minFuelConsumption
                        && airplaneList.get(index).getFuelConsumption() <= maxFuelConsumption) {
                    findedAirplane.add(airplaneList.get(index));
                }
            });
        }
        pool.shutdown();
        return findedAirplane;
    }
}
