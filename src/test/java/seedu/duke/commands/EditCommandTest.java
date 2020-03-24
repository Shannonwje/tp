package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Budget;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author trishaangelica
public class EditCommandTest {

    ShoppingList items = new ShoppingList();
    Budget myBudget = new Budget();
    Command command = new Command();


    @Test
    void testEdit_ItemDescriptionOnly_Success() {
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(1, "apples", null, null);
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(0).toString())
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback, command.feedbackToUser);

    }

    @Test
    void testEdit_ItemPriceOnly_Success() {
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(2, null, "3.5", null);
        command.setData(items, myBudget);
        command.execute();
        String expectedFeedback1 =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(1).toString())
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback1, command.feedbackToUser);
    }

    @Test
    void testEdit_ItemQuantityOnly_Success() {
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(2, null, null, "2");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback1 =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(1).toString())
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback1, command.feedbackToUser);
    }

    @Test
    void testEdit_ItemPriceAndDescription_Success() {

        command = new EditCommand(3, "banana", "5.00",null);
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(3, "cherry", "5.00","1");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback2 =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(2).toString())
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback2, command.feedbackToUser);
    }

    @Test
    public void testEdit_ItemToEditNotInList_exceptionThrown() {
        command.setData(items, null);
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(0, "banana", "5.00","2");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback3 = EditCommand.MESSAGE_FAILURE_NOT_IN_LIST;
        assertEquals(expectedFeedback3, command.feedbackToUser);
    }

    @Test
    public void testEdit_ItemPriceIncorrectFormat_exceptionThrown() {
        command.setData(items, null);
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(1, "cherry", "QWERTY","3");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback3 = EditCommand.MESSAGE_FAILURE_INCORRECT_FORMAT;
        assertEquals(expectedFeedback3, command.feedbackToUser);
    }

    @Test
    public void testEdit_ItemQuantityIncorrectFormat_exceptionThrown() {
        command.setData(items, null);
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(1, "lollipop", "3","QWERTY");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback3 = EditCommand.MESSAGE_FAILURE_INCORRECT_FORMAT;
        assertEquals(expectedFeedback3, command.feedbackToUser);
    }

    @Test
    void testEdit_ItemDuplicateDescription_FailureMessage() {

        command = new EditCommand(3, "banana", "5.00",null);
        items.clearList();
        items.add(new Item("apple", 2.0,1));
        items.add(new Item("donut", 3.0,1));
        items.add(new Item("banana", 4.50,1));
        command = new EditCommand(3, "banana", "5.00","1");
        command.setData(items,myBudget);
        command.execute();
        String expectedFeedback2 =  String.format(EditCommand.MESSAGE_FAILURE_DUPLICATE_DESCRIPTION,
                items.getList().get(2).toString())
                + String.format("\nNOTE: You have exceeded your budget by %.2f",items.getTotalCost());
        assertEquals(expectedFeedback2, command.feedbackToUser);
    }

}
//@@author