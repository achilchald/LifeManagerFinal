package Controllers;

import Entities.AboveGod;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Add_Service_Controller implements AboveGod {

    @FXML
    private VBox Services_Box;

    @FXML
    private Button AddServices;

    @FXML
    private VBox  InvoiceBox;

    String id;

    public void SetBox(VBox box,String id)
    {
        this.InvoiceBox = box;
        Load_Services();
        this.id = id;
    }

    public void Load_Services()
    {
        for(int i = 0;i<invoiceTypes.size();i++)
        {
            if (invoiceTypes.get(i).getRecurring().equals("Monthly") || invoiceTypes.get(i).getRecurring().equals("Once") || invoiceTypes.get(i).getRecurring().equals("Per Hour"))
            {
                HBox Service = new HBox();
                Service.setId(Integer.toString(i));
                CheckBox item = new CheckBox(invoiceTypes.get(i).getType());
                item.setId(Integer.toString(i));
                Service.getChildren().add(item);
                Services_Box.getChildren().add(Service);

            }
        }
    }

    @FXML
    void pressed(ActionEvent event) throws IOException
    {

        if (event.getSource() == AddServices)
        {
            for (int i = 0;i<Services_Box.getChildren().size();i++)
            {
                if(((CheckBox)((HBox)Services_Box.getChildren().get(i)).getChildren().get(0)).isSelected())
                {
                    for(int j = 0;j<invoiceTypes.size();j++)
                    {
                        if(invoiceTypes.get(j).getType().equals(((CheckBox)((HBox)Services_Box.getChildren().get(i)).getChildren().get(0)).getText()))
                        {
                            HBox box;

                            box = FXMLLoader.load(getClass().getResource("../fxml/Invoice_Item.fxml"));

                            customerMap.get(id).GetInvoicesList().add(invoiceTypes.get(j));

                            ((Label) box.getChildren().get(1)).setText(invoiceTypes.get(j).getType());

                            ((Label) box.getChildren().get(2)).setText(Float.toString(invoiceTypes.get(j).getPrice()));

                            InvoiceBox.getChildren().add(box);

                        }
                    }
                }
            }


        }
    }


}
