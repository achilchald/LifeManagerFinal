package Invoice_Price_Checking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Date_Checking {

    private ArrayList<TempInvoice> RecurringInvoices = new ArrayList<TempInvoice>();


    public Date_Checking()
    {

    }

    public void Load_Recurring_Invoices() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from INVOICE  WHERE INVOICE.RECCURENCE != \"ONCE\";");







        while (rs.next()) {
            int id = rs.getInt(1);
            int Customer_ID = rs.getInt(2);
            Date Bill_Date = rs.getDate(3);
            Date Payment_Date = rs.getDate(4);
            String Type = rs.getString(5);
            String Reccuring = rs.getString(6);
            float change = rs.getFloat(9);
            int Repetitions = rs.getInt(7);
            int Cycles = rs.getInt(8);
            boolean Fully_Payed = rs.getBoolean(10);

            TempInvoice invoice = new TempInvoice(id,Bill_Date,Payment_Date,Type,Reccuring,change);
            invoice.setRepetitions(Repetitions);
            invoice.setCycles(Cycles);
            invoice.SetPayed(Fully_Payed);

            RecurringInvoices.add(invoice);

        }
        con.close();



    }

    public void UpdateDatabase(TempInvoice invoice) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        System.out.println("Next Payement Date = " + invoice.getPayment_Date());
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from PAYMENTS  WHERE INVOICE_ID = " + invoice.getId() + " ;");
        stmt.executeUpdate("UPDATE INVOICE SET PAYMENT_DATE = \'" + invoice.getPayment_Date() + "\' WHERE INVOICE_ID = " + invoice.getId() + " ; " );
        stmt.executeUpdate("UPDATE INVOICE SET FULLYPAYED = 0 , CYCLES = " + invoice.getCycles() + " WHERE INVOICE_ID = " + invoice.getId() + " ; " );
        stmt.executeUpdate("UPDATE CUSTOMER_ITEMS SET ISPAYED = 0 WHERE INVOICE_ID = " + invoice.getId() + " ;");

    }


    public void Check_Dates() throws SQLException, ClassNotFoundException {

        //Get Current Date
        Date CurrentDate = Date.valueOf( LocalDate.now() );
        System.out.println("Current Date = " + CurrentDate);

        for(int i = 0 ; i <RecurringInvoices.size() ; i++)
        {
            TempInvoice invoice = RecurringInvoices.get(i);

            if ( CurrentDate.equals(invoice.getPayment_Date()) && invoice.getRecurring().equals("YEARLY") && invoice.Fully_Payed )
            {
                LocalDate temp = invoice.getPayment_Date().toLocalDate();
                System.out.println("Repetition = " + invoice.getRepetitions() + " Cycles = " + invoice.getCycles());
                temp = temp.plusYears(invoice.getRepetitions());
                System.out.println("Next Payment Date = " + temp);
                invoice.setPayment_Date(Date.valueOf(temp));
                invoice.UpdateRecurringData();
                invoice.SetPayed(false);
                UpdateDatabase(invoice);

            }

            if ( CurrentDate.equals(invoice.getPayment_Date()) && invoice.getRecurring().equals("MONTHLY") && invoice.Fully_Payed )
            {
                LocalDate temp = invoice.getPayment_Date().toLocalDate();
                temp = temp.plusMonths(invoice.getRepetitions());
                invoice.setPayment_Date(Date.valueOf(temp));
                invoice.UpdateRecurringData();
                invoice.SetPayed(false);
                UpdateDatabase(invoice);
            }



        }



    }






}
