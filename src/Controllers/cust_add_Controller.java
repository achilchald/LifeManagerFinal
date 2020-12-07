package Controllers;

import Entities.*;
import Methods.Read_Database;
import Methods.WriteFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cust_add_Controller extends Globals implements AboveGod {

    @FXML
    private TextField NameC;

    @FXML
    private TextField ZIP;

    @FXML
    private TextField Country;

    @FXML
    private TextField Address;

    @FXML
    private TextField City;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Email;

    @FXML
    private TextField AFM;

    @FXML
    private Button AddCustomer;

    //-----Helper Variables-----
    private VBox CustomerBox;

    private Pane EditArea;

    private StackPane Stackpane;

    public void SetCustomerVbox(VBox CustomerBox)
    {
        this.CustomerBox = CustomerBox;
    }
    public void SetEditArea(Pane EditPane){
        this.EditArea = EditPane;
    }

    public void setCustomerStackPane(StackPane Stackpane){
        this.Stackpane=Stackpane;
    }

    @FXML
    void pressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        LastCustomerId++;
        Customer NewCustomer = new Customer(
                String.valueOf(LastCustomerId),
                NameC.getText(),
                Country.getText(),
                City.getText(),
                Address.getText(),
                ZIP.getText(),
                Phone.getText(),
                Email.getText(),
                AFM.getText());

        Read_Database adder = new Read_Database();
        adder.AddCustomer(NewCustomer);

        NewCustomer.setPrice(0);
        NewCustomer.calculatePrice();


        //Add Customer to customers map
        customerMap.put(String.valueOf( LastCustomerId ), NewCustomer );

        HBox box;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Item.fxml"));

        box = loader.load();

        Edit_Controller control = loader.getController();


        control.SetEditArea(EditArea);

        control.SetStackArea(Stackpane);


        //give the items some effect

        ((Label)box.getChildren().get(1)).setText(NewCustomer.getName());

        ((Label)box.getChildren().get(3)).setText( String.valueOf(NewCustomer.getPrice()));

        ((Label)box.getChildren().get(4)).setText( String.valueOf(NewCustomer.getPayedAmount()));

        ((Label)box.getChildren().get(5)).setText( String.valueOf(NewCustomer.getDueAmount()));

        //A hidden box with a customer id
        ((Label)box.getChildren().get(6)).setText(NewCustomer.getId());

        control.SetPriceLabelId();
        box.setId(NewCustomer.getId());

        CustomerBox.getChildren().add(box);

    }

}
