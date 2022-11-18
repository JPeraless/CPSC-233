package Project_233;

import Project_233.util.Logger;

import java.io.File;
import java.util.Scanner;

/**
 * Utility clase in charge of dealing with System.in and most of System.out
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    21/03/2022
 * Tut:     T07
 */
public class Menu {

    /**
     * A Logger to log the execution of the program to an output file
     */
    private static Logger logger;

    /**
     * Scanner created to acquire access to System.in
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * programState == 0 initial stage of the program
     */
    private static final int INITIAL_STAGE = 0;

    /**
     * programState == 1 semi-complete stage of the program
     */
    private static final int SEMI_COMPLETE = 1;

    /**
     * programState == 3 full range of program unlocked
     */
    private static final int FULL_RANGE = 3;

    /**
     * Menu being displayed to the user
     *
     * @param programState Keeps track of the functionalities available to the user
     */
    public static void menuDisplay(int programState) {
        // Program should have menu option which will save data to a (comma separate value) .csv file.
        // Program should have menu option which will load data from a (comma separate value) .csv file.

        System.out.println("\nWhich action would you like to take?");
        System.out.println("(Please enter the number associated with that action)\n");

        System.out.println("\n--- File I/O ---\n");

        // option could disappear once it has been requested
        System.out.println("\t0) Save data to a .csv file.");
        System.out.println("\t1) Load data from a .csv file.");

        System.out.println("\n--- Add general data ---\n");

        System.out.println("\t2) Add a player (name, nickname, role, age).");

        if (programState == INITIAL_STAGE) {
            System.out.println("\n(You must add at least 1 player to unlock more functionalities.)\n");
        }

        if (programState >= SEMI_COMPLETE ) {  // at least one player exists
            System.out.println("\n--- Add player statistics ---\n");

            System.out.println("\t3) Add a kill to a player.");
            System.out.println("\t4) Add a death to a player.");
            System.out.println("\t5) Add a spike defuse to a player.");
            System.out.println("\t6) Add a spike plant to a player.");
            System.out.println("\t7) Add to the amount of points spent by a player.");

            System.out.println("\n--- General output ---\n");

            System.out.println("\t8) Print the players of a team.");
            System.out.println("\t9) Print all the players.");
            System.out.println("\t10) Print the players fulfilling a specific role.");
            System.out.println("\t11) Print the players of a given age range.");
        }

        if (programState == SEMI_COMPLETE || programState == SEMI_COMPLETE + 1) {
            System.out.println("\n(You must add at least 3 players to unlock the special output.)\n");
        }

        if (programState >= FULL_RANGE) {  // special output unlocked (at least 3 players exist)
            System.out.println("\n--- Special output ---\n");

            System.out.println("\t12) Print the top 3 players by no. of kills.");
            System.out.println("\t13) Print the top 3 players by kills/deaths ratio.");
            System.out.println("\t14) Print the top 3 players by kills per 100 points spent.");
            System.out.println("\t15) Print the top 3 players by no. of spikes defused.");
            System.out.println("\t16) Print the top 3 players by no. of spikes planted.");
        }
    }

