public class CustomerInLine implements Runnable{
    private BankingQueue queue;
    private int myNumber;

    public CustomerInLine(BankingQueue queue, int myNumber) {
        this.queue = queue;
        this.myNumber = myNumber;
    }

    @Override
    public void run() {
        // Continuously checks whether his/her number is called in a non-blocking loop
        while (true) {
            if (queue.getLastCalledNumber() == myNumber) {
                System.out.println("Great, finally #" + myNumber + " was called, now it is my turn");
                break;
            }
            // Yield to gracefully share CPU resources while spinning
            Thread.yield();
        }
    }
}
