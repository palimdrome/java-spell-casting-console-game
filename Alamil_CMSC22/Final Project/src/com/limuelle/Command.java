package com.limuelle;

import java.util.*;

/**
 * Command interface that contains an execute method which is implemented by concrete Command classes.
 * @author Limuelle Alamil
 *
 */

public interface Command {

	public void execute();

}


// Command that decreases the player's health.
class PlayerDamageCommand implements Command {

	Player player;
	Monster monster;

	public PlayerDamageCommand(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
	}
	public void execute() {
		player.health = player.health - monster.getAttack();
	}

}

// Command that decreases the monster's health.
class EnemyDamageCommand implements Command {

	Monster monster;
	Player player;

	public EnemyDamageCommand(Monster monster, Player player) {
		this.monster = monster;
		this.player = player;
	}
	public void execute() {
		monster.setHealth(player.attack);
	}

}

// Command that increases the player's health.
class PlayerHealCommand implements Command {

	Player player;

	public PlayerHealCommand(Player player) {
		this.player = player;
	}
	public void execute() {
		player.health = player.health + player.heal;
	}

}


// Command that assigns a value to the player's attack and heal attributes.
class SetStatsCommand implements Command {

	Player player;
	GameFunction gameFunction;
	String[][] spellName;
	int[][] spellData;
	int move;

	public SetStatsCommand(Player player, GameFunction gameFunction, int move) {
		this.player = player;
		this.gameFunction = gameFunction;
		this.spellName = gameFunction.spellName;
		this.spellData = gameFunction.spellData;
		this.move = move;
	}
	public void execute() {

		int index1 = gameFunction.getIndex(move);
		
		// if the spell hasn't ran out (PP != 0), the spell's attack/heal value will be assigned to player's attack/heal attribute
		if(spellData[index1][1] > 0) {
			if(spellName[index1][0].equals("EPISKEY") || spellName[index1][0].equals("FERULA")) {
				player.heal = spellData[index1][0];
			} else {
				player.attack = spellData[index1][0];
			}
		
		// if the spell has ran out (PP == 0), the player's attack/heal attribute will be set to a constant 5
		} else if(spellData[index1][1] <= 0) {
			if(spellName[index1][0].equals("EPISKEY") || spellName[index1][0].equals("FERULA")) {
				player.heal = player.struggleHeal;		// 5
			} else {
				player.attack = player.struggleAttack;	// 5
			}
		}
	}

}

// Command that decreases the spell limit of the player's spells.
class DecreaseSpellCommand implements Command {

	GameFunction gameFunction;
	int move;

	public DecreaseSpellCommand(GameFunction gameFunction, int move) {
		this.gameFunction = gameFunction;
		this.move = move;
	}
	public void execute() {

		int index1 = gameFunction.getIndex(move);
		gameFunction.spellData[index1][1]--;	// decrement a spell's PP

	}

}

// Command that displays the list of spells where the player can choose from.
class ShowSpellsCommand implements Command {

	Player player;
	GameFunction gameFunction;
	String[][] spellName;
	int[][] spellData;

	public ShowSpellsCommand(Player player, GameFunction gameFunction) {
		this.player = player;
		this.gameFunction = gameFunction;
		this.spellName = gameFunction.spellName;
		this.spellData = gameFunction.spellData;
	}
	
	public void execute() {
		// formatting
		System.out.println("Here are the spells that you can learn.");
		System.out.println("Choose " + player.amountOfSpells + " spells that you like.");
		System.out.println("-----------------------------------------------------------------");

		for(int i = 0; i < spellName.length; i++) {

			int lackingSpaces1 = 18 - spellName[i][0].length();
			String spaces1 = new String(new char[lackingSpaces1]).replace("\0", " ");

			int lackingSpaces2 = 20 - spellName[i][1].length();
			String spaces2 = new String(new char[lackingSpaces2]).replace("\0", " ");

			if((!spellName[i][0].equals("EPISKEY")) && (!spellName[i][0].equals("FERULA"))) {

				System.out.println((i) + ". " + spellName[i][0] + spaces1 + " > " + spellName[i][1] + spaces2
			        	+ " > [ ATK: " + spellData[i][0] + " PP: " + spellData[i][1] + " ]");

			} else {

				System.out.println((i) + ". " + spellName[i][0] + spaces1 + " > " + spellName[i][1] + spaces2
			        	+ " > [ HEAL:" + spellData[i][0] + " PP: " + spellData[i][1] + " ]");

			}

		}

		System.out.println("-----------------------------------------------------------------");
	}
}

// Command that displays all the necessary game information to the user.
// It includes the player's health, attack, remaining spells, and monster's health and attack.
class ShowOptionsCommand implements Command {

	Player player;
	Monster monster;
	String monsterName;
	GameFunction gameFunction;
	String[] chosenSpell;

	public ShowOptionsCommand(Player player, Monster monster, GameFunction gameFunction) {
		this.player = player;
		this.monster = monster;
		this.monsterName = monster.getName();
		this.gameFunction = gameFunction;
		this.chosenSpell = player.chosenSpell;
	}

