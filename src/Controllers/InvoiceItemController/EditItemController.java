package Controllers.InvoiceItemController;

import Entities.*;
import Methods.Database_Deleter;
import Methods.Read_Database;
import Methods.WriteToDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/*
This Controller is responsible for the Service Items Inside the Invoice
It is responsible for getting the Service and marking it as payed or editing it
 */
public class EditItemController implements AboveGod {

    @FXML
    private Button EditItem;

    @FXML
    private Button DeleteItem;

    private String ItemId;

    @FXML
    private GridPane MyGrid;

    @FXML
    private Button DeletePaymentButton;

    private VBox ItemsList;

    private int InvoiceId;

    private Label Price;

    private Label TotalIncome;

    private Label CustomerCost;

    private Label InvoiceCostLabel;

    private Label DueAmountLabel;

    private Label PayedAmountLabel;

    private Label TotalPayedAmount;

    private ArrayList<Item> InvoiceItems;

    private VBox PaymentsBox;

    private float price;

    private Invoice CurrentInvoice;

    private Payment CurrentPayment;

    private Item CurrentItem;

    //Edit item fxml fields

    @FXML
    private TextField NameText;

    @FXML
    private TextField PriceText;

    @FXML
    private TextField ReccuringText;

    @FXML
    private TextField DiscountField;

    @FXML
    private Button AddItemButton;

    @FXML
    private CheckBox Scaling;

    boolean RecurrenceFlag = false;


    //Edit Payment fields

    @FXML
    private TextField PaymentPrice;

    @FXML
    private TextArea PaymentNote;

    @FXML
    private Button UpdatePayment;

    @FXML
    private Button EditPayment;

    private String CustomerID;


    String PreviousPrice;

    String Myid;


    /*
        The following setter methods get the GUI Elements from the InvoiceGuiController class so as to add or remove items from its Vboxes
         */
    void SetItemList(ArrayList<Item> List){this.InvoiceItems = List;}//Get the itemsList of the invoice so at to apply possible changes

    void SetInvoice(Invoice invoice){this.CurrentInvoice = invoice;}
    //Get the id of the current Invoice
    void SetInvoiceID(int id)
    {
        this.InvoiceId = id;
    }

    void SetCustomerID(String id)
    {
        CustomerID = id;
    }

    //Get the total income and invoice price labels so that they can be updated when a Service is added or removed or payed
    void SetPriceLabels(Label InvoicePrice,Label TotalIncome,Label CustomerCost,Label InvoiceCost,Label DueAmount)
    {
        this.Price = InvoicePrice;
        this.TotalIncome = TotalIncome;
        this.CustomerCost = CustomerCost;
        this.InvoiceCostLabel = InvoiceCost;
        this.DueAmountLabel = DueAmount;
    }

    void SetPaymentLabels(Label PayedAmountLabel ,Label DueAmountLabel)
    {
        this.PayedAmountLabel = PayedAmountLabel;
        this.DueAmountLabel = DueAmountLabel;
    }

    //set the invoice hbox payed amount
    void SetTotalPayedAmount(Label PayedAmount)
    {
        this.TotalPayedAmount = PayedAmount;
    }

    //Get the Id of the current Service you are viewing
    void SetItemId(String itemId)
    {
        this.ItemId = itemId;
    }

    //Get the Vbox responsible for showing the unpayed Services
    void SetIdAndItemsBox(String id, VBox ItemList)
    {
        this.ItemsList = ItemList;
        this.Myid = id;

    }

    //Get the Vbox responsible for showing the payed services
    void SetPaymentsBox(VBox Payments)
    {
        this.PaymentsBox = Payments;
    }

    void SetCurrentPayment(Payment payment)
    {
     this.CurrentPayment = payment;
    }

    void SetItemGrid(GridPane grid)
    {
        this.MyGrid = grid;
    }

    void SetPaymentGrid(GridPane grid)
    {
        this.MyGrid = grid;
    }


