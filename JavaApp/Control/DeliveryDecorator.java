package Control;

// Decorator abstract class
public abstract class DeliveryDecorator implements Delivery {
    protected Delivery decoratedDelivery;

    public DeliveryDecorator(Delivery decoratedDelivery) {
        this.decoratedDelivery = decoratedDelivery;
    }

    @Override
    public double calculateDeliveryCost() {
        return decoratedDelivery.calculateDeliveryCost();
    }
}