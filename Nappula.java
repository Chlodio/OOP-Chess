import javax.swing.JLabel;

abstract class Nappula{
  protected boolean vari;
  protected boolean elossa;
  protected int arvo;
  protected boolean liikkunut;
  protected String symbooli;                          //index 0 = valkoinen, index 1 = musta
  protected JLabel ikoni;
  
  /*konstrukti*/
  public Nappula(){
    //pitää saada koordinaatit tänne, superin kautta olis kiva
    //Pelilauta.luoEsite(this, /*ruutu*/);
  }
  
  /*Linkitetään nappula sen graafiseen esitykseen*/
  public void asetaGUI(JLabel label){
      this.ikoni = label;
  }
     
  abstract boolean annaVari();
  abstract void asetaElossa(boolean elossa);
  abstract boolean annaElossa();
  abstract int annaArvo();
  abstract void asetaLiikkunut();
  abstract boolean annaLiikkunut();
  abstract int[][] siirrot(Pelilauta lauta);
  abstract void muutu(Pelilauta lauta);
}
