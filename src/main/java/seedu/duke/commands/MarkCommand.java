//@@author Shannonwje

package seedu.duke.commands;

import seedu.duke.data.Item;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "MARK";
    public static final String SUCCESS_MESSAGE = System.lineSeparator()
                                    + "Yes! I've marked this item as bought:"
                                    + System.lineSeparator() + "%s"
                                    + System.lineSeparator();
    public static final String FAIL_MESSAGE = System.lineSeparator()
                                    + "Oh No! This item does not exist in the list"
                                    + System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an item as bought."
                                    + System.lineSeparator() + "|| Parameters: MARK [INDEX]"
                                    + System.lineSeparator() + "|| Example: MARK 1"
                                    + System.lineSeparator();


    private int indexOfItem;

    public MarkCommand(int index) {
        this.indexOfItem = index;
        LOGGER.log(Level.INFO, "(Mark command) User entered index: " + indexOfItem);
    }

    @Override
    public void execute() {
        try {
            Item markItem = items.markAsBought(indexOfItem);
            assert markItem.getStatusIcon().equals("B") : "Item should be marked as bought";
            LOGGER.log(Level.INFO, "(Mark command)  Item has been marked: " + markItem.toString());
            feedbackToUser = String.format(SUCCESS_MESSAGE, markItem);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Mark command)  index is out of bounds");
            feedbackToUser = FAIL_MESSAGE;
        }
    }
}
