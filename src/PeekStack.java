public class PeekStack implements Runnable {
    private final StackAccess stackAccess;

    public PeekStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            stackAccess.peek();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
