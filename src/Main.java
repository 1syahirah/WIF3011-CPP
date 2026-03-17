import java.util.Scanner;

public class Main extends Thread {
    public static void main(String[] args) throws Exception {
        //Scanner myObj = new Scanner(System.in);

        // System.out.print("Enter character:");
        // String letter = myObj.nextLine();
        
        // System.out.print("Enter a number:");
        // int time = myObj.nextInt();

        // PrintChar q1 = new PrintChar("A", 5);
        // PrintNum q2 = new PrintNum(10);

        Control control = new Control();
        Thread t1 = new Thread(new PrintChar("A", 5,1, control));
        Thread t3 = new Thread(new PrintNum(20,2,control));
        Thread t2 = new Thread(new PrintChar("B", 5,3,control));

        t1.start();
        t2.start();
        t3.start();


        //they run these first baru threads?
        // q1.printChar("A", 2);
        // q2.printNum(5); 
    }
}
