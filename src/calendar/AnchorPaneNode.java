package calendar;

import Controllers.appointment_add_Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> pressed());

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void pressed(){
        Parent root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_appointment.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        appointment_add_Controller cntrl;

        cntrl = loader.getController();

        cntrl.setDate(getDate().toString());
        cntrl.loadAppointments();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Set Appointment");
        stage.show();


    }

}
