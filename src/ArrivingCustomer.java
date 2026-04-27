import java.util.Random;

public class ArrivingCustomer implements Runnable {
    private BankingQueue queue;
    private Random random = new Random();
    private int totalCustomers = 15; // Set to 15 to match the sample output

    public ArrivingCustomer(BankingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= totalCustomers; i++) {
            queue.enqueue(i);
            try {
                // Arrives every 100ms to 150ms
                Thread.sleep(100 + random.nextInt(51)); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Signal that no more customers are arriving
        queue.setFinished(true);
    }
    
}
