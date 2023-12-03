package Control;
import java.time.LocalTime;
import java.util.*;


public class DeliveryCostCalc {
    String confirmMsg;

    // create order id, delivery method
//                1. DeliveryCostCalc class
//                   2. display()
//                    3. clculate()
//
//                String orderId= "ABC-123";
//                String deliveryMethod = "";
    //calculate delivery price
    double totalCost=0;

    public void display() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nAvailable delivery methods:\n1. Normal\n2. Priority\nPlease select your option : ");
        confirmMsg = input.nextLine();

        switch (confirmMsg) {
            case "1":
                Delivery normalDelivery = new NormalDelivery();
                totalCost += normalDelivery.calculateDeliveryCost();
                break;
            case "2":
                Delivery priorityDelivery = new PriorityDelivery(new NormalDelivery());
                totalCost += priorityDelivery.calculateDeliveryCost();
                break;
            default:
                System.out.println("Leave my system");
        }


        if(isNight()){
            Delivery nightDelivery=new NightDelivery(new NormalDelivery());
            System.out.println("this is night");
            totalCost+=nightDelivery.calculateDeliveryCost();
        }
        System.out.printf("Total Cost: %.2f%n", totalCost);
    }


    public static boolean isNight(){
        LocalTime now = LocalTime.now();
        LocalTime startTime = LocalTime.parse("19:00:00");
        LocalTime endTime = LocalTime.parse("23:59:59");
        return (now.isAfter(startTime) && now.isBefore(endTime));
    }
