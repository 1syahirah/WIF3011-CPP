public class ReadStack implements Runnable {
    private StackAccess stackAccess;

    public ReadStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName() + " (Read)";
        for (int i = 0; i < 4; i++) {
            stackAccess.pop(name);
            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }
    }
}
