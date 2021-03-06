package GameFunction;

public class BillComputer {
	int PokeLimits;
	Creature[] BillComputer = new Creature[PokeLimits*2];
	
	public void storePoke(Creature newPokemon, int PokeStorageNum) {
		if(PokeStorageNum > BillComputer.length) {
			DoubleSpace(PokeStorageNum);
		}
		BillComputer[PokeStorageNum] = newPokemon;
		PokeStorageNum++;
	}
	//this will increase the space for storing computers
		public void DoubleSpace(int PokeStorageNum) {
			Creature[] newTemp = new Creature[BillComputer.length*2];
			for(int PokeInBill = 0; PokeInBill < PokeStorageNum;PokeInBill++) {
				newTemp[PokeInBill] = BillComputer[PokeInBill];
			}
			this.BillComputer = newTemp;
		}
		
	//it needs a way to change pokemon
		public void changePokemonfromStorage(String NameCreatureOut, String CreatureIn, Creature[] pokeMon, Creature[] pokeMonStats) {
			int In = 0;
			for(int pokein=0; pokein < pokeMon.length-1;pokein++) {
				if(CreatureIn.equalsIgnoreCase(pokeMon[pokein].getName())) {
					In = pokein;
				}
			}
			for(int NumofPoke = 0; NumofPoke < BillComputer.length-1;NumofPoke++) {
				if(NameCreatureOut.equalsIgnoreCase(BillComputer[NumofPoke].name)) {
						Creature temp = pokeMon[In];
						Creature temp2 = BillComputer[NumofPoke];
						pokeMon[In] = temp2;
						BillComputer[NumofPoke] = temp;
						pokeMonStats[In] =temp2;
						pokeMonStats[NumofPoke] = temp;
				}
			}
		}
}
