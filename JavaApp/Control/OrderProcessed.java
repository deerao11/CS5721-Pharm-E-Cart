// Concrete State classes
class OrderProcessed implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Order is already processed.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cancelling order.");
        order.setState(new OrderCancelled());
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Shipping order.");
        order.setState(new OrderShipped());
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Cannot deliver. Order is not shipped yet.");
    }
}