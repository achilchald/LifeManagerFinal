package Controllers;

import Entities.AboveGod;
import Entities.Worker;
import Methods.Read_Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;



public class Workers_Controller  implements Initializable,AboveGod {

    @FXML
    private Pane pnlWorkers;

    @FXML
    private Label TotalWorkersLabel;

    @FXML
    private Label AvailableWorkersLabel;

    @FXML
    private Label BusyWorkersLabel;

    @FXML
    private Button Add;

    @FXML
    private VBox pnWorkerItems;

    @FXML
    private Pane addWorkerPane;

    @FXML
    private Pane domainsPane;

    @FXML
    private Pane invoicesPane;

    @FXML
    private Button mailbtn;

    @FXML
    private Button AddWorker;




    public int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void initialize(URL location, ResourceBundle resources) {

        int i = 0;

        Read_Database reader = new Read_Database();

        try {
            reader.LoadProjects();
            reader.LoadWorkers();
            reader.LoadProjectWorkers();
            reader.LoadWorkerTasks();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (Map.Entry<Integer, Worker> entry : workerMap.entrySet()) {
            try {

                HBox box;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Worker_Item.fxml"));

                box = loader.load();

                Edit_Controller control = loader.getController();

                control.setPane(addWorkerPane);


                ((Label) box.getChildren().get(1)).setText(entry.getValue().getName());


                ((Label) box.getChildren().get(2)).setText(entry.getValue().getEmail());

                ((Label) box.getChildren().get(3)).setText(String.valueOf(entry.getValue().getWorkerid()));


                box.setId(String.valueOf(entry.getValue().getWorkerid()));

                pnWorkerItems.getChildren().add(box);

                setCounter(i);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        TotalWorkersLabel.setText(String.valueOf(workerMap.size()));
//
//        ClosestDeadlinePanel.setText(String.valueOf(findClosestDeadLine()));
//        colourDead();
//
//        TotalRevenuePanel.setText(String.valueOf(totalRevenue()));


    }


    @FXML
    public void addWorker(ActionEvent event) throws IOException {
        if(event.getSource()==AddWorker){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_worker.fxml"));

            Parent root = loader.load();
            Worker_add_Controller ctrl = loader.getController();

            ctrl.SetBox(pnWorkerItems);

            ctrl.setWorkerPane(addWorkerPane);


            setCounter(counter+1);

            addWorkerPane.getChildren().setAll(root);

        }
    }


    //sendEmail(){

}