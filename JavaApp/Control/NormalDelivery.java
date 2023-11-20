// Concrete component
public class NormalDelivery implements Delivery {
    @Override
    public double calculateDeliveryCost() {
        return 0; // Basic delivery is fixed as 1 Euro
    }
}