import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.text.DefaultCaret;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
   * Huom1: Luokka Pelilauta ei sisÃ¤llÃ¤ suunnitelmasta poiketen metodia shakkimatti. Ehdotan, ettÃ¤ tÃ¤mÃ¤ testataan Peli-
   * luokassa. Shakkimatti syntyy, jos mikÃ¤Ã¤n nappula ei palauta sallittuja siirtoja. Shakkimatti syntyy myÃ¶s
   * jos kaikki sallitut siirrot johtavat itsemurhaan. MielestÃ¤ni nÃ¤mÃ¤ testit on yksinkertaisempi toteuttaa Peli-
   * luokassa.
   *
   * Huom2: Olen aika epÃ¤varma tuon itsemurha-metodin toimivuudesta, mutta en oikein keksinyt helppoa tapaa testata
   * sitÃ¤
   *
   * Huom3: Koodissa on varmasti vielÃ¤ paljon virheitÃ¤...
   */

class Pelilauta{

  /*NÃ¤kymÃ¶tÃ¶n kerros nappuloita joiden avulla siirrot toimivat*/
  public static Map<Integer, JButton> ruutuValikko = new HashMap<>();
  /*Merkki labellit sivuille*/
  public static Map<Integer, JLabel> ruutuMerkki = new HashMap<>();
 /*Tulevan JFramen pÃ¤Ã¤lle*/
  public static MainPanel mainPanel;

 public static ArrayList<JButton> valitutRuudut = new ArrayList();

 public static JTextArea textBox = new JTextArea();
 public static JScrollPane scrollPanel = new JScrollPane(textBox);

  /*
   * Pelilaudan attribuutit alla
   */

  protected Nappula[][] lauta;
  protected boolean shakki;
  protected boolean shakkimatti;
  protected boolean ohestaLyonti;
  protected Nappula kohde;

    /*
   * Konstruktori
   */

  public Pelilauta(){
    this.lauta = new Nappula[8][8];
    this.shakki = false;
    this.shakkimatti = false;
    this.ohestaLyonti = false;
    this.kohde = null;
  }

    /*
   * Konstruktori, joka luo pelistÃ¤ kopion. KÃ¤ytetÃ¤Ã¤n mahdollisten siirtojen simulointiin ja itsemurhan testaukseen
   */

  public Pelilauta(Pelilauta kopio){
    this.lauta = new Nappula[8][8];
    for (int i = 0; i<8; i++){
      for (int a = 0; a<8; a++){
        lauta[i][a] = kopio.lauta[i][a];
      }
    }
    this.shakki = kopio.shakki;
    this.shakkimatti = kopio.shakkimatti;
    this.ohestaLyonti = kopio.ohestaLyonti;
    this.kohde = kopio.kohde;
  }
      /*
   * asetus ja tarkastus-metodit
   */

 public static MainPanel getMainPanel(){
  return mainPanel;
 }

  public void asetaShakki(boolean shakki){
    this.shakki = shakki;
  }

  public void asetaShakkimatti(){
    this.shakkimatti = true;
  }

  public boolean annaShakki(){
    return this.shakki;
  }

  public boolean annaShakkimatti(){
    return this.shakkimatti;
  }

  public Nappula annaNappula(int sNo, int sAbc){
    return lauta[sNo][sAbc];
  }

  public void asetaNappula(Nappula nappula, int sNo, int sAbc){
    lauta[sNo][sAbc] = nappula;
 if(nappula.getIkoni() != null){
  nappula.getIkoni().setLocation(84+(sAbc*80), 596-(sNo*80));
 }

 //  nappula.getIkoni().setLocation(105+(sAbc*100), 45+(sNo*100));
  }
  
  public void asetaOhestaLyonti(boolean totuus){
    this.ohestaLyonti = totuus;
  }
  
  public void asetaKohde(Nappula nappula){
    this.kohde = nappula;
  }
  
  public Nappula annaKohde(){
    return this.kohde;
  }
  
  public boolean annaOhestaLyonti(){
    return this.ohestaLyonti;
  }

        /*
   * metodi tarkastaa onko linnoittautuminen mahdollista
   */

