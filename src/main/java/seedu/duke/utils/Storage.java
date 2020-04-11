//@@author JLoh579

package seedu.duke.utils;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

/**
 * Main class which handles the storage and retrieval of data from disk.
 */
public class Storage {
    private WriteData writeData = new WriteData();
    private LoadData loadData = new LoadData();

    /**
     * Saves the shopping list and budget information onto disk.
     *
     * @param shoppingList The object containing the shopping list.
     * @param budget The object containing the budget.
     */
    public void saveAll(ShoppingList shoppingList, Budget budget) {
        writeData.writeShoppingList(shoppingList);
        writeData.writeBudget(budget);
    }

    /**
     * Loads the shopping list into memory.
     *
     * @return A shopping list object.
     */
    public ShoppingList loadShoppingList() {
        return loadData.readShoppingList();
    }

    /**
     * Loads the budget into memory.
     *
     * @return A budget object.
     */
    public Budget loadBudget() {
        return loadData.readBudget();
    }
}
//@@author