import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;


class Main{
	private static Peli TPeli;
 public static void main(String[] args){
  SwingUtilities.invokeLater(new Runnable() {
   public void run(){
    Pelilauta.createGUI();
   }
  });
  Peli peli = new Peli();
  TPeli = peli;
  boolean tallennettuPeli = false;
  try {
    tallennettuPeli = peli.tallennettuToimivaPeli();
  }
  catch (IOException e) {
    System.out.println("Tallennettua tiedostoa ei ole");
  }
  if (tallennettuPeli){
    if (1 == 1){  /*valinta on tallennettu peli*/
      try{
        peli.lataaPeliAsetuksilla();
      }
      catch (IOException e){
        System.out.println("Ei pitäisi päästä tänne asti");
	}
    }
  }
  else {
   peli.asetaPeliAsetuksilla();
  }
  peli.pelaa();

  //java.lang.System.exit(0);		//tarvitaanko me sulkeutumista, jos GUI hallitsee sitä?
 }

 public static Peli getTPeli(){
	 return TPeli;
 }

}
