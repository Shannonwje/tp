//@@author kokjoon97

package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.ui.CommandLineTable;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Displays the shopping list, cost of items, and budget details.
 */
public class FindCommand extends Command {

    private static String keyword;

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final String COMMAND_WORD = "FIND";

    public static final String FIND_MESSAGE = System.lineSeparator() + "Here are the matching items:\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all items that contain the keyword."
            + System.lineSeparator() + "|| Parameters: FIND [KEYWORD]"
            + System.lineSeparator() + "|| Example: FIND apples" + System.lineSeparator();

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute() {
        ArrayList<Item> filteredItems = new ArrayList<>();
        int size = items.getSize();
        int matches = 0;
        int[] indexArray = new int[size];
        for (int i = 0; i < size; i++) {
            Item unfilteredItem = items.getItem(i);
            assert unfilteredItem != null;
            if (unfilteredItem.getDescription().contains(keyword)) {
                filteredItems.add(unfilteredItem);
                indexArray[matches] = i + 1;
                matches += 1;
            }
        }
        int filteredListSize = filteredItems.size();
        LOGGER.log(Level.INFO,"(Find command) Matches found: " + filteredListSize);
        if (filteredListSize == 0) {
            feedbackToUser = "Sorry, no results could be found!";
        } else {
            assert filteredListSize != 0;
            feedbackToUser = "";
            System.out.println(FIND_MESSAGE);
            CommandLineTable st = new CommandLineTable();
            st.setShowVerticalLines(true);
            st.setHeaders("Item", "Price","Qty");
            for (int i = 0; i < filteredItems.size(); i++) {
                String itemLine = indexArray[i] + ". [" + filteredItems.get(i).getStatusIcon()
                        + "] " + filteredItems.get(i).getDescription();
                st.addRow(itemLine, String.format("$%.2f", filteredItems.get(i).getPrice()),
                        String.format("%d",filteredItems.get(i).getQuantity()));
            }
            st.print();
            LOGGER.log(Level.INFO,"(Find command) Results displayed successfully");
        }

    }
}