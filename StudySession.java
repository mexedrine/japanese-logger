import java.time.*;
import java.io.*;
import java.util.Scanner; 

public class StudySession {

    private String lastResponse;
    private boolean currentState; 
    private int typeID;
    private String note;
    private String request; 
    private String typeName[] = {
            "Flashcards",
            "Reading",
            "Video",
            "Other"
    };

    public StudySession() {
        readTempFile();
    }

    public boolean getCurrentState() {
        return currentState;
    }

    public String getLastResponse() {
        return lastResponse;
    }

    public StudySession(String request, int typeID, String note) {
        this.request = request;
        this.typeID = typeID;
        this.note = note;

        switch (request) {
            case "start": { 
                if (!currentState) { 
                    writeTempFile(typeName[typeID], note, null, null);
                    // send info to manager
                    currentState = true;
                    lastResponse = requestHandle("start");
                } else { 
                    lastResponse = requestHandle("activeSession");
                }
                break;
            }

            case "end": {
                if (currentState) {
                    lastResponse = requestHandle("end");
                    // tell manager to end
                    clearTempFile();

                } else {
                    lastResponse = requestHandle("inactiveSession");
                }
                break;
            }

            case "delete": { 
                if (currentState) { 
                    lastResponse = requestHandle("delete");
                    // tell manager to delete
                    clearTempFile();

                } else { 
                    lastResponse = requestHandle("inactiveSession");
                }
                break;
            }

            case "view": { 
                if (!currentState) { 
                    lastResponse = requestHandle("view");
                    // tell manager to print total hours

                } else { 
                    lastResponse = requestHandle("activeSession");
                }
                break;
            }

            default: {
                System.out.println("Error: Unknown input in StudySession method");
            } 
        }
    }

    public String requestHandle(String response) {
        switch (response) {
            case "activeSession":
                return "Invalid start/view request. Active session in progress. \nChoose: [end | delete | exit]";
            case "inactiveSession":
                return "Invalid end/delete request. No active session.\nChoose: [start | exit]";
            case "start":
                return "Start request successful. Keep the program open and begin studying. \nFinished studying? Input 'end' to log your time:";
            case "end":
                return "End request successful. Please study again soon.\nSession start and view hours now available:";
            case "delete":
                return "Delete request successful. Session start and view hours now available:";
            case "view":
                return "View hours request successful.";
            default: {
                return "Error: Unknown input in StudySession method";
            }
        }
    }

    public void readTempFile() {
        try {
            File tempObj = new File("tempState.txt");
        if (!tempObj.exists() || tempObj.length() == 0) {
            currentState = false;
            return;
        }
        Scanner reader = new Scanner(tempObj);
        if (reader.hasNextLine()) {
            currentState = true;
        } else {
            currentState = false;
        }
        reader.close();
    }
    
    catch (FileNotFoundException e) {
        System.out.println("An error has occurred.");
        e.printStackTrace();
    }
    }

    public void writeTempFile(String name, String note, LocalDate date, LocalTime time) { // decide if it should just be
                                                                                          // save or handle
        if (!name.isEmpty() && !note.isEmpty()) {
            StringBuilder buildString = new StringBuilder();
            buildString.append(name).append(",");
            buildString.append(note).append(",");
            buildString.append(LocalDate.now()).append(",");
            buildString.append(LocalTime.now());

            String tempData = buildString.toString();
            try { 
                FileWriter Writer = new FileWriter("tempState.txt", true);
                Writer.write(tempData);
                Writer.close();
                System.out.println("Successfully saved session: " + tempData + ".");
            }
            catch (IOException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
            }
        }
    }

    public void clearTempFile() {
        try {
            FileWriter writer = new FileWriter("tempState.txt", false); // false = overwrite mode
            writer.write(""); // write nothing, clears the file
            writer.close();
            currentState = false; // since file is empty, no active session
        } catch (IOException e) {
            System.out.println("An error occurred while clearing tempState.txt.");
            e.printStackTrace();
        }
    }
}
