public class StudySession {
<<<<<<< Updated upstream
    private String typeName[] = {
        "Flashcards",
        "Reading",
        "Video",
        "Other"
    };
    
=======

    private String lastResponse;
    private boolean currentState; 
>>>>>>> Stashed changes
    private int typeID;
    private String note;
    private boolean persistenProgress; // SET TO THE VALUE FROM PERSISTENT STORAGE
    private boolean inProgress = false;
    private boolean isDelete;
    private boolean isEnd;

    public StudySession(boolean isDelete, boolean isEnd) {
        this(isDelete, isEnd, 0, "");
    }

<<<<<<< Updated upstream
    public StudySession(boolean isDelete, boolean isEnd, int typeID, String note) {
        this.typeID = typeID;
        this.note = note;
        this.isEnd = isEnd;

        if (isEnd == persistenProgress && !isDelete){ // no invalid end request & !delete
            
            if (!isEnd) { 
                // valid request to start session
                startSession(typeName[typeID], note);
            }
            else if (typeID != 0) { 
                // valid request to end session
                endSession(typeName[typeID], note);
            }
            else {
                // valid request to keep session ended
                // call method with parameters to cause print all hours
                Manager manager = new Manager(false, false, "", "");
            }
        } 

        if (isDelete) { // all attempts to delete
            if (persistenProgress == isEnd) { // requests aligned with persistent state
                if (isEnd) { // deletes entry
                    deleteSession(); 
                } else { // doesn't delete and prints hours
                    Manager manager = new Manager(false, false, "", "");
                }           
            } else if (persistenProgress) {
                Manager manager = new Manager(false, true, "", "");
            } else {
                System.out.println("Failed to delete, there is no active session");
            }
            
        } 

        if (isEnd != persistenProgress && !isDelete) { // invalid request, no state changes will occur
            boolean state = persistenProgress;
            if (state == true) { // attempt to start new session during active session
                System.out.println("Session start failed, you cannot start a session during an active session.");
                // call method with specific parameters, causes print of last session
                Manager manager = new Manager(false, state, "", "");
            }
            if (state == false && isEnd) { // attempt to end without active session. no manager
                System.out.println("Session end failed, you cannot end a session without an active session.");
            }
        } 
        
    }

    public void startSession(String type, String note) {
        Manager manager = new Manager(false, true, type, note);
    }
=======
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
>>>>>>> Stashed changes

    public void endSession(String type, String note) {
        Manager manager = new Manager(false, false, type, note);
    }

        public void deleteSession() {
        Manager manager = new Manager(true, false, "", "");
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
