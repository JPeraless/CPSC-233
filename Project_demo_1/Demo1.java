import java.util.*;

/**
 * Name:    Jose Perales
 * Date:    21/03/2022
 */

public class Demo1 {

    private static final Scanner scanner = new Scanner(System.in);


    /**
     * Display the Initial Project_233.Menu of the Game
     */
    public static void initialMenu() {
        System.out.println("\nWhich action would you like to take?");
        System.out.println("(Please enter the number associated with that action)\n");
        System.out.println("(You must add at least one team and one player to unlock more functionalities.)\n");

        System.out.println("\n--- Add general data ---\n");

        System.out.println("\t1) Add a team.");
        System.out.println("\t2) Add a player to a team (name, nickname, role, age).");
    }


    /**
     * Get user input for Initial menu
     * @return int input option user selects
     */
    public static int initialInput() {
        System.out.print("\nEnter your selection: ");
        int input = scanner.nextInt();  // assuming the user enters an int
        scanner.nextLine();  // useless java

        while (input != 1 && input != 2) {
            System.out.print("Please enter a valid number: ");
            input = scanner.nextInt();  // assuming the user enters an int
            scanner.nextLine();
        }

        return input;
    }


    /**
     * Display the whole menu after user has input General Project_233.Menu
     */
    public static void menuDisplay() {
        System.out.println("\nWhich action would you like to take?");
        System.out.println("(Please enter the number associated with that action)\n");

        System.out.println("\n--- Add general data ---\n");

        System.out.println("\t1) Add a team.");  // team constructor?
        System.out.println("\t2) Add a player to a team (name, nickname, role, age).");  // player constructor?

        System.out.println("\n--- Add player statistics ---\n");

        System.out.println("\t3) Add a kill to a player.");  // ********************************************************
        System.out.println("\t4) Add a death to a player.");  // *******************************************************
        System.out.println("\t5) Add a spike defuse to a player.");  // ************************************************
        System.out.println("\t6) Add a spike plant to a player.");  // *************************************************
        System.out.println("\t7) Add to the amount of points spent by a player.");  // *********************************

        System.out.println("\n--- General output ---\n");

        System.out.println("\t8) Print the players of a team (by nickname).");  // done in Project_233.Player **********************
        System.out.println("\t9) Print all the players (by nickname).");  // *******************************************
        System.out.println("\t10) Print the players fulfilling a specific role.");  // *********************************
        System.out.println("\t11) Print the players of a given age range.");  // ***************************************

        System.out.println("\n--- Special output ---\n");

        System.out.println("\t12) Print the top 3 players by no. of kills."); // ***************************************
        System.out.println("\t13) Print the top 3 players by kills/deaths ratio.");  // ********************************
        System.out.println("\t14) Print the top 3 players by kills per 100 points spent.");  // ************************
        System.out.println("\t15) Print the top 3 players by no. of spikes planted.");  // *****************************
        System.out.println("\t16) Print the top 3 players by no. of spikes defused.");  // *****************************
    }


    /**
     * Get user input for the whole menu Game
     * @return int input option user selects
     */
    public static int getInput() {
        System.out.print("\nYour number selection: ");
        int input = scanner.nextInt();  // assuming the user enters an int
        scanner.nextLine();  // useless java

        while (input < 1 || input > 16) {
            System.out.print("Please enter a valid number: ");
            input = scanner.nextInt();  // assuming the user enters an int
            scanner.nextLine();
        }

        return input;
    }


    /**
     *Updates the ArrayList of all the teams in existence
     *
     * @param teamsList an arrayList of the teams in existence
     * check if the team entered by the user already exists
     */
    public static void addTeam(ArrayList<String> teamsList) {
        System.out.print("Enter the name of the team: ");
        String teamName = scanner.nextLine();  // assuming the user inputs a String

        // check if the team already exists
        if (teamsList.contains(teamName)) {
            boolean controlVar = true;
            while (controlVar) {
                System.out.println("The team " + teamName + "already exists.");
                System.out.println("Would you like to add another team? ([Y]es or [N]o)");  // assuming the user enters a String
                teamName = scanner.nextLine();
                if (teamName.equals("N") || teamName.equals("n")) {
                    return;

                }
                else if (teamName.equals("Y") || teamName.equals("y")) {
                    System.out.println("Enter the name of the team: ");
                    teamName = scanner.nextLine();
                    if (!teamsList.contains(teamName)) {
                        controlVar = false;
                    }
                }
            }
        }

        teamsList.add(teamName);
    }


