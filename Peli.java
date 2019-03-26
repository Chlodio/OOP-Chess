import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Peli {

 private static Pelilauta SLauta; ///En tiedÃ¤ mistÃ¤ saisin pelilaudan, niin tallennnan sen tÃ¤hÃ¤n ainakin vÃ¤liaikasesti

 private boolean pelaajanVuoro; // true = pelaaja1
 private Pelilauta pelilauta;
 private Ihmispelaaja pelaaja1;
 private Pelaaja pelaaja2;

 public Peli(){}

 public static void setSLauta(Pelilauta p){
  SLauta = p;
 }

 public static Pelilauta getSLauta(){
  return SLauta;
 }

 public boolean tallennettuToimivaPeli() throws IOException{
  BufferedReader br = new BufferedReader(new FileReader("tallennettuPeli.txt"));
  ArrayList<String> riveja = new ArrayList<String>();
  String rivi = br.readLine();
  while (rivi != null){
   riveja.add(rivi);
   rivi = br.readLine();
 }
  br.close();
  String[] rivit = riveja.toArray(new String[riveja.size()]);
  for (int i = 0; i < 5; i++){
   if (rivit[i] == null){
    return false;
   }
  }
  if (rivit[0] != "true" && rivit[0] != "false" || rivit[1] != "true" && rivit[1] != "false" || rivit[2] != "tietokonepelaaja" && rivit[2] != "ihmispelaaja"){
   return false;
  }
  int kuninkaat = 0;
  for (int i = 3; i < rivit.length; i++){
   String[] tiedot = rivit[i].split(",");
   int paikka1 = Integer.parseInt(tiedot[0]);
   int paikka2 = Integer.parseInt(tiedot[1]);
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


 /*Nappulat tarvitsevat konstruktorin, joka asettaa ne laudalle, saaden parametrit seuraavassa jÃ¤rjesteyksessÃ¤: x-koordinaatti, y-koordinaatti, onValkoinen, pelilauta jonka laudalle se asetetaan, onLiikkunut*/
 /*JUHA: konstuktori tarvitsi vain boolean varin, lisasin konstruktoriin myos boolean onLiikkunut.*/
 /*JUHA: Nappulat asetatetaan laudalle Pelilaudan metodilla asetaNappula(Nappula, int, int).*/

 public void asetaPeliAsetuksilla(){
  Pelilauta pelilauta = new Pelilauta();
  setSLauta(pelilauta);
  this.pelilauta = pelilauta;

  pelilauta.asetaNappula(new Sotilas(true, false), 1, 0);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 1);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 2);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 3);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 4);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 5);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 6);
  pelilauta.asetaNappula(new Sotilas(true, false), 1, 7);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 0);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 1);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 2);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 3);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 4);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 5);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 6);
  pelilauta.asetaNappula(new Sotilas(false, false), 6, 7);
  pelilauta.asetaNappula(new Torni(true, false), 0, 0);
  pelilauta.asetaNappula(new Torni(true, false), 0, 7);
  pelilauta.asetaNappula(new Torni(false, false), 7, 0);
  pelilauta.asetaNappula(new Torni(false, false), 7, 7);
  pelilauta.asetaNappula(new Lahetti(true, false), 0, 1);
  pelilauta.asetaNappula(new Lahetti(true, false), 0, 6);
  pelilauta.asetaNappula(new Lahetti(false, false), 7, 1);
  pelilauta.asetaNappula(new Lahetti(false, false), 7, 6);
  pelilauta.asetaNappula(new Ratsu(true, false), 0, 2);
  pelilauta.asetaNappula(new Ratsu(true, false), 0, 5);
  pelilauta.asetaNappula(new Ratsu(false, false), 7, 2);
  pelilauta.asetaNappula(new Ratsu(false, false), 7, 5);
  pelilauta.asetaNappula(new Kuningas(true, false), 0, 4);
  pelilauta.asetaNappula(new Kuningas(false, false), 7, 4);
  pelilauta.asetaNappula(new Kuningatar(true, false), 0, 3);
  pelilauta.asetaNappula(new Kuningatar(false, false), 7, 3);
  this.pelaajanVuoro = true;
  this.pelaaja1 = new Ihmispelaaja();
 }


 public void lataaPeliAsetuksilla() throws IOException{
  Pelilauta pelilauta = new Pelilauta();
  this.pelilauta = pelilauta;
  BufferedReader br = new BufferedReader(new FileReader("tallennettuPeli.txt"));
  ArrayList<String> riveja = new ArrayList<String>();
  String rivi = br.readLine();
  while (rivi != null){
   riveja.add(rivi);
   rivi = br.readLine();
  }
  br.close();
  String[] rivit = riveja.toArray(new String[riveja.size()]);
  this.pelaajanVuoro = Boolean.parseBoolean(rivit[0]);
  this.pelaaja1 = new Ihmispelaaja();
  if (Boolean.parseBoolean(rivit[1])){
   pelilauta.asetaShakki(true);
  }
  String pelaaja2Nimi = rivit[2];
  if (pelaaja2Nimi == "ihmispelaaja"){
   this.pelaaja2 = new Ihmispelaaja();
  }
  else {
   this.pelaaja2 = new Tietokonepelaaja();
  }
  for (int i = 3; i < rivit.length; i++){
   String[] tiedot = rivit[i].split(",");
   ArrayList<Nappula> nappulat = new ArrayList<Nappula>();
   if (tiedot[2] == "sotilas"){
    pelilauta.asetaNappula(new Sotilas(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
   if (tiedot[2] == "torni"){
    pelilauta.asetaNappula(new Torni(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
   if (tiedot[2] == "hevonen"){
    pelilauta.asetaNappula(new Ratsu(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
   if (tiedot[2] == "lahetti"){
    pelilauta.asetaNappula(new Lahetti(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
   if (tiedot[2] == "kuningatar"){
    pelilauta.asetaNappula(new Kuningatar(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
   if (tiedot[2] == "kuningas"){
    pelilauta.asetaNappula(new Kuningas(Boolean.parseBoolean(tiedot[3]), Boolean.parseBoolean(tiedot[4])), Integer.parseInt(tiedot[0]), Integer.parseInt(tiedot[1]));
   }
  }
 }



 public void tallennaPeli() throws FileNotFoundException{
  PrintWriter out = new PrintWriter("tallennettuPeli.txt");
  out.println(this.pelaajanVuoro);
  out.println(pelilauta.annaShakki());
  out.println(this.pelaaja2.annaNimi());
  for (int i = 0; i < 7; i++){
   for (int j = 0; j < 7; j++){
    Nappula nappula = this.pelilauta.annaNappula(i, j);
    if (nappula != null){
     /*JUHA: muokkasin nappula.annaLiikkunut.*/
      String s = Integer.toString(i) + ","+ Integer.toString(j)+ ","+ nappula.annaNimi()+ ","+ Boolean.toString(nappula.annaVari())+ ","+ Boolean.toString(nappula.annaLiikkunut());
     out.println(s);
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
     int[][] nappulanSiirrot = this.pelilauta.annaNappula(i, j).siirrot(this.pelilauta);
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
  while (!onShakkimatti()){
      Tuomari.asetaVaihe(0);    //Nyt saa valikoida nappulan
      if (this.pelaajanVuoro){
       pelaaja1.liikuttaa(pelilauta);
       this.pelaajanVuoro = false;
      }
      else {
       pelaaja2.liikuttaa(pelilauta);
       this.pelaajanVuoro = true;
    }
   }
  pelilauta.asetaShakkimatti();
 }




}
