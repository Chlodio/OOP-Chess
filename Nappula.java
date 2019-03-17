   /*
   * Huom1: Suunnitelman vastaisesti linnoitustesti löytyy Pelilauta-luokasta
   * Huom2: Suunnitelman vastaisesti Nappula-luokassa ei ole sijaintietoja. Ne löytyvät Pelilauta-luokasta
   */


abstract class Nappula{
  protected boolean vari;
  protected boolean elossa;
  protected int arvo;
  protected boolean liikkunut;
  
  abstract boolean annaVari();
  abstract void asetaElossa(boolean elossa);
  abstract boolean annaElossa();
  abstract int annaArvo();
  abstract void asetaLiikkunut();
  abstract boolean annaLiikkunut();
  abstract int[][] siirrot(Pelilauta lauta);
  abstract void muutu(Pelilauta lauta);
}

class Sotilas extends Nappula{
  
  public Sotilas(boolean vari){
    this.vari = vari;
    this.arvo = 1;
    this.liikkunut = false;
    this.elossa = true;
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
    Kuningatar uusi = new Kuningatar(vari);
    uusi.asetaLiikkunut();
    this.asetaElossa(false);
    lauta.asetaNappula(uusi, sNo, sAbc);
  }
  
}

class Ratsu extends Nappula{
    
  public Ratsu(boolean vari){
    this.vari = vari;
    this.arvo = 3;
    this.liikkunut = false;
    this.elossa = true;
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

class Lahetti extends Nappula{
    
  public Lahetti(boolean vari){
    this.vari = vari;
    this.arvo = 3;
    this.liikkunut = false;
    this.elossa = true;
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

class Torni extends Nappula{
    
  public Torni(boolean vari){
    this.vari = vari;
    this.arvo = 5;
    this.liikkunut = false;
    this.elossa = true;
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

class Kuningatar extends Nappula{
    
  public Kuningatar(boolean vari){
    this.vari = vari;
    this.arvo = 9;
    this.liikkunut = false;
    this.elossa = true;
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

class Kuningas extends Nappula{
  
  public Kuningas(boolean vari){
    this.vari = vari;
    this.arvo = 10;
    this.liikkunut = false;
    this.elossa = true;
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
