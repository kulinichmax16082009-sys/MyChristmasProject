# Sample Five
This is a 2D made text game, which was coded in java using IntelliJ IDEA. Game uses emojies and ANSI colors for better experience. Game uses czech language as main language, because it was made in Czech Republic

## Functions
* Walking in all world directions (North, East, West, South)
* Talking with NPCs in rooms
* Fully working inventory system (droping & collecting)
* Quests and Tasks
* Unique item abilities, which can be used
* Intelligence system
* Marks system

## Commands
### Walking
* jdi sever: Makes player move to the North
* jdi jih: Makes player move to the South
* jdi východ: Makes player move to the East
* jdi západ: Makes player move to the West
### Inventory
* seber: Makes player pick up 1 square near item
* vyhoď <item-name>: Makes player drop item in his inventory
* použíj <item-name>: Uses ability of the item
* inventář: Shows current inventory status
### Tasks & Marks
* úkoly: Shows all current player's tasks
* známky: Shows all player' marks
### Game Utils
* konec: Completely ends the game
* pomoc: Shows all possible commands
* nápověda: Shows hint for player's situation
### Talking with NPCs
* mluv: Starts dialog with NPC 1 square near
* odpověz <answer>: Makes player answer to the question
* ukonči_dialog: Makes player to quit dialog

## How to run
**for running .jar file you need:**
1) Create a folder with random name (ex. game)
2) Put .jar file and resources folder in folder you have just created
3) Open Windows Terminal and write: **chcp 65001**
4) Go to your folder in Terminal by using: **cd (folder-path)**
5) To run the program enter: **java -jar (.jar-file-name)**

## Requirements
* download JDK 17 or greater
* download [Windows Terminal app](https://apps.microsoft.com/detail/9n0dx20hk701?hl=ru-RU&gl=CZ) from Microsoft Store
