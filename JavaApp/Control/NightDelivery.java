// Concrete decorator for evening delivery
public class NightDelivery extends DeliveryDecorator {
    public NightDelivery(Delivery decoratedDelivery) {
        super(decoratedDelivery);
    }

    @Override
    public double calculateDeliveryCost() {
        return super.calculateDeliveryCost() + 5; // Evening delivery charge is 5 euro
    }
}