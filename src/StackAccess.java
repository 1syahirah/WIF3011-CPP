import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackAccess {
    private final Stack<Integer> stack = new Stack<>();
    private final int CAPACITY = 3;
    
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void push(int value) {
        lock.lock();
        try {
            long waitTime = TimeUnit.SECONDS.toNanos(1);
            while (stack.size() == CAPACITY) {
                if (waitTime <= 0) {
                    System.out.println(Thread.currentThread().getName() + " [DISCARD] Push operation for " + value + " discarded (Timeout). Stack is full.");
                    return;
                }
                waitTime = notFull.awaitNanos(waitTime);
            }
            stack.push(value);
            System.out.println(Thread.currentThread().getName() + " [PUSH] Pushed " + value + ". Stack size: " + stack.size());
            notEmpty.signalAll(); // Notify readers/peekers that stack is not empty
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void pop() {
        lock.lock();
        try {
            long waitTime = TimeUnit.SECONDS.toNanos(1);
            while (stack.isEmpty()) {
                if (waitTime <= 0) {
                    System.out.println(Thread.currentThread().getName() + " [DISCARD] Pop operation discarded (Timeout). Stack is empty.");
                    return;
                }
                waitTime = notEmpty.awaitNanos(waitTime);
            }
            int value = stack.pop();
            System.out.println(Thread.currentThread().getName() + " [POP] Popped " + value + ". Stack size: " + stack.size());
            notFull.signalAll(); // Notify writers that stack is not full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void peek() {
        lock.lock();
        try {
            long waitTime = TimeUnit.SECONDS.toNanos(1);
            while (stack.isEmpty()) {
                if (waitTime <= 0) {
                    System.out.println(Thread.currentThread().getName() + " [DISCARD] Peek operation discarded (Timeout). Stack is empty.");
                    return;
                }
                waitTime = notEmpty.awaitNanos(waitTime);
            }
            int value = stack.peek();
            System.out.println(Thread.currentThread().getName() + " [PEEK] Peeked " + value + ". Stack size: " + stack.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
