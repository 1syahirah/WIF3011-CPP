
import java.util.Random;

public class Main  {
    public static void main(String[] args) throws Exception {
         Random rd = new Random();

         int[] arr = rd.ints(1000000,1,50001).toArray();
        
       // int mid = arr.length/2;

        FindMax task1 = new FindMax(arr, 0, 250000);
        FindMax task2 = new FindMax(arr, 250000, 500000);
        FindMax task3 = new FindMax(arr, 500000, 750000);
        FindMax task4 = new FindMax(arr, 750000, arr.length);


         Thread t1 = new Thread(task1);
         Thread t2 = new Thread(task2);
         Thread t3 = new Thread(task3);
         Thread t4 = new Thread(task4);

         Timer timer = new Timer();
         timer.start();

         t1.start();
         t2.start();
         t3.start();
         t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

       timer.stop();

        int largest1 = Math.max(task1.getLocalMax(), task2.getLocalMax());
        int largest2 = Math.max(task3.getLocalMax(), task4.getLocalMax());
        int largest = Math.max(largest1, largest2);


        System.out.println("Largest number: " + largest);
        System.out.println("Time taken: " + timer.getTimer());
    }
}
