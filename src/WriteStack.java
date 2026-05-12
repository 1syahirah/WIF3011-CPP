import java.util.Random;

public class WriteStack implements Runnable {
    private final StackAccess stackAccess;
    private final Random random = new Random();

    public WriteStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            stackAccess.push(random.nextInt(100)); // Arbitrary number 0-99
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