    /**
     * Creates a profile for a player with their info, while checking that the team matches the player created.
     * @param teamsList an arrayList of the teams in existence
     * check if the team entered by the user already exists
     * @return playerInfo updated (Name, Nickname, Role, Age)
     */
    public static ArrayList<String> playerProfile(ArrayList<String> teamsList) {
        ArrayList<String> playerInfo = new ArrayList<String>();

        // get the team of the player
        System.out.println("Enter the player's team: ");  // index 0
        String teamName = scanner.nextLine();

        // check if the team already exists
        if (!teamsList.contains(teamName)) {
            boolean controlVar = true;
            while (controlVar) {
                System.out.println("The team " + teamName + " has not been registered.");  // not giving the option to the user to add a team
                System.out.println("Would you like to try a different team? ([Y]es or [N]o)");  // assuming the user enters a String
                String response = scanner.nextLine();
                if (response.equals("N") || response.equals("n")) {
                    return new ArrayList<>();
                }
                else if (response.equals("Y") || response.equals("y")) {
                    System.out.println("Enter the player's team: ");
                    teamName = scanner.nextLine();
                    if (teamsList.contains(teamName)) {
                        controlVar = false;
                    }
                }
            }
        }

        // get the name of the player
        System.out.println("Enter the name of the player: ");  // index 1
        String name = scanner.nextLine();

        // get the nickname of the player
        System.out.println("Enter the nickname of the player : ");  // index 2
        String nickName = scanner.nextLine();

        // get the role of the player
        System.out.println("Enter the role of the player: ");  // index 3
        String role = scanner.nextLine();

        // get the age of the player
        System.out.println("Enter the age of the player: ");  // index 4
        String age = scanner.nextLine();

        playerInfo.add(teamName); playerInfo.add(name); playerInfo.add(nickName);
        playerInfo.add(role); playerInfo.add(age);

        return playerInfo;
    }


    /** Add the profile of every player that has not been added to infoDB, to infoBD.
     *
     * @param infoDB DB storing all the player profiles according to team membership.
     * @param playerProfile from information input by user
     */
    public static void profile2InfoDB(ArrayList<ArrayList<String>> infoDB, ArrayList<String> playerProfile) {
        if (playerProfile.size() != 0) { // in case playerProfile does not complete
            for (int i = 0; i < infoDB.size(); i++) {

                // checks if the nickname of the player already exists
                if (infoDB.get(i).get(2).equals(playerProfile.get(2))) {
                    return;  // do not add the player to infoDB
                }
            }
            infoDB.add(playerProfile);
        }
    }


    // changed function
    /**
     * General Output (8.print players of a team by nickname)
     * @param infoDB DB storing all the player profiles according to team membership.
     * @param teamsList an arrayList of the teams in existence
     * check if the team entered by the user already exists
     */
    public static void playersByTeam(ArrayList<ArrayList<String>> infoDB, ArrayList<String> teamsList) {
        ArrayList<String> playersInTeam = new ArrayList<String>();

        System.out.println("Enter the team whose players will be displayed: ");
        String teamName = scanner.nextLine();

        // check if the team already exists
        if (!teamsList.contains(teamName)) {
            boolean controlVar = true;
            while (controlVar) {
                System.out.println("The team " + teamName + " does not exist.");
                System.out.println("Would you like to try a different team? ([Y]es or [N]o)");  // assuming the user enters a String
                teamName = scanner.nextLine();
                if (!teamName.equals("Y") && !teamName.equals("y")) {
                    // System.out.println("The action could not be completed.");
                    return;
                }
                else {
                    System.out.println("Enter the name of the team: ");
                    teamName = scanner.nextLine();
                    if (teamsList.contains(teamName)) {
                        controlVar = false;
                    }
                }
            }
        }

        for (int i = 0; i < infoDB.size(); i++) {
            if (infoDB.get(i).get(0).equals(teamName)) {
                playersInTeam.add(infoDB.get(i).get(2));
            }
        }

        System.out.println("The players in " + teamName + " are: ");

        int count = 1;
        for (int j = 0; j < playersInTeam.size(); j++) {
            System.out.println("\t" + count + ". " + playersInTeam.get(j));
            count++;
        }
    }


