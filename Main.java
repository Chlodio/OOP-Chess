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
 public static boolean tallennettuPeli;
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

//  peli.pelaa();
//  try {
//    Thread.sleep(5000);
//  }
//  catch(InterruptedException e) {
 //   System.out.println("got interrupted!");
 // }

 }

 public static Peli getTPeli(){
  return TPeli;
 }

}
