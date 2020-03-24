package seedu.duke.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;

import java.io.BufferedReader;
import java.lang.reflect.Type;

public class LoadData {
    private static final String SHOPPING_LIST_FILENAME = "shoppinglist.json";
    private static final String BUDGET_FILENAME = "budget.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Return a shopping list object based on stored data.
     *
     * @return Shopping list.
     */
    public ShoppingList readShoppingList() {
        BufferedReader bufferedReader = FileUtil.readFile(SHOPPING_LIST_FILENAME);
        if (bufferedReader == null) {
            return new ShoppingList();
        }
        Type shoppingListType = new TypeToken<ShoppingList>(){}.getType();
        ShoppingList shoppingList = gson.fromJson(bufferedReader, shoppingListType);
        if (shoppingList == null) {
            return new ShoppingList();
        }
        return shoppingList;
    }

    /**
     * Return a budget object based on stored data.
     *
     * @return Budget.
     */
    public Budget readBudget() {
        BufferedReader bufferedReader = FileUtil.readFile(BUDGET_FILENAME);
        if (bufferedReader == null) {
            return new Budget();
        }
        Type budgetType = new TypeToken<Budget>(){}.getType();
        Budget budget = gson.fromJson(bufferedReader, budgetType);
        if (budget == null) {
            return new Budget();
        }
        return budget;
    }
}
