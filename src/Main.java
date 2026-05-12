import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main  {
    public static void main(String[] args) throws Exception {
    System.out.println("=== Starting StackAccess Tests ===");

        // 1. Only push task
        runScenario("Only Push", new WriteStack(new StackAccess()));
        
        // 2. Only pop task
        runScenario("Only Pop", new ReadStack(new StackAccess()));

        // 3. Only peek task
        runScenario("Only Peek", new PeekStack(new StackAccess()));

        // 4. Two types: Push & Pop
        StackAccess sa1 = new StackAccess();
        runScenario("Push & Pop", new WriteStack(sa1), new ReadStack(sa1));

        // 5. Two types: Push & Peek
        StackAccess sa2 = new StackAccess();
        runScenario("Push & Peek", new WriteStack(sa2), new PeekStack(sa2));

        // 6. Two types: Pop & Peek
        StackAccess sa3 = new StackAccess();
        runScenario("Pop & Peek", new ReadStack(sa3), new PeekStack(sa3));

        // 7. Three types: Push, Pop, Peek
        StackAccess sa4 = new StackAccess();
        runScenario("Push, Pop & Peek", new WriteStack(sa4), new ReadStack(sa4), new PeekStack(sa4));

        // 8. Produce more than consumed (2 Writers, 1 Reader)
        StackAccess sa5 = new StackAccess();
        runScenario("Produce > Consume", new WriteStack(sa5), new WriteStack(sa5), new ReadStack(sa5));

        // 9. Consume more than produced (1 Writer, 2 Readers)
        StackAccess sa6 = new StackAccess();
        runScenario("Consume > Produce", new WriteStack(sa6), new ReadStack(sa6), new ReadStack(sa6));
    }

    /**
     * Helper method to initialize a thread pool, run a specific scenario, and wait for its completion
     * before moving on to the next scenario. This keeps console output organized.
     */
    private static void runScenario(String scenarioName, Runnable... tasks) throws InterruptedException {
        System.out.println("\n--- Scenario: " + scenarioName + " ---");
        
        // Thread pool of 3 as requested
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (Runnable task : tasks) {
            executor.execute(task);
        }
        
        // Initiate orderly shutdown and wait for tasks to finish (or timeout after 5 seconds)
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}