  public boolean linnoitusMahdollista(Nappula kuningas, Nappula torni){
    if( kuningas == null || torni == null){
    return false;
    }
   /*
   * tarkastetaan liikkuminen ja shakki-tilanne
   */
    boolean t1 = kuningas.annaLiikkunut();
    boolean t2 = torni.annaLiikkunut();
    boolean t3 = this.annaShakki();
    if (t1 || t2 || t3){
      return false;
    }

    int torniAbc = this.annaSijaintiAbc(torni);
    int torniNo = this.annaSijaintiNo(torni);

              /*
   * PitkÃƒÂ¤ linnoitus. Tarkastetaan onko kuninkaan ja tornin vÃƒÂ¤lissÃƒÂ¤ nappuloita
   * Tarkastetaan siirtyykÃƒÂ¶ Kuningas uhatun ruudun yli
   * tarkastetaan johtaako linnoitus itsemurhaan
   */


    if (torniAbc == 0){
      for (int i =1; i<4; i++){
        if (annaNappula(torniNo,i) != null){
          return false;
        }
      }
      boolean k1 = this.itsemurha(kuningas, torniNo, 3);
      boolean k2 = this.itsemurha(kuningas, torniNo, 2);
      if (k1 || k2){
        return false;
      }
    }
                  /*
   * Lyhyt linnoitus. samat tarkastukset
   */

    if (torniAbc == 7){
      for (int i = 6; i>4; i--){
        if (annaNappula(torniNo,i) != null){
          return false;
        }
      }
      boolean k3 = this.itsemurha(kuningas, torniNo, 5);
      if (k3){
        return false;
      }
    }
    return true;
  }

   /*
   * Liiku-metodi. Parametreina siirtyvÃ¤ Nappula, uusi sijaintirivi, uusi sijaintisarake
   * Jos uudessa ruudussa on toinen nappula, se poistetaan.
   * Metodi ei tarkasta, onko ruudussa samanvÃ¤rinen nappula. TÃ¤mÃ¤ tarkastus tehdÃ¤Ã¤n testaaSiirrot-metodissa.
   * Liiku-metodi olettaa, ettÃ¤ sitÃ¤ kutsutaan vain sallituilla siirroilla
   */

    public void liiku(Nappula nappula, int sijaintiNo, int sijaintiAbc){

    int vanhaNo = annaSijaintiNo(nappula);
    int vanhaAbc = annaSijaintiAbc(nappula);

    if(vanhaNo == sijaintiNo && vanhaAbc == sijaintiAbc){
      return;
    }
    
        /* testataan onko siirto ohestalyonti ja toteutetaan jos on*/
    if (nappula instanceof Sotilas && Math.abs(vanhaNo - sijaintiNo)==1 && Math.abs(vanhaAbc - sijaintiAbc)==1){
      if(lauta[sijaintiNo][sijaintiAbc] == null){
      int kohdeAbc = annaSijaintiAbc(kohde);
      int kohdeNo = annaSijaintiNo(kohde);
      lauta[kohdeNo][kohdeAbc]= null;
      kohde.asetaElossa(false);
    }
    }

    if(lauta[sijaintiNo][sijaintiAbc] != null){
      lauta[sijaintiNo][sijaintiAbc].asetaElossa(false); /* Testaa onko paikalla nappula ja poistaa jos on */
    }
    asetaNappula(nappula, sijaintiNo, sijaintiAbc); ; /* asettaa nappulan uudelle paikalleen*/
    nappula.asetaLiikkunut(); /* pÃƒÂ¤ivittÃƒÂ¤ÃƒÂ¤ nappulan liikkunut -parametrin*/
    if (vanhaNo < 8 && vanhaAbc < 8){
     lauta[vanhaNo][vanhaAbc]= null; /* poistaa nappulan alkuperÃƒÂ¤iseltÃƒÂ¤ paikaltaan*/
    }
    boolean onkoSotilas = nappula instanceof Sotilas;
    if (onkoSotilas && (sijaintiNo == 0 || sijaintiNo == 7)){
      nappula.muutu(this);
    }
    /* testataan onko siirto linnoitus ja toteutetaan jos on*/

    if (nappula instanceof Kuningas && Math.abs(vanhaAbc - sijaintiAbc)>1){
      if(sijaintiNo == 0 && sijaintiAbc == 2){
        liiku(lauta[0][0], 0,3);
      }
      if(sijaintiNo == 0 && sijaintiAbc == 6){
        liiku(lauta[0][7], 0,5);
      }
      if(sijaintiNo == 7 && sijaintiAbc == 2){
        liiku(lauta[7][0], 7,3);
      }
      if(sijaintiNo == 7 && sijaintiAbc == 6){
        liiku(lauta[7][7], 7,5);
      }
      }
    
    /* jos sotilas siirtyy 2 askelta, asetataan ohestalyonti mahdolliseksi. Jos ei, niin poistetaan mahd.*/
    
    if (nappula instanceof Sotilas && Math.abs(vanhaNo - sijaintiNo)>1){
      asetaOhestaLyonti(true);
      asetaKohde(nappula);
    } else { asetaOhestaLyonti(false);
    }
    
  }