    /**
     * Asks the user for input and checks for invalid int values
     *
     * @return int reflecting the menu selection of the user
     */
    public static int getInput(int programState) {
        System.out.print("\nYour number selection: ");
        int input = scanner.nextInt();  // assuming the user enters an int
        scanner.nextLine();

        if (programState == INITIAL_STAGE) {
            while (input < -1 || input > 2 ) {
                System.out.print("Please enter a valid number: ");
                input = scanner.nextInt();  // assuming the user enters an int
                scanner.nextLine();
            }
        }
        else if (programState == SEMI_COMPLETE || programState == FULL_RANGE - 1) {
            while (input < -1 || input > 12) {
                System.out.print("Please enter a valid number: ");
                input = scanner.nextInt();  // assuming the user enters an int
                scanner.nextLine();
            }
        }
        else {  // equal to or greater than FULL_RANGE
            while (input < -1 || input > 16) {
                System.out.print("Please enter a valid number: ");
                input = scanner.nextInt();  // assuming the user enters an int
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * Provide a filename for the logger to work on (initialize logger)
     *
     * @param outFile The file in which the data is being written to
     */
    public static void loggerSetup(File outFile) {
        logger = new Logger(outFile);
    }

    /**
     * Adds an entry to the output file
     *
     * @param obj Object (data) being added to outFile
     */
    public static void addEntry(Object obj) {  // (println)
        if (logger != null) {  // if logger has been set up with a filename
            System.out.println(obj);
            logger.entry(obj);
        }
        else {  // if logger has not been initialized
            throw new RuntimeException("It is not possible to add an entry until output(File outFile) has been run.");
        }
    }

    /**
     * Provides Main.outputFileCheck with the ability to get and check user input without using Scanner itself
     *
     * @return True if the user inputs Y or y, False otherwise.
     */
    public static boolean checkAnswer() {
        System.out.println("Anything other than (Y) or (y) is considered as No: ");
        String response = scanner.nextLine();
        return response.equals("Y") || response.equals("y");
    }

    /**
     * Gets a string from the user depending on which functionality requests the user
     *
     * @param function the functionality requested by the user
     * @return String input from the user
     */
    public static String getString(String function) {
        if (function.equals("team")) {  // missing to check if team exists
            System.out.println("Enter the team whose players will be displayed: ");
            return scanner.nextLine();
        }
        else if (function.equals("role")) {  // could check if role exists
            System.out.println("Enter the role of which player will be displayed: ");
            return scanner.nextLine();
        }
        else if (function.equals("nickname")) {
            System.out.println("Enter the nickname of the player: ");
            return scanner.nextLine();
        }
        return null;
    }


    /**
     * Gets the age range to for players between it to be printed. Checks if the starting age is greater than the ending age
     *
     * @return int[] where int[0] is the starting age and int[1] is the ending age
     */
    public static int[] getAgeRange() {
        System.out.println("Enter the age from which to start considering players: ");
        int lowerAge = scanner.nextInt();  // not sure if this causes a printing bug

        System.out.println("Enter the age until which to consider players: ");
        int upperAge = scanner.nextInt();  // same in here

        if (lowerAge > upperAge) {  // make sure that lowerAge <= upperAge
            do {
                System.out.println("The starting age: " + lowerAge + " is greater than the ending age: " + upperAge + ".");
                System.out.println("Please enter appropriate ages.\n");

                System.out.println("Enter the age from which to start considering players: ");  // assuming the user enters an Integer
                lowerAge = scanner.nextInt();

                System.out.println("Enter the age until which to consider players: ");  // assuming the user enters an Integer
                upperAge = scanner.nextInt();

            } while (lowerAge > upperAge);
        }

        return new int[] {lowerAge, upperAge};
    }

    /**
     * Get input from the user on the amount spent by a player
     *
     * @return int inputted by the user of the amount the player spent
     */
    public static int askForAmount() {
        System.out.println("Enter the amount spent: ");
        int amountSpent = scanner.nextInt();

        // check if the amount entered is less than 0
        if (amountSpent < 0) {
            do {
                System.out.println("The amount spent cannot be less than 0. ");
                System.out.println("Please enter another quantity (0 in case nothing was spent).");

                System.out.println("Enter the amount spent: ");
                amountSpent = scanner.nextInt();

            } while (!(amountSpent >= 0));
        }

        return amountSpent;

    }

    /**
     * Gets a player's complete profile (team, name, nickname, role, age)
     *
     * @return Player with the information the user inputted
     */
    public static Player askForPlayer() {
        System.out.println("Enter the player's team: ");
        String team = scanner.nextLine();

        System.out.println("Enter the name of the player: ");
        String name = scanner.nextLine();

        System.out.println("Enter the nickname of the player: ");
        String nickname = scanner.nextLine();

        System.out.println("Enter the role of the player: ");
        String role = scanner.nextLine();

        System.out.println("Enter the age of the player: ");
        String age = scanner.nextLine();

        return new Player(team, name, nickname, role, age);
    }

}
