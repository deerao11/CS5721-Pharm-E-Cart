// Concrete State classes
class OrderCancelled implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Cannot process. Order is cancelled.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Order is already cancelled.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Cannot ship. Order is cancelled.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver. Order is cancelled.");
    }
}