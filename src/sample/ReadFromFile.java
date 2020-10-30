package sample;

import Entities.*;

import java.sql.Date;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile implements AboveGod{



    public Map<String,Customer> ReadFile(String filename)  {

            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            String[] Customers=null;
            int i=0;
            try {

                br = new BufferedReader(new FileReader(filename));
                while ((line = br.readLine()) != null) {


                    // use comma as separator
                     Customers = line.split(cvsSplitBy);
                     //Fill the global list with the customers

                    //if there are two domains Split Them
                    String [] domainz=null;
                    String[]dates=null;
                    Map<String,Date> tempMap=new HashMap<>();
                    if (Customers[1].contains(":")){
                        domainz=Customers[1].split(":");
                        dates=Customers[2].split(":");

                        for(int j=0;j<domainz.length;j++){
                            tempMap.put(domainz[j],Date.valueOf(dates[j]));
                        }
                    }
                    //if there are no more than 1 domain just add it
                    else{
                        tempMap.put(Customers[1],Date.valueOf(Customers[2]));
                    }





                    //if there are two domains there are two dates.Split them
                    //customers.add(new Customer(Customers[0],Customers[1],Integer.parseInt(Customers[2]),Integer.parseInt(Customers[3])));
//                    Customer temp=new Customer(Customers[0],(HashMap)tempMap,Float.parseFloat(Customers[3]),Customers[4]);
//                    System.out.println("Customer Test OutPut == " + temp.CustomerOutputLine());
//                    customerMap.put(temp.getId(),temp);




                    //System.out.println("Name : " + Customers[0] + " ,Domain Name : " + Customers[1] + " , Price : "+Customers[2]+" Id : "+Customers[3]);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
return customerMap;

        }

    public Map<Integer, Project> ReadFileProjects(String filename)  {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Project=null;
        int i=0;
        try {

            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {


                // use comma as separator
                Project = line.split(cvsSplitBy);
                //Fill the global list with the customers
                //customers.add(new Customer(Customers[0],Customers[1],Integer.parseInt(Customers[2]),Integer.parseInt(Customers[3])));
               // Project temp=new Project(Project[0],Date.valueOf(Project[1]),Float.parseFloat(Project[2]),Integer.parseInt(Project[3]));
                //projectMap.put(temp.getId(),temp);





                //System.out.println("Name : " + Customers[0] + " ,Domain Name : " + Customers[1] + " , Price : "+Customers[2]+" Id : "+Customers[3]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return projectMap;

    }

    public void ReadInvoiceTypes() throws IOException {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Invoice = null;

        br = new BufferedReader(new FileReader("src/Csv/InvoiceItems.csv"));
        while ((line = br.readLine()) != null) {


            Invoice = line.split(cvsSplitBy);


            Invoice temp = new Invoice(Invoice[0], Integer.valueOf(Invoice[1]),Invoice[2]);

            invoiceTypes.add(temp);


        }
    }

    public void ReadHostings() throws IOException
    {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Domains = null;

        br = new BufferedReader(new FileReader("src/Csv/Hostings.csv"));
        while ((line = br.readLine()) != null) {


            Domains = line.split(cvsSplitBy);


            Domain temp = new Domain(Domains[0],Domains[1],Integer.valueOf(Domains[2]),Float.valueOf(Domains[3]));

            DomainMap.put(temp.getName(),temp);


        }
    }

    public ArrayList<Item> ReadItemsFile(String filename){

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Items = null;
        ArrayList<Item> item = new <Item>ArrayList();

        try {

            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                Items = line.split(cvsSplitBy);
                item.add(new Item(Items[0],Float.valueOf(Items[1]),Items[2]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;


    }




}



