package seedu.duke.commands;

//@@author trishaangelica
/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "HELP";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions."
            + System.lineSeparator() + "|| Parameters: HELP"
            + System.lineSeparator() + "|| Example: HELP" + System.lineSeparator();

    @Override
    public void execute() {
        feedbackToUser = System.lineSeparator()
                + AddCommand.MESSAGE_USAGE + System.lineSeparator()
                + EditCommand.MESSAGE_USAGE + System.lineSeparator()
                + ListCommand.MESSAGE_USAGE + System.lineSeparator()
                + FindCommand.MESSAGE_USAGE + System.lineSeparator()
                + DeleteCommand.MESSAGE_USAGE + System.lineSeparator()
                + ClearCommand.MESSAGE_USAGE + System.lineSeparator()
                + MarkCommand.MESSAGE_USAGE + System.lineSeparator()
                + UnmarkCommand.MESSAGE_USAGE + System.lineSeparator()
                + SetBudgetCommand.MESSAGE_USAGE + System.lineSeparator()
                + ResetBudgetCommand.MESSAGE_USAGE + System.lineSeparator()
                + HelpCommand.MESSAGE_USAGE + System.lineSeparator()
                + ExitCommand.MESSAGE_USAGE + System.lineSeparator();
    }
}
//@@author