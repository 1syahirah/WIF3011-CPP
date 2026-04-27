import java.util.Random;

public class WriteStack implements Runnable {
    private StackAccess stackAccess;
    private Random random = new Random();

    public WriteStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName() + " (Write)";
        for (int i = 0; i < 4; i++) {
            int randomNum = random.nextInt(100); // Generate 0 to 99
            stackAccess.push(randomNum, name);
            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }
    }
}
