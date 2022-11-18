package mvh.app;

import mvh.enums.Symbol;
import mvh.enums.WeaponType;
import mvh.util.Reader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mvh.world.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// import java.io.Reader;

/**
 * Name: Jose Perales
 * Date: 02/04/2022
 *
 * Controller class for the GUI
 */
public class MainController {

    //Store the data of editor
    private World world;

    /**
     * Setup the window state
     */
    @FXML
    public void initialize() {
    }


    // String for output file
    private String info;


    // world creation
    @FXML
    private TextField worldRows;

    @FXML
    private TextField worldCols;

    @FXML
    private Button createWorldBtn;


    // Monster info
    @FXML
    private RadioButton monButton;

    @FXML
    private TextField monSymField;

    @FXML
    private TextField monHealthField;

    @FXML
    private TextField monWeaponField;  // could be a TextField


    // Hero Info
    @FXML
    private RadioButton heroButton;

    @FXML
    private TextField heroSymField;

    @FXML
    private TextField heroHealthField;

    @FXML
    private TextField heroWeaponField;

    @FXML
    private TextField heroArmorField;


    // Entities
    @FXML
    private TextField entityRow;

    @FXML
    private TextField entityCol;

    @FXML
    private Button addEntityBtn;

    @FXML
    private Button delEntityBtn;


    // Error label
    @FXML
    private Label errorLabel;


    // Menu items
    @FXML
    private MenuItem infoItem;

    @FXML
    private MenuItem loadItem;

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem saveItem;


    // World display
    @FXML
    private Label worldDisplay;


    // World details
    @FXML
    private TextArea worldDetails;


    // Bottom status bar
    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;


    // start of the functions --------------------