    @FXML//Make the appropriate changes if a service is deleted or payed
    void pressed(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (event.getSource() == DeleteItem)
        {

            Linker linker = new Linker();
            System.out.println("Item to be deleted = "+ItemId);
            String ItemID = null;
            float itemPrice = 0;
            boolean ItemStatus = false;


//Get total customers values
            float TotalPayed = Float.parseFloat(linker.GetLabelLink("TotalCustomerPayed").getText()
                    .substring(0, linker.GetLabelLink("TotalCustomerPayed").getText().length() - 1 ));
            float TotalDue = Float.parseFloat(linker.GetLabelLink("TotalCustomerDue").getText()
                    .substring(0, linker.GetLabelLink("TotalCustomerDue").getText().length() - 1 ));
            TotalPayed -= customerMap.get(CustomerID).getPayedAmount();
            TotalDue -= customerMap.get(CustomerID).getDueAmount();



            //todo get the item on init so as to avoid the loops bellow
            for (int i = 0;i<CurrentInvoice.getItems().size();i++)
            {
                if (CurrentInvoice.getItems().get(i).getId().equals(ItemId))
                {
                    ItemID = CurrentInvoice.getItems().get(i).getId();
                    itemPrice = CurrentInvoice.getItems().get(i).getPrice();
                    ItemStatus = CurrentInvoice.getItems().get(i).isPayed();
                    String TotalIncomeString = TotalIncome.getText().substring(0, TotalIncome.getText().length() - 1);
                    TotalIncome.setText(String.valueOf( Float.parseFloat(TotalIncomeString) - CurrentInvoice.getItems().get(i).getPrice() ) + "$");
                    Price.setText(String.valueOf(Float.parseFloat(Price.getText()) - CurrentInvoice.getItems().get(i).getPrice() ));

                    CurrentInvoice.RemovePrice(CurrentInvoice.getItems().get(i).getPrice());
                    float CustCost = Float.parseFloat(CustomerCost.getText());
                    CustCost = CustCost - CurrentInvoice.getItems().get(i).getPrice();
                    CustomerCost.setText(String.valueOf(CustCost));
                }
            }

            System.out.println("Item List size = "+InvoiceItems.size());
            for (int i = 0;i<InvoiceItems.size();i++)
            {
                if (InvoiceItems.get(i).getId().equals(ItemID))
                {
                    InvoiceItems.remove(i);
                    break ;
                }
            }

            Read_Database deleter = new Read_Database();

            deleter.RemoveItem(InvoiceId,ItemID);

            //RemovePayments(itemPrice,ItemStatus);
            ItemsList.getChildren().remove(ItemsList.lookup("#"+Myid));

            InvoiceCostLabel.setText( String.valueOf( CurrentInvoice.getPrice()) ) ;
            DueAmountLabel.setText( String.valueOf( CurrentInvoice.getPrice() - CurrentInvoice.getPayedAmount()) ) ;



            customerMap.get(CustomerID).calculatePrice();

            linker.GetLabelLink(CustomerID + "CustomerPayedAmount").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
            linker.GetLabelLink(CustomerID + "CustomerDueAmount").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));

            linker.GetLabelLink(CustomerID + "InterCustomerCost").setText(String.valueOf(customerMap.get(CustomerID).getPrice()));
            linker.GetLabelLink(CustomerID + "InterCustomerPayed").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
            linker.GetLabelLink(CustomerID + "InterCustomerDue").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));



//Update total customers values
            TotalPayed += customerMap.get(CustomerID).getPayedAmount();
            TotalDue += customerMap.get(CustomerID).getDueAmount();

            linker.GetLabelLink("TotalCustomerPayed").setText(String.valueOf(TotalPayed) + "$");
            linker.GetLabelLink("TotalCustomerDue").setText(String.valueOf(TotalDue) + "$");

        }
        //        if (event.getSource() == PayItem)
