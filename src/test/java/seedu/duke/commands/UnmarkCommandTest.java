//@@author Shannonwje

package seedu.duke.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {
    ShoppingList items = new ShoppingList();
    Command command = new Command();

    @BeforeEach
    void setUp() {
        items.clearList();
        items.add(new Item("apple", 2.0));
        items.add(new Item("donut", 3.0));
        items.add(new Item("banana", 4.5));
    }

    @AfterEach
    void tearDown() {
        items.clearList();
    }


    @Test
    void testUnmark_MarkedItem_Success() {
        command = new MarkCommand(1);
        command.setData(items, null);
        command.execute();

        command = new UnmarkCommand(1);
        command.setData(items, null);
        command.execute();

        String expectedFeedback = System.lineSeparator()
                + "Yes! I've unmarked this item as bought:"
                + System.lineSeparator() + items.getList().get(1).toString()
                + System.lineSeparator();
        assertEquals(expectedFeedback, command.feedbackToUser);
    }

    @Test
    void testUnmark_outOfLimitIndex_Success() {
        command = new UnmarkCommand(10);
        command.setData(items, null);
        command.execute();
        String expectedFeedback1 = UnmarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback1, command.feedbackToUser);

    }

    @Test
    void testUnmark_negativeIndex_Success() {
        command = new UnmarkCommand(-5);
        command.setData(items, null);
        command.execute();
        String expectedFeedback2 = UnmarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback2, command.feedbackToUser);
    }
}
