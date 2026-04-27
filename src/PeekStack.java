public class PeekStack implements Runnable{
    private StackAccess stackAccess;

    public PeekStack(StackAccess stackAccess) {
        this.stackAccess = stackAccess;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName() + " (Peek)";
        for (int i = 0; i < 4; i++) {
            stackAccess.peek(name);
            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }
    }
}
