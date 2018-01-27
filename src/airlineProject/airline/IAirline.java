package airlineProject.airline;

import airlineProject.airline.aircraft.Aircraft;
import java.util.ArrayList;

public interface IAirline {

    public int getAmountOfAirplanes();

    void addAirplane(Aircraft newAirpnale);

    void removeAirplane(Aircraft removedAirpnale);

    void removeAirplane(int index);

    int calculateTotalCapacity();

    int calculateTotalCarryingCapacity();

    void sortByFlightRange();

    ArrayList<Aircraft> findAirplane(int minFuelConsumption, int maxFuelConsumption);
}
