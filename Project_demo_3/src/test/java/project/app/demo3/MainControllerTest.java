package project.app.demo3;

import code.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Backend testing file
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    13/04/2022
 * Tut:     T07
 */
class MainControllerTest {

    /**
     * Test that allPlayers works correctly and each player is added accordingly
     */
    @Test
    void playersToAllPlayers() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");
        Player player20 = new Player("team2", "name2", "nickname2", "role1", "19");
        Player player30 = new Player("team3", "name3", "nickname3", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        ArrayList<Player> allPlayersCopy = Player.copyOfPlayers();

        int count = 0;
        for (Player player : allPlayersCopy) {
            System.out.println(player + "\n");
            count++;
        }

        assertEquals(9, count);
    }

    /**
     * Test that allStats works correctly and each player is added accordingly
     */
    @Test
    void playersToAllStats() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");
        Player player20 = new Player("team2", "name2", "nickname2", "role1", "19");
        Player player30 = new Player("team3", "name3", "nickname3", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        for (Player player : allStatsCopy.keySet()) {
            System.out.println(player);
            System.out.println(Arrays.toString(allStatsCopy.get(player)));
        }

        int count = 0;
        for (Player player : allStatsCopy.keySet()) {
            count++;
        }

        assertEquals(9, count);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test special output methods

    /**
     * Test that the top3 by kills are given correctly
     */
    @Test
    void testKills() {

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        // set determined values
        player10.setKills(20); player11.setKills(5); player12.setKills(12);
        player20.setKills(12); player21.setKills(27); player22.setKills(30);
        player30.setKills(14); player31.setKills(9); player32.setKills(10);

        // results should be: 1. name22, 2. name21, 3. name10

        String result = Player.top3Kills();
        String[] topPlayers = result.split("\n");

        System.out.println(result);
        System.out.println(Arrays.toString(topPlayers));

        int count = 0;

        // look for the players in the HashMap
        for (String player : topPlayers) {
            if (player.contains("name10") || player.contains("name22") || player.contains("name21")) {
                count ++;
            }
        }

        assertEquals(3, count);
    }

    /**
     * Test that the top3 by kills/deaths ratio are given correctly
     */
    @Test
    void testRatio() {

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        // set determined values
        player10.setKills(20); player11.setKills(5); player12.setKills(12);
        player20.setKills(12); player21.setKills(24); player22.setKills(32);
        player30.setKills(14); player31.setKills(9); player32.setKills(10);

        player10.setDeaths(4); player11.setDeaths(9); player12.setDeaths(10);
        player20.setDeaths(5); player21.setDeaths(8); player22.setDeaths(11);
        player30.setDeaths(6); player31.setDeaths(7); player32.setDeaths(12);

        // results should be: 1. name10, 2. name, 21, 3. name 22

        String result = Player.top3Ratio();
        String[] topPlayers = result.split("\n");

        int count = 0;

        // look for the players in the HashMap
        for (String player : topPlayers) {
            if (player.contains("name10") || player.contains("name21") || player.contains("name22")) {
                count++;
            }
        }

        assertEquals(3, count);
    }

    /**
     * Test that the top3 by kills per point are given correctly
     */
    @Test
    void testKillsPerPoint() {
        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        // set determined values
        player10.setKills(4); player11.setKills(9); player12.setKills(12);
        player20.setKills(12); player21.setKills(17); player22.setKills(8);
        player30.setKills(14); player31.setKills(25); player32.setKills(10);

        player10.setAmount(4500); player11.setAmount(9500); player12.setAmount(1000);
        player20.setAmount(5700); player21.setAmount(8400); player22.setAmount(2100);
        player30.setAmount(6900); player31.setAmount(7300); player32.setAmount(1200);

        // Results should be: 1. name12, 2. name32, 3. name22

        String result = Player.top3KillsPerPoint();
        String[] topPlayers = result.split("\n");

        int count = 0;

        // look for the players in the HashMap
        for (String player : topPlayers) {
            if (player.contains("name22") || player.contains("name12") || player.contains("name32")) {
                count++;
            }
        }

        assertEquals(3, count);
    }

    /**
     * Test that the top3 by spikes planted are given correctly
     */
    @Test
    void testSpikePlants() {
        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        // set determined values
        player10.setPlants(10); player11.setPlants(12); player12.setPlants(15);
        player20.setPlants(5); player21.setPlants(7); player22.setPlants(17);
        player30.setPlants(9); player31.setPlants(14); player32.setPlants(13);

        // results should be: 1. name22, 2. name12, 3. name31

        String result = Player.top3SpikePlants();
        String[] topPlayers = result.split("\n");

        int count = 0;

        // look for the players in the HashMap
        for (String player : topPlayers) {
            if (player.contains("name22") || player.contains("name12") || player.contains("name31")) {
                count++;
            }
        }

        assertEquals(3, count);
    }

    /**
     * Test that the top3 by spikes defused are given correctly
     */
    @Test
    void testSpikeDefuses() {

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        // set determined values
        player10.setDefuses(10); player11.setDefuses(5); player12.setDefuses(15);
        player20.setDefuses(12); player21.setDefuses(7); player22.setDefuses(12);
        player30.setDefuses(14); player31.setDefuses(9); player32.setDefuses(13);

        // results should be: 1. name12, 2. name30, 3. name32

        String result = Player.top3SpikeDefuses();
        String[] topPlayers = result.split("\n");

        int count = 0;

        // look for the players in the HashMap
        for (String player : topPlayers) {
            if (player.contains("name12") || player.contains("name30") || player.contains("name32")) {
                count++;
            }
        }

        assertEquals(3, count);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test stats modifiers

    /**
     * Test that kills are added correctly
     */
    @Test
    void testAddKills() {
        int EXPECTED = 5;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addKills(Player.getPlayer("nickname1"), EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int kills = stats[0];

        assertEquals(EXPECTED, kills);
    }

    /**
     * Test that deaths are added correctly
     */
    @Test
    void testAddDeaths() {
        int EXPECTED = 5;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addDeaths(Player.getPlayer("nickname1"), EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int deaths = stats[1];

        assertEquals(EXPECTED, deaths);
    }

    /**
     * Test that defuses are added correctly
     */
    @Test
    void testAddDefuses() {
        int EXPECTED = 5;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addDefuses(Player.getPlayer("nickname1"),EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int defuses = stats[2];

        assertEquals(EXPECTED, defuses);
    }

    /**
     * Test that plants are added correctly
     */
    @Test
    void testAddPlants() {
        int EXPECTED = 5;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addPlants(Player.getPlayer("nickname1"), EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int plants = stats[3];

        assertEquals(EXPECTED, plants);
    }

    /**
     * Test that amount is added correctly
     */
    @Test
    void testAddAmount() {
        int EXPECTED = 3900;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addAmount(Player.getPlayer("nickname1"), EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int amount = stats[4];

        assertEquals(EXPECTED, amount);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test stats setters

    /**
     * Test that kills are set correctly
     */
    @Test
    void testSetKills() {
        int EXPECTED = 10;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");
        player10.setKills(EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int kills = 0;

        for (Player player : allStatsCopy.keySet()) {
            if (player.equals(player10)) {
                kills = allStatsCopy.get(player)[0];
            }
        }
        System.out.println(kills);

        assertEquals(EXPECTED, kills);
    }

    /**
     * Test that deaths are set correctly
     */
    @Test
    void testSetDeaths() {
        int EXPECTED = 10;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");
        player10.setDeaths(EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int deaths = 0;

        for (Player player : allStatsCopy.keySet()) {
            if (player.equals(player10)) {
                deaths = allStatsCopy.get(player)[1];
            }
        }
        System.out.println(deaths);

        assertEquals(EXPECTED, deaths);
    }

    /**
     * Test that defuses are set correctly
     */
    @Test
    void testSetDefuses() {
        int EXPECTED = 10;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setDefuses(EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int defuses = 0;

        for (Player player : allStatsCopy.keySet()) {
            if (player.equals(player10)) {
                defuses = allStatsCopy.get(player)[2];
            }
        }
        System.out.println(defuses);

        assertEquals(EXPECTED, defuses);
    }

    /**
     * Test that plants are set correctly
     */
    @Test
    void testSetPlants() {
        int EXPECTED = 10;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setPlants(EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int plants = 0;

        for (Player player : allStatsCopy.keySet()) {
            if (player.equals(player10)) {
                plants = allStatsCopy.get(player)[3];
            }
        }
        System.out.println(plants);

        assertEquals(EXPECTED, plants);
    }

    /**
     * Test that amount is set correctly
     */
    @Test
    void testSetAmount() {
        int EXPECTED = 10;

        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setAmount(EXPECTED);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int amount = 0;

        for (Player player : allStatsCopy.keySet()) {
            if (player.equals(player10)) {
                amount = allStatsCopy.get(player)[4];
            }
        }
        System.out.println(amount);

        assertEquals(EXPECTED, amount);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test general Player methods

    /**
     * Test that the correct number of players is returned by playersNo()
     */
    @Test
    void testPlayersNo() {
        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        int something = Player.playersNo();

        assertEquals(9, something);
    }

    /**
     * Test that getPlayer() correctly returns the player it is asked
     */
    @Test
    void testGetPlayer() {
        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player selected = Player.getPlayer( "nickname30");

        assertEquals(player30, selected);
    }

    /**
     * Test that all the players are correctly returned in String form as entries in an ArrayList
     */
    @Test
    void testPlayersToString() {
        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        ArrayList<String> allData = Player.playersToString();

        int playerCount = 0;

        for (String player : allData) {
            if (player.contains("name10") || player.contains("name20") || player.contains("name30") || player.contains("name11") || player.contains("name21")) {
                playerCount++;
            }
            else if (player.contains("name31") || player.contains("name12") || player.contains("name22") || player.contains("name32")) {
                playerCount++;
            }
        }

        assertEquals(9, playerCount);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Test general output methods

    /**
     * Test that allPlayers() returns a String with the info of all existing players
     */
    @Test
    void testAllPlayers() {
        // number of players
        final int VALID_PLAYERS = 9;

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        String allData = Player.allPlayers();
        String[] result = allData.split("\n----------\n\n----------\n");

        int count = 0;
        for (String player : result) {
            count++;
        }

        assertEquals(VALID_PLAYERS, count);
    }

    /**
     * Test that playersByTeam() returns a String with all the member of a given team
     */
    @Test
    void testPlayersByTeam() {
        // number of players in team
        final int VALID_PLAYERS = 3;

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        String allData = Player.playersByTeam("team2");
        String[] result = allData.split("\n----------\n\n----------\n");

        int count = 0;
        for (String player : result) {
            count++;
        }

        assertEquals(VALID_PLAYERS, count);

    }

    /**
     * Test that playersByRole() returns a String with all the players fulfilling the given role
     */
    @Test
    void testPlayersByRole() {
        // number of players is role
        final int VALID_PLAYERS = 3;

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        String allData = Player.playersByRole("role3");
        String[] result = allData.split("\n----------\n\n----------\n");

        int count = 0;
        for (String player : result) {
            count++;
        }

        assertEquals(VALID_PLAYERS, count);

    }

    /**
     * Test that playersByAge() returns a String with all the players in the given age range
     */
    @Test
    void testPlayersByAge() {
        // number of players in age range
        final int VALID_PLAYERS = 5;

        // initialize players
        Player player10 = new Player("team1", "name10", "nickname10", "role1", "18");
        Player player20 = new Player("team2", "name20", "nickname20", "role1", "19");
        Player player30 = new Player("team3", "name30", "nickname30", "role1", "20");

        Player player11 = new Player("team1", "name11", "nickname11", "role2", "19");
        Player player21 = new Player("team2", "name21", "nickname21", "role2", "20");
        Player player31 = new Player("team3", "name31", "nickname31", "role2", "18");

        Player player12 = new Player("team1", "name12", "nickname12", "role3", "22");
        Player player22 = new Player("team2", "name22", "nickname22", "role3", "24");
        Player player32 = new Player("team3", "name32", "nickname32", "role3", "23");

        String allData = Player.playersByAge(20, 24);
        String[] result = allData.split("\n----------\n\n----------\n");

        int count = 0;
        for (String player : result) {
            count++;
        }

        assertEquals(VALID_PLAYERS, count);

    }

}