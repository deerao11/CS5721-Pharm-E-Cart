package Control;

// Concrete decorator for Priority delivery
public class PriorityDelivery extends DeliveryDecorator {
    public PriorityDelivery(Delivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    @Override
    public double calculateDeliveryCost() {
        return super.calculateDeliveryCost() + 10; // Priority delivery charge is 10 euro
    }
}