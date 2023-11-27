// State interface
public interface OrderState {
    void processOrder(Order order);

    void cancelOrder(Order order);

    void shipOrder(Order order);

    void deliverOrder(Order order);
}