//        {
//            System.out.println("Item to be payed = " + ItemName);
//            String ItemID = null;
//
//            //Get the type of the item that is being payed
//            //Todo Make a copy of the item in the controller so as to remove the lines bellow this comment
//            for (Map.Entry<String, Item> entry : ItemsMap.entrySet())
//            {
//                if (entry.getValue().getType().equals(ItemName))
//                {
//                    ItemID = entry.getValue().getId();
//                    price = entry.getValue().getPrice();
//                    //Update the total cost label in the MainGui
//                    String TCost = TotalIncome.getText();
//                    //TCost = StringUtils.chop(TCost);
//                    TotalIncome.setText(String.valueOf( Float.parseFloat(TCost) - entry.getValue().getPrice() ) + "$");
//                    Price.setText(String.valueOf(Float.parseFloat(Price.getText()) - entry.getValue().getPrice() ));
//                }
//            }
//
//            //Mark the item as payed and apply that current date
//            //Todo Make a copy of the item in the controller so as to remove the lines bellow this comment
//            Date PaymentDate = Date.valueOf(LocalDate.now());
//            System.out.println("Item List size = "+InvoiceItems.size());
//            for (int i = 0;i<InvoiceItems.size();i++)
//            {
//                if (InvoiceItems.get(i).getId().equals(ItemID))
//                {
//                    InvoiceItems.get(i).SetPayed(true);
//                    InvoiceItems.get(i).setPayedDate(PaymentDate);
//                }
//            }
//
//
//
//
//
//            //Load the Services elements into a gui grid and put it into the payments Vbox
//            FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/PaymentGrid.fxml"));
//           MyGrid = ItemLoader.load();
//           MyGrid.setGridLinesVisible(true);
//            ItemLoader.setController(this);
//            MyGrid.add(new Label(PaymentDate.toString()),0,0);
//            MyGrid.add(new Label(ItemID),1,0);
//            MyGrid.add(new Label(ItemName), 2, 0);
//            MyGrid.add(new Label(String.valueOf(price)), 3, 0);
//
//            //Update the database
//            Read_Database markPayed = new Read_Database();
//            markPayed.PayItem(ItemID,InvoiceId,PaymentDate);
//
//            //Remove from the unpayed Items VBox
//            PaymentsBox.getChildren().add(MyGrid);
//            ItemsList.getChildren().remove(ItemsList.lookup("#"+Myid));
//
//
//
//        }
        if (event.getSource() == DeletePaymentButton)
        {
            Linker linker = new Linker();
            Database_Deleter deleter = new Database_Deleter();
            deleter.Delete_Payment(CurrentPayment.getPaymentId());


            //Get total customers values
            float TotalPayed = Float.parseFloat(linker.GetLabelLink("TotalCustomerPayed").getText()
                    .substring(0, linker.GetLabelLink("TotalCustomerPayed").getText().length() - 1 ));
            float TotalDue = Float.parseFloat(linker.GetLabelLink("TotalCustomerDue").getText()
                    .substring(0, linker.GetLabelLink("TotalCustomerDue").getText().length() - 1 ));
            TotalPayed -= customerMap.get(CustomerID).getPayedAmount();
            TotalDue -= customerMap.get(CustomerID).getDueAmount();


            System.out.println("ID of deleted payment = "+CurrentPayment.getPaymentId() + "Price of deleted payment = "+CurrentPayment.getPrice());
            CurrentInvoice.updatePayedAmount(CurrentPayment.getPrice());
            System.out.println("Updated Payed Amount = "+CurrentInvoice.getPayedAmount());
            //CurrentInvoice.setChangeFromPayment(0);

            PayedAmountLabel.setText( String.valueOf( CurrentInvoice.getPayedAmount() ));
            DueAmountLabel.setText( String.valueOf( CurrentInvoice.getPrice() - CurrentInvoice.getPayedAmount() ) );
            TotalPayedAmount.setText( String.valueOf( CurrentInvoice.getPayedAmount() ) );

            for(int i = 0; i < CurrentInvoice.getPayments().size(); i++)
            {
                if(CurrentInvoice.getPayments().get(i).getPaymentId() == CurrentPayment.getPaymentId())
                {
                    PaymentsBox.getChildren().remove(PaymentsBox.lookup("#"+String.valueOf(CurrentPayment.getPaymentId())));
                    CurrentInvoice.getPayments().remove(i);
                    break;
                }
            }


            customerMap.get(CustomerID).calculatePrice();

            linker.GetLabelLink(CustomerID + "CustomerPayedAmount").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
            linker.GetLabelLink(CustomerID + "CustomerDueAmount").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));

            linker.GetLabelLink(CustomerID + "InterCustomerCost").setText(String.valueOf(customerMap.get(CustomerID).getPrice()));
            linker.GetLabelLink(CustomerID + "InterCustomerPayed").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
            linker.GetLabelLink(CustomerID + "InterCustomerDue").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));


            //Update total customers values
            TotalPayed += customerMap.get(CustomerID).getPayedAmount();
            TotalDue += customerMap.get(CustomerID).getDueAmount();

            linker.GetLabelLink("TotalCustomerPayed").setText(String.valueOf(TotalPayed) + "$");
            linker.GetLabelLink("TotalCustomerDue").setText(String.valueOf(TotalDue) + "$");



        }
        if (event.getSource() == EditItem)
        {
            System.out.println("Item to be edited = "+ItemId);

            //todo get the item on init so as to avoid the loops bellow
            for (int i = 0;i<CurrentInvoice.getItems().size();i++)
            {
                if (CurrentInvoice.getItems().get(i).getId().equals(ItemId))
                {
                    CurrentItem = CurrentInvoice.getItems().get(i);
                    break ;
                }
            }


            EditItem();


        }
        if (event.getSource() == EditPayment)
        {
            EditPayment();
        }

    }



    void EditItem() throws IOException {
        //Load and show the Edit item panel
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemGuiTemplates/EditItem.fxml"));
        loader.setController(this);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Item");
        stage.show();

        //Initialize the panel's fields
        NameText.setText(CurrentItem.getType());
        PriceText.setText(String.valueOf(CurrentItem.getPrice()));
        DiscountField.setText(String.valueOf(CurrentItem.getDiscount()));
        ReccuringText.setText(CurrentItem.getRecurring());
        if (CurrentItem.isRecurring())
        {
            Scaling.setSelected(true);
            RecurrenceFlag = true;
        }

    }


    void EditPayment() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemGuiTemplates/EditPayment.fxml"));
        loader.setController(this);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Edit Payment");
        stage.show();

        //Initialize the panel's fields
        PaymentPrice.setText(String.valueOf(CurrentPayment.getPrice()));
        PaymentNote.setText(CurrentPayment.getNotes());
    }

    @FXML
    void TakeItem(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        float Item_Price = Float.parseFloat(PriceText.getText());
        float Discount = Float.parseFloat(DiscountField.getText());

        Linker linker = new Linker();

        //Get the total income price
        String TotalIncomeString = TotalIncome.getText().substring(0, TotalIncome.getText().length() - 1);
        float TotalIncomeNumber = Float.parseFloat(TotalIncomeString) - CurrentItem.getPrice();



        //Get total customers values
        float TotalPayed = Float.parseFloat(linker.GetLabelLink("TotalCustomerPayed").getText()
                .substring(0, linker.GetLabelLink("TotalCustomerPayed").getText().length() - 1 ));
        float TotalDue = Float.parseFloat(linker.GetLabelLink("TotalCustomerDue").getText()
                .substring(0, linker.GetLabelLink("TotalCustomerDue").getText().length() - 1 ));
        TotalPayed -= customerMap.get(CustomerID).getPayedAmount();
        TotalDue -= customerMap.get(CustomerID).getDueAmount();





        CurrentItem.setPrice(Item_Price);


        if (Scaling.isSelected() && !RecurrenceFlag)
        {
            System.out.println("CASE 1");
            System.out.println("Invoice reccurence = "+CurrentInvoice.getType() + " Item type = "+CurrentItem.getRecurring());
            //CurrentItem.DescaleItemPrice(CurrentInvoice.getType());
            CurrentItem.RemoveDiscount();
            CurrentItem.SetDiscount(Discount);
            CurrentItem.CalculateReccuringPrice(CurrentInvoice.getType());
            CurrentItem.SetRecurrence(true);
        }
        else if (!Scaling.isSelected() && RecurrenceFlag)
        {
            System.out.println("CASE 2");
            System.out.println("Invoice reccurence = "+CurrentInvoice.getType() + " Item type = "+CurrentItem.getRecurring());
            CurrentItem.DescaleItemPrice(CurrentInvoice.getType());
            CurrentItem.RemoveDiscount();
            CurrentItem.SetDiscount(Discount);
            CurrentItem.SetRecurrence(false);
            RecurrenceFlag = false;
        }
        else if (!Scaling.isSelected() && !RecurrenceFlag)
        {
            System.out.println("CASE 3");
            CurrentItem.RemoveDiscount();
            CurrentItem.SetDiscount(Discount);
        }
        else if (Scaling.isSelected() && RecurrenceFlag)
        {
            System.out.println("CASE 4");
            System.out.println("Invoice reccurence = "+CurrentInvoice.getType() + " Item type = "+CurrentItem.getRecurring());
            CurrentItem.DescaleItemPrice(CurrentInvoice.getType());
            CurrentItem.RemoveDiscount();
            CurrentItem.SetDiscount(Discount);
            CurrentItem.CalculateReccuringPrice(CurrentInvoice.getType());
            CurrentItem.SetRecurrence(true);
        }


        MyGrid.getChildren().remove(MyGrid.lookup("#Type"));
        MyGrid.getChildren().remove(MyGrid.lookup("#Discount"));
        MyGrid.getChildren().remove(MyGrid.lookup("#Price"));

        Label price = new Label(String.valueOf(CurrentItem.getPrice()));
        price.setId("Price");

        Label discount = new Label(String.valueOf(CurrentItem.getDiscount()));
        discount.setId("Discount");

        Label type = new Label(String.valueOf(CurrentItem.getType()));
        type.setId("Type");


        MyGrid.add(type, 0, 0);
        MyGrid.add(discount, 1, 0);
        MyGrid.add(price, 2, 0);
        //MyGrid.setGridLinesVisible(true);

        WriteToDatabase writer = new WriteToDatabase();
        writer.UpdateCustomerItem(CurrentInvoice.getId(),CurrentItem);

        CurrentInvoice.Calc_Invoice_Price();

        InvoiceCostLabel.setText(String.valueOf(CurrentInvoice.getPrice()));
        Price.setText(String.valueOf(CurrentInvoice.getPrice()));
        DueAmountLabel.setText(String.valueOf( CurrentInvoice.getPrice() - CurrentInvoice.getPayedAmount() ));



        customerMap.get(CustomerID).calculatePrice();

        linker.GetLabelLink(CustomerID + "CustomerPayedAmount").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
        linker.GetLabelLink(CustomerID + "CustomerDueAmount").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));

        linker.GetLabelLink(CustomerID + "InterCustomerCost").setText(String.valueOf(customerMap.get(CustomerID).getPrice()));
        linker.GetLabelLink(CustomerID + "InterCustomerPayed").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
        linker.GetLabelLink(CustomerID + "InterCustomerDue").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));


        CustomerCost.setText(String.valueOf(customerMap.get(CustomerID).getPrice()));

        TotalIncomeNumber += CurrentItem.getPrice();

        System.out.println("Total income = " + TotalIncomeNumber);
        TotalIncome.setText(String.valueOf( TotalIncomeNumber ) + "$");


        //Update total customers values
        TotalPayed += customerMap.get(CustomerID).getPayedAmount();
        TotalDue += customerMap.get(CustomerID).getDueAmount();

        linker.GetLabelLink("TotalCustomerPayed").setText(String.valueOf(TotalPayed) + "$");
        linker.GetLabelLink("TotalCustomerDue").setText(String.valueOf(TotalDue) + "$");


    }


    @FXML
    void Pay(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(Float.parseFloat(PaymentPrice.getText()) + (CurrentInvoice.getPayedAmount() - CurrentPayment.getPrice()) > CurrentInvoice.getPrice())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input \n The new payment price sum is greater than the invoices total cost \n Please put an appropriate amount", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();

            if(alert.getResult() == ButtonType.OK )
            {
                System.out.println("lma0");
                return;
            }
        }

        Linker linker = new Linker();

        //Get total customers values
        float TotalPayed = Float.parseFloat(linker.GetLabelLink("TotalCustomerPayed").getText()
                .substring(0, linker.GetLabelLink("TotalCustomerPayed").getText().length() - 1 ));
        float TotalDue = Float.parseFloat(linker.GetLabelLink("TotalCustomerDue").getText()
                .substring(0, linker.GetLabelLink("TotalCustomerDue").getText().length() - 1 ));
        TotalPayed -= customerMap.get(CustomerID).getPayedAmount();
        TotalDue -= customerMap.get(CustomerID).getDueAmount();


        CurrentInvoice.updatePayedAmount( CurrentPayment.getPrice() );


        float NewPrice = Float.parseFloat(PaymentPrice.getText());
        String NewNotes = PaymentNote.getText();
        CurrentPayment.setPrice(NewPrice);
        CurrentPayment.setNotes(NewNotes);

        CurrentInvoice.NewPayedAmount(NewPrice);


        PayedAmountLabel.setText( String.valueOf( CurrentInvoice.getPayedAmount() ));
        DueAmountLabel.setText( String.valueOf( CurrentInvoice.getPrice() - CurrentInvoice.getPayedAmount() ) );
        TotalPayedAmount.setText( String.valueOf( CurrentInvoice.getPayedAmount() ) );




        MyGrid.getChildren().remove(MyGrid.lookup("#PaymentDate"));
        MyGrid.getChildren().remove(MyGrid.lookup("#PaymentId"));
        MyGrid.getChildren().remove(MyGrid.lookup("#Price"));
        MyGrid.getChildren().remove(MyGrid.lookup("#Notes"));

        Label PaymentDate = new Label( CurrentPayment.getPaymentDate().toString() );
        PaymentDate.setId("PaymentDate");

        Label PaymentId = new Label( String.valueOf(CurrentPayment.getPaymentId()) );
        PaymentId.setId("PaymentId");

        Label Price = new Label( String.valueOf(CurrentPayment.getPrice()) );
        Price.setId("Price");

        Label Notes = new Label( CurrentPayment.getNotes() );
        Notes.setId("Notes");


        //Load the services elements into the Gui GridPane
        MyGrid.add(PaymentDate,0,0);
        MyGrid.add(PaymentId,1,0);
        MyGrid.add(Price, 2, 0);
        MyGrid.add(Notes, 3, 0);

        WriteToDatabase Updater = new WriteToDatabase();

        if(CurrentInvoice.getPayedAmount() == CurrentInvoice.getPrice())
        {
            Read_Database reader = new Read_Database();
            reader.setInvoiceAsPayed(CurrentInvoice.getId());
        }
        else if(CurrentInvoice.getPayedAmount() < CurrentInvoice.getPrice())
        {
            Read_Database reader = new Read_Database();
            reader.setInvoiceAsUnPayed(CurrentInvoice.getId());
        }


        Updater.UpdatePayment(CurrentPayment.getPaymentId(),CurrentPayment.getPrice());




        customerMap.get(CustomerID).calculatePrice();

        linker.GetLabelLink(CustomerID + "CustomerPayedAmount").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
        linker.GetLabelLink(CustomerID + "CustomerDueAmount").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));

        linker.GetLabelLink(CustomerID + "InterCustomerCost").setText(String.valueOf(customerMap.get(CustomerID).getPrice()));
        linker.GetLabelLink(CustomerID + "InterCustomerPayed").setText(String.valueOf(customerMap.get(CustomerID).getPayedAmount()));
        linker.GetLabelLink(CustomerID + "InterCustomerDue").setText(String.valueOf(customerMap.get(CustomerID).getDueAmount()));


        //Update total customers values
        TotalPayed += customerMap.get(CustomerID).getPayedAmount();
        TotalDue += customerMap.get(CustomerID).getDueAmount();

        linker.GetLabelLink("TotalCustomerPayed").setText(String.valueOf(TotalPayed) + "$");
        linker.GetLabelLink("TotalCustomerDue").setText(String.valueOf(TotalDue) + "$");

    }




