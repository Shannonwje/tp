//@@author JLoh579

package seedu.duke.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Performs tasks of a low level of abstraction involved in saving and loading.
 */
public class FileUtil {

    /**
     * Writes the JSON string to a file. File is created if it does not already exist.
     *
     * @param jsonStr String containing the data.
     * @param filename String representing the file that would store the data.
     */
    public static void writeFile(String jsonStr, String filename) {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            // Split into smaller strings for memory efficiency
            for (String lineStr : jsonStr.split("\n")) {
                bufferedWriter.write(lineStr);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads data from a file. Returns null if the file does not exist.
     *
     * @param filename String representing the file to be read from.
     * @return A buffered reader object containing the data.
     */
    public static BufferedReader readFile(String filename) {
        File file = new File(filename);
        BufferedReader bufferedReader = null;
        try {
            if (!file.exists()) {
                return null;
            }
            bufferedReader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bufferedReader;
    }
}
//@@author