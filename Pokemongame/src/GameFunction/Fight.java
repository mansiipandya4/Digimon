package GameFunction;

import java.util.Scanner;

import Graphics.DelayedUpdateAdapter;

public class Fight {
	
	EnemyTree enemyDeck;
	PersonalPokemon personalDeck;
	Creature enemy, ally;
	int Chose, pokemon, lives;
	int numberPoke = 0;
	String Schose, Slives, movement;
	boolean fighton = true;
	MovementTrainer MoveT = new MovementTrainer();
	Scanner input = new Scanner(System.in);
	
	public Fight(EnemyTree enemyDeck, PersonalPokemon personalDeck){
		this.enemyDeck = enemyDeck;
		this.personalDeck = personalDeck;
		this.enemy = getEnemy();
		this.ally = getPersonalPokemon(pokemon);
	}
	public Creature getEnemy(){
		return enemyDeck.getEnemy(enemyDeck.getRoot());
	}
	public Creature getPersonalPokemon(int pokemon) {
		//wait for keystroke select pokemon
		//add scanner class
		this.pokemon = pokemon;
		return personalDeck.getPokemon(pokemon);//for now its 0
	}
	
	public void firstMove(){
		if(enemy.getUsed()==false) {
		System.out.println("\nEnemy Pokemon:"+ this.enemy.getName() + "   Health:" +this.enemy.getTmpHealth());
		if(this.ally.getTmpHealth() == 0 || this.ally.getTmpHealth() < 0) {
			lost();
		}
		else if(this.getEnemy().getTmpHealth()< 0) {
			System.out.println("You have killed the enemy pokemon.");
				this.getEnemy().setTrue();
				win();
		}
		
		else if(this.ally.getSpeed() > this.enemy.getSpeed()) {
			//there are two method at the bottom
			TrainerActions trainerActionsDuringFight = new TrainerActions(personalDeck, this.getEnemy());
			if(personalDeck.getPokeball()!=0) {
			CaptureDialoge();
			trainerActionsDuringFight.capture(Chose);
			}else if(personalDeck.getPokeball()==0) {
				System.out.println("Oh no you outta pokeballs!");
			}
			if(this.enemy.catchs == false) {
			
			attack();
			nextMove1();
			firstMove();
			}
			else {
				this.enemy = getEnemy();
				firstMove();
			}	
		}
		else{
			//automatic attack from attack list
			if(this.enemy.catchs == false) {
			attack();
			nextMove2();
			firstMove();
			}
		}
		}
		else {
			this.enemy = getEnemy();
			return;
		}
	}
	public void attack() {
		attack(this.enemy,this.ally);
		System.out.println("Your Pokemon:" +this.ally.getName() + "  Health:" + this.ally.getTmpHealth() + "\n");
	}
	public void nextMove1(){
		//automatic attack from attack list
		if(this.enemy.catchs == false) {
		attack(this.getEnemy(),this.ally);
		}
	}
	
	public void nextMove2(){
		//keystrokes to choose action
		TrainerActions trainerActionsDuringFight = new TrainerActions(personalDeck, this.getEnemy());
		if(personalDeck.getPokeball()!=0) {
		CaptureDialoge();
		trainerActionsDuringFight.capture(Chose);
		}
		else if(personalDeck.getPokeball()==0) {
			System.out.println("Oh no you outta pokeballs!");
		}
		if(this.enemy.catchs == false) {
		attack(this.ally, this.getEnemy());
		}
		//this is where it stop after capture
		
	}
	
