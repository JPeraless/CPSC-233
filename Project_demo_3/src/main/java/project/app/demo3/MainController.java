package project.app.demo3;

import code.Player;
import code.util.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * GUI application controller
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    11/04/2022
 * Tut:     T07
 */
public class MainController {

    // player info
    @FXML
    private TextField playerTeam;

    @FXML
    private TextField playerName;

    @FXML
    private TextField playerNickname;

    @FXML
    private TextField playerRole;

    @FXML
    private TextField playerAge;


    //stats
    @FXML
    private TextField statsNickname;

    @FXML
    private TextField killsField;

    @FXML
    private TextField deathsField;

    @FXML
    private TextField defusesField;

    @FXML
    private TextField plantsField;

    @FXML
    private TextField spentField;


    // stats buttons
    @FXML
    private RadioButton killsBtn;

    @FXML
    private RadioButton deathsBtn;

    @FXML
    private RadioButton defusesBtn;

    @FXML
    private RadioButton plantsBtn;

    @FXML
    private RadioButton spentBtn;


    // options label
    @FXML
    private Label optionsLabel;


    // options input
    @FXML
    private TextField optionsInputOne;

    @FXML
    private TextField optionsInputTwo;


    // user choice
    @FXML
    private TextField userInput;


    // output label
    @FXML
    private TextArea outputArea;


    // status label
    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    // -----------------------------------------------------------------

    /**
     * Initializes the GUI with predetermined values
     */
    @FXML
    public void initialize() {
        // display the output options correctly
        optionsLabel.setText("""
                1) Print all the players.
                2) Print the players of a team.
                3) Print the players in a specific role.
                4) Print the players of a given age range.
                5) Top 3 players by no. of kills.
                6) Top 3 players by kills/deaths ratio.
                7) Top 3 players by kills per point spent.
                8) Top 3 players by no. of spikes defused.
                9) Top 3 players by no. of spikes planted.
                """);

        // remove options input textFields visibility
        optionsInputOne.setVisible(false);
        optionsInputTwo.setVisible(false);
    }

    /**
     * Unselects the other stats buttons if the kills one is selected
     *
     * @param event event type that triggers the function
     */
    @FXML
    void killsClicked(MouseEvent event) {
        deathsBtn.setSelected(false);
        defusesBtn.setSelected(false);
        plantsBtn.setSelected(false);
        spentBtn.setSelected(false);
    }

    /**
     * Unselects the other stats buttons if the deaths one is selected
     *
     * @param event event type that triggers the function
     */
    @FXML
    void deathsClicked(MouseEvent event) {
        killsBtn.setSelected(false);
        defusesBtn.setSelected(false);
        plantsBtn.setSelected(false);
        spentBtn.setSelected(false);
    }

    /**
     * Unselects the other stats buttons if the defuses one is selected
     *
     * @param event event type that triggers the function
     */
    @FXML
    void defusesClicked(MouseEvent event) {
        killsBtn.setSelected(false);
        deathsBtn.setSelected(false);
        plantsBtn.setSelected(false);
        spentBtn.setSelected(false);
    }

    /**
     * Unselects the other stats buttons is the plants one is selected
     *
     * @param event event type that triggers the function
     */
    @FXML
    void plantsClicked(MouseEvent event) {
        killsBtn.setSelected(false);
        deathsBtn.setSelected(false);
        defusesBtn.setSelected(false);
        spentBtn.setSelected(false);
    }

    /**
     * Unselects the other stats buttons if the amount spent one is selected
     *
     * @param event Event type that triggers the function
     */
    @FXML
    void spentClicked(MouseEvent event) {
        killsBtn.setSelected(false);
        deathsBtn.setSelected(false);
        defusesBtn.setSelected(false);
        plantsBtn.setSelected(false);
    }

