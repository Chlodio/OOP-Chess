import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

abstract class Nappula{
  private static int id = -1;                                     //kuinka monta nappulaa on luotu
  protected static Nappula[] lista = new Nappula[17];             //Array nappuloista
  protected static Nappula valittu;
  protected static ArrayList<Nappula> valSuodut = new ArrayList<>();
  protected static ArrayList<Nappula> musSuodut = new ArrayList<>();

  protected boolean vari;
  protected boolean elossa;
  protected int arvo;
  protected boolean liikkunut;
  protected String symbooli;                                      //index 0 = valkoinen, index 1 = musta
  protected JLabel ikoni;

  /*konstrukti*/
  public Nappula(boolean b){
      id++;
	  this.vari = b;
	  Nappula.luoNappulaGui(this);
  }

  /* siirtaa Nappulan ikonin laudan sivuun*/
	public void poistaLaudalta(){
		if(this.annaVari()){
			this.getIkoni().setLocation(
				749+(63*(valSuodut.size()%8)),
				325+((valSuodut.size()/8)*75)
			);
			valSuodut.add(this);
		}
		else{
			this.getIkoni().setLocation(
				749+(63*(musSuodut.size()%8)),
				150+((musSuodut.size()/8)*75)
			);
			musSuodut.add(this);
		}
	}

  /* linkitetään nappula sen graafiseen esitykseen*/
  public void asetaGUI(JLabel label){
      this.ikoni = label;
  }

  /* paluttaa Nappulan ikonin*/
  public JLabel getIkoni(){
    return this.ikoni;
  }

  /* palauttaa symboolin joka esittää nappulaa laudalla*/
  public String getSymbooli(){
    return this.symbooli;
  }

  /* palauttaa staattisen valittu-muuttujan */
  public static Nappula getValittu(){
    return valittu;
  }

  /* asettaa parametrin staattiseksi valittu-muuttujaksi*/
  public static void setValittu(Nappula n){
    valittu = n;
  }

 /* rakentaa Nappula-oliolle GUI-esityksen*/
  public static void luoNappulaGui(Nappula n){
    Dimension koko;
	JLabel label = new JLabel(n.getSymbooli());
    n.asetaGUI(label);
	label.setForeground(Color.black);
    n.getIkoni().setFont(new Font(label.getFont().getName(), 1, 75));
    koko = n.getIkoni().getPreferredSize();
    n.getIkoni().setBounds(80, 80, koko.width, koko.height);
    Pelilauta.getMainPanel().addInto(n.getIkoni());

  }

  public boolean annaVari(){
     return this.vari;
  }
  abstract void asetaElossa(boolean elossa);
  abstract boolean annaElossa();
  abstract int annaArvo();
  abstract void asetaLiikkunut();
  abstract boolean annaLiikkunut();
  abstract int[][] siirrot(Pelilauta lauta);
  abstract void muutu(Pelilauta lauta);
	abstract String annaNimi();
}
