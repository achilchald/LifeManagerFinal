package Entities;


import java.sql.Date;

public class Item {

    String id;
    String type;
    String recurring;
    float price;
    float Discount;
    Date PayedDate;
    boolean isPayed = false;

    public Item(String id,String ItemType,float price,float discount)
    {
        this.id = id;
        this.type = ItemType;
        this.price = price;
        this.Discount = discount;
    }
    public Item(Item item)
    {
        this.id=item.getId();
        this.type = item.getType();
        this.recurring = item.getRecurring();
        this.price = item.getPrice();
        this.Discount = item.Discount;
        this.PayedDate = item.getPayedDate();
        this.isPayed = item.isPayed();

    }

    public Item (String type, Float price, String recurring){

        setType(type);
        setRecurring(recurring);
        setPrice(price);

    }

    public Item (String id,String type, String recurring, Float price){

        setId(id);
        setType(type);
        setRecurring(recurring);
        setPrice(price);

    }

    public Item() {

    }



    public void SetDiscount(float discount)
    {
        this.Discount = discount;
        this.price -= discount;

    }

    public void CalculateReccuringPrice(String Reccurence)
    {
        if(Reccurence.equals("WEEKLY"))
        {
            if(recurring.equals("WEEKLY"))
            {
                this.price = this.price;
            }
            else if(recurring.equals("MONTHLY"))
            {
                this.price = this.price/4;
            }
            else if(recurring.equals("YEARLY"))
            {
                this.price = this.price/52;
            }

        }
        else if(Reccurence.equals("MONTHLY"))
        {
            if(recurring.equals("WEEKLY"))
            {
                this.price = this.price*4;
            }
            else if(recurring.equals("MONTHLY"))
            {
                this.price = this.price;
            }
            else if(recurring.equals("YEARLY"))
            {
                this.price = this.price/12;
            }
        }
        else if(Reccurence.equals("YEARLY"))
        {
            if(recurring.equals("WEEKLY"))
            {
                this.price = this.price*52;
            }
            else if(recurring.equals("MONTHLY"))
            {
                this.price = this.price*12;
            }
            else if(recurring.equals("YEARLY"))
            {
                this.price = this.price;
            }
        }
    }





    public Date getPayedDate() {
        return PayedDate;
    }

    public void setPayedDate(Date payedDate) {
        PayedDate = payedDate;
    }

    public boolean isPayed()
    {
        return isPayed;
    }
    public void SetPayed(Boolean isPayed)
    {
        this.isPayed = isPayed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecurring(String recurring) {
        this.recurring = recurring;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getRecurring() {
        return recurring;
    }

    public Float getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public float getDiscount() {
        return Discount;
    }
}