    /**
     * Creates a new world based on user input.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void createWorld(MouseEvent event) {
        int rows = -1;  // there is going to be an issue when printing the catches
        int cols = -1;

        try {
            rows = Integer.parseInt(worldRows.getText());
            try {
                cols = Integer.parseInt(worldCols.getText());
                world = new World(rows, cols);
                worldDetails.setText("Dimensions of the world:\n\nRows: " + rows + "\nColumns: " + cols);
                errorLabel.setText("");
                leftStatus.setText("Created a new world!");
                rightStatus.setText("World created successfully");
                updateWorld();
            }
            catch (NumberFormatException e) {  // if columns input is not valid
                rightStatus.setText("Invalid columns input");
                leftStatus.setText("Please enter a valid input");
                errorLabel.setText("Can't parse integer columns from '" + worldCols.getText() + "'");
            }
        }
        catch (NumberFormatException e) {  // if rows input is not valid
            rightStatus.setText("Invalid rows input");
            leftStatus.setText("Please enter a valid input");
            errorLabel.setText("Can't parse integer rows from '" + worldRows.getText() + "'");
        }
    }

    /**
     * Add the specified entity to the given position in the world.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void addEntity(MouseEvent event) {
        boolean monster = monButton.isSelected();
        boolean hero = heroButton.isSelected();
        int row = -1;
        int col = -1;

        // deals with invalid row / col input
        try {
            row = Integer.parseInt(entityRow.getText());
            try {
                col = Integer.parseInt(entityCol.getText());
            }
            catch (NumberFormatException e) {
                rightStatus.setText("Invalid column input");
                leftStatus.setText("Please enter a valid input");
                errorLabel.setText("Can't parse integer column from '" + entityCol.getText() + "'");
            }
        }
        catch (NumberFormatException e) {
            rightStatus.setText("Invalid row input");
            leftStatus.setText("Please enter a valid input");
            errorLabel.setText("Can't parse integer row from '" + entityRow.getText() + "'");
        }

        // in case both buttons are pressed down
        if (monster && hero) {
            errorLabel.setText("Both entities cannot be selected at the same time.");
            leftStatus.setText("Unselect one of the entities to continue.");
            rightStatus.setText("Please select a single entity.");
        }
        else if (!monster && !hero) {  // if no button is pressed down
            errorLabel.setText("Please indicate the entity to be added");
            leftStatus.setText("Chose one entity.");
            rightStatus.setText("Select an entity.");
        }
        else if (monster) {  // for monster
            char symbol;
            int health;
            WeaponType weapon;

            try {
                symbol = monSymField.getText().charAt(0);
                try {
                    health = Integer.parseInt(monHealthField.getText());
                    try {
                        weapon = WeaponType.getWeaponType(monWeaponField.getText().charAt(0));
                        try {
                            world.addEntity(row - 1, col - 1, new Monster(health, symbol, weapon));
                            worldDisplay.setText("Entity: Monster\nSymbol: " + symbol + "\nHealth: " + health + "\nWeapon: " + weapon + "\nRow: " + row + "\nColumn: " + col);
                            leftStatus.setText("Monster added successfully!");
                            rightStatus.setText(":)");
                            updateWorld();
                            errorLabel.setText("");
                        }
                        catch (Exception e) {  // out of bounds
                            errorLabel.setText("The position given is out of bounds");
                            leftStatus.setText("Please enter a location inside the world.");
                            rightStatus.setText("Please try again");
                        }
                    }
                    catch (Exception e) {  // weapon
                        errorLabel.setText("Can't parse weapon type from '" + monWeaponField.getText() + "'");
                        leftStatus.setText("The weapon type entered is not valid.");
                        rightStatus.setText("Please try again");
                    }
                }
                catch (Exception e) {  // health
                    errorLabel.setText("Can't parse health from '" + monHealthField.getText() + "'");
                    leftStatus.setText("The health value entered is not valid.");
                    rightStatus.setText("Please try again");
                }
            }
            catch (Exception e) {  // symbol
                errorLabel.setText("Can't parse symbol from '" + monSymField.getText() + "'");
                leftStatus.setText("The symbol value entered is not valid.");
                rightStatus.setText("Please try again");
            }
        }
        else {  //  == else if (hero)
            char symbol;
            int health;
            int weapon;
            int armor;

            try {
                symbol = heroSymField.getText().charAt(0);
                try {
                    health = Integer.parseInt(heroHealthField.getText());
                    try {
                        weapon = Integer.parseInt(heroWeaponField.getText());
                        try {
                            armor = Integer.parseInt(heroArmorField.getText());
                            try {
                                world.addEntity(row - 1, col - 1, new Hero(health, symbol, weapon, armor));
                                worldDetails.setText("Entity: Monster\nSymbol: " + symbol + "\nHealth: " + health + "\nWeapon: " + weapon + "\nRow: " + row + "\nColumn: " + col);
                                leftStatus.setText("Hero added successfully!");
                                rightStatus.setText(":)");
                                errorLabel.setText("");
                                updateWorld();
                            }
                            catch (Exception e) {  // out of bounds
                                errorLabel.setText("The position given is out of bounds");
                                leftStatus.setText("Please enter a location inside the world.");
                                rightStatus.setText("Please try again");
                            }
                        }
                        catch (Exception e) {  // armor
                            errorLabel.setText("Can't parse armor from '" + heroArmorField.getText() + "'");
                            leftStatus.setText("The armor value entered is not valid.");
                            rightStatus.setText("Please try again");
                        }
                    }
                    catch (Exception e) {  // weapon
                        errorLabel.setText("Can't parse weapon type from '" + heroWeaponField.getText() + "'");
                        leftStatus.setText("The weapon value entered is not valid.");
                        rightStatus.setText("Please try again");
                    }
                }
                catch (Exception e) {  // health
                    errorLabel.setText("Can't parse health from '" + heroHealthField.getText() + "'");
                    leftStatus.setText("The health value entered is not valid.");
                    rightStatus.setText("Please try again");
                }
            }
            catch (Exception e) {  // symbol
                errorLabel.setText("Can't parse symbol from '" + heroSymField.getText() + "'");
                leftStatus.setText("The symbol value entered is not valid.");
                rightStatus.setText("Please try again");
            }
        }
    }

    /**
     * Deletes the entity in the specified location.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void deleteEntity(MouseEvent event) {
        boolean monster = monButton.isSelected();
        boolean hero = heroButton.isSelected();
        int row = -1;
        int col = -1;

        // deals with invalid row / col input
        try {
            row = Integer.parseInt(entityRow.getText());
            try {
                col = Integer.parseInt(entityCol.getText());
            }
            catch (NumberFormatException e) {
                rightStatus.setText("Invalid column input");
                leftStatus.setText("Please enter a valid input");
                errorLabel.setText("Can't parse integer column from '" + entityCol.getText() + "'");
            }
        }
        catch (NumberFormatException e) {
            rightStatus.setText("Invalid row input");
            leftStatus.setText("Please enter a valid input");
            errorLabel.setText("Can't parse integer row from '" + entityRow.getText() + "'");
        }

        try {
            // only possibility of crashing is the location is out of bounds
            Entity entity = world.getEntity(row - 1, col - 1);

            if (entity == null) {  // if there is nothing on that location
                leftStatus.setText("There is no entity in that location");
                rightStatus.setText("No changes were made");
            }
            else {
                world.addEntity(row - 1, col - 1, null);
                updateWorld();

                if (monster) {  // for monster
                    leftStatus.setText("Monster deleted successfully!");
                }
                else {  // for hero
                    leftStatus.setText("Hero deleted successfully!");
                }
                rightStatus.setText(":)");
            }
            errorLabel.setText("");

        }
        catch (Exception e) {  // out of bounds
            errorLabel.setText("The position given is out of bounds");
            leftStatus.setText("Please enter a location inside the world.");
            rightStatus.setText("Please try again");
        }
    }

    /**
     * Creates a string from the information in world and outputs it to worldDisplay.
     */
    private void updateWorld() {
        // crate a 2D ArrayList in which all the info will be stored
        ArrayList<ArrayList<String>> completeWorld = new ArrayList<>();

        // create a row ArrayList that will be used for every row to add to completeWorld
        ArrayList<String> row = new ArrayList<>();

        // set rows and cols of world
        int rowsNo = world.getRows();
        int colsNo = world.getColumns();
        final int WALLS_NO = 2;

        // add a wall to every entry in the first row
        for (int i = 0; i < colsNo + WALLS_NO; i++) {
            row.add(Character.toString(Symbol.WALL.getSymbol()));
        }

        // add a newline at the end of the row
        row.add("\n");

        // add the row to completeWorld
        completeWorld.add(row);

        for (int j = 0; j < rowsNo; j++) {
            row = new ArrayList<>(); // reset row to reuse it
            row.add(Character.toString(Symbol.WALL.getSymbol()));  // add the initial wall

            for (int k = 0; k < colsNo; k++) {
                Entity entity = world.getEntity(j, k);

                if (entity != null) {
                    String position = Character.toString(entity.getSymbol());
                    row.add(position);
                }
                else {
                    row.add(Character.toString(Symbol.FLOOR.getSymbol()));
                }
            }
            // add the ending wall
            row.add(Character.toString(Symbol.WALL.getSymbol()));

            // add a newline at the end of the row
            row.add("\n");
            completeWorld.add(row);
        }

        row = new ArrayList<String>();  // reset row to reuse it

        // bottom row full of walls
        for (int l = 0; l < colsNo + WALLS_NO; l++) {
            row.add(Character.toString(Symbol.WALL.getSymbol()));
        }

        // add the last row to completeWorld
        completeWorld.add(row);

        String strCompleteWorld = completeWorld.toString();

        strCompleteWorld = strCompleteWorld.replace(" ", "");  // get rid of whitespace
        strCompleteWorld = strCompleteWorld.replace(",", "");  // get rid of commas
        strCompleteWorld = strCompleteWorld.replace("[", "");  // get rid of parentheses
        strCompleteWorld = strCompleteWorld.replace("]", "");  // get rid of parentheses

        worldDisplay.setText(strCompleteWorld);  // display the world
        info += strCompleteWorld + "\n\n";  // update info

        // Set the program status
        leftStatus.setText("World updated successfully!");
        rightStatus.setText(":)");
        errorLabel.setText("");
    }