    /**
     * Adds stats to an indicated player.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void addStats(MouseEvent event) {
        // if no stats button has been selected
        if (!killsBtn.isSelected() && !deathsBtn.isSelected() && !defusesBtn.isSelected() && !plantsBtn.isSelected() && !spentBtn.isSelected()) {
            leftStatus.setText("Please indicate which stats parameter is to be added");
            rightStatus.setText("Select a stat to be added");
            return;
        }

        // if a stats button was selected
        try {  // if the player given by the user does exist

            if (killsBtn.isSelected()) {  // add kills
                int kills;
                try {  // try adding the kills to the given player
                    kills = Integer.parseInt(killsField.getText());
                    Player.addKills(Player.getPlayer(statsNickname.getText()), kills);

                    // update the statuses
                    leftStatus.setText("Kills added successfully");
                    rightStatus.setText("Added " + kills + " kills");
                }
                catch (NumberFormatException e) {  // if user input is invalid

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse int kills from '" + killsField.getText() + "'");
                }

            }
            else if (deathsBtn.isSelected()) {  // add deaths
                int deaths;
                try {  // try adding the deaths to the given player
                    deaths = Integer.parseInt(deathsField.getText());
                    Player.addDeaths(Player.getPlayer(statsNickname.getText()), deaths);

                    // update the statuses
                    leftStatus.setText("Deaths added successfully");
                    rightStatus.setText("Added " + deaths + " deaths");
                }
                catch (NumberFormatException e) {  // if user input is invalid

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse int deaths from '" + deathsField.getText() + "'");
                }

            }
            else if (defusesBtn.isSelected()) {  // add defuses
                int defuses;
                try {  // try adding the defuses to the given player
                    defuses = Integer.parseInt(defusesField.getText());
                    Player.addDefuses(Player.getPlayer(statsNickname.getText()), defuses);

                    // update the statuses
                    leftStatus.setText("Defuses added successfully");
                    rightStatus.setText("Added " + defuses + " defuses");
                }
                catch (NumberFormatException e) {  // if user input is invalid

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse int defuses from '" + defusesField.getText() + "'");
                }

            }
            else if (plantsBtn.isSelected()) {  // add plants
                int plants;
                try {  // try adding the plants to the given player
                    plants = Integer.parseInt(plantsField.getText());
                    Player.addPlants(Player.getPlayer(statsNickname.getText()), plants);

                    // update the statuses
                    leftStatus.setText("Plants added successfully");
                    rightStatus.setText("Added " + plants + " plants");
                }
                catch (NumberFormatException e) {  // if user input is invalid

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse int deaths from '" + plantsField.getText() + "'");
                }

            }
            else if (spentBtn.isSelected()) {  // add amount
                int spent;
                try {  // try adding the amount to the given player
                    spent = Integer.parseInt(spentField.getText());
                    Player.addAmount(Player.getPlayer(statsNickname.getText()), spent);

                    // update the statuses
                    leftStatus.setText("Amount added successfully");
                    rightStatus.setText("Added " + spent + " to amount spent");
                }
                catch (NumberFormatException e) {  // if user input is invalid

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse int deaths from '" + spentField.getText() + "'");
                }
            }
        }
        catch (Exception e) {  // if the player does not exist (Player.getPlayer returns null)

            // update the statuses
            leftStatus.setText("Please try a different nickname");
            rightStatus.setText("'" + statsNickname.getText() + "' does not exist.");
        }

        // make both textFields invisible
        optionsInputOne.setVisible(false);
        optionsInputTwo.setVisible(false);
    }

    /**
     * Creates a new player with the information given. Performs basic error checking.
     *
     * @param event type that triggers the function
     */
    @FXML
    void addPlayer(MouseEvent event) {
        try {
            String team = playerTeam.getText();
            try {
                String name = playerName.getText();
                try {
                    String nickname = playerNickname.getText();
                    try {
                        String role = playerRole.getText();
                        try {
                            String age = playerAge.getText();
                            try {
                                // create a player with the information provided by the user
                                Player player = new Player(team, name, nickname, role, age);

                                // update the statuses
                                leftStatus.setText("Player was added successfully!");
                                rightStatus.setText("New player: '" + player.getNickName() + "'");

                            } catch (Exception e) {  // player couldn't be created

                                // update the statuses
                                leftStatus.setText("An error occurred when creating the player");
                                rightStatus.setText("Player could not be created");
                            }
                        } catch (Exception e) {  // invalid age

                            // update the statuses
                            leftStatus.setText("Please enter a valid input");
                            rightStatus.setText("Can't parse string age from '" + playerAge.getText() + "'");
                        }
                    } catch (Exception e) {  // invalid role

                        // update the statuses
                        leftStatus.setText("Please enter a valid input");
                        rightStatus.setText("Can't parse string role from '" + playerRole.getText() + "'");
                    }
                } catch (Exception e) {  // invalid nickname

                    // update the statuses
                    leftStatus.setText("Please enter a valid input");
                    rightStatus.setText("Can't parse string nickname from '" + playerNickname.getText() + "'");
                }
            } catch (Exception e) {  // invalid name

                // update the statuses
                leftStatus.setText("Please enter a valid input");
                rightStatus.setText("Can't parse string name from '" + playerName.getText() + "'");
            }
        } catch (Exception e) {  // invalid team name

            // update the statuses
            leftStatus.setText("Please enter a valid input");
            rightStatus.setText("Can't parse string team from '" + playerTeam.getText() + "'");
        }

        // make both textFields invisible
        optionsInputOne.setVisible(false);
        optionsInputTwo.setVisible(false);
    }

