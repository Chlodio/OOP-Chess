class Ihmispelaaja extends Pelaaja {
	
	public Ihmispelaaja(){}
	
	public void liikuttaa(Pelilauta pelilauta){
		int[][] omanVarinNappulat = new int[8][8];
		for(int i = 0; i < 7; i++){
			for(int j = 0; i < 7; j++){
				if (pelilauta.annaNappula(i,  j) != null && pelilauta.annaNappula(i, j).annaVari() == onValkoinen){
					omanVarinNappulat[i][j] = 1;
				}
			}
		}
		
		/*TÄHÄN ALLE TARVITAAN TOMIN KOODIA*/
		Nappula nappula = /*pelaajan valitsema nappula omanVarinNappuloista*/;
		int[][] siirrot = nappula.siirrot();
		int[][] sallitutSiirrot = pelilauta.testaaSiirrot(siirrot, nappula);
		int uusiSijaintiNo =/*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
		int uusiSijaintiAbc = /*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
		pelilauta.liiku(nappula, uusiSijaintiNo, uusiSijaintiAbc);
	}
	
	
	
}
