//1.	Write a Java program that sequentially finds the largest number in 
// an array of integers with 1,000,000 elements filled with randomly generated
//  numbers in the range of 1 to 50,000.


import java.util.Random;

public class Main  {
    public static void main(String[] args) throws Exception {
         Random rd = new Random();

         int[] arr = rd.ints(1000000,1,50001).toArray();
        
         int largest = arr[0];
         
        for(int i = 0; i<arr.length; i++){
            if(arr[i] > largest){
                largest = arr[i];
            }
        }

         System.out.println("largest num: " + largest);
    }
}
