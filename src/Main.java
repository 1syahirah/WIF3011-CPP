import java.util.stream.IntStream;

public class Main  {
    public static void main(String[] args){
       
      long count = IntStream.rangeClosed(1, 50)
                .parallel()
                .filter(Main::processAndCheckPrime)
                .count();

        System.out.println("\nTotal prime numbers between 1 and 50: " + count);
    }

    private static boolean processAndCheckPrime(int number) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " | Processing number: " + number);
        
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