    /**
     * Displays information about the program to the user.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void aboutProgram(ActionEvent event) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setContentText("Authors: Jose Perales and Matias Campuzano\n\nEmails: jose.peralesrivera@ucalgary.ca\n\t   matias.campuzano@ucalgary.ca\n\nVersion: v1.0\n\nThis is a Valorant stats tracker program");
        dialog.setTitle("About");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.show();
    }

    /**
     * Loads data from an input file.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void loadFile(ActionEvent event) {

        // boilerplate code to create an appropriate FileChooser
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Select the file to load data");
        fileChooser.setInitialFileName("inFileSmall.txt");

        int playerCount = 0;

        // prompt the user for a file to load
        File inFile = fileChooser.showOpenDialog(new Stage());
        if (inFile != null) {
            playerCount = Reader.loadFile(inFile);
        }

        // update the statuses
        leftStatus.setText("Data loaded successfully!");
        rightStatus.setText("Loaded " + playerCount + " players from file");

        // make both textFields invisible
        optionsInputOne.setVisible(false);
        optionsInputTwo.setVisible(false);
    }

    /**
     * Saves program data to an external file.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void saveFile(ActionEvent event) {
        if (Player.playersNo() > 0) {  // if there is something to store
             // boilerplate code to create an appropriate FileChooser
             final FileChooser fileChooser= new FileChooser();
             fileChooser.setTitle("Choose file to output the data");
             fileChooser.setInitialDirectory(new File ("."));
             fileChooser.setInitialFileName("outFile.txt");

             // prompt the user for a file to save
             File outFile = fileChooser.showSaveDialog(new Stage());
             if (outFile != null) {
                 try {
                     PrintWriter printWriter = new PrintWriter(outFile);

                     // get an arrayList with String data of every player in existence
                     ArrayList<String> allData = Player.playersToString();
                     for (String player : allData) {
                         printWriter.println(player);  // save the player to the selected file
                         printWriter.flush();  // flush the data to store it safely
                     }

                     // update the statuses
                     leftStatus.setText("Data saved successfully!");
                     rightStatus.setText("Saved in a .csv format");
                 }
                 catch (FileNotFoundException e) {

                     // update the statuses
                     leftStatus.setText("File could not be handled");
                     rightStatus.setText("Error initializing PrintWriter");
                 }
             }
         }
         else {  // if there is no data to store

            // update the statuses
             leftStatus.setText("Cannot save the file");
             rightStatus.setText("There must exist some data to save the file");
         }

         // make both textFields invisible
         optionsInputOne.setVisible(false);
         optionsInputTwo.setVisible(false);
    }

    /**
     * Ends the program
     *
     * @param event event type that triggers the function
     */
    @FXML
    void quitProgram(ActionEvent event) {
        System.out.println("Program terminated successfully");
        System.exit(1);
    }

