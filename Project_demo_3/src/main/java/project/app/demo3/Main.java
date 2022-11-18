package project.app.demo3;

import code.util.CommandLine;
import code.util.Reader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Executes the program
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    11/04/2022
 * Tut:     T07
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("Valorant Stats Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        CommandLine.argumentCheck(args);  // check no. of args inputted

        boolean openFile;  // true == open file; false == do nothing

        openFile = Boolean.parseBoolean(args[0]);

        if (openFile) {  // open file
            File inFile = new File(args[1]);
            CommandLine.inputFileCheck(inFile);
            Reader.loadFile(inFile);
        }

        launch();
    }

}