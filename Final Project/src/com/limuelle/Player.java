package com.limuelle;

/**
 * Contains all of the player's attributes.
 * @author Limuelle Alamil
 *
 */

public class Player {
	
	int health = 200;
	int attack;				// the player's main attack value that changes based on the user's chosen spell
	int struggleAttack = 5; // the player's attack value when a spell that already ran out was used
	int heal;				// the player's main heal value that changes based on the user's chosen spell
	int struggleHeal = 5;	// the player's heal value when a spell that already ran out was used
	int amountOfSpells = 4;	// the amount of spells that user can choose
	int amountOfSkips = 5;	// the amount of skips the user can do
	int score = 0;			// the amount of defeated monsters
	
	String chosenHouse;
	String[] chosenSpell = new String[amountOfSpells];	// list of the user's chosen spells	

}