	public void attack(Creature a, Creature b){//creature a attacks creature b and takes b's hp away
		if(b.getDefense() < a.getAttack()) {
			
		b.setTmpHealth(b.getTmpHealth()-a.attack);
		}
		else {
			b.setTmpHealth(b.getTmpHealth()- (a.attack/2)); 
		
			}
		}
	public void win() {
		System.out.println("you won");
		enemy.setTrue();
		enemy = enemyDeck.getEnemy(enemy);
		personalDeck.money();
		this.ally.incrementStats();
		
		
		
	}
	public void lost() {
		numberPoke = 0;
		System.out.println("your pokemon died");
		if(personalDeck.PokeOrder==1) {
			System.out.println("would you like to heal your pokemon?");
			System.out.println("hit 1 for yes and hit 0 for no");
			Slives = input.nextLine();
			checklive(Slives);
			//this will go thought the pokemon to check if there dead or not
		}
		else if(personalDeck.PokeOrder > 1){
			
			for(int pokemon = 0; pokemon < personalDeck.pokeMon.length-1;pokemon++) {
				if(personalDeck.pokeMon[pokemon] == null) {
					System.out.println("you have no more pokemon");
					fighton=false;
					break;
				}else if(numberPoke == 6){
					System.out.println("all your pokemon are dead");
					fighton=false;
					break;
					
				}else if(personalDeck.pokeMon[pokemon].getTmpHealth() <= 0){
					numberPoke++;
				}else if(personalDeck.pokeMon[pokemon] != null) {
					this.ally = personalDeck.pokeMon[pokemon];
					break;
				}
				else {
					break;
				}
			}
			if(fighton) {
			firstMove();
			}
			else {
			System.out.println("would you like to heal your pokemons?");
			System.out.println("hit 1 for yes and hit 0 for no");
			
			Slives= input.nextLine();
			
			if(checkliveall(Slives)==1) {
				healingPokeCenter();
			}
			else if(checkliveall(Slives)==0){
				personalDeck.setGameOver();
				System.out.println("Game Over");
				System.exit(0);
			}
		}
		}	
	}
	public void setChose(int Chose) {
		this.Chose = Chose;
	}
	public void CaptureDialoge() {
		System.out.println("Would you like to Capture this pokemon?");
		System.out.println("Hit 1 for Yes and Hit 0 for No");
		
		Schose = input.nextLine();
		if(Schose.equalsIgnoreCase("1") || Schose.equalsIgnoreCase("0")) {
			Chose = Integer.parseInt(Schose);
			if(Chose == 0 || Chose == 1) {
				setChose(Chose);
			}else {
				System.out.println("invalid anwer please try again");
			CaptureDialoge();
			}
		}else {
			System.out.println("invalid anwer please try again");
			CaptureDialoge();
		}
	}
	public void healingPokeCenter(){
		for(int pokeheal = numberPoke-1; pokeheal >= 0;pokeheal--) {
			personalDeck.pokeMon[pokeheal].resetTmpHealth();
		}
		numberPoke = 0;
		System.out.println("all your pokemon are healed!");
		this.ally = personalDeck.getPokemon(0);
	}
	public void lives(int lives) {
		if(lives==1) {
			personalDeck.pokeMon[0].resetTmpHealth();;
			System.out.println("your pokemon is healed");
		}else if(lives==0){
			personalDeck.setGameOver();
			System.out.println("Game Over");
			System.exit(0);
		}else {
			System.out.println("incorrect number put in Try again your pokemon needs you!");
			System.out.println("Hit 1 for Yes and Hit 0 for No");
			Slives = input.nextLine();
			checklive(Slives);
				}
			
		}//this also check but for another methods
	public void checklive(String B) {
		lives = Integer.parseInt(B);
		if(B.equalsIgnoreCase("1") || B.equalsIgnoreCase("0")) {
			if(lives == 0 || lives == 1) {
				lives = Integer.parseInt(Slives);
				lives(lives);
			}else {
				check(B);
			}
	}
	// this is to check if the user input a wrong answer
}
	public int checkliveall(String B) {
	lives = Integer.parseInt(B);
	if(B.equalsIgnoreCase("1") || B.equalsIgnoreCase("0")) {
		if(lives == 0 || lives == 1) {
			lives = Integer.parseInt(Slives);
			return lives;
		}else {
			check(B);
			}
		}
	return lives;
}
	
	public void check(String B) {
		if(B.length() >1) {
			System.out.println("invalid anwer please try again");
			B = input.nextLine();
			checkliveall(B);
		}else if(lives > 1) {
			System.out.println("invalid anwer please try again");
			B = input.nextLine();
			checkliveall(B);
	}

	}
}