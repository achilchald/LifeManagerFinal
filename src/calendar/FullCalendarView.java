package calendar;

import Entities.Appointment;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;


public class FullCalendarView {

    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    private String appointmentTitle;

    /**
     * Create a calendar view
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth) {
        currentYearMonth = yearMonth;
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setPrefSize(750, 500);
        calendar.setGridLinesVisible(true);
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200,200);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{ new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                                        new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                                        new Text("Saturday") };
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            txt.setFill(Paint.valueOf("#c4dbe9"));
            ap.setPrefSize(200, 10);
            ap.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);

        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        Button previousMonth = new Button(" << ");
        previousMonth.setOnAction(e -> previousMonth());
        Button nextMonth = new Button(" >> ");
        nextMonth.setOnAction(e -> nextMonth());
        Button refresh = new Button(" refresh ");
        refresh.setOnAction(e -> refresh());
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth,refresh);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar); //---------------------------------------------
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }

            //---------------------------------------------------------------------------------------------------------------

            ReadAppointments appointments = new ReadAppointments();
            ArrayList<Appointment> myAppointments = appointments.ReadFile("src\\Csv\\appointments.csv");

            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            txt.setFill(Paint.valueOf("#c4dbe9"));

            if( hasAppointment(calendarDate, myAppointments) ) {
                setAppointment(txt, calendarDate);
                ap.setStyle("-fx-background-color: #2f3e47");
            }else{
                ap.setStyle("-fx-background-color: #253138");
            }
            ap.setBorder(new Border(new BorderStroke(Color.rgb(196,219,233),
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            //---------------------------------------------------------------------------------------------------------------


            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setFill(Paint.valueOf("#c4dbe9"));
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));

    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public void refresh(){
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

    public void setAppointment (Text txt, LocalDate calendarDate){


        txt.setText(String.valueOf(calendarDate.getDayOfMonth())+"\n"+appointmentTitle);

    }

    public boolean hasAppointment(LocalDate calendarDate, ArrayList<Appointment> appointments){
        boolean flag = false;

        System.out.println(appointments.get(0).getDate());
        System.out.println(calendarDate);

        for (Appointment appointment : appointments) {
            flag = appointment.getDate().equals(calendarDate.toString());
            if (flag){
                appointmentTitle = appointment.getTitle();
                break;
            }

        }
        return flag;

    }

}
