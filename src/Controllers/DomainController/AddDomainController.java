package Controllers.DomainController;
import Controllers.Invoice_Editing_Controller;
import Entities.*;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
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

    private  ComboBox<String> DomainComboBox;

    private VBox YearlyBox;

    private VBox RecurringBox;




//todo Refresh invoices and domains combobox on info
    //This is triggered if the user hits the add domain button in the gui
    @FXML
    void pressed(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (event.getSource() == AddDomain)
        {

            //Get the Pricing labels of thee customer and the main GUI so as to update them
            Linker linker = new Linker();
            Label TotalIncomeLabel = linker.GetLabelLink("IncomeLabel");
            Label CustomerCostLabel = linker.GetLabelLink(CustomerId+"CustomerPrice");
            String TotalIncomeString = TotalIncomeLabel.getText().substring(0, TotalIncomeLabel.getText().length() - 1 );
            String CustomerIncomeString = CustomerCostLabel.getText().substring(0 ,CustomerCostLabel.getText().length() - 1 );
            float TotalIncome = Float.parseFloat( TotalIncomeString );
            float CustomerIncome = Float.parseFloat( CustomerIncomeString );



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

            //Update the Total income and Customer income labels
            TotalIncome += NewInvoice.getPrice();
            CustomerIncome += NewInvoice.getPrice();

            TotalIncomeLabel.setText( String.valueOf( TotalIncome ) );
            CustomerCostLabel.setText( String.valueOf( CustomerIncome ) );

            //Add the new Domain to the Domains Combo Box
            DomainComboBox.getItems().add(NewDomain.getName());

            //Add the invoice to the Recurring and Yearly Boxes
            Add_Invoice_to_VBox(YearlyBox,RecurringBox,NewInvoice);



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

    //Set the Customers Domain ComboBox
    public void SetDomainCombox (ComboBox<String> DomainsComboBox)
    {
        this.DomainComboBox = DomainsComboBox;
    }

    //Method responsible for knowing the cointainers to add the invoice to
    public void SetContainers(VBox YearlyBox , VBox ReccuringBox)
    {
        this.YearlyBox = YearlyBox;
        this.RecurringBox = ReccuringBox;
    }


    //Method responsible for adding the invoice to the VBoxes
    public void Add_Invoice_to_VBox(VBox YearlyBox , VBox ReccuringBox , Invoice temp) throws IOException {

        //Two boxes,one holds the items by its type(yearly,monthly etc)
        //the other is added if the items is reccuring
        HBox box,RecBox;

        //Get the invoice from the customer entity


        //Load the invoice template
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Invoice_Item.fxml"));

        box = loader.load();

        //Linker to get the price label
        Linker linker = new Linker();



        //Append a controller to the invoice GUI component
        Invoice_Editing_Controller EditControl = loader.getController();

        System.out.println("Customer id = "+CustomerId+"Invoice id = "+temp.getId());
        //Set to the controller the invoice and customer id's
        EditControl.setCustomerAndInvoiceId(CustomerId,temp.getId());


        //Initialize the Hbox that shows the invoice basic data such as price ,expiration date etc
        ((Label)box.getChildren().get(0)).setText("Invoice#"+temp.getId());
        ((Label)box.getChildren().get(1)).setText(temp.getBill_Date().toString());
        ((Label)box.getChildren().get(2)).setText(temp.getPayment_Date().toString());
        ((Label)box.getChildren().get(3)).setText(Float.toString(temp.getPrice()));
        ((Label)box.getChildren().get(4)).setText(Float.toString(temp.getPayedAmount()));
        ((ComboBox)box.getChildren().get(5)).getItems().addAll("Add Payment","Edit","Delete");


        //Create a link to the invoice Price Label so it can be updated on domain hosting/type change
        ((Label)box.getChildren().get(3)).setId(temp.getId() + ((Label)box.getChildren().get(3)).getId());
        System.out.println("Invoice price label id = "+ ((Label)box.getChildren().get(3)).getId());
        linker.CreateLink( ( (Label)box.getChildren().get(3) ) );




        System.out.println(temp.getRecurring());
        System.out.println(temp.getType());


        //Add the invoice to the Yearly box since its a domain invoice
        EditControl.SetContainer(YearlyBox);

        //Add to Yearly Box
        YearlyBox.getChildren().add(box);





            //Add to Recurring Box
            //Load the invoice template
            FXMLLoader loaderRec = new FXMLLoader(getClass().getResource("/fxml/Invoice_Item.fxml"));

            RecBox = loaderRec.load();

            //Append a controller to the invoice component
            Invoice_Editing_Controller EditControlRec = loaderRec.getController();

            System.out.println("Reccurence type = "+temp.getRecurring());

            //Set customer and invoice id's to the controller
            EditControlRec.setCustomerAndInvoiceId(CustomerId,temp.getId());
           // EditControlRec.SetIncomeLabel(this.TotalIncome);

            //Initialize the Hbox values
            ((Label)RecBox.getChildren().get(0)).setText("Invoice#"+temp.getId());
            ((Label)RecBox.getChildren().get(1)).setText(temp.getBill_Date().toString());
            ((Label)RecBox.getChildren().get(2)).setText(temp.getPayment_Date().toString());
            ((Label)RecBox.getChildren().get(3)).setText(Float.toString(temp.getPrice()));
            ((Label)RecBox.getChildren().get(4)).setText(Float.toString(temp.getPayedAmount()));
            ((ComboBox)RecBox.getChildren().get(5)).getItems().addAll("Add Payment","Edit","Delete");

            //Add the component to the Reccurrence Box
            ReccuringBox.getChildren().add(RecBox);
            EditControlRec.SetContainer(ReccuringBox);












    }



}
