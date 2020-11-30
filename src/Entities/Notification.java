package Entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Notification {
    String data;
    int projId;
    int taskId;
    int workerId;

   public Notification(String data,int projId,int taskId,int workerId){
        this.data=data;
        this.projId=projId;
        this.taskId=taskId;
        this.workerId=workerId;
    }

    public Notification(String data){
       this.data=data;
    }

    public void addNotification(VBox notificationBox,HBox Hbc) throws IOException {
        Label txt=new Label(this.data);
        notificationBox.getChildren().add(txt);


        HBox notification;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../fxml/NotificationItem.fxml"));
        notification = loader2.load();
        notification.getChildren().add(txt);
        txt.setPrefHeight(Region.USE_PREF_SIZE);

        notificationBox.getChildren().add(notification);
    }


}
