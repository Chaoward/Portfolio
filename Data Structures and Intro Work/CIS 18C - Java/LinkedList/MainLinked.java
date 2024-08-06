import java.util.LinkedList;
import java.util.Scanner;

public class MainLinked {
    public static LinkedList<Highscore> scoreboard;

    public static void main(String[] args) {
        scoreboard = new LinkedList<>();
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
                case "2":
                    System.out.print("Enter index: ");
                    index = scanner.nextInt();
                case "1":
                    System.out.print("Enter the Name: ");
                    nameIn = scanner.next();
                    System.out.print("Enter a Score: ");
                    scoreIn = scanner.nextInt();
                    System.out.print("Add to a certain Index? (y or n): ");
                    if (index != 0) {
                        scoreboard.add(Math.abs(index) >= scoreboard.size() ? scoreboard.size() - 1 : index,
                                new Highscore(nameIn, scoreIn));
                    } else {
                        scoreboard.add(new Highscore(nameIn, scoreIn));
                    }
                    break;

                //remove
                case "3":
                    System.out.print("Index to remove: ");
                    scoreboard.remove(scanner.nextInt());
                    break;

                //Exit Program
                case "0":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid Input!\n");
            }
            printScore();
        } while (isRunning);
    }


    public static void printScore() {
        System.out.println("\n===== Scoreboard =====");
        System.out.println("index\tName\tScore");
        for (Highscore item : scoreboard) {
            System.out.println(scoreboard.indexOf(item) + ".) " + item.name + " : " + item.score);
        }
        System.out.println("======================");
    }

}
