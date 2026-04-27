import java.util.Random;

public class Main  {
    public static void main(String[] args) throws Exception {
      BankingQueue queue = new BankingQueue();
        Random random = new Random();

        // Assign two random, distinct numbers between 1 and 12
        int wait1 = 1 + random.nextInt(12);
        int wait2;
        do {
            wait2 = 1 + random.nextInt(12);
        } while (wait1 == wait2); 

        System.out.println("Numbers to wait: " + wait1 + " " + wait2);

        // Initialize threads
        Thread arrivingThread = new Thread(new ArrivingCustomer(queue));
        Thread callingThread = new Thread(new CallingQueue(queue));
        Thread cust1Thread = new Thread(new CustomerInLine(queue, wait1));
        Thread cust2Thread = new Thread(new CustomerInLine(queue, wait2));

        // Start tasks
        callingThread.start(); // Start calling first to potentially trigger "No customer to call"
        arrivingThread.start();
        cust1Thread.start();
        cust2Thread.start();
    }
}