    /**
     * Outputs the specified option to the GUI
     *
     * @param event event type that triggers the function
     */
    @FXML
    void display(MouseEvent event) {
        boolean specialOutput = false;

        // make special output valid if there are at least 3 players in existence
        if (Player.playersNo() >= 3) {
            specialOutput = true;
        }

        int choice;
        try {
            choice = Integer.parseInt(userInput.getText());

            if (choice == 1) {  // all the players (no need for input from textField)
                // print the info
                outputArea.setText(Player.allPlayers());

                // update the statuses
                leftStatus.setText("All players (" + Player.playersNo() + ")");
                rightStatus.setText("Output was successful!");

                // make both textFields invisible
                optionsInputOne.setVisible(false);
                optionsInputTwo.setVisible(false);
            }
            else if (choice == 2) {  // players of a team (input from textField required)
                byTeamOutput();
            }
            else if (choice == 3) {  // players in a specific role (input from textField required)
                byRoleOutput();
            }
            else if (choice == 4) {  // players in a given age range (input from textField required)
                byAgeOutput();
            }
            else if (choice == 5) {  // top 3 by kills
                if (specialOutput) {
                    // print the info
                    outputArea.setText(Player.top3Kills());

                    // update the statuses
                    leftStatus.setText("Top 3 kills");
                    rightStatus.setText("Output was successful!");

                    // make both textFields invisible
                    optionsInputOne.setVisible(false);
                    optionsInputTwo.setVisible(false);
                }
                else {
                    // update the statuses
                    leftStatus.setText("Not enough players in existence");
                    rightStatus.setText("Number of players: " + Player.playersNo());
                }
            }
            else if (choice == 6) {  // top 3 by ratio
                if (specialOutput) {
                    // print the info
                    outputArea.setText(Player.top3Ratio());

                    // update the statuses
                    leftStatus.setText("Top 3 ratio");
                    rightStatus.setText("Output was successful!");

                    // make both textFields invisible
                    optionsInputOne.setVisible(false);
                    optionsInputTwo.setVisible(false);
                }
                else {
                    // update the statuses
                    leftStatus.setText("Not enough players in existence");
                    rightStatus.setText("Number of players: " + Player.playersNo());
                }
            }
            else if (choice == 7) {  // top 3 kills per point
                if (specialOutput) {
                    // print the info
                    outputArea.setText(Player.top3KillsPerPoint());

                    // update the statuses
                    leftStatus.setText("Top 3 kills per point");
                    rightStatus.setText("Output was successful!");

                    // make both textFields invisible
                    optionsInputOne.setVisible(false);
                    optionsInputTwo.setVisible(false);
                }
                else {
                    // update the statuses
                    leftStatus.setText("Not enough players in existence");
                    rightStatus.setText("Number of players: " + Player.playersNo());
                }
            }
            else if (choice == 8) {  // top 3 spikes defused
                if (specialOutput) {
                    // print the info
                    outputArea.setText(Player.top3SpikeDefuses());

                    // update the statuses
                    leftStatus.setText("Top 3 spikes defused");
                    rightStatus.setText("Output was successful!");

                    // make both textFields invisible
                    optionsInputOne.setVisible(false);
                    optionsInputTwo.setVisible(false);
                }
                else {
                    // update the statuses
                    leftStatus.setText("Not enough players in existence");
                    rightStatus.setText("Number of players: " + Player.playersNo());
                }
            }
            else if (choice == 9) {  // top 3 spikes planted
                if (specialOutput) {
                    // print the info
                    outputArea.setText(Player.top3SpikePlants());

                    // update the statuses
                    leftStatus.setText("Top 3 spikes planted");
                    rightStatus.setText("Output was successful!");

                    // make both textFields invisible
                    optionsInputOne.setVisible(false);
                    optionsInputTwo.setVisible(false);
                }
                else {
                    // update the statuses
                    leftStatus.setText("Not enough players in existence");
                    rightStatus.setText("Number of players: " + Player.playersNo());
                }

            }
            else {
                // update the statuses
                leftStatus.setText("Please enter a valid option");
                rightStatus.setText("Int " + userInput.getText() + " is out of bounds");

                // make both textFields invisible
                optionsInputOne.setVisible(false);
                optionsInputTwo.setVisible(false);
            }

        }
        catch (NumberFormatException e) {
            // update the statuses
            leftStatus.setText("Please enter a valid input");
            rightStatus.setText("Can't parse int choice from '" + userInput.getText() + "'");
        }
    }

