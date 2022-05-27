package com.limuelle;

import java.util.*;

/**
 * Factory class that contains the method that generate object of concrete Monster class.
 * @author Limuelle Alamil
 *
 */

public class MonsterFactory {
	
	/**
	 * Randomly generate object of concrete Monster class.
	 * @return A randomly generated object of type Monster.
	 */
	
	public Monster getMonster() {
		
		String[] monsters = {"ACROMANTULA", "BOGGART", "DEMENTOR", "TROLL",
							 "CORNISH PIXIE", "BASILISK", "CHIMERA"};

		Random randomizer = new Random();
		String monsterType = monsters[randomizer.nextInt(monsters.length)];

		if(monsterType.equals("ACROMANTULA")) {
			return new Acromantula();

		} else if(monsterType.equals("BOGGART")) {
			return new Boggart();

		} else if(monsterType.equals("DEMENTOR")) {
			return new Dementor();

		} else if(monsterType.equals("TROLL")) {
			return new Troll();

		} else if(monsterType.equals("CORNISH PIXIE")) {
			return new CornishPixie();
			
		} else if(monsterType.equals("BASILISK")) {
			return new Basilisk();

		} else if(monsterType.equals("CHIMERA")) {
			return new Chimera();

		} else {
			return null;
		}
	}
}
