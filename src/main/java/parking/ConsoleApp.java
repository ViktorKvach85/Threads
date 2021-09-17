package parking;

import java.util.Scanner;

public class ConsoleApp {
    public static int capacity;
    public static int numberOfArrivingCars;

    public static int getCapacity() {
        return capacity;
    }

    public static int getNumberOfArrivingCars() {
        return numberOfArrivingCars;
    }

    void startConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set parking capacity:");
        capacity = scanner.nextInt();
        System.out.println("Set the number of arriving cars");
        numberOfArrivingCars = scanner.nextInt();
//               for (int i = 0; i < numberOfArrivingCars; i++) {
//            Car car = new Car(i);
//            car.start();
//    }
    }
}

