package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Entities.Item;
import Methods.Database_Sorter;
import Methods.Read_Database;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ReadFromFile;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Items implements Initializable, AboveGod {

    @FXML
    private VBox pnItems;

    @FXML
    public Pane EditArea;

    @FXML
    private StackPane pnlItem;

    @FXML
    private Button Add;

    @FXML
    private Label ITEM_NAME;

    @FXML
    private Label PRICE;

    @FXML
    private Label RECCURING;

    private int SortingFlag;
    int isPressed = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        Read_Database reader = new Read_Database();

        try {
            reader.Load_Items();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        for (Map.Entry<String,Item> entry : ItemsMap.entrySet()){

            try {

                Item item = entry.getValue();
                HBox box;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemForItems.fxml"));
                box = loader.load();

                Edit_Controller ctrl = loader.getController();
                ctrl.SetItemHbox(box);
                ctrl.SetItemsContainer(pnItems);

                //give the items some effect
                box.setId(item.getId());

                ((Label)box.getChildren().get(0)).setText(item.getType());

                ((Label)box.getChildren().get(1)).setText(String.valueOf(item.getPrice()));

                ((Label)box.getChildren().get(2)).setText(item.getRecurring());

                pnItems.getChildren().add(box);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void pressed() {
        if (isPressed==0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_item.fxml"));

            Parent root = null;

            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            item_add_Controller cntrl = loader.getController();

            cntrl.SetBox(pnItems);

            cntrl.SetEditArea(EditArea);
            cntrl.setItemStackPane(pnlItem);

            EditArea.getChildren().setAll(root);

            new SlideInLeft(EditArea).play();
            isPressed = 1;
            Add.setText("Cancel");

        }else {

            new SlideOutLeft(EditArea).play();
            isPressed = 0;
            Add.setText("Add Item");

        }

    }


    @FXML
    public void SortItems(MouseEvent event) throws SQLException, ClassNotFoundException, InterruptedException {

        String SortingType = null;

        Database_Sorter sorter = new Database_Sorter();

        pnItems.getChildren().clear();
        if (SortingFlag == 0) {
            SortingType = "DESC";
            SortingFlag = 1;
        } else if (SortingFlag == 1) {
            SortingType = "ASC";
            SortingFlag = 0;
        }


        List<String> SortedItems = sorter.GetSortedItems(((Label) event.getSource()).getId(), SortingType);

        for (int i = 0; i < SortedItems.size(); i++) {

            //entry.getValue().calculatePrice();
            try {

                String id = SortedItems.get(i);

                HBox box;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemForItems.fxml"));
                box = loader.load();

                Edit_Controller ctrl = loader.getController();
                ctrl.SetItemHbox(box);
                ctrl.SetItemsContainer(pnItems);

                //give the items some effect
                box.setId(id);
                ((Label)box.getChildren().get(0)).setText(ItemsMap.get(id).getType());

                ((Label)box.getChildren().get(1)).setText(String.valueOf(ItemsMap.get(id).getPrice()));

                ((Label)box.getChildren().get(2)).setText(ItemsMap.get(id).getRecurring());

                pnItems.getChildren().add(box);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
