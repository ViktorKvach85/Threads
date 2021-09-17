package parking;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        ConsoleApp consoleApp = new ConsoleApp();
        consoleApp.startConsole();
        Car car =new Car();
        car.startArrivingCars();
      //  Thread.sleep(5000);
    }
}
