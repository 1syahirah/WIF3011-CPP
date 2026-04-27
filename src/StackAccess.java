import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StackAccess {
    private final Stack<Integer> stack = new Stack<>();
    private final int CAPACITY = 3;
    
    // Concurrency control tools
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void push(int item, String taskName) {
        lock.lock();
        try {
            // Wait while the stack is full
            while (stack.size() == CAPACITY) {
                System.out.println(taskName + " waiting. Stack is FULL.");
                // Wait for 1 second. If it returns false, the timeout expired.
                boolean signaled = notFull.await(1, TimeUnit.SECONDS);
                if (!signaled) {
                    System.out.println(taskName + " DISCARDED push (" + item + ") after 1 second wait.");
                    return;
                }
            }
            
            // Critical Section
            stack.push(item);
            System.out.println(taskName + " PUSHED: " + item + " | Stack size: " + stack.size());
            
            // Signal any waiting consumers that the stack is no longer empty
            notEmpty.signalAll();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // Always unlock in a finally block to prevent deadlocks
        }
    }

    public Integer pop(String taskName) {
        lock.lock();
        try {
            // Wait while the stack is empty
            while (stack.isEmpty()) {
                System.out.println(taskName + " waiting. Stack is EMPTY.");
                boolean signaled = notEmpty.await(1, TimeUnit.SECONDS);
                if (!signaled) {
                    System.out.println(taskName + " DISCARDED pop after 1 second wait.");
                    return null;
                }
            }
            
            // Critical Section
            Integer item = stack.pop();
            System.out.println(taskName + " POPPED: " + item + " | Stack size: " + stack.size());
            
            // Signal any waiting producers that the stack is no longer full
            notFull.signalAll();
            return item;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    public Integer peek(String taskName) {
        lock.lock();
        try {
            // We apply the same 1-second wait logic to peek so it doesn't instantly fail
            while (stack.isEmpty()) {
                System.out.println(taskName + " waiting to peek. Stack is EMPTY.");
                boolean signaled = notEmpty.await(1, TimeUnit.SECONDS);
                if (!signaled) {
                    System.out.println(taskName + " DISCARDED peek after 1 second wait.");
                    return null;
                }
            }
            
            // Critical Section (Retrieve without delete)
            Integer item = stack.peek();
            System.out.println(taskName + " PEEKED: " + item + " | Stack size: " + stack.size());
            return item;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }
}
