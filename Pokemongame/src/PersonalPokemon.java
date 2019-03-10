
public class PersonalPokemon {
	int PokeOrder;
	int PokeLimits = 6;
	int PokeStorageNum;
	Creature[] pokeMon = new Creature[PokeLimits];
	Creature[] BillComputer = new Creature[PokeLimits*2];
	public void storePersonalPokemon(Creature Pokemon) {
		pokeMon[PokeOrder] = Pokemon;
		PokeOrder++;
	}
	public void removesPokemon(String Pokemon) {
		for(int PokeName = 0; PokeName < pokeMon.length-1;PokeName++) {
			if(pokeMon[PokeName].getName().equalsIgnoreCase(Pokemon)) {
				PokeOrder--;
				pokeMon[PokeName] = null;
				movePokemonlist();
			}
			else {
				//this is just to make it go if anything happens
			}
		}
	}//this is to move the array down and fix it 
	public void movePokemonlist() {
		for(int NumofPoke= 0; NumofPoke < pokeMon.length-1;NumofPoke++) {
			if(pokeMon[NumofPoke] == null) {
				if(NumofPoke+1 < pokeMon.length) {
					pokeMon[NumofPoke] = pokeMon[NumofPoke+1];
				}
				else {
				//this one is to check if it reaches the end of the pokemon array so it doesnt break	
				}
			}
			else {
				
			}
		}
	}//this will just add the pokemon
	public void addPokemon(Creature newPokemon) {
		if(PokeOrder != 6) {
			pokeMon[PokeOrder] = newPokemon;
		PokeOrder++;
		
		}
		else {
			System.out.println("You have too many pokemon!!");
			System.out.println("Your pokemone will be store here");
		}
		
	}
	public void storePoke(Creature newPokemon) {
		if(PokeStorageNum > BillComputer.length) {
			DoubleSpace();
		}
		BillComputer[PokeStorageNum] = newPokemon;
		
		PokeStorageNum++;
	}//it needs a way to change pokemon
	public void changePokemonfromStorage(String NameCreature) {
		for(int NumofPoke = 0; NumofPoke < BillComputer.length-1;NumofPoke++) {
			if(NameCreature.equalsIgnoreCase(BillComputer[NumofPoke].name)) {
				if(PokeOrder >= 6) {
					System.out.println("you have too many pokemon");
				}else {
					pokeMon[PokeOrder] = BillComputer[NumofPoke];
				}
			}
		}
	}//this will increase the space for storing computers
	public void DoubleSpace() {
		Creature[] newTemp = new Creature[BillComputer.length*2];
		for(int PokeInBill = 0; PokeInBill < PokeStorageNum;PokeInBill++) {
			newTemp[PokeInBill] = BillComputer[PokeInBill];
		}
		this.BillComputer = newTemp;
	}
}