    /**
     * Displays information about the program to the user in a new window.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void displayProgramInfo(ActionEvent event) {  // couldn't find the correct dialog type
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("About");
        dialog.setContentText("Author: Jose Perales\nEmail: jose.peralesrivera@ucalgary.ca\nVersion: v1.0\nThis is a world editor for Monsters versus Heroes");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.show();
    }

    /**
     * Ends the program.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void quitProgram(ActionEvent event) {
        System.out.println("Program was successfully terminated");
        System.exit(0);
    }

    /**
     * Loads data from an input file.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void loadFile(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open file to load data");
        fileChooser.setInitialFileName("world.txt");

        File loadFile = fileChooser.showOpenDialog(new Stage());
        if (loadFile != null) {
            world = Reader.loadWorld(loadFile);
            updateWorld();
        }
    }

    /**
     * Stores program data to output file.
     *
     * @param event event type that triggers the function
     */
    @FXML
    void saveFile(ActionEvent event) {
        if (world != null) {  // if there is something to save
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.setTitle("Choose file to output data");
            fileChooser.setInitialFileName("world.txt");

            File outputFile = fileChooser.showSaveDialog(new Stage());
            if (outputFile != null) {
                logger(info, outputFile);
            }
            errorLabel.setText("");
        }
        else {  // if there is nothing to save
            errorLabel.setText("A world must exist for the file to be saved");
            leftStatus.setText("File cannot be saved");
            rightStatus.setText("There is no data to save");
        }
    }

