public class Kuningatar extends Nappula{

  public Kuningatar(boolean vari, boolean liikkunut){
	super(vari);
    this.vari = vari;
    this.arvo = 9;
    this.liikkunut = liikkunut;
    this.elossa = true;
  }

  public String getSymbooli(){
	  if (this.annaVari()){
		 return "\u2655";
	  } else {
		return "\u265B";
	  }
  }


  public String annaNimi(){
		return "kuningatar";
	}


  public void asetaElossa(boolean elossa){
    this.elossa = elossa;
	if (!elossa){
		this.poistaLaudalta();
	}
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
    int eka = sNo;
    int toka = sAbc;
    while (eka < 7){
      siirrot[eka+1][sAbc]=1;
      eka++;
    }
    eka = sNo;
    while (eka > 0){
      siirrot[eka-1][sAbc]=1;
      eka--;
    }
    eka = sAbc;
    while (eka > 0 ){
      siirrot[sNo][eka-1]=1;
      eka--;
    }
    eka = sAbc;
    while (eka < 7 ){
      siirrot[sNo][eka+1]=1;
      eka++;
    }
    eka = sNo;
    toka = sAbc;
    while (eka < 7 && toka < 7){
      siirrot[eka+1][toka+1]=1;
      eka++;
      toka++;
    }
    eka = sNo;
    toka = sAbc;
    while (eka > 0 && toka < 7){
      siirrot[eka-1][toka+1]=1;
      eka--;
      toka++;
    }
    eka = sNo;
    toka = sAbc;
    while (eka > 0 && toka > 0){
      siirrot[eka-1][toka-1]=1;
      eka--;
      toka--;
    }
    eka = sNo;
    toka = sAbc;
    while (eka < 7 && toka > 0){
      siirrot[eka+1][toka-1]=1;
      eka++;
      toka--;
    }
    return siirrot;
  }
    public void muutu(Pelilauta lauta){
  }
}
