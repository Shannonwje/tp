//@@author JLoh579

package seedu.duke.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

/**
 * Performs writing tasks which are one level of abstraction below that of the Storage class.
 */
public class WriteData {
    private static final String SHOPPING_LIST_FILENAME = "shoppinglist.json";
    private static final String BUDGET_FILENAME = "budget.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Saves the shopping list in JSON format.
     *
     * @param shoppingList The object containing the shopping list.
     */
    public void writeShoppingList(ShoppingList shoppingList) {
        String jsonStr = gson.toJson(shoppingList);
        FileUtil.writeFile(jsonStr, SHOPPING_LIST_FILENAME);
    }

    /**
     * Saves the budget in JSON format.
     *
     * @param budget The object containing the budget.
     */
    public void writeBudget(Budget budget) {
        String jsonStr = gson.toJson(budget);
        FileUtil.writeFile(jsonStr, BUDGET_FILENAME);
    }
}
//@@author