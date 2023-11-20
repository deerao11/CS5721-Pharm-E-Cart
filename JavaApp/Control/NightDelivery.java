// Concrete decorator for Night delivery
public class NightDelivery extends DeliveryDecorator {
    public NightDelivery(Delivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    @Override
    public double calculateDeliveryCost() {
        return super.calculateDeliveryCost() + 5; // Night delivery charge is 5 euro
    }
}