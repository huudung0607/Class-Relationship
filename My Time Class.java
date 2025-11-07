import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.io.*;

class MyTime{
    private int hour,minute,second;

    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    public void setTime(int hour,int minute,int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    public String toString(){
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }
    public MyTime nextSecond(){
        second++;
        if(second >= 60){
            second = 0;
            nextMinute();
        }
        return this;
    }
    public MyTime nextMinute(){
        minute++;
        if (minute >= 60) {
            minute = 0;
            nextHour();
        }
        return this;
    }
    public MyTime nextHour(){
        hour++;
        if(hour >= 24){
            hour = 0;
        }
        return this;
    }
    public MyTime previousSecond(){
        second--;
        if(second < 0){
            second = 59;
            previousMinute();
        }
        return this;
    }
    public MyTime previousMinute(){
        minute--;
        if(minute < 0){
            minute = 59;
            previousHour();
        }
        return this;
    }
    public MyTime previousHour() {
        hour--;
        if (hour == -1) hour = 23;
        return this;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String time = sc.nextLine();
        String []parts = time.split(":");
        int gio = Integer.parseInt(parts[0]);
        int phut = Integer.parseInt(parts[1]);
        int giay = Integer.parseInt(parts[2]);
        MyTime myTime = new MyTime(gio,phut,giay);
        System.out.println(new MyTime(gio,phut,giay).nextSecond());
        System.out.println(new MyTime(gio,phut,giay).nextMinute());
        System.out.println(new MyTime(gio,phut,giay).nextHour());
        System.out.println(new MyTime(gio,phut,giay).previousSecond());
        System.out.println(new MyTime(gio,phut,giay).previousMinute());
        System.out.println(new MyTime(gio,phut,giay).previousHour());
    }
}
