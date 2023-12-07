package Control;

import java.time.LocalTime;
import java.util.*;


public class DeliveryCostCalc {
    String confirmMsg;
    double totalCost = 0;

    public double getDeliveryCost() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nAvailable delivery methods:\n1. Normal\n2. Priority\nPlease select your option : ");
        confirmMsg = input.nextLine();

        switch (confirmMsg) {
            case "1":
                DeliveryFactory normalFactory = new NormalDeliveryFactory();
                Delivery normalDelivery = normalFactory.createDelivery();
                totalCost += normalDelivery.calculateDeliveryCost();

                break;
            case "2":
                DeliveryFactory priorityFactory = new PriorityDeliveryFactory();
                Delivery priorityDelivery = priorityFactory.createDelivery();
                totalCost += priorityDelivery.calculateDeliveryCost();
                break;
            default:
                System.out.println("Leave my system");
        }


        if (isNight()) {
            Delivery nightDelivery = new NightDelivery(new NormalDelivery());
            System.out.println("this is night");
            totalCost += nightDelivery.calculateDeliveryCost();

        }
        System.out.printf("Delivery Cost: %.2f%n", totalCost);
        return totalCost;
    }


    public static boolean isNight() {
        LocalTime now = LocalTime.now();
        LocalTime startTime = LocalTime.parse("18:00:00");
        LocalTime endTime = LocalTime.parse("23:59:59");
        return (now.isAfter(startTime) && now.isBefore(endTime));
    }
}
