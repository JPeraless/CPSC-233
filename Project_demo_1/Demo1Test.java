import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Demo1Test {


    ArrayList<String> profile1 = new ArrayList<String>();
    ArrayList<String> profile2 = new ArrayList<String>();
    ArrayList<String> team1 = new ArrayList<String>();
    ArrayList<String> team2 = new ArrayList<String>();

    ArrayList<String> teamsList = new ArrayList<String>();
    ArrayList<ArrayList<String>> infoDB = new ArrayList<ArrayList<String>>();
    HashMap<String, int[]> statsDB = new HashMap<String, int[]>();


    // *****************************************************************************************************************

    // test for 1 profile in infoDB
    @Test
    void oneTeam2InfoDB() {
        profile1.add("team1"); profile1.add("name"); profile1.add("nickname"); profile1.add("role"); profile1.add("age");
        Demo1.profile2InfoDB(infoDB, profile1);
        assertEquals(1, infoDB.size());
    }

    // test for 2 profiles in infoDB
    @Test
    void twoTeams2InfoDB() {

        profile1.add("team1"); profile1.add("name1"); profile1.add("nickname1"); profile1.add("role"); profile1.add("age");
        profile2.add("team2"); profile2.add("name2"); profile2.add("nickname2"); profile2.add("role"); profile2.add("age");
        System.out.println(profile1+"\n"+profile2);
        Demo1.profile2InfoDB(infoDB, profile1);
        Demo1.profile2InfoDB(infoDB, profile2);
        assertEquals(2, infoDB.size());
    }

    // test for no profiles in infoDB
    @Test
    void noTeams2infoDB() {
        assertEquals(0, infoDB.size());
    }

    // *****************************************************************************************************************

    // test for one profile
    @Test
    void oneProfileOneTeamUpdateStatsDB() {
        teamsList.add("team1");
        profile1.add("team1"); profile1.add("name"); profile1.add("nickname"); profile1.add("role"); profile1.add("age");
        Demo1.profile2InfoDB(infoDB, profile1);
        Demo1.updateStatsDB(infoDB, statsDB);

        int count = 0;
        for (String key : statsDB.keySet()) {
            count++;
        }

        assertEquals(1, count);
    }

    // test for two profiles in two different teams{
    @Test
    void twoProfilesTwoTeamsUpdateStatsDB() {
        teamsList.add("team1");
        teamsList.add("team2");

        profile1.add("team1"); profile1.add("name1"); profile1.add("nickname1"); profile1.add("role1"); profile1.add("age1");
        profile2.add("team2"); profile2.add("name2"); profile2.add("nickname2"); profile2.add("role2"); profile2.add("age2");


        Demo1.profile2InfoDB(infoDB, profile1);
        Demo1.profile2InfoDB(infoDB, profile2);
        Demo1.updateStatsDB(infoDB, statsDB);

        int count = 0;
        for (String key : statsDB.keySet()) {
            count++;
        }

        assertEquals(2, count);
    }

    // test for two profiles in the same team
    @Test
    void twoProfilesOneTeamUpdateStatsDB() {
        teamsList.add("team1");

        profile1.add("team1"); profile1.add("name1"); profile1.add("nickname1"); profile1.add("role1"); profile1.add("age1");
        profile2.add("team1"); profile2.add("name2"); profile2.add("nickname2"); profile2.add("role2"); profile2.add("age2");

        Demo1.profile2InfoDB(infoDB, profile1);
        Demo1.profile2InfoDB(infoDB, profile2);

        Demo1.updateStatsDB(infoDB, statsDB);

        int count = 0;
        for (String key : statsDB.keySet()) {
            count++;
        }

        assertEquals(2, count);
    }

}