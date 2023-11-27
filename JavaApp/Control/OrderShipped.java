class OrderShipped implements OrderState {
    @Override
    public void processOrder(Order order) {
        System.out.println("Cannot process. Order is already shipped.");
    }

    @Override
    public void cancelOrder(Order order) {
        System.out.println("Cannot cancel. Order is already shipped.");
    }

    @Override
    public void shipOrder(Order order) {
        System.out.println("Order is already shipped.");
    }

    @Override
    public void deliverOrder(Order order) {
        System.out.println("Delivering order.");
        order.setState(new OrderReceived());
    }
}