    /**
     * Creates the FileWriter in charge of writing the data to the output file
     * And writes the data to that file
     *
     * @param outFile file where the input will be written
     */
    public static void logger(String info, File outFile) {
        try {
            FileWriter fileWriter = new FileWriter(outFile);

            // loop through the elements
            int elementNo = 0;
            while (elementNo < 4) {
                info = info.substring(1);
                elementNo++;
            }

            // write the info to outFile
            fileWriter.write(info);

            // close the fileWriter
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provides the details of an entity requested by the user
     *
     * @param event event type that triggers the function
     */
    @FXML
    void viewDetails(MouseEvent event) {
        int row = -1;
        int col = -1;

        // deals with invalid row / col input
        try {
            row = Integer.parseInt(entityRow.getText());
            try {
                col = Integer.parseInt(entityCol.getText());
            }
            catch (NumberFormatException e) {
                rightStatus.setText("Invalid column input");
                leftStatus.setText("Please enter a valid input");
                errorLabel.setText("Can't parse integer column from '" + entityCol.getText() + "'");
            }
        }
        catch (NumberFormatException e) {
            rightStatus.setText("Invalid row input");
            leftStatus.setText("Please enter a valid input");
            errorLabel.setText("Can't parse integer row from '" + entityRow.getText() + "'");
        }
        try {
            Entity entity = world.getEntity(row - 1, col - 1);

            if (entity == null) {
                worldDetails.setText("There is no entity in that location");
                leftStatus.setText("No details to show");
                rightStatus.setText("No entity was found");
                errorLabel.setText("");
            }
            else {
                if (world.isMonster(row - 1, col - 1)) {
                    String weapon;
                    if (entity.weaponStrength() == 2) {
                        weapon = "CLUB";
                    }
                    else if (entity.weaponStrength() == 3) {
                        weapon = "AXE";
                    }
                    else {
                        weapon = "SWORD";
                    }
                    worldDetails.setText("Entity: Monster\nSymbol: " + entity.getSymbol() + "\nHealth: " + entity.getHealth() + "\nWeapon: " + weapon +"\nWeapon Strength: " + entity.weaponStrength());
                }
                else {
                    worldDetails.setText("Entity: Hero\nSymbol: " + entity.getSymbol() + "\nHealth: " + entity.getHealth() + "\nWeapon Strength: " + entity.weaponStrength() + "\nArmor Strength: " + entity.armorStrength());
                }
                leftStatus.setText("Entity details shown");
                rightStatus.setText(":)");
                errorLabel.setText("");
            }
        }
        catch (Exception e) {
            errorLabel.setText("The position given is out of bounds");
            leftStatus.setText("Please enter a location inside the world.");
            rightStatus.setText("Please try again");
        }
    }

}
