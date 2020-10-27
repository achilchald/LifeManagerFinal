package Controllers.InvoiceItemController;


import Entities.AboveGod;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class InvoiceItemControll  implements AboveGod {

    @FXML
    private GridPane ItemGrid;


    public void BuildItemGrid(String CustomerId,GridPane pane)
    {

        String name = customerMap.get(CustomerId).getName();
        Label nameLabel = new Label(name);
        ItemGrid.add(nameLabel,0,0);
        pane.add(ItemGrid,0,0);

    }

}
