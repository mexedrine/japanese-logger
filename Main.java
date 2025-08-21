import java.util.*; // for lists, maps, etc.
// import java.sql.*; // SQLite driver, use JSON or CSV for now.
// option later: javafx.* → if you want a desktop GUI.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int select;
        do {
            System.out.println("Input Selection: ");
            System.out.println("1. Start Study");
            System.out.println("2. End or Delete");
            System.out.println("3. View Total Hours");
            System.out.println("4. Exit");
            select = scanner.nextInt();
        switch (select) {
            case 1:
                
                do {
                    System.out.println("Select Study/Immersion Method");
                    System.out.println("1. Flashcards");
                    System.out.println("2. Reading");
                    System.out.println("3. Video");
                    System.out.println("4. Other");
                    System.out.println("5. Go back");
                    select = scanner.nextInt();

                    if (select > 0 && select < 4){ // note is optional

                        System.out.println("Enter 'start' or an optional note to begin: " + scanner.nextLine());
                        String selectString = scanner.nextLine(); 

                        // add prompt and option to either delete or end last study session if starting session fails

                        if (selectString == "start") { // starts w no note
                            StudySession session = new StudySession(false, false, select, ""); 
                            System.out.println(session + "Start studying now...");
                        } else { // start incl. note
                            StudySession session = new StudySession(false, false, select, selectString);
                            System.out.println(session + "/nStart studying now...");
                        }
                        
                        
                        return;
                    } else if (select == 4) { // non optional note
                        
                    } if (select == 5) {
                        // go back
                        return;
                    
                    
                    } else {
                        System.out.println("Invalid Input");
                    }

                } while (select != 5);
                break;

            case 2: // make a do while loop here
                {
                    StudySession session = new StudySession(true, false);
                }
                break;

            case 3: // attempt to print all hours
                {StudySession session = new StudySession(false, false);}
                break;

            case 4:
                System.out.println("Now exiting:");
                break;
                
            default:
                System.out.println("Invalid Input");
                break;
        }
        
    } while(select != 4); 
    scanner.close();
    }
}