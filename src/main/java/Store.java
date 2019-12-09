
public class Store {
    private static Store store;
    private volatile int goods = 1000;
    private int maxPurchases;

    private Store() {
    }

    public static Store getInstance() {
        if (store == null)
            return new Store();
        else
            return store;
    }

    public int getGoods() {
        return goods;
    }

    public int getMaxPurchases() {
        return maxPurchases;
    }

    public void setMaxPurchases(int maxPurchases) {
        this.maxPurchases = maxPurchases;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }
}
