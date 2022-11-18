package code;

import java.util.*;

/**
 * Player is a Code.Player Object with user provided team, name, nickname, role and age
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    11/04/2022
 * Tut:     T07
 */
public class Player implements Comparable<Player> {

    /**
     * Established the maximum number of players to be considered in the podium (3 to form the top 3)
     */
    private static final int MAX_PLAYERS = 3;

    /**
     * Index where the kills are stored in the stats array of every Player
     */
    private static final int KILLS_INDEX = 0;

    /**
     * Index where the deaths are stored in the stats array of every Player
     */
    private static final int DEATHS_INDEX = 1;

    /**
     * Index where the defuses are stored in the stats array of every Player
     */
    private static final int DEFUSES_INDEX = 2;

    /**
     * Index where the plants are stored in the stats array of every Player
     */
    private static final int PLANTS_INDEX = 3;

    /**
     * Index where the amount is stored in the stats array of every Player
     */
    private static final int AMOUNT_INDEX = 4;

    /**
     * Info and stats about every object (player) created
     */
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
     *
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
        // look for the player matching the given nickname
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

            // put all the info into a single String
            String infoString = player.team + "," + player.name + "," + player.nickName + "," + player.role + "," + player.age + ",";

            // put all the stats into a single string
            String statsString = stats[KILLS_INDEX] + "," + stats[DEATHS_INDEX] + "," + stats[DEFUSES_INDEX] + "," + stats[PLANTS_INDEX] + "," + stats[AMOUNT_INDEX];

            // concatenate the info and stats of each player into a single String
            String playerString = infoString + statsString;

            // add the final String to allPlayerString
            allPlayerStrings.add(playerString);
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

        // copy every entry to playersCopy
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

        // copy ever entry to copyOfStats
        for (Player player : allStats.keySet()) {
            statsCopy.put(player, allStats.get(player));
        }

