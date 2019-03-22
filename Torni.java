public class Torni extends Nappula{

  public Torni(boolean vari, boolean liikkunut){
    this.vari = vari;
    this.arvo = 5;
    this.liikkunut = liikkunut;
    this.elossa = true;
    if (!vari){
       this.symbooli = "\2656";
    } else {
      this.symbooli = "\265C";
    }
  }

  public String getSymbooli(){
	  if (!this.annaVari()){
		 return "\u2656";
	  } else {
		return "\u265C";
	  }
  }

  public boolean annaVari(){
    return this.vari;
  }

    public String annaNimi(){
	    return "torni";
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
    int eka = sNo;
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
    return siirrot;
  }

   public void muutu(Pelilauta lauta){
  }

}
