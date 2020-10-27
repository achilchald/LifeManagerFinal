package Entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Worker implements AboveGod{

    int workerid;
    String name;
    String email;
    HashMap<Integer,ArrayList<Task>> todo;

    String phone;



    public Worker(int id,String name,String email){
        this.workerid=id;
        this.name=name;
        this.email=email;
        this.todo=new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getWorkerid() {
        return workerid;
    }

    public String getEmail() {
        return email;
    }

    public void addTasks(Task task){
        todo.get(task.project_id).add(task);

    }


    //check if a project contains the selected worker
    public boolean isInProject(Project project){
        if(project.getWorkers().containsKey(this.workerid)){
            return true;
        }
        else {
            return false;
        }
    }



    //gets the "t o d o" of a Certain Worker in a project

    public HashMap<Integer,ArrayList<Task>> getTasks(){
        return this.todo;
    }

    //Changes the worker on a current task
    //Take

//    void reassign(Task task){
//        //get the global list of the workers
//        //select one of them
//        //assign him the selected task and remove it from the current worker
//    this.getTodo().remove(task);
//
//    }



}