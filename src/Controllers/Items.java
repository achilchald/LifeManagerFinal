package Controllers;

import Entities.Customer;
import Entities.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.ReadFromFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class Items implements Initializable {

    @FXML
    private VBox pnItems;

    @FXML
    private Button AddB;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        ReadFromFile rd=new ReadFromFile();

        ArrayList<Item> items =rd.ReadItemsFile("src\\Csv\\InvoiceItems.csv");

        for (Item item : items){

            try {

                HBox box;
                box = FXMLLoader.load(getClass().getResource("../fxml/ItemForItems.fxml"));

                //give the items some effect

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
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_item.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        item_add_Controller cntrl;

        cntrl = loader.getController();

        cntrl.SetBox(pnItems);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Item");
        stage.show();
    }


}
