
public class Main  {
    public static void main(String[] args){
        int a = 10;
        int b = 5;
 
        // Each lambda below implements MathOperation's single abstract
        // method, operation(int, int), with a different calculation.
        MathOperation addition = (x, y) -> x + y;
        MathOperation subtraction = (x, y) -> x - y;
        MathOperation multiplication = (x, y) -> x * y;
        MathOperation division = (x, y) -> x / y;
 
        System.out.println(a + " + " + b + " = " + addition.operation(a, b));
        System.out.println(a + " - " + b + " = " + subtraction.operation(a, b));
        System.out.println(a + " * " + b + " = " + multiplication.operation(a, b));
        System.out.println(a + " / " + b + " = " + division.operation(a, b));
    }
}
