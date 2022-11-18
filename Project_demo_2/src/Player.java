package Project_233;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Player is a Player Object with user provided team, name, nickname, role and age
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    21/03/2022
 * Tut:     T07
 */
public class Player implements Comparable<Player> {

    // info and stats about every object created
    private static ArrayList<Player> allPlayers = new ArrayList<Player>();
    private static HashMap<Player, int[]> allStats = new HashMap<Player, int[]>();

    // info about the player
    private String team;
    private String name;
    private String nickName;
    private String role;
    private String age;

    /**
     * Player constructor
     * @param team team of the player
     * @param name name of the player
     * @param nickName nickname of the player
     * @param role role of the player
     * @param age age of the player
     */
    public Player(String team, String name, String nickName, String role, String age) {
        this.team = team;
        this.name = name;
        this.nickName = nickName;
        this.role = role;
        this.age = age;

        allPlayers.add(this);  // add every player created to allPlayers
        allStats.put(this, new int[] {0, 0, 0, 0, 0});  // add every player to allStats
    }


    /**
     * Gets a player matching the nickname inputted by the user
     *
     * @param nickname nickname that a Player must match
     * @return Player that matches the nickname
     */
    public static Player getPlayer(String nickname) {
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(nickname)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Converts all the existing players into a String to output to outFile
     *
     * @return ArrayList in which each entry is made of all the info af a player
     */
    public static ArrayList<String> playersToString() {
        ArrayList<String> allPlayerStrings = new ArrayList<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            String playerString = player.team + "," + player.name + "," + player.nickName + "," + player.role + "," + player.age + ",";
            String statsString = stats[0] + "," + stats[1] + "," + stats[2] + "," + stats[3] + "," + stats[4];
            String finalString = playerString + statsString;
            allPlayerStrings.add(finalString);
        }

        return allPlayerStrings;
    }

    /**
     * Deep copy of allPlayers for testing purposes
     *
     * @return ArrayList copy of allPlayers
     */
    public static ArrayList<Player> copyOfPlayers() {
        ArrayList<Player> playersCopy = new ArrayList<>();
        for (Player player : allPlayers) {
            playersCopy.add(player);
        }

        return playersCopy;
    }

    /**
     * Deep copy of allStats for testing purposes
     *
     * @return ArrayList copy of allStats
     */
    public static HashMap<Player, int[]> copyOfStats() {
        HashMap<Player, int[]> statsCopy = new HashMap<>();
        for (Player player : allStats.keySet()) {
            statsCopy.put(player, allStats.get(player));
        }

        return statsCopy;
    }



    // -----------------------------------------------------------------------------------------------------------------

    //  ----------------------------------------------------------------------------------------------------------------
    // Getters

    /**
     * Gets the team of a player
     *
     * @return String team of the player
     */
    public String getTeam() {
        return team;
    }

