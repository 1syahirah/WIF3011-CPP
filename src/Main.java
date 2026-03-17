
public class Main extends Thread {
    public static void main(String[] args) throws Exception {
        //Scanner myObj = new Scanner(System.in);

        // System.out.print("Enter character:");
        // String letter = myObj.nextLine();
        
        // System.out.print("Enter a number:");
        // int time = myObj.nextInt();

        // PrintChar q1 = new PrintChar("A", 5);
        // PrintNum q2 = new PrintNum(10);

        Thread t1 = new Thread(new PrintChar("A", 3));
        Thread t2 = new Thread(new PrintChar("B", 15));
        Thread t3 = new Thread(new PrintNum(5));

        t1.start();
        t2.start();
        t3.start();


        //they run these first baru threads?
        // q1.printChar("A", 2);
        // q2.printNum(5); 
    }
}
