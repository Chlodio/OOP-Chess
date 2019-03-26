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
	 peli.asetaPeliAsetuksilla
	 peli.pelaa();

  //java.lang.System.exit(0);		//tarvitaanko me sulkeutumista, jos GUI hallitsee sitä? //Ei tarvita
 }

 public static Peli getTPeli(){
	 return TPeli;
 }

}
