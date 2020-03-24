//@@author Shannonwje

package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnmarkCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "UNMARK";
    public static final String SUCCESS_MESSAGE = System.lineSeparator()
                                    + "Yes! I've unmarked this item as bought:"
                                    + System.lineSeparator() + "%s"
                                    + System.lineSeparator();
    public static final String FAIL_MESSAGE = System.lineSeparator()
                                    + "Oh No! This item does not exist in the list"
                                    + System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks a bought item."
                                    + System.lineSeparator() + "|| Parameters: UNMARK [INDEX]"
                                    + System.lineSeparator() + "|| UNMARK 1" + System.lineSeparator();

    private int indexOfItem;

    public UnmarkCommand(int index) {
        this.indexOfItem = index;
        LOGGER.log(Level.INFO, "(Unmark command) User entered index: " + indexOfItem);
    }

    @Override
    public void execute() {
        try {
            Item unmarkItem = items.unmarkAsBought(indexOfItem);
            assert unmarkItem.getStatusIcon().equals("0") : "Item should be marked as unbought";
            LOGGER.log(Level.INFO, "(Unmark command)  Item has been marked: " + unmarkItem.toString());
            feedbackToUser = String.format(SUCCESS_MESSAGE, unmarkItem);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Unmark command)  index is out of bounds");
            feedbackToUser = FAIL_MESSAGE;
        }
    }
}
