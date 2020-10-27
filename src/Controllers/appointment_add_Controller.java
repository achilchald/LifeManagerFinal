package Controllers;

import Entities.Appointment;
import Methods.WriteFile;
import calendar.ReadAppointments;
import calendar.WriteFileAppointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class appointment_add_Controller {

    @FXML
    public HBox itemAppointment;

    @FXML
    public AnchorPane wholePanel;

    @FXML
    private TextField Title;

    @FXML
    private TextField Time;

    @FXML
    private TextField Attendee;

    @FXML
    private TextArea Notes;

    @FXML
    private Button AddB;

    @FXML
    private RadioButton Important;

    @FXML
    private RadioButton Regular;

    @FXML
    private RadioButton Unimportant;

    @FXML
    private VBox pnItems;

    @FXML
    private Label Date;

    @FXML
    private Button EditC;

    @FXML
    private Button DelC;

    @FXML
    private Label Importance;

    private String importance="";

    @FXML
    private Label Ldate;

    private String date="";

    public ArrayList<Appointment> BringAppointments(){
        ReadAppointments appointments = new ReadAppointments();
        return appointments.ReadFile("src\\Csv\\appointments.csv");
    }

    public void loadAppointments() {

        ArrayList<Appointment> myAppointments = BringAppointments();

        for (Appointment appointment : myAppointments){

            if( getDate().equals( appointment.getDate() ) ) {
                try {

                    HBox box;
                    box = FXMLLoader.load(getClass().getResource("../fxml/Appointment.fxml"));

                    //give the items some effect

                    ((Label) box.getChildren().get(0)).setText(appointment.getTitle());

                    ((Label) box.getChildren().get(1)).setText(appointment.getTime());

                    ((Label) box.getChildren().get(2)).setText(appointment.getImportance());

                    ((Label) box.getChildren().get(5)).setText(appointment.getAttendee());

                    ((Label) box.getChildren().get(6)).setText(appointment.getNotes());

                    ((Label) box.getChildren().get(7)).setText(appointment.getDate());

                    pnItems.getChildren().add(box);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void setDate(String date) {
        this.date = date;

        Date.setText("on " + date);
    }

    public String getDate(){
        return this.date;
    }

    public void setImportant(){
        System.out.println("--->"+Important.getId());

        if (importance.equals(Important.getId())){
            importance = "";
        }else {
            importance = Important.getId();
        }

    }

    public void setRegular(){
        System.out.println("--->"+Regular.getId());

        if (importance.equals(Regular.getId())){
            importance = "";
        }else {
            importance = Regular.getId();
        }
    }

    public void setUnimportant(){
        System.out.println("--->"+Unimportant.getId());

        if (importance.equals(Unimportant.getId())){
            importance = "";
        }else {
            importance = Unimportant.getId();
        }
    }


    public void add_Appointment(ActionEvent event ) throws IOException{

        if (event.getSource() == AddB) {

            String title = Title.getText();
            System.out.println("aaAA"+title);

            String time = Time.getText();
            System.out.println("aaAA"+time);

            String attendee = Attendee.getText();
            System.out.println("aaAA"+attendee);

            String notes = Notes.getText();
            System.out.println("aaAA"+notes);

            String date = getDate();
            System.out.println("aaAA"+date);

            // get a handle to the stage
            Stage stage = (Stage) AddB.getScene().getWindow();

            // do what you have to do
            Appointment appointment = new Appointment(title, time, date, attendee, notes, importance);

            WriteFileAppointment wr = new WriteFileAppointment();

            wr.SaveAppointmentAdded(title, time, date, attendee, notes, importance);


            stage.close();
        }


    }


    public void edit_Appointment(ActionEvent event ) throws IOException{


        if (event.getSource() == EditC) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/edit_appointment.fxml"));

            Parent root = loader.load();
            Editor ctrl = loader.getController();
            ctrl.SetBox3(itemAppointment);


            //The following both lines are the only addition we need to pass the arguments


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editor");
            stage.show();


        }
    }

    public void delete_Appointment(ActionEvent event ) throws IOException {

        if (event.getSource() == DelC) {

            VBox ri = (VBox) itemAppointment.getParent();
            String temp = itemAppointment.getId();

            ri.getChildren().remove(ri.lookup("#"+temp));

            WriteFileAppointment wr = new WriteFileAppointment();

            String title = (((Label) itemAppointment.getChildren().get(0)).getText());
            wr.DeleteAppointment(title);

        }
    }



}