     /*
   * testaaSiirrot-metodi. Parametreina nappulan mahdolliset siirrot sekÃ¤ siirtyvÃ¤ nappula.
   * muodollisessa parametrissa saadussa arrayssa 1 merkkaa mahdollista siirtoa.
   * Metodi testaa voidaanko siirrot suorittaa.
   * Testi 1 == onko ruudussa oma nappula. Testi 2 == onko matkalla esteitÃ¤. Metodi ei testaa
   * aiheuttaako siirto itsemurhatilanteen. TÃ¤tÃ¤ varten Peli-luokan tulee kutsua erikseen
   * Metodi palauttaa matriisin, jossa ykkÃ¶nen merkkaa mahdollista siirtoa
   */

 public int[][] testaaSiirrot(int[][] siirrot, Nappula nappula){
    boolean onkoRatsu = nappula instanceof Ratsu; /* Ratsule tarvitaan vain testi 1 */
    int sijaintiNo = annaSijaintiNo(nappula); /* kutsutaan apumetodia (alla) */
    int sijaintiAbc = annaSijaintiAbc(nappula); /* kutsutaan apumetodia (alla) */
    boolean valkoinen = nappula.annaVari(); /* testataan minkÃƒÂ¤ vÃƒÂ¤rinen nappula on siirtymÃƒÂ¤ssÃƒÂ¤ */
    int[][] sallitutSiirrot = new int[8][8]; /* luodaan uusi matriisi sallituista siirroista */

      /*
   * Testataan onko mahdollisissa siirtoruuduissa oma tai vastustajan nappula vai onko ruutu tyhjÃƒÂ¤
   * 0 == ei sallittu, 1 == sallittu, 2 == sallittu - syÃƒÂ¶ vastustajan
   */

    for(int i = 0; i < 8; i++){
      for(int a = 0; a < 8; a++){
        if(siirrot[i][a] != 1){
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen nappulalle */
        } else if(lauta[i][a] == null) {
          sallitutSiirrot[i][a] = 1; /* siirto on mahdollinen nappulalle eikÃƒÂ¤ ruudussa ole toista nappulaa */
        } else if(lauta[i][a].annaVari() != valkoinen) {
          sallitutSiirrot[i][a] = 2; /* siirto on mahdollinen, ruudussa on vastustajan nappula */
        } else {
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen koska ruudussa on oma nappula*/
        }
      }
    }
          /*
   * Testataan onko reitillÃƒÂ¤ muita nappuloita. Ratsulle testiÃƒÂ¤ ei tarvita. Kuljetaan kaikki kahdeksan mahdollista
   * suuntaa. Jos reitillÃƒÂ¤ on matkalla ruutu, johon ei voi siirtyÃƒÂ¤, kulkeminen pÃƒÂ¤ÃƒÂ¤ttyy. Jos reitillÃƒÂ¤ on syÃƒÂ¶tÃƒÂ¤vÃƒÂ¤
   * nappula, kulku pÃƒÂ¤ÃƒÂ¤ttyy. Kuljetut ruudut merkataan numerolla 3.
   */

   int b = sijaintiNo; /* aloitetaan nappulan nykyisestÃƒÂ¤ paikasta */
   int c = sijaintiAbc;

   if(onkoRatsu == false){
     while (b>-1 && b<7 && c>-1 && c<8){ /* jatketaan kulkemista kunnes tullaan laudan reunalle */
       b++; /* ensimmÃƒÂ¤inen kahdeksasta suunnasta */
       if (sallitutSiirrot[b][c] == 0){
         break; /* suuntaan ei voinut kulkea */
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break; /* suunnassa oli syÃƒÂ¶tÃƒÂ¤vÃƒÂ¤ nappula, pidemmÃƒÂ¤lle ei voi mennÃƒÂ¤, merkataan ruutu 3:lla */
       } else {
         sallitutSiirrot[b][c] = 3; /* suuntaan voi kulkea, merkataan ruutu ja jatketaan */
       }
     }

     /* aloitetaan uudestaan ja kuljetaan kaikki kahdeksan suuntaa */
     b = sijaintiNo;
     c = sijaintiAbc;

     while (b>-1 && b<8 && c>-1 && c<7){
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

     b = sijaintiNo;
     c = sijaintiAbc;

     while (b>0 && b<8 && c>-1 && c<8){
       b--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }
       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<8 && c>0 && c<8){
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>0 && b<8 && c>0 && c<8){
       b--;
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<7 && c>-1 && c<7){
       b++;
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<7 && c>0 && c<8){
       b++;
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>0 && b<8 && c>-1 && c<7){
       b--;
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }
   } else {                        /* merkataan hevosen kaikki siirrot 3:lla sillÃƒÂ¤ se voi ylittÃƒÂ¤ÃƒÂ¤ nappuloita */
      for(int i = 0; i < 8; i++){
       for(int a = 0; a < 8; a++){
         if (sallitutSiirrot[i][a] >0){
           sallitutSiirrot[i][a] = 3;
         }
       }
      }
   }
    /* testataan onko linnoitus mahdollista ja merkitÃƒÂ¤ÃƒÂ¤n siirrot kuninkaalle*/

   if(nappula instanceof Kuningas){
     if(linnoitusMahdollista(nappula, annaNappula(0,0))){
       sallitutSiirrot[0][2]=3;
     }
     if(linnoitusMahdollista(nappula, annaNappula(0,7))){
          sallitutSiirrot[0][6]=3;
     }
     if(linnoitusMahdollista(nappula, annaNappula(7,0))){
          sallitutSiirrot[7][2]=3;
     }
     if(linnoitusMahdollista(nappula, annaNappula(7,7))){
          sallitutSiirrot[7][6]=3;
     }
        }

             /*
   * Siivotaan matriisi niin, ettÃƒÂ¤ sallitut siirrot ovat ykkÃƒÂ¶siÃƒÂ¤ ja muut nollia kaikille nappuloille
   */

   for(int i = 0; i < 8; i++){
       for(int a = 0; a < 8; a++){
         if (sallitutSiirrot[i][a] <3){
           sallitutSiirrot[i][a] = 0;
         } else {
           sallitutSiirrot[i][a] = 1;
         }
       }
   }
                /*
   * Poistetaan itsemurhaan johtavat siirrot
   */
   for(int x = 0; x<8; x++){
     for(int y = 0; y<8; y++){
       if(sallitutSiirrot[x][y] == 1 && this.itsemurha(nappula, x, y)){
           sallitutSiirrot[x][y] = 0;
         }
     }
   }
   siirrot[sijaintiNo][sijaintiAbc] = 0;
   return sallitutSiirrot;
  }

