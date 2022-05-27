package com.limuelle;

import java.util.Scanner;

/**
 * Contains the rest of the game methods and game information.
 * @author Limuelle Alamil
 *
 */

public class GameFunction {
	
	Player player;
	
	public GameFunction(Player player) {
		this.player = player;
	}
	
	// {spell name, spell description}
	String[][] spellName = {{"STUPEFY", "Stunning Spell"},
        	{"BOMBARDA MAXIMA", "Exploding Charm"},
			{"INCENDIO", "Fire-Making Charm"},
			{"EPISKEY", "Healing Charm"},
			{"FERULA", "Bandaging Charm"},
			{"PETRIFICUS TOTALUS", "Full Body-Bind Curse"},
			{"AVADA KEDAVRA", "Killing Curse"},
			{"CRUCIO", "Torture Curse"},
			{"REDUCTO", "Reductor Curse"},
			{"EXPECTO PATRONUM", "Patronus Charm"}};
	
	// PP - amount of times a spell can be used
	// {{ATK/HEAL, PP}}
	int[][] spellData = {{30, 20}, {60, 10}, {20, 30}, {60, 10}, {20, 30},
						{20, 30}, {200, 3}, {30, 20}, {100, 6}, {20, 30}};
	
	String[][] hogwartsHouse = {
			{"Gryffindor", "Quidditch practice with your teammates", "practice spells on your own", "brave person by nature"},
			{"Hufflepuff", "Herbology class with Prof. Longbottom", "look for cute magical beasts", "very optimistic person"},
			{"Ravenclaw", "Charms class with Professor Flitwick", "write things on your journal", "curious person by nature"},
			{"Slytherin", "Potions class with Professor Slughorn", "search herbs for your potion", "very persistent person"}
			};
	
	/**
	 * Asks the user for an integer input within a specified range (min, max).
	 * @param min The lower limit
	 * @param max The upper limit
	 * @return An integer value within a specified range (min, max). 
	 */

	public int getInput(int min, int max) {
		
		Scanner userInput = new Scanner(System.in);
		int input = 0;
        boolean repeat = true;

        // will keep on asking for an input until a desired input is given
        while(repeat == true) {
            try {
                input = userInput.nextInt();
                if(input > max || input < min) {
                	System.out.println("! INVALID INPUT. PLEASE ENTER A NUMBER [" + min + " - " + max + "] !");
                	System.out.println("\nENTER YOUR CHOICE: ");
                	userInput.nextLine();
                	repeat = true;
                	continue;
                }
                repeat = false;
            } catch(Exception e) {
                System.out.println("! INVALID INPUT. PLEASE ENTER A NUMBER [" + min + " - " + max + "] !");
                System.out.println("\nENTER YOUR CHOICE: ");
                userInput.nextLine();
                repeat = true;
            }
        }
        return input;
	}
	
	
	/**
	 * Looks for the user's chosen spell in the list spellName.
	 * Will compare the user's chosen spell to the existing spells in the list spellName.
	 * Will return the index of a spell in the list spellName that matches the user's chosen spell.
	 * @param move An integer that specifies the spell whose index must be taken.
	 * @return An integer value that tells the index of the user's chosen spell in the list spellName.
	 */

	public int getIndex(int move) {

		String spell = player.chosenSpell[move];	// the spell that the user want to use
		int index1 = 0;
		
		for(int i = 0; i < spellName.length; i++) {
			for(int j = 0; j < spellName[i].length; j++) {
				if(spellName[i][j].equals(spell)) {
					index1 = i;
				}
			}
		}
		return index1;
	}
	
	/**
	 * Return the spell limit or the number of times a specified spell can be used.
	 * Looks for the specified spell's limit in the list spellData
	 * @param move An integer that specifies the spell whose limit must be taken.
	 * @return An integer value that tells the number of times a spell can be used.
	 */

	public int getSpellLimit(int move) {

		int index1 = getIndex(move);
		return spellData[index1][1];

	}
}
