package Controllers;

import Entities.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import Methods.Database_Sorter;
import Methods.Read_Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ReadFromFile;

public class Projects_Controller  extends Globals implements Initializable ,AboveGod  {

    @FXML
    private StackPane pnlProjects;

    @FXML
    private Label ActiveProjectsPanel;

    @FXML
    private Label CompletedProjectsPanel;

    @FXML
    private Label ClosestDeadlinePanel;

    @FXML
    private Label TotalRevenuePanel;

    @FXML
    private Button Add;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane addProjectPane;

    @FXML
    private AnchorPane TabPaneAnchor;

    @FXML
    private TabPane TabProject;

    @FXML
    public Button Edit;


    @FXML
    private Label PROJECT_NAME;

    @FXML
    private Label DUE_DATE;

    @FXML
    private Label PRICE;

    @FXML
    private Label WORKFORCE;

    private int SortingFlag = 0;





    public int counter = 0;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {


        int i=0;
        //ArrayList<Customer> list =rd.ReadFile("src\\Csv\\customers.csv");

        //projectMap=rd.ReadFileProjects("LifeManager/src/Csv/Projects_test.csv");
        Read_Database reader=new Read_Database();

        try {
            reader.LoadProjects();
            reader.LoadWorkers();
            reader.LoadProjectWorkers();
            reader.LoadWorkerTasks();
            reader.Load_Category();

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //Linker for the labels
        Linker linker = new Linker();

        for (Map.Entry<Integer, Project>entry:projectMap.entrySet()){
            entry.getValue().truesetWorkforce();
            try {

                HBox box;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Project_Item.fxml"));

                box = loader.load();

                Edit_Controller temp = loader.getController();

                temp.SetStackArea(pnlProjects);

                //Make the items
                ((Label)box.getChildren().get(1)).setText(entry.getValue().getName());

                ((Label)box.getChildren().get(2)).setText(entry.getValue().getDueDate().toString());

                ((Label)box.getChildren().get(3)).setText(String.valueOf(entry.getValue().getPrice()));

                ((Label)box.getChildren().get(4)).setText(String.valueOf(entry.getValue().getWorkforce()));

                ((Label)box.getChildren().get(5)).setText(String.valueOf(entry.getValue().getId()));


                //Set the box id with its project id.
                box.setId(String.valueOf(entry.getValue().getId()));

                pnItems.getChildren().add(box);

                setCounter(i);
                i++;


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        ActiveProjectsPanel.setText(String.valueOf(projectMap.size()));

        ClosestDeadlinePanel.setText(String.valueOf(findClosestDeadLine()));
        colourDead();

        TotalRevenuePanel.setText(String.valueOf(totalRevenue()));

    }

    public long findClosestDeadLine(){
        long min=100000000;
        for (Map.Entry<Integer, Project>entry:projectMap.entrySet()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline_date = LocalDate.parse(entry.getValue().getDueDate().toString(), dtf);
            LocalDate currentDate = LocalDate.now();
            long timeToDeadLine = ChronoUnit.DAYS.between(currentDate, deadline_date);
            if (timeToDeadLine<min)
                min=timeToDeadLine;
        }
        return min;
    }


    public int totalRevenue(){
        int price=0;
        for (Map.Entry<Integer, Project>entry:projectMap.entrySet()){
            price+=entry.getValue().getPrice();
        }
        return price;
    }


    public void colourDead(){
        if (findClosestDeadLine()>12){
            ClosestDeadlinePanel.setTextFill(Color.web("#12de00"));
        }
        if (findClosestDeadLine()>6&&findClosestDeadLine()<10){
            ClosestDeadlinePanel.setTextFill(Color.web("#dade00"));
        }
        if (findClosestDeadLine()<6){
            ClosestDeadlinePanel.setTextFill(Color.web("#de0000"));
        }
    }


    @FXML
    public void Add_Project(ActionEvent event) throws IOException {
        if (event.getSource() == Add)
        {
            int count=0;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_project.fxml"));

            Parent root = loader.load();
            proj_add_Controller ctrl = loader.getController();
            ctrl.SetBox(pnItems,count,this.TabPaneAnchor);

            ctrl.setProjectStackPane(pnlProjects);

            setCounter(counter+1);

        }

    }


    @FXML
    public void SortProjects(MouseEvent event) throws SQLException, ClassNotFoundException, InterruptedException {

        Database_Sorter sorter = new Database_Sorter();

        String SortingType = null;

        pnItems.getChildren().clear();
        if(SortingFlag == 0)
        {
            SortingType = "DESC";
            SortingFlag = 1;
        }
        else if(SortingFlag == 1)
        {
            SortingType = "ASC";
            SortingFlag = 0;
        }


        List<Integer> SortedProjects = sorter.GetSortedProjects(((Label)event.getSource()).getId(),SortingType);

        for (int i = 0; i<SortedProjects.size();i++) {

            try {
                HBox box;

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Project_Item.fxml"));

                box = loader.load();

                Edit_Controller temp = loader.getController();

                temp.SetStackArea(pnlProjects);

                int id = SortedProjects.get(i);

                //Make the items
                ((Label)box.getChildren().get(1)).setText(projectMap.get(id).getName());

                ((Label)box.getChildren().get(2)).setText(projectMap.get(id).getDueDate().toString());

                ((Label)box.getChildren().get(3)).setText(String.valueOf(projectMap.get(id).getPrice()));

                ((Label)box.getChildren().get(4)).setText(String.valueOf(projectMap.get(id).getWorkforce()));

                ((Label)box.getChildren().get(5)).setText(String.valueOf(projectMap.get(id).getId()));


                //Set the box id with its project id.
                box.setId(String.valueOf(projectMap.get(id).getId()));

                pnItems.getChildren().add(box);

                setCounter(i);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}


