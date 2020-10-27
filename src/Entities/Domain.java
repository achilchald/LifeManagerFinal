package Entities;

import java.sql.*;

public class Domain implements AboveGod {

    int id;
    public String Name;
    public String date;
    public Date Start_Date;
    public Date Expiry_Date;
    int Invoice_Id;
    public int type;
    public float price;

    public Domain (String name,String Date,int type,float price)
    {
        this.Name=name;
        this.date=Date;
        this.type=type;
        this.price = price;
    }

    public Domain(int id,String name,Date Start_Date,Date Expiry_Date,int Invoice_ID)
    {
        this.id = id;
        this.Name = name;
        this.Start_Date = Start_Date;
        this.Expiry_Date = Expiry_Date;
        this.Invoice_Id = Invoice_ID;
    }

    public String DisplayDomain()
    {
        String out = "";
        out = Name + " " + Start_Date + " " + Expiry_Date + " " + Invoice_Id;
        return  out;
    }

    //Getters
    public String getName() {
        return Name;
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Date getExpiry_Date() {
        return Expiry_Date;
    }

    public int getInvoice_Id() {
        return Invoice_Id;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExpiry_Date(Date expiry_Date) {
        Expiry_Date = expiry_Date;
    }

    public void setInvoice_Id(int invoice_Id) {
        Invoice_Id = invoice_Id;
    }

    public void setStart_Date(Date start_Date) {
        Start_Date = start_Date;
    }

    public int getId() {
        return id;
    }

    public Date getStart_Date() {
        return Start_Date;
    }

    public String[] DomainOutputString()
    {
        String []Output = new String[4];
            Output[0] = Name;
            Output[1] = date;
            Output[2] = Integer.toString(type);
            Output[3] = Float.toString(price);



        return  Output;
    }


}
