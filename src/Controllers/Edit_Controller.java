package Controllers;

import Entities.AboveGod;
import Entities.Customer;
import Entities.Linker;
import Entities.Task;
import Methods.Database_Deleter;
import Methods.WriteFile;

import Methods.WriteToDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private HBox ToDoItem;

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

    private Label completed;

    private Label notCompleted;

    private ProgressBar bar;

    private Label progressLabel;



    //-----------------------------


    public void SetEditArea (Pane EditArea){this.EditPane = EditArea;}


    //This method sets the is of the price label of the customer
    //So as to be able to be utilised by other controllers
    public void SetPriceLabelId(){
        CustomerPrice.setId(Customerid.getText()+CustomerPrice.getId());
        Linker link = new Linker();
        link.CreateLink(CustomerPrice);

    }

    public void setPane(Pane addPane){
        this.addPane = addPane;
    }

    //todo Na kleinei kai na apo8ikeyei to arxeio
    public void Edit_Customer(MouseEvent event) throws IOException  {


        //Load the customer edit GUI
        FXMLLoader EditLoader = new FXMLLoader(getClass().getResource("/fxml/CustomerInfo.fxml"));
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
    public void Delete_Customer(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (event.getSource() == DelC) {
            VBox ri = (VBox) itemC.getParent();
            String temp = itemC.getId();
            Database_Deleter deleter = new Database_Deleter();


            String id= (((Label) itemC.getChildren().get(4)).getText());


            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));

            deleter.Delete_Customer(customerMap.get(Customerid.getText()));

            customerMap.remove(Customerid.getText());



        }
    }


    //---- Kwstas stuffs ----

    public void Edit_Worker(MouseEvent event) throws  IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Edit_Worker.fxml"));

        Parent root = loader.load();
        Editor ctrl = loader.getController();



        ctrl.setWorkerBox(WorkerItem,this.addPane);

    }

    public void deleteWorker() throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete this worker?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {

            VBox ri = (VBox) WorkerItem.getParent();

            String temp = WorkerItem.getId();


            //Delete the Worker from the map

            //Delete operation

            String id = WorkerItem.getId();

            WriteToDatabase deleter = new WriteToDatabase();

            deleter.deleteWorker(id);

            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }
    }


    public void Edit_Project(MouseEvent event) throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Edit_Project.fxml"));

        Parent root = loader.load();
        Editor ctrl = loader.getController();
        ctrl.SetBox1(Hbc);

        //The following both lines are the only addition we need to pass the arguments
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Editor");
        stage.show();


    }


    public void Delete_Project(ActionEvent event) throws SQLException, ClassNotFoundException {


        if (event.getSource() == Del) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete this project?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                VBox ri = (VBox) Hbc.getParent();

                String temp = Hbc.getId();


                //Delete the Project Entry

                //Delete operation

                String id = (((Label) Hbc.getChildren().get(5)).getText());

                WriteToDatabase deleter = new WriteToDatabase();

                deleter.deleteProject(Integer.parseInt(id));


                //Delete the box
                ri.getChildren().remove(ri.lookup("#" + temp));
            }
        }
    }

    public void Edit_Task(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskInfo.fxml"));

        Parent root = loader.load();
        Editor ctrl = loader.getController();

        ctrl.setTaskInfo(ToDoItem);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Task");
        stage.show();

    }

    public void setLabel(Label completed,Label notCompleted,ProgressBar bar,Label progress){

        this.completed = completed;

        this.notCompleted = notCompleted;

        this.bar=bar;

        this.progressLabel=progress;


    }

    public void setStatus() throws SQLException, ClassNotFoundException {
        int ProjectId = Integer.parseInt(((Label) ToDoItem.getChildren().get(7)).getText());
        int WorkerId = Integer.parseInt(((Label) ToDoItem.getChildren().get(5)).getText());
        int IndexOfTask =  Integer.parseInt(((Label) ToDoItem.getChildren().get(6)).getText());
        int taskId= Integer.parseInt(ToDoItem.getId());


        projectMap.get(ProjectId).getWorkers().get(WorkerId).getTasks().get(ProjectId).get(IndexOfTask).changeStatus();

        boolean status=projectMap.get(ProjectId).getWorkers().get(WorkerId).getTasks().get(ProjectId).get(IndexOfTask).getStatus();

        int completed_tasks= Integer.parseInt(completed.getText());
        int pending_tasks= Integer.parseInt(notCompleted.getText());
        double total=completed_tasks+pending_tasks;

        if (status) {
            ToDoItem.getChildren().get(8).setStyle("-fx-background-color: #34eb37; ");//set to green


            completed_tasks++;
            pending_tasks--;

            completed.setText(String.valueOf(completed_tasks));
            notCompleted.setText(String.valueOf(pending_tasks));

            double progress=completed_tasks/total;

            bar.setProgress(progress);

            progressLabel.setText(String.valueOf(Math.round(progress*100)));




        }
        else {
            ToDoItem.getChildren().get(8).setStyle("-fx-background-color: #e0e0e0; ");
            completed_tasks--;
            pending_tasks++;

            completed.setText(String.valueOf(completed_tasks));
            notCompleted.setText(String.valueOf(pending_tasks));

            double progress=completed_tasks/total;

            bar.setProgress(progress);

            progressLabel.setText(String.valueOf(progress*100));
        }
        System.out.println("Task id= "+taskId);
        WriteToDatabase wr = new WriteToDatabase();
        wr.changeTaskStatus(status,taskId);

    }

    @FXML
    public void DeleteTask() throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete this task?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {

            VBox ri = (VBox) ToDoItem.getParent();

            String temp = ToDoItem.getId();

            int WorkerId = Integer.parseInt(((Label) ToDoItem.getChildren().get(5)).getText());

            int ProjectId = Integer.parseInt(((Label) ToDoItem.getChildren().get(7)).getText());

            int IndexOfTask =  Integer.parseInt(((Label) ToDoItem.getChildren().get(6)).getText());

           


            projectMap.get(ProjectId).getWorkers().get(WorkerId).getTasks().get(ProjectId).remove(IndexOfTask);







            //Delete the Worker from the map

            //Delete operation

            String id = ToDoItem.getId();

            Database_Deleter deleter = new Database_Deleter();

            deleter.Delete_Task(Integer.parseInt(id));

            //Delete the box
            ri.getChildren().remove(ri.lookup("#" + temp));
        }

    }



}
