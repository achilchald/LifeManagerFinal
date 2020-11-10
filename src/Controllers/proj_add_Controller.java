
package Controllers;

import Entities.*;
import Methods.WriteFile;
import Methods.WriteToDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class proj_add_Controller  extends Globals implements AboveGod {

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField NameF;

    @FXML
    private TextField DateF;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField WorkforceF;

    @FXML
    private Button AddB;

    @FXML
    private VBox vbc;


    @FXML
    private VBox workersbox;

    @FXML
    private VBox todobox;

    @FXML
    private Button addWorkerBtn;

    @FXML
    private Button AddToDoBtn;

    @FXML
    private DatePicker date;

    @FXML
    private  ComboBox<String> workerscombo;

    @FXML
    private  ComboBox<String> taskCombo;


    @FXML
    private AnchorPane AddProjectAnchor;


    @FXML
    private VBox pnItems;

    @FXML
    private TabPane TabProject;

    @FXML
    private Button AddW;

    @FXML
    private Button AddT;

    @FXML
    private TextField nametask;

    @FXML
    private TextField desctask;

    @FXML
    private ComboBox<String> taskCategory;

    @FXML
    private DatePicker datePicker;

    private AnchorPane parent;

    private StackPane Stackpane;

    public int counter = 0;

    public void setProjectStackPane(StackPane Stackpane){
        this.Stackpane=Stackpane;
    }

    //SetBox is PROJECT ADD INITILIAZE
    public void SetBox(VBox box, int counter, AnchorPane pane)
    {
        this.vbc = box;
        this.counter = counter + 1;
        this.parent=pane;

        pane.getChildren().add(AddProjectAnchor);
        initComboBoxes();

    }

    public void setCounter(int counter)
    {
        this.counter = counter + 1;
    }

    //todo delete newly added items
    public void Add_Project() throws IOException {
        String name = NameF.getText();
        LocalDate localdate=date.getValue();
        String price = PriceF.getText();



        HBox hbox = new HBox();
        hbox = FXMLLoader.load(getClass().getResource("../fxml/Project_Item.fxml"));


        int id=projectMap.size()+1;



        //check if neccessery attributes are added
        if (name.equals("")||localdate==null||price.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Text Input ");
            alert.setHeaderText("Please enter all neccessery values");
            alert.setContentText("Vro min eisai etsi!");

            alert.showAndWait();
        }
        else {
            //ftiakse to project
            Project temp = new Project(id, name, Date.valueOf(localdate), Float.parseFloat(price), 0);

            projectMap.put(temp.getId(), temp);

            //Edw se paei sto allo panel
            SingleSelectionModel<Tab> selectionModel = TabProject.getSelectionModel();
            selectionModel.select(1);


            //Now you add the workers to the project


            ((Label) hbox.getChildren().get(4)).setText("23");


        }

    }

    public void addWorkerToProject() throws IOException {
        //get the selected option on the checkbox
        String selection = workerscombo.getSelectionModel().getSelectedItem();

        //gets the WOKRER id from the name selected in the combobox
        int id = Integer.valueOf(selection.substring(0, selection.indexOf(".")));
        System.out.println("Id of worker = " + id);

        int projectid=projectMap.size();

        HBox box = new HBox();
        box = FXMLLoader.load(getClass().getResource("../fxml/worker_item.fxml"));

        //find the worker name selected and get its id;


        ((Label) box.getChildren().get(1)).setText(workerMap.get(id).getName());

        ((Label) box.getChildren().get(2)).setText(workerMap.get(id).getEmail());

        ((Label) box.getChildren().get(3)).setText(String.valueOf(id));

        //if the worker is not already added add him
        if (projectMap.get(projectMap.size()).getWorkers().get(id)==null) {
            projectMap.get(projectMap.size()).addWorker(id);
            workerMap.get(id).getTasks().put(projectid,new ArrayList<Task>());
            System.out.println("Project workers Added : " + projectMap.get(projectMap.size()).getWorkers().get(id));

            //adds the options in the next tab Combobox
            taskCombo.getItems().add((projectMap.get(projectid).getWorkers().get(id).getWorkerid()+"."+projectMap.get(projectid).getWorkers().get(id).getName()));
            workersbox.getChildren().add(box);


        }





    }

    public void addTask() throws IOException {

        int projectid=projectMap.size();

        String selection = taskCombo.getSelectionModel().getSelectedItem();

        //gets the id from the name selected in the combobox
        int id = Integer.parseInt(selection.substring(0, selection.indexOf(".")));
        System.out.println("Id of worker = " + id);

        HBox box = new HBox();
        box = FXMLLoader.load(getClass().getResource("../fxml/ToDoItem.fxml"));

        //find the worker name selected and get its id;

        ((Label) box.getChildren().get(1)).setText(nametask.getText());

        ((Label) box.getChildren().get(2)).setText(String.valueOf(projectid));

        ((Label) box.getChildren().get(3)).setText(desctask.getText());


        String category = taskCategory.getSelectionModel().getSelectedItem();

        LocalDate localDate = datePicker.getValue();

        //make the task

        boolean status=false;
        Task temp=new Task(++lastTaskId,nametask.getText(),desctask.getText(),Date.valueOf(localDate),status,projectid,id,category);

        //now add it to the worker
        workerMap.get(id).addTasks(temp);


        //projectMap.get(projectid).getWorkers().get(id).addTasks(temp);
        // projectMap.get(projectid).getTasks().put()

        todobox.getChildren().add(box);
//        }

    }

    @FXML
    public void finish() throws IOException, SQLException, ClassNotFoundException {
        SingleSelectionModel<Tab> selectionModel = TabProject.getSelectionModel();
        int proj_id= projectMap.size();

        String name = NameF.getText();
        LocalDate localdate=date.getValue();
        String price = PriceF.getText();

        //todo there may be a bug when adding blank project

        //Validate Checks
        if (name.equals("")||localdate==null||price.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Text Input ");
            alert.setHeaderText("Please enter valid types on the");
            alert.setContentText("Vro min eisai etsi!");

            alert.showAndWait();
            selectionModel.select(0);

        }
        else {
            //now it sets the project item
            HBox hbox;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Project_Item.fxml"));

            hbox = loader.load();

            Edit_Controller control = loader.getController();

            control.SetStackArea(Stackpane);

            ((Label) hbox.getChildren().get(1)).setText(name);

            ((Label) hbox.getChildren().get(2)).setText(String.valueOf(localdate));

            ((Label) hbox.getChildren().get(3)).setText(price);

            ((Label) hbox.getChildren().get(4)).setText(String.valueOf(projectMap.get(proj_id).getWorkers().size()));

            //id
            ((Label) hbox.getChildren().get(5)).setText(String.valueOf(proj_id));

            //set the ITEM BOX id.
            hbox.setId(String.valueOf(proj_id));

            //setCounter(counter);

            vbc.getChildren().add(hbox);

            WriteToDatabase wr=new WriteToDatabase();


            wr.addProject(projectMap.get(proj_id));


            parent.getChildren().removeAll(parent.lookup("#AddProjectAnchor"));
        }

    }


    @FXML
    public  void initComboBoxes() {

        for (Map.Entry<Integer, Worker> entry2 : workerMap.entrySet()) {
            workerscombo.getItems().add(entry2.getKey()+"."+entry2.getValue().getName());

        }


        for (Map.Entry<Integer, String> entry2 :categoryMap.entrySet()) {

            taskCategory.getItems().add(entry2.getKey()+"."+entry2.getValue());

        }
    }



    //todo More advanced error handling
    //todo Adding the box when
    public void AddedWorker() throws IOException {
        SingleSelectionModel<Tab> selectionModel = TabProject.getSelectionModel();


        String name = NameF.getText();
        LocalDate localdate=date.getValue();
        String price = PriceF.getText();


//        Project temp=new Project(name, Date.valueOf(date),Float.parseFloat(price),Integer.parseInt(wf));
        if (name.equals("")||localdate==null||price.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Text Input ");
            alert.setHeaderText("Please enter valid types on the");
            alert.setContentText("Vro min eisai etsi!");

            alert.showAndWait();
            selectionModel.select(0);
        }
        else {
            selectionModel.select(2);
        }
    }



    @FXML
    public void pressed(ActionEvent event) throws IOException {
        if (event.getSource() == AddB) {
            Add_Project();
        }
    }
}