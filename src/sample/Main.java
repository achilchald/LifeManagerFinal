package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        double width = resolution.getWidth();
        double height = resolution.getHeight();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds.getHeight()+ " " +screenBounds.getWidth());
        double w = width/screenBounds.getWidth();
        double h = height/screenBounds.getHeight();
        Scale scale = new Scale(w, h, 0, 0);
        root.getTransforms().add(scale);
        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        root.prefWidth(screenBounds.getWidth());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
