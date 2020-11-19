package Controllers;

import Controllers.InvoiceItemController.InvoiceGuiController;
import Entities.AboveGod;
import Entities.Customer;
import Entities.Linker;
import Methods.Database_Deleter;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import org.apache.commons.lang3.StringUtils;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

/*
This controller class is responsible for handling the options selected in the invoice GUI element
that is contained in the Edit Customer GUI
 */
public class Invoice_Editing_Controller implements AboveGod,Initializable {

//-----Invoice Item Data-----
@FXML
private Pane pnlCustomers;

    @FXML
    private HBox InvoiceItem;

    @FXML
    private Label nameid;

    @FXML
    private Label BillingDate;

    @FXML
    private Label Price;

    @FXML
    private Label PayedAmount;

    @FXML
    private ComboBox<String> Options_Box;

    private String CustomerId;

    private int InvoiceId;

    private Label TotalIncome;

    private VBox InvoiceContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    //Set the customer and invoice id's to the controller as well ass the HBox id(that contains the invoice)
    public void setCustomerAndInvoiceId(String CustomerId,int InvoiceId)
    {
        this.CustomerId = CustomerId;
        this.InvoiceId = InvoiceId;
        this.InvoiceItem.setId(String.valueOf(InvoiceId));
    }

    public void SetIncomeLabel(Label Income){this.TotalIncome = Income;}

    //Set the vbox that the invoice is contained in(YearlyBox,MonthlyBox etc)
    public void SetContainer(VBox InvoiceContainer){this.InvoiceContainer = InvoiceContainer;}


    @FXML
    void OptionSelected(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //This is triggered if the user selects the edit option in the Invoice HBox
        if(Options_Box.getValue().equals("Edit"))
        {
            //Options_Box.getSelectionModel().clearSelection();
            //Load the Invoice summary GUI panel
            FXMLLoader LoadEditGui = new FXMLLoader(getClass().getResource("/fxml/Invoice_Summary.fxml"));
            Parent root = LoadEditGui.load();
            //Append a controller to the panel so as to apply any changes made
            InvoiceGuiController GuiControll = LoadEditGui.getController();
            GuiControll.LoadItems(CustomerId,InvoiceId,Price,TotalIncome,PayedAmount);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editor");
            stage.show();


        }
        //This is triggered if the user selects the delete invoice option
        if(Options_Box.getValue().equals("Delete"))
        {
            //Create a Database Deleter entity so as to delete the invoice from the database
            //as well as any entries in the tables that contain its Invoice ID
            Database_Deleter deleter = new Database_Deleter();
            //Delete the invoice from the database
            deleter.Delete_Invoice(InvoiceId);
            //Delete the invoice from its container VBox
            InvoiceContainer.getChildren().remove(InvoiceContainer.lookup("#"+InvoiceId));

            //Create a linker so as to apply changes to the customer price label as well as the total income label
            Linker link = new Linker();

            //Get the values of the customer price label and total income label
            String Tcost = link.GetLabelLink("IncomeLabel").getText();
            Tcost = Tcost.substring(0 , Tcost.length() - 1);
            float TotalCost = Float.parseFloat(Tcost);
            float CustomerCost = Float.parseFloat(link.GetLabelLink(CustomerId+"CustomerPrice").getText());


            //Apply the changes to the aforementioned labels and remove the invoice from the customer map
            for(int i = 0;i<customerMap.get(CustomerId).GetInvoicesList().size();i++)
            {
                if(customerMap.get(CustomerId).GetInvoicesList().get(i).getId() == InvoiceId)
                {
                    TotalCost = TotalCost - customerMap.get(CustomerId).GetInvoicesList().get(i).getPrice();
                    CustomerCost = CustomerCost - customerMap.get(CustomerId).GetInvoicesList().get(i).getPrice();

                    link.GetLabelLink("IncomeLabel").setText(String.valueOf(TotalCost));
                    link.GetLabelLink(CustomerId+"CustomerPrice").setText(String.valueOf(CustomerCost));

                    customerMap.get(CustomerId).GetInvoicesList().remove(i);
                }
            }

        }

    }












}
