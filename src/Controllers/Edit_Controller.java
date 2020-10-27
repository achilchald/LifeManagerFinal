package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Entities.Linker;
import Methods.WriteFile;

import Methods.WriteToDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.IOException;
import java.sql.SQLException;

/*
This Controller is responsible for for the Customer HBox gui element
If the Customer Hbox is clicked it opens the Customer edit gui
where the user can change things in the customer such as basic data
and invoices,domains etc
 */
public class Edit_Controller implements AboveGod {


    @FXML
    private Button Edit;

    @FXML
    private Button EditC;

    @FXML
    private HBox Hbc;

    @FXML
    private HBox itemC;

    @FXML
    private Button Del;

    @FXML
    private Button DelC;

    @FXML
    private Button Add_Dom;

    @FXML
    public Pane addPane;

    @FXML
    public Pane DomsPane;

    public Pane InvPane;

    @FXML
    public Pane EditPane;

    @FXML
    private Label TotalIncome;

    @FXML
    private Label CustomerPrice;

    @FXML
    private Label Customerid;


    //----- Kwstas Fxml stuffs ----


    @FXML
    private VBox VBoxToDo;

    @FXML
    private VBox PnWorkerItems;

    @FXML
    private HBox WorkerItem;

    @FXML
    private Button AddToDoBtn;

    @FXML
    private HBox HBoxToDo;


    //-----------------------------


    public void SetEditArea (Pane EditArea){this.EditPane = EditArea;}


    //This method sets the is of the price label of the customer
    //So as to be able to be utilised by other controllers
    public void SetPriceLabelId(){
        CustomerPrice.setId(Customerid.getText()+CustomerPrice.getId());
        System.out.println("Customer id = "+Customerid.getText());
        System.out.println("Customer Price Label Id = "+CustomerPrice.getId());
        Linker link = new Linker();
        link.CreateLink(CustomerPrice);

    }

    public void setPane(Pane addPane){
        this.addPane = addPane;
    }

    //todo Na kleinei kai na apo8ikeyei to arxeio
    public void Edit_Customer(MouseEvent event) throws IOException  {


        //Load the customer edit GUI
        FXMLLoader EditLoader = new FXMLLoader(getClass().getResource("../fxml/CustomerInfo.fxml"));
        Parent root = EditLoader.load();



        //Append a controller to the GUI
        Editor CustomerEditor = EditLoader.getController();
        CustomerEditor.SetEditArea(itemC,TotalIncome);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add Item");
        stage.show();



    }


    //todo delete has bugs
    public void Delete_Customer(ActionEvent event) throws IOException {
        if (event.getSource() == DelC) {
            VBox ri = (VBox) itemC.getParent();
            String temp = itemC.getId();


            String id= (((Label) itemC.getChildren().get(4)).getText());
            System.out.println("Domain  : " +id);

            System.out.println("Id searching for : " + id);


            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }
    }


    //---- Kwstas stuffs ----

    public void Edit_Worker(MouseEvent event) throws  IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Edit_Worker.fxml"));

        Parent root = loader.load();
        Editor ctrl = loader.getController();



        ctrl.setWorkerBox(WorkerItem,this.addPane);

    }

    public void deleteWorker() throws SQLException, ClassNotFoundException {

        VBox ri = (VBox) WorkerItem.getParent();

        String temp = WorkerItem.getId();

        System.out.println("Temp id = "+temp);

        //Delete the Worker from the map

        //Delete operation

        String id=WorkerItem.getId();


        System.out.println("Worker Id searching for : " + id);
//
        WriteToDatabase deleter=new WriteToDatabase();

        deleter.deleteWorker(id);


        //Delete the box
        ri.getChildren().remove(ri.lookup("#" + temp));
    }


    //todo Na kleinei kai na apo8ikeyei to arxeio
    public void Edit_Project(MouseEvent event) throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Edit_Project.fxml"));

        Parent root = loader.load();
        Editor ctrl = loader.getController();
        ctrl.SetBox1(Hbc);

        //The following both lines are the only addition we need to pass the arguments
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Editor");
        stage.show();


    }


    public void Delete_Project(ActionEvent event) throws IOException,  SQLException, ClassNotFoundException {


        if (event.getSource() == Del) {

            VBox ri = (VBox) Hbc.getParent();

            String temp = Hbc.getId();

            System.out.println("Temp id = "+temp);

            //Delete the Project Entry

            //Delete operation

            String id = (((Label) Hbc.getChildren().get(5)).getText());

            System.out.println("Project Id searching for : " + id);

            WriteToDatabase deleter=new WriteToDatabase();

            deleter.deleteProject(Integer.valueOf(id));



            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }
    }





    //todo Na kleinei kai na apo8ikeyei to arxeio

}
