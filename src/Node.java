import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<T> {
    private T value;
    private Lock lock;
    private Condition valueChanged;

    public Node() {
        lock = new ReentrantLock();
        valueChanged = lock.newCondition();
    }

    // Set value and notify waiting threads
    public void setValue(T newValue) {
        lock.lock();
        try {
            value = newValue;
            System.out.println("Value updated to: " + value);
            valueChanged.signalAll(); // notify all waiting threads
        } finally {
            lock.unlock();
        }
    }

    // Execute task when desired value is found
    public void executeOnValue(T desiredValue, Runnable task) {
    lock.lock();
    try {
        // Wait until value == desiredValue
        while (!desiredValue.equals(value)) {
            valueChanged.await();
        }

        // Execute task
        task.run();

        // 🚨 IMPORTANT: wait until value changes away
        while (desiredValue.equals(value)) {
            valueChanged.await();
            
        }

    } catch (InterruptedException e) {
        e.printStackTrace();
    } finally {
        lock.unlock();
    }
}
    
}
