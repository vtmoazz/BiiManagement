/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author demo
 */
public class ListBill {
    private ArrayList<Bill> listBill ;

    public ListBill(ArrayList<Bill> listBill) {
        this.listBill = new ArrayList();
    }
    public ArrayList<Bill> getListBill() {
        return listBill;
    }
    public void setListBill(ArrayList<Bill> listBill) {
        this.listBill = listBill;
    }
    //cau a 1.5d
    public Bill getBillById(int id){
        for (Bill bill : listBill){
            if(id == bill.getId()){
                return bill;
            }
        }
        return null;// neu tra ve null thi bill cua id nay chua ton tai
    }
    public void loadObjectFromFile(String path){
        try{
            File newFile = new File(path);
            Scanner sc = new Scanner(newFile);
            while(sc.hasNextLine()== true){//boolean co du lieu thi tra ve true
                String line = sc.nextLine();
                String[] result = line.split(",");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Bill newBill = new Bill(Integer.parseInt(result[0]),result[1],Double.parseDouble(result[2]),sdf.parse(path),Boolean.parseBoolean(result[4]) );
                listBill.add(newBill);
                //localDate
            }  
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
    public void addBill(Bill newBill){
        listBill.add(newBill);
    }
    public void removeBill(int id){
        Bill findBill = getBillById(id);
        if(findBill != null){
            listBill.remove(findBill);
            System.out.println("Remove success");
        }else{
            System.out.println("Remove fail");
        }
    }
    public void displayUnpaidBill(){
        System.out.println("List unpaid Bill: ");
        for(Bill bill :listBill){
            if(bill.isIsPaid() == false){
                System.out.print(bill.toString());     
            }
        }
    }
    public double getLargestAmountBill(){
        double amountLargest = listBill.get(0).getAmount();
        for(Bill bill :listBill){
            if(bill.getAmount()>amountLargest){
                amountLargest = bill.getAmount();
            }
        }
        return amountLargest;
    }
    
    
}
