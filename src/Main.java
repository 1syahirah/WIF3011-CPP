import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main  {
    public static void main(String[] args) throws Exception {
    System.out.println("Starting Concurrency Tests...\n");

        runScenario("1. Only Push Task", new WriteStack(new StackAccess()));
        
        StackAccess sa2 = new StackAccess();
        runScenario("2. Push & Pop Tasks", new WriteStack(sa2), new ReadStack(sa2));
        
        StackAccess sa3 = new StackAccess();
        runScenario("3. Push & Peek Tasks", new WriteStack(sa3), new PeekStack(sa3));
        
        StackAccess sa4 = new StackAccess();
        runScenario("4. All Three Types of Tasks", new WriteStack(sa4), new ReadStack(sa4), new PeekStack(sa4));
        
        StackAccess sa5 = new StackAccess();
        runScenario("5. Produce MORE than Consumed (2 Writes, 1 Read)", 
                new WriteStack(sa5), new WriteStack(sa5), new ReadStack(sa5));
        
        StackAccess sa6 = new StackAccess();
        runScenario("6. Consume MORE than Produced (1 Write, 2 Reads)", 
                new WriteStack(sa6), new ReadStack(sa6), new ReadStack(sa6));
    }

    // Helper method to isolate and cleanly test each scenario using a Thread Pool of size 3
    private static void runScenario(String scenarioName, Runnable... tasks) throws InterruptedException {
        System.out.println("======================================================");
        System.out.println("SCENARIO: " + scenarioName);
        System.out.println("======================================================");
        
        // Ensure a thread pool of exactly 3 is used
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        
        for (Runnable task : tasks) {
            threadPool.execute(task);
        }
        
        // Shut down pool and wait for all tasks (and timeouts) to finish before moving to the next test
        threadPool.shutdown();
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("\n");
    }
}
