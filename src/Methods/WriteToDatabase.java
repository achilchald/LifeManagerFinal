package Methods;

import Entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


public class WriteToDatabase extends Globals implements AboveGod {



    //todo fix Vunerabilities
    public void addWorker(Worker worker) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        //SQL INJECTION
        stmt.executeUpdate("INSERT INTO WORKERS VALUES (" + worker.getWorkerid() + " ,  \"" + worker.getName() + "\"  , \" " +worker.getEmail()+  "\"  );");

        con.close();
    }

    public void deleteWorker(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        //todo If there are projects WITH ACTIVE STATUS ask where to put them

        //Delete the workers Tasks
        stmt.executeUpdate("Delete from casperweb_databse.tasks where Worker_id="+id+";");

        //Deletes the worker from the projects
        stmt.executeUpdate("Delete from  casperweb_databse.proj_worker_link where Worker_id="+id+";");

        //Delete the Worker
        stmt.executeUpdate("Delete From casperweb_databse.workers where Worker_id = "+id+";");
    }

    public void updateWorker(Worker worker) throws ClassNotFoundException, SQLException {
        String name=worker.getName();
        String email=worker.getEmail();
        int id=worker.getWorkerid();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        stmt.executeUpdate("UPDATE Workers " + "Set worker_name = \"" + name + "\",EMAIL = \"" + email + "\" WHERE Worker_id = " + id + ";");





    }

    public void addWorkerToProjectDB(Worker worker,int projectid) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();

        stmt.executeUpdate("INSERT INTO proj_worker_link VALUES (" + projectid + " ,  " + worker.getWorkerid() + "  );");

    }

    public void addTaskToProjectDB(Task temp,int workid) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();


        stmt.executeUpdate("INSERT INTO tasks VALUES (" + temp.getTaskid() + " ,  \"" + temp.getName() + " \",  \"" + temp.getDescription() + "\" ,  \" " + temp.getTodoDate() + "  \" ,  " + 0 + "  ,  " + temp.getProject_id() + " ,  " + workid+ "  );");
    }



    public void addProject(Project project) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();
        //get workforce is initiallized after adding workers in the project
        stmt.executeUpdate("INSERT INTO PROJECTS VALUES (" + project.getId() + " ,  \"" + project.getName() + "\"  , \" " +project.getDueDate()+  "\"  , \" "+
                project.getPrice()+ "\"  , \" " +project.getWorkforce()+" \" );");
        for (Map.Entry<Integer, Worker> workersInProject : projectMap.get(project.getId()).getWorkers().entrySet()) {
            stmt.executeUpdate("INSERT INTO proj_worker_link VALUES (" + project.getId() + " ,  " + workersInProject.getValue().getWorkerid() + "  );");

            for (Map.Entry<Integer, ArrayList<Task>> tasksInProject : workersInProject.getValue().getTasks().entrySet()){
                ArrayList<Task>temp=tasksInProject.getValue();

                for (int i=0;i< temp.size();i++) {
                    //If the tasks belongs to the current project
                    if (temp.get(i).getProject_id()==project.getId()) {
                        stmt.executeUpdate("INSERT INTO tasks VALUES (" + temp.get(i).getTaskid() + " ,  \"" + temp.get(i).getName() + " \",  \"" + temp.get(i).getDescription() + "\" ,  \" " + Date.valueOf(LocalDate.now()) + "  \" ,  " + 0 + "  ,  " + project.getId() + " ,  " + workersInProject.getKey() + "  );");
                        lastTaskId = temp.get(i).getTaskid();
                    }
                }
            }


        }


        //todo add workers and tasks to project
//        project.getWorkers().get(0).getTasks();

        con.close();
    }

    public void deleteProject(int projectId) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CASPERWEB_DATABSE", "root", "root");
        //here sonoo is database name, root is username and password
        Statement stmt = con.createStatement();




        //Deletes the tasks of the project
        stmt.executeUpdate("DELETE FROM casperweb_databse.tasks WHERE Project_ID = "+ projectId +";");

        //Deletes the workers associated with the project
        stmt.executeUpdate("DELETE FROM casperweb_databse.proj_worker_link WHERE Project_ID = "+ projectId +";");

        //Deletes the project
        stmt.executeUpdate("DELETE FROM casperweb_databse.projects WHERE Project_ID = "+ projectId +";");


        System.out.println("DELETE FROM casperweb_databse.project WHERE Project_ID = "+ projectId +";");



        con.close();
    }

}