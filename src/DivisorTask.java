import java.util.concurrent.Callable;

public class DivisorTask implements Callable<DivisorResult> {
    private final int start;
    private final int end;

    public DivisorTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public DivisorResult call() {
        int maxDivisors = 0;
        int numberWithMax = start;

        for (int i = start; i <= end; i++) {
            int count = countDivisors(i);
            if (count > maxDivisors) {
                maxDivisors = count;
                numberWithMax = i;
            }
        }
        return new DivisorResult(numberWithMax, maxDivisors);
    }

    private int countDivisors(int n) {
        int count = 0;
        int limit = (int) Math.sqrt(n);
        for (int i = 1; i <= limit; i++) {
            if (n % i == 0) {
                count++; 
                if (i != n / i) {
                    count++; 
                }
            }
        }
        return count;
    }
    
}
