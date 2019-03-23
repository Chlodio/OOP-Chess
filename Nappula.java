import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

abstract class Nappula{
  private static int id = -1;                                     //kuinka monta nappulaa on luotu
  protected static Nappula[] lista = new Nappula[17];             //Array nappuloista
  protected static Nappula valittu;

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

  abstract boolean annaVari();
  abstract void asetaElossa(boolean elossa);
  abstract boolean annaElossa();
  abstract int annaArvo();
  abstract void asetaLiikkunut();
  abstract boolean annaLiikkunut();
  abstract int[][] siirrot(Pelilauta lauta);
  abstract void muutu(Pelilauta lauta);
	abstract String annaNimi();
}
