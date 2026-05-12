import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main  {
    public static void main(String[] args) throws Exception {
        final int MAX_RANGE = 1000001;
        final int BATCH_SIZE = 1000;

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
        List<Future<DivisorResult>> futures = new ArrayList<>();

        for (int start = 1; start <= MAX_RANGE; start += BATCH_SIZE) {
            int end = Math.min(start + BATCH_SIZE - 1, MAX_RANGE);
            DivisorTask task = new DivisorTask(start, end);
            futures.add(executor.submit(task));
        }

        DivisorResult overallMax = new DivisorResult(0, 0);

        try {
            for (Future<DivisorResult> future : futures) {
                DivisorResult result = future.get(); 
                
                if (result.divisorCount > overallMax.divisorCount) {
                    overallMax = result;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("An error occurred during execution: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        System.out.printf("In the range of 1 to %d, %d has the most divisors of %d%n",
                MAX_RANGE, overallMax.number, overallMax.divisorCount);
    }
}