	public void execute() {
		// formatting
		String spaces1 = new String(new char[13 - monsterName.length()]).replace("\0", " ");
		System.out.println(monsterName + spaces1 + ": HP [ " + monster.getHealth() + " ] ATK: [ " + monster.getAttack() + " ]");
		System.out.println("\nPLAYER       : HP [ " + player.health + " ] " + "SCORE [ " + player.score + " ]");
		System.out.println("What would you like to do?");

		for(int i = 0; i < chosenSpell.length ; i++) {
			String spell = chosenSpell[i];
			int lackingSpaces = 18 - spell.length();
			String spaces2 = new String(new char[lackingSpaces]).replace("\0", " ");
			int power = gameFunction.spellData[gameFunction.getIndex(i)][0];
			int amount = gameFunction.spellData[gameFunction.getIndex(i)][1];
			if(spell.equals("EPISKEY") || spell.equals("FERULA")) {
				System.out.println("\t" + i + ". " + spell + spaces2 + "\tHEAL: [ " + power + " ] \tPP : [ " + amount + " ]");
			} else {
				System.out.println("\t" + i + ". " + spell + spaces2 + "\tATK : [ " + power + " ] \tPP : [ " + amount + " ]");
			}
		}
		System.out.println("\t4. APPARATE          \tATK : [ 0 ] \tPP : [ " + player.amountOfSkips + " ]");
		System.out.println("\t5. QUIT");

	}

}

// Command that asks the user's chosen spells.
class ChooseSpellsCommand implements Command {

	Player player;
	GameFunction gameFunction;
	String[][] spellName;
	String[] chosenSpell;

	public ChooseSpellsCommand(Player player, GameFunction gameFunction) {
		this.player = player;
		this.gameFunction = gameFunction;
		this.spellName = gameFunction.spellName;
		this.chosenSpell = player.chosenSpell;
	}

	public void execute() {

		System.out.println("ENTER THE NUMBER OF SPELLS YOU LIKE (Type number and press ENTER):");

		int[] spellCount = new int[spellName.length];	// contains the number of times a spell has been chosen
		Arrays.fill(spellCount, 0);

		for(int j = 0; j < player.amountOfSpells; j++) {

			int choice = gameFunction.getInput(0, (spellName.length - 1));
			spellCount[choice]++;

			// if the spell has only been chosen once, it will qualify as one of the player's chosen spells
			if(spellCount[choice] == 1) {
				chosenSpell[j] = spellName[choice][0];

			// if the spell has been chosen already, it won't be added to the player's chosen spells (to avoid duplicates)
			} else {
				System.out.println("! YOU ALREADY CHOSE THIS SPELL. CHOOSE ANOTHER !");
				System.out.println("\nENTER YOUR CHOICE: ");
				j--;
			}
		}
		
		System.out.println("THE SPELLS YOU HAVE LEARNED ARE: ");
		for(int i = 0; i < chosenSpell.length; i++) {
			System.out.println(chosenSpell[i]);
		}
	}
}

// Command that asks the user to choose a house
class ChooseHouseCommand implements Command {
	GameFunction gameFunction;
	Player player;
	
	public ChooseHouseCommand(Player player, GameFunction gameFunction) {
		this.player = player;
		this.gameFunction = gameFunction;
	}
	
	public void execute() {
		// formatting
		System.out.println("-----------------------------------------------------------------");
		System.out.println("            Which Hogwarts house are you sorted in?              ");
		System.out.println("\t\t1. GRYFFINDOR");
		System.out.println("\t\t2. HUFFLEPUFF");
		System.out.println("\t\t3. RAVENCLAW");
		System.out.println("\t\t4. SLYTHERIN");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("ENTER YOUR CHOICE: ");
		
		int choice = gameFunction.getInput(1, 4);
		player.chosenHouse = gameFunction.hogwartsHouse[choice-1][0];
		
		System.out.println("You're a " + player.chosenHouse + "!");
		System.out.println("-----------------------------------------------------------------");
	}
}


// Command that show the cutscene or a short story about the game
class ShowStoryCommand implements Command {
	
	Player player;
	GameFunction gameFunction;
	String[][] hogwartsHouse;
	
	public ShowStoryCommand(Player player, GameFunction gameFunction) {
		this.player = player;
		this.gameFunction = gameFunction;
		this.hogwartsHouse = gameFunction.hogwartsHouse;
	}
	
	public void execute() {
		// looks for the index of the user's chosen house in the list hogwartsHouses
		int index1 = 0;
		for(int i = 0; i < hogwartsHouse.length; i++) {
			if(hogwartsHouse[i][0].equals(player.chosenHouse)) {
				index1 = i;
			}
		}
		// formatting
		System.out.println("==================================================================");
        System.out.println(">>>------------------------------------------------------------<<<");
        System.out.println("==================================================================");
		System.out.println("  You are a newly enrolled student in the famous magical school  ");
		System.out.println("          Hogwarts School of Witchcraft and Wizardry.            ");
		System.out.println(" It is your first time entering the wizarding world and Hogwarts ");
		System.out.println("        so you became very curious of your new environment       ");
		System.out.println(" Everyday, after your " + hogwartsHouse[index1][1] + ", you");
		System.out.println(" would wander around the school and " + hogwartsHouse[index1][2] + ".");
		System.out.println(" One night, you realized that you've already visited every corner");
		System.out.println("        of the school and has nowhere to go. Except one.         ");
		System.out.println("                     The Forbidden Forest...                     ");
		System.out.println("  Being a " + hogwartsHouse[index1][3] + ", and a " + hogwartsHouse[index1][0] + ", you entered ");
		System.out.println("   the forest, forgetting everything Hagrid said about monsters. ");
		System.out.println(" Suddenly, a thick fog covered the surroundings and you got lost.");
		System.out.println("   As you try to find your way out, you hear a peculiar sound... ");
		
	}
}
