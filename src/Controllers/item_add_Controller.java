package Controllers;

import Entities.AboveGod;
import Entities.Appointment;
import Entities.Invoice;
import Methods.WriteFile;
import calendar.WriteFileAppointment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entities.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class item_add_Controller implements AboveGod {

    @FXML
    private TextField Type;
    @FXML
    private TextField Price;
    @FXML
    private TextField Recurring;
    @FXML
    private Button AddB;
    @FXML
    private VBox pnItems;


    public void SetBox(VBox box)
    {
        this.pnItems = box;
    }


    public void add_Item(javafx.event.ActionEvent event) throws IOException {

        if (event.getSource() == AddB) {

            String type = Type.getText();

            String price = Price.getText();

            String recurring = Recurring.getText();

            // get a handle to the stage
            Stage stage = (Stage) AddB.getScene().getWindow();

            // do what you have to do
            Item item = new Item(type, Float.valueOf(price), recurring);

            WriteFile wr = new WriteFile();

            wr.SaveItemAdded(type, price, recurring);


            HBox box;
            box = FXMLLoader.load(getClass().getResource("../fxml/ItemForItems.fxml"));

            //give the items some effect

            ((Label)box.getChildren().get(0)).setText(item.getType());

            ((Label)box.getChildren().get(1)).setText(String.valueOf(item.getPrice()));

            ((Label)box.getChildren().get(2)).setText(item.getRecurring());

            pnItems.getChildren().add(box);

            invoiceTypes.add(new Invoice(item.getType(),Integer.parseInt(String.valueOf(item.getPrice())),item.getRecurring()));

            stage.close();
        }
    }

}
