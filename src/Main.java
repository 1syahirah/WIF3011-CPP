import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main  {
    public static void main(String[] args) throws Exception {
        int n1 = 30;
        int n2 = 31;

        // ==========================================
        //         SEQUENTIAL CALCULATION
        // ==========================================
        System.out.println("Sequential Calculation");
        Timer totalSeqTimer = new Timer();
        totalSeqTimer.start();
        
        // Calculate Fibonacci 30
        System.out.println("Calculating Fibonacci(" + n1 + ")");
        Timer timer1 = new Timer();
        timer1.start();
        long seqFib1 = fibonacci(n1);
        timer1.stop();
        System.out.println("Fibonacci(" + n1 + ") = " + seqFib1);
        System.out.printf("Calculation time for Fibonacci(%d) = %d milliseconds%n", n1, timer1.getTimer());

        // Calculate Fibonacci 31
        System.out.println("Calculating Fibonacci(" + n2 + ")");
        Timer timer2 = new Timer();
        timer2.start();
        long seqFib2 = fibonacci(n2);
        timer2.stop();
        System.out.println("Fibonacci(" + n2 + ") = " + seqFib2);
        System.out.printf("Calculation time for Fibonacci(%d) = %d milliseconds%n", n2, timer2.getTimer());

        totalSeqTimer.stop();
        System.out.println("Total sequential calculation time = " + totalSeqTimer.getTimer() + " milliseconds\n");


        // ==========================================
        //         ASYNCHRONOUS CALCULATION
        // ==========================================
        System.out.println("Asynchronous Calculation");
        System.out.println("Calculating Fibonacci(" + n1 + ")");
        System.out.println("Calculating Fibonacci(" + n2 + ")");

        Timer totalAsyncTimer = new Timer();
        totalAsyncTimer.start();

        // Launch Task 1 asynchronously
        CompletableFuture<CalcResult> future1 = CompletableFuture.supplyAsync(() -> {
            Timer asyncTimer1 = new Timer();
            asyncTimer1.start();
            long fib = fibonacci(n1);
            asyncTimer1.stop();
            return new CalcResult(n1, fib, asyncTimer1);
        });

        // Launch Task 2 asynchronously
        CompletableFuture<CalcResult> future2 = CompletableFuture.supplyAsync(() -> {
            Timer asyncTimer2 = new Timer();
            asyncTimer2.start();
            long fib = fibonacci(n2);
            asyncTimer2.stop();
            return new CalcResult(n2, fib, asyncTimer2);
        });

        try {
            // Wait for both futures to complete
            CompletableFuture.allOf(future1, future2).join();
            totalAsyncTimer.stop();

            // Retrieve the results
            CalcResult asyncResult1 = future1.get();
            CalcResult asyncResult2 = future2.get();

            // Print Async Task 1 Results
            System.out.println("Fibonacci(" + n1 + ") = " + asyncResult1.fibValue);
            System.out.printf("Calculation time for Fibonacci(%d) = %d milliseconds%n", 
                              n1, asyncResult1.timer.getTimer());

            // Print Async Task 2 Results
            System.out.println("Fibonacci(" + n2 + ") = " + asyncResult2.fibValue);
            System.out.printf("Calculation time for Fibonacci(%d) = %d milliseconds%n", 
                              n2, asyncResult2.timer.getTimer());

            System.out.println("Total asynchronous calculation time = " + totalAsyncTimer.getTimer() + " milliseconds");

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("An error occurred during asynchronous execution: " + e.getMessage());
        }
    }

    static class CalcResult {
        final int n;
        final long fibValue;
        final Timer timer;

        public CalcResult(int n, long fibValue, Timer timer) {
            this.n = n;
            this.fibValue = fibValue;
            this.timer = timer;
        }
    }

    // 1. Recursive method to calculate Fibonacci
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
