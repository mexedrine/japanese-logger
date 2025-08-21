import java.time.*;
import java.io.*;
import java.util.Scanner; 

public class StudySession {

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

    public boolean getCurrentState() {
        return currentState;
    }

    public StudySession(String request, int typeID, String note) { // all parameters incl.
        this.request = request;
        this.typeID = typeID;
        this.note = note;

        switch (request) {
            case "start": { // start requests
                if (!currentState) { // request is valid
                    System.out.println("Request Validated: ");
                    writeTempFile(typeName[typeID], note, null, null);
                } else { // invalid request

                }
                break;
            }

            case "end": { // end request
                if (currentState) { // valid end request

                } else { // invalid end request

                }
                break;
            }

            case "delete": { // delete requests
                if (currentState) { // valid delete request

                } else { // invalid delete request

                }
                break;
            }

            case "search": { // search requests
                if (!currentState) { // search is valid (no active session)

                } else { // search is invalid (active session)

                }
                break;
            }

            default: {
            } // add exception handler
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
}
