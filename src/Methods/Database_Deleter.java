package Methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
