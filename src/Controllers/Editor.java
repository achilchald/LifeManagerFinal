package Controllers;

import Controllers.DomainController.AddDomainController;
import Controllers.InvoiceItemController.AddInvoiceController;
import Entities.*;
import Methods.Database_Deleter;
import Methods.Read_Database;
import Methods.WriteFile;
import Methods.WriteToDatabase;
//import animatefx.animation.*;
import calendar.WriteFileAppointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
This controller is responsible for handling and applying changes the user does to the customer and its data
It calls other controllers, each one responsible for doing different things to the customer entity
 */
public class Editor extends Globals implements AboveGod {

    @FXML
    private Label progressLabel;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField DateF;

    @FXML
    private TextField WorkforceF;

    @FXML
    private TextField NameC;


    @FXML
    private Button EditB;


    @FXML
    private HBox Hbc;



    @FXML
    private TextField Title;

    @FXML
    private TextField Time;

    @FXML
    private TextField Attendee;

    @FXML
    private TextArea Notes;

    @FXML
    private Button Change;

    @FXML
    private RadioButton Important;

    @FXML
    private RadioButton Regular;

    @FXML
    private RadioButton Unimportant;

    @FXML
    private TextField date;



    @FXML
    private TabPane EditPane;
    //------- Customer Data ------
    @FXML
    private TextField NameF;

    @FXML
    private TextField ZIP;

    @FXML
    private TextField Country;

    @FXML
    private TextField Address;

    @FXML
    private TextField City;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Email;

    @FXML
    private TextField AFM;
    //-------------------

    //-------   Domains Data --------
    @FXML
    public ComboBox<String>  DomList;

    @FXML
    private TextField DomName;

    @FXML
    private ComboBox<String> HostingType;

    @FXML
    private ComboBox<String> DomainType;

    @FXML
    private Button AddCustomer;

    @FXML
    private TextField ExpDate;

    @FXML
    private Label taskProjectID;

    @FXML
    private Pane DomainPane;

    private int DomainId;

    private boolean DeleteFlag = false;

    //Invoice Gui Data

    @FXML
    private Pane InvoicePane;

    @FXML
    private VBox OneTimeBox;

    @FXML
    private VBox MonthlyBox;

    @FXML
    private VBox YearlyBox;

    @FXML
    private TextField InvoiceId;

    @FXML
    private ComboBox<?> Services;

    @FXML
    private TextField BillDate;

    @FXML
    private AnchorPane AddDomainPane;

    @FXML
    private Button AddDomain;

    @FXML
    private VBox CustomBox;

    @FXML
    private Label projectIdLabel;

    @FXML
    private Label pendingTasksLabel;

    @FXML
    private Label completedTasksLabel;


    @FXML
    private TextField DomainC;

    @FXML
    private TextField PriceC;

    @FXML
    private Button EditC;

    @FXML
    private Label TaskWorkerID;

    @FXML
    private VBox Dom_Box;

    @FXML
    private VBox Invoice_Box;

    @FXML
    private Button Add_Dom;


    @FXML
    private CheckBox Small;

    @FXML
    private CheckBox Medium;

    @FXML
    private CheckBox Large;

    @FXML
    private DatePicker Domain_Date;

    @FXML
    private Button Update_Customer;


    @FXML
    private Button Add_Services;

    @FXML
    private Pane megaPane;

    @FXML
    private VBox VBoxToDo;

    @FXML
    private AnchorPane handleTasksPane;


    @FXML
    private TextField NameWorker;

    @FXML
    private TextField PhoneWorker;

    @FXML
    private TextField EmailWorker;

    @FXML
    private Label WorkerId;

    @FXML
    private VBox ProjectWorkersPanel;

    @FXML
    private VBox ProjectToDoPanel;

    @FXML
    private TextField nametask;

    @FXML
    private TextArea desctask;

    @FXML
    private TextField nametaskpr;

    @FXML
    private TextArea desctaskpr;

    @FXML
    private ComboBox<String> workerCombo;

    @FXML
    private ComboBox<String> projectCombo;

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Button AddT;

    @FXML
    private Button ProjBtnBack;

    @FXML
    private Button WorkerBtnBack;

    @FXML
    private Button CustomerBtnBack;

    @FXML
    private Button finish;

    @FXML
    private Label workerId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label idLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ComboBox<String>workerComboProject;

    @FXML
    private ComboBox<String>taskCategory;

    //Update Buttons
    @FXML
    private Button UpdateCustomer;

    @FXML
    private Button UpdateDomain;

    @FXML
    private Button AddInvoice;

    //Handle stray task fxmls
    @FXML
    private ComboBox<String>workersComboHandle;

    @FXML
    private Label strayTaskId;

    @FXML
    private Label workerIDstray;

    @FXML
    private Label NameTask;

    @FXML
    private TextField CategoryF;

    @FXML
    private TextField StatusF;

    @FXML
    private Label index;

    @FXML
    private TextArea DescF;

    @FXML
    private TextField BelongProjectF;

    @FXML
    private TextField AssignedWorkerF;

    //Temporary variables

    private int DomainInvoiceId;

    //Previous Domain Invoice Values
    private String hostingType;

    private String domainType;

    //Helper Variables
    private String importance = "";

    private String id;

    public Map<String,Domain> CustomerDomains = new HashMap<>();

    Label TotalIncome;


    int strayTasksCounter=0;
    int strayTasksCounterHelp=0;

    int flag = 0;

    @FXML
    private Button DeleteDomain;



