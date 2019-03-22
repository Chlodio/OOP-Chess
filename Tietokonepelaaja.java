import java.util.concurrent.ThreadLocalRandom;
class Tietokonepelaaja extends Pelaaja {

	public Tietokonepelaaja(){}

	public String annaNimi(){
		return "tietokonepelaaja";
	}


	public void liikuttaa(Pelilauta pelilauta){
		int parhaanNappulanArvo = 11;
		int parhaanSyodynNappulanArvo = 0;
		int parhaanNappulanLahtopaikkaNo;
		int parhaanNappulanLahtopaikkaAbc;
		int parhaanNappulanSijoituspaikkaNo;
		int parhaanNappulanSijoituspaikkaAbc;
		int[][] omanVarinNappulat = new int[8][8];
		int[][] vastustajanNappulat = new int[8][8];
		for(int i = 0; i < 7; i++){
			for(int j = 0; i < 7; j++){
				if (pelilauta.annaNappula(i, j) != null && pelilauta.annaNappula(i, j).annaVari() == true){
					omanVarinNappulat[i][j] = 1;
				}
			}
		}
		for(int i = 0; i < 7; i++){
			for(int j = 0; i < 7; j++){
				if (omanVarinNappulat[i][j] == 0 && pelilauta.annaNappula(i, j) != null){
					vastustajanNappulat[i][j] = 1;
				}
			}
		}
		for(int i = 0; i < 7; i++){
			for(int j = 0; i < 7; j++){
				if (omanVarinNappulat[i][j] > 0){
					int[][] siirrot = pelilauta.annaNappula(i, j).siirrot(pelilauta);
					int[][] sallitutSiirrot = pelilauta.testaaSiirrot(siirrot, pelilauta.annaNappula(i, j));
					for (int k = 0; k < 7; k++){
						for (int l = 0; l < 7; l++){
							if (sallitutSiirrot[k][l] > 0 && vastustajanNappulat[k][l] > 0 && pelilauta.annaNappula(i, j).annaArvo() < parhaanNappulanArvo && !pelilauta.itsemurha(pelilauta.annaNappula(i, j), k, l)){
								parhaanNappulanLahtopaikkaNo = i;
								parhaanNappulanLahtopaikkaAbc = j;
								parhaanNappulanSijoituspaikkaNo = k;
								parhaanNappulanSijoituspaikka = l;
								parhaanNappulanArvo = pelilauta.annaNappula(i, j).annaArvo();
								parhaanSyodynNappulanArvo = pelilauta.annaNappula(k, l).annaArvo();
							}
							else if (sallitutSiirrot[k][l] > 0 && vastustajanNappulat[k][l] > 0 && pelilauta.annaNappula(i, j).annaArvo() == parhaanNappulanArvo && pelilauta.annaNappula(k, l).annaArvo() > parhaanSyodynNappulanArvo && !pelilauta.itsemurha(pelilauta.annaNappula(i, j), k, l)){
								parhaanNappulanLahtopaikkaNo = i;
								parhaanNappulanLahtopaikkaAbc = j;
								parhaanNappulanSijoituspaikkaNo = k;
								parhaanNappulanSijoituspaikkaAbc = l;
								parhaanNappulanArvo = pelilauta.annaNappula(i, j).annaArvo();
								parhaanSyodynNappulanArvo = pelilauta.annaNappula(k, l).annaArvo();
							}
						}
					}
				}
			}
		}
		if (parhaanNappulanArvo < 11){
			pelilauta.liiku(pelilauta.annaNappula(parhaanNappulanLahtopaikkaNo, parhaanNappulanLahtopaikkaAbc), parhaanNappulanSijoituspaikkaNo, parhaanNappulanSijoituspaikkaAbc);
		}
		else {
			while (1 == 1){
				int random = ThreadLocalRandom.current().nextInt(0, 8);
				int random2 = ThreadLocalRandom.current().nextInt(0, 8);
				int random3 = ThreadLocalRandom.current().nextInt(0, 8);
				int random4 = ThreadLocalRandom.current().nextInt(0, 8);
				int[][] siirrot = pelilauta.annaNappula(random,random2).siirrot(pelilauta);
				int[][] sallitutSiirrot = pelilauta.testaaSiirrot(siirrot, pelilauta.annaNappula(random,random2));
				if (sallitutSiirrot[random3][random4] > 0 && !pelilauta.itsemurha(pelilauta.annaNappula(random, random2), random3, random4)){
					pelilauta.liiku(pelilauta.annaNappula(random, random2), random3, random4);
					break;
				}
			}
		}
	}





}