            /*
   * itsemurha-metodi. Parametrina siirtyvÃ¤ nappula ja sen uusi paikka koordinaatteina.
   * Metodi luo pelilaudan tilanteesta kopion ja testaa syntyykÃ¶ vastustajalle shakki (false == ei synny)
   */

  public boolean itsemurha(Nappula nappula, int uusiNo, int uusiAbc){
    if (nappula == null){
     return false;
    }
    boolean onkoItsari;
    boolean vari = nappula.annaVari();
    Pelilauta kopioPeli = new Pelilauta(this);  /* kopioi pelin */
    kopioPeli.liikuItsari(nappula, uusiNo, uusiAbc); /* suorittaa siirron kopiopelissÃ¤ */
    kopioPeli.testaaShakkiItsari(vari); /* testaa tuliko vastustajalle shakki */
    onkoItsari = kopioPeli.annaShakki();
    return onkoItsari;
  }

                /*
   * testaaShakki-metodi. Parametrina testattava vari (kuninkaan vari jota mahdollisesti uhataan).
   * palauttaa
   */


  public void testaaShakki(boolean vari){
 /* hakee kuninkaan paikan laudalta */
    int sNoK =0;
    int sAbcK =0;
    for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if(lauta[i][a] instanceof Kuningas && lauta[i][a].annaVari() == vari){
          sNoK = i;
          sAbcK = a;
        }
      }
     }
     /* luo attribuutit testaamista varten */
    int[][] testiSiirrot = new int[8][8];
    int[][] sallitutTestiSiirrot = new int[8][8];

    /* kÃ¤y lÃ¤pi koko laudan ja kaikki sillÃ¤ olevat vastustajan nappulat */
    for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if (lauta[i][a] != null){ /* laudalla on nappula*/
          if (vari != lauta[i][a].annaVari()){ /* nappula on vastustajan */
            testiSiirrot = lauta[i][a].siirrot(this); /* kutsutaan nappulan metodia joka palauttaa mahd. siirrot */
            /* kutsutaan pelilaudan metodia joka testaa voisiko siirrot toteuttaa */
            sallitutTestiSiirrot = testaaSiirrot(testiSiirrot, lauta[i][a]);
            /* testataan voiko joku nappuloista siirtyÃ¤ kuninkaan ruutuun */
            if (sallitutTestiSiirrot[sNoK][sAbcK] == 1){
            asetaShakki(true);
            return;
          }
        }
      }
    }
    }
    asetaShakki(false);
    return;
  }

                /*
   * apumetodi, joka antaa parametrinaan saaman nappulan rivin pelilaudalla
   * Jos nappulaa ei lÃ¶ydy, metodi palauttaa 9
   */

  public int annaSijaintiNo(Nappula nappula){
     for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if(lauta[i][a] == nappula){
          return i;
        }
      }
     }
     return 9;
  }

                 /*
   * apumetodi, joka antaa parametrinaan saaman nappulan sarakkeen pelilaudalla
   * Jos nappulaa ei lÃ¶ydy, metodi palauttaa 9
   */

    public int annaSijaintiAbc(Nappula nappula){
     for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if(lauta[i][a] == nappula){
          return a;
        }
      }
     }
     return 9;
  }

 /*aloittaaa GUIN:n rakentamisen*/
 public static void createGUI(){
  JFrame frame = new JFrame("Shakki");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  Container panel = frame.getContentPane();
  frame.setResizable(false);
  MainPanel.randomizeSquares();
  mainPanel = new MainPanel();
  frame.add(mainPanel);
  mainPanel.setLayout(null);
  frame.setSize(1280, 720);
  JButton multiButton = new JButton("Kaksinpeli");
  JButton singleButton = new JButton("Yksinpeli");
  JButton loadButton= new JButton("Lataa");
  JButton saveButton = new JButton("Tallenna");
  saveButton.setEnabled(false);



  JButton exitButton = new JButton("X");
  ActionListener sulje = new ActionListener() {
      @Override
   public void actionPerformed(ActionEvent e) {
          System.exit(0);
      }
  };

  exitButton.addActionListener(sulje);
  exitButton.setBounds(10,10,45,40);
  mainPanel.add(exitButton);

  ActionListener yksinpeli = new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     if (Main.getTPeli() != null){
       multiButton.setEnabled(false);
       singleButton.setEnabled(false);
       loadButton.setEnabled(false);
       saveButton.setEnabled(true);
     }
         Main.getTPeli().asetaPeliAsetuksilla("yksinpeli");
         Pelilauta.tulosta("Uusi yksinpeli aloitettu.");
         Main.getTPeli().pelaa();
   }
  };

  singleButton.addActionListener(yksinpeli);
  singleButton.setBounds(740,25,100,40);
  mainPanel.add(singleButton);

  ActionListener moninpeli = new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     if (Main.getTPeli() != null){
       multiButton.setEnabled(false);
       singleButton.setEnabled(false);
       loadButton.setEnabled(false);
       saveButton.setEnabled(true);
     }
    Main.getTPeli().asetaPeliAsetuksilla("kaksinpeli");
    Pelilauta.tulosta("Uusi kaksinpeli aloitettu.");
    Main.getTPeli().pelaa();
   }
  };

  multiButton.addActionListener(moninpeli);
  multiButton.setBounds(845,25,120,40);
  mainPanel.add(multiButton);


  ActionListener lataaPeli = new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
     if (Main.getTPeli() != null){
       multiButton.setEnabled(false);
       singleButton.setEnabled(false);
       loadButton.setEnabled(false);
       saveButton.setEnabled(true);
     }
    try {
         Main.getTPeli().lataaPeliAsetuksilla();
         Pelilauta.tulosta("Tallennettua peliï¿½ jatketaan...");
         Main.getTPeli().pelaa();
       }
       catch (IOException er){}

   }
  };

  loadButton.addActionListener(lataaPeli);
  loadButton.setBounds(1065,25,100,40);
  mainPanel.add(loadButton);

  ActionListener tallenna = new ActionListener() {
      @Override
   public void actionPerformed(ActionEvent e) {
    try{
     if (Main.getTPeli() != null){
      Main.getTPeli().tallennaPeli();
     }
    }
    catch(FileNotFoundException er){}
      }
  };


  saveButton.addActionListener(tallenna);
    saveButton.setBounds(1170,25,95,40);
     mainPanel.add(saveButton);

  textBox.setEditable(false);
  scrollPanel.setBounds(740, 500, 532,200);
  DefaultCaret caret = (DefaultCaret)textBox.getCaret();
  caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

  JLabel styleB = new JLabel("Tyyli: "+MainPanel.getStyleName());
    styleB.setBounds(350, 8, 200,20);

  mainPanel.add(styleB);


  mainPanel.add(scrollPanel);

  buildSelectors(mainPanel);
  buildSideLabels(mainPanel);
  frame.pack();
  frame.setVisible(true);
 }

 /*rakentaa 64 nÃ¤kymÃ¤tÃ¶ntÃ¤ nappia ruutujen pÃ¤Ã¤lle jotkat hallisevat siirtoja*/
 public static void buildSelectors(MainPanel mp){
  int xc; int yc;
  int id = -1;
  ActionListener valitseR = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     Ruutu r = ((Ruutu) e.getSource());
     if (Tuomari.onkoVaihe(0)){
       if (Ihmispelaaja.getOmanVarNap()[r.haeY()][r.haeX()] == 1){
       Nappula nap = Peli.getSLauta().annaNappula(r.haeY(), r.haeX());
       Pelilauta pel = Ihmispelaaja.getTLauta();
       r.ekavalikoi();
       if( nap != null){
       Nappula.setValittu(nap);
       Tuomari.asetaVaihe(1);
       Ihmispelaaja.getTPelaaja().liikuttaa(Ihmispelaaja.getTLauta());
      } else{ Tuomari.julistaLaittomaksi(); }
    } else{ Tuomari.julistaLaittomaksi(); }
    }
    else if (Tuomari.onkoVaihe(1)){
     if (r != Ruutu.haeEkavalittu()){
      if (r.onkoSallitu()){
       r.valikoi();
       Tuomari.asetaVaihe(2);
       Ihmispelaaja.getTPelaaja().liikuttaa(Ihmispelaaja.getTLauta());
      } else{
       Tuomari.julistaLaittomaksi();
      }
     } else{
      /*Nappulan valikointi resetoituu*/
      tulosta("Tuomari: Nappulan vaihto.");
      Ruutu.resetValittu();
      Pelilauta.normaalisoiRuudut();
      Tuomari.asetaVaihe(0);
     }
    }
    }
  };
  for(int y = 0; y < 8; y++){
   xc = 80; yc = 600-(y*80);
   for(int x = 0; x < 8; x++){
     id++;
     ruutuValikko.put(id, new Ruutu(id, y, x));
     ruutuValikko.get(id).addActionListener(valitseR);
     ruutuValikko.get(id).setBounds(xc,yc,80,80);

     ruutuValikko.get(id).setContentAreaFilled(false);
        mp.add(ruutuValikko.get(id));
     xc += 80;
    }

   }


 }

 public static void varitaRuudut(int[][] ruudut){
  int xd = 0;
  int id = 0;
  for (int x = 0; x < ruudut.length; x++){
   for (int y = 0; y < ruudut[x].length; y++){
    if (ruudut[x][y] == 1){
     xd++;
     colorizeRuutu(ruutuValikko.get(id));
     valitutRuudut.add(ruutuValikko.get(id));
     ((Ruutu) ruutuValikko.get(id)).asetaSallitu(true);
    }
    id++;
   }
  }
  tulosta("Mahdollisten siirtojen lukumÃ¤Ã¤rÃ¤:"+" "+Integer.toString(xd));
 }


 public static boolean onkoSiirtoja(int[][] ruudut){
  for (int x = 0; x < ruudut.length; x++){
   for (int y = 0; y < ruudut[x].length; y++){
    if (ruudut[x][y] == 1){ return true; }
   }
  }
  return false;
 }


 public static void normaalisoiRuudut(){
  for(JButton x: valitutRuudut){
   normalizeRuutu(x);
  }
  valitutRuudut.clear();
  Ruutu.resetEkavalittu();
 }

 public static void colorizeRuutu(JButton b){
  b.setContentAreaFilled(true);
 }

 public static void normalizeRuutu(JButton b){
  b.setContentAreaFilled(false);
  ((Ruutu) b).asetaSallitu(false);
 }

 /*Rakentaa sivu merkit*/
 public static void buildSideLabels(MainPanel mp){
  char[] ruutuK = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};                  //ruudun kirjain
  Dimension sizexc;
  for (int x = 1; x <= 8; x++){
   ruutuMerkki.put(x, new JLabel(Character.toString(ruutuK[x-1])));
   mp.add(ruutuMerkki.get(x));
   ruutuMerkki.get(x).setFont(new Font(ruutuMerkki.get(x).getFont().getName(), 1,20));
   sizexc = ruutuMerkki.get(x).getPreferredSize();
   ruutuMerkki.get(x).setBounds(80, 80, sizexc.width, sizexc.height);
   ruutuMerkki.get(x).setLocation(30+(80*x), 680);
   ruutuMerkki.put(x+8, new JLabel(Integer.toString(9-x)));
   mp.add(ruutuMerkki.get(x+8));
   ruutuMerkki.get(x+8).setFont(new Font(ruutuMerkki.get(x+8).getFont().getName(), 1,20));
   sizexc = ruutuMerkki.get(x+8).getPreferredSize();
   ruutuMerkki.get(x+8).setBounds(80, 80, sizexc.width, sizexc.height);
   ruutuMerkki.get(x+8).setLocation(60, 72+(80*(x-1)));
  }
 }


