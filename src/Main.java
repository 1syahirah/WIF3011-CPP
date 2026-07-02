import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main  {

    // Takes a list of integers and a Predicate<Integer>.
    // For each element, calls predicate.test(element) — if it returns true,
    // the element is printed.
    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (int number : list) {
            if (predicate.test(number)) {
                System.out.println(number);
            }
        }
    }
    public static void main(String[] args){
       List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }
 
        System.out.println("Print all numbers:");
        evaluate(numbers, n -> true);
 
        System.out.println("\nPrint odd numbers:");
        evaluate(numbers, n -> n % 2 != 0);
 
        System.out.println("\nPrint even numbers:");
        evaluate(numbers, n -> n % 2 == 0);
 
        System.out.println("\nPrint numbers greater than 5:");
        evaluate(numbers, n -> n > 5);
    }
}
