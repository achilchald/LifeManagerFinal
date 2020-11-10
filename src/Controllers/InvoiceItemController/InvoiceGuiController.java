package Controllers.InvoiceItemController;

import Entities.*;
import Methods.Read_Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

/*
This controller is responsible for the Edit invoice GUI
It is also responsible for applying any changes done to the invoice to the customer
 */
public class InvoiceGuiController extends Globals implements AboveGod {



    @FXML
    private VBox ItemsList;




    //------Add Item Panel Data----

    @FXML
    private TextField DiscountField;

    @FXML
    private Button AddPayment;

    @FXML
    private TextField PaymentPrice;

    @FXML
    private TextArea PaymentNote;

    @FXML
    private Button PayInvoice;

    @FXML
    private ComboBox<String> ItemListBox;

    @FXML
    private TextField NameText;

    @FXML
    private TextField PriceText;

    @FXML
    private TextField ReccuringText;

    @FXML
    private Button AddItemButton;

    @FXML
    private ScrollPane PaymentsPane;

    @FXML
    private VBox PaymentsBox;

    @FXML
    private CheckBox Scaling;

    private String ItemId;

    //------------------------------

    //Helper variables

    private int InvoiceID;

    private ArrayList<Item> ItemList;

    private ArrayList<Payment> PaymentsList;

    private int maxId;

    private Label cost;

    //------Payment Fields-----
    private Label TotalIncome;

    private Label CustomerCost;

    private Label PayedAmount;

//--------------------------

    private  Label Test;

    private String InvoiceType;

    private String CustomerID;

    private Invoice CurrentInvoice;





    //Load the Invoice Items to the GUI
    public void LoadItems(String CustomerId, int InvoiceId,Label InvoiceCost,Label TotalIncome,Label PayedAmount) throws IOException {

        //Create a linker so as to be able to update the customers,total income and invoice price labels
        Linker link = new Linker();
        this.TotalIncome = link.GetLabelLink("IncomeLabel");
        this.CustomerCost = link.GetLabelLink(CustomerId+"CustomerPrice");
        this.PayedAmount = PayedAmount;

        System.out.println("Cust Cost = "+CustomerCost.getText());





        //this.TotalIncome = TotalIncome;

        //Get the InvoiceId and its Items
        ArrayList<Item> temp = customerMap.get(CustomerId).GetInvoicesList().get(0).getItems();
        for (int i = 0;i<customerMap.get(CustomerId).GetInvoicesList().size();i++)
        {
            if(customerMap.get(CustomerId).GetInvoicesList().get(i).getId() == InvoiceId)
            {

                //Get the invoice id
                this.InvoiceID = InvoiceId;
                //Get the current invoice
                CurrentInvoice = customerMap.get(CustomerId).GetInvoicesList().get(i);

                //Get the list of the invoice payments
                this.PaymentsList = customerMap.get(CustomerId).GetInvoicesList().get(i).getPayments();


                //Get the list of the invoice items,its type and cost
                this.ItemList = customerMap.get(CustomerId).GetInvoicesList().get(i).getItems();
                this.InvoiceType = customerMap.get(CustomerId).GetInvoicesList().get(i).getRecurring();
                this.cost = InvoiceCost;
            }
        }


        //Load the invoice items to the GUI
        for (int i = 0; i < ItemList.size(); i++) {
            System.out.println("Item = "+ItemList.get(i).getType()+" "+ItemList.get(i).isPayed());
            LoadItems(i);
            System.out.println("Max id = "+maxId);
        }

        //Load the invoice payments to the GUI
        for(int i = 0;i<PaymentsList.size();i++)
        {
            LoadPayments(i);
        }


    }


    //This method is responsible for loading the add payment gui
    @FXML
    void Add_Payment(ActionEvent event) throws IOException
    {

        //Load the add payment template and show its GUI
        FXMLLoader LoadEditGui = new FXMLLoader(getClass().getResource("/fxml/Add_Payment.fxml"));
        //Append this controller to the add payment GUI
        LoadEditGui.setController(this);
        Parent root = LoadEditGui.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Payment");
        stage.show();
    }

    //Open the Add Item GUI
    @FXML
    void Add_Item(ActionEvent event) throws IOException {
        //Load the add item GUI and show it
        FXMLLoader LoadEditGui = new FXMLLoader(getClass().getResource("/fxml/AddItem.fxml"));

        //Append this controller to the add item GUI
        LoadEditGui.setController(this);

        Parent root = LoadEditGui.load();
        this.InitItemsAdder();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Item");
        stage.show();

    }



    //Initialize the combobox of the selectable items
    void InitItemsAdder()
    {

        for (Map.Entry<String,Item>entry : ItemsMap.entrySet())
        {
            System.out.println(entry.getValue().getType());
            //if(entry.getValue().getRecurring().equals(InvoiceType))
            ItemListBox.getItems().addAll(entry.getValue().getType());
        }

    }

