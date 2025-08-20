import java.time.*; // for handling dates, times, durations
import java.io.*; // for file saving/loading
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
            System.out.println("2. View Total Hours");
            System.out.println("3. Exit");
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

                    if (select > 0 &&){

                    }

                } while (select != 5);
                break;

            case 2:
                System.out.println("yummy2");
                // code to calculate and print sum of all study time
                break;
                
            case 3:
                System.out.println("Now exiting:");
                break;
                
            default:
                System.out.println("Invalid Input");
                break;
        }
        
    } while(select != 3); 
    scanner.close();
    }
}