public static void tulosta(String s){
 textBox.append(s+"\n");

}

public static void tulosta(String s, int n){
 textBox.append(s+Integer.toString(n)+"\n");
}

public void liikuItsari(Nappula nappula, int sijaintiNo, int sijaintiAbc){

    int vanhaNo = annaSijaintiNo(nappula);
    int vanhaAbc = annaSijaintiAbc(nappula);
    
    if (nappula instanceof Sotilas && Math.abs(vanhaNo - sijaintiNo)==1 && Math.abs(vanhaAbc - sijaintiAbc)==1){
      if(lauta[sijaintiNo][sijaintiAbc] == null){
      int kohdeAbc = annaSijaintiAbc(kohde);
      int kohdeNo = annaSijaintiNo(kohde);
      lauta[kohdeNo][kohdeAbc]= null;
      }
    }
    
    lauta[sijaintiNo][sijaintiAbc] = nappula;  /* asettaa nappulan uudelle paikalleen*/
    lauta[vanhaNo][vanhaAbc] = null; /* poistaa nappulan alkuperÃƒÂ¤iseltÃƒÂ¤ paikaltaan*/

    /* testataan onko siirto linnoitus ja toteutetaan jos on*/

    if (nappula instanceof Kuningas && Math.abs(vanhaAbc - sijaintiAbc)>1){
      if(sijaintiNo == 0 && sijaintiAbc == 2){
        lauta[0][3] = lauta[0][0];
        lauta[0][0] = null;
      }
      if(sijaintiNo == 0 && sijaintiAbc == 6){
        lauta[0][5] = lauta[0][7];
        lauta[0][7] = null;
      }
      if(sijaintiNo == 7 && sijaintiAbc == 2){
        lauta[7][3] = lauta[7][0];
        lauta[7][0] = null;
      }
      if(sijaintiNo == 7 && sijaintiAbc == 6){
        lauta[7][5] = lauta[7][7];
        lauta[7][7] = null;
      }
      }
  }
 public void testaaShakkiItsari(boolean vari){
 /* hakee kuninkaan paikan laudalta */
    int sNoK =0;
    int sAbcK =0;
    for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if(lauta[i][a] instanceof Kuningas && lauta[i][a].annaVari() == vari){
          sNoK = i;
          sAbcK = a;
        }
      }
     }
     /* luo attribuutit testaamista varten */
    int[][] testiSiirrot = new int[8][8];
    int[][] sallitutTestiSiirrot = new int[8][8];

    /* kÃƒÆ’Ã‚Â¤y lÃƒÆ’Ã‚Â¤pi koko laudan ja kaikki sillÃƒÆ’Ã‚Â¤ olevat vastustajan nappulat */
    for (int i = 0; i < 8; i++){
      for (int a = 0; a < 8; a++){
        if (lauta[i][a] != null){ /* laudalla on nappula*/
          if (vari != lauta[i][a].annaVari()){ /* nappula on vastustajan */
            testiSiirrot = lauta[i][a].siirrot(this); /* kutsutaan nappulan metodia joka palauttaa mahd. siirrot */
            /* kutsutaan pelilaudan metodia joka testaa voisiko siirrot toteuttaa */
            sallitutTestiSiirrot = testaaSiirrotItsari(testiSiirrot, lauta[i][a]);
            /* testataan voiko joku nappuloista siirtyÃƒÆ’Ã‚Â¤ kuninkaan ruutuun */
            if (sallitutTestiSiirrot[sNoK][sAbcK] == 1){
            asetaShakki(true);
            return;
          }
        }
      }
    }
    }
    asetaShakki(false);
    return;
  }
