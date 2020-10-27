package Entities;

public class Appointment {

    String title;
    String date;
    String time;
    String attendee;
    String notes;
    String importance;

    public Appointment (String title, String date, String time, String attendee, String notes, String importance){

        setTitle(title);
        setDate(date);
        setTime(time);
        setAttendee(attendee);
        setNotes(notes);
        setImportance(importance);

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setImportance(String importance) {

        if (importance.equals("Important")){
            this.importance = "Important";
        }
        if (importance.equals("Regular")){
            this.importance = "Regular";
        }
        if (importance.equals("Unimportant")){
            this.importance = "Unimportant";
        }

    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getAttendee() {
        return attendee;
    }

    public String getNotes() {
        return notes;
    }

    public String getImportance(){
        return importance;
    }

    public String getDate() {
        return date;
    }
}
