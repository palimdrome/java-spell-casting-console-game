package com.limuelle;

/**
 * Monster interface containing methods which is implemented by concrete Monster classes.
 * @author Limuelle Alamil
 *
 */

public interface Monster {

	String getName();
	int getHealth();
	int setHealth(int num);
	int getAttack();

}

// Creates an object of type Acromantula.
class Acromantula implements Monster {

	String name = "Acromantula";
	int health = 50;
	int attack = 30;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type Boggart.
class Boggart implements Monster {

	String name = "Boggart";
	int health = 20;
	int attack = 20;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type Dementor.
class Dementor implements Monster {

	String name = "Dementor";
	int health = 100;
	int attack = 50;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type Troll.
class Troll implements Monster {

	String name = "Troll";
	int health = 50;
	int attack = 40;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type CornishPixie.
class CornishPixie implements Monster {

	String name = "Cornish Pixie";
	int health = 10;
	int attack = 10;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type Basilisk.
class Basilisk implements Monster {

	String name = "Basilisk";
	int health = 200;
	int attack = 80;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}

// Creates an object of type Chimera.
class Chimera implements Monster {

	String name = "Chimera";
	int health = 200;
	int attack = 80;

	public String getName(){
		return this.name;
	}
	public int getHealth(){
		return this.health;
	}
	public int setHealth(int num){
		this.health = this.health - num;
		return this.health;
	}
	public int getAttack(){
		return this.attack;
	}

}
