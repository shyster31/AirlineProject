package airlineProject;

import airlineProject.airline.Airline;
import airlineProject.airline.aircraft.*;
import java.util.Scanner;

public class AirlineProject {

    public static void main(String[] args) {
        Airline airline = new Airline(getStringAfterQuestion("Enter a name of your airline."));

        while (true) {
            System.out.println("What do you want to do?");
            switch (getDecisionFromConsole().toLowerCase()) {
                case "add":
                    airline.addAirplane(new Plane.Builder(getStringAfterQuestion("Enter a name of your plane."))
                            .capacity(getIntegerAfterQuestion("Enter capasity"))
                            .flightRange(getIntegerAfterQuestion("Enter flight range"))
                            .carryingCapacity(getIntegerAfterQuestion("Enter carying capacity"))
                            .fuelConsumption(getFloatAfterQuestion("Enter fuel consumption")).build());
                    break;
                case "remove":
                    airline.removeAirplane(getStringAfterQuestion("Enter a name of aircraft"));
                    break;
                case "amount":
                    System.out.println(new StringBuilder().append("You have a ")
                            .append(airline.getAmountOfAirplanes())
                            .append(" airplanes"));
                    break;
                case "capacity":
                    System.out.println("Total capacity is " + airline.calculateTotalCapacity());
                    break;
                case "carrying capacity":
                    System.out.println("Total carrying capacity is " + airline.calculateTotalCarryingCapacity());
                    break;
                case "sort":
                    airline.sortByFlightRange();
                    System.out.println("Sorted successfully");
                case "print":
                    System.out.println(airline);
                    break;
                case "find":
                    System.out.println(airline.findAirplane(getIntegerAfterQuestion("Enter minimum of range")
                            , getIntegerAfterQuestion("Enter maximum of range")));
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid data");
                    break;
            }
        }
    }

    private static String getDecisionFromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private static String getStringAfterQuestion(String question) {
        System.out.println(question);
        return getDecisionFromConsole();
    }

    private static int getIntegerAfterQuestion(String question) {
        String data = getStringAfterQuestion(question);
        while (true) {
            try {
                return Integer.parseInt(data);
            } catch (NumberFormatException e) {
                data = getStringAfterQuestion("Invalid data, enter again");
            }
        }
    }

    private static float getFloatAfterQuestion(String question) {
        while (true) {
            try {
                return Float.parseFloat(getStringAfterQuestion(question));
            } catch (NumberFormatException e) {
                question = getStringAfterQuestion("Invalid data, enter again");
            }
        }
    }
}
