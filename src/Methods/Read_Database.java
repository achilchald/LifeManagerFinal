package Methods;

import Entities.*;

import java.sql.*;
import java.util.ArrayList;

public class Read_Database extends Globals implements AboveGod {

    public void Load_Customers() throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CUSTOMERS ");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String Country = rs.getString(3);
                String City = rs.getString(4);
                String Address = rs.getString(5);
                String Zip = rs.getString(6);
                String Phone = rs.getString(7);
                String Email = rs.getString(8);
                String AFM = rs.getString(9);
                Customer customer = new Customer(id, name, Country, City, Address, Zip, Phone, Email, AFM);
                customerMap.put(id, customer);
                LastCustomerId = Integer.parseInt(id);

                //customerMap.put(id,customer);


            }
            con.close();

        } catch (Exception e){ System.out.println(e);}
    }

    public void Load_Items() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from ITEMS");
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String Reccuring = rs.getString(3);
            float price = rs.getFloat(4);

            Item item = new Item(id,name,Reccuring,price);
            ItemsMap.put(id,item);



        }
        con.close();
    }

    public void Load_Domains() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from DOMAINS");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String Customer_Id = rs.getString(3);
            int Invoice_ID = rs.getInt(4);
            Date Start_Date = rs.getDate(5);
            Date Expiry_Date = rs.getDate(6);
            Domain domain = new Domain(id,name,Start_Date,Expiry_Date,Invoice_ID);
            customerMap.get(Customer_Id).DomainsList.add(domain);
            LastDomainId = id;



        }
        con.close();
    }

    public void Load_Invoices() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from INVOICE");
        while (rs.next()) {
            int id = rs.getInt(1);
            int Customer_ID = rs.getInt(2);
            Date Bill_Date = rs.getDate(3);
            Date Payment_Date = rs.getDate(4);
            String Type = rs.getString(5);
            String Reccuring = rs.getString(6);
            float change = rs.getFloat(9);
            LastInvoiceId = id;



            ArrayList<Item> items = new ArrayList<>();
            ArrayList<Payment> payments = new ArrayList<>();

            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery("select  * from CUSTOMER_ITEMS\n" +
                    "where INVOICE_ID = "+ id +  " ;");

            while(rs2.next())
            {
                String item_id = Integer.toString(rs2.getInt(2));
                String ItemType = rs2.getString(3);
                float price = rs2.getFloat(4);
                float discount = rs2.getFloat(5);
                Boolean isPayed = rs2.getBoolean(7);
                int quantity = rs2.getInt(6);
                Date paymentDate = rs2.getDate(8);
                boolean isRecurring = rs2.getBoolean(9);
                String Recurrence = rs2.getString(10);

                Item temp = new Item(item_id,ItemType,price,discount,quantity,isRecurring);
                temp.SetPayed(isPayed);
                temp.setPayedDate(paymentDate);
                temp.setRecurring(Recurrence);
                items.add(temp);

                LastItemId = Integer.parseInt(item_id);
            }

            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt2.executeQuery("select  * \n" +
                    "from PAYMENTS pay\n"  +
                    "where (pay.INVOICE_ID = "+ id +  ")");
            while (rs3.next())
            {
                int PaymentId = rs3.getInt(1);
                int InvoiceId = id;
                Date PaymentDate = rs3.getDate(3);
                float Price = rs3.getFloat(4);
                String Notes = rs3.getString(5);
                LastPaymentId = PaymentId;


                Payment payment = new Payment(PaymentId,InvoiceId,PaymentDate,Price,Notes);
                payments.add(payment);
            }




            Invoice invoice = new Invoice(id,Bill_Date,Payment_Date,Type,Reccuring,items,payments,change);

            invoice.Calc_Invoice_Price();
            invoice.Calc_Payed_Amount();


            customerMap.get(Integer.toString(Customer_ID)).GetInvoicesList().add(invoice);





        }
        con.close();


    }

    public void UpdateCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password

        String NewName = customerMap.get(id).getName();
        String NewCountry = customerMap.get(id).getCountry();
        String NewCity = customerMap.get(id).getCity();
        String NewAddress = customerMap.get(id).getAddress();
        String NewZip = customerMap.get(id).getZip();
        String NewPhone = customerMap.get(id).getPhone();
        String NewEmail = customerMap.get(id).getEmail();
        String NewAFM = customerMap.get(id).getAFM();


        Statement stmt = con.createStatement();
         stmt.executeUpdate("UPDATE CUSTOMERS"
                 + " SET NAME = \"" + NewName
                 + "\",COUNTRY = \"" + NewCountry
                 + "\",CITY = \"" + NewCity
                 + "\",ADDRESS = \"" + NewAddress
                 + "\",ZIP = \""     +NewZip
                 + "\",PHONE = \"" + NewPhone
                 + "\",EMAIL = \"" + NewEmail
                 + "\",AFM = \""   + NewAFM
        + "\" WHERE CUSTOMER_ID = " + id + ";");

        con.close();
    }

    public void UpdateDomain(Domain UpdatedDomain , Invoice UpdatedInvoice) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        int id = UpdatedDomain.getId();
        String name = UpdatedDomain.getName();
        Date StartDate = UpdatedDomain.getStart_Date();
        Date ExpiryDate = UpdatedDomain.getExpiry_Date();

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE DOMAINS"
                + " SET DOMAIN_NAME = \"" + name
                + "\",START_DATE = \"" + StartDate
                + "\",EXPIRY_DATE = \"" + ExpiryDate
                + "\" WHERE DOMAIN_ID = " + id + ";");



            stmt.executeUpdate("DELETE FROM CUSTOMER_ITEMS"
                    +" WHERE INVOICE_ID = "+UpdatedInvoice.getId() +";");


        for (int i = 0; i<UpdatedInvoice.getItems().size();i++)
        {
            stmt.executeUpdate("INSERT INTO CUSTOMER_ITEMS VALUES("
                    +UpdatedInvoice.getId() +","+UpdatedInvoice.getItems().get(i).getId()+", \""+UpdatedInvoice.getItems().get(i).getType() + "\" , "+UpdatedInvoice.getItems().get(i).getPrice()
                    +",0"+ ","+ "0"+  ","+" null "+");");
        }


        con.close();
    }

    public void UpdateInvoice(int InvoiceID ,Item item) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO CUSTOMER_ITEMS VALUES("
                +InvoiceID +","+item.getId()+ " , \""+item.getType()+"\" , " + item.getPrice()+    ","+item.getDiscount()+ ","+ "1"+  ","+ "0"+"," +" null "+ ","+item.isRecurring() + " , \""+item.getRecurring()+"\"  " +");");
        con.close();
    }

    public void AddDomain(String CustomerId,Domain NewDomain) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO DOMAINS VALUES ( "+
                NewDomain.getId()+" , \"" + NewDomain.getName() + "\" , "+
                CustomerId + " , " +NewDomain.getInvoice_Id() + " , \'" +
                NewDomain.getStart_Date() + "\' , \'" + NewDomain.getExpiry_Date() + "\' );");
        con.close();
    }

    public void AddInvoice(String customerid,Invoice invoice) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO INVOICE VALUES("+
        invoice.getId()+" , "+customerid + " , \'" + invoice.getBill_Date() + "\' , \'" +
                invoice.getPayment_Date() + "\' , \"" + invoice.getType() + "\" , \"" + invoice.getRecurring() +
                "\"  , "  + invoice.getRepetitions() + " , " + invoice.getCycles() + " , " + invoice.getChangeFromPayment() + " , 0 " + " ) ;" );
        con.close();
    }

    public void RemoveItem(int InvoiceID , String ItemId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM CUSTOMER_ITEMS"
                +" WHERE INVOICE_ID = "+InvoiceID +" AND ITEM_ID = "+ItemId+";");

        con.close();
    }

    public void PayItem(String ItemId,int InvoiceID,Date PaymentDate) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE CUSTOMER_ITEMS"+
                " SET ISPAYED = 1 "+ ", "+ "PAYMENT_DATE = "+
                "\'"+PaymentDate + "\'"
                +" WHERE INVOICE_ID = "+InvoiceID +" AND ITEM_ID = "+ItemId+";");

        con.close();


    }

    public void SetChange(Invoice invoice) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE INVOICE SET CHANGE_FROM_PAYMENT = "+ invoice.getChangeFromPayment() + " WHERE INVOICE_ID = "+invoice.getId());
        con.close();
    }

    public void AddPayment(Payment payment) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO PAYMENTS VALUES ( "+payment.getPaymentId() + " , " + payment.getInvoiceId() + " , \'" + payment.getPaymentDate().toString() + "\' , " + payment.getPrice() + " , \" "
        + payment.getNotes() + "\" );");
        con.close();
    }

    public void AddCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        String id = customer.getId();
        String NewName = customer.getName();
        String NewCountry = customer.getCountry();
        String NewCity = customer.getCity();
        String NewAddress = customer.getAddress();
        String NewZip = customer.getZip();
        String NewPhone = customer.getPhone();
        String NewEmail = customer.getEmail();
        String NewAFM = customer.getAFM();
        String Group ="";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO CUSTOMERS VALUES ("
                +id
                + " ,\"" + NewName
                + "\",\"" + NewCountry
                + "\",\"" + NewCity
                + "\",\"" + NewAddress
                + "\",\"" + NewZip
                + "\",\"" + NewPhone
                + "\",\"" + NewEmail
                + "\",\"" + NewAFM
                +"\",\"" + Group
                + "\");");

        con.close();
    }

    public void Load_Category() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Category");
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);

            categoryMap.put(Integer.valueOf(id),name);




        }
        con.close();
    }

    public void LoadProjects() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from PROJECTS ");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date deadline = rs.getDate(3);
            float price = rs.getFloat(4);
            int workforce=rs.getInt(5);
            Project project = new Project(id,name,deadline,price,workforce);
            projectMap.put(id,project);




        }
        con.close();

    }

    public void LoadWorkers() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from WORKERS ");

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            Worker worker=new Worker(id,name,email);
            workerMap.put(id,worker);






        }
        con.close();

    }

    public void LoadProjectWorkers() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("\n" +
                "SELECT PROJ.PROJECT_ID , WOR.WORKER_ID \n" +
                "FROM PROJECTS PROJ\n" +
                "INNER JOIN PROJ_WORKER_LINK LINK ON PROJ.PROJECT_ID = LINK.PROJECT_ID\n" +
                "INNER JOIN WORKERS WOR ON LINK.WORKER_ID = WOR.WORKER_ID");
        while (rs.next()) {
            int project_id = rs.getInt(1);
            int worker_id=rs.getInt(2);

            projectMap.get(project_id).addWorker(worker_id);
            //Here we Link the worker INSIDE the project and init an empty ArraList with his todolist
            projectMap.get(project_id).getWorkers().get(worker_id).getTasks().put(project_id,new ArrayList<Task>());




        }
        con.close();
    }

    public void LoadWorkerTasks() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM TASKS;");
        while (rs.next()) {
            int task_id = rs.getInt(1);
            String taskname=rs.getString(2);
            String taskDesc=rs.getString(3);
            Date deadline=rs.getDate(4);
            Boolean status=rs.getBoolean(5);
            int project_id=rs.getInt(6);
            int worker_id=rs.getInt(7);

            Task task=new Task(task_id,taskname,taskDesc,deadline,status,project_id,worker_id);




            //
            //adds every task to its corresponding worker
            lastTaskId=task_id;
            workerMap.get(worker_id).addTasks(task);

            //projectMap.get(project_id).getWorkers().get(worker_id).addTasks(task);






        }
        con.close();
    }

    public void UpdateProject(Integer id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        String NewName = projectMap.get(id).getName();
        Date NewDate = projectMap.get(id).getDueDate();
        int NewWorkforce = projectMap.get(id).getWorkforce();
        float NewPrice = projectMap.get(id).getPrice();


        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE PROJECTS"
                + " SET PROJECT_NAME = \"" + NewName
                + "\",DUE_DATE = \"" + NewDate
                + "\",PRICE = \"" + NewPrice
                + "\",WORKFORCE = \"" + NewWorkforce
                + "\" WHERE PROJECT_ID = " + id + ";");

        con.close();


    }


    public void setInvoiceAsPayed(int InvoiceId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE INVOICE SET FULLYPAYED = 1 WHERE INVOICE_ID = " + InvoiceId);

        con.close();
    }


}
