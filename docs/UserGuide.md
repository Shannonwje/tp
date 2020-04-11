# SHOCO v2.1 - User Guide
<!-- @@author Shannonwje -->
By: `Team SHOCOTech`

Since: `Feb 2020`

Creators: `Tan Kok Joon`, `Labi Trisha Angelica Vergara`, `Loh Ching Wei, Joshua`, `Phoon Jia Juin`, `Wong Jin En, Shannon`

&nbsp;

<!-- @@author -->

<!-- @@author trishaangelica -->
### Table of Contents
* **[Introduction](#introduction)**
* **[Quick Start](#quick-start)**
* **[Features](#features)**
  * [Viewing help: `HELP`](#viewing-help-help)
  * [Displaying list and budget details: `DISPLAY`](#displaying-list-and-budget-details-display)
  * [Setting a budget: `SET`](#setting-a-budget-set)
  * [Adding an item: `ADD`](#adding-an-item-add)
  * [Editing an item: `EDIT`](#editing-an-item-edit)
  * [Marking an item as bought: `MARK`](#marking-an-item-as-bought-mark)
  * [Un-marking a marked item: `UNMARK`](#un-marking-a-marked-item-unmark)
  * [Finding an item: `FIND`](#finding-an-item-find)
  * [Deleting an item: `DEL`](#deleting-an-item-del)
  * [Clearing the list: `CLEAR`](#clearing-the-list-clear)
  * [Resetting a budget: `RES`](#resetting-a-budget-res)
  * [Exiting the Program: `BYE`](#exiting-the-program-bye)
* **[Additional information](#additional-information)**
* **[FAQ](#faq)**
* **[Command Summary](#command-summary)**

&nbsp;

***

<!-- @@author -->

<!-- @@author kokjoon97 -->
## Introduction

Have you ever encountered the problem of having to make multiple trips to the supermarket
because you forgot to get something important? Have you ever gone to the supermarket just to realise
you do not have enough cash on you?

If these problems sound familiar to you, fret not! With SHOCO, such troubles are now a thing of the
past.

SHOCO is a command-line interface (CLI) application that allows you to 
manage and plan your shopping list and budget. With better organisation and also a
budget tracker, we are here to enhance your grocery-shopping experience and make the woes of
grocery shopping disappear.

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

## Quick Start
1. Ensure that you have Java 11 or above installed. Otherwise download it from
[here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Download the latest version of `SHOCO` from [here](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases), named `CS2113T-T13-1.Shoco.jar` under version 2.1.
3. Copy the JAR file into an empty folder
4. Open the command prompt in the empty folder and type in the following command:
   `java -jar CS2113T-T13-1.Shoco.jar`
5. You are now all set to plan your shopping list!

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

## Features 

#### Command Format
* Please note the words in UPPER_CASE are the parameters that you can supply 
  * e.g. in `ADD i/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `ADD i/apple`.

* Items in square brackets are *optional* and you can omit them. 
  * e.g `EDIT 1 [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]` can be used as `EDIT 1 i/apple p/4.00` or as `EDIT 1 i/apple`.
<!-- @@author kokjoon97 -->
* All command words are case-sensitive and you should always use uppercase.
  * e.g `ADD` in `ADD i/DESCRIPTION` consists of only uppercase letters.
  
* You can supply parameters for the `ADD` and `EDIT` command, namely `DESCRIPTION`, `QUANTITY` and `PRICE`, in any order.
  * e.g `ADD i/apple q/5` **AND** `ADD q/5 i/apple` should both produce the same result.
  
* The keyword for the `FIND` command is case-insensitive.
  * e.g If you have an item named "apple" in the list, both `FIND APPLE` **AND** `FIND apple` will display this
  entry to the user.

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author trishaangelica -->
### Viewing help: `HELP`
Shows the available commands, their purpose and how they are to be used.

Format: `HELP`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author JLoh579 -->
### Displaying list and budget details: `DISPLAY`
Shows the shopping list, budget, cost of the items and the remaining budget.

Format: `DISPLAY` 

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author kokjoon97 -->
### Setting a budget: `SET`
Sets a budget for the user.

Format: `SET b/AMOUNT`

* The `AMOUNT` can be any decimal number that is between 0 to 5000.
* The `b/` phrase should be present in the command.  

Example of usage: 

`SET b/3.00`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author jiajuinphoon -->
### Adding an item: `ADD`
Adds an item to the shopping list.

Format: `ADD i/DESCRIPTION [p/PRICE] [q/QUANTITY]`

* The `DESCRIPTION` must exist.
* The `[QUANTITY]` must be a **positive whole number**. *e.g 1, 2, 3 ..*
* The `[PRICE]` must be in **positive numerical** form (decimal form accepted).
* `[PRICE]` and `[QUANTITY]` are optional values, user can choose to provide the 
  respective values or omit them. The system will set the price and quantity to 
  the default values `0.0` and `1` if omitted.
> :information_source: Note: You can rearrange the delimiters i/, p/ , q/ in <em>any</em> order. e.g `i/.. p/.. q/..` or `q/.. i/.. p/..`.

Examples of usage: 

1. `ADD i/potato p/5.00 q/3` **OR** `ADD p/5.00 q/3 i/potato`
    * Add the description, price and quantity of this item in the shopping list
    
2. `ADD i/potato chips p/5.00`  **OR**  `ADD i/potato chips q/2` **OR** `ADD p/5.00 i/potato chips` 
   **OR** `ADD q/5 i/potato chips`
    * Add the description and price /  description and quantity /  of the item in the shopping list
    
3. `ADD i/potato chips` 
    * Add only description of the item in the shopping list

&nbsp;

<b><a href="#shoco-v21---user-guide"> &#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author trishaangelica -->
### Editing an item: `EDIT`
Edits the specified item in the shopping list.

Format: `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`

* Edits the item at the specified `INDEX`. The `INDEX` refers to the index number 
shown in the displayed shopping list. 
* You can view an item's `INDEX` number by using the `DISPLAY` command. More info [here](#displaying-list-and-budget-details-display).
* The `INDEX` and `[QUANTITY]` must be a **positive whole number**. *e.g 1, 2, 3 ..*
* The `INDEX` should not be out of bounds of the shopping list.
  * Out of bounds indices include negative indices & indices that are greater than the size of the shopping list.
* Indices that are not numbers or are out of bounds will produce an error message indicating the error of the index.
* The `[PRICE]` must be in **positive numerical** form (decimal form accepted).
* **At least one** of the three parameters (description/price/quantity) must be present in the command.
> :information_source: Note: You can rearrange the delimiters i/, p/ , q/ in <em>any</em> order. e.g `i/.. p/.. q/..` or `q/.. i/.. p/..`.

Examples of usage: 

1. `EDIT 3 i/potato p/5.00 q/3`
    * Edits the description, price and quantity of the 3rd item in the shopping list
    
2. `EDIT 3 i/potato chips p/5.00`  **OR**  `EDIT 3 i/potato chips q/2`  **OR**  `EDIT 3 p/5.00 q/2`
    * Edits the description and price /  description and quantity /  price and quantity of the 
    3rd item in the shopping list
    
3. `EDIT 3 i/potato chips` **OR** `EDIT 3 p/5.00` **OR** `EDIT 3 q/2`
    * Edits only description / only price / only quantity of the 3rd item in the shopping list
    
&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author Shannonwje -->
### Marking an item as bought: `MARK`
Marks an item from the list at the specified index as bought.
When first added initially, the item will have the status `[X]` 
to indicate that it is un-marked. After marking the item as bought, 
the status of item becomes `[B]`.

Format: `MARK INDEX`

* The `INDEX` should be a **positive whole number**.
* The `INDEX` should not be out of bounds of the shopping list.
  * Out of bounds indices include negative indices & indices that are greater than the size of the shopping list.
* Indices that are not numbers or are out of bounds will produce an error message indicating the error of the index.
* Marking an item whose status was previously `[B]` will be successful, assuring the success of the mark command executed.

Example of the usage: 

1. `MARK 5`
   * This marks the 5th item in your list as bought.
   * The status of the 5th item is now `[B]`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

### Un-marking a marked item: `UNMARK`
Un-marks a marked-as-bought item from the list at the specified index.
After being marked as bought, the item will have the status `[B]`
to indicate that it is marked as bought. After un-marking the marked-as-bought
item, the status of the item becomes `[X]`.

Format: `UNMARK INDEX`

* The `INDEX` should be a **positive whole number**.
* The `INDEX` should not be out of bounds of the shopping list.
  * Out of bounds indices include negative indices & indices that are greater than the size of the shopping list.
* Indices that are not numbers or are out of bounds will produce an error message indicating the error of the index.
* Un-marking an item whose status was previously `[X]` will be successful, assuring the success of the un-mark command executed.

Example of the usage:

1. `UNMARK 3`
   * This marks the 3rd item in your list as not bought yet.
   * The status of the 3rd item is now `[X]`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author kokjoon97 -->
### Finding an item: `FIND`
Filters the shopping list according to a keyword specified by the user.

Format: `FIND KEYWORD`

* The `KEYWORD` can be any word or phrase.
* The `KEYWORD` field should not be left empty.  

Example of usage: 

`FIND apple`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

### Deleting an item: `DEL`
Removes an item from the list at the specified index.

Format: `DEL INDEX`

* The `INDEX` should be a **positive whole number**.
* The `INDEX` should not be out of bounds of the shopping list.
  * Out of bounds indices include negative indices & indices that are greater than the size of the shopping list.
* Indices that are not numbers or are out of bounds will produce an error message indicating the error of the index.

Example of usage: 

`DEL 3`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author JLoh579 -->
### Clearing the list: `CLEAR`
Clears all items in the shopping list.

Format: `CLEAR`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author Shannonwje -->
### Resetting a budget: `RES`
Resets the budget to be $0.00 for the user.

Format: `RES`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author trishaangelica -->
### Exiting the program: `BYE`
Exits the program.

Format: `BYE`

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author kokjoon97 -->
## Additional information

### 1. Loading and saving your shopping list

All your shopping list and budget data are saved to JSON files after you
exit the application. This data is also retrieved from the same JSON files the next time you boot up
Shoco. No further action is required from you as this is an automatic process.

### 2. Automated budget tracker

When the total cost of the items in your shopping list exceeds the stored budget amount, a message will be
displayed which states by how much you have overrun your current budget. This message will only
stop appearing when you increase your budget amount sufficiently or remove enough items from your list to keep within
your budget.

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Simply transfer the JSON files that contain your SHOCO data onto the new computer and place them in the
folder that contains the SHOCO app. If the folder already has the JSON files, replace them.

<!-- @@author kokjoon97 -->

**Q**: Is it possible to restore a list that I have deleted?

**A**: Unfortunately, we are still working on this feature and there is no such functionality at this
point in time. It is however, possible to manually backup the `shoppinglist.json` file
from time to time so that if you unintentionally cleared your list, you can always replace the empty
`shoppinglist.json` file with your backed up version.

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>

***

<!-- @@author -->

<!-- @@author trishaangelica -->
## Command Summary

* View help `HELP`
* Display list and budget details `DISPLAY`
* Set budget `SET b/AMOUNT`
* Add item `ADD i/DESCRIPTION [p/PRICE] [q/QUANTITY]`
* Edit item `EDIT INDEX [i/DESCRIPTION] [p/PRICE] [q/QUANTITY]`
* Mark item `MARK INDEX`
* Un-mark item `UNMARK INDEX`
* Find item `FIND KEYWORD`
* Delete item `DEL INDEX`
* Clear list `CLEAR`
* Reset budget `RES`
* Exit program `BYE`
<!-- @@author -->

&nbsp;

<b><a href="#shoco-v21---user-guide">&#129053; back to top</a></b>
