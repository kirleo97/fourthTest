
public class Task {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        for (int i = 1; i < 11; i++) {
            new Consumer(i).getThread().start();
        }
        while (true) {
            if (store.getGoods() == 0) {
                System.out.println("Конец циклов");
                break;
            }
        }
    }
}