
public class Consumer implements Runnable {
    private int name;
    private int numberOfGoods;
    private int numberOfPurchases;

    public Consumer(int name) {
        this.name = name;
    }

    public int getName() { return name; }

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public void setNumberOfGoods(int numberOfGoods) { this.numberOfGoods = numberOfGoods; }

    public void setNumberOfPurchases(int numberOfPurchases) { this.numberOfPurchases = numberOfPurchases; }

    @Override
    public void run() {
        Store store = Store.getInstance();
        while (store.getGoods() > 0) {
            synchronized (Store.getInstance()) {
                if (store.getGoods() == 0) break;
                int quantity = 1 + (int) (Math.random() * 9);
                store.tryToBuy(this, quantity);
            }
        }
        System.out.println("\nИТОГО: у потребителя " + getName() + " " + getNumberOfGoods() + " товаров(а) и " + getNumberOfPurchases() + " покупок(и)");
    }
}