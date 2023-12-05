package Control;

public class PriorityDeliveryFactory implements DeliveryFactory {
    @Override
    public Delivery createDelivery() {
        return new PriorityDelivery(new NormalDelivery());
    }
}