    //SetBox 1 sets the projects view thing.
    public void SetBox1(HBox hb) throws IOException, SQLException, ClassNotFoundException {
        this.Hbc = hb;
        NameF.setText(((Label) Hbc.getChildren().get(1)).getText());
        DateF.setText(((Label) Hbc.getChildren().get(2)).getText());
        WorkforceF.setText(((Label) Hbc.getChildren().get(3)).getText());
        PriceF.setText(((Label) Hbc.getChildren().get(4)).getText());
        int completedTasks=0;
        int notCompletedTask=0;



        String projectid = ((Label) Hbc.getChildren().get(5)).getText();
        projectIdLabel.setText(projectid);



        //workers inside the specific clicked project
        HashMap<Integer, Worker> workersInside = projectMap.get(Integer.valueOf(projectid)).getWorkers();
        //tasks inside the worker in the clicked project


        //here you fill the workers hbox with its workers
        for (Map.Entry<Integer, Worker> entry : workersInside.entrySet()) {
            //box for workers

            HBox box;
            box = FXMLLoader.load(getClass().getResource("../fxml/Worker_Item_NoClick.fxml"));


            ((Label) box.getChildren().get(1)).setText(entry.getValue().getName());
            ((Label) box.getChildren().get(2)).setText(entry.getValue().getEmail());
            ((Label) box.getChildren().get(3)).setText(String.valueOf(entry.getValue().getWorkerid()));

            box.getChildren().remove(4);

            box.setId(String.valueOf(entry.getValue().getWorkerid()));

            Button minus=new Button("-");
            box.getChildren().add(minus);
            minus.setOnAction(e -> {
                try {
                    deleteWorkerFromProject(entry.getValue().getWorkerid());
                } catch (SQLException | ClassNotFoundException | IOException throwables) {
                    throwables.printStackTrace();
                }
            });

            box.setId(String.valueOf(entry.getValue().getWorkerid()));

            //the workers task lists
            //HashMap<Integer, ArrayList<Task>> tasksInside = entry.getValue().getTasks();
            HashMap<Integer,ArrayList<Task>> tasksInside = projectMap.get(Integer.valueOf(projectid)).getWorkers().get(entry.getValue().getWorkerid()).getTasks();


            //Here the project todos are loaded
            for (Map.Entry<Integer, ArrayList<Task>> workerTasks : tasksInside.entrySet()) {
                if (workerTasks.getKey().equals(Integer.valueOf(projectid))) {
                    // a local copy of the Array list inside
                    ArrayList<Task> temp = workerTasks.getValue();

                    for (int i = 0; i < temp.size(); i++) {
                        //box for to do
                        HBox box2;

                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../fxml/ToDoItem.fxml"));



                        box2 = loader.load();
                        ((Label) box2.getChildren().get(1)).setText(temp.get(i).getName());
                        ((Label) box2.getChildren().get(2)).setText(String.valueOf(entry.getValue().getName()));
                        ((Label) box2.getChildren().get(3)).setText(temp.get(i).getDescription());
                        System.out.println("Worker id : "+entry.getValue().getWorkerid());

                        ((Label) box2.getChildren().get(5)).setText(String.valueOf(entry.getValue().getWorkerid()));
                        System.out.println("Index is : "+i);
                        ((Label) box2.getChildren().get(6)).setText(String.valueOf(i));
                        ((Label) box2.getChildren().get(7)).setText(projectid);

                        boolean status2=temp.get(i).getStatus();
                        //boolean status=projectMap.get(Integer.parseInt(projectid)).getWorkers().get(temp.get(i).getWorker_id()).getTasks().get(Integer.parseInt(projectid)).get(i).getStatus();
                        if (status2) {
                            box2.getChildren().get(8).setStyle("-fx-background-color: #34eb37; ");//set to green
                            completedTasks++;
                        }
                        else {
                            box2.getChildren().get(8).setStyle("-fx-background-color: #a7a7a7; ");//set to black
                            notCompletedTask++;
                        }

                        Edit_Controller ctrl=loader.getController();

                        ctrl.setLabel(completedTasksLabel,pendingTasksLabel,progressBar,progressLabel);
                        System.out.println("PRINTED TASK ID : "+temp.get(i).getTaskid());
                        box2.setId(String.valueOf(temp.get(i).getTaskid()));
                        ProjectToDoPanel.getChildren().add(box2);


                    }



                }
            }



            ProjectWorkersPanel.getChildren().add(box);


        }
        //Here we Loop the available workers

        for (Map.Entry<Integer, Worker> currentWorker : workerMap.entrySet()) {
            HBox boxAvailable;
            boxAvailable = FXMLLoader.load(getClass().getResource("../fxml/Worker_Item_NoClick.fxml"));

            //Key for current
            Worker currWorker = currentWorker.getValue();

            if (!workersInside.containsValue(currWorker)) {
                ((Label) boxAvailable.getChildren().get(1)).setText(currentWorker.getValue().getName());
                ((Label) boxAvailable.getChildren().get(2)).setText(currentWorker.getValue().getEmail());
                ((Label) boxAvailable.getChildren().get(3)).setText(String.valueOf(currentWorker.getValue().getWorkerid()));
                boxAvailable.setId(String.valueOf(currentWorker.getValue().getWorkerid()));
                boxAvailable.getChildren().remove(4);

                boxAvailable.setStyle("-fx-background-color : #00ce52;");
                Button plus=new Button("+");
                boxAvailable.getChildren().add(plus);
                plus.setOnAction(e -> {
                    try {
                        addWorkerToProject(currentWorker.getValue().getWorkerid(),projectid,boxAvailable);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });
                ProjectWorkersPanel.getChildren().add(boxAvailable);
            }
        }

        //Sets the Data Labels
        completedTasksLabel.setText(String.valueOf(completedTasks));
        pendingTasksLabel.setText(String.valueOf(notCompletedTask));


        int total_tasks=completedTasks+notCompletedTask;

        double colmple=completedTasks;
        double nocomple=notCompletedTask;

        double progress = (double) colmple/(double)total_tasks;

        System.out.println("Progress = "+progress);

        progressBar.setProgress(progress);

        progressBar.setStyle("-fx-background-color: green;");

        progressLabel.setText(String.valueOf(Math.round(progress*100)));

    }

    public void setToDoBox(VBox box){
        this.VBoxToDo=box;
        String localid= ((Label) Hbc.getChildren().get(5)).getText();
        initComboBoxesProject(Integer.parseInt(localid));
        initComboBoxCategory();


    }

    @FXML
    public void addToDo() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/addTaskToProject.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        setToDoBox(ProjectToDoPanel);

        //initComboBoxesProject(Integer.parseInt(idLabel.getText()));



        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add ToDo to ");
        stage.show();

    }

