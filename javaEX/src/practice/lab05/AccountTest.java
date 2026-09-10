package practice.lab05;

import java.math.BigDecimal;

class Account {
    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        balance = balance.add(BigDecimal.valueOf(amount));
    }

    public synchronized void withdraw(int amount) {
        balance = balance.subtract(BigDecimal.valueOf(amount));
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

class Deposit implements Runnable {
    
    private final Account account;

    public Deposit(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(1000);
        }
    }
}

class Withdraw implements Runnable {

    private final Account account;

    public Withdraw(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.withdraw(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(new BigDecimal("10000"));
        Thread t1 = new Thread(new Deposit(account));
        Thread t2 = new Thread(new Deposit(account));

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println("account = " + account.getBalance());
        System.out.println("---");

        Thread t3 = new Thread(new Withdraw(account));
        t3.start();
        t3.join();

        System.out.println("account = " + account.getBalance());
    }
}