    // changed function
    /**
     * General Output (9.print all the players by nickname)
     * @param infoDB DB storing all the player profiles according to team membership.
     */
    public static void displayAllPlayers(ArrayList<ArrayList<String>> infoDB) {
        ArrayList<String> players = new ArrayList<String>();

        for (int i = 0; i < infoDB.size(); i++) {
            String nickName = infoDB.get(i).get(2);
            players.add(nickName);
        }

        System.out.println("The players registered are: ");
        int count = 1;
        for (int j = 0; j < players.size(); j++) {
            System.out.println("\t" + count + ". " + players.get(j));
            count++;
        }
    }


    // changed function
    /**
     * General Output (10.print the players by role)
     * @param infoDB DB storing all the player profiles according to team membership.
     */
    public static void playersByRole(ArrayList<ArrayList<String>> infoDB) {
        ArrayList<String> playersInRole = new ArrayList<String>();

        System.out.println("Enter the role whose players will be displayed: ");
        String roleToMatch = scanner.nextLine();

        for (int i = 0; i < infoDB.size(); i++) {
            String role = infoDB.get(i).get(3);  // 3 is the index for role in a player's profile
            if (role.equals(roleToMatch)) {
                playersInRole.add(infoDB.get(i).get(2));  // 2 is the index for nickName in a player's profile
            }
        }

        System.out.println("The players fulfilling the role '" + roleToMatch + "' are: ");  // the team of each player could be printed before their nickName
        int count = 1;
        for (int j = 0; j < playersInRole.size(); j++) {
            System.out.println("\t" + count + ". " + playersInRole.get(j));
            count++;
        }

    }


    // changed function
    /**
     * General Output (11.print the players by age)
     * @param infoDB DB storing all the player profiles according to team membership.
     */
    public static void playersByAge(ArrayList<ArrayList<String>> infoDB) {
        HashMap<String, Integer> playersInAge = new HashMap<String, Integer>();

        System.out.println("Enter the age from which to start considering players: ");  // assuming the user enters an Integer
        int lowerAge = scanner.nextInt();

        System.out.println("Enter the age until which to consider players: ");  // assuming the user enters an Integer
        int upperAge = scanner.nextInt();

        if (lowerAge > upperAge) {  // make sure that lowerAge <= upperAge
            do {
                System.out.println("The starting age: " + lowerAge + "is greater than the ending age: " + upperAge + ".");
                System.out.println("Please enter appropriate ages.\n");

                System.out.println("Enter the age from which to start considering players: ");  // assuming the user enters an Integer
                lowerAge = scanner.nextInt();

                System.out.println("Enter the age until which to consider players: ");  // assuming the user enters an Integer
                upperAge = scanner.nextInt();

            } while (lowerAge > upperAge);
        }

        for (int i = 0; i < infoDB.size(); i++) {
            String sAge = infoDB.get(i).get(4); // 4 is the index for age in a player's profile
            int iAge = Integer.parseInt(sAge);

            if (lowerAge <= iAge && iAge <= upperAge) {  // if the player´s age is within the age range
                playersInAge.put(infoDB.get(i).get(2), iAge);
            }
        }

        System.out.println("The players within the specified age range " + lowerAge + "-" + upperAge + " are: ");
        int count = 1;
        for (String key : playersInAge.keySet()) {
            System.out.println("\t" + count + ". Project_233.Player: " + key + "\tAge: " + playersInAge.get(key));
            count++;
        }

    }


