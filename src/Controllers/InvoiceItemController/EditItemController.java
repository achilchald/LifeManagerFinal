package Controllers.InvoiceItemController;

import Entities.AboveGod;
import Entities.Invoice;
import Entities.Item;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
//import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/*
This Controller is responsible fot the Service Items Inside the Invoice
It is responsible for getting the Service and marking it as payed or editing it
 */
public class EditItemController implements AboveGod {

    @FXML
    private Button PayItem;

    @FXML
    private Button DeleteItem;

    private String ItemName;

    @FXML
    private GridPane MyGrid;

    private VBox ItemsList;

    private int InvoiceId;

    private Label Price;

    private Label TotalIncome;

    private Label CustomerCost;

    private ArrayList<Item> InvoiceItems;

    private VBox PaymentsBox;

    private float price;

    private Invoice CurrentInvoice;

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

    //Get the total income and invoice price labels so that they can be updated when a Service is added or removed or payed
    void SetPriceLabels(Label InvoicePrice,Label TotalIncome,Label CustomerCost)
    {
        this.Price = InvoicePrice;
        this.TotalIncome = TotalIncome;
        this.CustomerCost = CustomerCost;
    }

    //Get the Id of the current Service you are viewing
    void SetItemId(String name)
    {
        this.ItemName = name;
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

    @FXML//Make the appropriate changes if a service is deleted or payed
    void pressed(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (event.getSource() == DeleteItem)
        {

            System.out.println("Item to be deleted = "+ItemName);
            String ItemID = null;

            for (int i = 0;i<CurrentInvoice.getItems().size();i++)
            {
                if (CurrentInvoice.getItems().get(i).getType().equals(ItemName))
                {
                    ItemID = CurrentInvoice.getItems().get(i).getId();
                    String TCost = TotalIncome.getText();
                    //TCost = StringUtils.chop(TCost);
                    TotalIncome.setText(String.valueOf( Float.parseFloat(TCost) - CurrentInvoice.getItems().get(i).getPrice() ) + "$");
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

                }
            }

            Read_Database deleter = new Read_Database();

            deleter.RemoveItem(InvoiceId,ItemID);

            ItemsList.getChildren().remove(ItemsList.lookup("#"+Myid));

        }
        if (event.getSource() == PayItem)
        {
            System.out.println("Item to be payed = " + ItemName);
            String ItemID = null;

            //Get the type of the item that is being payed
            //Todo Make a copy of the item in the controller so as to remove the lines bellow this comment
            for (Map.Entry<String, Item> entry : ItemsMap.entrySet())
            {
                if (entry.getValue().getType().equals(ItemName))
                {
                    ItemID = entry.getValue().getId();
                    price = entry.getValue().getPrice();
                    //Update the total cost label in the MainGui
                    String TCost = TotalIncome.getText();
                    //TCost = StringUtils.chop(TCost);
                    TotalIncome.setText(String.valueOf( Float.parseFloat(TCost) - entry.getValue().getPrice() ) + "$");
                    Price.setText(String.valueOf(Float.parseFloat(Price.getText()) - entry.getValue().getPrice() ));
                }
            }

            //Mark the item as payed and apply that current date
            //Todo Make a copy of the item in the controller so as to remove the lines bellow this comment
            Date PaymentDate = Date.valueOf(LocalDate.now());
            System.out.println("Item List size = "+InvoiceItems.size());
            for (int i = 0;i<InvoiceItems.size();i++)
            {
                if (InvoiceItems.get(i).getId().equals(ItemID))
                {
                    InvoiceItems.get(i).SetPayed(true);
                    InvoiceItems.get(i).setPayedDate(PaymentDate);
                }
            }





            //Load the Services elements into a gui grid and put it into the payments Vbox
            FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/PaymentGrid.fxml"));
           MyGrid = ItemLoader.load();
           MyGrid.setGridLinesVisible(true);
            ItemLoader.setController(this);
            MyGrid.add(new Label(PaymentDate.toString()),0,0);
            MyGrid.add(new Label(ItemID),1,0);
            MyGrid.add(new Label(ItemName), 2, 0);
            MyGrid.add(new Label(String.valueOf(price)), 3, 0);

            //Update the database
            Read_Database markPayed = new Read_Database();
            markPayed.PayItem(ItemID,InvoiceId,PaymentDate);

            //Remove from the unpayed Items VBox
            PaymentsBox.getChildren().add(MyGrid);
            ItemsList.getChildren().remove(ItemsList.lookup("#"+Myid));



        }

    }
}
