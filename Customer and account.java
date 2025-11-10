import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;
class Customer{
    private int cusID;
    private String name;
    private char gender;

    public Customer(int cusID, String name, char gender) {
        this.cusID = cusID;
        this.name = name;
        this.gender = gender;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    public String toString(){
        return "Customer ID : " + cusID + "\n" + "Full Name : " + name + "\n" + "Gender : " + gender + "\n";
    }
}
class Account{
    private int accountID;
    private Customer customer;
    private double balance;

    public Account(int accountID, Customer customer, double balance) {
        this.accountID = accountID;
        this.customer = customer;
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getCustomerName(){
        return customer.getName();
    }
    public void deposit(double amount){
        setBalance(amount + getBalance());
        System.out.print("transaction successful\n");
    }
    public void withdraw(double amount){
        if(getBalance() >= amount){
            setBalance(getBalance() - amount);
            System.out.print("transaction successful\n");
        }
        else{
            System.out.print("amount withdrawn exceeds the current balance!\n");
        }
    }
    public String toString(){
        return customer.toString() + "Account ID : " + accountID + "\n" + "Balance : " + String.format("%.2f",balance) + " $\n";
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int customerID = Integer.parseInt(sc.nextLine().substring(14));
        String fullName = sc.nextLine().substring(12);
        char gender = sc.nextLine().substring(9).charAt(0);
        int accountID = Integer.parseInt(sc.nextLine().substring(13));
        String balan = sc.nextLine();
        double balance = Double.parseDouble(balan.substring(10,balan.length() - 1));
        Customer customer = new Customer(customerID,fullName,gender);
        Account account = new Account(accountID,customer,balance);
        sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < n;i++){
            String s = sc.nextLine();
            String []parts = s.trim().split("\\s+");
            String option = parts[0];
            int amount = Integer.parseInt(parts[1]);
            if(option.equals("withdraw")){
                account.withdraw(amount);
            }
            else if(option.equals("deposit")){
                account.deposit(amount);
            }
        }
        System.out.print("-------------------\n");
        System.out.print(account);
    }
}
