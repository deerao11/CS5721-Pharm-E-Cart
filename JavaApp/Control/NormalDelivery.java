package Control;

// Concrete component
public class NormalDelivery implements Delivery {
    @Override
    public double calculateDeliveryCost() {
        return 1; // Normal delivery is fixed as 1 Euro
    }
}