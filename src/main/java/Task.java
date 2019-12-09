
public class Task {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++)
            new Thread(new Consumer(i)).start();
    }
}