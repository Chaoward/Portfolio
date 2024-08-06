package src;
//import com.twilio.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***** MASS TEXT MESSAGE SENDER ******************
 * Authors: HowardN, Adian, JasonJ, CarlosG
 *
 * This program will take message template or a user
 * written message then take the users contacts.txt and
 * send a personalized message to each contact using
 * the selected message.
 ************************************************/

public class Main {
    private static final String USER_FILE_PATH = "src\\src\\data\\users.txt";
    private static ArrayList<User> users = new ArrayList();
    //Main Global Scanner
    private static Scanner input = new Scanner(System.in);
    private static MessageManager messageManager = new MessageManager();
    private static ContactsManager contactsManager = new ContactsManager();
    private static History history = new History();
    //For displaying correct user or password request

    public static void main(String[] args) throws IOException {

        boolean runLoop = true;
        boolean runLoop2 = true;

        // the first do while run loop starts here for the login
        do {
            readUsers();
            //Get Username and Password from user
            String username = getUserOrPasswordI("username");
            String password = getUserOrPasswordI("password");
            int userChoice;
            // if (foundUser == null || password == null) check the username and password
            //
            User foundUser = linearUserSearch(username, password);

            if (foundUser == null) {
                System.out.println("Login attempt failed user or password are incorrect");
                //attempt counter here?
                // If the password is correct move onto the User Interface loop.
            } else {
                System.out.println("------Access Granted------\n");
                runLoop = true;
                contactsManager.retrieve(foundUser.getUsername());
                messageManager.retrieve(foundUser.getUsername());

                //If user logged in read all messaged that are saved to file

                do {
                    //Display message menu and get choice
                    userChoice = menuInput(username);
                    input.nextLine(); //clear buffer

                    //Examine input
                    if (userChoice == 1) {
                        //Display contact menu and get choice
                        contactsMenu(userChoice);
                    } else if (userChoice == 2) {
                        messageMenu(userChoice);
                    } else if (userChoice == 3) {
                        messageSender(userChoice);
                    } else if (userChoice == 4) {
                        //more history options can be explored
                        history.display();
                    } else if (userChoice == 5) {
                        runLoop = false;
                    } else if (userChoice == 6) {
                        contactsManager.save();
                        messageManager.save();
                        System.exit(0);
                    }
                } while (runLoop == true);
                contactsManager.logOut();
                messageManager.logOut();
                history = new History();
            }

        } while (runLoop2 == true);


    }


