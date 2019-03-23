class Sotilas extends Nappula{

  public Sotilas(boolean vari, boolean liikkunut){
	super(vari);
    this.vari = vari;
    this.arvo = 1;
    this.liikkunut = liikkunut;
    this.elossa = true;
}

  public String getSymbooli(){
	  if (!this.annaVari()){
		 return "\u2659";
	  } else {
		return "\u265F";
	  }
  }

  public boolean annaVari(){
    return this.vari;
  }

  public String annaNimi(){
		return "sotilas";
	}


  public void asetaElossa(boolean elossa){
    this.elossa = elossa;
  }

  public boolean annaElossa(){
    return this.elossa;
  }

  public int annaArvo(){
    return this.arvo;
  }

  public void asetaLiikkunut(){
    this.liikkunut = true;
  }

  public boolean annaLiikkunut(){
    return this.liikkunut;
  }

  public int[][] siirrot(Pelilauta lauta){
    int sNo = lauta.annaSijaintiNo(this);
    int sAbc = lauta.annaSijaintiAbc(this);
    int[][] siirrot = new int[8][8];
    if (sNo < 7){
      siirrot[sNo+1][sAbc]=1;
      if (this.liikkunut == false){
        siirrot[sNo+2][sAbc] = 1;
      }
      if(sAbc > 0){
        Nappula n1 = lauta.annaNappula(sNo+1,sAbc-1);
        if (n1 != null){
          if (this.vari != n1.annaVari()){
            siirrot[sNo+1][sAbc-1] = 1;
          }
        }
      }
      if(sAbc < 7){
        Nappula n2 = lauta.annaNappula(sNo+1,sAbc+1);
        if (n2 != null){
          if (this.vari != n2.annaVari()){
            siirrot[sNo+1][sAbc+1] = 1;
          }
        }
      }
    }
    return siirrot;
  }

  public void muutu(Pelilauta lauta){
    int sNo = lauta.annaSijaintiNo(this);
    int sAbc = lauta.annaSijaintiAbc(this);
    boolean vari = this.vari;
    Kuningatar uusi = new Kuningatar(vari, false);
    uusi.asetaLiikkunut();
    this.asetaElossa(false);
    lauta.asetaNappula(uusi, sNo, sAbc);
  }

}
