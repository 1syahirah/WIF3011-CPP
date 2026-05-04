public class BankTransaction implements Runnable{

    private BankAccountSync acc;

    public BankTransaction(BankAccountSync acc){
        this.acc = acc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            acc.deposit(50);
            acc.withdraw(30);
        }
    }
}     

