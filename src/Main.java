import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main  {
    public static void main(String[] args){
         List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Poseidon", "M", 23));
        employees.add(new Employee("Hera", "F", 18));
        employees.add(new Employee("Apollo", "M", 20));
        employees.add(new Employee("Athena", "F", 35));
        employees.add(new Employee("Demeter", "F", 50));
 
        // Solution 1: single filter with a compound lambda condition
        System.out.println("Solution 1:");
        employees.stream()
                .filter(e -> e.getGender().equals("F") && e.getAge() >= 21)
                .forEach(System.out::println);
 
        // Solution 2: two separate Predicates combined using Predicate.and()
        System.out.println("\nSolution 2:");
        Predicate<Employee> isFemale = e -> e.getGender().equals("F");
        Predicate<Employee> isAgeAbove21 = e -> e.getAge() >= 21;
 
        employees.stream()
                .filter(isFemale.and(isAgeAbove21))
                .forEach(System.out::println);
 
        // Solution 3: two separate chained filter() calls instead of one
        // compound condition
        System.out.println("\nSolution 3:");
        employees.stream()
                .filter(e -> e.getGender().equals("F"))
                .filter(e -> e.getAge() >= 21)
                .forEach(System.out::println);
 
        // Solution 4: method reference to an instance method (isEligible)
        // defined directly in Employee
        System.out.println("\nSolution 4:");
        employees.stream()
                .filter(Employee::isEligible)
                .forEach(System.out::println);
    }
}

