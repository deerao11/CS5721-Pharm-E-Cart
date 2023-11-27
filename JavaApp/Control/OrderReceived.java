package Control;
class OrderReceived implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Cannot process. Order is already received.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel. Order is already received.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship. Order is already received.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Order is already delivered.");
    }
}