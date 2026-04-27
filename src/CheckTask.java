public class CheckTask implements Runnable {
    private SharedData data;

    public CheckTask(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        int lastSeenValue = 0;
        
        // Stops the loop (and thus the thread) when counter reaches 5000
        while (lastSeenValue < 5000) {
            // Only print when the value has actually changed from our last read
            if (data.counter > lastSeenValue) {
                lastSeenValue = data.counter;
                System.out.println("Counter changed: " + lastSeenValue);
            }
        }
    }
}
