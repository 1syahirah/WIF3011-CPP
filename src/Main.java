
public class Main  {
    public static void main(String[] args) throws Exception {
         BankAccountSync account = new BankAccountSync();

        // Creating multiple threads to perform concurrent deposits/withdrawals [cite: 232]
        Runnable task = () -> {
            for (int i = 0; i < 2; i++) {
                account.deposit(50);
                account.withdraw(30);
            }
        };

        Thread t1 = new Thread(task, "User_A");
        Thread t2 = new Thread(task, "User_B");

        t1.start();
        t2.start();
    }
}
