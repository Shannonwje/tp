package seedu.duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import seedu.duke.commands.Command;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.ResetBudgetCommand;
import seedu.duke.commands.SetBudgetCommand;
import seedu.duke.commands.UnmarkCommand;


public class Parser {

    private static Command newCommand;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] commandAndArgs = splitCommandAndArgs(userInput);
        String commandWord = commandAndArgs[0];
        String arguments;
        try {
           arguments = commandAndArgs[1];
        } catch (IndexOutOfBoundsException e) {
            arguments = null;
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            createAddCommand(arguments);
            break;

        case MarkCommand.COMMAND_WORD:
            createMarkCommand(arguments);
            break;

        case UnmarkCommand.COMMAND_WORD:
            createUnmarkCommand(arguments);
            break;

        case EditCommand.COMMAND_WORD:
            createEditCommand(arguments);
            break;

        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand(arguments);
            break;

        case ListCommand.COMMAND_WORD:
            createListCommand();
            break;

        case ClearCommand.COMMAND_WORD:
            createClearCommand();
            break;

        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;

        case ResetBudgetCommand.COMMAND_WORD:
            createResetBudgetCommand();
            break;

        case HelpCommand.COMMAND_WORD: // Fallthrough
            createHelpCommand();
            break;

        case ExitCommand.COMMAND_WORD:
            createExitCommand();
            break;

        default:
            createHelpCommand();
        }

        return newCommand;
    }

    private void createClearCommand() {
        newCommand = new ClearCommand();
    }

    private void createListCommand() {
        newCommand = new ListCommand();
    }

    /**
     * Initialises the ResetBudgetCommand.
     */
    public static void createResetBudgetCommand() {
        newCommand = new ResetBudgetCommand();
    }

    private void createAddCommand(String arguments) {
        try {
            String[] args = splitArgsForAddCommand(arguments);
            String description;
            String prices;
            description = args[0];
            prices = args[1];

            if (prices == null) {
                newCommand = new AddCommand(description, 0.0);
            } else {
                double price = Double.parseDouble(prices);
                newCommand = new AddCommand(description, price);
            }
        } catch (NullPointerException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Error! Description of an item cannot be empty."
                    + "\nExample: ADD 1 i/apple p/4.50");
        } catch (ArrayIndexOutOfBoundsException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! For that to be done properly, check if these are met:"
                    + System.lineSeparator()
                    + " - Description of an item cannot be empty."
                    + " - Price of an item has to be in decimal form."
                    + System.lineSeparator()
                    + " - At least 'i/' or 'p/' should be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apple p/2.50");
        }
    }

    private String[] splitCommandAndArgs (String userInput) {
        String[] commandandArgs = userInput.trim().split(" ", 2);
        return commandandArgs;
    }
    private String[] splitArgsForAddCommand(String arguments) throws NullPointerException {
        String[] argsArray = new String[]{};
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String itemPrice;
        String itemDescription;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if (descriptionPresent && !pricePresent) { //eg args: ADD i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
            argsArray = new String[]{itemDescription, null};
        } else if (descriptionPresent && pricePresent) {
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            if (indexOfpPrefix < indexOfiPrefix) { //e.g args: ADD 2 p/4.50 i/apple
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix);
            } else { //e.g args: ADD 2 i/apple p/4.50
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer, indexOfpPrefix);
                itemPrice = arguments.substring(indexOfpPrefix + buffer);
            }
            argsArray = new String[]{itemDescription, itemPrice};
        } else if (!descriptionPresent && pricePresent) { //ADD p/3.50
            argsArray = new String[]{null, null};
        }

        if (argsArray[0] == null && argsArray[1] == null) {
            throw new NullPointerException();
        }
        return argsArray;
    }

    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        int indexOfItem;
        String newItemPrice;
        String newItemDescription;
        try {
            String[] args = splitArgsforEditCommand(arguments);
            indexOfItem = Integer.parseInt(args[0]);
            newItemDescription = args[1];
            newItemPrice = args[2];
            newCommand = new EditCommand(indexOfItem, newItemDescription, newItemPrice);
        } catch (NumberFormatException | NullPointerException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! For that to be done properly, check if these are met:"
                    + System.lineSeparator()
                    + " - Index of item must be a positive number."
                    + System.lineSeparator()
                    + " - Price of an item has to be in decimal form."
                    + System.lineSeparator()
                    + " - At least 'i/' or 'p/' should be present."
                    + System.lineSeparator()
                    + "|| Example: EDIT 2 i/apple p/2.50");
        }
    }

    /**
     * Initialises the Unmark Command.
     */
    public static void createUnmarkCommand(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            newCommand = new IncorrectCommand("Can't find the item to unmark! Try again");
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new UnmarkCommand(index);
    }

    /**
     * Initialises the MarkCommand.
     */
    public static void createMarkCommand(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            newCommand = new IncorrectCommand("Can't find the item to mark! Try again");
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new MarkCommand(index);
    }

    /**
     * Split args for Edit Command.
     */
    private String[] splitArgsforEditCommand(String arguments) throws NullPointerException {
        String[] argsArray;
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String indexOfItem;
        String itemPrice;
        String itemDescription;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);

        if (descriptionPresent && !pricePresent) { //e.g args: EDIT 2 i/apple
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
            argsArray = new String[]{indexOfItem, itemDescription, null};

        } else if (pricePresent && !descriptionPresent) { //e.g args: EDIT 2 p/4.50
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);
            itemPrice = arguments.trim().substring(indexOfpPrefix + buffer);
            indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
            argsArray = new String[]{indexOfItem, null, itemPrice};

        } else if (descriptionPresent && pricePresent) { //e.g args: EDIT 2 i/.. p/..
            indexOfiPrefix = arguments.trim().indexOf(descriptionDelimiter);
            indexOfpPrefix = arguments.trim().indexOf(priceDelimiter);

            if (indexOfpPrefix < indexOfiPrefix) { //e.g args: EDIT 2 p/4.50 i/apple
                indexOfItem = arguments.substring(0, indexOfpPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer);
                itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix);
            } else { //e.g args: EDIT 2 i/apple p/4.50
                indexOfItem = arguments.substring(0, indexOfiPrefix).trim();
                itemDescription = arguments.trim().substring(indexOfiPrefix + buffer, indexOfpPrefix);
                itemPrice = arguments.substring(indexOfpPrefix + buffer);
            }
            argsArray = new String[]{indexOfItem, itemDescription, itemPrice};
        } else {
            argsArray = new String[]{null, null, null};
        }
        if (argsArray[1] == null && argsArray[2] == null) {
            throw new NullPointerException();
        }
        return argsArray;
    }

    /**
     * Initialises the ExitCommand.
     */
    public static void createExitCommand() {
        newCommand = new ExitCommand();
    }

    /**
     * Initialises the SetBudgetCommand.
     */
    public static void createSetBudgetCommand(String arguments) {
        double amount = Double.parseDouble(arguments.substring(2));
        newCommand = new SetBudgetCommand(amount);
    }

    /**
     * Initialises the DeleteCommand.
     */
    public static void createDeleteCommand(String arguments) {
        int index = Integer.parseInt(arguments);
        newCommand = new DeleteCommand(index);
    }

    /**
     * Initialises the HelpCommand.
     */
    public static void createHelpCommand() {
        newCommand = new HelpCommand();
    }

}
