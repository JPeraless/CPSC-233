package Project_233.util;

import Project_233.Player;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class in charge of reading the file inputted by the user (in case there is one)
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    21/03/2022
 * Tut:     T07
 */
public class Reader {

    /**
     * Attempts to open the file provided. If successful, stores the data into a HashMap
     *
     * @param inFile Given .csv file from which input is read
     * @return HashMap with Player objects as keys and their stats as values
     */
    public static HashMap<Player, int[]> loadFile(File inFile) {
        HashMap<Player, int[]> infoFromFile = new HashMap<>();

        try {
            // Initialize file and buffer readers
            FileReader fileReader = new FileReader(inFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // reading from the file
            Scanner scanner = new Scanner(bufferedReader);
            while (scanner.hasNextLine()) {  // loop until all the file has been read
                String line = scanner.nextLine();

                String[] info = line.split(",");  // split by commas to distinguish the data

                // assignment of all the values
                String team = info[0];
                String name = info[1];
                String nickname = info[2];
                String role = info[3];
                String age = info[4];
                int kills = Integer.parseInt(info[5]);  // .csv file must have 0 if there are no stats registered
                int deaths = Integer.parseInt(info[6]);
                int defuses = Integer.parseInt(info[7]);
                int plants = Integer.parseInt(info[8]);
                int spent = Integer.parseInt(info[9]);

                // format the values as appropriate
                Player player = new Player(team, name, nickname, role, age);
                int[] stats = new int[] {kills, deaths, defuses, plants, spent};

                // add the data from each line to infoFromFile
                infoFromFile.put(player, stats);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return infoFromFile;
    }

}
