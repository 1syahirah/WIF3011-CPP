
public class Main  {

    public static void main(String[] args){
        
        // Lambda expression implementing SquareChecker's single abstract
        // method, check(int). Given a number, it returns true if that
        // number is a perfect square.
        SquareChecker isPerfectSquare = n -> {
            if (n < 0) {
                return false;
            }
            int root = (int) Math.sqrt(n);
            return root * root == n;
        };
 
        int[] numbers = {1, 2, 3, 4, 9, 15, 16, 20, 25, 30};
 
        for (int number : numbers) {
            if (isPerfectSquare.check(number)) {
                System.out.println(number + " is a perfect square");
            } else {
                System.out.println(number + " is not a perfect square");
            }
        }
    }
}

