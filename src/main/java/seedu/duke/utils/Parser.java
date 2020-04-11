package seedu.duke.utils;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DisplayCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.ResetBudgetCommand;
import seedu.duke.commands.SetBudgetCommand;
import seedu.duke.commands.UnmarkCommand;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    private static final String regex = "^(?<index>[\\d ]*[^a-zA-Z\\/])"
            + "|i\\/(?<description>(?=)(?:(?!p\\/)(?!q\\/).)+)"
            + "|p\\/(?<price>(?=)(?:(?!i\\/)(?!q\\/).)+)+"
            + "|q\\/(?<quantity>(?=)(?:(?!p\\/)(?!i\\/).)+)|$;";

    private static final Pattern EDIT_ITEM_ARGS_FORMAT = Pattern.compile(regex, Pattern.MULTILINE);
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
        //@@author jiajuinphoon
        case AddCommand.COMMAND_WORD:
            createAddCommand(arguments);
            break;
        //author

        //@@author Shannonwje
        case MarkCommand.COMMAND_WORD:
            createMarkCommand(arguments);
            break;

        case UnmarkCommand.COMMAND_WORD:
            createUnmarkCommand(arguments);
            break;
        //@@author

        //@@author trishaangelica
        case EditCommand.COMMAND_WORD:
            createEditCommand(arguments);
            break;
        //@@author

        //@@author kokjoon97
        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand(arguments);
            break;

        case FindCommand.COMMAND_WORD:
            createFindCommand(arguments);
            break;

        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand(arguments);
            break;
        //@@author

        //@@author JLoh579
        case DisplayCommand.COMMAND_WORD:
            createDisplayCommand(arguments);
            break;

        case ClearCommand.COMMAND_WORD:
            createClearCommand(arguments);
            break;
        //@@author

        //@@author Shannonwje
        case ResetBudgetCommand.COMMAND_WORD:
            createResetBudgetCommand(arguments);
            break;
        //@@author

        //JLoh579
        case ExitCommand.COMMAND_WORD:
            createExitCommand(arguments);
            break;
        //@@author

        //@@author trishaangelica
        case HelpCommand.COMMAND_WORD:
            createHelpCommand(arguments);
            break;
            // @@author

        default:
            newCommand = new HelpCommand();
        }
        assert newCommand != null : "newCommand should have been initialised";
        return newCommand;
    }

    /**
     * Splits Command and Args from full input string.
     *
     * @param userInput full input string.
     */
    private String[] splitCommandAndArgs(String userInput) {
        return userInput.trim().split(" ", 2);
    }
    //@@author jiajuinphoon

    private void createAddCommand(String arguments) {
        try {
            final boolean iPresent = arguments.contains("i/");
            final boolean pPresent = arguments.contains("p/");
            final boolean qPresent = arguments.contains("q/");
            boolean delimiterPresent = iPresent | pPresent | qPresent;
            boolean hasSingleDelimiter = singleDelimiterChecker(arguments);
            String[] args = splitArgsForAddCommand(arguments);
            String description;
            String prices;
            String quantity;
            if (delimiterPresent && hasSingleDelimiter) {
                description = args[0];
                prices = args[1];
                quantity = args[2];

                if (description != null && description.length() == 0) {
                    newCommand = new IncorrectCommand(System.lineSeparator()
                            + "Oops! Invalid Command. Check if these are met:"
                            + System.lineSeparator()
                            + " - Price of an item should be in positive numerical form."
                            + System.lineSeparator()
                            + " - Quantity of an item should be in positive numerical form (no decimals)."
                            + System.lineSeparator()
                            + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                            + "p/[PRICE] or q/[QUANTITY] must be present."
                            + System.lineSeparator()
                            + "|| Example: ADD i/apples p/9.90 q/9");
                } else {
                    if (prices == null && quantity != null) {
                        int quantityInInteger = Integer.parseInt(quantity);
                        if (quantityInInteger < 0) {
                            newCommand = new IncorrectCommand(System.lineSeparator()
                                    + "Oops! Invalid Command. Check if these are met:"
                                    + System.lineSeparator()
                                    + " - Price of an item should be in positive numerical form."
                                    + System.lineSeparator()
                                    + " - Quantity of an item should be in positive numerical form (no decimals)."
                                    + System.lineSeparator()
                                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                                    + "p/[PRICE] or q/[QUANTITY] must be present."
                                    + System.lineSeparator()
                                    + "|| Example: ADD i/apples p/9.90 q/9");
                        } else {
                            newCommand = new AddCommand(description, 0.0, quantityInInteger);
                        }
                    } else if (prices != null && quantity == null) {
                        double price = Double.parseDouble(prices);
                        if (price < 0.0) {
                            newCommand = new IncorrectCommand(System.lineSeparator()
                                    + "Oops! Invalid Command. Check if these are met:"
                                    + System.lineSeparator()
                                    + " - Price of an item should be in positive numerical form."
                                    + System.lineSeparator()
                                    + " - Quantity of an item should be in positive numerical form (no decimals)."
                                    + System.lineSeparator()
                                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                                    + "p/[PRICE] or q/[QUANTITY] must be present."
                                    + System.lineSeparator()
                                    + "|| Example: ADD i/apples p/9.90 q/9");
                        } else {
                            newCommand = new AddCommand(description, price,1);
                        }
                    } else if (prices == null && quantity == null) {
                        newCommand = new AddCommand(description,0.0,1);
                    } else {
                        double price = Double.parseDouble(prices);
                        int quantityInInteger = Integer.parseInt(quantity);
                        if (quantityInInteger < 0 || price < 0.0) {
                            newCommand = new IncorrectCommand(System.lineSeparator()
                                    + "Oops! Invalid Command. Check if these are met:"
                                    + System.lineSeparator()
                                    + " - Price of an item should be in positive numerical form."
                                    + System.lineSeparator()
                                    + " - Quantity of an item should be in positive numerical form (no decimals)."
                                    + System.lineSeparator()
                                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                                    + "p/[PRICE] or q/[QUANTITY] must be present."
                                    + System.lineSeparator()
                                    + "|| Example: ADD i/apples p/9.90 q/9");
                        } else {
                            newCommand = new AddCommand(description, price, quantityInInteger);
                        }
                    }
                }
            } else {
                newCommand = new IncorrectCommand(System.lineSeparator()
                        + "Oops! Invalid Command. Check if these are met:"
                        + System.lineSeparator()
                        + " - Price of an item should be in positive numerical form."
                        + System.lineSeparator()
                        + " - Quantity of an item should be in positive numerical form (no decimals)."
                        + System.lineSeparator()
                        + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                        + "p/[PRICE] or q/[QUANTITY] must be present."
                        + System.lineSeparator()
                        + "|| Example: ADD i/apples p/9.90 q/9");
            }
        } catch (NullPointerException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException
                | NumberFormatException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! Invalid Command. Check if these are met:"
                    + System.lineSeparator()
                    + " - Price of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - Quantity of an item should be in positive numerical form (no decimals)."
                    + System.lineSeparator()
                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                    + "p/[PRICE] or q/[QUANTITY] must be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apples p/9.90 q/9");
        }
    }

    private String[] splitArgsForAddCommand(String arguments) throws NullPointerException {

        String[] argsArray = new String[]{};
        String descriptionDelimiter = "i/";
        String priceDelimiter = "p/";
        String quantityDelimiter = "q/";
        String itemPrice;
        String itemDescription;
        String itemQuantity;

        int buffer = 2;
        int indexOfiPrefix;
        int indexOfpPrefix;
        int indexOfqPrefix;
        boolean descriptionPresent = arguments.contains(descriptionDelimiter);
        boolean pricePresent = arguments.contains(priceDelimiter);
        boolean quantityPresent = arguments.contains(quantityDelimiter);

        String args = arguments.trim();
        try {
            if (descriptionPresent && !pricePresent && !quantityPresent) { //eg args: ADD i/apple
                indexOfiPrefix = args.indexOf(descriptionDelimiter);
                itemDescription = args.substring(indexOfiPrefix + buffer).trim();
                argsArray = new String[]{itemDescription, null, null};
            } else if (descriptionPresent && pricePresent && !quantityPresent) {
                indexOfiPrefix = args.indexOf(descriptionDelimiter);
                indexOfpPrefix = args.indexOf(priceDelimiter);
                if (indexOfpPrefix < indexOfiPrefix) { //e.g args: ADD p/4.50 i/apple
                    itemDescription = args.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                } else { //e.g args: ADD i/apple p/4.50
                    itemDescription = args.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, itemPrice, null};
            } else if (descriptionPresent && !pricePresent && quantityPresent) {
                indexOfiPrefix = args.indexOf(descriptionDelimiter);
                indexOfqPrefix = args.indexOf(quantityDelimiter);
                if (indexOfqPrefix < indexOfiPrefix) { //e.g args: ADD q/1 i/apple
                    itemDescription = args.substring(indexOfiPrefix + buffer).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else { //e.g args: ADD i/apple q/1
                    itemDescription = args.substring(indexOfiPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, null, itemQuantity};
            } else if (descriptionPresent && pricePresent && quantityPresent) {
                indexOfiPrefix = args.indexOf(descriptionDelimiter);
                indexOfpPrefix = args.indexOf(priceDelimiter);
                indexOfqPrefix = args.indexOf(quantityDelimiter);
                if (indexOfqPrefix < indexOfpPrefix && indexOfpPrefix < indexOfiPrefix) {
                    //e.g args: ADD q/2 p/4.50 i/apple
                    itemDescription = args.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer, indexOfpPrefix).trim();
                } else if (indexOfpPrefix < indexOfqPrefix && indexOfqPrefix < indexOfiPrefix) {
                    //e.g args: ADD p/2.5 q/4 i/apple
                    itemDescription = args.substring(indexOfiPrefix + buffer).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else if (indexOfpPrefix < indexOfiPrefix && indexOfiPrefix < indexOfqPrefix) {
                    //e.g args: ADD p/2.5 i/apple q/4
                    itemDescription = args.substring(indexOfiPrefix + buffer,indexOfqPrefix).trim();
                    itemPrice = arguments.substring(indexOfpPrefix + buffer, indexOfiPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer).trim();
                } else if (indexOfqPrefix < indexOfiPrefix && indexOfiPrefix < indexOfpPrefix) {
                    //e.g args: ADD q/2 i/apple p/4.50
                    itemDescription = args.substring(indexOfiPrefix + buffer,indexOfpPrefix).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer, indexOfiPrefix).trim();
                } else if (indexOfiPrefix < indexOfqPrefix && indexOfqPrefix < indexOfpPrefix) {
                    //e.g args: ADD i/apple q/4 p/2.5
                    itemDescription = args.substring(indexOfiPrefix + buffer,indexOfqPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer).trim();
                } else { //e.g args: ADD i/apple p/4.50 q/2
                    itemDescription = args.substring(indexOfiPrefix + buffer, indexOfpPrefix).trim();
                    itemPrice = args.substring(indexOfpPrefix + buffer, indexOfqPrefix).trim();
                    itemQuantity = args.substring(indexOfqPrefix + buffer).trim();
                }
                argsArray = new String[]{itemDescription, itemPrice, itemQuantity};
            } else if (!descriptionPresent && pricePresent || quantityPresent) { //ADD p/3.50
                argsArray = new String[]{null, null, null};
            }

            if (argsArray[0] == null && argsArray[1] == null && argsArray[2] == null) {
                throw new NullPointerException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Oops! Invalid Command. Check if these are met:"
                    + System.lineSeparator()
                    + " - Price of an item should be in positive numerical form."
                    + System.lineSeparator()
                    + " - Quantity of an item should be in positive numerical form (no decimals)."
                    + System.lineSeparator()
                    + " - If 'i/', 'p/' or 'q/' is present, i/[DESCRIPTION], "
                    + "p/[PRICE] or q/[QUANTITY] must be present."
                    + System.lineSeparator()
                    + "|| Example: ADD i/apples p/9.90 q/9");
        }
        return argsArray;
    } //@@author

    //@@author Shannonwje
    /**
     * Initialises the MarkCommand.
     */
    private void createMarkCommand(String arguments) {
        try {
            String[] words = arguments.trim().split(" ");
            if (words.length == 1) {
                int index = Integer.parseInt(words[0]) - 1;
                newCommand = new MarkCommand(index);
            } else {
                LOGGER.log(Level.WARNING, "(Mark command) Rejecting user command, too many or no arguments.");
                newCommand = new IncorrectCommand("Please provide a single numerical index number!");
            }

        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING, "(Mark command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand("Please provide a single numerical index number!");
        }
    }

    /**
     * Initialises the Unmark Command.
     */
    private void createUnmarkCommand(String arguments) {
        try {
            String[] words = arguments.trim().split(" ");
            if (words.length == 1) {
                int index = Integer.parseInt(words[0]) - 1;
                newCommand = new UnmarkCommand(index);
            } else {
                LOGGER.log(Level.WARNING,
                        "(Unmark command) Rejecting user command, too many or no arguments.");
                newCommand = new IncorrectCommand("Please provide a single numerical index number!");
            }

        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING,
                    "(Unmark command) Rejecting user command, invalid command format entered.");
            newCommand = new IncorrectCommand("Please provide a single numerical index number!");
        }
    }
    //@@author

    //@@author trishaangelica

    /**
     * Initialises the EditCommand.
     */
    private void createEditCommand(String arguments) {
        try {
            final Matcher matcher = EDIT_ITEM_ARGS_FORMAT.matcher(arguments.trim());

            String[] newValues;
            final boolean iPresent = arguments.contains("i/");
            final boolean pPresent = arguments.contains("p/");
            final boolean qPresent = arguments.contains("q/");

            boolean delimiterPresent = iPresent | pPresent | qPresent;
            boolean hasSingleDelimiter = singleDelimiterChecker(arguments);
            newValues = splitArgsForEditCommand(matcher);
            boolean validValues = validValuesChecker(newValues, iPresent, pPresent, qPresent);

            if (delimiterPresent && hasSingleDelimiter && validValues) {

                String newItemDescription = null;
                String newItemPrice = null;
                String newItemQuantity = null;

                if (newValues[1] != null) {
                    newItemDescription = newValues[1].trim();
                }
                if (newValues[2] != null) {
                    newItemPrice = newValues[2].trim();
                }
                if (newValues[3] != null) {
                    newItemQuantity = newValues[3].trim();
                }

                int itemIndex = Integer.parseInt(newValues[0].trim());

                newCommand = new EditCommand(itemIndex, newItemDescription, newItemPrice, newItemQuantity);
            } else {

                LOGGER.log(Level.WARNING, "(Edit command) Rejecting user command, invalid command format entered.");
                newCommand = new IncorrectCommand(EditCommand.MESSAGE_FAILURE_INCORRECT_FORMAT);
            }
        } catch (NullPointerException | NumberFormatException e) {
            newCommand = new IncorrectCommand(EditCommand.MESSAGE_FAILURE_INCORRECT_FORMAT);
        }

    }

    /**
     * Checks the input string for duplicate delimiter.
     *
     * @param stringToCheck full input string.
     * @return true if there is no duplicate delimiter found.
     */
    private boolean singleDelimiterChecker(String stringToCheck) {
        boolean result;
        String[] splitByDescription = stringToCheck.split("i/");
        String[] splitByPrice = stringToCheck.split("p/");
        String[] splitByQuantity = stringToCheck.split("q/");

        int countI = splitByDescription.length - 1;
        int countP = splitByPrice.length - 1;
        int countQ = splitByQuantity.length - 1;
        result = countI < 2 && countP < 2 && countQ < 2;
        return result;
    }

    /**
     * Split arguments for Edit Command.
     *
     * @param matcher matcher object to match the entire input sequence against the regex pattern.
     * @return an array of the found matches.
     **/
    private String[] splitArgsForEditCommand(Matcher matcher) {

        String indexOfItem = null;
        String itemDescription = null;
        String itemPrice = null;
        String itemQuantity = null;


        while (matcher.find()) {

            if (matcher.group("index") != null) {
                indexOfItem = matcher.group("index").trim();
            }

            if (matcher.group("description") != null) {
                itemDescription = matcher.group("description").trim();
            }

            if (matcher.group("price") != null) {
                itemPrice = matcher.group("price").trim();
            }

            if (matcher.group("quantity") != null) {
                if (matcher.group(0).contains("-")) {
                    throw new NumberFormatException();
                } else {
                    itemQuantity = matcher.group("quantity").trim();
                }
            }
        }

        return new String[]{indexOfItem, itemDescription, itemPrice, itemQuantity};
    }

    /**
     * Validates the previously found matches.
     * E.g if "i/" was present in input sequence, it's found match should be a value and not null.
     *
     * @param arrToCheck         Array that contains the found matches.
     * @param descriptionPresent indicator for presence of "i/" in input string.
     * @param pricePresent       indicator for presence of "p/" in input string.
     * @param quantityPresent    indicator for presence of "q/" in input string.
     * @return true if all values are valid.
     **/
    private boolean validValuesChecker(String[] arrToCheck, Boolean descriptionPresent, Boolean pricePresent,
                                       Boolean quantityPresent) {
        boolean validIndex = false;
        boolean validDescription = false;
        boolean validPrice = false;
        boolean validQuantity = false;

        if (arrToCheck[0] != null) {
            validIndex = true;
        }

        if (descriptionPresent) {
            if (arrToCheck[1] != null) {
                validDescription = true;
            }
        }
        if (!descriptionPresent) {
            if (arrToCheck[1] == null) {
                validDescription = true;
            }
        }

        if (pricePresent) {
            if (arrToCheck[2] != null) {

                if (Double.parseDouble(arrToCheck[2]) > 0) {
                    validPrice = true;
                }
            }
        }
        if (!pricePresent) {
            if (arrToCheck[2] == null) {
                validPrice = true;
            }
        }

        if (quantityPresent) {
            if (arrToCheck[3] != null) {
                if (Integer.parseInt(arrToCheck[3]) > 0) {
                    validQuantity = true;
                }
            }
        }
        if (!quantityPresent) {
            if (arrToCheck[3] == null) {
                validQuantity = true;
            }
        }

        return validIndex && validDescription && validPrice && validQuantity;
    }
    //@@author


    //@@author kokjoon97
    /**
     * Initialises the DeleteCommand.
     */
    private void createDeleteCommand(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim());
            newCommand = new DeleteCommand(index);
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.log(Level.WARNING, "(Delete command) Rejecting user command, "
                    + "user did not enter a number for index.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an index"
                    + System.lineSeparator()
                    + "Example: DEL 3");
        }

    }

    //@@author JLoh579
    /**
     * Initialises the ListCommand.
     */
    private void createDisplayCommand(String arguments) {
        if (arguments != null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To display your shopping list, just input \"DISPLAY\".");
        } else {
            newCommand = new DisplayCommand();
        }
    }

    /**
     * Initialises the ClearCommand.
     */
    private void createClearCommand(String arguments) {
        if (arguments != null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To clear your shopping list, just input \"CLEAR\".");
        } else {
            newCommand = new ClearCommand();
        }
    }

    //@@author kokjoon97
    /**
     * Initialises the SetBudgetCommand.
     */
    private void createSetBudgetCommand(String arguments) {
        try {
            if (!arguments.contains("b/")) {
                LOGGER.log(Level.WARNING, "(Set budget) Rejecting user command, no \"b/\" substring.");
                newCommand = new IncorrectCommand(System.lineSeparator()
                        + "Please enter the amount after the \"b/\" divider"
                        + System.lineSeparator()
                        + "Example: SET b/300");
            } else {
                assert arguments.indexOf("b/") != -1 : "Substring \"b/\" should be present";
                double amount = Double.parseDouble(arguments.trim().substring(2));
                newCommand = new SetBudgetCommand(amount);
            }

        } catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "(Set budget) Rejecting user command, no number was specified for budget.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter an amount for your budget"
                    + System.lineSeparator()
                    + "Example: SET b/300");
        }
    }
    //@@author

    //@@author Shannonwje
    /**
     * Initialises the ResetBudgetCommand.
     */
    private void createResetBudgetCommand(String arguments) {
        if (arguments != null) {
            LOGGER.log(Level.WARNING,
                    "(Reset Budget command) Rejecting user command, should not have arguments.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To reset your budget, just input \"RES\".");
        } else {
            newCommand = new ResetBudgetCommand();
        }
    }
    //@@author

    /**
     * Initialises the HelpCommand.
     */
    private void createHelpCommand(String arguments) {
        if (arguments != null) {
            LOGGER.log(Level.WARNING,
                    "(Help command) Rejecting user command, should not have arguments.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "For the help command, just input \"HELP\".");
        } else {
            newCommand = new HelpCommand();
        }
    }

    /**
     * Initialises the ExitCommand.
     */
    private void createExitCommand(String arguments) {
        if (arguments != null) {
            LOGGER.log(Level.WARNING,
                    "(Exit command) Rejecting user command, should not have arguments.");
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Invalid command."
                    + System.lineSeparator()
                    + "To exit SHOCO, just input \"BYE\".");
        } else {
            newCommand = new ExitCommand();
        }
    }

    //@@author kokjoon97
    private void createFindCommand(String arguments) {
        if (arguments == null) {
            newCommand = new IncorrectCommand(System.lineSeparator()
                    + "Please enter a keyword after FIND"
                    + System.lineSeparator()
                    + "Example: FIND apples");
            LOGGER.log(Level.INFO, "(Find command) User did not supply keyword for FIND");
        } else {
            assert arguments != null;
            LOGGER.log(Level.INFO, "(Find command) User supplied keyword: " + arguments);
            newCommand = new FindCommand(arguments.trim());
        }
    }
    //@@author
}
