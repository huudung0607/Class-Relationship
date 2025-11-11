import org.w3c.dom.css.Rect;

import javax.sound.midi.Soundbank;
import java.util.Scanner;
import java.io.*;
import java.util.*;

class MonHoc{
    private String ten;
    private int tinChi;
    private double diemSo;

    public MonHoc(String ten, int tinChi, double diemSo) {
        this.ten = ten;
        this.tinChi = tinChi;
        this.diemSo = diemSo;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public double getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(double diemSo) {
        this.diemSo = diemSo;
    }
}

class SinhVien{
    private String ma,ten,lop;
    private List<MonHoc> monHoc;

    public SinhVien(String ma, String ten, String lop, List<MonHoc> monHoc) {
        this.ma = ma;
        this.ten = ten;
        this.lop = lop;
        this.monHoc = monHoc;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public List<MonHoc> getMonHoc() {
        return monHoc;
    }
    public void setMonHoc(List<MonHoc> monHoc) {
        this.monHoc = monHoc;
    }
    public double getGPA(){
        int sum = 0;
        double res = 0.0;
        for(int i = 0;i < monHoc.size();i++){
            sum += monHoc.get(i).getTinChi();
        }
        for(int i = 0;i < monHoc.size();i++){
            res += (monHoc.get(i).getTinChi() * monHoc.get(i).getDiemSo());
        }
        return res/(1.0 * sum);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Vector<SinhVien> v = new Vector<>();
        for(int i = 0;i < n;i++){
            sc.nextLine();
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String lop = sc.nextLine();
            int q = Integer.parseInt(sc.nextLine());
            List<MonHoc> monHocs = new ArrayList<>();
            for(int j = 0;j < q;j++){
                String mon = sc.nextLine();
                String []diem = sc.nextLine().split("\\s+");
                int tinChi = Integer.parseInt(diem[0]);
                double diemThi = Double.parseDouble(diem[1]);
                MonHoc monHoc = new MonHoc(mon,tinChi,diemThi);
                monHocs.add(monHoc);
            }
            SinhVien sinhVien = new SinhVien(ma,ten,lop,monHocs);
            v.add(sinhVien);
        }
        Collections.sort(v, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                if(o1.getGPA() == o2.getGPA()){
                    return o1.getMa().compareTo(o2.getMa());
                }
                return Double.compare(o2.getGPA(),o1.getGPA());
            }
        });
        for(SinhVien x : v){
            System.out.println(x.getMa() + " " + x.getTen() + " " + x.getLop() + " " + String.format("%.2f",x.getGPA()));
            System.out.print("------------------------\n");
        }
    }
}
