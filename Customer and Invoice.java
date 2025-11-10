import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;
class Customer{
    private int id;
    private String name;
    private int discount;

    public Customer(int id, String name, int discount) {
        this.id = id;
        this.name = name;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String toString(){
        return "Customer ID : " + id + "\nFull Name : " + name + "\n";
    }
}
class Invoice{
    private int InvoiceId;
    private Customer customer;
    private double amount;

    public Invoice(int InvoiceId, Customer customer, double amount) {
        this.InvoiceId = InvoiceId;
        this.customer = customer;
        this.amount = amount;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount * (100 - customer.getDiscount())/100.0;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String toString(){
        return customer.toString() + "Amount : " + String.format("%.2f $",getAmount()) + "\n" + "--------------------\n";
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Vector<Invoice> v = new Vector<>();
        for(int i = 0;i < n;i++){
            sc.nextLine();
            int customerID = Integer.parseInt(sc.nextLine().substring(14));
            String name = sc.nextLine().substring(12);
            int discount = Integer.parseInt(sc.nextLine().substring(11));
            int invoiceID = Integer.parseInt(sc.nextLine().substring(13));
            String s = sc.nextLine();
            double amount = Double.parseDouble(s.substring(9,s.length() - 1));
            Customer customer = new Customer(customerID,name,discount);
            v.add(new Invoice(invoiceID,customer,amount));
        }
        Collections.sort(v, new Comparator<Invoice>() {
            @Override
            public int compare(Invoice o1, Invoice o2) {
                if(o1.getAmount() == o2.getAmount()){
                    return o1.getInvoiceId() - o2.getInvoiceId();
                }
                return Double.compare(o2.getAmount(),o1.getAmount());
            }
        });
        for(Invoice invoice : v){
            System.out.print(invoice);
        }
    }
}