        return statsCopy;
    }


    /**
     * Counts the number of players currently in existence
     *
     * @return int of players in existence
     */
    public static int playersNo() {
        int count = 0;
        for (Player player : allPlayers) {
            count++;
        }

        return count;
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  ----------------------------------------------------------------------------------------------------------------
    // Info getter

    /**
     * Gets the nickname of a player
     *
     * @return String nickname of the player
     */
    public String getNickName() {
        return nickName;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // General output (based on info)

    /**
     * Gets all the players into a String
     *
     * @return String containing the relevant info
     */
    public static String allPlayers() {
        String result = "";

        if (allPlayers != null) {
            Collections.sort(allPlayers);

            for (Player player : allPlayers) {
                result += "\n----------\n";
                result += player;
                result += "\n----------\n";
            }
        }

        return result;
    }

    /**
     * Gets the players in the given team into a String
     *
     * @param teamName String team that players must match
     *
     * @return String containing the relevant info
     */
    public static String playersByTeam(String teamName) {
        String result = "";
        ArrayList<Player> playersInTeam = new ArrayList<Player>();

        for (Player player : allPlayers) {
            if (player.team.equals(teamName)) {
                playersInTeam.add(player);
            }
        }

        for (Player player : playersInTeam) {
            result += "\n----------\n";
            result += player;
            result += "\n----------\n";
        }
        return result;
    }

    /**
     * Gets the players fulfilling the given role into a String
     *
     * @param role String role that players must match
     *
     * @return String containing the relevant info
     */
    public static String playersByRole(String role) {
        String result = "";
        ArrayList<Player> playersInRole = new ArrayList<Player>();

        for (Player player : allPlayers) {
            if (player.role.equals(role)) {
                playersInRole.add(player);
            }
        }

        Collections.sort(playersInRole);
        for (Player player : playersInRole) {
            result += "\n----------\n";
            result += player;
            result += "\n----------\n";
        }

        return result;
    }

    /**
     * Gets the players in the given age range into a String
     *
     * @param startingAge starting int of the age range
     * @param endingAge ending int of the age range
     *
     * @return String containing the relevant info
     */
    public static String playersByAge(int startingAge, int endingAge) {
        String result = "";
        ArrayList<Player> playersInAge = new ArrayList<Player>();

        for (Player player : allPlayers) {
            int age = Integer.parseInt(player.age);
            if (age >= startingAge && age <= endingAge) {
                playersInAge.add(player);
            }
        }

        Collections.sort(playersInAge);
        for (Player player : playersInAge) {
            result += "\n----------\n";
            result += player;
            result += "\n----------\n";
        }

        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Stat modifiers

    /**
     * Add kills
     *
     * @param player player that will get added the kill
     */
    public static void addKills(Player player, int kills) {
        int[] stats = allStats.get(player);
        stats[KILLS_INDEX] += kills;
        allStats.put(player, stats);
    }

    /**
     * Add deaths
     *
     * @param player player that will get added the death
     */
    public static void addDeaths(Player player, int deaths) {
        int[] stats = allStats.get(player);
        stats[DEATHS_INDEX] += deaths;
        allStats.put(player, stats);
    }

    /**
     * Add spikes defused
     *
     * @param player player that will get added the spike defuse
     */
    public static void addDefuses(Player player, int defuses) {
        int[] stats = allStats.get(player);
        stats[DEFUSES_INDEX] += defuses;
        allStats.put(player, stats);
    }

    /**
     * Add spikes planted
     *
     * @param player player that will get added the spike plant
     */
    public static void addPlants(Player player, int plants) {
        int[] stats = allStats.get(player);
        stats[PLANTS_INDEX] += plants;
        allStats.put(player, stats);
    }

    /**
     * Add amount spent
     *
     * @param player player that will get added the amount spent
     * @param amount of points
     */
    public static void addAmount(Player player, double amount) {
        int[] stats = allStats.get(player);
        stats[AMOUNT_INDEX] += amount;
        allStats.put(player, stats);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Stat setters

    /**
     * Set the kills of a player
     *
     * @param kills int to set the kills to
     */
    public void setKills(int kills) {
        int[] stats = allStats.get(this);
        stats[KILLS_INDEX] = kills;
        allStats.put(this, stats);
    }

    /**
     * Set the deaths of a player
     *
     * @param deaths int to set the deaths to
     */
    public void setDeaths(int deaths) {
        int[] stats = allStats.get(this);
        stats[DEATHS_INDEX] = deaths;
        allStats.put(this, stats);
    }

    /**
     * Set the defuses of a player
     *
     * @param defuses int to set the spikes defused to
     */
    public void setDefuses(int defuses) {
        int[] stats = allStats.get(this);
        stats[DEFUSES_INDEX] = defuses;
        allStats.put(this, stats);
    }

    /**
     * Set the plants of a player
     *
     * @param plants int to set the spikes planted to
     */
    public void setPlants(int plants) {
        int[] stats = allStats.get(this);
        stats[PLANTS_INDEX] = plants;
        allStats.put(this, stats);
    }

    /**
     * Set the amount spent of a player
     *
     * @param amount in to set the amount spent to
     */
    public void setAmount(int amount) {
        int[] stats = allStats.get(this);
        stats[AMOUNT_INDEX] = amount;
        allStats.put(this, stats);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Special output (based on stats)

    /**
     * Get the top 3 players by kills
     *
     * @return HashMap for testing purposes
     */
    public static String top3Kills() {
        String result = "";

        ArrayList<Integer> killsValues = new ArrayList<>();

        // add the kills values of every player to killsValues
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int kills = stats[KILLS_INDEX];
            killsValues.add(kills);
        }
        Collections.sort(killsValues);

        // get the top 3 values of killsValues. 2 or 3 values can be the same
        int firstKills = killsValues.get(killsValues.size() - 1);  // highest kills value
        int secondKills = killsValues.get(killsValues.size() - 2);  // second-highest kills value
        int thirdKills = killsValues.get(killsValues.size() - 3);  // third-highest kills value

        HashMap<Player, Integer> validKills = new HashMap<>();

        // for every player, if the player matches any of the top 3 values it is added to validKills
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int kills = stats[KILLS_INDEX];
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

        int playerCount = 0;  // keeps track of the players added to result

        for (Player player : validKills.keySet()) {  // for first place
            if (validKills.get(player) == firstKills) {
                result += "1. " + player.nickName + " from team " + player.team + " with " + firstKills + " kills.\n";
                validKills.put(player, 0);  // remove the player after adding them
                playerCount++;
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for second place
            for (Player player : validKills.keySet()) {
                if (validKills.get(player) == secondKills) {
                    result += "2. " + player.nickName + " from team " + player.team + " with " + secondKills + " kills.\n";
                    validKills.put(player,0);  // remove the player after adding them
                    playerCount++;
                }
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for third place
            for (Player player : validKills.keySet()) {
                if (validKills.get(player) == thirdKills) {
                    result += "3. " + player.nickName + " from team " + player.team + " with " + thirdKills + " kills.\n";
                    validKills.put(player, 0);  // remove the player after adding them
                }
            }
        }

        return result;
    }

    /**
     * Get the top 3 players by k/d ratio
     *
     * @return HashMap for testing purposes
     */
    public static String top3Ratio() {
        String result = "";

        HashMap<Double, Player> ratiosHM = new HashMap<>();
        ArrayList<Double> ratioValues = new ArrayList<>();

        // add the ratio value of every player to ratiosHM and ratioValues
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            double kills = stats[KILLS_INDEX];
            double deaths = stats[DEATHS_INDEX];
            double ratio;

            if (deaths == 0) {
                ratio = kills;
            }
            else {
                ratio = kills/deaths;
            }

            ratiosHM.put(ratio, player);
            ratioValues.add(ratio);
        }

        Collections.sort(ratioValues);  // ordered AL of unique values

        // get the top 3 values of ratioValues. 2 or 3 values can be the same
        double firstRatio = ratioValues.get(ratioValues.size() - 1);  // highest ratio value
        double secondRatio = ratioValues.get(ratioValues.size() - 2);  // second-highest ratio value
        double thirdRatio = ratioValues.get(ratioValues.size() - 3);  // third-highest ratio value

        HashMap<Player, Double> validRatios = new HashMap<Player, Double>();

        // for every player, if the player matches any of the top 3 values it is added to validRatios
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

        int playerCount = 0;  // keeps track of the players added to result

        for (Player player : validRatios.keySet()) {  // for first place
            if (validRatios.get(player) == firstRatio) {
                result += "1. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(firstRatio * 100) / 100 + "\n";
                validRatios.put(player, 0.0);  // remove the player after adding them
                playerCount++;
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for second place
            for (Player player : validRatios.keySet()) {
                if (validRatios.get(player) == secondRatio) {
                    result += "2. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(secondRatio * 100) / 100 + "\n";
                    validRatios.put(player, 0.0);  // remove the player after adding them
                    playerCount++;
                }
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for third place
            for (Player player : validRatios.keySet()) {
                if (validRatios.get(player) == thirdRatio) {
                    result += "3. " + player.nickName + " from team " + player.team + " with a ratio of: " + Math.floor(thirdRatio * 100) / 100 + "\n";
                    validRatios.put(player, 0.0);  // remove the player after adding them
                }
            }
        }

        return result;
    }

    /**
     * Get the top 3 players by kills per point
     *
     * @return HashMap for testing purposes
     */
    public static String top3KillsPerPoint() {
        String result = "";

        HashMap<Double, Player> quotientsHM = new HashMap<Double, Player>();
        HashSet<Double> quotientValues = new HashSet<Double>();

        // add the kills per point value of every player to quotientsHM and quotientValues
        for (Player key : allStats.keySet()) {
            int[] stats = allStats.get(key);
            double kills = stats[KILLS_INDEX];
            double points = stats[AMOUNT_INDEX] / 100.0; // points of each player

            double killsPer100Points = kills / points;

            quotientsHM.put(killsPer100Points, key);
            quotientValues.add(killsPer100Points);
        }

        ArrayList<Double> orderedValues = new ArrayList<>(quotientValues);
        Collections.sort(orderedValues);  // ordered AL of unique values

        // get the top 3 values of orderedValues. 2 or 3 values can be the same
        double firstQuotient = orderedValues.get(orderedValues.size() - 1);  // highest quotient value
        double secondQuotient = orderedValues.get(orderedValues.size() - 2);  // second-highest quotient value
        double thirdQuotient = orderedValues.get(orderedValues.size() - 3);  // third-highest quotient value

        HashMap<Player, Double> validQuotients = new HashMap<Player, Double>();

        // for every player, if the player matches any of the top 3 values it is added to validQuotients
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

        int playerCount = 0;  // keeps track of the players added to result

        for (Player player : validQuotients.keySet()) {  // for first place
            if (validQuotients.get(player) == firstQuotient) {
                result += "1. " + player.nickName + " from team " + player.team + " with " +  Math.floor(firstQuotient * 100) / 100 + " kills per 100 points.\n";
                validQuotients.put(player, 0.0);  // remove the player after adding them
                playerCount++;
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for second place
            for (Player player : validQuotients.keySet()) {
                if (validQuotients.get(player) == secondQuotient) {
                    result += "2. " + player.nickName + " from team " + player.team + " with " + Math.floor(secondQuotient * 100) / 100 + " kills per 100 points.\n";
                    validQuotients.put(player, 0.0);  // remove the player after adding them
                    playerCount++;
                }
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for third place
            for (Player player : validQuotients.keySet()) {
                if (validQuotients.get(player) == thirdQuotient) {
                    result += "3. " + player.nickName + " from team " + player.team + " with " + Math.floor(thirdQuotient * 100) / 100 + " kills per 100 points.\n";
                    validQuotients.put(player, 0.0);  // remove the player after adding them
                }
            }
        }

        return result;
    }

    /**
     * Get the top 3 players by spikes
     *
     * @return HashMap for testing purposes
     */
    public static String top3SpikeDefuses() {
        String result = "";

        ArrayList<Integer> defusesValues = new ArrayList<>();

        // add the defuses value of every player to defusesValues
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int defuses = stats[DEFUSES_INDEX];

            defusesValues.add(defuses);
        }
        Collections.sort(defusesValues);

        // get the top 3 values of ratioValues. 2 or 3 values can be the same
        int firstDefuses = defusesValues.get(defusesValues.size() - 1);  // highest defuses value
        int secondDefuses = defusesValues.get(defusesValues.size() - 2);  // second-highest defuses value
        int thirdDefuses = defusesValues.get(defusesValues.size() - 3);  // third-highest defuses value

        HashMap<Player, Integer> validDefuses = new HashMap<Player, Integer>();

        // for every player, if the player matches any of the top 3 values it is added to validDefuses
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int defuses = stats[DEFUSES_INDEX];
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

        int playerCount = 0;  // keeps track of the players added to result

        for (Player player : validDefuses.keySet()) {  // for first place
            if (validDefuses.get(player) == firstDefuses) {
                result += "1. " + player.nickName + " from team " + player.team + " with " + firstDefuses + " spikes defused.\n";
                validDefuses.put(player, 0);  // remove the player after adding them
                playerCount++;
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for second place
            for (Player player : validDefuses.keySet()) {
                if (validDefuses.get(player) == secondDefuses) {
                    result += "2. " + player.nickName + " from team " + player.team + " with " + secondDefuses + " spikes defused.\n";
                    validDefuses.put(player, 0);  // remove the player after adding them
                    playerCount++;
                }
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for third place
            for (Player player : validDefuses.keySet()) {
                if (validDefuses.get(player) == thirdDefuses) {
                    result += "3. " + player.nickName + " from team " + player.team + " with " + thirdDefuses + " spikes defused.\n";
                    validDefuses.put(player, 0);  // remove the player after adding them
                }
            }
        }

        return result;
    }

    /**
     * Get the top 3 players by spikes planted
     *
     * @return HashMap for testing purposes
     */
    public static String top3SpikePlants() {
        String result = "";

        ArrayList<Integer> spikesPlanted = new ArrayList<>();

        // add the plants value of every player to spikesPlanted
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int plants = stats[PLANTS_INDEX];

            spikesPlanted.add(plants);
        }

        Collections.sort(spikesPlanted);

        // get the top 3 values of ratioValues. 2 or 3 values can be the same
        int firstPlants = spikesPlanted.get(spikesPlanted.size() - 1);  // highest plants value
        int secondPlants = spikesPlanted.get(spikesPlanted.size() - 2);  // second-highest plants value
        int thirdPlants = spikesPlanted.get(spikesPlanted.size() - 3);  // third-highest plants value

        HashMap<Player, Integer> validPlants = new HashMap<Player, Integer>();

        // for every player, if the player matches any of the top 3 values it is added to validPlants
        for (Player player : allStats.keySet()) {
            int[] stats = allStats.get(player);
            int plants = stats[PLANTS_INDEX];
            if (plants == firstPlants) {
                validPlants.put(player, plants);
            }
            else if (plants == secondPlants) {
                validPlants.put(player, plants);
            }
            else if (plants == thirdPlants) {
                validPlants.put(player, plants);
            }
        }

        int playerCount = 0;  // keeps track of the players added to result

        for (Player player : validPlants.keySet()) {  // for first place
            if (validPlants.get(player) == firstPlants) {
                result += "1. " + player.nickName + " from team " + player.team + " with " + firstPlants + " spikes planted.\n";
                validPlants.put(player, 0);  // remove the player after adding them
                playerCount++;
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for second place
            for (Player player : validPlants.keySet()) {
                if (validPlants.get(player) == secondPlants) {
                    result += "2. " + player.nickName + " from team " + player.team + " with " + secondPlants + " spikes planted.\n";
                    validPlants.put(player, 0);  // remove the player after adding them
                    playerCount++;
                }
            }
        }
        if (playerCount < MAX_PLAYERS) {  // for third place
            for (Player player : validPlants.keySet()) {
                if (validPlants.get(player) == thirdPlants) {
                    result += "3. " + player.nickName + " from team " + player.team + " with " + thirdPlants + " spikes planted.\n";
                    validPlants.put(player, 0);  // remove the player after adding them
                }
            }
        }

        return result;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // Overridden functions

    /**
     * Override toString to print Code.Player objects with useful information
     *
     * @return string way to print a player
     */
    @Override
    public String toString() {
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
