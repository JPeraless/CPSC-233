package Project_233;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class testing the program
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    23/03/2022
 * Tut:     T07
 */
class Project_233Test {

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

        System.out.println(allPlayersCopy);
        int count = 0;
        for (Player player : allPlayersCopy) {
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
    // test specific program output

    // test if the top3 by kills are given correctly
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

        HashMap<Player, Integer> result = Player.top3Kills();

        int count = 0;

        // look for the players in the HashMap
        for (Player player : result.keySet()) {
            if (player.getName().equals("name10") || player.getName().equals("name22") || player.getName().equals("name21")) {
                count += 1;
            }
        }

        assertEquals(3, count);
    }

    // test if the top3 by kills/deaths ratio are given correctly
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

        HashMap<Player, Double> result = Player.top3Ratio();

        int count = 0;

        // look for the players in the HashMap
        for (Player player : result.keySet()) {
            if (player.getName().equals("name10") || player.getName().equals("name21") || player.getName().equals("name22")) {
                count += 1;
            }
        }

        assertEquals(3, count);
    }

    // test if the top3 by kills per point are given correctly
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

        HashMap<Player, Double> result = Player.top3KillsPerPoint();

        int count = 0;

        // look for the players in the HashMap
        for (Player player : result.keySet()) {
            if (player.getName().equals("name12") || player.getName().equals("name32") || player.getName().equals("name22")) {
                count += 1;
            }
        }

        assertEquals(3, count);
    }

    // test if the top3 by spikes planted are given correctly
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

        HashMap<Player, Integer> result = Player.top3SpikePlants();

        int count = 0;

        // look for the players in the HashMap
        for (Player player : result.keySet()) {
            if (player.getName().equals("name22") || player.getName().equals("name12") || player.getName().equals("name31")) {
                count += 1;
            }
        }

        assertEquals(3, count);
    }

    // test if the top3 by spikes defused are given correctly
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

        HashMap<Player, Integer> result = Player.top3SpikeDefuses();

        int count = 0;

        // look for the players in the HashMap
        for (Player player : result.keySet()) {
            if (player.getName().equals("name12") || player.getName().equals("name30") || player.getName().equals("name32")) {
                count += 1;
            }
        }

        assertEquals(3, count);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // test info setters

    // test if team is set correctly
    @Test
    void testSetTeam() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setTeam("Kan");
        assertEquals("Kan", player10.getTeam());
    }

    // test if team is set correctly
    @Test
    void testSetName() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setName("Maria");
        assertEquals("Maria", player10.getName());
    }

    // test if team is set correctly
    @Test
    void testSetNickname() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setNickName("Nugle");
        assertEquals("Nugle", player10.getNickName());
    }

    // test if team is set correctly
    @Test
    void testSetRole() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setRole("Duelist");
        assertEquals("Duelist", player10.getRole());
    }

    // test if team is set correctly
    @Test
    void testSetAge() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        player10.setAge("23");
        assertEquals("23", player10.getAge());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // stats modifiers

    // test if kill is added correctly
    @Test
    void testAddKill() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addKill(Player.getPlayer("nickname1"));

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int kills = stats[0];

        assertEquals(1, kills);
    }

    // test if death is added correctly
    @Test
    void testAddDeath() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addDeath(Player.getPlayer("nickname1"));

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int deaths = stats[1];

        assertEquals(1, deaths);
    }

    // test if defuse is added correctly
    @Test
    void testAddDefuse() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addDefuse(Player.getPlayer("nickname1"));

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int defuses = stats[2];

        assertEquals(1, defuses);
    }

    // test if plant is added correctly
    @Test
    void testAddPlant() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addPlant(Player.getPlayer("nickname1"));

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int plants = stats[3];

        assertEquals(1, plants);
    }

    // test if amount is added correctly
    @Test
    void testAddAmount() {
        Player player10 = new Player("team1", "name1", "nickname1", "role1", "18");

        Player.addAmount(Player.getPlayer("nickname1"), 3900);

        HashMap<Player, int[]> allStatsCopy = Player.copyOfStats();

        int[] stats = allStatsCopy.get(player10);
        int amount = stats[4];

        assertEquals(3900, amount);
    }

}
