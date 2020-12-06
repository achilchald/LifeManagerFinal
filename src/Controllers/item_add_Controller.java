package Controllers;

import Entities.*;
import Methods.Read_Database;
import Methods.WriteFile;
import calendar.WriteFileAppointment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class item_add_Controller extends Globals implements AboveGod {

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

    @FXML
    public Label ItemName;

    @FXML
    public AnchorPane editItemPane;


    @FXML
    private Button EditButton;

    private VBox ItemContainer;

    private HBox ItemBox;

    private Item CurrentItem;
    public Pane EditArea;
    public StackPane Stackpane;

    @FXML
    public AnchorPane wholePanel;

    @FXML
    public StackPane pnlItem;


    public void SetBox(VBox box)
    {
        this.pnItems = box;
    }

    public void SetHbox(HBox box)
    {
        this.ItemBox = box;
    }

    public void SetEditArea(Pane EditPane){ this.EditArea = EditPane; }

    public void setItemStackPane(StackPane Stackpane){
        this.Stackpane=Stackpane;
    }

    public void SetEditItemPane(AnchorPane EditPane){ this.editItemPane = EditPane; }


    public void add_Item(javafx.event.ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (event.getSource() == AddB) {

            int id = Integer.parseInt(LastTemplatedItemId);
            id++;
            LastTemplatedItemId = String.valueOf(id);

            Read_Database Reader = new Read_Database();

            String type = Type.getText();

            String price = Price.getText();

            String recurring = Recurring.getText();

            // get a handle to the stage
            Stage stage = (Stage) AddB.getScene().getWindow();

            // do what you have to do
            Item item = new Item(String.valueOf(id) , type , recurring , Float.valueOf(price));

            Reader.AddItem(item);


            HBox box;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemForItems.fxml"));
            box = loader.load();
            box.setId(String.valueOf(id));

            Edit_Controller ctrl = loader.getController();
            ctrl.SetItemHbox(box);
            ctrl.SetItemsContainer(pnItems);

            //give the items some effect

            ((Label)box.getChildren().get(0)).setText(item.getType());

            ((Label)box.getChildren().get(1)).setText(String.valueOf(item.getPrice()));

            ((Label)box.getChildren().get(2)).setText(item.getRecurring());

            pnItems.getChildren().add(box);

            ItemsMap.put(item.getId(),item);

            stage.close();
        }
    }


    public void Initialize() {

        String id = ItemBox.getId();

        CurrentItem = ItemsMap.get(id);

        Type.setText(CurrentItem.getType());
        Price.setText(String.valueOf(CurrentItem.getPrice()));
        Recurring.setText(CurrentItem.getRecurring());

    }


    @FXML
    public void UpdateItem() throws SQLException, ClassNotFoundException {
        CurrentItem.setType(Type.getText());
        CurrentItem.setPrice( Float.parseFloat(Price.getText()) );
        CurrentItem.setRecurring(Recurring.getText());


        ((Label)ItemBox.getChildren().get(0)).setText(CurrentItem.getType());

        ((Label)ItemBox.getChildren().get(1)).setText(String.valueOf(CurrentItem.getPrice()));

        ((Label)ItemBox.getChildren().get(2)).setText(CurrentItem.getRecurring());

        Read_Database reader = new Read_Database();
        reader.UpdateItem(CurrentItem);

    }

}
