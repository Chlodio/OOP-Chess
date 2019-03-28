class Sotilas extends Nappula{

  public Sotilas(boolean vari, boolean liikkunut){
 super(vari);
    this.vari = vari;
    this.arvo = 1;
    this.liikkunut = liikkunut;
    this.elossa = true;
}

  public String getSymbooli(){
   if (this.annaVari()){
   return "\u2659";
   } else {
  return "\u265F";
   }
  }


  public String annaNimi(){
  return "sotilas";
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
    
    if(this.vari){                                             // valkoisen siirrot
   
      if (sNo < 7 && lauta.annaNappula(sNo+1,sAbc)==null){       // voi mennÃ¤ ylÃ¶s && edessÃ¤ ei nappulaa
      siirrot[sNo+1][sAbc]=1;                                   // ota askel ylÃ¶s
      if (this.liikkunut == false && lauta.annaNappula(sNo+2,sAbc)==null){  // ei liikkunut ja edessÃ¤ 2 vapaata
        siirrot[sNo+2][sAbc] = 1;                                // ota kaksi askelta ylÃ¶s
      }
    }
      if(sAbc > 0){                                               // ei vasemmassa reunassa
        Nappula n1 = lauta.annaNappula(sNo+1,sAbc-1);
        if (n1 != null){                                         // vasemmassa ylÃ¤viistossa nappula
          if (this.vari != n1.annaVari()){                          // nappulan vÃ¤ri eri
            siirrot[sNo+1][sAbc-1] = 1;                        // liiku vasenylÃ¤
          }
        }
        if(lauta.annaNappula(sNo,sAbc-1) == lauta.annaKohde() && lauta.annaOhestaLyonti()){
          siirrot[sNo+1][sAbc-1] = 1;
        }
      }
      if(sAbc < 7){                                               // ei oikeassa reunassa
        Nappula n2 = lauta.annaNappula(sNo+1,sAbc+1);             
        if (n2 != null){                                           // oikea ylÃ¤ on nappula
          if (this.vari != n2.annaVari()){                           // nappulan vÃ¤ri on eri
            siirrot[sNo+1][sAbc+1] = 1;                             // askel oikea ylÃ¤
          }
        }
        if(lauta.annaNappula(sNo,sAbc+1) == lauta.annaKohde() && lauta.annaOhestaLyonti()){
          siirrot[sNo+1][sAbc+1] = 1;
        }
      }
      
    }
    
    if(!this.vari){
      if (sNo > 0 && lauta.annaNappula(sNo-1,sAbc)==null){
      siirrot[sNo-1][sAbc]=1;
      if (this.liikkunut == false && lauta.annaNappula(sNo-2,sAbc)==null){
        siirrot[sNo-2][sAbc] = 1;
      }
      }
      if(sAbc > 0){
        Nappula n1 = lauta.annaNappula(sNo-1,sAbc-1);
        if (n1 != null){
          if (this.vari != n1.annaVari()){
            siirrot[sNo-1][sAbc-1] = 1;
          }
        }
       if(lauta.annaNappula(sNo,sAbc-1) == lauta.annaKohde() && lauta.annaOhestaLyonti()){
          siirrot[sNo-1][sAbc-1] = 1;
        }  
      }
      if(sAbc < 7){
        Nappula n2 = lauta.annaNappula(sNo-1,sAbc+1);
        if (n2 != null){
          if (this.vari != n2.annaVari()){
            siirrot[sNo-1][sAbc+1] = 1;
          }
        }
      if(lauta.annaNappula(sNo,sAbc+1) == lauta.annaKohde() && lauta.annaOhestaLyonti()){
          siirrot[sNo-1][sAbc+1] = 1;
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