    /**
     * Handles the output of players in a given team
     */
    private void byTeamOutput() {
        // make the required textField visible
        optionsInputOne.setVisible(true);
        optionsInputOne.setPromptText("Enter the team name");

        optionsInputTwo.setVisible(false);

        try {
            // print the info
            outputArea.setText(Player.playersByTeam(optionsInputOne.getText()));

            // update the statuses
            leftStatus.setText("Players in team: " + optionsInputOne.getText());
            rightStatus.setText("Output was successful!");
        }
        catch (Exception e) {  // if there is an error parsing the name of the team

            // update the statuses
            leftStatus.setText("Please enter a valid input");
            rightStatus.setText("Can't parse string team from '" + optionsInputOne.getText() + "'");
        }

        // reset text for easier input from the user
        optionsInputOne.setText("");
    }

    /**
     * Handles the output of players in a given role
     */
    private void byRoleOutput() {
        // make the required textField visible
        optionsInputOne.setVisible(true);
        optionsInputOne.setPromptText("Enter the role");

        optionsInputTwo.setVisible(false);

        try {
            // print the info
            outputArea.setText(Player.playersByRole(optionsInputOne.getText()));

            // update the statuses
            leftStatus.setText("Players in role: " + optionsInputOne.getText());
            rightStatus.setText("Output was successful!");
        }
        catch (Exception e) {

            // update the statuses
            leftStatus.setText("Please enter a valid input");
            rightStatus.setText("Can't parse string role from '" + optionsInputOne.getText() + "'");
        }

        // reset text for easier input from the user
        optionsInputOne.setText("");
    }

    /**
     * Handles the output of players in a given age range
     */
    private void byAgeOutput() {
        // make both textFields visible
        optionsInputOne.setVisible(true);
        optionsInputOne.setPromptText("Enter the starting age");

        optionsInputTwo.setVisible(true);
        optionsInputTwo.setPromptText("Enter the ending age");

        int startAge;
        int endAge;

        try {
            startAge = Integer.parseInt(optionsInputOne.getText());
            try {
                endAge = Integer.parseInt(optionsInputTwo.getText());

                // check if the starting and ending ages are appropriate
                if (startAge > endAge) {

                    // update the statuses
                    leftStatus.setText("Please enter valid age values");
                    rightStatus.setText("Starting age can't be greater than ending age");
                    return;
                }

                // print the info
                outputArea.setText(Player.playersByAge(startAge, endAge));

                // update the statuses
                leftStatus.setText("Players from age " + optionsInputOne.getText() + " to " + optionsInputTwo.getText());
                rightStatus.setText("Output was successful!");

            } catch (NumberFormatException e) {  // invalid ending age

                // update the statuses
                leftStatus.setText("Please enter a valid ending age");
                rightStatus.setText("Can´t parse int ending age from '" + optionsInputTwo + "'");
            }
        } catch (NumberFormatException e) {  // invalid starting age

            // update the statuses
            leftStatus.setText("Please enter a valid starting age");
            rightStatus.setText("Can't parse int starting age from '" + optionsInputOne.getText() + "'");
        }

        // reset text for easier input from the user
        optionsInputOne.setText("");
        optionsInputTwo.setText("");
    }

}
