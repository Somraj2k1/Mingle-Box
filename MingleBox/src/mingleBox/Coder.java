package mingleBox;

import java.util.List;

public class Coder extends User {
	private List<String> languages;
	private double balance;

    public Coder(String username, String password, List<String> languages) {
        super(username, password);
        this.languages = languages;
        this.balance = 0;
    }

    public List<String> getLanguages() {
        return languages;
    }

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addBalance(double amount) {
        balance += amount;
    }
}
