class Peli {
	private boolean pelaajanVuoro; // true = pelaaja1
	private Pelilauta pelilauta;
	private Pelaaja pelaaja1;
	private Pelaaja pelaaja2;
	
	public Peli(){}
	

	
	public boolean tallennettuToimivaPeli(String tiedostonNimi) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(tiedostonNimi));
		try {
			String[] rivit = new String[19];
			String rivi = br.readLine();
			int indeksi = 0;
			while (line != null){
				rivit[indeksi] = rivi;
				rivi = br.readLine();
				indeksi += 1;
			}
		}
		catch (IOException e){
			return false;
		}
		finally {
			br.close();
		}
		for (int i = 0; i < 5; i++){
			if (rivit[i] == null){
				return false;
			}
		}
		if (rivit[0] != "1" && rivit[0] != "2" || rivit[1] != "true" && rivit[1] != "false" || rivit[2] != "tietokonepelaaja" && rivit[2] != "ihmispelaaja"){
			return false;
		}
		int kuninkaat = 0;
		for (int i = 3; i < rivit.length; i++){
			int paikka1 = Integer.parseInt(tiedot[0]);
			int paikka2 = Integer.parseInt(tiedot[1]);
			String[] tiedot = rivit[i].split(",");
			if (tiedot.length != 5){					
				return false;
			}
			else if (paikka1 < 0 || paikka1 > 7){
				return false;
			}
			else if (paikka2 < 0 || paikka2 > 7){
				return false;
			}
			else if (tiedot[3] != "true" && tiedot[3] != "false"){
				return false;
			}
			else if (tiedot[4] != "true" && tiedot[4] != "false"){
				return false;
			}
			else if (tiedot[2] == "kuningas"){
				kuninkaat +=1;
			}
		}
		if (kuninkaat == 2){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	/*Nappulat tarvitsevat konstruktorin, joka asettaa ne laudalle, saaden parametrit seuraavassa järjesteyksessä: x-koordinaatti, y-koordinaatti, onValkoinen, pelilauta jonka laudalle se asetetaan, onLiikkunut*/
	/*JUHA: konstuktori tarvitsi vain boolean varin, lisasin konstruktoriin myos boolean onLiikkunut.*/
	/*JUHA: Nappulat asetatetaan laudalle Pelilaudan metodilla asetaNappula(Nappula, int, int).*/
	
	public void asetaPeliAsetuksilla(){
		Pelilauta pelilauta = new Pelilauta();
		
		Sotilas sotilas1 = new Sotilas(true, false);
		Sotilas sotilas2 = new Sotilas(true, false);
		Sotilas sotilas3 = new Sotilas(true, false);
		Sotilas sotilas4 = new Sotilas(true, false);
		Sotilas sotilas5 = new Sotilas(true, false);
		Sotilas sotilas6 = new Sotilas(true, false);
		Sotilas sotilas7 = new Sotilas(true, false);
		Sotilas sotilas8 = new Sotilas(true, false);
		Sotilas sotilas9 = new Sotilas(false, false);
		Sotilas sotilas10 = new Sotilas(false, false);
		Sotilas sotilas11 = new Sotilas(false, false);
		Sotilas sotilas12 = new Sotilas(false, false);
		Sotilas sotilas13 = new Sotilas(false, false);
		Sotilas sotilas14 = new Sotilas(false, false);
		Sotilas sotilas15 = new Sotilas(false, false);
		Sotilas sotilas16 = new Sotilas(false, false);
		Torni torni1 = new Torni(true, false);
		Torni torni2 = new Torni(true, false);
		Torni torni3 = new Torni(true, false);
		Torni torni4 = new Torni(true, false);
		Lahetti lahetti1 = new Lahetti(true, false);
		Lahetti lahetti2 = new Lahetti(true, false);
		Lahetti lahetti3 = new Lahetti(true, false);
		Lahetti lahetti4 = new Lahetti(true, false);
		Hevonen hevonen1 = new Ratsu(true, false);
		Hevonen hevonen2 = new Ratsu(true, false);
		Hevonen hevonen3 = new Ratsu(false, false);
		Hevonen hevonen4 = new Ratsu(false, false);
		Kuningas kuningas1 = new Kuningas(false, false);
		Kuningas kuningas2 = new Kuningas(false, false);
		Kuningatar kuningatar1 = new Kuningatar(true, false);
		Kuningatar kuningatar2 = new Kuningatar(false, false);		
		
		pelilauta.asetaNappula(sotilas1, 1, 0);
		pelilauta.asetaNappula(sotilas2, 1, 1);
		pelilauta.asetaNappula(sotilas3, 1, 2);
		pelilauta.asetaNappula(sotilas4, 1, 3);
		pelilauta.asetaNappula(sotilas5, 1, 4);
		pelilauta.asetaNappula(sotilas6, 1, 5);
		pelilauta.asetaNappula(sotilas7, 1, 6);
		pelilauta.asetaNappula(sotilas8, 1, 7);
		pelilauta.asetaNappula(sotilas9, 6, 0);
		pelilauta.asetaNappula(sotilas10, 6, 1);
		pelilauta.asetaNappula(sotilas11, 6, 2);
		pelilauta.asetaNappula(sotilas12, 6, 3);
		pelilauta.asetaNappula(sotilas13, 6, 4);
		pelilauta.asetaNappula(sotilas14, 6, 5);
		pelilauta.asetaNappula(sotilas15, 6, 6);
		pelilauta.asetaNappula(sotilas16, 6, 7);
		pelilauta.asetaNappula(torni1, 0, 0);
		pelilauta.asetaNappula(torni2, 0, 7);
		pelilauta.asetaNappula(torni3, 7, 0);
		pelilauta.asetaNappula(torni4, 7, 7);
		pelilauta.asetaNappula(lahetti1, 0, 1);
		pelilauta.asetaNappula(lahetti2, 0, 6);
		pelilauta.asetaNappula(lahetti3, 7, 1);
		pelilauta.asetaNappula(lahetti4, 7, 6);
		pelilauta.asetaNappula(hevonen1, 0, 2);
		pelilauta.asetaNappula(hevonen2, 0, 5);
		pelilauta.asetaNappula(hevonen3, 7, 2);
		pelilauta.asetaNappula(hevonen4, 7, 5);
		pelilauta.asetaNappula(kuningas1, 0, 3);
		pelilauta.asetaNappula(kuningas2, 7, 3);
		pelilauta.asetaNappula(kuningatar1, 0, 4);
		pelilauta.asetaNappula(kuningatar2, 7, 4);
		
		this.pelaajanVuoro = 1;
		this.pelilauta = pelilauta;
	}
	
	
	public void lataaPeliAsetuksilla(){
		Pelilauta pelilauta = new Pelilauta();
		this.pelilauta = pelilauta;
		BufferedReader br = new BufferedReader(new FileReader(tiedostonNimi));
		String[] rivit = new String[19];
		String rivi = br.readLine();
		int indeksi = 0;
		while (line != null){
			rivit[indeksi] = rivi;
			rivi = br.readLine();
			indeksi += 1;
		}
		br.close();
		this.pelaajanVuoro = Boolean.parseBoolean(rivit[0]);
		this.pelaaja1 = new Ihmispelaaja();
		if (Boolean.parseBoolean(rivit[1])){
			pelilauta.asetaShakki();
		}
		String pelaaja2Nimi = rivit[2];
		if (pelaaja2Nimi == "ihmispelaaja"){
			this.pelaaja2 = new Ihmispelaaja();
		}
		else {
			this.pelaaja2 = new Tietokonepelaaja();
		}
		for (int i = 3; i < rivit.length; i++){
			int paikka1 = Integer.parseInt(tiedot[0]);
			int paikka2 = Integer.parseInt(tiedot[1]);
			String[] tiedot = rivit[i].split(",");
			ArrayList<Nappula> nappulat = new ArrayList<Nappula>();
			if (tiedot[2] == "sotilas"){
				nappulat.add(new Sotilas(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
			if (tiedot[2] == "torni"){
				nappulat.add(new Torni(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
			if (tiedot[2] == "hevonen"){
				nappulat.add(new Hevonen(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
			if (tiedot[2] == "lahetti"){
				nappulat.add(new Lahetti(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
			if (tiedot[2] == "kuningatar"){
				nappulat.add(new Kuningatar(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
			if (tiedot[2] == "kuningas"){
				nappulat.add(new Kuningas(Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]), Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])));
			}
		}
	}
	
	
	
	public void tallennaPeli(){
		PrintWriter out = new PrintWriter("tallennettuPeli.txt") 
    out.println(this.pelaajanVuoro);
		out.println(pelilauta.annaShakki());
		out.println(this.pelaaja2.annaNimi());
		for (int i = 0; i < 7; i++){
			for (int j = 0; j < 7; j++){
				Nappula nappula = this.pelilauta.annaNappula(i, j);
				if (nappula != null){
					/*JUHA: muokkasin nappula.annaLiikkunut.*/
					out.println(i, ",", j, ",", nappula.annaNimi(), ",", nappula.annaVari(), ",", nappula.annaLiikkunut())
				}
			}
		}
		out.close();
	}
	
	
	
	public boolean onShakkimatti(){
		for (int i = 0; i < 7; i++){
			for (int j = 0; j < 7; j++){
				Nappula nappula = this.pelilauta.annaNappula(i, j);
				if (nappula != null && nappula.annaVari() == this.pelaajanVuoro){
					int[][] nappulanSiirrot = this.pelilauta[i][j].siirrot();
					int[][] nappulanOikeatSiirrot = this.pelilauta.testaaSiirrot(nappulanSiirrot, nappula);
					for (int k = 0; k < 7; k++){
						for (int l = 0; l < 7; l++){
							if (nappulanOikeatSiirrot[k][l] == 1 && !this.pelilauta.itsemurha(this.pelilauta.annaNappula(k, l), k, l)){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	
	
	public void pelaa(){
		while (!this.pelilauta.onShakkimatti()){
			for (int i = 0; i < 7; i++){
				for (int j = 0; j < 7; j++){
					if (this.pelilauta.annaNappula(i, j){
						if (this.pelaajanVuoro){
							pelaaja1.liikuttaa(pelilauta);
							this.pelaajanVuoro = false;
						}
						else {
							pelaaja2.liikuttaa(pelilauta);
							this.pelaajanVuoro = true;
						}
					}
				}
			}
		}
		pelilauta.asetaShakkimatti();
	}
	
	
	
	
}
