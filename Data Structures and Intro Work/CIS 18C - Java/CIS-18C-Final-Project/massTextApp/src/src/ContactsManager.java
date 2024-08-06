package src;

import java.io.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;


/***** ContactsManager **************************
 * This class manages the current login user's contact
 * list, and will save updated info into a file. The
 * information is save by combining the username and
 * the contact list as one string. When retrieving contacts
 * the class will read data into a unordered collection (Hashmap)
 *
 * IMPORTANT: INSTANCE OBJ MUST CALL retrieve() TO
 *          READ/EDIT THE USERS DATABASE!!!
 *
 * File Name: contacts.txt
 *
 * Save File Format:
 *      - ";" : splits between userName and contactList
 *      - "~" : splits between pair of contact information
 *      - "/" : splits between contactName and their phoneNumber
 ************************************************/

public class ContactsManager {
    private static final String FILE_PATH = "src\\src\\data\\contacts.txt";
    private HashMap<String, String> contactList;
    private String curUsername;
    private static BufferedReader reader;
    private static PrintWriter writer;

    ContactsManager() {
        this.contactList = new HashMap<>();
        this.curUsername = "";
    }



    /////// Methods ////////////////////////////////////////////////

    //===== getUserName ==============================
    /** Returns the current contact list's UserName. */
    //================================================
    public String getUserName() {
        return this.curUsername;
    }


    //===== getNumber ================================
    /** Returns the phone number of the passed in name. */
    //================================================
    public String getNumber(String contactName) {
        return this.contactList.get(contactName);
    }


    //===== getNumberStr ==========================
    /** Returns the phone number of the passed in name
     * as a String with general phone # format.
     * If phone# is less than 10 digits return # as is. */
    //================================================
    public String getNumberStr(String userName) {
        String num = this.contactList.get(userName).toString();
        //if number is 10 or greater digits, use phone# format else return # as is
        return num.length() >= 10 ?
                "(" + num.substring(0, 3) + ")-" + num.substring(3, 6) + "-" + num.substring(6) :
                num;
    }

    //===== nameSet ===================================
    /** Returns the name Set. */
    //=================================================
    public Set<String> nameSet() {
        return this.contactList.keySet();
    }


    //===== retrieve ==================================
    /** reads contacts.txt and retrieves the passed in
     * user's contact list as a single string. Splits the string
     * into pairs of name and number and store s them together in contactList */
    //=================================================
    public void retrieve(String username) {
        this.contactList = new HashMap<>(); //empties the current collection
        String[] data = {};

        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            boolean userFound = false;

            //searches for the passed in userName assigns line to data variable
            while (!userFound && reader.ready()) {
                String[] curLine = reader.readLine().split(";");
                if (curLine[0].equals(username)) {
                    //
                    data = curLine[1].split("~");
                    this.curUsername = curLine[0];
                    userFound = true;
                }
            }
            if (!userFound) {
                this.curUsername = username;
                reader.close();
                return;
            }

            String[] splitPair;
            for (String pair : data) {
                splitPair = pair.split("/");
                this.contactList.put(splitPair[0], splitPair[1].trim());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    //===== *END* retrieve *END* ==============================



    //===== displayContacts ===================================
    /** displays each contact name and number form the current
     * contactList in the console. */
    //=========================================================
    public void displayContacts() {
        for (String name : this.contactList.keySet()) {
            System.out.println(name + "  :  " + getNumberStr(name));
        }
    }
    //===== *END* displayContacts *END* =======================



    //===== add ===============================================
    /** adds a new contact with passed in name and numebr to
     * contactList */
    //=========================================================
    public boolean add(String username, String number) {
        if (this.contactList.containsKey(username)) return false;
        this.contactList.put(username, number);
        return this.contactList.containsKey(username);
    }
    //===== *END* add *END* ====================================



    //===== remove ===========================================
    /** removes the passed in username from contactList */
    //========================================================
    public boolean remove(String username) {
        if (this.contactList.containsKey(username)) {
            System.out.println("Removed!");
            return this.contactList.remove(username, this.contactList.get(username));
        }
        return !this.contactList.containsKey(username);
    }
    //===== *END* remove *END* ===============================



    //===== save =============================================
    /** updates save file with updated data. Reads each file line
     * and stores it in memory but updates the current login data.
     * Writes to file afterwards.*/
    //========================================================
    public void save() {
        File contactsFile = new File((FILE_PATH));
        boolean saved = false;
        ArrayList<String> fileData = new ArrayList<>(5);

        //read and copy current data from the file
        try {
            reader = new BufferedReader(new FileReader(contactsFile));
            String[] lineData; //array variable to split current reading line

            do {
                lineData = reader.readLine().split(";");
                //if current user's data, add updated data
                //else add the current line
                if (lineData[0].equals(this.curUsername)) {
                    fileData.add(listToString());
                    saved = true;
                }
                else {
                    fileData.add(lineData[0] + ";" + lineData[1]);
                }
            } while (reader.ready());

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }

        //if the updated data is not saved, add to the end of arrayList
        if (!saved) {
            fileData.add(listToString());
        }

        //write updated data to the file
        try {
            writer = new PrintWriter(contactsFile);
            //writes each array item as each line in file
            for (String line: fileData) {
                writer.println(line);
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    //===== *END* save *END* =================================


    //===== logOut ===========================================
    /** saves and empties the current contactList and userName. */
    //========================================================
    public void logOut() {
        if (this.curUsername == "") return;
        save();
        this.contactList = new HashMap<>();
        this.curUsername = "";
    }
    //===== *END* logOut *END* ===============================



    //==== listToString ======================================
    /** Returns the current hashmap data as a singular String.
     * Format: [userName] ; [contactName / phoneNumber] ~ [contactName / phoneNumber] ...
     * N0 whitespace except for any names with them. */
    //========================================================
    private String listToString() {
        String updatedLine = this.curUsername + ";";
        for (String name : this.contactList.keySet()) {
            updatedLine += name + "/" + this.contactList.get(name).toString() + "~";
        }
        updatedLine += "`";

        return updatedLine.replace("~`", ""); //removes the last "~"
    }
    //===== *END* listToString *END* ========================
}
