class Order {
    // Context class
    private OrderState state;

    public Order() {
        this.state = new OrderProcessed();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.processOrder(this);
    }

    public void cancelOrder() {
        state.cancelOrder(this);
    }

    public void shipOrder() {
        state.shipOrder(this);
    }

    public void deliverOrder() {
        state.deliverOrder(this);
    }
}