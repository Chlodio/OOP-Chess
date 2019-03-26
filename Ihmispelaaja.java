class Ihmispelaaja implements Pelaaja {

 private boolean vari;
 private static int[][] omanVarNap;
 private static Pelilauta TLauta;
 private static Ihmispelaaja TPelaaja;

 public static void setOmanVarNap(int[][] i){
  omanVarNap = i;

 }

 public static int[][] getOmanVarNap(){
  return omanVarNap;
 }

 public static void setTLauta(Pelilauta pl){
  TLauta = pl;
 }

 public static Pelilauta getTLauta(){
  return TLauta;
 }

 public static void setTPelaaja(Ihmispelaaja pl){
  TPelaaja = pl;
 }

 public static Ihmispelaaja getTPelaaja(){
  return TPelaaja;
 }

 public Ihmispelaaja(boolean vari){
   this.vari = vari;
 }

 public String annaNimi(){
  return "ihmispelaaja";
 }


 public void liikuttaa(Pelilauta pelilauta){
  if (Tuomari.onkoVaihe(0)){
   setTLauta(pelilauta);
   setTPelaaja(this);
   int[][] omanVarinNappulat = new int[8][8];
   for(int i = 0; i <= 7; i++){
    for(int j = 0; j <= 7; j++){
     if (pelilauta.annaNappula(i,  j) != null && pelilauta.annaNappula(i, j).annaVari() == this.vari){
      omanVarinNappulat[i][j] = 1;
     }
    }
   }
   setOmanVarNap(omanVarinNappulat);
  }
  else if (Tuomari.onkoVaihe(1)){
   Nappula nappula = Nappula.getValittu();  /*pelaajan valitsema nappula omanVarinNappuloista*/;
   int[][] siirrot = nappula.siirrot(pelilauta); /*JUHA: parametriksi tarvitaan Pelilauta*/
   int[][] sallitutSiirrot = pelilauta.testaaSiirrot(siirrot, nappula);
   Pelilauta.varitaRuudut(sallitutSiirrot);
  }
  else if (Tuomari.onkoVaihe(2)){
    Tuomari.asetaVaihe(-1);       /*Toisen vuorolla ei tarvi koskea ruutuihin*/
   Nappula nappula = Nappula.getValittu();
   int uusiSijaintiNo = Ruutu.valittu.haeY();   /*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
   int uusiSijaintiAbc =  Ruutu.valittu.haeX(); /*pelaajan valitsema uusi sijainti sallitutSiirroista*/;
   Pelilauta.normaalisoiRuudut();
   /*JUHA: tähän voi lisätä vielä itse-murhatestin. Eli kyseinen siirto voidaan testata metodilla pelilauta.itsemurha(Nappula,int,int). Jos true, niin sitten pitäisi kysyä uutta siirtoa*/
   /*JUHA: huom: sallitutSiirrot ei palauta linnoitusmahdollisuutta. Tämä pitää kysyä erikseen pelilauta.linnoitusMahdollista(Kuningas,Torni)*/
   pelilauta.liiku(nappula, uusiSijaintiNo, uusiSijaintiAbc);
   Main.getTPeli().pelaa();
  }
 }

}
