package mvh.util;

import mvh.enums.WeaponType;
import mvh.world.Hero;
import mvh.world.Monster;
import mvh.world.World;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to assist reading in world file
 * @author Jonathan Hudson
 * @version 1.0
 *
 * Name:    Jose Perales
 * Date:    11/03/2022
 * Tut:     T07
 */
public final class Reader {

    /**
     * Attempts to open the file provided. Creates a World object based off of it.
     * @param fileWorld provided file to attempt to open
     * @return World created based on the file provided.
     */
    public static World loadWorld(File fileWorld) {
        World startingWorld = null;

        // create ArrayLists to store info about world
        ArrayList<ArrayList<String>> specificInfo = new ArrayList<ArrayList<String>>();
        ArrayList<String> generalInfo = new ArrayList<String>();

        // try to open the file
        try {
            // initialize file and buffer readers
            FileReader fileReader = new FileReader(fileWorld);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // get the rows and columns
            int rows = Integer.parseInt(bufferedReader.readLine());
            int cols = Integer.parseInt(bufferedReader.readLine());

            // add info to generalInfo
            for (int i = 0; i < (rows * cols); i++) {
                generalInfo.add(bufferedReader.readLine());
            }

            // initialize the world
            startingWorld = new World(rows, cols);

            // stores the values from the given file into correct entries in the 2d ArrayList specificInfo
            ArrayList<String> line;
            for (int j = 0, k = 0; j < rows; j++, k++) {
                line = new ArrayList<String>();
                for (int l = 0; l < cols; l++) {
                    line.add(generalInfo.get(k));
                }
                specificInfo.add(line);  // add each line to specificInfo
            }

            // 2D ArrayList to copy necessary information
            ArrayList<ArrayList<String>> worldResult = new ArrayList<>();

            bufferedReader.close();  // closing the file
            ArrayList<String> list;
            for (int row = 0; row < rows; row++) {
                list = new ArrayList<String>();
                String[] info;
                String location;
                for (int col = 0; col < cols; col++) {
                    location = specificInfo.get(row).get(col);
                    location = location.replace(" ","");  // replace for replaceAll
                    info = location.split(",");

                    if (info.length <= 3) {
                        list.add(null);
                    }

                    else {
                        if (info[2].equals("HERO")) {  // for HEROES
                            int heroHealth = Integer.parseInt(info[4]);
                            char heroSymbol = info[3].charAt(0);
                            int weaponStrength = Integer.parseInt(info[5]);
                            int armorStrength = Integer.parseInt(info[6]);
                            Hero hero = new Hero(heroHealth, heroSymbol, weaponStrength, armorStrength);
                            startingWorld.addEntity(row, col, hero);
                            list.add(String.valueOf(heroSymbol));
                        }

                        else if (info[2].equals("MONSTER")) {  // for MONSTERS
                            char monsSymbol = info[3].charAt(0);
                            int monsHealth = Integer.parseInt(info[4]);
                            char charOfWeapon = info[5].charAt(0);
                            WeaponType weaponType = WeaponType.getWeaponType(charOfWeapon);
                            Monster monster = new Monster(monsHealth, monsSymbol, weaponType);
                            startingWorld.addEntity(row, col, monster);
                            list.add(String.valueOf(monsSymbol));

                        }
                    }
                }
                // add the information necessary to worldResult in each row iteration
                worldResult.add(list);
            }
        }
        catch (IOException e) {  // if the file fails to open
            e.printStackTrace();
        }
        return startingWorld;
    }
}