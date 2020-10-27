package calendar;

import Entities.Appointment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadAppointments {

    public ArrayList<Appointment> ReadFile(String filename) {


        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] Appointments = null;
        ArrayList appointment = new <Appointment>ArrayList();

        try {

            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                Appointments = line.split(cvsSplitBy);
                appointment.add(new Appointment(Appointments[0],Appointments[1],Appointments[2],Appointments[3],Appointments[4],Appointments[5]));
                System.out.println("Title : " + Appointments[0] + " ,Date : " + Appointments[1]+ " ,Time : " + Appointments[2] + " ,Attendee : "+Appointments[3] + " ,Notes : "+Appointments[4] + " ,Importance : "+Appointments[5]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return appointment;

    }

}
