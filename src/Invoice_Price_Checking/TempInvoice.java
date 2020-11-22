package Invoice_Price_Checking;

import java.sql.Date;

public class TempInvoice {

    int id;
    String type;
    // ArrayList<Item> items = new ArrayList<>();
    // ArrayList<Payment> Payments = new ArrayList<>();

    float price = 0;
    float PayedAmount = 0;
    float CostToMax = 0;
    float ChangeFromPayment = 0;

    Date Bill_Date;
    Date Payment_Date;
    String recurring;
    int Cycles = 0;
    int Repetitions = 0;
    boolean Fully_Payed;
    String Notes;



    public  TempInvoice(int id, Date Bill_Date, Date PaymentDate, String type,String recurring ,float change)
    {
        this.id = id;
        this.Bill_Date = Bill_Date;
        this.Payment_Date = PaymentDate;
        this.type = type;
        this.recurring = recurring;
        //this.items = items;
        // this.Payments = payments;
        this.ChangeFromPayment = change;
    }



    public TempInvoice(String type, float price, String reccuring){
        this.type=type;
        this.price=price;
        this.recurring =reccuring;
    }

    public TempInvoice (TempInvoice invoice)
    {
        this.type = invoice.getType();
        this.price = invoice.getPrice();
        this.recurring = invoice.getRecurring();

    }

    public TempInvoice() {

    }


    public float getPayedAmount() {
        return PayedAmount;
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



    public int getId() {
        return id;
    }

    public Date getBill_Date() {
        return Bill_Date;
    }


    public Date getPayment_Date() {
        return Payment_Date;
    }

    public float getCostToMax() {
        return CostToMax;
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

    public void UpdateRecurringData()
    {
        this.Cycles--;
    }

    public void SetPayed(boolean payed)
    {
        this.Fully_Payed = payed;
    }

    public boolean isFully_Payed() {
        return Fully_Payed;
    }

    public void setPayment_Date(Date payment_Date) {
        Payment_Date = payment_Date;
    }
}
