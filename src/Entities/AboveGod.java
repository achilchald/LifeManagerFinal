package Entities;



import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AboveGod {

//    public static ArrayList<Customer> customers=new ArrayList<Customer>();
//    public static ArrayList<Project> projects=new ArrayList<Project>();

    Map<String,Customer> customerMap = new HashMap<>();

    Map<Integer,Project> projectMap=new HashMap<>();

    ArrayList<Invoice> invoiceTypes=new ArrayList<>();

    Map<String, Domain> DomainMap = new HashMap<>();

    Map<String,Item> ItemsMap = new HashMap<>();

    Map<Integer,Worker> workerMap=new HashMap<>();

   Map<Integer,String> categoryMap=new HashMap<>();

   Map<Integer,LogEvent> logMap=new HashMap<>();














}
