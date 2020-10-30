package Controllers.InvoiceItemController;

import Entities.AboveGod;
import Entities.Item;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Add_ItemController implements AboveGod {
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
    private int InvoiceID;
    private ArrayList<Item> ItemList;
    private VBox ItemsList;
    private int maxId;
    private Label cost;
    private Label TotalIncome;
    private String ItemId;

    void InitItemsAdder(VBox pane, ArrayList<Item> Items, int Invoiceid, int MaxId, Label Price, Label TotalIncome)
    {

        this.InvoiceID = Invoiceid;
        this.ItemList = Items;
        this.ItemsList = pane;
        this.maxId = MaxId;
        this.cost = Price;
        this.TotalIncome = TotalIncome;

        for (Map.Entry<String,Item>entry : ItemsMap.entrySet())
        {
            ItemListBox.getItems().addAll(entry.getValue().getType());
        }

    }

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

    @FXML
    void TakeItem(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        FXMLLoader ItemLoader = new FXMLLoader(getClass().getResource("/fxml/ItemGrid.fxml"));



        Item temp = new Item(ItemsMap.get(ItemId));


        ItemList.add(temp);

        GridPane pane = ItemLoader.load();
        EditItemController ctrl = ItemLoader.getController();
        maxId++;
        pane.setId(String.valueOf(maxId));

        ctrl.SetInvoiceID(InvoiceID);
        ctrl.SetItemId(temp.getType());
        ctrl.SetIdAndItemsBox(pane.getId(),ItemsList);
       // ctrl.SetPriceLabels(cost,TotalIncome,Cu);
        ctrl.SetItemList(ItemList);

        pane.setGridLinesVisible(true);

        pane.add(new Label(temp.getType()), 0, 0);
        pane.add(new Label("0"), 1, 0);
        pane.add(new Label(String.valueOf(temp.getPrice())), 2, 0);


        ItemsList.getChildren().add(pane);

        cost.setText(String.valueOf( Float.parseFloat(cost.getText()) + temp.getPrice())  );
        String TCost = TotalIncome.getText();
        //TCost = StringUtils.chop(TCost);

        TotalIncome.setText(String.valueOf( Float.parseFloat(TCost) + temp.getPrice() ) + "$");

        Read_Database updater = new Read_Database();
        //updater.UpdateInvoice(InvoiceID,ItemId);
    }

    }

