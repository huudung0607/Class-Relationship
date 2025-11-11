import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.Scanner;
import java.io.*;
import java.util.*;
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
    public String toString(){
        return "Name : " + name + "\nEmail : " + email + "\nGender : " + gender + "\n";
    }
}

class Book{
    private String bookName;
    private List<Author> authors;
    private double price;
    private int qty;

    public Book(String bookName, List<Author> authors, double price, int qty) {
        this.bookName = bookName;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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

    @Override
    public String toString() {
        String s ="Book information : \n" + "Name : " + bookName + "\nPrice : " + String.format("%.0f",price) +
                "\nQuantity : " + qty + "\n" +
                "Author information : \n";
        for(int i = 0;i < authors.size();i++){
            s += "#" + (i + 1) +"\n";
            s += authors.get(i).toString();
        }
        return s;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Vector<Book> v = new Vector<>();
        for(int i = 0;i < n;i++){
            sc.nextLine();
            String title = sc.nextLine();
            double price = Double.parseDouble(sc.nextLine());
            int qty = Integer.parseInt(sc.nextLine());
            int m = Integer.parseInt(sc.nextLine());
            List<Author> authors = new ArrayList<>();
            for(int j = 0;j < m;j++){
                Author author = new Author(sc.nextLine(),sc.nextLine(),sc.next().charAt(0));
                sc.nextLine();
                authors.add(author);
            }
            Book book = new Book(title,authors,price,qty);
            v.add(book);
        }
        Collections.sort(v, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookName().compareTo(o2.getBookName());
            }
        });

        for(Book x : v){
            System.out.print("-----------------------\n");
            System.out.print(x);
        }
    }
}
