// Concrete decorator for morning delivery
public class PriorityDelivery extends DeliveryDecorator {
    public PriorityDelivery(Delivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    @Override
    public double calculateDeliveryCost() {
        return super.calculateDeliveryCost() + 10; // Morning delivery charge is 1 euro
    }
}