    public void deleteWorkerFromProject(int workerId) throws SQLException, ClassNotFoundException, IOException {
        //Delete operation

        String projId=projectIdLabel.getText();

        //If the worker is in the project
        if (projectMap.get(Integer.parseInt(projId)).getWorkers().containsKey(workerId)) {
            //And if the worker has no tasks
            if (!projectMap.get(Integer.parseInt(projId)).getWorkers().get(workerId).getTasks().get(Integer.parseInt(projId)).isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This worker has tasks on this project.Do you want to assign them on a different worker?E?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    if (projectMap.get(Integer.parseInt(projId)).getWorkers().size()<2){
                        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "This is the last worker in project ! \n " +
                                "If you want to reassign his tasks so that they won't be deleted consider assigning new worker to the project.",
                                ButtonType.OK);
                        alert2.showAndWait();
                    }else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/handleTasks.fxml"));
                        loader.setController(this);
                        Parent root = loader.load();
                        NameTask.setText(projectMap.get(Integer.parseInt(projId)).getWorkers().get(workerId).getTasks().get(Integer.parseInt(projId)).get(strayTasksCounter).getName());
                        strayTaskId.setText(String.valueOf(projectMap.get(Integer.parseInt(projId)).getWorkers().get(workerId).getTasks().get(Integer.parseInt(projId)).get(strayTasksCounter).getTaskid()));
                        workerIDstray.setText(String.valueOf(workerId));
                        initComboBoxesHandleTask(Integer.parseInt(projId), Integer.parseInt(workerIDstray.getText()));

                        Stage stage = new Stage();
                        stage.setTitle("Handle Tasks on :" + projectMap.get(Integer.parseInt(projId)).getName());
                        stage.setScene(new Scene(root));
                        stage.show();
                    }

                }
                if (alert.getResult() == ButtonType.NO){

                    int size = projectMap.get(Integer.parseInt(projId)).getWorkers().get(workerId).getTasks().get(Integer.parseInt(projId)).size();

                    for (int i = 0; i < size ; i++) {
                        ProjectToDoPanel.getChildren().remove( ProjectToDoPanel.lookup("#" +
                                projectMap.get(Integer.parseInt(projId)).getWorkers().get(workerId).getTasks().get(Integer.parseInt(projId)).get(i).getTaskid()));

                    }

                    projectMap.get(Integer.parseInt(projId)).getWorkers().remove(workerId);
                    WriteToDatabase wr=new WriteToDatabase();
                    wr.deleteWorkerFromProject(String.valueOf(workerId),Integer.parseInt(projId));
                    ProjectWorkersPanel.lookup("#" + workerId).setStyle("-fx-background-color : #50b065;");
                    HBox boxWorkerToDelete=(HBox)ProjectWorkersPanel.lookup("#" + workerId);
                    boxWorkerToDelete.getChildren().remove(4);
                    Button plus=new Button("+");
                    boxWorkerToDelete.getChildren().add(plus);
                    plus.setOnAction(e -> {
                        try {
                            addWorkerToProject(workerId,projId,boxWorkerToDelete);
                        } catch (SQLException | ClassNotFoundException throwables) {
                            throwables.printStackTrace();
                        }
                    });

                }

            }//if empty
            else{
                projectMap.get(Integer.parseInt(projId)).getWorkers().remove(workerId);
                WriteToDatabase wr=new WriteToDatabase();
                wr.deleteWorkerFromProject(String.valueOf(workerId),Integer.parseInt(projId));
                ProjectWorkersPanel.lookup("#" + workerId).setStyle("-fx-background-color : #50b065;");
                HBox boxWorkerToDelete=(HBox)ProjectWorkersPanel.lookup("#" + workerId);
                boxWorkerToDelete.getChildren().remove(4);

                Button plus=new Button("+");
                boxWorkerToDelete.getChildren().add(plus);
                plus.setOnAction(e -> {
                    try {
                        addWorkerToProject(workerId,projId,boxWorkerToDelete);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });
            }
        }
    }

    public void step(String projId) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<Task> straytasks = projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(String.valueOf(workerIDstray.getText()))).getTasks().get(Integer.parseInt(projId));

        //Take all the completed Tasks and handle them




        String worker=workersComboHandle.getSelectionModel().getSelectedItem();

        String id = worker.substring(0, worker.indexOf("."));

        Worker temp = workerMap.get(Integer.parseInt(id));

        if (strayTasksCounter < straytasks.size()) {


            Task test=straytasks.get(strayTasksCounter);
            projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(id)).addTasks(test);

            System.out.println("What is added :" +projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(id)).getTasks().get(Integer.parseInt(projId)).get(strayTasksCounter).getStatus());
            System.out.println("Status of the newly added task is : "+test.getStatus());


