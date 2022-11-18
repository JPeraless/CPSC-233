package Project_233.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * Class in charge of outputting information to a file.
 *
 * Name:    Jose Perales & Matias Campuzano
 * Date:    21/03/2022
 * Tut:     T07
 */
public class Logger {

    /**
     * PrintWriter recording the data.
     */
    private PrintWriter printWriter;

    /**
     * PrintWriter constructor
     *
     * @param outFile File in which the output will be recorded.
     */
    public Logger(File outFile) {
        try {
            printWriter = new PrintWriter(outFile);
        }
        catch (FileNotFoundException e){
            System.err.printf("The file %s could not be opened.", outFile.getAbsoluteFile());
            System.exit(1);
        }
    }

    /**
     * Stores data in the output file and flushes afterwards.
     *
     * @param obj Object added to the output file.
     */
    public void entry(Object obj) {
        printWriter.println(obj);  // entry
        printWriter.flush();  // in case something crashes
    }

}
