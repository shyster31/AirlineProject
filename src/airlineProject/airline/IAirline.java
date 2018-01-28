package airlineProject.airline;

import airlineProject.airline.aircraft.Aircraft;
import java.util.List;

public interface IAirline {

    /**
     *Returns the number of elements in this airline.  If this airline contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     * @return the number of elements in this airline
     */
    public int getAmountOfAirplanes();

    /**
     *Appends the specified element to the end of this airline's list (optional
     * operation).
     * 
     * @param newAirpnale element to be appended to this airline
     */
    void addAirplane(Aircraft newAirpnale);

    /**
     *Removes the first occurrence of the specified element from this airlne,
     * if it is present (optional operation).  If this airline does not contain
     * the element, it is unchanged.
     * 
     * @param name the name of removed aircraft
     */
    void removeAirplane(String name);

    /**
     * Removes the element at the specified position in this airline's list 
     * (optional operation).
     * 
     * @param index the index of the element to be removed
     */
    void removeAirplane(int index);

    /**
     * Calculate total capacity of all aircrafts in this airline.Return 0,if 
     * this airline hasn't any aircraft.
     * 
     * @return total capacity of all aircrafts
     */
    int calculateTotalCapacity();

    /**
     * Calculate total carrying capacity of all aircrafts in this airline. 
     * Return 0,if this airline hasn't any aircraft.
     * 
     * @return total carrying capacity of all aircrafts
     */
    int calculateTotalCarryingCapacity();

    /**
     *Sorts this airline's list by flight range(from smaller to larger)
     */
    void sortByFlightRange();

    /**
     *
     * @param minFuelConsumption
     * @param maxFuelConsumption
     * @return
     */
    List<Aircraft> findAirplane(int minFuelConsumption, int maxFuelConsumption);
}
