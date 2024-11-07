/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view_controller;
import model.Bill;
import model.ListBill;
import view_controller.Menu;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillManagement extends Menu {
    private ListBill billList;

    public BillManagement() {
        super("Electricity Bills Management", new String[]{"Load data","Add new bill","Delete a bill","Largest amount","Unpaid list","Quit"});
        billList = new ListBill(new ArrayList<>());
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                loadData();
                break;
            case 2:
                addBill();
                break;
            case 3:
                deleteBill();
                break;
            case 4:
                displayLargestAmount();
                break;
            case 5:
                displayUnpaidBills();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void loadData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String path = sc.nextLine();
        billList.loadObjectFromFile(path);
        System.out.println("Data loaded successfully.");
    }

    private void addBill() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bill ID: ");
        int id = sc.nextInt();
        sc.nextLine();  
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        System.out.print("Enter due date (dd/MM/yyyy): ");
        sc.nextLine(); 
        String dueDateStr = sc.nextLine();
        System.out.print("Is the bill paid (true/false): ");
        boolean isPaid = sc.nextBoolean();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dueDate = sdf.parse(dueDateStr);
            Bill newBill = new Bill(id, customerName, amount, dueDate, isPaid);
            billList.addBill(newBill);
            System.out.println("Bill added successfully.");
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    private void deleteBill() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete choice");
        System.out.println("------------------");
        System.out.println("1. Delete by ID");
        System.out.println("2. Delete by customer name");
        System.out.println("3. Delete by due date");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                deleteById();
                break;
            case 2:
                deleteByCustomerName();
                break;
            case 3:
                deleteByDueDate();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    // Delete by bill ID
    private void deleteById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bill ID to delete: ");
        int id = sc.nextInt();
        billList.removeBill(id);
    }

    // Delete by customer name
    private void deleteByCustomerName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer name to delete: ");
        sc.nextLine(); // Consume newline
        String customerName = sc.nextLine();
        boolean found = false;

        for (Bill bill : billList.getListBill()) {
            if (bill.getCustomerName().equalsIgnoreCase(customerName)) {
                billList.removeBill(bill.getId());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Bill not found for customer: " + customerName);
        }
    }

    // Delete by due date
    private void deleteByDueDate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter due date to delete bills (dd/MM/yyyy): ");
        String dueDateStr = sc.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dueDate = sdf.parse(dueDateStr);
            boolean found = false;

            for (Bill bill : billList.getListBill()) {
                if (bill.getDueDate().equals(dueDate)) {
                    billList.removeBill(bill.getId());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No bills found with the due date: " + dueDateStr);
            }

        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    // Display the largest bill amount
    private void displayLargestAmount() {
        double largestAmount = billList.getLargestAmountBill();
        System.out.println("The largest bill amount is: " + largestAmount);
    }

    // Display unpaid bills
    private void displayUnpaidBills() {
        billList.displayUnpaidBill();
    }

    private void exit() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }
    public static void main(String[] args) {
        BillManagement billManagement = new BillManagement();
        billManagement.run(); 
    }
    
    
}