public int[][] testaaSiirrotItsari(int[][] siirrot, Nappula nappula){
    boolean onkoRatsu = nappula instanceof Ratsu; /* Ratsule tarvitaan vain testi 1 */
    int sijaintiNo = annaSijaintiNo(nappula); /* kutsutaan apumetodia (alla) */
    int sijaintiAbc = annaSijaintiAbc(nappula); /* kutsutaan apumetodia (alla) */
    boolean valkoinen = nappula.annaVari(); /* testataan minkÃƒÆ’Ã‚Â¤ vÃƒÆ’Ã‚Â¤rinen nappula on siirtymÃƒÆ’Ã‚Â¤ssÃƒÆ’Ã‚Â¤ */
    int[][] sallitutSiirrot = new int[8][8]; /* luodaan uusi matriisi sallituista siirroista */

      /*
   * Testataan onko mahdollisissa siirtoruuduissa oma tai vastustajan nappula vai onko ruutu tyhjÃƒÆ’Ã‚Â¤
   * 0 == ei sallittu, 1 == sallittu, 2 == sallittu - syÃƒÆ’Ã‚Â¶ vastustajan
   */

    for(int i = 0; i < 8; i++){
      for(int a = 0; a < 8; a++){
        if(siirrot[i][a] != 1){
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen nappulalle */
        } else if(lauta[i][a] == null) {
          sallitutSiirrot[i][a] = 1; /* siirto on mahdollinen nappulalle eikÃƒÆ’Ã‚Â¤ ruudussa ole toista nappulaa */
        } else if(lauta[i][a].annaVari() != valkoinen) {
          sallitutSiirrot[i][a] = 2; /* siirto on mahdollinen, ruudussa on vastustajan nappula */
        } else {
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen koska ruudussa on oma nappula*/
        }
      }
    }
          /*
   * Testataan onko reitillÃƒÆ’Ã‚Â¤ muita nappuloita. Ratsulle testiÃƒÆ’Ã‚Â¤ ei tarvita. Kuljetaan kaikki kahdeksan mahdollista
   * suuntaa. Jos reitillÃƒÆ’Ã‚Â¤ on matkalla ruutu, johon ei voi siirtyÃƒÆ’Ã‚Â¤, kulkeminen pÃƒÆ’Ã‚Â¤ÃƒÆ’Ã‚Â¤ttyy. Jos reitillÃƒÆ’Ã‚Â¤ on syÃƒÆ’Ã‚Â¶tÃƒÆ’Ã‚Â¤vÃƒÆ’Ã‚Â¤
   * nappula, kulku pÃƒÆ’Ã‚Â¤ÃƒÆ’Ã‚Â¤ttyy. Kuljetut ruudut merkataan numerolla 3.
   */
   int b = sijaintiNo; /* aloitetaan nappulan nykyisestÃƒÂ¤ paikasta */
   int c = sijaintiAbc;

   if(onkoRatsu == false){
     while (b>-1 && b<7 && c>-1 && c<8){ /* jatketaan kulkemista kunnes tullaan laudan reunalle */
       b++; /* ensimmÃƒÂ¤inen kahdeksasta suunnasta */
       if (sallitutSiirrot[b][c] == 0){
         break; /* suuntaan ei voinut kulkea */
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break; /* suunnassa oli syÃƒÂ¶tÃƒÂ¤vÃƒÂ¤ nappula, pidemmÃƒÂ¤lle ei voi mennÃƒÂ¤, merkataan ruutu 3:lla */
       } else {
         sallitutSiirrot[b][c] = 3; /* suuntaan voi kulkea, merkataan ruutu ja jatketaan */
       }
     }

 /* aloitetaan uudestaan ja kuljetaan kaikki kahdeksan suuntaa */
     b = sijaintiNo;
     c = sijaintiAbc;

     while (b>-1 && b<8 && c>-1 && c<7){
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

     b = sijaintiNo;
     c = sijaintiAbc;

     while (b>0 && b<8 && c>-1 && c<8){
       b--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }
       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<8 && c>0 && c<8){
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>0 && b<8 && c>0 && c<8){
       b--;
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<7 && c>-1 && c<7){
       b++;
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>-1 && b<7 && c>0 && c<8){
       b++;
       c--;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }

       b = sijaintiNo;
       c = sijaintiAbc;

       while (b>0 && b<8 && c>-1 && c<7){
       b--;
       c++;
       if (sallitutSiirrot[b][c] == 0){
         break;
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break;
       } else {
         sallitutSiirrot[b][c] = 3;
       }
     }
   } else {                        /* merkataan hevosen kaikki siirrot 3:lla sillÃƒÂ¤ se voi ylittÃƒÂ¤ÃƒÂ¤ nappuloita */
      for(int i = 0; i < 8; i++){
       for(int a = 0; a < 8; a++){
         if (sallitutSiirrot[i][a] >0){
           sallitutSiirrot[i][a] = 3;
         }
       }
      }
   }
             /*
   * Siivotaan matriisi niin, ettÃƒÆ’Ã‚Â¤ sallitut siirrot ovat ykkÃƒÆ’Ã‚Â¶siÃƒÆ’Ã‚Â¤ ja muut nollia kaikille nappuloille
   */

   for(int i = 0; i < 8; i++){
       for(int a = 0; a < 8; a++){
         if (sallitutSiirrot[i][a] <3){
           sallitutSiirrot[i][a] = 0;
         } else {
           sallitutSiirrot[i][a] = 1;
         }
       }
   }

return sallitutSiirrot;
}

}
