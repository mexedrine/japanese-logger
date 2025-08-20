public class StudySession {
    private String typeName[] = {
        "Flashcards",
        "Reading",
        "Video",
        "Other"
    };
    
    private int typeID;
    private String note;
    private boolean persistenProgress; // SET TO THE VALUE FROM PERSISTENT STORAGE
    private boolean inProgress = false;
    private boolean isDelete;
    private boolean isEnd;

    public StudySession(boolean isDelete, boolean isEnd) {
        this(isDelete, isEnd, 0, "");
    }

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

    public void endSession(String type, String note) {
        Manager manager = new Manager(false, false, type, note);
    }

        public void deleteSession() {
        Manager manager = new Manager(true, false, "", "");
    }
}
