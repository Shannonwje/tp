package seedu.duke.data;

import seedu.duke.ui.CommandLineTable;

import java.util.ArrayList;

public class ShoppingList {

    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getList() {
        return items;
    }

    //@@author kokjoon97

    /**
     * Returns the size of the items ArrayList.
     *
     * @return Size of the items ArrayList.
     */
    public int getSize() {
        return items.size();
    }
    //@@author

    //@@author JLoh579

    /**
     * Prints a table representing the shopping list.
     */
    public void showTableOfItems() {
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("Item", "Price", "Qty");//optional - if not used then there will be no header and horizontal lines
        int bulletNum = 1;
        String itemLine;
        for (Item item : items) {

            itemLine = bulletNum + ". [" + item.getStatusIcon() + "] " + item.getDescription();
            st.addRow(itemLine, String.format("$%.2f", item.getPrice()), String.valueOf(item.getQuantity()));
            bulletNum++;
        }
        st.print();
    }
    //@@author


    /**
     * Calculates and returns the total cost of the items in the shopping list.
     *
     * @return Total cost of items in shopping list.
     */
    public double getTotalCost() {
        double totalCost = 0.0;
        for (Item item : items) {
            totalCost += item.getPrice();
        }
        return totalCost;

    }

    /**
     * Clear all items from the list.
     */
    public void clearList() {
        items.clear();
    }

    /**
     * Marks item in index as bought.
     *
     * @param index index of item to mark
     * @return item that is marked
     */
    public Item markAsBought(int index) {
        if (index >= items.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Item itemBought = items.get(index);
        itemBought.markAsBought();
        return itemBought;
    }

    /**
     * Returns the item at the specified index.
     *
     * @param index Index of the item requested.
     * @return Item at the specified index.
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Unmarks item in index as bought.
     *
     * @param index index of item to unmark
     * @return item that is unmarked
     */
    public Item unmarkAsBought(int index) {
        if (index >= items.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Item itemNotBought = items.get(index);
        itemNotBought.unmarkAsBought();
        return itemNotBought;
    }

    //@@author kokjoon97

    /**
     * Removes an item at the specified index.
     *
     * @param index Index of the item to be removed.
     */
    public void deleteItem(int index) {
        Item unwantedItem = items.get(index);
        items.remove(unwantedItem);
    }
    //@@author

    /**
     * Adds item to list.
     *
     * @param item Item to add.
     * @throws NullPointerException If there is no description.
     */
    public void add(Item item) throws NullPointerException, NumberFormatException {
        items.add(item);
        if (item.getDescription() == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Returns true if an equivalent item exists in the address book.
     */
    public boolean isSameItemDescription(String descriptionToCheck) {
        boolean result = false;
        for (Item i : items) {
            if (i.getDescription().equals(descriptionToCheck)) {
                result = true;
            }

        }
        return result;
    }
}

