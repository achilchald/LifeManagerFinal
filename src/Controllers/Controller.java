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

    @FXML
    private Button btnHome;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnExit;

    @FXML
    private StackPane Stackpane;

    @FXML
    private Button btnWorkers;

    @FXML
    private AnchorPane MainAnchor;

    private Pane prj;
    private Pane cust;
    private Pane Items_sp;
    private Pane Workers_sp;
    private Pane home;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainAnchor.setPrefSize(1450, 760);


        prj = new Pane();
        try {
            prj = FXMLLoader.load(getClass().getResource("/fxml/Projects.fxml"));
            prj.setId("project");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(prj);

        cust = new Pane();
        try {
            cust = FXMLLoader.load(getClass().getResource("/fxml/Customers.fxml"));
            cust.setId("customers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(cust);

        Items_sp = new Pane();
        try {
            Items_sp = FXMLLoader.load(getClass().getResource("/fxml/Items.fxml"));
            Items_sp.setId("items");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Items_sp);

        Workers_sp = new Pane();
        try {
            Workers_sp = FXMLLoader.load(getClass().getResource("/fxml/Workers.fxml"));
            Workers_sp.setId("workers");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(Workers_sp);

        home = new Pane();
        try {
            home = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
            home.setId("home");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Stackpane.getChildren().add(home);

        home.toFront();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void handleClicks(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnHome) {
            home.toFront();
        }
        if(actionEvent.getSource()==btnProjects) {
            prj.toFront();
        }
        if (actionEvent.getSource() == btnCustomers) {
            cust.toFront();
        }
        if(actionEvent.getSource()==btnItems) {
            Items_sp.toFront();
        }
        if(actionEvent.getSource()== btnWorkers) {
            Workers_sp.toFront();
        }
        if(actionEvent.getSource()==btnExit) {
            closeButtonAction();
        }

    }
}