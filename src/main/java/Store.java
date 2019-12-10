
public class Store {
    private static final Store store = new Store();
    private int goods = 1000;
    private int maxPurchases;

    private Store() {
    }

    public static Store getInstance() { return store; }

    public int getGoods() {
        return goods;
    }

    public int getMaxPurchases() { return maxPurchases; }

    public void setMaxPurchases(int maxPurchases) { this.maxPurchases = maxPurchases; }

    public void setGoods(int goods) { this.goods = goods; }


    public void tryToBuy(Consumer consumer, int quantity) {
        if (checkTaskCondition(consumer)) {
            int finalQuantity = checkBalanceOfGoods(quantity);
            changeConsumerDataAfterDeal(consumer, finalQuantity);
            setGoods(getGoods() - finalQuantity);
            if (consumer.getNumberOfPurchases() >  getMaxPurchases())
                setMaxPurchases(consumer.getNumberOfPurchases());
            printDeal(consumer, finalQuantity);
        }
    }

    public boolean checkTaskCondition(Consumer consumer) {
        if (consumer.getNumberOfPurchases() - Store.getInstance().getMaxPurchases() > 0) {
            System.out.printf("Потребитель %d не может совершить новую покупку. Система не позволяет\n", consumer.getName());
            return false;
        }
        return true;
    }

    public int checkBalanceOfGoods(int quantity) {
        int balanceOfGoods = getGoods();
        if (quantity > balanceOfGoods) {
            System.out.printf("Количество требуемого товара меньше остатка на складе. Вы можете купить только %d товаров(а)\n", balanceOfGoods);
            return balanceOfGoods;
        }
        return quantity;
    }

    public void changeConsumerDataAfterDeal(Consumer consumer, int goods) {
        consumer.setNumberOfGoods(consumer.getNumberOfGoods() + goods);
        consumer.setNumberOfPurchases(consumer.getNumberOfPurchases() + 1);
    }

    public void printDeal(Consumer consumer, int quantity) {
        System.out.println("Потребитель " + consumer.getName() + " купил " + quantity + " товаров(а);");
        System.out.println("Сейчас у него " + consumer.getNumberOfGoods() + " товаров(а) и " + consumer.getNumberOfPurchases() + " покупок(и);");
        System.out.println("В магазине осталось " + getGoods() + " товаров(а), а максимальное число покупок = " + getMaxPurchases() + "\n");
    }
}