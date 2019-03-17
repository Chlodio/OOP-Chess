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
	
	public void asetaPeliAsetuksilla(){
		Pelilauta pelilauta = new Pelilauta();
		
		Sotilas sotilas1 = new Sotilas(1, 0, true, pelilauta, false);
		Sotilas sotilas2 = new Sotilas(1, 1, true, pelilauta, false);
		Sotilas sotilas3 = new Sotilas(1, 2, true, pelilauta, false);
		Sotilas sotilas4 = new Sotilas(1, 3, true, pelilauta, false);
		Sotilas sotilas5 = new Sotilas(1, 4, true, pelilauta, false);
		Sotilas sotilas6 = new Sotilas(1, 5, true, pelilauta, false);
		Sotilas sotilas7 = new Sotilas(1, 6, true, pelilauta, false);
		Sotilas sotilas8 = new Sotilas(1, 7, true, pelilauta, false);
		Sotilas sotilas9 = new Sotilas(6, 0,false, pelilauta, false);
		Sotilas sotilas10 = new Sotilas(6, 1,false, pelilauta, false);
		Sotilas sotilas11 = new Sotilas(6, 2,false, pelilauta, false);
		Sotilas sotilas12 = new Sotilas(6, 3,false, pelilauta, false);
		Sotilas sotilas13 = new Sotilas(6, 4,false, pelilauta, false);
		Sotilas sotilas14 = new Sotilas(6, 5,false, pelilauta, false);
		Sotilas sotilas15 = new Sotilas(6, 6,false, pelilauta, false);
		Sotilas sotilas16 = new Sotilas(6, 7,false, pelilauta, false);
		Torni torni1 = new Torni(0, 0, true, pelilauta, false);
		Torni torni2 = new Torni(0, 7, true, pelilauta, false);
		Torni torni3 = new Torni(7, 0,false, pelilauta, false);
		Torni torni4 = new Torni(7, 7,false, pelilauta, false);
		Lahetti lahetti1 = new Lahetti(0, 1, true, pelilauta, false);
		Lahetti lahetti2 = new Lahetti(0, 6, true, pelilauta, false);
		Lahetti lahetti3 = new Lahetti(7, 1,false, pelilauta, false);
		Lahetti lahetti4 = new Lahetti(7, 6,false, pelilauta, false);
		Hevonen hevonen1 = new Hevonen(0, 2, true, pelilauta, false);
		Hevonen hevonen2 = new Hevonen(0, 5, true, pelilauta, false);
		Hevonen hevonen3 = new Hevonen(7, 2,false, pelilauta, false);
		Hevonen hevonen4 = new Hevonen(7, 5,false, pelilauta, false);
		Kuningas kuningas1 = new Kuningas(0, 3, true, pelilauta, false);
		Kuningas kuningas 2 = new Kuningas(7, 3,false, pelilauta, false);
		Kuningatar kuningatar1 = new Kuningatar(0, 4, true, pelilauta, false);
		Kuningatar kuningatar2 = new Kuningatar(7, 4,false, pelilauta, false);
		
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
					out.println(i, ",", j, ",", nappula.annaNimi(), ",", nappula.annaVari(), ",", nappula.onLiikkunut())
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
