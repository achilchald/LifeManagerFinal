package Controllers;

import Entities.AboveGod;
import Methods.Read_Database;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Dashboard implements Initializable, AboveGod {

    @FXML
    private PieChart pie;

    @FXML
    private AreaChart areaChart;

    @FXML
    private Pane calendarPane;

    @FXML
    private Label customers;

    @FXML
    private Label projects;

    @FXML
    private Label workers;

    @FXML
    private Label tasks;

    @FXML
    private VBox NotificationsBox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        customers.setText(String.valueOf(customerMap.size()));
        projects.setText(String.valueOf(projectMap.size()));
        workers.setText(String.valueOf(workerMap.size()));

        Read_Database rd = new Read_Database();
        int i=0;

        try {
            i = rd.totalTasks();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        tasks.setText(String.valueOf(i));

        DatePicker datePicker = new DatePicker(LocalDate.now());
       // DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
       // Node popupContent = datePickerSkin.getPopupContent();
        LocalDate selectedDate = datePicker.getValue();

       // popupContent.setStyle("-fx-pref-width: 298px;");

      //  calendarPane.getChildren().add(popupContent);

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New Value: " + newValue);

            Parent root = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_appointment.fxml"));

            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            appointment_add_Controller cntrl;

            cntrl = loader.getController();

            cntrl.setDate(newValue.toString());
            cntrl.loadAppointments();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Set Appointment");
            stage.show();

        });


        // statisticsPie: Inovice type,


        // Changing random data after every 1 second.
        Timeline PieTimeline = new Timeline();

        PieTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
            int i=1;
            @Override
            public void handle(ActionEvent actionEvent) {
                setPieChart(i);
                if (i==1){
                    i=2;
                }else if(i==2){
                    i=3;
                }else {
                    i=1;
                }

            }
        }));
        // Repeat indefinitely until stop() method is called.
        PieTimeline.setCycleCount(Animation.INDEFINITE);
        PieTimeline.setAutoReverse(true);
        PieTimeline.play();


        // Changing random data after every 1 second.
        Timeline timeline = new Timeline();

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
            int i=1;
            @Override
            public void handle(ActionEvent actionEvent) {
                setAreaChart(i);

                if (i==1){
                    i=2;
                }else if(i==2){
                    i=3;
                }else {
                    i=1;
                }

            }
        }));
        // Repeat indefinitely until stop() method is called.
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();




    }

    private void setPieChart(int i) {

        switch (i) {

            case 1:
                pie.setTitle("Economy");
                ObservableList<PieChart.Data> pieData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Income",15),
                            new PieChart.Data("Expenses",5),
                            new PieChart.Data("Tax",25),
                            new PieChart.Data("Workers' expenses",32),
                            new PieChart.Data("Payments",23));
                pie.setData(pieData);
                break;

            case 2:
                pie.setTitle("Domains hosting");
                ObservableList<PieChart.Data> pieData2 =
                        FXCollections.observableArrayList(
                                new PieChart.Data("COM",25),
                                new PieChart.Data("GR",15),
                                new PieChart.Data("EDU",20),
                                new PieChart.Data("GOV",40));

                pie.setData(pieData2);
                break;

            case 3:
                pie.setTitle("Clients");
                ObservableList<PieChart.Data> pieData3 =
                        FXCollections.observableArrayList(
                                new PieChart.Data("New Clients",50),
                                new PieChart.Data("Old Clients",30),
                                new PieChart.Data("Clients left",20));
                pie.setData(pieData3);
                break;
        }
    }

    public void setAreaChart(int i) {
        switch (i){

            case 1:
                areaChart.setTitle("Expenses vs Income");

                XYChart.Series seriesIncome= new XYChart.Series();
                seriesIncome.setName("Income");
                seriesIncome.getData().add(new XYChart.Data("Monday", 4));
                seriesIncome.getData().add(new XYChart.Data("Tuesday", 10));
                seriesIncome.getData().add(new XYChart.Data("Wednesday", 15));
                seriesIncome.getData().add(new XYChart.Data("Thursday", 23));
                seriesIncome.getData().add(new XYChart.Data("Friday", 25));
                seriesIncome.getData().add(new XYChart.Data("Saturday", 32));
                seriesIncome.getData().add(new XYChart.Data("Sunday", 27));

                XYChart.Series seriesExpenses = new XYChart.Series();
                seriesExpenses.setName("Expenses");
                seriesExpenses.getData().add(new XYChart.Data("Monday", 2));
                seriesExpenses.getData().add(new XYChart.Data("Tuesday", 7));
                seriesExpenses.getData().add(new XYChart.Data("Wednesday", 18));
                seriesExpenses.getData().add(new XYChart.Data("Thursday", 24));
                seriesExpenses.getData().add(new XYChart.Data("Friday", 26));
                seriesExpenses.getData().add(new XYChart.Data("Saturday", 30));
                seriesExpenses.getData().add(new XYChart.Data("Sunday", 25));

                areaChart.getData().removeAll(areaChart.getData());
                areaChart.getData().addAll(seriesIncome, seriesExpenses);
                break;

            case 2:

                areaChart.setTitle("Clients left vs new clients");

                XYChart.Series seriesLeft= new XYChart.Series();
                seriesLeft.setName("Clients left");
                seriesLeft.getData().add(new XYChart.Data("Monday", 3));
                seriesLeft.getData().add(new XYChart.Data("Tuesday", 8));
                seriesLeft.getData().add(new XYChart.Data("Wednesday", 13));
                seriesLeft.getData().add(new XYChart.Data("Thursday", 20));
                seriesLeft.getData().add(new XYChart.Data("Friday", 21));
                seriesLeft.getData().add(new XYChart.Data("Saturday", 17));
                seriesLeft.getData().add(new XYChart.Data("Sunday", 30));

                XYChart.Series seriesNew = new XYChart.Series();
                seriesNew.setName("New clients");
                seriesNew.getData().add(new XYChart.Data("Monday", 4));
                seriesNew.getData().add(new XYChart.Data("Tuesday", 10));
                seriesNew.getData().add(new XYChart.Data("Wednesday", 12));
                seriesNew.getData().add(new XYChart.Data("Thursday", 16));
                seriesNew.getData().add(new XYChart.Data("Friday", 21));
                seriesNew.getData().add(new XYChart.Data("Saturday", 24));
                seriesNew.getData().add(new XYChart.Data("Sunday", 35));

                areaChart.getData().removeAll(areaChart.getData());
                areaChart.getData().addAll(seriesLeft, seriesNew);
                break;

            case 3:
                areaChart.setTitle("Domains GR vs COM");

                XYChart.Series GR= new XYChart.Series();
                GR.setName("Domains GR");
                GR.getData().add(new XYChart.Data("Monday", 40));
                GR.getData().add(new XYChart.Data("Tuesday", 46));
                GR.getData().add(new XYChart.Data("Wednesday", 38));
                GR.getData().add(new XYChart.Data("Thursday", 52));
                GR.getData().add(new XYChart.Data("Friday", 64));
                GR.getData().add(new XYChart.Data("Saturday", 58));
                GR.getData().add(new XYChart.Data("Sunday", 32));

                XYChart.Series COM = new XYChart.Series();
                COM.setName("Domains COM");
                COM.getData().add(new XYChart.Data("Monday", 35));
                COM.getData().add(new XYChart.Data("Tuesday", 63));
                COM.getData().add(new XYChart.Data("Wednesday", 72));
                COM.getData().add(new XYChart.Data("Thursday", 75));
                COM.getData().add(new XYChart.Data("Friday", 56));
                COM.getData().add(new XYChart.Data("Saturday", 64));
                COM.getData().add(new XYChart.Data("Sunday", 25));

                areaChart.getData().removeAll(areaChart.getData());
                areaChart.getData().addAll(GR, COM);
                break;

        }

    }
}
