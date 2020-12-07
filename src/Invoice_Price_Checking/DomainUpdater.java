package Invoice_Price_Checking;

import java.sql.*;

public class DomainUpdater {


    public void UpdateDomains() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select dom.EXPIRY_DATE,inv.PAYMENT_DATE,dom.DOMAIN_ID\n" +
                "from DOMAINS dom \n" +
                "inner join INVOICE inv on dom.INVOICE_ID = inv.INVOICE_ID;");


        while (rs.next()) {
            Date ExpiryDate = rs.getDate(1);
            Date PayementDate = rs.getDate(2);
            int id = rs.getInt(3);

            if(!ExpiryDate.equals(PayementDate))
            {
                Statement st2 = con.createStatement();
                st2.executeUpdate("UPDATE DOMAINS SET EXPIRY_DATE = \'"+PayementDate+"\' WHERE DOMAIN_ID = "+id+ " ;");
            }

        }
        con.close();
    }




}
