package parking;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread {
    private int id;
    private final int WaitingNanos = ThreadLocalRandom.current().nextInt(10, 20);
    private int ParkedNanos = ThreadLocalRandom.current().nextInt(10000, 11000);
    private final static Lock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();
    //  private static final Parking parking = Parking.getParking();
    private static int numberOfArrivingCars = ConsoleApp.getNumberOfArrivingCars();
    private boolean isParkedCar = false;

    public Car() {
    }

    public void setIsParking(boolean value) {
        isParkedCar = value;
    }

    private static Parking parking = new Parking();

    public Car(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void startArrivingCars() {
        for (int i = 0; i < numberOfArrivingCars; i++) {
            Car car = new Car(i);
            car.start();
        }
    }

    private void tryPark() throws InterruptedException {
        try {
            lock.lock();
            if (parking.getOccupied() == parking.getMaxCapacity()) {
                long nanos = condition.awaitNanos(WaitingNanos);
                while (parking.getOccupied() == parking.getMaxCapacity()) {
                    if (nanos <= 0L) {
                        //  leave();
                        System.out.println("Водитель авто " + getId() + " устал ждать и уехал");
                        return;
                    }
                }
                System.out.println("Водитель авто " + getId() + " дождался и припарковался");
                parking();
            }
            if (parking.getOccupied() < parking.getMaxCapacity()) {
                parking.upOccupied();
                parking.addCar(this);
                setIsParking(true);
                System.out.println("Авто " + getId() + " припарковалось. Свободных мест " + ( parking.getMaxCapacity() - parking.getOccupied() ));

                long nanos = condition.awaitNanos(ParkedNanos);
                    if (nanos <= 0L) {
                          leave();
                        // return;
                    }



            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            lock.unlock();
        }
    }

    void parking() {
        parking.upOccupied();
        parking.addCar(this);
        setIsParking(true);
        System.out.println("Авто " + getId() + " припарковалось. Свободных мест " + ( parking.getMaxCapacity() - parking.getOccupied() ));
    }

    void leave() {
        try {
            lock.lock();
            if (isParkedCar) {
                parking.downOccupied();
                parking.removeCar(this);
            }
            System.out.println("Авто " + this.getId() + " сделал дела и уехал. Свободных мест " + ( parking.getMaxCapacity() - parking.getOccupied() ));
            condition.signalAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        try {
            tryPark();
            //   Thread.sleep(0, maxParkedNanos);
            //  leave();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}