    public static void readUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH));
            String[] lineData;

            while (reader.ready()) {
                lineData = reader.readLine().split("~");
                users.add(new User(lineData[0], lineData[1]));
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    //===== getUserOrPassword =============================================

    /**
     * Utilizes input to either display text that asks for a password or
     * username, then stores them, or displays a message explaining t
     * he argument was invalid if it was
     * Returns -> String that contains a password or username,
     * based on the argument, or an empty string
     */
    //========================================================
    public static String getUserOrPasswordI(String q) {
        //Variable to hold the username or password entered
        String temp;
        //Check contents of q
        if (q == "username") {
            //Get Username
            System.out.println("Please log in.\n" +
                    "Enter your username:");
            temp = input.nextLine();
        } else if (q == "password") {
            //Get Password
            System.out.println("Please enter your password:");
            temp = input.nextLine();
        } else {
            System.out.println("Invalid Argument");
            temp = "";
        }
        return temp;
    }
    //===== linearUserSearch =============================================

    /**
     * Uses linear search to find a username that matches that
     * which was inputted.
     * Returns -> User object with information matching the username's
     */
    //========================================================
    public static User linearUserSearch(String u, String p) {
        boolean searching = true;
        User temp = null;
        //searches through all users to match inputted values
        for (int i = 0; searching && i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(u)) {
                //return null if password is wrong
                temp = users.get(i).passwordEquals(p) ? users.get(i) : null;
                searching = false;
            }
        }
        return temp;
    }
    //===== menuInput =============================================

    /**
     * Displays possible actions and the inputs required to access the
     * functions for multiple menus
     * <p>
     * Parameters:
     * u    = username
     * type = type of menu required
     * <p>
     * Returns -> Integer representing menu option
     */
    //========================================================
    public static int menuInput(String u) {
        //Display message action menu
        System.out.println("\nHello " + u +
                ", Welcome to the Mass Text Message Sender!\n" +
                "What would you like to do? \n" +
                "1. Add/Remove/Display Contacts\n" +
                "2. Add/Remove/Display Messages\n" +
                "3. Send Message\n" +
                "4. Recent Activity\n" +
                "5. Log out\n" +
                "6. Exit Program");

        //Return input
        return input.nextInt();
    }
    //===== contactMenu =============================================

    /**
     * Displays possible actions and the inputs required to access the
     * functions for contactsMenu
     * Parameters:
     * ContactsManager contactsManager
     * int userChoice
     */
    //================================================================
    public static void contactsMenu(int userChoice) {
        System.out.println("1. Add Contact\n2. Remove Contact\n3. Display all Contacts\n4. Return to Main Menu");
        userChoice = input.nextInt();
        input.nextLine(); //clear buffer

        if (userChoice == 1) {  //add new contact
            System.out.println("Enter name:\n");
            String name = input.nextLine();
            System.out.println("Enter phone number:\n");
            String number = new String(input.nextLine());
            contactsManager.add(name, number);
        } else if (userChoice == 2) {   //remove a contact
            contactsManager.displayContacts();
            System.out.println("Enter name of contact to be removed");
            String name = input.nextLine();
            contactsManager.remove(name);

        } else if (userChoice == 3) {   //
            contactsManager.displayContacts();
        } else {
            System.out.println("Invalid option");
        }
    }
    //===== messageMenu =============================================
    /**
     * Displays possible actions and the inputs required to access the
     * functions for MessageMenu
     * Parameters:
     * MessageManager messageManager
     * int userChoice
     */
    //================================================================
    private static void messageMenu(int userChoice) {
        System.out.println("1. Add message\n" +
                "2. Remove message\n" +
                "3. Display all message\n" +
                "4. Display messages by group\n" +
                "5. Return to Main Menu");
        userChoice = input.nextInt();
        input.nextLine(); //clear buffer

        if (userChoice == 1) {  //adding msg
            String newTitle;
            String newMessage;

            System.out.println("Enter your message here and place an \"`\" for the name placement.\n");
            //get user msg
            newMessage = input.nextLine();
            System.out.println("Tag Category?\n1.) YES\n2.)NO ");
            if (input.nextInt() == 1) {
                input.nextLine(); //clear buffer
                System.out.println("Enter Category: ");
                newTitle = input.nextLine();
                messageManager.add(newMessage, newTitle);
                return;
            }
            messageManager.add(newMessage);

        } else if (userChoice == 2) {   //removing msg
            if (messageManager.size() == 0) {
                System.out.println("No messages to delete");
                return;
            }
            // delete a message from the messageManager
            System.out.println("What message would you like to delete? (negative to cancel)\n");
            messageManager.displayMessage();
            userChoice = input.nextInt();
            input.nextLine(); //clear buffer
            if (userChoice < 0) return; //canceled
            messageManager.remove(userChoice);

        } else if (userChoice == 3) {   //display msg
            //display all the current messages
            System.out.println("Here is your saved messages.\n");
            messageManager.displayMessage();
        }
        else if (userChoice == 4) { //display by title
            System.out.println("Enter Category Name: ");
            messageManager.displayByTitle(input.nextLine());
        }

    }

    private static void messageSender(int userChoice) {
        System.out.println("Choose a message: ");
        messageManager.displayMessage();
        userChoice = input.nextInt();
        if (userChoice >= messageManager.size()) {
            System.out.println("Invalid Section, Canceled!");
            return;
        }
        Messages msg = messageManager.get(userChoice);

        System.out.println("Sending...\n\n");
        for (String name : contactsManager.nameSet()) {
            System.out.println("Sent: " + msg.nameInput(name));
            //push to history
            history.push(msg.nameInput(name));

            /* SEND SMS CODE HERE */
        }
    }
}


