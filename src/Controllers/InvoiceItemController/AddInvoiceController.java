package Controllers.InvoiceItemController;


import Controllers.Invoice_Editing_Controller;
import Entities.*;
import Methods.Read_Database;
import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*
This controller class is responsible for adding an invoice to the customer
as well as updating the customer price label and the total income label
It also adds the new invoice to the Customer Summary GUI
 */

public class AddInvoiceController extends Globals implements AboveGod, Initializable {

    //Add Invoice GUI Elements
    @FXML
    private TextField InvoiceName;

    @FXML
    private DatePicker PayDate;

    @FXML
    private RadioButton Reccurence;

    @FXML
    private VBox ReccuringOptions;

    @FXML
    private ComboBox<String> InvoiceType;

    @FXML
    private TextArea Notes;

    @FXML
    private Button AddInvoice;

    //Reccurence GUI Elements

    @FXML
    private AnchorPane ReccuringPane;

    @FXML
    private TextField TypeCount;

    @FXML
    private ComboBox<String> ReccurenceType;

    @FXML
    private TextField Cycles;

    boolean checkFlag = false;

    //Variables necessary for updating the customer
    private String CustomerID;
    private ArrayList<Invoice> CustomerInvoices;

    private VBox MonthlyBox;
    private VBox YearlyBox;
    private VBox OneTimeBox;
    private VBox ReccuruningBox;

    private String DefaultType = "ONCE";

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    //Set the Customer data to the controller
    public void SetCustomerData(String CustomerId)
    {
        this.CustomerID = CustomerId;
        //Initialize the InvoiceType combo box
        InvoiceType.getItems().addAll("ONCE","WEEKLY","MONTHLY","YEARLY");
    }

    //Set the containers where the invoice will be added to the controller
    public void SetContainers(VBox MonthlyBox,VBox YearlyBox,VBox OneTimeBox,VBox ReccuruningBox)
    {
        this.MonthlyBox = MonthlyBox;
        this.YearlyBox = YearlyBox;
        this.OneTimeBox = OneTimeBox;
        this.ReccuruningBox = ReccuruningBox;
    }

    @FXML
    void pressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        //If the user clicks the reccurence radio button then three extra GUI elements are shown in the Add invoice panel
        if (event.getSource() == Reccurence && !checkFlag)
        {
            //Load the extra GUI components
            FXMLLoader ReccuringOptionsLoader = new FXMLLoader(getClass().getResource("/fxml/ReccurenceGui.fxml"));
            //Set this controller as their controller
            ReccuringOptionsLoader.setController(this);
            //Load the to the GUI
            AnchorPane pane = ReccuringOptionsLoader.load();

            //Initialize the RecurringOptions Combo box with the types of recurrence
            ((ComboBox) pane.lookup("#"+"ReccurenceType")).getItems().addAll("WEEKLY","MONTHLY","YEARLY");
            ReccuringOptions.getChildren().add(0,pane);

            //Make the reccurence flag true so as to add the invoice correctly
            checkFlag = true;
        }
        //If the user clicks the reccurence raido button again then close it
        else if(event.getSource() == Reccurence && checkFlag)
        {
            ReccuringOptions.getChildren().remove(0);
            checkFlag = false;
        }

