//1.	Write a Java program that sequentially finds the largest number in 
// an array of integers with 1,000,000 elements filled with randomly generated
//  numbers in the range of 1 to 50,000.
//2. Repeat the above program but using two threads to concurrently find the largest number.


import java.util.Random;

public class Main  {
    public static void main(String[] args) throws Exception {
         Random rd = new Random();

         int[] arr = rd.ints(1000000,1,50001).toArray();
        
        int mid = arr.length/2;

        FindMax task1 = new FindMax(arr, 0, mid);
        FindMax task2 = new FindMax(arr, mid, arr.length);

         Thread t1 = new Thread(task1);
         Thread t2 = new Thread(task2);

         t1.start();
         t2.start();

        t1.join();
        t2.join();

        int largest = Math.max(task1.getLocalMax(), task2.getLocalMax());

        System.out.println("Largest number: " + largest);
    }
}
