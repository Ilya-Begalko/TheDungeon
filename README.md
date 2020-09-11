# TheDungeon
The Dungeon game.
-----------------------------------
Saving the game is based on encrypting the progress in the game into a file
txt format. Saving occurs if the user entered his game
name, otherwise when exiting he is asked to come up with a nickname and save
file with the given name, if the user refuses, then saving is not
going on. Encryption and decryption happens using the class
Cipher.java by replacing each data character with bits, and adding
encryption key. Reads back the saved data.
The data is saved in the application project folder - Figure 8.
To simplify the gameplay, a class was created
CONFIRMATION.java, which recognizes affirmative responses from different
variations. This is done so that during the game, if the user
entered "Ok" instead of "Yes", the application accepted a positive answer, and not
finished work.
The Player.java class somewhat unifies all the classes in the application, because
that in it we create a player, and keep statistics of killed enemies,
we assign it armor, weapons and potions, we assign a game name, golden
coins. Also implemented in this class are checks for unplanned
errors and data return for saving the game in text format.
And the most important class of the game is TheDungeon.java. In this class
the menu is implemented, the switch tool selects the desired item
menu, it is also implemented here - battle, the appearance of enemies, the loss of objects and
coins. For all this we needed a random, this problem was

solved by the Random () function, and by introducing value constraints for each
damage, enemy, etc.

-----------------------------------
1.Start and progress of the game
------------------------------------
When the program starts, the user is greeted and provided
the ability to download the latest game if the user wishes
continue, then he needs to enter his name or nickname through which he was
the game is saved. If this save does not exist, then a new one will start.
unsaved game.
At the beginning of the game, the player is assigned health equal to
100 life units, 3 bottles of a potion that restores health
and 0 coins. For the convenience of the player, each move is displayed higher and higher
the listed items, along with the number of enemies killed. At the beginning
each level a mob attacks the player, his health is also displayed
player, and the battle begins. Every move the player has the opportunity
use the store or drink a health potion.
The end of the game occurs as soon as the player kills 50 mobs, while not
dying even once, at death the score is reset to zero, and the game starts over.
If you want to end the game, there is a menu item "Exit the game"
by clicking which you will be prompted to save the progress if you entered a name
user earlier, then he will save the game with this name by default, if
you did not enter, it will offer to write a username and save the data.

-------------------------------------------------------------------------
2.Fight
-------------------------------------------------------
As mentioned earlier, each level a user is attacked by a mob, and
each turn the user and the mob damage each other. To implement
this action, 15 types of mobs were developed. For a change, everything
mobs were divided into 5 categories according to difficulty, and each category
assigned their ranges of maximum health, and maximum damage
mob. Since the implementation of levels through the choice of doors, I had to
refuse, the category of mobs was implemented, consisting of one mob -
a treasure chest with zero attack. At his death
the user receives a certain number of coins. Thus, this mob,
replaces the player with a treasure room.
The process of the battle itself is arranged in the following way, after the attack of the mob on
user, the program displays his health, and offers a choice
the following actions: "Attack", "Use the potion", "Escape", "Shop"
and Quit Game. When selecting the first item, the user applies
random damage to the mob, in the range of maximum damage, based on
the quality of the user's sword. The user also receives damage based on
the complexity of the mob and the quality of the user's armor. Received and inflicted
damage is output after each turn. Potion can be used every turn
health, which will restore 30 life units of the user's health.
When choosing the "Escape" item, the user can escape from the mob if
considers that the enemy is stronger than him, thereby saving himself from death, but if
this will be fined, either for coins, or 5 life units if
the user does not have the required amount of coins.
Once the user deals equal or greater damage than
the current health value of the mob, the mob dies, and at the user's expense
points are added, a random number of coins, based on the complexity of the mob,
and also the player can drop a health potion, armor or sword, with a certain
probability. After the death of the mob, the next level begins, and on
the user is attacked by the next mob.

-----------------------------------------
3.Store
----------------------------------------
Each turn, the player can go to the store and improve his armor, sword
or replenish the amount of health potions. To do this, in the battle menu
the user should select the item "Store" and it will be transferred to the store. IN
the store presents the entire range of items available in the game. IN
the price of course increases depending on the quality. After the purchase,
the user is shown his purchase and spent coins, and he is transferred
back into battle.

--------------------------------------------------------------------------
4.Items
---------------------------------------------------------------------------
The game has 3 different types of items. The first is the potion
health, which restore the health of the character. The second type is armor.
The function of armor is to absorb some of the damage that
user, also each type of armor has its own strength. In Game
3 types of armor have been implemented: leather, iron and gold. Respectively
quality of armor increases its durability and absorption of damage taken from
mob. The third type of objects, swords, is similarly executed. There are also three
type of swords: wooden, iron, gold. The function of swords is
increased damage done, swords, like armor, have durability.

----------------------------------------------------------------
5.Other functions
--------------------------------------------------------------
Were developed interesting functions in the game, for a change
gameplay. In the game to help the player, the function is implemented
"Help and advice", it is presented in the main menu of the program. With her
choice to the user, tips on the game from the developers and
help with common questions.
There is also an "Easter egg" in the game, if you enter a value in the store
1308, then the player will receive a sword with high strength and damage. The game was
added references to the developers' favorite games. They can be found
when finding and killing mobs-chests, in addition to coins, they drop out
scrapbook with phrases of characters of games.
