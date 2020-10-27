package Controllers.DomainController;
import Entities.*;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;



/*
This controller is responsible for accepting user input from the add domain GUI
and applying the changes to the customer
 */
public class AddDomainController extends Globals implements Initializable, AboveGod {

    @FXML
    private Pane DomainPane;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private TextField DomName;

    @FXML
    private ComboBox<String> HostingType;

    @FXML
    private ComboBox<String> DomainType;

    @FXML
    private Button AddDomain;

    @FXML
    private DatePicker ExpirationDate;

    private String CustomerId;

    private Map<String,Domain> CustomerDomains;





//todo Refresh invoices and domains combobox on info
    //This is triggered if the user hits the add domain button in the gui
    @FXML
    void pressed(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (event.getSource() == AddDomain)
        {
            //Create a database reader so as to add the new domain to the customer in the database
            Read_Database AddDomain = new Read_Database();

            //Create an empty arraylist with items so you can store in it the Hosting and Domain type
            ArrayList<Item> DomainServices = new ArrayList<>();

            //Get the date that the domain is added to the customer
            //This is important so as to calculate when the domain is to be payed and refreshed
            Date PaymentDate = Date.valueOf(LocalDate.now());


            //Get the templated items from the Items Map depending if the user chose to add hosting or/and domain type to the domain
            for (Map.Entry<String,Item> entry : ItemsMap.entrySet())
            {
                if (entry.getValue().getType().equals(HostingType.getValue()) || entry.getValue().getType().equals(DomainType.getValue()))
                {
                    DomainServices.add(new Item(entry.getValue()));
                    System.out.println("Item added = "+entry.getValue().getType());
                }
            }

            //Increment the Last Domain and Last Invoice id's
            LastDomainId++;
            LastInvoiceId++;

            //Create the new domain
            Domain NewDomain = new Domain(LastDomainId,DomName.getText(),PaymentDate,Date.valueOf(ExpirationDate.getValue()),LastInvoiceId);

            //Create an invoice for the new domain,containing the hosting and domain type items
            Invoice NewInvoice = new Invoice(LastInvoiceId,Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusYears(1)),"YEARLY","YEARLY",DomainServices,new ArrayList<Payment>(),0);
            //Put the new domain to the customer domains map
            CustomerDomains.put(DomName.getText(),NewDomain);

            //Calculate the price of the new invoice
            NewInvoice.Calc_Invoice_Price();

            //Add the new domain and its invoice to the customer
            customerMap.get(CustomerId).getDomainsList().add(NewDomain);
            customerMap.get(CustomerId).GetInvoicesList().add(NewInvoice);

            //Apply the changes to the database
            AddDomain.AddInvoice(CustomerId,NewInvoice);
            for(int i = 0;i<NewInvoice.getItems().size();i++)
            {
                AddDomain.UpdateInvoice(LastInvoiceId,DomainServices.get(i));
            }

            AddDomain.AddDomain(CustomerId,NewDomain);
        }

    }

    //Initialize the prompt texts of the Add Domain GUI components and the combo boxes
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DomName.setPromptText("Domain Name");
        ExpirationDate.setPromptText("Expiration Date");
        HostingType.getItems().addAll("HOSTING SMALL","HOSTING MEDIUM","HOSTING BIG");
        DomainType.getItems().addAll("DOMAIN.GR","DOMAIN.COM");
    }

    //Set the customer id to the controller
    public void SetCustomerId(String id)
    {
        this.CustomerId = id;
    }

    //Set the Customer Domains map to the controller
    public void SetCustomerDomains(Map<String,Domain> CustomerDomains)
    {
        this.CustomerDomains = CustomerDomains;
    }
}
