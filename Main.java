import java.util.*; // for lists, maps, etc.
// import java.sql.*; // SQLite driver, use JSON or CSV for now.
// option later: javafx.* → if you want a desktop GUI.

public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int select;
        String request = "";   
        StudySession session = new StudySession(); 
        


        do {
            System.out.println("Input Selection: ");
            System.out.println("1. Start Study");
            System.out.println("2. View");
            System.out.println("3. Exit");
            select = scanner.nextInt();
            switch (select) {
                case 1: {

                    do {
                        System.out.println("Select Study/Immersion Method");
                        System.out.println("1. Flashcards");
                        System.out.println("2. Reading");
                        System.out.println("3. Video");
                        System.out.println("4. Other");
                        System.out.println("5. Go back");
                        request = "";
                        select = scanner.nextInt();

                        if (select > 0 && select < 5) { // note is optional
                            request = "start";

                            if (select != 4) {

                                String selectString = scanner.nextLine();
                                System.out.println("Enter a note or enter 'start' to begin session: ");
                                String note = scanner.nextLine();
                                if (note.equals("start")) {
                                    note.equals("");
                                    session = new StudySession(request, select, note);
                                    System.out.println(session.getLastResponse());
                                    if (session.getLastResponse().contains("Invalid start/view request")) {
                                        selectString = scanner.nextLine();
                                            switch (selectString) {
                                                case "end": {session = new StudySession("end", 0, "note");}                                                   
                                                    break;
                                                case "delete": {session = new StudySession("delete", 0, "note");}                    
                                                    break;
                                                case "exit": {System.exit(0);}                                                  
                                                    break;
                                                default: {System.exit(0);}
                                                    break;
                                            }

                                    } else if (session.getLastResponse().contains("Start request successful")) {
                                        selectString = scanner.nextLine();
                                        if (selectString == "end") {session = new StudySession("end", 0, "");}
                                    } else {System.exit(0);}
                                }
                                

                            } else {

                                String selectString = scanner.nextLine();
                                System.out.println("Enter a note to start session: ");
                                String note = scanner.nextLine();
                                session = new StudySession(request, 4, note);
                                System.out.println(session.getLastResponse());
                                if (session.getLastResponse().contains("Invalid start/view request")) {
                                        selectString = scanner.nextLine();
                                            switch (selectString) {
                                                case "end": {session = new StudySession("end", 0, "note");}                                                   
                                                    break;
                                                case "delete": {session = new StudySession("delete", 0, "note");}                    
                                                    break;
                                                case "exit": {System.exit(0);}                                                  
                                                    break;
                                                default: {System.exit(0);}
                                                    break;
                                            }

                                    } else if (session.getLastResponse().contains("Start request successful")) {
                                        selectString = scanner.nextLine();
                                        if (selectString == "end") {session = new StudySession("end", 0, "");}
                                    } else {System.exit(0);}
                            }

                        } else if (select == 5) {} 
                        else {
                            System.out.println("Invalid Input");
                        }
                    } while (select != 5);
                    break;
                }
                case 2: {
                    request = "view";
                    session = new StudySession(request, 0, "");
                    System.out.println(session.getLastResponse());
                    if (session.getLastResponse().contains("Invalid start/view request")) {
                        String selectString = scanner.nextLine();
                        selectString = scanner.nextLine();
                            switch (selectString) {
                                case "end": {session = new StudySession("end", 0, "note");}                                                   
                                    break;
                                case "delete": {session = new StudySession("delete", 0, "note");}                    
                                    break;
                                case "exit": {System.exit(0);}                                                  
                                    break;
                                default: {System.exit(0);}
                                    break;
                            }

                    } else if (session.getLastResponse().contains("hours request successful")) {
                        // print hours
                    } else {System.exit(0);}
                    break;
                }               
                case 3: {
                    System.out.println("Now exiting:");
                    break;
                }
                default: {
                    System.out.println("Invalid Input");
                    break;
                }
            }

        } while (select != 3);
        scanner.close();
    }
}