//    public void RemovePayments(float ItemPrice,boolean ItemStatus) throws IOException, SQLException, ClassNotFoundException {
//        float change = 0;
//        Database_Deleter deleter = new Database_Deleter();
//        for(int i = 0; i<CurrentInvoice.getPayments().size(); i++)
//        {
//            Payment currentPayment = CurrentInvoice.getPayments().get(i);
//            if(ItemPrice == currentPayment.getPrice())
//            {
//                System.out.println("CASE 1");
//                CurrentInvoice.updatePayedAmount(currentPayment.getPrice());
//                CurrentInvoice.getPayments().remove(i);
//                PaymentsBox.getChildren().remove(PaymentsBox.lookup("#"+String.valueOf(currentPayment.getPaymentId())));
//                deleter.Delete_Payment(currentPayment.getPaymentId());
//                return;
//            }
//           else if(ItemPrice > currentPayment.getPrice())
//            {
//                System.out.println("CASE 2");
//                ItemPrice -= currentPayment.getPrice();
//                CurrentInvoice.updatePayedAmount(currentPayment.getPrice());
//                PaymentsBox.getChildren().remove(PaymentsBox.lookup("#"+String.valueOf(currentPayment.getPaymentId())));
//                CurrentInvoice.getPayments().remove(i);
//                deleter.Delete_Payment(currentPayment.getPaymentId());
//                i--;
//            }
//           else if(ItemPrice < currentPayment.getPrice())
//            {
//                System.out.println("CASE 3");
//                if(ItemStatus == false)
//                {
//                    ItemPrice=CurrentInvoice.getChangeFromPayment();
//                }
//                System.out.println("Payment id = " + currentPayment.getPaymentId());
//                System.out.println("Item price = "+ItemPrice );
//                PaymentsBox.getChildren().remove(PaymentsBox.lookup("#"+String.valueOf(currentPayment.getPaymentId())));
//                float previousPrice = currentPayment.getPrice();
//                currentPayment.setPrice(previousPrice - ItemPrice);
//
//                CurrentInvoice.getPayments().get(i).setPrice( previousPrice - ItemPrice );
//                CurrentInvoice.updatePayedAmount(ItemPrice);
//
//                FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/PaymentGrid.fxml"));
//
//
//
//                //Take the services GridPane
//                GridPane pane = ItemLoader.load();
//
//                pane.setId(String.valueOf(currentPayment.getPaymentId()));
//
//                //Load the services and the guis data on the Service controller
//
//
//                pane.setGridLinesVisible(true);
//
//
//                //Load the services elements into the Gui GridPane
//                pane.add(new Label(currentPayment.getPaymentDate().toString()),0,0);
//                pane.add(new Label(String.valueOf(currentPayment.getPaymentId())),1,0);
//                pane.add(new Label(String.valueOf(currentPayment.getPrice())), 2, 0);
//                pane.add(new Label(currentPayment.getNotes()), 3, 0);
//                PaymentsBox.getChildren().add(pane);
//                WriteToDatabase writer = new WriteToDatabase();
//                writer.UpdatePayment(currentPayment.getPaymentId(),currentPayment.getPrice());
//
//                return;
//            }
//        }
//
//    }



}
