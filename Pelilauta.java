import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
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
 
   /*
   * Huom1: Luokka Pelilauta ei sisällä suunnitelmasta poiketen metodia shakkimatti. Ehdotan, että tämä testataan Peli-
   * luokassa. Shakkimatti syntyy, jos mikään nappula ei palauta sallittuja siirtoja. Shakkimatti syntyy myös
   * jos kaikki sallitut siirrot johtavat itsemurhaan. Mielestäni nämä testit on yksinkertaisempi toteuttaa Peli-
   * luokassa.
   * 
   * Huom2: Olen aika epävarma tuon itsemurha-metodin toimivuudesta, mutta en oikein keksinyt helppoa tapaa testata
jonkun verran    * sitä
   * 
   * Huom3: Koodissa on varmasti vielä paljon virheitä...
   */

class Pelilauta{
   /*Näkymötön kerros nappuloita joiden avulla siirrot toimivat*/
   public static Map<Integer, JButton> ruutuValikko = new HashMap<>();
   /*Mrkki labellit sivuille*/
   public static Map<Integer, JLabel> ruutuMerkki = new HashMap<>();
   /*
   * Pelilaudan attribuutit alla
   */
  
  protected Nappula[][] lauta;
  protected boolean shakki;
  protected boolean shakkimatti;
  
    /*
   * Konstruktori
   */
  
  public Pelilauta(){
    this.lauta = new Nappula[8][8];
    this.shakki = false;
    this.shakkimatti = false;
  }
  
    /*
   * Konstruktori, joka luo pelistä kopion. Käytetään mahdollisten siirtojen simulointiin ja itsemurhan testaukseen
   */
  
  public Pelilauta(Pelilauta kopio){
    this.lauta = kopio.lauta;
    this.shakki = kopio.shakki;
    this.shakkimatti = kopio.shakkimatti;
  }
  
      /*
   * asetus ja tarkastus-metodit
   */
  
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
  
   /*
   * Liiku-metodi. Parametreina siirtyvä Nappula, uusi sijaintirivi, uusi sijaintisarake
   * Jos uudessa ruudussa on toinen nappula, se poistetaan.
   * Metodi ei tarkasta, onko ruudussa samanvärinen nappula. Tämä tarkastus tehdään testaaSiirrot-metodissa.
   * Liiku-metodi olettaa, että sitä kutsutaan vain sallituilla siirroilla
   */
  
  public void liiku(Nappula nappula, int sijaintiNo, int sijaintiAbc){
    
    if(lauta[sijaintiNo][sijaintiAbc] != null){
      lauta[sijaintiNo][sijaintiAbc].asetaElossa(false); /* Testaa onko paikalla nappula ja poistaa jos on */
    }
    lauta[sijaintiNo][sijaintiAbc] = nappula; /* asettaa nappulan uudelle paikalleen*/
  }
  
     /*
   * testaaSiirrot-metodi. Parametreina nappulan mahdolliset siirrot sekä siirtyvä nappula. 
   * muodollisessa parametrissa saadussa arrayssa 1 merkkaa mahdollista siirtoa.
   * Metodi testaa voidaanko siirrot suorittaa. 
   * Testi 1 == onko ruudussa oma nappula. Testi 2 == onko matkalla esteitä. Metodi ei testaa
   * aiheuttaako siirto itsemurhatilanteen. Tätä varten Peli-luokan tulee kutsua erikseen
   * Metodi palauttaa matriisin, jossa ykkönen merkkaa mahdollista siirtoa
   */
  
