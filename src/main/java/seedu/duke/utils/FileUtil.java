package seedu.duke.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File utility class.
 */
public class FileUtil {

    /**
     * Write to file.
     *
     * @param jsonStr Data to be stored.
     * @param filename File that stores the data.
     */
    public static void writeFile(String jsonStr, String filename) {
        File file = new File(filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            // Split large strings into smaller strings for memory efficiency
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
     * Read from file.
     *
     * @param filename File to be read from.
     * @return A buffered reader.
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
