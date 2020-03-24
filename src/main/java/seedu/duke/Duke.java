package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;
import seedu.duke.ui.Ui;
import seedu.duke.utils.Parser;
import seedu.duke.utils.Storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Duke {

    private static Budget myBudget = new Budget();
    private static ShoppingList items = new ShoppingList();
    private Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String DEFAULT_FILEPATH = "Logger.log";
    private static final String LOG_FILE_ERROR = "Logging to file unsuccessful";

    /**
     * Main entry-point for the java.duke.Duke application.
     *
     */
    public static void main(String[] args) {
        Duke.setUpLogger();
        new Duke().run();
    }

    //@@author kokjoon97
    /**
     * Sets up the logger to log to the console and a file.
     */
    private static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler console = new ConsoleHandler();
        console.setLevel(Level.SEVERE);
        LOGGER.addHandler(console);
        File logFile = new File(DEFAULT_FILEPATH);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            FileHandler logHandler = new FileHandler(DEFAULT_FILEPATH);
            logHandler.setLevel(Level.INFO);
            LOGGER.addHandler(logHandler);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,LOG_FILE_ERROR);
        }
    }
    //@@author

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Prints the welcome message.
     */
    private void start() {
        items = storage.loadShoppingList();
        myBudget = storage.loadBudget();
        LOGGER.log(Level.INFO,"Application starting.");
        ui.greet();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        storage.saveAll(items, myBudget);
        ui.bidFarewell();
        LOGGER.log(Level.INFO,"Application shutting down");
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            ui.printline("\nEnter command:");

            String userInput = ui.readCommand();
            assert !userInput.isEmpty() : "Input should not be empty";

            command = new Parser().parseCommand(userInput);
            assert command != null : "Command should have been initialised";

            executeCommand(command);
            assert command.feedbackToUser != null : "Result should have been initialised";

            ui.printline(command.feedbackToUser);
        } while (!command.isExit);
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private void executeCommand(Command command) {
        try {
            command.setData(items,myBudget);
            command.execute();
        } catch (Exception e) {
            ui.printline(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
