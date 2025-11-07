import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;

class Author{
    private String name,email;
    private char gender;

    public Author(String name, String email, char gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
class Book{
    private String name;
    private double price;
    private int qty;
    private Author author;

    public Book(String name, double price, int qty, Author author) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public void print(){
        System.out.print("Book Details : \n");
        System.out.println(name);
        System.out.printf("%.0f\n",price);
        System.out.println(qty);
        System.out.print("Author Information : \n");
        System.out.println(author.getName());
        System.out.println(author.getEmail());
        System.out.println(author.getGender());
        System.out.println("--------------------");
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Vector<Book> v = new Vector<>();
        for(int i = 0;i < n;i++){
            String title = sc.nextLine();
            double price = Double.parseDouble(sc.nextLine());
            int qty = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            String email = sc.nextLine();
            char gender = sc.next().charAt(0);
            sc.nextLine();
            Author author = new Author(name,email,gender);
            v.add(new Book(title,price,qty,author));
        }
        Collections.sort(v, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getPrice() != o2.getPrice()){
                    return (int)(o2.getPrice() - o1.getPrice());
                }
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(var i : v){
            i.print();
        }
    }
}