    //This method is responsible for getting the selected item-to-be-added values and showing them to the GUI
    @FXML
    void GetSelectedItem(ActionEvent event) {

        String ItemName = ItemListBox.getValue();
        for (Map.Entry<String,Item>entry : ItemsMap.entrySet())
        {
            if(entry.getValue().getType().equals(ItemName))
            {
                ItemId = entry.getValue().getId();
                NameText.setText(ItemName);
                PriceText.setText(String.valueOf(entry.getValue().getPrice()));
                ReccuringText.setText(entry.getValue().getRecurring());
            }
        }
    }


    @FXML//Method responsible for adding a new service to the Gui and the invoice
    void TakeItem(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        float Discount = Float.parseFloat(DiscountField.getText());

        //Load the ItemGrid gridpane
        FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/ItemGrid.fxml"));


        //Create a temp entity of the item to be added
        Item temp = new Item(ItemsMap.get(ItemId));
        //Set a discount to the item if need be
        temp.SetDiscount(Discount);
        System.out.println("INVOICE RECCURENCE = "+CurrentInvoice.getRecurring());

        //If the item is marked as scaling with the reccurence then calculate its total price
        if(Scaling.isSelected())
            temp.CalculateReccuringPrice(CurrentInvoice.getType());

        System.out.println("Item to be added = "+temp.getType());



        System.out.println("INVOICE TYPE = "+InvoiceType+"iTEM TYPE = "+temp.getType());

        //Get the price of the item
        float ItemPrice = temp.getPrice();
        System.out.println("Item price = "+temp.getPrice());

        //Add the item to the items list of the invoice
        ItemList.add(temp);

        //Calculate the price of the invoice after the item is added
        CurrentInvoice.NewPrice(ItemPrice);
        System.out.println("Invoice Price = "+CurrentInvoice.getPrice());

        //Load the item gridpane
        GridPane pane = ItemLoader.load();


        //Append a controller to the item gridpane
        EditItemController ctrl = ItemLoader.getController();


        //Increment the max id of the Gridpane items
        //This is important for removing the item correctly
        maxId++;


        //Set the id to the Gridpane
        pane.setId(String.valueOf(maxId));


        //Load the services and the guis data on the Service controller
        ctrl.SetInvoiceID(InvoiceID);
        ctrl.SetItemId(temp.getType());
        ctrl.SetIdAndItemsBox(pane.getId(),ItemsList);
        ctrl.SetPriceLabels(cost,TotalIncome,CustomerCost);
        ctrl.SetItemList(ItemList);
        ctrl.SetPaymentsBox(PaymentsBox);
        ctrl.SetInvoice(CurrentInvoice);



        System.out.println("Pane id = "+pane.getId());
        System.out.println("PRICE = "+ItemPrice);
        pane.setGridLinesVisible(true);

        //Add the values to the item gridpane
        pane.add(new Label(temp.getType()), 0, 0);
        pane.add(new Label("0"), 1, 0);
        pane.add(new Label(String.valueOf(ItemPrice)), 2, 0);


        //Add the item to the VBox containing the invoices items
        ItemsList.getChildren().add(pane);


        //Update the cost of the invoice
        cost.setText(String.valueOf( Float.parseFloat(cost.getText()) + temp.getPrice() ) );
        String TCost = TotalIncome.getText();
        TCost = TCost.substring(0,TCost.length() - 1);


        //Update the total income label
        TotalIncome.setText(String.valueOf( Float.parseFloat(TCost) + temp.getPrice() ) + "$");

        //Update the customer price label
        float CustCost = Float.parseFloat(CustomerCost.getText());
        CustCost = CustCost + temp.getPrice();
        CustomerCost.setText(String.valueOf(CustCost));




        //Create a database reader to apply the changes to the database
        Read_Database updater = new Read_Database();
        System.out.println(InvoiceID + "  " +ItemId);
        updater.UpdateInvoice(InvoiceID,temp);
    }






    //This method is responsible adding a payment to the invoice
    @FXML
    void Pay(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        float NewAmount =  Float.parseFloat(  PaymentPrice.getText()  );

        //Check if the payed amount is valid
        if(NewAmount > CurrentInvoice.getPrice() - CurrentInvoice.getPayedAmount())
        {
            System.out.println("Wrong amount ,please enter a valid amount");
            System.out.println("New Ammount = "+NewAmount + "Invoice Cost = " + CurrentInvoice.getPrice());
        }
        else
        {
            //Load the payment template
            FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/PaymentGrid.fxml"));
            //Create a payment entity and fill its values from the fields in the GUI
            Payment NewPayment = new Payment(LastPaymentId++,InvoiceID, Date.valueOf(LocalDate.now()) ,NewAmount,PaymentNote.getText());

            System.out.println(NewPayment.getPaymentDate());
            //Create a database reader so as to add the payment to the databse
            Read_Database payer = new Read_Database();
            payer.AddPayment(NewPayment);

            GridPane PaymentGrid = ItemLoader.load();
            //Set the id of the payment to the gridpane
            PaymentGrid.setId(String.valueOf(LastPaymentId));

            PaymentGrid.setGridLinesVisible(true);


            //Load the services elements into the Gui GridPane
            PaymentGrid.add(new Label(NewPayment.getPaymentDate().toString()),0,0);
            PaymentGrid.add(new Label(String.valueOf(NewPayment.getPaymentId())),1,0);
            PaymentGrid.add(new Label(String.valueOf(NewPayment.getPrice())), 2, 0);
            PaymentGrid.add(new Label(NewPayment.getNotes()), 3, 0);

            //Update the invoices payed amount label
            float Payed_Amount = Float.parseFloat(PayedAmount.getText());
            Payed_Amount = Payed_Amount + NewPayment.getPrice();
            PayedAmount.setText(String.valueOf(Payed_Amount));



            //Add the payment to the invoice
            CurrentInvoice.getPayments().add(NewPayment);
            CurrentInvoice.NewPayedAmount(NewPayment.getPrice());
            //Start the payment algorithm to determine if the amount payed is enough to mark an item as payed
            PaymentAlgorithm(NewPayment.getPrice());
            //Add the payment to the GUI
            PaymentsBox.getChildren().add(PaymentGrid);
        }


    }







