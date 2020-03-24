package seedu.duke.commands;

import seedu.duke.data.Item;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author trishaangelica
public class EditCommand extends Command {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "EDIT";
    public static final String EXCEED_WARNING = "\nNOTE: You have exceeded your budget by %.2f";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified item in the list."
            + System.lineSeparator() + "|| Parameters: EDIT [INDEX] i/[DESCRIPTION] p/[PRICE]"
            + System.lineSeparator() + "|| Example 1: EDIT 1 i/apple p/3.00 q/4"
            + System.lineSeparator() + "|| Example 2: EDIT 1 i/apple"
            + System.lineSeparator() + "|| Example 3: EDIT 1 p/3.00"
            + System.lineSeparator() + "|| Example 4: EDIT 1 q/4" + System.lineSeparator();
    public static final String MESSAGE_SUCCESS = System.lineSeparator()
            + "The item has been updated to: %s";
    public static final String MESSAGE_FAILURE_DUPLICATE_DESCRIPTION = System.lineSeparator()
            + "OOPS! I already have an item with that name. Perhaps try another description? :D";
    public static final String MESSAGE_FAILURE_NOT_IN_LIST = System.lineSeparator()
            + "OOPS! I don't have that item in the list yet. Try again? :D";
    public static final String MESSAGE_FAILURE_INCORRECT_FORMAT = System.lineSeparator()
            + "Oops! Invalid Command. Check if these are met:"
            + System.lineSeparator()
            + " - Index of item must be a positive number."
            + System.lineSeparator()
            + " - Price of an item should be in positive numerical form."
            + System.lineSeparator()
            + " - Quantity of an item should be in positive numerical form."
            + System.lineSeparator()
            + " - 'i/', 'p/' and 'q/' must be in alphabetical order."
            + System.lineSeparator()
            + " - If 'i/', 'p/' or 'q/' is present, i/[NEW DESCRIPTION], "
            + "p/[NEW PRICE] or q/[QUANTITY] must be present."
            + System.lineSeparator()
            + "|| Example: EDIT 2 i/lollipop p/2.50 q/5";

    private int indexOfItem;
    private String newDescription;
    private String newPrice;
    private String newQuantity;

    /**
     * Creates an EditCommand and initialises the item index,
     * description and price that needs to be edited.
     *
     * @param index       index of item to change
     * @param description new description of item to change
     * @param price       new price of item to change
     */
    public EditCommand(int index, String description, String price, String quantity) {
        this.indexOfItem = index;
        this.newDescription = description;
        this.newPrice = price;
        this.newQuantity = quantity;
        LOGGER.log(Level.INFO, "(Edit command) User entered index: " + indexOfItem
                + " description: '" + newDescription + "' price: " + newPrice + "' quantity: " + newQuantity);
    }

    @Override
    public void execute() {
        try {
            boolean duplicateDescription = false;
            indexOfItem -= 1;
            Item item = items.getItem(indexOfItem);

            if (newDescription != null) {
                duplicateDescription = items.isSameItemDescription(newDescription);
                if (duplicateDescription) {
                    feedbackToUser = String.format(MESSAGE_FAILURE_DUPLICATE_DESCRIPTION, item.toString());
                } else {
                    item.setDescription(newDescription);
                    assert item.getDescription().equals(newDescription);
                }
            }

            if (newPrice != null && !duplicateDescription) {
                item.setPrice(Double.parseDouble(newPrice));
                assert Double.parseDouble(newPrice) >= 0.0;
                assert item.getPrice() == Double.parseDouble(newPrice);
            }
            if (newQuantity != null && !duplicateDescription) {
                item.setQuantity(Integer.parseInt(newQuantity));
                assert Integer.parseInt(newQuantity) >= 0;
                assert item.getQuantity() == Integer.parseInt(newQuantity);
            }
            if (!duplicateDescription) {
                LOGGER.log(Level.INFO, "(Edit command)  Item has been updated to: " + item.toString());
                feedbackToUser = String.format(MESSAGE_SUCCESS, item.toString());
            }

            //@@author kokjoon97
            assert myBudget != null;
            double remainder = myBudget.getRemainingBudget(items.getTotalCost());
            if (remainder < 0) {
                LOGGER.log(Level.INFO, "(Edit command) User exceeded budget by: " + (-1) * remainder);
                feedbackToUser += String.format(EXCEED_WARNING, (-1) * remainder);
            }
            //@@author

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Edit command)  Item to edit is not found in list");
            feedbackToUser = MESSAGE_FAILURE_NOT_IN_LIST;
        } catch (NumberFormatException e) {
            feedbackToUser = MESSAGE_FAILURE_INCORRECT_FORMAT;
        }
    }
}
//@@author