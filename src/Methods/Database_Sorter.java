package Methods;

import Entities.Customer;
import Entities.Project;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Database_Sorter {


    public List GetSortedCustomers(String ColumnName , String sortType) throws ClassNotFoundException, SQLException {


        List<String> SortedCustomers = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();


        //Delete the workers Tasks
        ResultSet rs = stmt.executeQuery("SELECT CUSTOMER_ID FROM CUSTOMERS ORDER BY " + ColumnName + " " + sortType);
        while (rs.next()) {
            String id = rs.getString(1);
            SortedCustomers.add(id);
        }
        con.close();
        return SortedCustomers;

    }

    public List GetSortedProjects (String ColumnName , String sortType) throws ClassNotFoundException, SQLException
    {
        List<Integer> SortedProjects = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from PROJECTS ORDER BY " + ColumnName + " " + sortType);
        while (rs.next()) {

            int id = rs.getInt(1);
            SortedProjects.add(id);
            System.out.println("Project id = " + id);

        }
        con.close();

        return SortedProjects;
    }


    public List GetSortedItems (String ColumnName , String sortType) throws ClassNotFoundException, SQLException
    {
        List<String> SortedItems = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
//here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from ITEMS ORDER BY " + ColumnName + " " + sortType);
        while (rs.next()) {

            String id = rs.getString(1);
            SortedItems.add(id);
            System.out.println("Item id = " + id);

        }
        con.close();

        return SortedItems;
    }






}
