public class BankAccountSync {
    private double balance = 0;

    // Control mechanism to ensure balance is always correct
    public synchronized void deposit(double amount) {
        balance += amount; 
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". New Balance: " + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". New Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " failed: Insufficient funds.");
        }
    }
}
