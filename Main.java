import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


class Main{
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				Lauta.createGUI();
			}
		});
	}
}
