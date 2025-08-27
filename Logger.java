import java.io.File;
import java.io.FileWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Logger {
    DateTimeFormatter my_datetimeformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String time_ofstart, time_ofend, activity, note;
    long startms, endms;
    double duration;
    ZoneId zoneId = ZoneId.of("America/Chicago"); // TIMEZONE HERE
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Logger application = new Logger();
        application.enterMenu();

    }

    private void enterMenu() {
        
        boolean running = true; // menu loop's exit condition when false

        while (running) {
            System.out.println(
                    "1. Start Session:\n" +
                            "2. End Session:\n" +
                            "3. Print Total Hours:\n" +
                            "4. Exit");
            byte numselect = scanner.nextByte();
            scanner.nextLine();

            switch (numselect) {
                case 1:
                    startSession();
                    break;
                case 2:
                    endSession();
                    break;
                case 3:
                    printTotal();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }

    private void startSession() {
        if (time_ofstart == null) {
            // Scanner sessionscan = new Scanner(System.in); // close the scanner break the program. no, i dont care to debug
            System.out.println(
                    "Enter your study activity\n" +
                            "Known study activities include but are not limited to:\n" +
                            "- Flashcards\n" +
                            "- Manga\n" +
                            "- Anime \n" +
                            "- Wrting \n" +
                            "- Textbook");
            activity = scanner.nextLine();

            System.out.println(
                    "Enter an optional note (NO COMMAS!):");
            note = scanner.nextLine();

            LocalDateTime unformatted_datetime = LocalDateTime.now();
            time_ofstart = unformatted_datetime.format(my_datetimeformat);

            ZonedDateTime zonedDateTime = unformatted_datetime.atZone(zoneId);
            startms = zonedDateTime.toInstant().toEpochMilli();

            System.out.println("Time started: " + time_ofstart + " with the activity: " + activity
                    + ", along with the note: " + note);
        } else {
            System.out.println("You are already in an active session, started at: " + time_ofstart);
        }
    }

    private void endSession() {
        if (time_ofstart != null) {
            LocalDateTime unformatted_datetime = LocalDateTime.now();
            time_ofend = unformatted_datetime.format(my_datetimeformat);

            ZonedDateTime zonedDateTime = unformatted_datetime.atZone(zoneId);
            endms = zonedDateTime.toInstant().toEpochMilli();
            long millis = endms - startms;
            duration = millis / (1000.0 * 60 * 60); // truncate is lame

            System.out.printf("Time ended: %s%nDuration: %.2f hours.%n", time_ofend, duration);
            saveSession();
        } else {
            System.out.println("No active session.");
        }
    }

    private void saveSession() {
        try (FileWriter file = new FileWriter("sessions.csv", true)) {
            String row = String.format("%.2f,%s,%s,%s,%s%n", 
                                   duration, time_ofstart, time_ofend, activity, note);
        file.write(row);
        System.out.println("Session saved: " + row.trim());
        
            // reset after successful save
            time_ofstart = null;
            time_ofend = null;
            startms = 0;
            endms = 0;
            duration = 0;
        } catch (Exception e) {
            System.out.println("Failed to save session: " + e.getMessage());
        }
    }


    private void printTotal() {

        try (Scanner filescan = new Scanner(new File("sessions.csv"))) {
            filescan.useDelimiter(",|\\n"); // sets basic csv delimiter
            double total = 0;

            while (filescan.hasNext()) {
                if (filescan.hasNextDouble()) {
                    double value = filescan.nextDouble();
                    total += value;
                } else {
                    filescan.next();
                }
            }
            System.out.printf("Total: %.2f hours logged.%n", total);
            filescan.close();
            return;
        } catch (Exception e) {
            System.out.println("Error reading sessions.csv");
        }
    }
}