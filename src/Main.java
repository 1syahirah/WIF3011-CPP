
public class Main  {
    public static void main(String[] args) throws Exception {
        //shared resource
         BankAccountSync account = new BankAccountSync();

         //create the task and give shared resource
        BankTransaction bankTask = new BankTransaction(account);

        Thread t1 = new Thread(bankTask, "User_A");
        Thread t2 = new Thread(bankTask, "User_B");

        t1.start();
        t2.start();
    }
}