//            workerMap.get(Integer.parseInt(id)).addTasks(straytasks.get(strayTasksCounter));

            WriteToDatabase wr = new WriteToDatabase();
            wr.reassignTask(Integer.parseInt(workerIDstray.getText()), Integer.parseInt(id), Integer.parseInt(projId));



            //Handles the visual aspect of the reassign

            HBox box;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ToDoItem.fxml"));

            box = loader.load();

            Edit_Controller ctrl = loader.getController();

            ctrl.setLabel(completedTasksLabel, pendingTasksLabel, progressBar, progressLabel);

            int index = projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(id)).getTasks().get(Integer.parseInt(projId)).size()-1 ;
            //int index =workerMap.get(Integer.parseInt(id)).getTasks().get(Integer.parseInt(projId)).size()-1;

            System.out.println("Index now is + " + index);
            //find the worker name selected and get its id;

            ((Label) box.getChildren().get(1)).setText(straytasks.get(strayTasksCounter).getName());

            ((Label) box.getChildren().get(2)).setText(String.valueOf(temp.getName()));

            ((Label) box.getChildren().get(3)).setText(straytasks.get(strayTasksCounter).getDescription());


            ((Label) box.getChildren().get(5)).setText(id);

            ((Label) box.getChildren().get(6)).setText(String.valueOf(index));

            ((Label) box.getChildren().get(7)).setText(projId);


            ProjectToDoPanel.getChildren().remove(ProjectToDoPanel.lookup("#" + straytasks.get(strayTasksCounter).getTaskid()));

            box.setId(String.valueOf(straytasks.get(strayTasksCounter).getTaskid()));

            Task tasktemp=straytasks.get(strayTasksCounter);
            //Make the status button the corresponding color

            if (straytasks.get(strayTasksCounter).getStatus()){
                box.getChildren().get(8).setStyle("-fx-background-color : #50b065;");
//                Label txt=new Label("The task "+straytasks.get(strayTasksCounter).getName()+" was originally completed by "+workerMap.get(Integer.parseInt(workerIDstray.getText())).getName()+" Man !");
//                ProjectToDoPanel.getChildren().add(txt);
            }
            else{
                box.getChildren().get(8).setStyle("-fx-background-color : #b3b3b3;");
            }




            ProjectToDoPanel.getChildren().add(box);



            box.setId(String.valueOf(straytasks.get(strayTasksCounter).getTaskid()));


        }
        if (strayTasksCounter == straytasks.size() - 1) {
            flag = 1;

            //Empties the worker tasks list inside this project.
            projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(workerIDstray.getText())).getTasks().get(Integer.parseInt(projId)).removeAll(straytasks);

            //removes the worker from the project map worker list.
            projectMap.get(Integer.parseInt(projId)).getWorkers().remove(Integer.parseInt(workerIDstray.getText()));

            //Now remove the worker and change his buttons
            WriteToDatabase wr = new WriteToDatabase();
            wr.deleteWorkerFromProject(workerIDstray.getText(), Integer.parseInt(projId));


            ProjectWorkersPanel.lookup("#" + workerIDstray.getText()).setStyle("-fx-background-color : #50b065;");
            HBox boxWorkerToDelete = (HBox) ProjectWorkersPanel.lookup("#" + workerIDstray.getText());
            boxWorkerToDelete.getChildren().remove(4);



            Button plus = new Button("+");
            boxWorkerToDelete.getChildren().add(plus);
            plus.setOnAction(e -> {
                try {
                    addWorkerToProject(Integer.parseInt(workerIDstray.getText()), projId, boxWorkerToDelete);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            });


        }


        strayTasksCounter++;
        strayTasksCounterHelp++;
    }


    public void addStrayTask(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {


        String projId=projectIdLabel.getText();

        step(projId);
        if (!(flag==1)){
            NameTask.setText(projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(String.valueOf(workerIDstray.getText()))).getTasks().get(Integer.parseInt(projId)).get(strayTasksCounter).getName());
            strayTaskId.setText(String.valueOf(projectMap.get(Integer.parseInt(projId)).getWorkers().get(Integer.parseInt(String.valueOf(workerIDstray.getText()))).getTasks().get(Integer.parseInt(projId)).get(strayTasksCounter).getTaskid()));
        }else{
            flag=0;
            Node  source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            projectMap.get(Integer.parseInt(projId)).getWorkers().remove(Integer.parseInt(String.valueOf(workerIDstray.getText())));
            strayTasksCounter = 0;
            stage.close();

        }

    }


    public void addTaskToProject() throws IOException, SQLException, ClassNotFoundException {
        //nametask may have a bug
        String localid= ((Label) Hbc.getChildren().get(5)).getText();
        String name=nametaskpr.getText();
        LocalDate localDate = datePicker.getValue();
        String category = taskCategory.getSelectionModel().getSelectedItem();
        String worker=workerComboProject.getSelectionModel().getSelectedItem();
        String desc=desctaskpr.getText();


        int id = Integer.parseInt(worker.substring(0, worker.indexOf(".")));
        System.out.println("Id of worker = " + id);

        int catid = Integer.parseInt(category.substring(0, category.indexOf(".")));
        System.out.println("Id of Category = " + id);

        String nameid=category.substring(category.indexOf("."));

        //make the new task
        Task temp=new Task(++lastTaskId,name,desc,Date.valueOf(localDate),false,Integer.parseInt(localid),id,nameid);


        //Now add it

        //workerMap.get(id).addTasks(temp);

        projectMap.get(temp.getProject_id()).getWorkers().get(id).addTasks(temp);

        int i =  projectMap.get(temp.getProject_id()).getWorkers().get(id).getTasks().get(temp.getProject_id()).size()-1;

        //Now the visual



        HBox box;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ToDoItem.fxml"));

        box = loader.load();

        Edit_Controller ctrl = loader.getController();

        ctrl.setLabel(completedTasksLabel, pendingTasksLabel, progressBar, progressLabel);

        ((Label) box.getChildren().get(1)).setText(temp.getName());
        ((Label) box.getChildren().get(2)).setText(workerMap.get(id).getName());
        ((Label) box.getChildren().get(3)).setText(temp.getDescription());
        ((Label) box.getChildren().get(5)).setText(String.valueOf(temp.getWorker_id()));
        ((Label) box.getChildren().get(6)).setText(String.valueOf(i));
        ((Label) box.getChildren().get(7)).setText(String.valueOf(temp.getProject_id()));

        box.setId(String.valueOf(temp.getTaskid()));

        ProjectToDoPanel.getChildren().add(box);

        WriteToDatabase wr=new WriteToDatabase();
        wr.addTaskToProjectDB(temp,id);

        int pend= Integer.parseInt(pendingTasksLabel.getText());

        pend++;

        pendingTasksLabel.setText(String.valueOf(pend));

        int completed_tasks= Integer.parseInt(completedTasksLabel.getText());
        int pending_tasks= Integer.parseInt(pendingTasksLabel.getText());
        double total=completed_tasks+pending_tasks;

        double progress=completed_tasks/total;

        progressBar.setProgress(progress);

        progressLabel.setText(String.valueOf(Math.round(progress*100)));

        //close the window
        Stage stage = (Stage)nametaskpr.getScene().getWindow();

        stage.close();

    }

    public void addWorkerToProject(int workId,String projectid,HBox box) throws SQLException, ClassNotFoundException {

        projectMap.get(Integer.valueOf(projectid)).addWorker(workId);
        projectMap.get(Integer.parseInt(projectid)).getWorkers().get(workId).getTasks().put(Integer.parseInt(projectid),new ArrayList<Task>());
        System.out.println("His list"+projectMap.get(Integer.parseInt(projectid)).getWorkers().get(workId).getTasks().get(Integer.parseInt(projectid)));
        box.setStyle("-fx-background-color : #a0a0a0;");
        box.getChildren().remove(4);
        Button minus=new Button("-");
        box.getChildren().add(minus);
        minus.setOnAction(e -> {
            try {
                deleteWorkerFromProject(Integer.parseInt(box.getId()));
            } catch (SQLException | ClassNotFoundException | IOException throwables) {
                throwables.printStackTrace();
            }
        });

        if(box.getChildren().size()>5) {
            box.getChildren().remove(5);
        }

        WriteToDatabase wr=new WriteToDatabase();
        wr.addWorkerToProjectDB(workerMap.get(workId),Integer.parseInt(projectid));
    }

    @FXML
    public void Add_Edited_Worker(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/add_worker_task.fxml"));
        Parent root = loader.load();
        Editor ctrl = loader.getController();
        ctrl.setAddTaskToWorkerBox(VBoxToDo,workerId);



        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add ToDo to "+workerMap.get(Integer.valueOf(workerId.getText())).getName());
        stage.show();


    }

    public void setAddTaskToWorkerBox(VBox VBoxToDo,Label label) throws IOException {
        this.VBoxToDo=VBoxToDo;
        this.workerId=label;
        initComboBoxesWorker();


    }

    @FXML
    public  void initComboBoxCategory() {

        for (Map.Entry<Integer, String> entry2 :categoryMap.entrySet()) {

            taskCategory.getItems().add(entry2.getKey()+"."+entry2.getValue());
        }
    }
    @FXML
    public  void initComboBoxesHandleTask(int id, int prevID) {
        HashMap<Integer, Worker> workersInProject = projectMap.get(id).getWorkers();
        for (Map.Entry<Integer, Worker> entry2 :workersInProject.entrySet()) {
            if (!(entry2.getValue().getWorkerid() == prevID)) {
                workersComboHandle.getItems().add(entry2.getValue().getWorkerid() + "." + entry2.getValue().getName());
            }
        }
    }

    @FXML
    public  void initComboBoxesProject(int id) {
        HashMap<Integer, Worker> workersInProject = projectMap.get(id).getWorkers();
        for (Map.Entry<Integer, Worker> entry2 :workersInProject.entrySet()) {

            workerComboProject.getItems().add(entry2.getValue().getWorkerid()+"."+entry2.getValue().getName());
        }
    }

    @FXML
    public  void initComboBoxesWorker() {

        for (Map.Entry<Integer, Project> entry2 : projectMap.entrySet()) {



            projectCombo.getItems().add(entry2.getKey()+"."+entry2.getValue().getName());

        }
    }

    public void AddTaskToWorker() throws IOException {

        //Loads the item to be added
        HBox box;
        box = FXMLLoader.load(getClass().getResource("../fxml/ToDoItem.fxml"));
        String name=nametask.getText();
        String desc=desctask.getText();

        //specifies what worker we care about.
        int workid=Integer.parseInt(workerId.getText());



        String selection = projectCombo.getSelectionModel().getSelectedItem();
        //gets the id from the name selected in the combobox
        int projectid = Integer.parseInt(selection.substring(0, selection.indexOf(".")));
        int taskid=projectMap.get(projectid).getWorkers().get(workid).getTasks().size()+1;

        //creates the new Task and temporary saves it.

        Task temptask=new Task(taskid,name,desc,Date.valueOf(LocalDate.now()),false,projectid,workid);
        projectMap.get(projectid).getWorkers().get(workid).addTasks(temptask);

        ((Label) box.getChildren().get(1)).setText(name);
        ((Label) box.getChildren().get(2)).setText("12");
        ((Label) box.getChildren().get(3)).setText(desc);

        VBoxToDo.getChildren().add(box);



    }
    //Closes and SAVES the added task
    public void finishAddTaskToWorker(){

        Stage stage = (Stage)finish.getScene().getWindow();

        stage.close();

    }


    public void setWorkerBox(HBox hb) throws IOException {
        this.Hbc=hb;

        NameWorker.setText(((Label) Hbc.getChildren().get(1)).getText());

        EmailWorker.setText(((Label) Hbc.getChildren().get(2)).getText());

        String id=(((Label) Hbc.getChildren().get(3)).getText());
        workerId.setText(id);

        //box is the local item(workeritem) loaded when adding the existing customers

        //gets the Worker Pressed on
        Worker localWorker=workerMap.get(Integer.valueOf(id));
        //gets localWorkers to do List and parses it

//            for (int i=0;i<localWorker.getTasks().size();i++) {

        for (Map.Entry<Integer, ArrayList<Task>> allTasks : localWorker.getTasks().entrySet()){
            ArrayList<Task> tempTasks=allTasks.getValue();
            for (int i=0;i<tempTasks.size();i++) {

                HBox box;
                box = FXMLLoader.load(getClass().getResource("../fxml/ToDoItem.fxml"));


                ((Label) box.getChildren().get(1)).setText((tempTasks.get(i).getName()));


                int taskonprojectid = tempTasks.get(i).getProject_id();

                ((Label) box.getChildren().get(2)).setText(String.valueOf(projectMap.get(taskonprojectid).getName()));

                VBoxToDo.getChildren().add(box);
            }

        }


    }


    public void SetBox3(HBox hb) {
        this.Hbc = hb;
        Title.setText(((Label) Hbc.getChildren().get(0)).getText());
        Time.setText(((Label) Hbc.getChildren().get(1)).getText());
        Attendee.setText(((Label) Hbc.getChildren().get(5)).getText());
        Notes.setText(((Label) Hbc.getChildren().get(6)).getText());
        date.setText(((Label) Hbc.getChildren().get(7)).getText());

        switch (((Label) Hbc.getChildren().get(2)).getText()) {
            case "Important":
                Important.setSelected(true);
                setImportant();
                break;
            case "Regular":
                Regular.setSelected(true);
                setRegular();
                break;
            case "Unimportant":
                Unimportant.setSelected(true);
                setUnimportant();
                break;
        }
    }


    //This method is responsible for initializing the customer data to the GUI
    public void SetEditArea(HBox CustomerBox,Label TotalIncome) throws IOException {
        //Pass The Customer Box Along With The Edit Area
        this.Hbc = CustomerBox;
        this.TotalIncome = TotalIncome;

        //Get the ID of the selected customer
        id = (((Label) CustomerBox.getChildren().get(4)).getText());

        //Set the customer data to the Text fields
        NameC.setText(((Label) Hbc.getChildren().get(1)).getText());
        Country.setText(customerMap.get(id).getCountry());
        Address.setText(customerMap.get(id).getAddress());
        ZIP.setText(customerMap.get(id).getZip());
        AFM.setText(customerMap.get(id).getAFM());
        City.setText(customerMap.get(id).getCity());
        Email.setText(customerMap.get(id).getEmail());
        Phone.setText(customerMap.get(id).getPhone());



        //Set the customer Domains to the FXML Elements
        for(int i = 0;i<customerMap.get(id).getDomainsList().size();i++)
        {
            DomList.getItems().add(customerMap.get(id).getDomainsList().get(i).Name);
            CustomerDomains.put(customerMap.get(id).getDomainsList().get(i).Name,customerMap.get(id).getDomainsList().get(i));
        }

        //Add to the combo boxes responsible for domain hostings and types the coresponding items
        //This happens so the customer can change these values according to his/her needs
        for(Map.Entry<String,Item>entry : ItemsMap.entrySet())
        {
            if (entry.getValue().getType().equals("HOSTING SMALL") || entry.getValue().getType().equals("HOSTING MEDIUM") ||entry.getValue().getType().equals("HOSTING BIG"))
            {
                HostingType.getItems().add(entry.getValue().getType());

            }
            else if(entry.getValue().getType().equals("DOMAIN.GR") || entry.getValue().getType().equals("DOMAIN.COM"))
            {
                DomainType.getItems().add(entry.getValue().getType());
            }
        }


        //Create a linker so as to generate links to labels that will be updated
        Linker linker = new Linker();

        //Set the customer invoices to the FXML Elements coresponding to them
        for (int i = 0;i<customerMap.get(id).GetInvoicesList().size();i++)
        {
            //Two boxes,one holds the items by its type(yearly,monthly etc)
            //the other is added if the items is reccuring
            HBox box,RecBox;

            //Get the invoice from the customer entity
            Invoice temp = customerMap.get(id).GetInvoicesList().get(i);


            if(temp.getRecurring().equals("ONCE")) {
                //Load the invoice template
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Invoice_Item.fxml"));

                box = loader.load();


                //Append a controller to the invoice GUI component
                Invoice_Editing_Controller EditControl = loader.getController();

                System.out.println("Customer id = " + id + "Invoice id = " + temp.getId());
                //Set to the controller the invoice and customer id's
                EditControl.setCustomerAndInvoiceId(id, temp.getId());


                //Initialize the Hbox that shows the invoice basic data such as price ,expiration date etc
                ((Label) box.getChildren().get(0)).setText("Invoice#" + temp.getId());
                ((Label) box.getChildren().get(1)).setText(temp.getBill_Date().toString());
                ((Label) box.getChildren().get(2)).setText(temp.getPayment_Date().toString());
                ((Label) box.getChildren().get(3)).setText(Float.toString(temp.getPrice()));
                ((Label) box.getChildren().get(4)).setText(Float.toString(temp.getPayedAmount()));
                ((ComboBox) box.getChildren().get(5)).getItems().addAll("Add Payment", "Edit", "Delete");



                //Create a link to the invoice Price Label so it can be updated on domain hosting/type change
                ((Label) box.getChildren().get(3)).setId(temp.getId() + ((Label) box.getChildren().get(3)).getId());
                System.out.println("Invoice price label id = " + ((Label) box.getChildren().get(3)).getId());
                linker.CreateLink(((Label) box.getChildren().get(3)));


                System.out.println(temp.getRecurring());
                System.out.println(temp.getType());

                //Add the invoice to a box that coresponds with its type
                if (temp.getType().equals("MONTHLY")) {
                    MonthlyBox.getChildren().add(box);
                    EditControl.SetContainer(MonthlyBox);
                }
                if (temp.getType().equals("ONCE")) {
                    OneTimeBox.getChildren().add(box);
                    EditControl.SetContainer(OneTimeBox);
                }
                if (temp.getType().equals("YEARLY")) {
                    YearlyBox.getChildren().add(box);
                    EditControl.SetContainer(YearlyBox);
                }

            }
            //If the invoice is recurring then add it to the recurring panel
            if(!temp.getRecurring().equals("ONCE"))
            {
                //Load the invoice template
                FXMLLoader loaderRec = new FXMLLoader(getClass().getResource("../fxml/Invoice_Item.fxml"));

                RecBox = loaderRec.load();

                //Append a controller to the invoice component
                Invoice_Editing_Controller EditControlRec = loaderRec.getController();

                //Set customer and invoice id's to the controller
                EditControlRec.setCustomerAndInvoiceId(id,temp.getId());
                EditControlRec.SetIncomeLabel(this.TotalIncome);

                //Initialize the Hbox values
                ((Label)RecBox.getChildren().get(0)).setText("Invoice#"+temp.getId());
                ((Label)RecBox.getChildren().get(1)).setText(temp.getBill_Date().toString());
                ((Label)RecBox.getChildren().get(2)).setText(temp.getPayment_Date().toString());
                ((Label)RecBox.getChildren().get(3)).setText(Float.toString(temp.getPrice()));
                ((Label)RecBox.getChildren().get(4)).setText(Float.toString(temp.getPayedAmount()));
                ((ComboBox)RecBox.getChildren().get(5)).getItems().addAll("Add Payment","Edit","Delete");

                //Create a link to the invoice Price Label so it can be updated on domain hosting/type change
                ((Label) RecBox.getChildren().get(3)).setId(temp.getId() + ((Label) RecBox.getChildren().get(3)).getId());
                System.out.println("Invoice price label id = " + ((Label) RecBox.getChildren().get(3)).getId());
                linker.CreateLink(((Label) RecBox.getChildren().get(3)));

                //Add the component to the Reccurrence Box
                CustomBox.getChildren().add(RecBox);
                EditControlRec.SetContainer(CustomBox);
            }


        }


    }


    //This method is triggered id the user selects a domain from the domains Combo box
    @FXML
    void SelectDomain(ActionEvent event) {

        if (event.getSource() == DomList && !DeleteFlag)
        {
            System.out.println(DomList.getValue());

            //Get the domains invoice ID
            DomainId = CustomerDomains.get(DomList.getValue()).getId();
            DomainInvoiceId = CustomerDomains.get(DomList.getValue()).getInvoice_Id();
            System.out.println("Domain invoice id = "+DomainInvoiceId);


            //Initialize an empty invoice entity for later use
            Invoice DomainInvoice = null;

            //Get the invoice entity that belongs to this domain
            for(int i = 0; i<customerMap.get(id).GetInvoicesList().size();i++)
            {

                if (customerMap.get(id).GetInvoicesList().get(i).getId() == DomainInvoiceId)
                {
                    DomainInvoice = customerMap.get(id).GetInvoicesList().get(i);
                    System.out.println(DomainInvoice.getItems().size());
                }

            }


            //Set the domain name to the textfield
            DomName.setText(DomList.getValue());

            //Set the expiration date to the Date Picker field
            ExpDate.setText(CustomerDomains.get(DomList.getValue()).getExpiry_Date().toString());

            //Get the Hosting and Domain types and put show them in the combo box
            for (int i = 0;i<DomainInvoice.getItems().size();i++)
            {
                System.out.println(DomainInvoice.getItems().get(i).getType());
                if(DomainInvoice.getItems().get(i).getType().equals("HOSTING SMALL") || DomainInvoice.getItems().get(i).getType().equals("HOSTING MEDIUM") || DomainInvoice.getItems().get(i).getType().equals("HOSTING BIG"))
                {
                    HostingType.setPromptText(DomainInvoice.getItems().get(i).getType());
                    hostingType = DomainInvoice.getItems().get(i).getType();
                }
                else if(DomainInvoice.getItems().get(i).getType().equals("DOMAIN.GR") || DomainInvoice.getItems().get(i).getType().equals("DOMAIN.COM"))
                {
                    DomainType.setPromptText(DomainInvoice.getItems().get(i).getType());
                    domainType = DomainInvoice.getItems().get(i).getType();
                }
            }
            System.out.println(hostingType);
            System.out.println(domainType);

        } else if(event.getSource() == DomList && DeleteFlag)
        {
            //DomList.setPromptText("Domains");
            DomList.valueProperty().set(null);
            DeleteFlag = false;
        }



    }


    @FXML
    void pressed(ActionEvent event) throws IOException,  SQLException, ClassNotFoundException {
        //This part is triggered if the user updates the customers data
        if (event.getSource() == UpdateCustomer)
        {


            //First we check if the inputs are correct
            String regex = "\\d+";

            if (!AFM.getText().matches(regex) || !ZIP.getText().matches(regex) || !Phone.getText().matches(regex))
            {
                System.out.println("lamo");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your new input has a typo \n Please check all the fields and type appropriate values", ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait();

                if(alert.getResult() == ButtonType.OK )
                {
                    System.out.println("lma0");
                    return;
                }
            }
            else {


                //Create a database reader so as to update the customer values in the database
                Read_Database DatabaseUpdater = new Read_Database();

                //Set to the customer the new values

                customerMap.get(id).setName(NameC.getText());
                customerMap.get(id).setName(NameC.getText());
                customerMap.get(id).setCountry(Country.getText());
                customerMap.get(id).setAddress(Address.getText());
                customerMap.get(id).setZip(ZIP.getText());
                customerMap.get(id).setAFM(AFM.getText());
                customerMap.get(id).setCity(City.getText());
                customerMap.get(id).setEmail(Email.getText());
                customerMap.get(id).setPhone(Phone.getText());
                ((Label) Hbc.getChildren().get(1)).setText(NameC.getText());

                //Update the customer in the database
                DatabaseUpdater.UpdateCustomer(id);
            }
        }

        //This part is triggered if the user updates the domain with new values
        if (event.getSource() == UpdateDomain)
        {
            //Get the New domain data
            String DomainName = DomName.getText();
            Date StartDate = Date.valueOf(ExpDate.getText());
            Date ExpiryDate = Date.valueOf(ExpDate.getText());
            String HostType = HostingType.getValue();
            String DomType = DomainType.getValue();

            //Create 4 items responsible for updating the invoice in the database and in the customer
            Invoice NewInvoice = null;
            Domain NewDomain = null;
            Item HostingItem = null;
            Item DomainItem= null;


            //The price of the invoice before it was changed and the total income and customer cost
            Linker linker = new Linker();
            float PreviousPrice = 0 ;
            Label TotalIncomeLabel = linker.GetLabelLink("IncomeLabel");
            Label CustomerCostLabel = linker.GetLabelLink(id+"CustomerPrice");
            String TotalIncomeString = TotalIncomeLabel.getText().substring(0, TotalIncomeLabel.getText().length() - 1);
            String CustomerIncomeString = CustomerCostLabel.getText().substring(0 ,CustomerCostLabel.getText().length() - 1 );
            float TotalIncome = Float.parseFloat( TotalIncomeString );
            float CustomerIncome = Float.parseFloat( CustomerIncomeString );
            TotalIncome -= CustomerIncome;



            System.out.println("New Domain Name : " + DomainName);
            System.out.println("New Hosting Type : " + HostType);
            System.out.println("New Domain Type : " + DomType);


            //Get the domain from the customer
            for (int i = 0;i<customerMap.get(id).getDomainsList().size();i++)
            {
                if(customerMap.get(id).getDomainsList().get(i).getName().equals(DomList.getValue()))
                {
                    customerMap.get(id).getDomainsList().get(i).setName(DomainName);
                    //customerMap.get(id).getDomainsList().get(i).setStart_Date(StartDate);
                    customerMap.get(id).getDomainsList().get(i).setExpiry_Date(ExpiryDate);
                    NewDomain = customerMap.get(id).getDomainsList().get(i);
                    break ;
                }
            }

            //Get the new Hosting and or Domain Items
            for(Map.Entry<String,Item>entry : ItemsMap.entrySet())
            {
                if(HostType != null && entry.getValue().getType().equals(HostType))
                {
                    //New Hosting Item
                    HostingItem = new Item(entry.getValue());
                }
                else if(DomType != null && entry.getValue().getType().equals(DomType))
                {
                    //New Domain Item
                    DomainItem =new Item(entry.getValue());
                }
            }


            //Create the new invoice with the updated domain values
            for(int i = 0; i<customerMap.get(id).GetInvoicesList().size();i++)
            {
                if (customerMap.get(id).GetInvoicesList().get(i).getId() == DomainInvoiceId)
                {
                    for (int j = 0;j<customerMap.get(id).GetInvoicesList().get(i).getItems().size();j++)
                    {
                        //Check if a new hosting is added
                        if(customerMap.get(id).GetInvoicesList().get(i).getItems().get(j).getType().equals(hostingType) && HostType != null)
                        {
                            float Hosting_Price = customerMap.get(id).GetInvoicesList().get(i).getItems().get(j).getPrice();
                            hostingType = HostType;
                            CustomerIncome -= Hosting_Price;
                            assert HostingItem != null;
                            CustomerIncome += HostingItem.getPrice();
                            customerMap.get(id).GetInvoicesList().get(i).getItems().remove(j);
                            customerMap.get(id).GetInvoicesList().get(i).getItems().add(HostingItem);
                            NewInvoice = customerMap.get(id).GetInvoicesList().get(i);

                            NewInvoice.RemovePrice(Hosting_Price);

                            System.out.println("HOSTING ADDED");

                        }
                        //Check if a new domain type is added
                        if(customerMap.get(id).GetInvoicesList().get(i).getItems().get(j).getType().equals(domainType) && DomType != null)
                        {
                            float Type_Price = customerMap.get(id).GetInvoicesList().get(i).getItems().get(j).getPrice();
                            domainType = DomType;
                            CustomerIncome -= Type_Price;
                            CustomerIncome += DomainItem.getPrice();
                            customerMap.get(id).GetInvoicesList().get(i).getItems().remove(j);
                            customerMap.get(id).GetInvoicesList().get(i).getItems().add(DomainItem);
                            NewInvoice = customerMap.get(id).GetInvoicesList().get(i);
                            NewInvoice.RemovePrice(Type_Price);
                            System.out.println("DOMAIN TYPE ADDED");
                        }

                    }

                }
            }

            //Calculate the new price of the invoice
            NewInvoice.Calc_Invoice_Price();
            System.out.println("New Price = " + NewInvoice.getPrice() + "Invoice Label Id = " + linker.GetLabelLink( NewInvoice.getId()+"Price").getId());
            linker.GetLabelLink( NewInvoice.getId() + "Price").setText( String.valueOf( NewInvoice.getPrice() ) );
            CustomerCostLabel.setText(String.valueOf(CustomerIncome));
            TotalIncome +=CustomerIncome;
            TotalIncomeLabel.setText(String.valueOf(TotalIncome));



            //Update the database with the new domain and invoice values
            Read_Database DomainUpdater = new Read_Database();
            DomainUpdater.UpdateDomain(NewDomain,NewInvoice);

        }

        //This is triggered if the user decides to add a new domain to the customer
        if (event.getSource() == AddDomain)
        {
            //Load the Add domain gui panel
            FXMLLoader AddDomainLoader = new FXMLLoader(getClass().getResource("/fxml/Add_Domain.fxml"));
            //Add the panel to the side of the screen
            AddDomainPane.getChildren().add(AddDomainLoader.load());

            //Append a controller to the panel so as to get the data inputted by the user
            AddDomainController ctrl = AddDomainLoader.getController();
            //Set to the controller the map containing the domains of the customer
            ctrl.SetCustomerDomains(CustomerDomains);
            //Set to the controller the id of the customer so as to add the domain successfully
            ctrl.SetCustomerId(id);

            //Set the Domain Combox so as to update it when a new domain is added
            ctrl.SetDomainCombox(DomList);

            //Set the containers that contain the invoices
            ctrl.SetContainers(YearlyBox,CustomBox);


        }

        //This is triggered if the user decides to add a new invoice to the customer
        if (event.getSource() == AddInvoice)
        {
            //Load the add invoice GUI panel and show it to the user
            FXMLLoader AddInvoiceLoader = new FXMLLoader(getClass().getResource("/fxml/Add_Invoice.fxml"));
            Parent root = AddInvoiceLoader.load();
            //Append a controller to the panel
            AddInvoiceController ctrl = AddInvoiceLoader.getController();

            //Set to the cotroller the Customer id
            ctrl.SetCustomerData(id);
            //Set to the controller the VBoxes containing the invoices based on their type and reccurence
            ctrl.SetContainers(MonthlyBox,YearlyBox,OneTimeBox,CustomBox);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Item");
            stage.show();
        }

        if(event.getSource() == DeleteDomain)
        {
            DeleteFlag = true;
            DomList.getItems().remove(DomList.getValue());
            DomName.setText(null);
            ExpDate.setText(null);
            HostingType.setPromptText("Hosting Types");
            DomainType.setPromptText("Domain Type");
            Database_Deleter deleter = new Database_Deleter();
            deleter.Delete_Domain(DomainId,DomainInvoiceId);
            DeleteDomainInvoice(DomainInvoiceId);

            for(int i = 0; i<customerMap.get(id).getDomainsList().size(); i++)
            {
                if(customerMap.get(id).getDomainsList().get(i).getId() == DomainId)
                {
                    customerMap.get(id).getDomainsList().remove(i);
                    break ;
                }
            }

            for(int i = 0; i<customerMap.get(id).GetInvoicesList().size(); i++)
            {
                if(customerMap.get(id).GetInvoicesList().get(i).getId() == DomainInvoiceId)
                {
                    customerMap.get(id).GetInvoicesList().remove(i);
                    break;
                }
            }

        }

        if (event.getSource() == EditB) {

            Read_Database DatabaseUpdater = new Read_Database();

            String id = ((Label) Hbc.getChildren().get(5)).getText();

            projectMap.get(Integer.valueOf(id)).setName(NameF.getText());
            projectMap.get(Integer.valueOf(id)).setDueDate(Date.valueOf(DateF.getText()));
            projectMap.get(Integer.valueOf(id)).setWorkforce(projectMap.get(Integer.valueOf(id)).getWorkers().size());
            projectMap.get(Integer.valueOf(id)).setPrice(Float.parseFloat(PriceF.getText()));

            ((Label) Hbc.getChildren().get(1)).setText(NameF.getText());
            ((Label) Hbc.getChildren().get(2)).setText(DateF.getText());
            ((Label) Hbc.getChildren().get(3)).setText(PriceF.getText());
            ((Label) Hbc.getChildren().get(4)).setText(String.valueOf(projectMap.get(Integer.valueOf(id)).getWorkers().size()));

            DatabaseUpdater.UpdateProject(Integer.valueOf(id));

        }

    }



    public void DeleteDomainInvoice(int DomainInvoiceId) throws SQLException, ClassNotFoundException {
        //Create a Database Deleter entity so as to delete the invoice from the database
        //as well as any entries in the tables that contain its Invoice ID
        Database_Deleter deleter = new Database_Deleter();
        //Delete the invoice from the database
        deleter.Delete_Invoice(DomainInvoiceId);
        //Delete the invoice from its container VBox
        CustomBox.getChildren().remove(CustomBox.lookup("#"+DomainInvoiceId));

        //Create a linker so as to apply changes to the customer price label as well as the total income label
        Linker link = new Linker();

        //Get the values of the customer price label and total income label
        String Tcost = link.GetLabelLink("IncomeLabel").getText();
        Tcost = Tcost.substring(0 , Tcost.length() - 1);
        float TotalCost = Float.parseFloat(Tcost);
        float CustomerCost = Float.parseFloat(link.GetLabelLink(id+"CustomerPrice").getText());


        //Apply the changes to the aforementioned labels and remove the invoice from the customer map
        for(int i = 0;i<customerMap.get(id).GetInvoicesList().size();i++)
        {
            if(customerMap.get(id).GetInvoicesList().get(i).getId() == DomainInvoiceId)
            {
                TotalCost = TotalCost - customerMap.get(id).GetInvoicesList().get(i).getPrice();
                CustomerCost = CustomerCost - customerMap.get(id).GetInvoicesList().get(i).getPrice();

                link.GetLabelLink("IncomeLabel").setText(String.valueOf(TotalCost));
                link.GetLabelLink(id+"CustomerPrice").setText(String.valueOf(CustomerCost));

                customerMap.get(id).GetInvoicesList().remove(i);
            }
        }
    }



    public void setTaskInfo(HBox ToDoItem){

        this.Hbc = ToDoItem;

        int projID = Integer.parseInt(((Label) ToDoItem.getChildren().get(7)).getText());

        int taskID = Integer.parseInt(ToDoItem.getId());

        int workerID = Integer.parseInt(((Label) ToDoItem.getChildren().get(5)).getText());

        int i = Integer.parseInt(((Label) ToDoItem.getChildren().get(6)).getText());

        AssignedWorkerF.setText(workerMap.get(workerID).getName());

        BelongProjectF.setText(projectMap.get(projID).getName());

        NameF.setText(String.valueOf(projectMap.get(projID).getWorkers().get(workerID).getTasks().get(projID).get(i).getName()));

        DateF.setText(String.valueOf(projectMap.get(projID).getWorkers().get(workerID).getTasks().get(projID).get(i).getTodoDate()));

        CategoryF.setText(String.valueOf(projectMap.get(projID).getWorkers().get(workerID).getTasks().get(projID).get(i).getCategory()));

        StatusF.setText(String.valueOf(projectMap.get(projID).getWorkers().get(workerID).getTasks().get(projID).get(i).getStatus()));

        DescF.setText(String.valueOf(projectMap.get(projID).getWorkers().get(workerID).getTasks().get(projID).get(i).getDescription()));
    }


    public void editWorkerSave() throws SQLException, ClassNotFoundException {
        String name=NameWorker.getText();
        String email=EmailWorker.getText();

        String id=(((Label) Hbc.getChildren().get(3)).getText());

        //call the database edit


        //call the database edit


        WriteToDatabase edit=new WriteToDatabase();


        Worker temp=new Worker(Integer.parseInt(id),name,email);
        edit.updateWorker(temp);

        (((Label) Hbc.getChildren().get(1))).setText(name);
        (((Label) Hbc.getChildren().get(2))).setText(email);



    }


    public void setImportant () {

        if (importance.equals(Important.getId())) {
            importance = "";
        } else {
            importance = Important.getId();
        }

    }

    public void setRegular () {

        if (importance.equals(Regular.getId())) {
            importance = "";
        } else {
            importance = Regular.getId();
        }
    }

    public void setUnimportant () {

        if (importance.equals(Unimportant.getId())) {
            importance = "";
        } else {
            importance = Unimportant.getId();
        }
    }

    public void projGoBack () {

        Parent root = Hbc.getParent().getParent().getParent().getParent().getParent();

        root.toFront();
    }

    public void workerGoBack () {

        Parent root = Hbc.getParent().getParent().getParent().getParent().getParent();

        root.toFront();

    }

    public void customerGoBack () {

        Parent root = Hbc.getParent().getParent().getParent().getParent().getParent();

        root.toFront();

    }


}

