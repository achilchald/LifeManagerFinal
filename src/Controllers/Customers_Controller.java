package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Entities.Item;
import Entities.Linker;
import Methods.Database_Sorter;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

/*
This Controllers class is responsible for the Customers GUI Interface
In it there are methods for the GUI's initialization as well as calls to other controllers
responsible for some GUI Elements Such as the Edit_Controller who is responsible for handling the editing
of the Customers Elements

Another Controller that is called from here Is the customer add Controller which is responsible for
adding a customer to the application and showing him in this controller
 */

public class Customers_Controller implements AboveGod , Initializable  {

    @FXML
    private Pane pnlCustomers;

    @FXML
    private Pane EditArea;


    @FXML
    private Label ActiveCustomersPanel;

    @FXML
    private VBox pnItems;

    @FXML
    private Button Add;

    @FXML
    private Label IncomeLabel;

    @FXML
    private Label HostingLabel;

    @FXML
    private Label CustomerName;

    @FXML
    private Label CustomerPrice;

    public int counter = 0;

    private HBox itemC;

    private String SortingType ;

    private int SortingFlag = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Create a database reader to load the Customer Data from the sql database
        Read_Database reader = new Read_Database();

        IncomeLabel.setText("0");

        //Create a linker so as to handle the income label from the various methods that change its pricing
        Linker Link = new Linker();
        Link.CreateLink(this.IncomeLabel);
        try {
            reader.Load_Customers();
            reader.Load_Items();
            reader.Load_Domains();
            reader.Load_Invoices();

            for(Map.Entry<String,Customer> entry : customerMap.entrySet())
            {
                entry.getValue().GetCustomerData();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        //In this loop the Customers are loaded in the GUI and controllers are added to their GUI Elements
        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){

            entry.getValue().calculatePrice();
            try {

                //The main customer data are stored in a HBox component
                HBox box;

                //Load the fxml template for the customer data
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));

                box = loader.load();

                //Get the Gui element Controller
                Edit_Controller control = loader.getController();


                //todo will be removed
               // control.SetEditArea(EditArea);
               // control.SetIncomeLabel(IncomeLabel);



                ((Label)box.getChildren().get(1)).setText(entry.getValue().getName());


                //Load the domains to the customer Combobox Component
                for(int j = 0;j<entry.getValue().DomainsList.size();j++)
                {
                    ((ComboBox) box.getChildren().get(2)).getItems().add(entry.getValue().DomainsList.get(j).Name);
                }

                //Load the customer price to the Hbox's label
                ((Label)box.getChildren().get(3)).setText( String.valueOf(entry.getValue().getPrice()));

                //A hidden box with a customer id
                ((Label)box.getChildren().get(4)).setText(entry.getValue().getId());

                //Set to the Editor Controller the price label id
                //this is done so as to update the label when items are added or removed from an  invoice
                control.SetPriceLabelId();

                //Set the box id so as to be able to remove the item if need be
                box.setId(entry.getValue().getId());

                //Add the customer Hbox to the Vbox containing all the customer boxes
                pnItems.getChildren().add(box);


                //Fill the Labels containing global data
                ActiveCustomersPanel.setText(String.valueOf(customerMap.size()));

                IncomeLabel.setText((calcTotalPrice())+"$");

                HostingLabel.setText(String.valueOf(numberHosting()));


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Calculate the total price of all the customers and add it to the Total income label
    public int calcTotalPrice(){
        int total=0;
        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){
            total+=entry.getValue().getPrice();
        }
        return total;
    }

    //Calculate the number of all hostings
    public int numberHosting(){
        int count=0;

        for (Map.Entry<String,Customer>entry:customerMap.entrySet()){
        count+=entry.getValue().DomainsList.size();
            }

        return count;
    }


    //This method is responsible for adding a new Customer to the application
    public void Add_Customer(ActionEvent event) throws IOException {
        //If the add button is hit then show the gui for adding a customer
        if (event.getSource() == Add)
        {

            //Load the Gui elements
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add_customer.fxml"));

            Parent root = loader.load();

            //Get the add customer controller
            cust_add_Controller ctrl = loader.getController();

            //Set to the controller the Vbox containing the customers and the small area to appear
            ctrl.SetCustomerVbox(pnItems);
            ctrl.SetEditArea(EditArea);

            setCounter(counter+1);

            EditArea.getChildren().setAll(root);

        }

    }


    @FXML
    public void SortCustomers(MouseEvent event) throws SQLException, ClassNotFoundException, InterruptedException {
        String ColumnName = null;

        if (event.getSource() == CustomerName)
        {
            ColumnName = "NAME";
        }
        else if(event.getSource() == CustomerPrice )
        {
            ColumnName = "Price";
        }

        Database_Sorter sorter = new Database_Sorter();

        pnItems.getChildren().clear();
        if(SortingFlag == 0)
        {
            SortingType = "DESC";
            SortingFlag = 1;
        }
        else if(SortingFlag == 1)
        {
            SortingType = "ASC";
            SortingFlag = 0;
        }


        List<String>SortedCustomers = sorter.GetSortedCustomers(ColumnName,SortingType);

        for (int i = 0; i<SortedCustomers.size();i++) {

            //entry.getValue().calculatePrice();
            try {
                //The main customer data are stored in a HBox component
                HBox box;

                String id = SortedCustomers.get(i);
                //Load the fxml template for the customer data
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));

                box = loader.load();

                //Get the Gui element Controller
                Edit_Controller control = loader.getController();


                ((Label) box.getChildren().get(1)).setText(customerMap.get(id).getName());


                //Load the domains to the customer Combobox Component
                for (int j = 0; j < customerMap.get(id).DomainsList.size(); j++) {
                   ((ComboBox) box.getChildren().get(2)).getItems().add(customerMap.get(id).DomainsList.get(j).Name);
               }

                //Load the customer price to the Hbox's label
                ((Label) box.getChildren().get(3)).setText(String.valueOf(customerMap.get(id).getPrice()));

                //A hidden box with a customer id
                ((Label) box.getChildren().get(4)).setText(customerMap.get(id).getId());

                //Set to the Editor Controller the price label id
                //this is done so as to update the label when items are added or removed from an  invoice
                control.SetPriceLabelId();

                //Set the box id so as to be able to remove the item if need be
                box.setId(customerMap.get(id).getId());

                //Add the customer Hbox to the Vbox containing all the customer boxes
                pnItems.getChildren().add(box);


                //Fill the Labels containing global data
                ActiveCustomersPanel.setText(String.valueOf(customerMap.size()));



            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
