package Control;

public class NormalDeliveryFactory implements DeliveryFactory {
    @Override
    public Delivery createDelivery() {
        return new NormalDelivery();
    }
}