import org.w3c.dom.css.Rect;

import java.util.*;
import java.io.*;
class Account{
    private String maID,customerID,userName,password;

    public Account(String maID, String customerID, String userName, String password) {
        this.maID = maID;
        this.customerID = customerID;
        this.userName = userName;
        this.password = password;
    }

    public String getMaID() {
        return maID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Vector<Account> v = new Vector<>();
        sc.nextLine();
        for(int i = 1;i <= n;i++){
            Account ac = new Account(sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine());
            v.add(ac);
        }
        int q = sc.nextInt();
        sc.nextLine();
        while(q-- > 0){
            String username = sc.nextLine();
            String password = sc.nextLine();
            boolean flag = false;
            for(var tmp : v){
                if(tmp.getUserName().equals(username) && tmp.getPassword().equals(password)){
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("Login Successful");
            }
            else{
                System.out.println("Login Failed");
            }
        }
    }
}