    //Start loading the items into their VBox
    void LoadItems(int i) throws IOException {
        FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/ItemGrid.fxml"));

        //Get the Service
        Item temp = ItemList.get(i);

        System.out.println(" item = "+temp.getType()+temp.isPayed()+temp.getPrice()+" "+temp.getPayedDate());
        //Take the services GridPane
        GridPane pane = ItemLoader.load();

        EditItemController ctrl = ItemLoader.getController();

        pane.setId(String.valueOf(i));

        //Load the services and the guis data on the Service controller
        ctrl.SetInvoiceID(InvoiceID);
        ctrl.SetItemId(temp.getType());
        ctrl.SetIdAndItemsBox(pane.getId(),ItemsList);
        ctrl.SetPriceLabels(cost,this.TotalIncome,this.CustomerCost);
        ctrl.SetItemList(ItemList);
        ctrl.SetPaymentsBox(PaymentsBox);
        ctrl.SetInvoice(CurrentInvoice);

        System.out.println("Pane id = "+pane.getId());

        pane.setGridLinesVisible(true);


        //if the item is fully payed then mark it
        if(temp.isPayed())
            pane.setStyle("-fx-background-color:#42f587;");

        //Load the services elements into the Gui GridPane
        pane.add(new Label(temp.getType()),0,0);
        pane.add(new Label("0"), 1, 0);
        pane.add(new Label(String.valueOf(temp.getPrice())), 2, 0);


        //Add it to the Vbox
        ItemsList.getChildren().add(pane);


        maxId = i;
    }





    //Start loading the payed Services into their VBox
    void LoadPayments(int i) throws IOException {
        FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/PaymentGrid.fxml"));

        //Get the Service
        Payment payment = PaymentsList.get(i);
        System.out.println("payed item = "+payment.getPaymentDate());

        //Take the services GridPane
        GridPane pane = ItemLoader.load();

        pane.setId(String.valueOf(i));

        //Load the services and the guis data on the Service controller


        System.out.println("Pane id = "+pane.getId());

        pane.setGridLinesVisible(true);


        //Load the services elements into the Gui GridPane
        pane.add(new Label(payment.getPaymentDate().toString()),0,0);
        pane.add(new Label(String.valueOf(payment.getPaymentId())),1,0);
        pane.add(new Label(String.valueOf(payment.getPrice())), 2, 0);
        pane.add(new Label(payment.getNotes()), 3, 0);


        //Add it to the Vbox
        PaymentsBox.getChildren().add(pane);

    }










    //This algorithm is responsible for checking if an item can be marked as payed
    public void PaymentAlgorithm(float PayedAmount) throws SQLException, ClassNotFoundException {
        //Create a database reader to mark the item in the database as payed,and add the change from the payement to the invoice
        Read_Database Payer = new Read_Database();
        //Get the change from the previous payment
        float change = CurrentInvoice.getChangeFromPayment();

        //Set a temp variable with the payed amount and the change from previous payments
        float temp = PayedAmount+change;
        for (int i = 0;i<ItemList.size();i++)
        {
            //Check if an item can be marked as payed
            if(ItemList.get(i).getPrice()<=temp && !ItemList.get(i).isPayed())
            {
                //Refract the price of the item from the temp variable
                temp = temp - ItemList.get(i).getPrice();
                //Set the item as payed
                ItemList.get(i).SetPayed(true);
                String itemId = ItemList.get(i).getId();
                //Mark the item as payed
                ItemsList.getChildren().get(i).setStyle("-fx-background-color:#42f587;");
                //Mark the item as payed in the database
                Payer.PayItem(itemId,InvoiceID,Date.valueOf(LocalDate.now()));

            }

        }
        //Set to the invoice the change from the payement
        CurrentInvoice.setChangeFromPayment(temp);
        //Set the change to the invoice in the database
        Payer.SetChange(CurrentInvoice);
        System.out.println("Resta = "+temp);

        if(CurrentInvoice.getPayedAmount() == CurrentInvoice.getPrice())
        {
            Payer.setInvoiceAsPayed(CurrentInvoice.getId());
        }

    }

}


