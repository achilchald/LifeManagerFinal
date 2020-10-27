package Entities;

import java.sql.Date;

public class Payment {
    private int PaymentId;
    private int InvoiceId;
    private Date PaymentDate;
    private float Price;
    private String Notes;








public Payment(int Paymentid,int Invoiceid,Date paymentDate,float price,String notes)
{
    this.PaymentId = Paymentid;
    this.InvoiceId = Invoiceid;
    this.PaymentDate= paymentDate;
    this.Price = price;
    this.Notes = notes;
}

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
