package calendar;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    // Get the pane to put the calendar on
    @FXML Pane calendarPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());

    }


}
