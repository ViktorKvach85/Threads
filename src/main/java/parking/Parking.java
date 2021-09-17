package parking;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {



    protected static List<Car> parkedCars = new ArrayList<>();
    int numberOfArrivingCars = ConsoleApp.getNumberOfArrivingCars();
    int capacity = ConsoleApp.getCapacity();
    int occupiedPlaces = 0;
//    private static Parking parking = new Parking();
//    public static Parking getParking(){
//        return parking;
//    }

//        public Parking() {
//        for (int i = 0; i < numberOfArrivingCars; i++) {
//            Car car = new Car(i);
//            parkedCars.add(car);
//            car.start();
//        }
//    }
//    public void parking1() {
//        for (int i = 0; i < numberOfArrivingCars; i++) {
//            Car car = new Car(i);
//            car.start();
//            parkedCars.add(car);
//        }
//    }

    public int getMaxCapacity() {
        return capacity;
    }

    public int getOccupied() {
        return occupiedPlaces;
    }

    public void upOccupied() {
        occupiedPlaces++;
    }

    public void downOccupied() {
        occupiedPlaces--;
    }

    public void addCar(Car car) {
        parkedCars.add(car);
    }

    public void removeCar(Car car) {
        parkedCars.remove(car);
    }

}


