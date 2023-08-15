package mingleBox;

public class Buyer extends User{
	private double balance;

    public Buyer(String username, String password) {
        super(username, password);
        this.balance = 10000.0; // Initial balance
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
		this.balance = balance;
	}

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public void addBalance(double amount) {
        balance += amount;
    }

}