        //This is triggered if the user hits the Add invoice button
         if(event.getSource() == AddInvoice)
        {
            Date BillingDate = Date.valueOf(LocalDate.now());
            int cycles = 0;
            int repetitions = 0;

            //If the flag is true then the user made the invoice reccuring
            if (checkFlag)
            {
                DefaultType = ReccurenceType.getValue();//Add the recurrence type to the Deafault type variable
                cycles = Integer.parseInt( Cycles.getText() ) ;
                repetitions = Integer.parseInt( TypeCount.getText() ) ;
            }


            //Increment the last invoice id
            LastInvoiceId++;
            //Create the new invoice
            Invoice NewInvoice = new Invoice(LastInvoiceId,BillingDate,Date.valueOf(PayDate.getValue()),InvoiceType.getValue(),DefaultType,new ArrayList<Item>(),new ArrayList<Payment>(),0);
            NewInvoice.setRepetitions(repetitions);
            NewInvoice.setCycles(cycles);

            //Set notes to the invoice
            NewInvoice.setNotes(Notes.getText());

            //Create a database adder to add the invoice to the database
            Read_Database adder = new Read_Database();
            System.out.println("Invoice Type = "+NewInvoice.getType() + "Invoice reccurence = "+NewInvoice.getRecurring());

            //Add the invoice to the database
            adder.AddInvoice(CustomerID,NewInvoice);
            //Add the invoice to the customer
            customerMap.get(CustomerID).GetInvoicesList().add(NewInvoice);

            //-----Add invoice to Box----
            HBox box,RecBox;

            Linker linker = new Linker();
            if(NewInvoice.getRecurring().equals("ONCE")) {
                //Load the invoice template component
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Invoice_Item.fxml"));

                box = loader.load();


                //Append a controller to the invoice
                Invoice_Editing_Controller EditControl = loader.getController();

                System.out.println("Customer id = " + CustomerID + "Invoice id = " + NewInvoice.getId());
                //Set the customer and invoice id's to the controller
                EditControl.setCustomerAndInvoiceId(CustomerID, NewInvoice.getId());
                //EditControl.SetIncomeLabel(this.TotalIncome);

                //Initialize the HBoxes components with the invoice data
                ((Label) box.getChildren().get(0)).setText("Invoice#" + NewInvoice.getId());
                ((Label) box.getChildren().get(1)).setText(NewInvoice.getBill_Date().toString());
                ((Label) box.getChildren().get(2)).setText(NewInvoice.getPayment_Date().toString());
                ((Label) box.getChildren().get(3)).setText(Float.toString(NewInvoice.getPrice()));
                ((Label) box.getChildren().get(4)).setText(Float.toString(NewInvoice.getPayedAmount()));


                //Create a link to the invoice Price Label so it can be updated on domain hosting/type change
                ((Label) box.getChildren().get(3)).setId(NewInvoice.getId() + ((Label) box.getChildren().get(3)).getId());
                System.out.println("Invoice price label id = " + ((Label) box.getChildren().get(3)).getId());
                linker.CreateLink(((Label) box.getChildren().get(3)));


                //Check the type of the invoice and add it to the corresponding VBox container
                if (NewInvoice.getType().equals("MONTHLY")) {
                    MonthlyBox.getChildren().add(box);
                    EditControl.SetContainer(MonthlyBox);
                }
                if (NewInvoice.getType().equals("ONCE")) {
                    OneTimeBox.getChildren().add(box);
                    EditControl.SetContainer(OneTimeBox);
                }
                if (NewInvoice.getType().equals("YEARLY")) {
                    YearlyBox.getChildren().add(box);
                    EditControl.SetContainer(YearlyBox);
                }


            }

            //If the invoice is marked as recurring then add it to the recurring VBox container
            if(!NewInvoice.getRecurring().equals("ONCE"))
            {
                //Load the invoice template component
                FXMLLoader loaderRec = new FXMLLoader(getClass().getResource("/fxml/Invoice_Item.fxml"));

                RecBox = loaderRec.load();
                //Append a controller to the component
                Invoice_Editing_Controller EditControlRec = loaderRec.getController();

                System.out.println("Reccurence type = "+NewInvoice.getRecurring());
                EditControlRec.setCustomerAndInvoiceId(CustomerID,NewInvoice.getId());
                //EditControlRec.SetIncomeLabel(this.TotalIncome);

                //Initialize the HBoxes items
                ((Label)RecBox.getChildren().get(0)).setText("Invoice#"+NewInvoice.getId());
                ((Label)RecBox.getChildren().get(1)).setText(NewInvoice.getBill_Date().toString());
                ((Label)RecBox.getChildren().get(2)).setText(NewInvoice.getPayment_Date().toString());
                ((Label)RecBox.getChildren().get(3)).setText(Float.toString(NewInvoice.getPrice()));
                ((Label)RecBox.getChildren().get(4)).setText(Float.toString(NewInvoice.getPayedAmount()));


                //Create a link to the invoice Price Label so it can be updated on domain hosting/type change
                ((Label) RecBox.getChildren().get(3)).setId(NewInvoice.getId() + ((Label) RecBox.getChildren().get(3)).getId());
                System.out.println("Invoice price label id = " + ((Label) RecBox.getChildren().get(3)).getId());
                linker.CreateLink(((Label) RecBox.getChildren().get(3)));

                //Add the invoice to the reccuring box
                ReccuruningBox.getChildren().add(RecBox);
                //Set the box to the cotnroller
                EditControlRec.SetContainer(ReccuruningBox);
            }



        }

    }
}