    // changed function
    /**
     * updates statsDB with the profiles of players that have not been added yet.
     *
     * @param infoDB DB storing all the player profiles according to team membership.
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void updateStatsDB(ArrayList<ArrayList<String>> infoDB, HashMap<String, int[]> statsDB) {
        for (int i = 0; i < infoDB.size(); i++) {  // for player in infoDB
            String nickName = infoDB.get(i).get(2);

            if (!statsDB.containsKey(nickName)) {  // if the player is not in statsDB
                statsDB.put(nickName, new int[]{0, 0, 0, 0, 0}); // create a key and value for the player
            }
        }
    }


    /**
     * modifies statsDB (Players Stats) (3-7) (it adds what user inputs)
     * @param statsDB HashMap storing all the stats about each player.
     * @param input from user
     */
    public static void addStats(HashMap<String, int[]> statsDB, int input) {
        System.out.println("Enter the nickname of the player: ");
        String nickName = scanner.nextLine();

        // check if the nickName exists
        if (!statsDB.containsKey(nickName)) {
            boolean controlVar = true;
            while (controlVar) {
                System.out.println("The player with the nickname: " + nickName + " does not exist.");
                System.out.println("Would you like to try a different nickname? ([Y]es or [N]o");  // assuming the user enters a String
                String response = scanner.nextLine();
                if (response.equals("N") || response.equals("n")) {
                    return;
                }
                else if (response.equals("Y") || response.equals("y")) {
                    System.out.println("Enter the player's nickname: ");
                    nickName = scanner.nextLine();
                    if (statsDB.containsKey(nickName)) {
                        controlVar = false;
                    }
                }
            }
        }

        int[] stats = new int[5];

        // stats = statsDB.get(nickName);  // creo que en esta linea se resume el for loop inútil

        // int[] stats = statsDB.get(nickName);  // de dos a una línea, si me la había mamada que pedo.

        for (String key : statsDB.keySet()) {
            if (key.equals(nickName)) {
                stats = statsDB.get(key);
            }
        }

        if (input == 3) {  // add a kill
            stats[0] += 1;
        }
        else if (input == 4) {  // add a death
            stats[1] += 1;
        }
        else if (input == 5) {  // add a spike defuse
            stats[2] += 1;
        }
        else if (input == 6) {  // add a spike plant
            stats[3] += 1;
        }
        else if (input == 7) {  // add to amount spent
            System.out.println("Enter the amount spent: ");
            double amountSpent = scanner.nextDouble();

            // check if the amount entered is less than 0
            if (amountSpent < 0) {
                do {
                    System.out.println("The amount spent cannot be less than 0. ");
                    System.out.println("Please enter another quantity (0 in case nothing was spent).");

                    System.out.println("Enter the amount spent: ");
                    amountSpent = scanner.nextDouble();

                } while (!(amountSpent >= 0));
            }

            stats[4] += amountSpent;
        }

        // gets the value was inputted into stats[]
        for (String key : statsDB.keySet()) {
            if (key.equals(nickName)) {
                statsDB.put(key, stats); // overwrites the previous int[] for stats[]
            }
        }
    }