  public int[][] testaaSiirrot(int[][] siirrot, Nappula nappula){
    boolean onkoRatsu = nappula instanceof Ratsu; /* Ratsule tarvitaan vain testi 1 */
    int sijaintiNo = annaSijaintiNo(nappula); /* kutsutaan apumetodia (alla) */
    int sijaintiAbc = annaSijaintiAbc(nappula); /* kutsutaan apumetodia (alla) */
    boolean valkoinen = nappula.annaVari(); /* testataan minkä värinen nappula on siirtymässä */
    int[][] sallitutSiirrot = new int[8][8]; /* luodaan uusi matriisi sallituista siirroista */
    
      /*
   * Testataan onko mahdollisissa siirtoruuduissa oma tai vastustajan nappula vai onko ruutu tyhjä
   * 0 == ei sallittu, 1 == sallittu, 2 == sallittu - syö vastustajan
   */
    
    for(int i = 0; i < 8; i++){
      for(int a = 0; a < 8; a++){
        if(siirrot[i][a] != 1){
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen nappulalle */
        } else if(lauta[i][a] == null) {
          sallitutSiirrot[i][a] = 1; /* siirto on mahdollinen nappulalle eikä ruudussa ole toista nappulaa */
        } else if(lauta[i][a].annaVari != valkoinen) {
          sallitutSiirrot[i][a] = 2; /* siirto on mahdollinen, ruudussa on vastustajan nappula */
        } else {
          sallitutSiirrot[i][a] = 0; /* siirto ei mahdollinen koska ruudussa on oma nappula*/
        }
      }
    }
          /*
   * Testataan onko reitillä muita nappuloita. Ratsulle testiä ei tarvita. Kuljetaan kaikki kahdeksan mahdollista
   * suuntaa. Jos reitillä on matkalla ruutu, johon ei voi siirtyä, kulkeminen päättyy. Jos reitillä on syötävä
   * nappula, kulku päättyy. Kuljetut ruudut merkataan numerolla 3.
   */
    
   int b = sijaintiNo; /* aloitetaan nappulan nykyisestä paikasta */
   int c = sijaintiAbc;   
  
   if(onkoRatsu == false){
     while (b>0 && b<7 && c>0 && c<7){ /* jatketaan kulkemista kunnes tullaan laudan reunalle */
       b++; /* ensimmäinen kahdeksasta suunnasta */
       if (sallitutSiirrot[b][c] == 0){
         break; /* suuntaan ei voinut kulkea */
       } else if (sallitutSiirrot[b][c] == 2){
         sallitutSiirrot[b][c] = 3;
         break; /* suunnassa oli syötävä nappula, pidemmälle ei voi mennä, merkataan ruutu 3:lla */
       } else {
         sallitutSiirrot[b][c] = 3; /* suuntaan voi kulkea, merkataan ruutu ja jatketaan */
       }
     }
     
     /* aloitetaan uudestaan ja kuljetaan kaikki kahdeksan suuntaa */
     b = sijaintiNo;
     c = sijaintiAbc;
     
     while (b>0 && b<7 && c>0 && c<7){
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
     
     while (b>0 && b<7 && c>0 && c<7){
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
     
       while (b>0 && b<7 && c>0 && c<7){
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
     
       while (b>0 && b<7 && c>0 && c<7){
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
     
       while (b>0 && b<7 && c>0 && c<7){
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
     
       while (b>0 && b<7 && c>0 && c<7){
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
     
       while (b>0 && b<7 && c>0 && c<7){
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
   } else {                        /* merkataan hevosen kaikki siirrot 3:lla sillä se voi ylittää nappuloita */
      for(int i = 0; i < 8; i++){ 
       for(int a = 0; a < 8; a++){
         if (sallitutSiirrot[i][a] >0){
           sallitutSiirrot[i][a] = 3;
         }
       }
      }
   }
   
             /*
   * Siivotaan matriisi niin, että sallitut siirrot ovat ykkösiä ja muut nollia kaikille nappuloille
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
  
            /*
   * itsemurha-metodi. Parametrina siirtyvä nappula ja sen uusi paikka koordinaatteina.
   * Metodi luo pelilaudan tilanteesta kopion ja testaa syntyykö vastustajalle shakki (false == ei synny)
   */
  
  public boolean itsemurha(Nappula nappula, int uusiNo, int uusiAbc){
    boolean onkoItsari;
    boolean vari = nappula.annaVari();
    Pelilauta kopioPeli = new Pelilauta(this);  /* kopio pelin */
    kopioPeli.liiku(nappula, uusiNo, uusiAbc); /* suorittaa siirron kopiopelissä */
    kopioPeli.testaaShakki(vari); /* testaa tuliko vastustajalle shakki */
    onkoItsari = kopioPeli.annaShakki();
    return onkoItsari;
  }
    
                /*
   * testaaShakki-metodi. Parametrina testattava vari (kuninkaan vari jota mahdollisesti uhataan).
   * palauttaa
   */
      
    
  public void testaaShakki(boolean vari){
 /* hakee kuninkaan paikan laudalta */
    int sNoK;
    int sAbcK;
    for (int i = 0; i < 8; i++){
      for (int a = 0; i < 8; a++){
        if(lauta[i][a] instanceof Kuningas && lauta[i][a].annaVari() == vari){
          sNoK = i;
          sAbcK = a;
        }
      }
     }
     /* luo attribuutit testaamista varten */
    int[][] testiSiirrot = new int[8][8];
    int[][] sallitutTestiSiirrot = new int[8][8];
    
    /* käy läpi koko laudan ja kaikki sillä olevat vastustajan nappulat */
    for (int i = 0; i < 8; i++){
      for (int a = 0; i < 8; a++){
        if (lauta[i][a] != null){ /* laudalla on nappula*/
          if (vari != lauta[i][a].annaVari()){ /* nappula on vastustajan */
            testiSiirrot = lauta[i][a].annaSiirrot(); /* kutsutaan nappulan metodia joka palauttaa mahd. siirrot */
            /* kutsutaan pelilaudan metodia joka testaa voisiko siirrot toteuttaa */
            sallitutTestiSiirrot = testaaSiirrot(testiSiirrot, lauta[i][a]);
            /* testataan voiko joku nappuloista siirtyä kuninkaan ruutuun */
            if (sallitutTestiSiirrot[sNoK][sAbcK] == 3){
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
   * Jos nappulaa ei löydy, metodi palauttaa 9
   */

  public int annaSijaintiNo(Nappula nappula){
     for (int i = 0; i < 8; i++){
      for (int a = 0; i < 8; a++){
        if(lauta[i][a] == nappula){
          return i;
        }
      }
     }
     return 9;
  }
  
                 /*
   * apumetodi, joka antaa parametrinaan saaman nappulan sarakkeen pelilaudalla
   * Jos nappulaa ei löydy, metodi palauttaa 9
   */
  
    public int annaSijaintiAbc(Nappula nappula){
     for (int i = 0; i < 8; i++){
      for (int a = 0; i < 8; a++){
        if(nappula[i][a] == nappula){
          return a;
        }
      }
     }
     return 9;
  }  

	/*aloittaaa GUIN:n rakentamisen*/
	public static void createGUI(){
		JFrame frame = new JFrame();
		frame.setSize(1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container panel = frame.getContentPane();
		MainPanel mainPanel = new MainPanel();
		frame.add(mainPanel);
		frame.pack();
		JLabel label = new JLabel("♜");                                		//väliaikainen label
		mainPanel.add(label);
		mainPanel.setLayout(null);
		label.setFont(new Font(label.getFont().getName(), 1, 100));
		Dimension size = label.getPreferredSize();
		label.setBounds(100, 100, size.width, size.height);
		label.setLocation(105, 45);
		JButton exitButton=new JButton("X");															//sulkemis nappi

		ActionListener acLiX = new ActionListener() {
		    @Override
			public void actionPerformed(ActionEvent e) {
				label.setLocation(105, label.getLocation().y+100);
		    }
		};
		
		ActionListener acLi = new ActionListener() {
		    @Override
			public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		};
		
		exitButton.addActionListener(acLi);
	    exitButton.setBounds(25,25,45,40);
    	mainPanel.add(exitButton);
		buildSelectors(mainPanel, acLiX);
		frame.setVisible(true);
	}

	/*rakentaa 64 näkymätöntä nappia ruutujen päälle jotkat hallisevat siirtoja*/
	public static void buildSelectors(MainPanel mp, ActionListener al){
		int xc; int yc;
		int id = 0;
		for(int x = 0; x < 8; x++){
			xc = 100; yc = 50+(x*100);
			for(int z = 0; z < 4; z++){
				for(int y = 0; y < 2; y++){
					id++;
					ruutuValikko.put(id, new JButton());
					ruutuValikko.get(id).addActionListener(al);
					ruutuValikko.get(id).setBounds(xc,yc,100,100);
					ruutuValikko.get(id).setContentAreaFilled(false);
			    	mp.add(ruutuValikko.get(id));
					System.out.println(xc+" "+yc);
					xc += 100;
				}
			}
		}
	}

	/*Rakentaa sivu merkit*/
	public static void buildSideLabels(MainPanel mp){
		char[] ruutuK = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};																		//ruudun kirjain
		Dimension sizexc;
		for (int x = 1; x <= 8; x++){
			ruutuMerkki.put(x, new JLabel(Character.toString(ruutuK[x-1])));
			mp.add(ruutuMerkki.get(x));
			ruutuMerkki.get(x).setFont(new Font(ruutuMerkki.get(x).getFont().getName(), 1,25));
			sizexc = ruutuMerkki.get(x).getPreferredSize();
			ruutuMerkki.get(x).setBounds(100, 100, sizexc.width, sizexc.height);
			ruutuMerkki.get(x).setLocation(38+(100*x), 850);
			ruutuMerkki.put(x+8, new JLabel(Integer.toString(9-x)));
			mp.add(ruutuMerkki.get(x+8));
			ruutuMerkki.get(x+8).setFont(new Font(ruutuMerkki.get(x+8).getFont().getName(), 1,25));
			sizexc = ruutuMerkki.get(x+8).getPreferredSize();
			ruutuMerkki.get(x+8).setBounds(100, 100, sizexc.width, sizexc.height);
			ruutuMerkki.get(x+8).setLocation(75, 91+(100*(x-1)));
		}
	}

}
  
