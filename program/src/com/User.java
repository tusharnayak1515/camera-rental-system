package com;
public class User {
    private String username;
    private String password;
    private double walletBalance;

    public User() {
        setWalletBalance(10000);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getWalletBalance() {
        return walletBalance;
    }
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public void addToWallet(double amount)
	{
		double balance = getWalletBalance();
        setWalletBalance(balance+amount);
	}
}
