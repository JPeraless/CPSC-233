package Project_233;

import Project_233.util.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main class executing the program
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    21/03/2022
 * Tut:     T07
 */
public class Main {
    /**
     * Executes the program
     *
     * @param args arguments entered by the user
     */
    public static void main(String[] args) {
        boolean loadData = false;  // flag for loading data to a file
        boolean outputData = false;  // flag for reading data from a file
        int programState = 0;  // keeps track of the state of the program

        argumentCheck(args);  // must be provided 2 arguments

        boolean controlVar = true;

        while (controlVar) {  // -1 to exit the program
            System.out.println("\n***********************************************************************************");
            // Player.allPlayers();

            Menu.menuDisplay(programState);
            int input = Menu.getInput(programState);

            if (input == 0 && !outputData) {  // save data to a .csv
                File outFile = new File(args[1]);
                outputFileCheck(outFile);
                Menu.loggerSetup(outFile);
                outputData = true;

            }
            else if (input == 1 && !loadData) {  // load data from a .csv file
                File inFile = new File(args[0]);
                inputFileCheck(inFile);

                // only creates a Player with all their info, stats are not added
                HashMap<Player, int[]> givenInfo = Reader.loadFile(inFile);

                // add stats manually
                for (Player player : givenInfo.keySet()) {  // for each player in the file
                    int[] stats = givenInfo.get(player);
                    player.setKills(stats[0]);  // set the kills
                    player.setDeaths(stats[1]);  // set the deaths
                    player.setDefuses(stats[2]);  // set the defuses
                    player.setPlants(stats[3]);  // set the plants
                    player.setAmount(stats[4]);  // set the amount

                    programState += 1;
                }

                System.out.println("\nProgram state: " + programState);

                loadData = true;

            }
            else if (input == 2) {  // add a player
                Player player = Menu.askForPlayer();
                System.out.println("\n" + player);
                programState += 1;

            }
            else if (input == 3) {  // add a kill
                Player.addKill(Player.getPlayer(Menu.getString("nickname")));
            }
            else if (input == 4) {  // add a death
                Player.addDeath(Player.getPlayer(Menu.getString("nickname")));
            }
            else if (input == 5) {  // add a spike defuse
                Player.addDefuse(Player.getPlayer(Menu.getString("nickname")));;
            }
            else if (input == 6) {  // add a spike plant
                Player.addPlant(Player.getPlayer(Menu.getString("nickname")));
            }
            else if (input == 7) {  // add to amount spent
                Player.addAmount(Player.getPlayer(Menu.getString("nickname")), Menu.askForAmount());
            }
            else if (input == 8) {  // print players of a team
                Player.playersByTeam(Menu.getString("team"));
            }
            else if (input == 9) {  // print all players
                Player.allPlayers();
            }
            else if (input == 10) {  // print player by role
                Player.playersByRole(Menu.getString("role"));
            }
            else if (input == 11) {  // print players of an age range
                int[] ageRange = Menu.getAgeRange();
                Player.playersByAge(ageRange[0], ageRange[1]);
            }
            else if (input == 12) {  // top 3 by kills
                Player.top3Kills();
            }
            else if (input == 13) {  // top 3 by kills/deaths ratio
                Player.top3Ratio();
            }
            else if (input == 14) {  // top 3 by kills per point
                Player.top3KillsPerPoint();
            }
            else if (input == 15) {  // top 3 by spikes defused
                Player.top3SpikeDefuses();
            }
            else if (input == 16) {  // top 3 by spikes planted
                Player.top3SpikePlants();
            }
            else if (input == -1) {
                controlVar = false;
            }
        }

        // load the data to a file
        if (outputData) {
            ArrayList<String> allPlayers = Player.playersToString();
            for (String player : allPlayers) {
                Menu.addEntry(player);
            }
        }
    }

    /**
     * Verifies that the program is provided with the arguments necessary to run
     */
    private static void argumentCheck(String[] args) {
        if (args.length != 2) {
            System.err.println("Program requires 2 arguments to run.");
            System.err.println("No. of arguments provided: " + args.length);
            System.exit(1);
        }
    }

    /**
     *
     * @param inFile file given by the user from which input will be read
     */
    private static void inputFileCheck(File inFile) {
        // general check of the file provided
        if (!inFile.exists() || !inFile.isFile() || !inFile.canRead()) {
            System.err.printf("The file %s does not exist.%n", inFile.getAbsoluteFile());
            System.exit(1);
        }
    }

    /**
     *
     * @param outFile file in which the output will be stored if requested by the user
     */
    private static void outputFileCheck(File outFile) {
        // if outFile has already been created
        if (outFile.isFile() && outFile.exists() && outFile.canWrite()) {
            System.out.println("Do you want to overwrite the output file? ([Y]es or [N]o)");
            if (Menu.checkAnswer()) {
                outFile.delete();
            } else {
                System.err.printf("The program will end so that %s is not overwritten.", outFile.getAbsoluteFile());
                System.exit(0);
            }
        }

        // if outFile needs to be created
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                System.err.printf("The output file %s could not be created", outFile.getAbsoluteFile());
                e.printStackTrace();
            }
        }

        // check if there is an error in outFile
        if (!outFile.isFile() || !outFile.exists() || !outFile.canWrite()) {
            System.err.printf("There was an error with the %s", outFile.getAbsoluteFile());
            System.exit(1);
        }
    }


}
