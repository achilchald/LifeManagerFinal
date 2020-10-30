package Methods;

import Entities.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class Database_Deleter {

    public void Delete_Invoice(int InvoiceId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM CUSTOMER_ITEMS WHERE INVOICE_ID = "+InvoiceId);
        stmt.executeUpdate("DELETE FROM PAYMENTS WHERE INVOICE_ID = "+InvoiceId);
        stmt.executeUpdate("DELETE FROM INVOICE WHERE INVOICE_ID = "+InvoiceId);

        con.close();

    }

    public void Delete_Domain(int DomainId,int DomainInvoiceId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM DOMAINS WHERE DOMAIN_ID = " + DomainId + " ;");
        Delete_Invoice(DomainInvoiceId);


        con.close();
    }

    public void Delete_Domain(int DomainId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM DOMAINS WHERE DOMAIN_ID = " + DomainId + " ;");



        con.close();
    }


    public void Delete_Customer(Customer customer) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        for(int i = 0; i<customer.getDomainsList().size(); i++)
        {
            Delete_Domain(customer.getDomainsList().get(i).getId());
        }

        for (int i = 0; i< customer.GetInvoicesList().size(); i++)
        {
            Delete_Invoice(customer.GetInvoicesList().get(i).getId());
        }


        stmt.executeUpdate("DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = " + customer.getId() + " ;");


        con.close();
    }

}
