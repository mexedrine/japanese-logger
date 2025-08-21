// Do not read seriously, these are temporary and haven't been reviewed. Maybe be
// completely out of date in terms of current system.

- Main:

  - Merge the view hours and the delete function in the Main method. The initial
    send to StudySession will be a status check, and based on the status check
    it will either send the total hours or print the last session and Main
    will prompt the user to delete it or end it.

- StudySession:

  - Figure out a way that it can store the value of boolean inProgress like
    writing to a file, this can then be used to check if it = true, then
    sending an error back to Main saying you didn't end last session.

  - Pass true boolean to Manager once confirming session, pass false when
    ending the session. This is just a flip from what is received initially
    as inProgress from main.

  - Rename current variable inProgress to isEnd. inProgress now functions how
    the name suggests, after confirming session switch to true

- Manager:

  - Constructor should take input of boolean value isDelete, StudySession calls
    method with false because it is a normal addition, Main will call it with
    true from case 2 to delete the last session information. Print confirm it

  - Method accepts value of "" for type, this prompts the manager to print
    and not write anything when both boolean are false and type and note = ""

  - Everytime method from Manager, if starting (inProgress=false) is called it
    can print the start time, if ending (inProgress=true) print the duration
    of last study session and total. This functionality will be compatible
    with delete from case 2 and view from
