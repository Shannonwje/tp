//@@author Shannonwje

package seedu.duke.commands;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    ShoppingList items = new ShoppingList();
    Command command;

    @BeforeEach
    void setUp() {
        items.add(new Item("apple", 2.0));
        items.add(new Item("donut", 3.0));
        items.add(new Item("banana", 4.5));
    }

    @AfterEach
    void tearDown() {
        items.clearList();
    }

    @Test
    void testMark_unmarkedItem_Success() {
        command = new MarkCommand(1);
        command.setData(items, null);
        command.execute();
        String expectedFeedback = System.lineSeparator()
                + "Yes! I've marked this item as bought:"
                + System.lineSeparator() + items.getList().get(1).toString()
                + System.lineSeparator();
        assertEquals(expectedFeedback, command.feedbackToUser);
    }

    @Test
    void testMark_outOfLimitIndex_Success() {
        command = new MarkCommand(10);
        command.setData(items, null);
        command.execute();
        String expectedFeedback1 = MarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback1, command.feedbackToUser);
    }

    @Test
    void testMark_negativeIndex_Success() {
        command = new MarkCommand(-5);
        command.setData(items, null);
        command.execute();
        String expectedFeedback2 = MarkCommand.FAIL_MESSAGE;
        assertEquals(expectedFeedback2, command.feedbackToUser);
    }
}
