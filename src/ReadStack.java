public class ReadStack implements Runnable {
    private final StackAccess stackAccess;

    public ReadStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            stackAccess.pop();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
