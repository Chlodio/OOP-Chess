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
		int[][] siirrot = nappula.siirrot(); /*JUHA: parametriksi tarvitaan Pelilauta*/
		int[][] sallitutSiirrot = pelilauta.testaaSiirrot(siirrot, nappula);
		Pelilauta.varitaRuudut(sallitutSiirrot);
		int uusiSijaintiNo =	x%8  	/*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
		int uusiSijaintiAbc = 	x/8	/*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
		Pelilauta.normaalisoiRuudut(sallitutSiirrot);
		/*JUHA: tähän voi lisätä vielä itse-murhatestin. Eli kyseinen siirto voidaan testata metodilla pelilauta.itsemurha(Nappula,int,int). Jos true, niin sitten pitäisi kysyä uutta siirtoa*/
		/*JUHA: huom: sallitutSiirrot ei palauta linnoitusmahdollisuutta. Tämä pitää kysyä erikseen pelilauta.linnoitusMahdollista(Kuningas,Torni)*/
		pelilauta.liiku(nappula, uusiSijaintiNo, uusiSijaintiAbc);
	}
	
	
	
}
