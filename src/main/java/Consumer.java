
public class Consumer implements Runnable {
    private int name;
    private int numberOfGoods;
    private int numberOfPurchases;
    private Thread thread;

    public Consumer(int name) {
        this.name = name;
        this.thread = new Thread(this);
    }

    public int getName() { return name; }

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public int getNumberOfPurchases() {
        return numberOfPurchases;
    }

    public Thread getThread() {
        return thread;
    }

    public void setNumberOfGoods(int numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }

    public void setNumberOfPurchases(int numberOfPurchases) {
        this.numberOfPurchases = numberOfPurchases;
    }

    @Override
    public void run() {
        Store store = Store.getInstance();
        while (store.getGoods() > 0) {
            synchronized (store) {
                if (checkTaskCondition()) {
                    int quantity = 1 + (int) (Math.random() * 9);
                    if (quantity > store.getGoods())
                        quantity = store.getGoods();
                    setNumberOfGoods(getNumberOfGoods() + quantity);
                    setNumberOfPurchases(getNumberOfPurchases() + 1);
                    store.setGoods(store.getGoods() - quantity);
                    if (getNumberOfPurchases() >  store.getMaxPurchases())
                        store.setMaxPurchases(getNumberOfPurchases());
                    System.out.println("Потребитель " + getName() + " купил " + quantity + " товаров;");
                    System.out.println("Сейчас у него " + getNumberOfGoods() + " товаров и " + getNumberOfPurchases() + " покупок;");
                    System.out.println("В магазине осталось " + store.getGoods() + " товаров, а макс число покупок = " + store.getMaxPurchases());
                }
            }
            getThread().yield();
        }
    }

    public synchronized boolean checkTaskCondition() {
        if (getNumberOfPurchases() - Store.getInstance().getMaxPurchases() > 0) {
            System.out.printf("Потребитель %d не может совершить новую покупку. Система не позволяет\n", getName());
            return false;
        }
        return true;
    }
}