package Controllers;

import Entities.AboveGod;
import Entities.Worker;
import Methods.WriteToDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Worker_add_Controller implements AboveGod {

    @FXML
    private VBox vbc;

    @FXML
    private Button addWorker;

    @FXML
    private TextField NameWorker;

    @FXML
    private TextField EmailWorker;

    private Pane pane;
    private StackPane Stackpane;


    public void SetBox(VBox box) {
        this.vbc=box;
    }

    public void setWorkerPane(Pane pane){
        this.pane=pane;
    }

    public void setWorkerStackPane(StackPane Stackpane){
        this.Stackpane=Stackpane;
    }


    public void addWorker() throws IOException, ClassNotFoundException, SQLException {


        String name=NameWorker.getText();
        String email=EmailWorker.getText();
        Worker temp=new Worker(workerMap.size()+1,name,email);

        workerMap.put(temp.getWorkerid(),temp);

        System.out.println("Added : "+ workerMap.get(temp.getWorkerid()));

        HBox box;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Worker_Item.fxml"));

        box = loader.load();

        Edit_Controller control = loader.getController();

        control.SetStackArea(Stackpane);

        ((Label)box.getChildren().get(1)).setText( name);

        ((Label)box.getChildren().get(2)).setText(email);

        ((Label)box.getChildren().get(3)).setText(String.valueOf(temp.getWorkerid()));

        box.setId(String.valueOf(temp.getWorkerid()));

        control = loader.getController();

        control.setPane(pane);

         vbc.getChildren().add(box);



        WriteToDatabase wr=new WriteToDatabase();

        wr.addWorker(temp);


    }


    @FXML
    public void pressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if(event.getSource()==addWorker)
        {
            addWorker();
        }

    }


}