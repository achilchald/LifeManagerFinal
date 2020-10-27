package calendar;

import Entities.Appointment;
import Entities.Project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WriteFileAppointment {

    public void SaveAppointmentAdded (String title, String time, String date, String attendee,String notes,String importance ) throws IOException {

        FileWriter pw = new FileWriter("src\\Csv\\appointments.csv",true);
        pw.append(title);
        pw.append(",");
        pw.append(date);
        pw.append(",");
        pw.append(time);
        pw.append(",");
        pw.append(attendee);
        pw.append(",");
        pw.append(notes);
        pw.append(",");
        pw.append(importance);
        pw.append("\n");
        pw.flush();
        pw.close();

    }

    public void DeleteAppointment(String title) throws IOException {

        ReadAppointments appointments = new ReadAppointments();
        ArrayList<Appointment> myAppointments = appointments.ReadFile("src\\Csv\\appointments.csv");

        myAppointments.removeIf(appointment -> appointment.getTitle().equals(title));

        FileWriter pw = new FileWriter("src\\Csv\\appointments.csv");

        for (Appointment appointment : myAppointments){
            pw.write(appointment.getTitle()+","+appointment.getDate()+","
                    +appointment.getTime()+","+appointment.getAttendee()+","
                    +appointment.getNotes()+","+appointment.getImportance()+"\n");
        }
        pw.flush();
        pw.close();

    }



}
