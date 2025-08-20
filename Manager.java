import java.time.*;
// import java.io.*; // for file saving/loading
public class Manager {

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    private boolean activeSession;
    private String studyType;
    private String studyNote;

    public Manager(boolean delete, boolean state, String type, String note) {

        GetDuration findDuration = new GetDuration(date, time);
        StudyLength setDuration = new StudyLength(0);


        if (state && !delete) { // functions that work when session is in active memory
            activeSession = true;
            if (type != "") {
                // call method to update list with data of new active session
                System.out.println(type);
                System.out.println(note);
                System.out.println(date);
                System.out.println(time);
                System.out.println("You have started your session!");
            } else {
                // call method to print the data of last entry (unclosed entry)
                System.out.println("Last session: 'date' 'time' ");
            }
        } 

        else { // functions that work when session is out of active memory
            activeSession = false;
            if (!delete && type != "") { 
                // call method with function to update list with end session
                System.out.println("You ended your " + type + " session!");
            } else if (!delete && type == "") {
                // call method to print total hours
                System.out.println("You have no hours of study! lazy fuck...");
            } else {
                // call method to delete last session in list
                System.out.println("You deleted 'session' from the database!");
            }
        }
    }
    
    public class GetDuration {
        public GetDuration(LocalDate date, LocalTime time) {

        }
    }

    public class StudyLength {
        public StudyLength(double length) {

        }
    }
}
