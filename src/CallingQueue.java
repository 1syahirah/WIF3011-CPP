import java.util.Random;

public class CallingQueue implements Runnable{
    private BankingQueue queue;
    private Random random = new Random();

    public CallingQueue(BankingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // Continue running until arriving is done AND queue is empty
        while (!queue.isFinished() || !queue.isEmpty()) {
            Integer called = queue.dequeue();
            
            if (called == null) {
                System.out.println("No customer to call");
            }
            
            try {
                // Calls next number every 200ms to 300ms
                Thread.sleep(200 + random.nextInt(101)); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("All customers called.");
    }
}
