package Control;

interface IObservable {
    public void addObs(IObserver observer);
    public void removeObs(IObserver observer);
    public void notifyObservers(String orderId);
}