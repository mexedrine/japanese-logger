import java.time.*;

public class StudySession {
    private String typeName[] = {
            "Flashcards",
            "Reading",
            "Video",
            "Other"
    };

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    private boolean persistState; // SET TO THE VALUE, IF PERSIST FILE IS EMPTY = FALSE
    private String persistData = "Card " + "Value " + "Kobe ";
    private int typeID;
    private String note;
    private String request; // variable to receive the request from main. - start, end, delete, or search

    public StudySession(String request, int typeID, String note) { // all parameters incl.
        this.request = request;
        this.typeID = typeID;
        this.note = note;

        switch (request) {
            case "start": { // start requests
                if (!persistState) { // request is valid

                } else { // invalid request

                }
                break;
            }

            case "end": { // end request
                if (persistState) { // valid end request

                } else { // invalid end request

                }
                break;
            }

            case "delete": { // delete requests
                if (persistState) { // valid delete request

                } else { // invalid delete request

                }
                break;
            }

            case "search": { // search requests
                if (!persistState) { // search is valid (no active session)

                } else { // search is invalid (active session)

                }
                break;
            }

            default: {
            } // add exception handler
        }
    }

    public void HandlePersist(String name, String note, LocalDate date, LocalTime time) { // decide if it should just be
                                                                                          // save or handle
        if (name != "" && note != "") {
            StringBuilder buildString = new StringBuilder();
            buildString.append(name);
            buildString.append(note);
            buildString.append(date);
            buildString.append(time);

            String persistData = buildString.toString();
            // write persist data to file
        }
    }
}
