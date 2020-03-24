package seedu.duke.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

public class WriteData {
    private static final String SHOPPING_LIST_FILENAME = "shoppinglist.json";
    private static final String BUDGET_FILENAME = "budget.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeShoppingList(ShoppingList shoppingList) {
        String jsonStr = gson.toJson(shoppingList);
        FileUtil.writeFile(jsonStr, SHOPPING_LIST_FILENAME);
    }

    public void writeBudget(Budget budget) {
        String jsonStr = gson.toJson(budget);
        FileUtil.writeFile(jsonStr, BUDGET_FILENAME);
    }
}
