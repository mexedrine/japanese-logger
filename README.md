cool stuff, logs anything, not just japanese, no fluff just pure utility.
use it properly if you download it, i didn't make it foolproof, because i am lazy

//

!!! TO DO: 
1. Set your own timezone in the program at line 12, ZoneId zoneId = ZoneId.of("America/Chicago");, google java zoneid values i guess.

2. Reccomend you add all the different study activities you do into the println message in the startSession() method, there isn't a functionality that limits the inputs for this so I set my own standard. 

3. Make sure you have sessions.csv in the same folder as the java script. 

4. Ready to use.

5. Read the csv after your first use, pay attention to the format, manual editing is currently required for any fixes needed on a session.

//

How to use:
1. Have java installed.

2. Execute program with "java Logger" command in terminal.

3. Start session through menu prompts.

4. Keep program open while studying.

5. End session through menu prompt before closing terminal to save to file.

6. Exit or print hours through menu prompt.

//

Development to-do:

- No commas in note, fix by wrapping values or move to actual csv package.

- Function to export to spreadsheet?
 
- Hours w/ minutes display.
