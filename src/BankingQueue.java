import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class BankingQueue {
    // Non-blocking approach: Optimistic locking using AtomicReference
    private AtomicReference<ArrayList<Integer>> queueRef = new AtomicReference<>(new ArrayList<>());
    
    // Volatile variable ensures visibility without blocking
    private volatile int lastCalledNumber = 0;
    private volatile boolean isFinished = false; 

    public void enqueue(int customerNumber) {
        while (true) {
            ArrayList<Integer> currentQueue = queueRef.get();
            // Copy the data structure to apply intended modifications
            ArrayList<Integer> newQueue = new ArrayList<>(currentQueue);
            newQueue.add(customerNumber);
            
            // Swap in the new queue if no other thread modified it in the meantime
            if (queueRef.compareAndSet(currentQueue, newQueue)) {
                System.out.println("Customer " + customerNumber + " has arrived.");
                break;
            }
        }
    }

    public Integer dequeue() {
        while (true) {
            ArrayList<Integer> currentQueue = queueRef.get();
            if (currentQueue.isEmpty()) {
                return null;
            }
            // Copy the data structure to apply intended modifications
            ArrayList<Integer> newQueue = new ArrayList<>(currentQueue);
            Integer called = newQueue.remove(0);
            
            // Swap in the new queue
            if (queueRef.compareAndSet(currentQueue, newQueue)) {
                lastCalledNumber = called;
                System.out.println("Calling customer number: " + called);
                return called;
            }
        }
    }

    public int getLastCalledNumber() {
        return lastCalledNumber;
    }

    public boolean isEmpty() {
        return queueRef.get().isEmpty();
    }

    public void setFinished(boolean finished) {
        this.isFinished = finished;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
