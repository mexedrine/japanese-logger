Menu: 
- 1. Create New Log
    - 1. Start Study Now
        - 1. Flashcards         
        - 2. Reading             
        - 3. Video Content  
            - 1. Enter a note
                - Prompt: Write your note and hit enter to begin (Char limit????)
            - 2. Start time without a note
                - Prompt: Start time recorded… /n Once you’re finished enter “end” or if you need a break enter “pause”
                    - Prompt: You paused your session at ‘time-variable’ enter “end” to save and exit session or “resume” to continue study
            - 3. Go back
        - 4. Other
            - Prompt: Enter a note (required) to start or enter “5” to go back
        - 5. Go back
- 2. View Total Hours
    - Prints Hours
- 3. Exit
    - Exits

1. Keep Main focused on the menu / program flow

Your Main should only handle the menu system (input, printing options, directing logic).

It should not directly store the study sessions or calculations. Instead, it should call methods from other classes.

2. Create a StudySession class

Purpose: Represents one single study session.

Fields you’d want: String method, LocalDateTime startTime, LocalDateTime endTime, maybe a Duration.

Methods:

startSession() → set startTime

endSession() → set endTime

getDuration() → return duration in minutes/hours

This is like your data model for one block of study.

3. Create a SessionManager (or Management) class

Purpose: Manages a collection of study sessions.

It could store all sessions in an ArrayList<StudySession>.

Methods:

addSession(StudySession session)

getTotalHours() → loop through all sessions, sum durations

listSessions() → print them out nicely

This will keep your data organized.

4. (Optional) A Storage helper class

If you want persistence (saving/loading to CSV, JSON, or text file), you should not mix it into Main or StudySession.

Storage handles saveSessions(List<StudySession>) and loadSessions().

5. How the flow works together

Main → handles menu.

When you choose “Start Study”, Main creates a StudySession and runs its start/end logic.

Once ended, it passes that session to SessionManager.addSession().

When you choose “View Total Hours”, Main just calls sessionManager.getTotalHours() and prints it.