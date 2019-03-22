public class Ratsu extends Nappula{

  public Ratsu(boolean vari, boolean liikkunut){
    this.vari = vari;
    this.arvo = 3;
    this.liikkunut = false;
    this.elossa = true;
  }

  public String getSymbooli(){
	  if (!this.annaVari()){
		 return "\u2658";
	  } else {
		return "\u265E";
	  }
  }

  public boolean annaVari(){
    return this.vari;
  }

  public String annaNimi(){
	  return "ratsu";
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
    if (sNo < 6 && sAbc < 7){
      siirrot[sNo+2][sAbc+1]=1;
    }
    if (sNo < 7 &&  sAbc < 6){
      siirrot[sNo+1][sAbc+2] =1;
    }
    if (sNo > 0 && sAbc < 6){
      siirrot[sNo-1][sAbc+2]=1;
    }
    if (sNo > 1 &&  sAbc < 7){
      siirrot[sNo-2][sAbc+1] =1;
    }
    if (sNo > 1 && sAbc > 0){
      siirrot[sNo-2][sAbc-1]=1;
    }
    if (sNo > 0 &&  sAbc > 1){
      siirrot[sNo-1][sAbc-2] =1;
    }
    if (sNo < 7 && sAbc > 1){
      siirrot[sNo+1][sAbc-2]=1;
    }
    if (sNo < 6 &&  sAbc > 0){
      siirrot[sNo+2][sAbc-1] =1;
    }
    return siirrot;
  }
  public void muutu(Pelilauta lauta){
  }

}
