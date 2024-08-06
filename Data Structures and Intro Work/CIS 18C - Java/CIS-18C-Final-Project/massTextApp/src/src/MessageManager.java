package src;

import java.io.*;
import java.util.ArrayList;

/***** MessageManager **************************
 * This class manages the login user's messages
 * and will write and read the user's message file.
 * When retrieving messages, the data is stored in
 * a collection (arrayList). The class will save to
 * a file named as the user under the messages folder;
 * category and message string is separated by "~".
 *
 * IMPORTANT: INSTANCE OBJ MUST CALL retrieve() TO
 *         READ/EDIT THE USERS DATABASE!!!
 *
 * File Name: [Username].txt
 *      - file is named after the user
 *
 * Save File Format:
 *      - "~" : splits between title and message string
 *      - white space under then "\n" messages are allowed
 ************************************************/

public class MessageManager {
    private static final String FOLDER_PATH = "src\\src\\data\\messages\\";
    private ArrayList<Messages> messageList;
    private String curUsername;
    private static BufferedReader reader;
    private static PrintWriter writer;

    MessageManager() {
        this.messageList = new ArrayList<>(5);
        this.curUsername = "";
    }


    //===== retrieve ==================================
    /** retrieves the passed in user's messages and stores
     * them into the current messageList. */
    //=================================================
    public void retrieve(String username) {
        logOut();

        try {
            reader = new BufferedReader(new FileReader(FOLDER_PATH + username + ".txt"));
            this.curUsername = username;

            while (reader.ready()) {
                String[] lineData = reader.readLine().split("~");
                this.messageList.add(new Messages(lineData[1] , lineData[0]));
            }

            reader.close();
        } catch (IOException e) { //if the file is not found create a new file
            try {
                writer = new PrintWriter(FOLDER_PATH + username + ".txt");
                writer.print("");
                writer.flush();
            } catch (IOException e2) {
                System.out.println(e2.toString());
            }
        }
    }
    //===== *END* retrieve *END* ==============================


    //===== displayMessage ====================================
    /** Display the current messageList with their index and titles. */
    //=========================================================
    public void displayMessage() {
        System.out.println("Index\t:\tCategory\t:\tMessage\n");
        for (Messages msg : this.messageList) {
            System.out.println(this.messageList.indexOf(msg) + "\t:\t"
                    + msg.getTitle() + "\t:\t"
                    + msg.getContent());
        }
    }

    //===== displayByTitle =======================================
    /** Display all messages in the current list that belong to the
     * passed title. */
    //============================================================
    public void displayByTitle(String title) {
        if (title.isBlank()) title = "";
        System.out.println("Category: " + (title.equals("") ? "no category" : title)
                + "\nIndex\t:\tMessage");

        for (Messages msg : this.messageList) {
            if (msg.getTitle().equals(title)) {
                System.out.println(this.messageList.indexOf(msg) + ".) " + msg.getContent());
            }
        }
    }

    //===== get ==============================================
    public Messages get(int i) {
        return messageList.get(i);
    }

    //===== add ===============================================
    /** adds a new message to the arrayList. */
    //=========================================================
    public boolean add(String message) {
        Messages newMsg = new Messages(message);
        this.messageList.add(newMsg);
        return this.messageList.contains(newMsg);
    }

    //===== add ===============================================
    /** adds a new message to the arrayList with a category. */
    //=========================================================
    public boolean add(String message, String title) {
        Messages newMsg = new Messages(message, title);
        this.messageList.add(newMsg);
        return this.messageList.contains(newMsg);
    }

    //===== remove ===========================================
    /** removes the passed in username from contactList */
    //========================================================
    public void remove(int index) {
        this.messageList.remove(index);
        System.out.println("Removed!");
    }



    //===== save =============================================
    /** updates save file. */
    //========================================================
    public void save() {
        File msgFile = new File((FOLDER_PATH + this.curUsername + ".txt"));

        //write updated data to the file
        try {
            writer = new PrintWriter(msgFile);
            //writes each array item as each line in file
            for (Messages msg: this.messageList) {
                writer.println(msg.getTitle() + "~" + msg.getContent());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    //===== *END* save *END* =================================



    //===== logOut ===========================================
    /** saves and empties the current messageList and userName. */
    //========================================================
    public void logOut() {
        if (this.curUsername == "") return;
        save();
        this.messageList = new ArrayList<>(5);
        this.curUsername = "";
    }
    //===== *END* logOut *END* ===============================


    //===== size ==============================================
    public int size() {
        return this.messageList.size();
    }

}
