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
///      lista[id] = this;
	//  Nappula.luoNappulaGui(this);
  }
	public void poistaLaudalta(){
		if(!this.annaVari()){
			this.getIkoni().setLocation(
				920+(38*valSuodut.size()),
				500
			);
			valSuodut.add(this);
		}
		else{
			this.getIkoni().setLocation(
				920+(38*musSuodut.size()),
				400
			);
			musSuodut.add(this);
		}
	}

  /*Linkitetään nappula sen graafiseen esitykseen*/
  public void asetaGUI(JLabel label){
      this.ikoni = label;
  }

  public JLabel getIkoni(){
    return this.ikoni;
  }

  public String getSymbooli(){
    return this.symbooli;
  }

  public static Nappula getValittu(){
    return valittu;
  }

  public static void setValittu(Nappula n){
    valittu = n;
  }

  public static void luoNappulaGui(Nappula n){
    Dimension koko;
	JLabel label = new JLabel(n.getSymbooli());
    n.asetaGUI(label);
	label.setForeground(Color.black);
    n.getIkoni().setFont(new Font(label.getFont().getName(), 1, 100));
    koko = n.getIkoni().getPreferredSize();
    n.getIkoni().setBounds(100, 100, koko.width, koko.height);
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
