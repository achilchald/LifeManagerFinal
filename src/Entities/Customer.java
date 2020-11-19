package Entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Customer implements AboveGod{
    String id;

    String name;

    String Country;

    String City;

    String Address;

    String Zip;

    String Phone;

    String Email;

    String AFM;

    float price;

    String domain;

    String invoices;


    //Domain,Expiry Date Key Pairs
   public Map<String, Date> Domains=new HashMap<>();

   public ArrayList<Domain> DomainsList = new ArrayList<>();

    ArrayList<Invoice> invoicesList=new ArrayList<>();

    public Customer(String id,String name,String Country,String City,String Address,String Zip,String Phone,String Email,String AFM)
    {
        this.id = id;
        this.name = name;
        this.Country = Country;
        this.City = City;
        this.Address = Address;
        this.Zip = Zip;
        this.Phone = Phone;
        this.Email = Email;
        this.AFM =AFM;
    }

    public Customer() {

    }

    public ArrayList<Invoice> GetInvoicesList()
    {
        return invoicesList;
    }


    public String[] CustomerOutputLine()
    {
        String DomainsOut ="";
        String DatesOut ="";
        String[] OutputArray = new String[5];

        String InvoicesCode = "";
        OutputArray[0] = name ;
        OutputArray[4] = "";

        int  [] InvoiceTypes = new int[invoiceTypes.size()];
            for(int i = 0;i<InvoiceTypes.length;i++)
            {
                InvoiceTypes[i] = 0;
            }

        //Get the invoices of the customer

        for (int i = 0;i<invoiceTypes.size();i++)
        {
            for(int j = 0;j<invoicesList.size();j++)
            {
                if(invoiceTypes.get(i).getType().equals(invoicesList.get(j).getType()))
                {
                    InvoiceTypes[i] += 1;
                }
            }

            InvoicesCode += InvoiceTypes[i];
        }


        int j = 0;
        for (Map.Entry<String,Date> entry : Domains.entrySet())
        {
            if(j == Domains.size() - 1)
            {
                DomainsOut = DomainsOut + entry.getKey() ;
                DatesOut = DatesOut + entry.getValue() ;
            }
            else
            {
                DomainsOut = DomainsOut + entry.getKey() + ":";
                DatesOut = DatesOut + entry.getValue() + ":";
                j++;
            }

        }



        OutputArray[1] = DomainsOut ;
        OutputArray[2] = DatesOut ;
        OutputArray[3] = Float.toString(price);
        OutputArray[4] = OutputArray[4] + InvoicesCode;




        return OutputArray;
    }

    public void GetCustomerData()
    {}




    public Customer(String name,HashMap domains){
        setName(name);
        setDomains(domains);
        setId();

    }





    public Customer(Customer copy){
        setName(copy.getName());
        setDomain(copy.getDomain());
        setPrice(copy.getPrice());
        this.Domains = copy.Domains;
    }




    public void setDomains(Map<String, Date> domains) {
        Domains = domains;
    }

    public Map<String, Date> getDomains() {
        return Domains;
    }

    //
    public void setInvoices(String invoices) {
        this.invoices = invoices;
    }



    public void setInvoicesList() {


        String temp = this.invoices;
        int[] localList = new int[invoiceTypes.size()];
        for (int i = 0; i < temp.length(); i++) {
            localList[i] = temp.charAt(i) - '0';
        }



        for (int k = 0; k <invoiceTypes.size(); k++) {
            if (localList[k] > 0)
                for(int j = 0;j<localList[k];j++)
                {

                        this.invoicesList.add(new Invoice(invoiceTypes.get(k).getType(), invoiceTypes.get(k).getPrice(), invoiceTypes.get(k).getRecurring()));
                    }

                }



        }



    
    public void calculatePrice(){
        price = 0;
       for (int i=0;i<invoicesList.size();i++){


               this.price += invoicesList.get(i).getPrice();
           }
       }




    //Getters
    public String getId() {
        return id;
    }

    public void setId() {
        this.id =String.valueOf(ThreadLocalRandom.current().nextInt(0, 10000000 + 1)) ;
    }

    public float getPrice() {
        return price;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAddress() {
        return Address;
    }

    public String getAFM() {
        return AFM;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getZip() {
        return Zip;
    }

    public ArrayList<Domain> getDomainsList() {
        return DomainsList;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setAFM(String AFM) {
        this.AFM = AFM;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setZip(String zip) {
        Zip = zip;
    }


}