    /**
     * gets the name of a player
     *
     * @return String name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the nickname of a player
     *
     * @return String nickname of the player
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Gets the role of a player
     *
     * @return String role of the player
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the age of a player
     *
     * @return String age of the player
     */
    public String getAge() {
        return age;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // Setters

    /**
     * Change the team of a player
     *
     * @param newTeam String to change the team to
     */
    public void setTeam(String newTeam) {
        for (Player player : allPlayers) {
            if (player.nickName.equals(this.nickName)) {  // what is this.nickName?? (does it work)
                player.team = newTeam;
            }
        }
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(this.nickName)) {
                player.team = newTeam;
            }
        }
        this.team = newTeam;
    }

    /**
     * Chang the name of a player
     *
     * @param newName String to change the name to
     */
    public void setName(String newName) {
        for (Player player : allPlayers) {
            if (player.nickName.equals(this.nickName)) {
                player.name = newName;
            }
        }
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(this.nickName)) {
                player.name = newName;
            }
        }
        this.name = newName;
    }

    /**
     * Change the nickname of a player
     *
     * @param newNickName String to change the nickname to
     */
    public void setNickName(String newNickName) {
        for (Player player : allPlayers) {
            if (player.nickName.equals(this.nickName)) {
                player.nickName = newNickName;
            }
        }
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(this.nickName)) {
                player.nickName = newNickName;
            }
        }
        this.nickName = newNickName;
    }

    /**
     * Change the role of a player
     *
     * @param newRole String to change the role to
     */
    public void setRole(String newRole) {
        for (Player player : allPlayers) {
            if (player.nickName.equals(this.nickName)) {
                player.role = newRole;
            }
        }
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(this.nickName)) {
                player.role = newRole;
            }
        }
        this.role = newRole;
    }

    /**
     * Change the age of a player
     *
     * @param newAge String to change the age to
     */
    public void setAge(String newAge) {
        for (Player player : allPlayers) {
            if (player.nickName.equals(this.nickName)) {
                player.age = newAge;
            }
        }
        for (Player player : allStats.keySet()) {
            if (player.nickName.equals(this.nickName)) {
                player.age = newAge;
            }
        }
        this.age = newAge;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // General output (based on info)

    /**
     * We sort all players and print them by nickname
     */
    public static void allPlayers() {
        if (allPlayers != null) {
            Collections.sort(allPlayers);

            System.out.println("\nAll the players registered are: ");

            for (Player player : allPlayers) {
                System.out.println("\n----------");
                System.out.println(player);
                System.out.println("----------");
            }
        }
    }

    /**
     * Print all players fulfilling a specific role (ordered by nickName).
     */
    public static void playersByRole(String role) { // create function players by role using parameter Role (a string)
        ArrayList<Player> playersInRole = new ArrayList<Player>(); // create an arraylist of Project_233.Player called playersInRole

        for (Player player : allPlayers) {
            if (player.role.equals(role)) {
                playersInRole.add(player);
            }
        }

        Collections.sort(playersInRole);
        System.out.println("\nThe players fulfilling the role " + role + ",  are:");
        for (Player player : playersInRole) {
            System.out.println("\n----------");
            System.out.println(player);
            System.out.println("----------");
        }
    }

    /**
     * Print all players in a given age range by nickname
     *
     * @param startingAge youngest age
     * @param endingAge oldest age
     */
    public static void playersByAge(int startingAge, int endingAge) {
        ArrayList<Player> playersInAge = new ArrayList<Player>();

        for (Player player : allPlayers) {
            int age = Integer.parseInt(player.age);
            if (age >= startingAge && age <= endingAge) {
                playersInAge.add(player);
            }
        }

        Collections.sort(playersInAge);
        System.out.println("\nThe players between " + startingAge + " and " + endingAge + " years of age are:");
        for (Player player : playersInAge) {
            System.out.println("\n----------");
            System.out.println(player);
            System.out.println("----------");
        }
    }

    /**
     * Print all the players of a team
     *
      * @param teamName String of the name of the team to be printed
     */
    public static void playersByTeam(String teamName) {  // this could be done from Project_233.Team class (unnecessary tho?)
        ArrayList<Player> playersInTeam = new ArrayList<Player>();

        for (Player player : allPlayers) {
            if (player.team.equals(teamName)) {
                playersInTeam.add(player);
            }
        }
        System.out.println("\nThe players in " + teamName + " are: ");
        for (Player player : playersInTeam) {
            System.out.println("\n----------");
            System.out.println(player);
            System.out.println("----------");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // add stats

    /**
     * add a kill
     *
     * @param player player that will get added the kill
     */
    public static void addKill(Player player) { //create function
        int[] stats = allStats.get(player);
        stats[0] += 1;
        allStats.put(player, stats);
    }

    /**
     * add a death
     *
     * @param player player that will get added the death
     */
    public static void addDeath(Player player) { //create function
        int[] stats = allStats.get(player);
        stats[1] += 1;
        allStats.put(player, stats);
    }

    /**
     * add a spike plant
     *
     * @param player player that will get added the spike plant
     */
    public static void addPlant(Player player) {
        int[] stats = allStats.get(player);
        stats[3] += 1;
        allStats.put(player, stats);
    }

    /**
     * add a spike defuse
     *
     * @param player player that will get added the spike defuse
     */
    public static void addDefuse(Player player) {
        int[] stats = allStats.get(player);
        stats[2] += 1;
        allStats.put(player, stats);
    }

    /**
     * add to amount spent
     *
     * @param player player that will get added the amount spent
     * @param amount of points
     */
    public static void addAmount(Player player, double amount) {
        int[] stats = allStats.get(player);
        stats[4] += amount;
        allStats.put(player, stats);
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // set stats

    /**
     * Set the kills of a player
     *
     * @param kills int to set the kills to
     */
    public void setKills(int kills) {
        int[] stats = allStats.get(this);
        stats[0] = kills;
        allStats.put(this, stats);
    }

    /**
     * Set the deaths of a player
     *
     * @param deaths int to set the deaths to
     */
    public void setDeaths(int deaths) {
        int[] stats = allStats.get(this);
        stats[1] = deaths;
        allStats.put(this, stats);
    }

    /**
     * Set the defuses of a player
     *
     * @param defuses int to set the spikes defused to
     */
    public void setDefuses(int defuses) {
        int[] stats = allStats.get(this);
        stats[2] = defuses;
        allStats.put(this, stats);
    }

    /**
     * Set the plants of a player
     *
     * @param plants int to set the spikes planted to
     */
    public void setPlants(int plants) {
        int[] stats = allStats.get(this);
        stats[3] = plants;
        allStats.put(this, stats);
    }

    /**
     * Set the amount spent of a player
     *
     * @param amount in to set the amount spent to
     */
    public void setAmount(int amount) {
        int[] stats = allStats.get(this);
        stats[4] = amount;
        allStats.put(this, stats);
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // Special output (based on stats)

    /**
     * print top 3 players by kills
     *
     * @return HashMap for testing purposes
     */
    public static HashMap<Player, Integer> top3Kills() {

        ArrayList<Integer> killsValues = new ArrayList<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int kills = stats[0];
            killsValues.add(kills);
        }
        Collections.sort(killsValues);

        int firstKills = killsValues.get(killsValues.size() - 1);  // highest kills value
        int secondKills = killsValues.get(killsValues.size() - 2);  // second-highest kills value
        int thirdKills = killsValues.get(killsValues.size() - 3);  // third-highest kills value

        HashMap<Player, Integer> validKills = new HashMap<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int kills = stats[0];
            if (kills == firstKills) {
                validKills.put(player, kills);
            }
            else if (kills == secondKills) {
                validKills.put(player, kills);
            }
            else if (kills == thirdKills) {
                validKills.put(player, kills);
            }
        }

        // make a copy of validKills to return
        HashMap<Player, Integer> copyToReturn = new HashMap<>();
        for (Player player : validKills.keySet()) {
            copyToReturn.put(player, validKills.get(player));
        }

        System.out.println("\nThe top 3 players by no. of kills are: \n");

        int printCount = 0;
        for (Player player : validKills.keySet()) {
            if (validKills.get(player) == firstKills) {  // the problem is that the players satisfy this constraint
                System.out.println("\t1. " + player.nickName + " from team " + player.team + " with " + firstKills + " kills.");
                validKills.put(player, 0);  // to get rid of that entry since using an iterator is very messy
                printCount++;
            }
        }
        if (printCount < 3) {
            for (Player player : validKills.keySet()) {
                if (validKills.get(player) == secondKills) {
                    System.out.println("\t2. " + player.nickName + " from team " + player.team + " with " + secondKills + " kills.");
                    validKills.put(player,0);
                    printCount++;
                }
            }
        }
        if (printCount < 3) {
            for (Player player : validKills.keySet()) {
                if (validKills.get(player) == thirdKills) {
                    System.out.println("\t3. " + player.nickName + " from team " + player.team + " with " + thirdKills + " kills.");
                    validKills.put(player, 0);
                }
            }
        }

        return copyToReturn;
    }

    /**
     * print top 3 players by k/d ratio
     *
     * @return HashMap for testing purposes
     */
    public static HashMap<Player, Double> top3Ratio() { //create function
        HashMap<Double, Player> ratiosHM = new HashMap<>(); //create hashMap of double and player called ratiosHM
        ArrayList<Double> ratios4 = new ArrayList<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            double kills = stats[0];
            double deaths = stats[1];
            double ratio;

            if (deaths == 0) {
                ratio = kills;
            }
            else {
                ratio = kills/deaths;
            }

            ratiosHM.put(ratio, player);
            ratios4.add(ratio);
        }

        Collections.sort(ratios4);  // ordered AL of unique values

        double firstRatio = ratios4.get(ratios4.size() - 1);  // highest ratio value
        double secondRatio = ratios4.get(ratios4.size() - 2);  // second-highest ratio value
        double thirdRatio = ratios4.get(ratios4.size() - 3);  // third-highest ratio value

        HashMap<Player, Double> validRatios = new HashMap<Player, Double>();

        for (Double ratio : ratiosHM.keySet()) {
            if (ratio == firstRatio) {
                validRatios.put(ratiosHM.get(firstRatio), ratio);
            }
            else if (ratio == secondRatio) {
                validRatios.put(ratiosHM.get(secondRatio), ratio);
            }
            else if (ratio == thirdRatio) {
                validRatios.put(ratiosHM.get(thirdRatio), ratio);
            }
        }

        // make a copy of validRatios to return
        HashMap<Player, Double> copyToReturn = new HashMap<>();
        for (Player player : validRatios.keySet()) {
            copyToReturn.put(player, validRatios.get(player));
        }

        System.out.println("The top 3 players by kills/deaths ratio are: \n");

        int printCount = 0;
        for (Player player : validRatios.keySet()) {
            if (validRatios.get(player) == firstRatio) {
                System.out.println("\t1. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(firstRatio * 100) / 100);
                validRatios.put(player, 0.0);
                printCount += 1;
            }
        }
        if (printCount < 3) {
            for (Player player : validRatios.keySet()) {
                if (validRatios.get(player) == secondRatio) {
                    System.out.println("\t2. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(secondRatio * 100) / 100);
                    validRatios.put(player, 0.0);
                    printCount += 1;
                }
            }
        }
        if (printCount < 3) {
            for (Player player : validRatios.keySet()) {
                if (validRatios.get(player) == thirdRatio) {
                    System.out.println("\t3. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(thirdRatio * 100) / 100);
                    validRatios.put(player, 0.0);
                }
            }
        }

        return copyToReturn;
    }

    /**
     * print top 3 players by kills per point
     *
     * @return HashMap for testing purposes
     */
    public static HashMap<Player, Double> top3KillsPerPoint() {
        HashMap<Double, Player> quotientsHM = new HashMap<Double, Player>();
        HashSet<Double> quotientValues = new HashSet<Double>();

        for (Player key : allStats.keySet()) {
            int[] stats = allStats.get(key);
            double kills = stats[0];
            double points = stats[4] / 100.0; // points of each player
            
            double killsPer100Points = kills / points;
            
            quotientsHM.put(killsPer100Points, key);
            quotientValues.add(killsPer100Points);
        }

        ArrayList<Double> orderedValues = new ArrayList<>(quotientValues);
        Collections.sort(orderedValues);  // ordered AL of unique values

        double firstQuotient = orderedValues.get(orderedValues.size() - 1);  // highest quotient value
        double secondQuotient = orderedValues.get(orderedValues.size() - 2);  // second-highest quotient value
        double thirdQuotient = orderedValues.get(orderedValues.size() - 3);  // third-highest quotient value

        HashMap<Player, Double> validQuotients = new HashMap<Player, Double>();

        for (Double quotient : quotientsHM.keySet()) {
            if (quotient == firstQuotient) {
                validQuotients.put(quotientsHM.get(firstQuotient), quotient);
            }
            else if (quotient == secondQuotient) {
                validQuotients.put(quotientsHM.get(secondQuotient), quotient);
            }
            else if (quotient == thirdQuotient) {
                validQuotients.put(quotientsHM.get(thirdQuotient), quotient);
            }
        }

        // make a copy of validQuotients to return
        HashMap<Player, Double> copyToReturn = new HashMap<>();
        for (Player player : validQuotients.keySet()) {
            copyToReturn.put(player, validQuotients.get(player));
        }

        System.out.println("The top 3 players by kills per 100 points spent are: \n");

        int printCount = 0;
        for (Player player : validQuotients.keySet()) {
            if (validQuotients.get(player) == firstQuotient) {
                System.out.println("\t1. " + player.nickName + " from team " + player.team + " with " +  Math.floor(firstQuotient * 100) / 100 + " kills per 100 points.");
                validQuotients.put(player, 0.0);
                printCount += 1;
            }
        }
        if (printCount < 3) {
            for (Player player : validQuotients.keySet()) {
                if (validQuotients.get(player) == secondQuotient) {
                    System.out.println("\t2. " + player.nickName + " from team " + player.team + " with " + Math.floor(secondQuotient * 100) / 100 + " kills per 100 points.");
                    validQuotients.put(player, 0.0);
                    printCount += 1;
                }
            }
        }
        if (printCount < 3) {
            for (Player player : validQuotients.keySet()) {
                if (validQuotients.get(player) == thirdQuotient) {
                    System.out.println("\t3. " + player.nickName + " from team " + player.team + " with " + Math.floor(thirdQuotient * 100) / 100 + " kills per 100 points.");
                    validQuotients.put(player, 0.0);
                }
            }
        }

        return copyToReturn;
    }

    /**
     * print top 3 players by spikes
     *
     * @return HashMap for testing purposes
     */
    public static HashMap<Player, Integer> top3SpikeDefuses() {
        ArrayList<Integer> defuses4 = new ArrayList<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int defuses = stats[2];

            defuses4.add(defuses);
        }
        Collections.sort(defuses4);

        int firstDefuses = defuses4.get(defuses4.size() - 1);  // highest defuses value
        int secondDefuses = defuses4.get(defuses4.size() - 2);  // second-highest defuses value
        int thirdDefuses = defuses4.get(defuses4.size() - 3);  // third-highest defuses value

        HashMap<Player, Integer> validDefuses = new HashMap<Player, Integer>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int defuses = stats[2];
            if (defuses == firstDefuses) {
                validDefuses.put(player, defuses);
            }
            else if (defuses == secondDefuses) {
                validDefuses.put(player, defuses);
            }
            else if (defuses == thirdDefuses) {
                validDefuses.put(player, defuses);
            }
        }

        // make a copy of validDefuses to return
        HashMap<Player, Integer> copyToReturn = new HashMap<>();
        for (Player player : validDefuses.keySet()) {
            copyToReturn.put(player, validDefuses.get(player));
        }

        System.out.println("The top 3 players by no. of spikes defused are: \n");

        int printCount = 0;
        for (Player player : validDefuses.keySet()) {
            if (validDefuses.get(player) == firstDefuses) {
                System.out.println("\t1. " + player.nickName + " from team " + player.team + " with " + firstDefuses + " spikes defused.");
                validDefuses.put(player, 0);
                printCount += 1;
            }
        }
        if (printCount < 3) {
            for (Player player : validDefuses.keySet()) {
                if (validDefuses.get(player) == secondDefuses) {
                    System.out.println("\t2. " + player.nickName + " from team " + player.team + " with " + secondDefuses + " spikes defused.");
                    validDefuses.put(player, 0);
                    printCount += 1;
                }
            }
        }
        if (printCount < 3) {
            for (Player player : validDefuses.keySet()) {
                if (validDefuses.get(player) == thirdDefuses) {
                    System.out.println("\t3. " + player.nickName + " from team " + player.team + " with " + thirdDefuses + " spikes defused.");
                    validDefuses.put(player, 0);
                }
            }
        }

        return copyToReturn;
    }

    /**
     * print top 3 players by spikes planted
     *
     * @return HashMap for testing purposes
     */
    public static HashMap<Player, Integer> top3SpikePlants() {
        ArrayList<Integer> spikesPlanted = new ArrayList<>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int plants = stats[3];

            spikesPlanted.add(plants);
        }

        Collections.sort(spikesPlanted);

        int firstPlants = spikesPlanted.get(spikesPlanted.size() - 1);  // highest plants value
        int secondPlants = spikesPlanted.get(spikesPlanted.size() - 2);  // second-highest plants value
        int thirdPlants = spikesPlanted.get(spikesPlanted.size() - 3);  // third-highest plants value

        HashMap<Player, Integer> validPlants = new HashMap<Player, Integer>();

        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int playerPlants = stats[3];
            if (playerPlants == firstPlants) {
                validPlants.put(player, playerPlants);
            }
            else if (playerPlants == secondPlants) {
                validPlants.put(player, playerPlants);
            }
            else if (playerPlants == thirdPlants) {
                validPlants.put(player, playerPlants);
            }
        }

        // make a copy of validPlants to return
        HashMap<Player, Integer> copyToReturn = new HashMap<>();
        for (Player player : validPlants.keySet()) {
            copyToReturn.put(player, validPlants.get(player));
        }

        System.out.println("The top 3 players by no. of spikes planted are: \n");

        int printCount = 0;
        for (Player player : validPlants.keySet()) {
            if (validPlants.get(player) == firstPlants) {
                System.out.println("\t1. " + player.nickName + " from team " + player.team + " with " + firstPlants + " spikes planted.");
                validPlants.put(player, 0);
                printCount += 1;
            }
        }
        if (printCount < 3) {
            for (Player player : validPlants.keySet()) {
                if (validPlants.get(player) == secondPlants) {
                    System.out.println("\t2. " + player.nickName + " from team " + player.team + " with " + secondPlants + " spikes planted.");
                    validPlants.put(player, 0);
                    printCount += 1;
                }
            }
        }
        if (printCount < 3) {
            for (Player player : validPlants.keySet()) {
                if (validPlants.get(player) == thirdPlants) {
                    System.out.println("\t3. " + player.nickName + " from team " + player.team + " with " + thirdPlants + " spikes planted.");
                    validPlants.put(player, 0);
                }
            }
        }

        return copyToReturn;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // Overridden functions

    /**
     * Override toString to print Player objects with useful information
     *
     * @return string way to print a player
     */
    @Override
    public String toString() {
        // return team + "," + name + "," + nickName + "," + role + "," + age;
        return "Team: " + team + "\nName: " + name + "\nNickname: " + nickName + "\nRole: " + role + "\nAge: " + age;
    }

    /**
     * Override to make players sortable by nickname
     *
     * @param other player being compared against
     * @return int depending on which String (this.nickName or other.nickName) comes first
     */
    @Override
    public int compareTo(Player other) {
        return this.nickName.compareTo(other.nickName);
    }

}
