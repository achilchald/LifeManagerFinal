package Entities;

import java.util.ArrayList;
import java.sql.*;
public class Invoice {
    int id;
    String type;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Payment> Payments = new ArrayList<>();

    float price = 0;
    float PayedAmount = 0;
    float CostToMax = 0;
    float ChangeFromPayment = 0;

    Date Bill_Date;
    Date Payment_Date;
    String recurring;
    int Cycles = 0;
    int Repetitions = 0;
    String Notes;

    boolean isArchived = false;


    public  Invoice(int id, Date Bill_Date, Date PaymentDate, String type,String recurring, ArrayList<Item> items, ArrayList<Payment> payments ,float change)
    {
        this.id = id;
        this.Bill_Date = Bill_Date;
        this.Payment_Date = PaymentDate;
        this.type = type;
        this.recurring = recurring;
        this.items = items;
        this.Payments = payments;
        this.ChangeFromPayment = change;
    }



    public Invoice(String type, float price, String reccuring){
    this.type=type;
    this.price=price;
    this.recurring =reccuring;
    }

    public Invoice (Invoice invoice)
    {
        this.type = invoice.getType();
        this.price = invoice.getPrice();
        this.recurring = invoice.getRecurring();

    }

    public Invoice() {

    }




    public void Calc_Invoice_Price()
    {
        this.price = 0;
        for(int i = 0;i<items.size();i++)
        {
            this.price = this.price + items.get(i).getPrice();
        }


    }

    public float getPayedAmount() {
        return PayedAmount;
    }

    public void Calc_Payed_Amount()
    {
        for(int i = 0;i<Payments.size();i++)
        {
            this.PayedAmount = this.PayedAmount + Payments.get(i).getPrice();
        }
    }


    public void NewPrice(float price)
    {
        this.price = this.price + price;
    }

    public void RemovePrice(float price){this.price = this.price - price;}

    public void NewPayedAmount(float PayedAmount)
    {
        this.PayedAmount = this.PayedAmount + PayedAmount;
    }



    public void setChangeFromPayment(float changeFromPayment) {
        ChangeFromPayment = changeFromPayment;
    }

    public float getPrice() {
        return price;
    }


    public String getRecurring() {
        return recurring;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public Date getBill_Date() {
        return Bill_Date;
    }

    public ArrayList<Payment> getPayments() {
        return Payments;
    }

    public Date getPayment_Date() {
        return Payment_Date;
    }


    public float getChangeFromPayment() {
        return ChangeFromPayment;
    }

    public int getCycles() {
        return Cycles;
    }

    public int getRepetitions() {
        return Repetitions;
    }



    public String getNotes() {
        return Notes;
    }

    public void setCycles(int cycles) {
        Cycles = cycles;
    }

    public void setRepetitions(int repetitions) {
        Repetitions = repetitions;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public void updatePayedAmount(float price)
    {
        this.PayedAmount -= price;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public boolean isArchived() {
        return isArchived;
    }
}