    /**
     * Special Output(12. top 3 players by # of kills)
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void top3Kills(HashMap<String, int[]> statsDB) {
        HashMap<Integer, String> killCount = new HashMap<Integer, String>();
        ArrayList<Integer> killValues = new ArrayList<Integer>();

        for (String key : statsDB.keySet()) {  // loop through the keySet
            int[] stats = statsDB.get(key);  // put the int[] key of each player into stats[]
            int kills = stats[0];  // get the kills of each player

            killValues.add(kills); // add the value of kills to killValues
            killCount.put(kills, key);  // add the value of kills as a key and the nickName of each player as the value
        }

        // el bug es porque agarra
        Collections.sort(killValues);  // sort killValues from least to most

        int firstPlace = killValues.get(killValues.size() - 1);  // get the highest kill count
        int secondPlace = killValues.get(killValues.size() - 2);  // get the second-highest kill count
        int thirdPlace = killValues.get(killValues.size() - 3);  // get the third-highest kill count

        System.out.println("The top 3 player by no. of kills are: ");
        System.out.println("\t1. " + killCount.get(firstPlace) + " with " + firstPlace + " kills.");
        System.out.println("\t2. " + killCount.get(secondPlace) + " with " + secondPlace + " kills.");
        System.out.println("\t3. " + killCount.get(thirdPlace) + " with " + thirdPlace + " kills.");

    }


    /**
     * Special Output (13. top 3 players by kills/deaths ratio)
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void top3Ratio(HashMap<String, int[]> statsDB) {
        HashMap<Double, String> ratioCount = new HashMap<Double, String>();
        ArrayList<Double> ratioValues = new ArrayList<Double>();

        for (String key : statsDB.keySet()) {
            int[] stats = statsDB.get(key);

            double kills = stats[0];
            double deaths = stats[1];
            double ratio = kills / deaths;

            ratioValues.add(ratio);
            ratioCount.put(ratio, key);
        }

        Collections.sort(ratioValues);
        double firstPlace = ratioValues.get(ratioValues.size() - 1);
        double secondPlace = ratioValues.get(ratioValues.size() - 2);
        double thirdPlace = ratioValues.get(ratioValues.size() - 3);

        System.out.println("The top 3 players by kills to deaths ratio are: ");
        System.out.println("\t1. " + ratioCount.get(firstPlace) + " with a ratio of: " + firstPlace + ".");
        System.out.println("\t2. " + ratioCount.get(secondPlace) + " with a ratio of: " + secondPlace + ".");
        System.out.println("\t3. " + ratioCount.get(thirdPlace) + " with a ratio of: " + thirdPlace + ".");

    }


    /**
     * Special Output (14. top 3 players by kills per 100 points spent)
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void top3KillsPerPoint(HashMap<String, int[]> statsDB) {
        HashMap<Double, String> killPerPointsCount = new HashMap<Double, String>();
        ArrayList<Double> killsPerPointsValues = new ArrayList<Double>();

        for (String key : statsDB.keySet()) {
            int[] stats = statsDB.get(key);

            double kills = stats[0];
            double points = stats[4] / 100.0;

            double killsPer100Point = kills / points;

            killsPerPointsValues.add(killsPer100Point);
            killPerPointsCount.put(killsPer100Point, key);
        }

        Collections.sort(killsPerPointsValues);
        double firstPlace = killsPerPointsValues.get(killsPerPointsValues.size() - 1);
        double secondPlace = killsPerPointsValues.get(killsPerPointsValues.size() - 2);
        double thirdPlace = killsPerPointsValues.get(killsPerPointsValues.size() - 3);

        System.out.println("The top 3 players by kills per 100 points are: ");
        System.out.println("\t1. " + killPerPointsCount.get(firstPlace) + " with " + firstPlace + " kills per 100 points.");
        System.out.println("\t2. " + killPerPointsCount.get(secondPlace) + " with " + secondPlace + " kills per 100 points.");
        System.out.println("\t3. " + killPerPointsCount.get(thirdPlace) + " with " + thirdPlace + " kills per 100 points.");
    }


    /**
     * Special Output (15. top 3 players by # spikes defused)
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void top3SpikePlants(HashMap<String, int[]> statsDB) {
        HashMap<Integer, String> plantingCount = new HashMap<Integer, String>();
        ArrayList<Integer> plantingValues = new ArrayList<Integer>();

        for (String key : statsDB.keySet()) {
            int[] stats = statsDB.get(key);

            int plants = stats[3];
            plantingValues.add(plants);
            plantingCount.put(plants, key);
        }

        Collections.sort(plantingValues);
        int firstPlace = plantingValues.get(plantingValues.size() - 1);
        int secondPlace = plantingValues.get(plantingValues.size() - 2);
        int thirdPlace = plantingValues.get(plantingValues.size() - 3);

        System.out.println("The top 3 players by no. of spikes planted are: ");
        System.out.println("\t1. " + plantingCount.get(firstPlace) + " with " + firstPlace + " spikes planted. ");
        System.out.println("\t2. " + plantingCount.get(secondPlace) + " with " + secondPlace + " spikes planted. ");
        System.out.println("\t3. " + plantingCount.get(thirdPlace) + " with " + thirdPlace + " spikes planted. ");
    }


    /**
     * Special Output (16. top 3 players by # spikes planted )
     * @param statsDB HashMap storing all the stats about each player.
     */
    public static void top3SpikeDefuses(HashMap<String, int[]> statsDB) {
        HashMap<Integer, String> defusesCount = new HashMap<Integer, String>();
        ArrayList<Integer> defuseValues = new ArrayList<Integer>();

        for (String key : statsDB.keySet()) {
            int[] stats = statsDB.get(key);

            int defuses = stats[2];

            defuseValues.add(defuses);
            defusesCount.put(defuses, key);
        }

        Collections.sort(defuseValues);

        int firstPlace = defuseValues.get(defuseValues.size() - 1);
        int secondPlace = defuseValues.get(defuseValues.size() - 2);
        int thirdPlace = defuseValues.get(defuseValues.size() - 3);

        System.out.println("The top 3 players by no. of spikes defused are: ");
        System.out.println("\t1. " + defusesCount.get(firstPlace) + " with " + firstPlace + " spikes defused.");
        System.out.println("\t2. " + defusesCount.get(secondPlace) + " with " + secondPlace + " spikes defused.");
        System.out.println("\t3. " + defusesCount.get(thirdPlace) + " with " + thirdPlace + " spikes defused.");
    }


