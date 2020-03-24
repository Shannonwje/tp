package seedu.duke.utils;

import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;


//@@ JLoh579

public class Storage {
    private WriteData writeData = new WriteData();
    private LoadData loadData = new LoadData();

    public void saveAll(ShoppingList shoppingList, Budget budget) {
        writeData.writeShoppingList(shoppingList);
        writeData.writeBudget(budget);
    }

    public ShoppingList loadShoppingList() {
        return loadData.readShoppingList();
    }

    public Budget loadBudget() {
        return loadData.readBudget();
    }

}
//@@
