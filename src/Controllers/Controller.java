package Controllers;
import Entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
//import sample.ReadFromFile;
//This is commited
//import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //home projects calendar customers email exit
    @FXML
    private Pane pnlProjects = null;

    @FXML
    private VBox pnItems = null;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnCalendar;

    @FXML
    private Button btnEmails;

    @FXML
    private Button btnExit;

    @FXML
    private Pane pnlCustomers;

    @FXML
    private Pane pnlHome;

    @FXML
    private Pane paneItems;

    @FXML
    private Pane pnlEmails;

    @FXML
    private Pane pnlExit;

    @FXML
    private Pane pnlCalendar;

    @FXML
    private StackPane Stackpane;

    @FXML
    private Pane pnlWorkers;

    @FXML
    private Button btnWorkers;

    @FXML
    private AnchorPane MainAnchor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pnlHome.setStyle("-fx-background-color : #50b065"); //green
        //pnlEmails.setStyle("-fx-background-color : #ffffff"); //white
        MainAnchor.setPrefWidth(1280);

        Pane prj = new Pane();
        try {
            prj = FXMLLoader.load(getClass().getResource("/fxml/Projects.fxml"));
            prj.setId("project");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(prj);

        Pane cust = new Pane();
        try {
            cust = FXMLLoader.load(getClass().getResource("/fxml/Customers.fxml"));
            cust.setId("customers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(cust);

        Pane Calendar_sp = new Pane();
        try {
            Calendar_sp = FXMLLoader.load(getClass().getResource("/fxml/fullCalendar.fxml"));
            Calendar_sp.setId("calendar");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Calendar_sp);

        Pane Items_sp = new Pane();
                try {
                    Items_sp = FXMLLoader.load(getClass().getResource("/fxml/Items.fxml"));
                    Items_sp.setId("items");

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stackpane.getChildren().add(Items_sp);

        Pane Workers_sp = new Pane();
        try {
            Workers_sp = FXMLLoader.load(getClass().getResource("/fxml/Workers.fxml"));
            Workers_sp.setId("workers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Workers_sp);


        pnlHome.toFront();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            Stackpane.lookup("#customers").toFront();
        }
        if (actionEvent.getSource() == btnHome) {
            pnlHome.toFront();
        }
        if (actionEvent.getSource() == btnCalendar) {
            Stackpane.lookup("#calendar").toFront();
        }
        if(actionEvent.getSource()==btnProjects)
        {
            Stackpane.lookup("#project").toFront();
        }

        if(actionEvent.getSource()==btnItems)
        {
            Stackpane.lookup("#items").toFront();
        }


        if(actionEvent.getSource()== btnWorkers)
        {
            Stackpane.lookup("#workers").toFront();
        }

        if(actionEvent.getSource()==btnExit)
        {
            closeButtonAction();
        }

        if(actionEvent.getSource()==btnEmails)
        {
            pnlEmails.toFront();
        }

    }
}