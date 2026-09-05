package practice.lab03.exception;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int withdrawal(int amount) throws InsufficientBalanceException {
        if (amount > balance) throw new InsufficientBalanceException("Insufficient balance");

        return this.balance -= amount;
    }
}
