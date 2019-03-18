public class Lahetti extends Nappula{
    
  public Lahetti(boolean vari, boolean liikkunut){
    this.vari = vari;
    this.arvo = 3;
    this.liikkunut = liikkunut;
    this.elossa = true;
    if (!vari){ 
       this.symbooli = "♗";
    } else {
      this.symbooli = "♝";
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
    int eka = sNo;
    int toka = sAbc;
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
