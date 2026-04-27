public class IncrementTask implements Runnable {
    private SharedData data;

    public IncrementTask(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (data.counter < 5000) {
            data.counter++;
            System.out.println("Counter incremented: " + data.counter);
            
            // A tiny yield helps the threads interleave their output 
            // closer to the sample provided in your question.
            Thread.yield(); 
        }
    }
}
