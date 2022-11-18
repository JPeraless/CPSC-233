package code.util;

import code.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class in charge of reading input files
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    11/04/2022
 * Tut:     T07
 */
public class Reader {

    /**
     * Attempts to open the file provided. If successful, stores the data into a HashMap
     *
     * @param inFile Given .csv file from which input is read
     * @return int keeping track of the number of players loaded
     */
    public static int loadFile(File inFile) {
        int playerCount = 0;

        try {
            // Initialize file and buffer readers
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // reading from the file
            Scanner scanner = new Scanner(bufferedReader);
            while (scanner.hasNextLine()) {  // loop until all the file has been read
                String line = scanner.nextLine();

                String[] info = line.split(",");  // split by commas to distinguish the data

                // info assignment
                String team = info[0];
                String name = info[1];
                String nickname = info[2];
                String role = info[3];
                String age = info[4];

                // stats assignment
                int kills = Integer.parseInt(info[5]);  // .csv file must have 0 if there are no stats registered
                int deaths = Integer.parseInt(info[6]);
                int defuses = Integer.parseInt(info[7]);
                int plants = Integer.parseInt(info[8]);
                int spent = Integer.parseInt(info[9]);

                // create the player
                Player player = new Player(team, name, nickname, role, age);

                // set all the player's stats
                player.setKills(kills);
                player.setDeaths(deaths);
                player.setDefuses(defuses);
                player.setPlants(plants);
                player.setAmount(spent);

                playerCount += 1;
            }
        }
        catch (IOException e) {
            System.out.println("File could not be read");
            e.printStackTrace();
        }

        return playerCount;
    }

}
