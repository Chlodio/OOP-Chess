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
  boolean tallennettuPeli;
  try {
    tallennettuPeli = peli.tallennettuToimivaPeli();
  }
  catch (IOException e){
    tallennettuPeli = false;
  }
  if (args.length < 2 || args.length > 2){
    System.out.println("Aseta haluamasi asetukset.");
    System.exit(0);
  }
  else if (!tallennettuPeli || args[1].equals("uusipeli")){
    if (args[0].equals("yksinpeli")){
      peli.asetaPeliAsetuksilla("yksinpeli");
      Pelilauta.tulosta("Uusi yksinpeli aloitettu.");
    }
    else{
      peli.asetaPeliAsetuksilla("kaksinpeli");
      Pelilauta.tulosta("Uusi kaksinpeli aloitettu.");
    }
  }
  else if (tallennettuPeli && args[1].equals("tallennettupeli")){
    try {
      peli.lataaPeliAsetuksilla();
      Pelilauta.tulosta("Tallennettua peliä jatketaan...");
    }
    catch (IOException e){}
  }
  else {
    Pelilauta.tulosta("Uusi kaksinpeli aloitettu.");
    peli.asetaPeliAsetuksilla("kaksinpeli");
  }
  peli.pelaa();
  try {
    Thread.sleep(5000);
  }
  catch(InterruptedException e) {
    System.out.println("got interrupted!");
  }

 }

 public static Peli getTPeli(){
  return TPeli;
 }

}