    /**
     * Executes the program.
     */
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> infoDB = new ArrayList<ArrayList<String>>();
        ArrayList<String> teamsList = new ArrayList<String>();

        HashMap<String, int[]> statsDB = new HashMap<String, int[]>();

        boolean player = false;

        // initial state of the program
        while (!player) {
            System.out.println("\n\ninfoDB" + infoDB);
            System.out.println("teamsList" + teamsList);

            initialMenu();
            int input = initialInput();

            if (input == 1) { // ---------------------------------------------------------------------------------------
                addTeam(teamsList);
            }
            else if (input == 2) { // ----------------------------------------------------------------------------------
                ArrayList<String> playerInfo = playerProfile(teamsList);
                profile2InfoDB(infoDB, playerInfo);
            }

            // check if there exists at least one player
            for (int i = 0; i < infoDB.size(); i++) {
                if (infoDB.get(i).size() > 1) {
                    player = true;
                    break;
                }
            }
        }


        // more functionalities
        boolean controlVar = true;
        while (controlVar) {
            System.out.println("\n\ninfoDB" + infoDB);
            System.out.println("teamsList" + teamsList);
            for (String key : statsDB.keySet()) {
                System.out.println(key + " stores " + Arrays.toString(statsDB.get(key)));
            }

            menuDisplay();
            int input = getInput();  // asks the user for input


            if (input == 1) {  // add a team
                addTeam(teamsList);  // update teamsList
            }

            else if (input == 2) {  // add a player to a team (name, nickname, role, age)
                ArrayList<String> playerInfo = playerProfile(teamsList);  // create a player profile
                profile2InfoDB(infoDB, playerInfo);  // update infoDB with that profile
            }

            else if (3 <= input && input <= 7) {  // modify statsDB
                updateStatsDB(infoDB, statsDB);
                addStats(statsDB, input);
            }

            else if (input == 8) {  // general output
                playersByTeam(infoDB, teamsList);  // prints the players in the given team
            }

            else if (input == 9) {  // general output
                displayAllPlayers(infoDB);  // prints all the players' nickNames
            }

            else if (input == 10) {  // general output
                playersByRole(infoDB);  // prints the players in the given role
            }

            else if (input == 11) {  // general output
                playersByAge(infoDB);  // prints the players in the given age range
            }

            else if (input == 12) {  // top 3 players (no. of kills) ---------------------------------------------------
                top3Kills(statsDB);
            }

            else if (input == 13) {  // top 3 players (kills/deaths ratio) ---------------------------------------------
                top3Ratio(statsDB);
            }

            else if (input == 14) {  // top 3 players (kills per 100 points spent) -------------------------------------
                top3KillsPerPoint(statsDB);
            }

            else if (input == 15) {  // top 3 players (no. of spikes planted) ------------------------------------------
                top3SpikePlants(statsDB);
            }

            else if (input == 16) {  // top 3 players (no. spikes defused) ---------------------------------------------
                top3SpikeDefuses(statsDB);
            }

            else if (input == 20) {
                controlVar = false;
            }
        }


    }

}
