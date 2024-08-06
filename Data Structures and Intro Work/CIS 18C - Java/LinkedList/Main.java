import java.util.Scanner;

public class Main {
    private static Highscore[] scoreboard;
    private static int itemCount = 0;

    public static void main(String[] args) {
        scoreboard = new Highscore[5];
        Scanner scanner = new Scanner(System.in);
        String nameIn;
        int scoreIn = 0;
        int index = 0;
        boolean isRunning = true;

        do {
            index = 0;
            System.out.println("\n1.) add score\n2.) add by index\n3.) remove score\n0.) Exit");
            switch (scanner.next()) {
                //add
                case "2": //with index
                    System.out.print("Enter index: ");
                    index = scanner.nextInt();
                case "1": //to end of list
                    System.out.print("Enter the Name: ");
                    nameIn = scanner.next();
                    System.out.print("Enter a Score: ");
                    scoreIn = scanner.nextInt();
                    if (index != 0) {
                        add(new Highscore(nameIn, scoreIn), index);
                    } else {
                        add(new Highscore(nameIn, scoreIn));
                    }
                    break;

                //remove
                case "3":
                    System.out.print("Index to remove: ");
                    remove(scanner.nextInt());
                    break;

                //Exit Program
                case "0":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid Input!\n");
            }
            printScores();
        } while (isRunning);
    }



    public static void printScores() {
        System.out.println("===== Scoreboard =====");
        System.out.println("index\tName\tScore");
        int printCount = 0;
        for (int i = 0; printCount < itemCount && i < scoreboard.length; ++i) {
            if (scoreboard[i] != null) {
                System.out.println(i + ".) " + scoreboard[i].name + " : " + scoreboard[i].score);
                ++printCount;
            }
        }
        System.out.println("======================");
    }


    //===== add Functions ================================
    //standard add
    public static void add(Highscore newScore) {
        if (itemCount == scoreboard.length) {
            Highscore[] newBoard = new Highscore[scoreboard.length + 5];
            for (int i = 0; i < scoreboard.length; ++i) {
                newBoard[i] = scoreboard[i];
            }
            scoreboard = newBoard;
        }

        scoreboard[itemCount++] = newScore;
        System.out.println("Added!");
    }

    //add to certain index
    public static void add(Highscore newScore, int index) {
        if (itemCount == scoreboard.length || index > scoreboard.length) {
            Highscore[] newBoard = new Highscore[index > scoreboard.length ? index + 1 : scoreboard.length + 5];
            for (int i = 0; i < scoreboard.length; ++i) {
                if (i == index) {
                    newBoard[i] = newScore;
                    ++i;
                    while(i < scoreboard.length) {
                        newBoard[i++] = scoreboard[i - 1];
                    }
                    scoreboard = newBoard;
                    return;
                }
                else {
                    newBoard[i] = scoreboard[i];
                }
            }

            scoreboard = newBoard;
            add(newScore);
            itemCount++;
            System.out.println("Added!");
            return;
        }

        //if null between latest item and new item, add next to last item
        if (scoreboard[index - 1] == null) {
            add(newScore);
            return;
        }

        //shift, then add
        for (int i = scoreboard.length - 1; i > index; --i) {
            if (scoreboard[i - 1] != null) {
                scoreboard[i] = scoreboard[i - 1];
            }
        }
        scoreboard[index] = newScore;

        itemCount++;
        System.out.println("Added!");
    }
    //===== add functions *END* ======================================


    //remove by index
    public static void remove(int index) {
        //check if last of index
        if (index + 1 >= scoreboard.length) {
            if (index == scoreboard.length) {
                scoreboard[index] = null;
                itemCount--;
            }
            System.out.println(index < scoreboard.length ? "Removed!" : "No item Found");
            return;
        }

        //remove and for-loop shift items down
        scoreboard[index] = null;
        for (int i = index; i < scoreboard.length; ++i) {
            scoreboard[i] = i + 1 < scoreboard.length ? scoreboard[i + 1] : null;
        }
        itemCount--;
        System.out.println("Removed!");
    }
}
