package com.limuelle;

/**
 * Contains the main method that runs the whole game.
 * @author Limuelle Alamil
 *
 */

public class Game {
	/**
	 * Runs the whole game.
	 * Instantiate the factory, commands and objects of the game.
	 * Sets game conditions and display a text-based user interface.
	 * @param args Do nothing.
	 */
	public static void main(String[] args) {

        RemoteControl control = new RemoteControl();	// the invoker - hold and execute commands

        Player player = new Player();							// creates a player object
        GameFunction gameFunction = new GameFunction(player);

        System.out.println("=================================================================");
        System.out.println(">>>----------->>> WELCOME TO THE WIZARDING WORLD! <<<---------<<<");
        System.out.println("=================================================================");
        
        Command chooseHouse = new ChooseHouseCommand(player, gameFunction);	// asks the user to choose a house
        control.setCommand(chooseHouse);
        control.pressButton();

        Command showSpells = new ShowSpellsCommand(player, gameFunction);	// shows the spells to choose from
        control.setCommand(showSpells);
        control.pressButton();
 
        Command chooseSpells = new ChooseSpellsCommand(player, gameFunction);	// asks the user to choose spells
        control.setCommand(chooseSpells);
        control.pressButton();
        
        Command showStory = new ShowStoryCommand(player, gameFunction);		// shows the cut scene
        control.setCommand(showStory);
        control.pressButton();
        
        boolean playing = true;

        ADVENTURE:
        while(playing) {

            System.out.println("==================================================================");
            System.out.println(">>>------------------------------------------------------------<<<");
            System.out.println("==================================================================");

            MonsterFactory monsterFactory = new MonsterFactory();	// the factory - generates a random monster
            Monster monster = monsterFactory.getMonster();

            System.out.println("Attention! A " + monster.getName() + " has appeared!");
            
            // instantiate all the commands needed in the game (combat)
            Command playerDamage = new PlayerDamageCommand(player, monster);
            Command enemyDamage = new EnemyDamageCommand(monster, player);
            Command playerHeal = new PlayerHealCommand(player);
            Command showOptions = new ShowOptionsCommand(player, monster, gameFunction);

            while((monster.getHealth()) > 0) {		// while the monster wasn't defeated

                control.setCommand(showOptions);	// show all the necessary game information to the user
                control.pressButton();

                System.out.print("\nENTER YOUR CHOICE: ");

                int move = gameFunction.getInput(0, 5);	// asks the user what to do

                String spell = new String();			// will contain the spell that the user chose, if the user chose a spell

                if(move <= 3) {							// if the user choose 0 - 3, the user chose a spell (doesn't skip or quit)
                    spell = player.chosenSpell[move];
                }

                // instantiate a command to decrease a spell's PP (number of times a spell can be used)
                Command decreaseSpell = new DecreaseSpellCommand(gameFunction, move);	

                if((!spell.equals("EPISKEY")) && (!spell.equals("FERULA"))	// if the user uses an attack spell
                          && (move != 4) && (move != 5)) {

                	// if the spell hasn't ran out of PP
                    if((gameFunction.getSpellLimit(move)) > 0) {

                    	// sets the attack of the chosen spell as the player's attack value
                        Command setStats = new SetStatsCommand(player, gameFunction, move);
                        control.setCommand(setStats);
                        control.pressButton();
                        
                        // decreases the monster's health
                        control.setCommand(enemyDamage);
                        control.pressButton();

                        System.out.println("You attacked the " + monster.getName()
                                        + " dealing " + player.attack + " damage!");
                        
                        // decreases the PP of the chosen spell
                        control.setCommand(decreaseSpell);
                        control.pressButton();

                    // if the spell has ran out of PP    
                    }else{

                    	// sets the player's attack value to a constant 5 (struggleAttack)
                        Command setStats = new SetStatsCommand(player, gameFunction, move);
                        control.setCommand(setStats);
                        control.pressButton();

                        // decreases the monster's health
                        control.setCommand(enemyDamage);
                        control.pressButton();

                        System.out.println("You can no longer use this spell properly and struggled.");
                        System.out.println("You attacked the " + monster.getName()
                                        + " dealing " + player.attack + " damage only!");
                        
                    }

                    // if the monster wasn't defeated from the player's first attack, the monster will retaliate
                    if((monster.getHealth()) >= 1) {
                    	
                    	// decreases the player's health
                        control.setCommand(playerDamage);
                        control.pressButton();

                        System.out.println("The " + monster.getName() + " attacked you dealing "
                                            + monster.getAttack() + " damage!");

                        System.out.println("-----------------------------------------------------------------");

                    }

                    // if the player was defeated from the monster's retaliation, the game will end
                    if(player.health < 1) {
                        System.out.println("You have taken too much damage. You'll be taken to the healers.");
                        System.out.println("You have successfully defeated " + player.score + " magical beasts!");
                        break;

                    }

                }else if((spell.equals("EPISKEY")) || (spell.equals("FERULA"))) {	// if the user chose a healing spell

                	// if the spell hasn't ran out of PP
                    if((gameFunction.getSpellLimit(move)) > 0) {

                    	// sets the heal of the chosen spell as the player's heal value
                        Command setStats = new SetStatsCommand(player, gameFunction, move);
                        control.setCommand(setStats);
                        control.pressButton();

                        // increases the player's health (heal)
                        control.setCommand(playerHeal);
                        control.pressButton();

                        System.out.println("You healed yourself with " + player.heal + " HP.");

                        // decreases the PP of the chosen spell
                        control.setCommand(decreaseSpell);
                        control.pressButton();

                    // if the spell has ran out of PP
                    }else{

                    	// sets the player's heal value to a constant 5 (struggleHeal)
                        Command setStats = new SetStatsCommand(player, gameFunction, move);
                        control.setCommand(setStats);
                        control.pressButton();

                        // increases the player's health (heal)
                        control.setCommand(playerHeal);
                        control.pressButton();

                        System.out.println("You can no longer use this spell properly and struggled.");
                        System.out.println("You healed yourself with " + player.heal + " HP only.");

                    }

                    // if the monster wasn't defeated yet, the monster will retaliate
                    if((monster.getHealth()) >= 1) {

                    	// decreases the player's health
                        control.setCommand(playerDamage);
                        control.pressButton();

                        System.out.println("The " + monster.getName() + " attacked you dealing "
                                            + monster.getAttack() + " damage!");

                        System.out.println("-----------------------------------------------------------------");

                    }

                    // if the player was defeated from the monster's retaliation, the game will end
                    if((player.health) < 1) {
                        System.out.println("You have taken too much damage. You'll be taken to the healers.");
                        System.out.println("You have successfully defeated " + player.score + " magical beasts!");
                        break;

                    }

                // if the user wants to skip the current monster (APPARATE)
                }else if(move == 4) {

                	// if the player can still skip, the current battle will reset
                    if(player.amountOfSkips > 0) {	
                        System.out.println("You escaped from the " + monster.getName() + " by apparating to another place.");
                        player.amountOfSkips--;
                        continue ADVENTURE;

                    // if the player can no longer skip but still chose skip, the player will lose a move and the monster will retaliate
                    } else {						
                        System.out.println("You are too tired to apparate. Finish the monster now!");

                        // decreases the player's health
                        control.setCommand(playerDamage);
                        control.pressButton();

                        System.out.println("The " + monster.getName() + " attacked you dealing "
                                            + monster.getAttack() + " damage!");
                        System.out.println("-----------------------------------------------------------------");
                    }

                    // if the player was defeated from monster's retaliation, the game will end
                    if((player.health) < 1) {
                        System.out.println("You have taken too much damage. You'll be taken to the healers.");
                        System.out.println("You have successfully defeated " + player.score + " magical beasts!");
                        break;
                    }

                // if the user wants to quit the game, the game will end
                }else if(move == 5) {

                    System.out.println("You get out of the Forbidden Forest safely.");
                    System.out.println("You have successfully defeated " + player.score + " magical beasts!");
                    break ADVENTURE;

                }else {

                    System.out.println("< INVALID COMMAND >");

                }

            }

            // if the player was defeated, will break the game
            if((player.health) < 1) {
                break;
            // if the player wasn't defeated, will ask the user what to do next
            } else {

	            System.out.println("=================================================================");
	            System.out.println("The " + monster.getName() + " was defeated! Nicely done!");
	            System.out.println("=================================================================");
	            player.score++;
	            System.out.println("What would you like to do next?");
	            System.out.println("1. CONTINUE");
	            System.out.println("2. QUIT");
	            System.out.println("\nENTER YOUR CHOICE: ");
	
	            int nextMove = gameFunction.getInput(1, 2);
	
	            if(nextMove == 1) {			// the player continue playing
	
	                System.out.println("You continue to wander in the Forbidden Forest.");
	
	            }else if(nextMove == 2) {	// the player quit
	
	                System.out.println("You get out of the Forbidden Forest safely.");
	                System.out.println("You have successfully defeated " + player.score + " magical beasts!");
	                break;
	
	            }
            }

        }

        System.out.println("==================================================================");
        System.out.println(">>>---------------->>> THANKS FOR PLAYING! <<<-----------------<<<");
        System.out.println("==================================================================");

    }
}
