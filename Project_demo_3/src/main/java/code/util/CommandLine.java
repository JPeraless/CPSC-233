package code.util;

import java.io.File;

/**
 * Command line arguments check
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    11/04/2022
 * Tut:     T07
 */
public class CommandLine {

    /**
     * Verifies that the program is provided with the arguments necessary to run
     */
    public static void argumentCheck(String[] args) {
        if (args.length != 2) {
            System.err.println("Program requires 2 arguments to run.");
            System.err.println("No. of arguments provided: " + args.length);
            System.exit(1);
        }
    }

    /**
     *
     * @param inFile file given by the user from which input will be read
     */
    public static void inputFileCheck(File inFile) {
        // general check of the file provided
        if (!inFile.exists() || !inFile.isFile() || !inFile.canRead()) {
            System.err.printf("The file %s does not exist.%n", inFile.getAbsoluteFile());
            System.exit(1);
        }
    }

}
