
public class Main  {

    public static void main(String[] args){
        // Lambda expression implementing PalindromeChecker's single
        // abstract method, isPalindrome(String). It lowercases the string
        // (so the check is case-insensitive), reverses it, and compares
        // the reversed version to the original.
        PalindromeChecker checker = s -> {
            String cleaned = s.toLowerCase();
            String reversed = new StringBuilder(cleaned).reverse().toString();
            return cleaned.equals(reversed);
        };
 
        String[] words = {"racecar", "hello", "madam", "java", "level", "Deified"};
 
        for (String word : words) {
            if (checker.isPalindrome(word)) {
                System.out.println(word + " is a palindrome");
            } else {
                System.out.println(word + " is not a palindrome");
            }
        }
       
    }
}

