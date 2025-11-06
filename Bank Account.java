import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;
class bankAccount{
    private String id,customerID,stk,pin;
    private int soDu;

    public bankAccount(String id, String customerID, String stk, String pin, int soDu) {
        this.id = id;
        this.customerID = customerID;
        this.stk = stk;
        this.pin = pin;
        this.soDu = soDu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    @Override
    public String toString() {
        return "ID : " + id + "\n" + "CusID : " + customerID + "\n" +
                "Number : " + stk + "\n" + "PIN : " + pin + "\n" + "Balance : " + soDu + "VND\n";
    }
}
public class Main {
    static HashMap<String,bankAccount> mp = new HashMap<>();
    public static void deposit(String accountNumber,int amount){
        bankAccount account = mp.get(accountNumber);
        if(account != null){
            account.setSoDu(account.getSoDu() + amount);
        }
    }
    public static void withdraw(String accountNumber,int amount){
        bankAccount account = mp.get(accountNumber);
        if (account != null && account.getSoDu() - amount >= 50000) {
            account.setSoDu(account.getSoDu() - amount);
        }
    }
    public static void transfer(String sender, String receiver, int amount){
        bankAccount fromAccount = mp.get(sender);
        bankAccount toAcccount = mp.get(receiver);
        if(fromAccount != null && toAcccount != null && fromAccount.getSoDu() - amount >= 50000){
            fromAccount.setSoDu(fromAccount.getSoDu() - amount);
            toAcccount.setSoDu(toAcccount.getSoDu() + amount);
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Vector<bankAccount> v = new Vector<>();
        for(int i = 1;i <= n;i++){
            sc.nextLine();
            bankAccount account = new bankAccount(sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextInt());
            mp.put(account.getStk(),account);
            v.add(account);
        }
        int q = sc.nextInt();   
        sc.nextLine();
        for(int i = 1; i <= q;i++){
            String type = sc.nextLine();
            String []value = sc.nextLine().split("\\s+");
            if(type.equals("deposit")){
                deposit(value[0],Integer.parseInt(value[1]));
            }
            if(type.equals("withdraw")){
                withdraw(value[0],Integer.parseInt(value[1]));
            }
            if(type.equals("transfer")){
                transfer(value[0],value[1],Integer.parseInt(value[2]));
            }
        }
        for(var tmp : v){
            System.out.print(tmp);
            System.out.println("-------------------");
        }
    }
}
