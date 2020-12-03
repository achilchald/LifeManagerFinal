package Entities;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LogEvent {
    String data;
    int projId;
    int taskId;
    int workerId;

    public LogEvent(String data,int projId,int taskId,int workerId){
        this.data=data;
        this.projId=projId;
        this.taskId=taskId;
        this.workerId=workerId;
    }

    public LogEvent(String data){
        this.data=data;
    }

    public LogEvent(LogEvent event){
        this.data=event.getData();
        this.workerId=event.getWorkerId();
        this.projId=event.getProjId();
        this.taskId=event.taskId;
    }


    public void addLog(VBox notificationBox) throws IOException {
        Label txt=new Label(this.data);
        notificationBox.getChildren().add(txt);


        HBox notification;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../fxml/NotificationItem.fxml"));
        notification = loader2.load();
        notification.getChildren().add(txt);
        txt.setPrefHeight(Region.USE_PREF_SIZE);



        notificationBox.getChildren().add(notification);


    }

    public void addLog(VBox notificationBox,String id) throws IOException {
        Label txt=new Label(this.data);
        notificationBox.getChildren().add(txt);


        HBox notification;
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../fxml/NotificationItem.fxml"));
        notification = loader2.load();
        notification.getChildren().add(txt);
        txt.setPrefHeight(Region.USE_PREF_SIZE);

        notification.setId(id);



        notificationBox.getChildren().add(notification);


    }



    public int getProjId() {
        return projId;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public String getData() {
        return data;
    }

}
