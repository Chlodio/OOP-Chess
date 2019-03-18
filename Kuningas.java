public class Kuningas extends Nappula{
  
  public Kuningas(boolean vari){
    this.vari = vari;
    this.arvo = 10;
    this.liikkunut = false;
    this.elossa = true;
    if (!vari){ 
       this.symbooli = "♔";
    } else {
      this.symbooli = "♚";
    } 
  }
  
  public boolean annaVari(){
    return this.vari;
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
    }
    if (sNo > 0){
      siirrot[sNo-1][sAbc]=1;
    }
    if (sAbc > 0){
      siirrot[sNo][sAbc-1]=1;
    }
    if (sAbc < 7){
      siirrot[sNo][sAbc+1]=1;
    }
    if (sNo < 7 && sAbc < 7){
      siirrot[sNo+1][sAbc+1]=1;
    }
    if (sNo < 7 && sAbc > 0){
      siirrot[sNo+1][sAbc-1]=1;
    }
    if (sNo > 0 && sAbc < 7){
      siirrot[sNo-1][sAbc+1]=1;
    }
    if (sNo >0 && sAbc > 0){
      siirrot[sNo-1][sAbc-1]=1;
    }
    return siirrot;
  }
    public void muutu(Pelilauta lauta){
  }
    
}
