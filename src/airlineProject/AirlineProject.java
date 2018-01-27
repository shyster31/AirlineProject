package airlineProject;

import airlineProject.airline.Airline;
import airlineProject.airline.aircraft.*;

public class AirlineProject {

    public static void main(String[] args) {
        Airline boing = new Airline("Boing");
        for (int i = 10; i > 0; i--) {
            boing.addAirplane(new Plane.Builder("name" + i).fuelConsumption(i).build());
        }
        
//        System.out.println(boing.calculateTotalCarryingCapacity());
//        System.out.println(boing.calculateTotalCapacity());
//        boing.sortByFlightRange();
        System.out.println(boing);
//        System.out.println(boing.findAirplane(3, 6));
    }
}
