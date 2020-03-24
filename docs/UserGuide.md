# SHOCO v1.0 - User Guide

## Introduction

SHOCO is a command-line interface (CLI) application that allows users to 
manage and plan their shopping lists and budget. If you are a fast typer, 
you will find SHOCO to be even more effective than applications with graphical 
user interfaces.


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `SHOCO` from [here](https://github.com/AY1920S2-CS2113T-T13-1/tp/releases).

## Features 

{Give detailed description of each feature}

### Deleting an item: `DEL`
Removes an item from the list at the specified index.

Format: `DEL [index]`

* The `[index]` should be an integer.
* The `[index]` should not be out of bounds of the shopping list.  

Example of usage: 

`DEL 3`


### Setting a budget: `SET`
Sets a budget for the user.

Format: `SET b/[amount]`

* The `[amount]` can be any double that is between 0 to 5000.
* The `b/` substring should be present in the command.  

Example of usage: 

`SET b/3.00`

### Finding an item: `FIND`
Filters the shopping list according to a keyword specified by the user.

Format: `FIND [keyword]`

* The `[keyword]` can be any character or string.
* The `[keyword]` field should not be left empty.  

Example of usage: 

`FIND apple`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Well, write the User Guide in active voice anyway.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add to-do `todo n/TODO_NAME d/DEADLINE`
* Delete item `DEL [index]`
* Set budget `SET b/[amount]`
* Find item `FIND [keyword]`
