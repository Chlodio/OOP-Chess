import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class MainPanel extends JPanel{

	public MainPanel() {}

	public Dimension getPreferredSize(){ return new Dimension(1280, 720); }

	public void paintComponent(Graphics g){


		/*Rakentaa valkoisen taustan GUI:ille;
		vaikka ilmanan tätäkin pärjää, swing haluaa ohjeet miltä laudan pitäisi näyttää*/
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);

		/*Rakentaa shakki ruudut*/
		String[] boardColors = {"#990033", "#BFC1C2", "#990033"};
		int xc; int yc;
		boolean squareColor = true;
		for(int x = 0; x < 8; x++){
			xc = 80; yc = 40+(x*80);
			for(int z = 0; z < 4; z++){
				for(int y = 0; y < 2; y++){
					g.setColor(Color.decode("#000000"));
					g.drawRect(xc,yc, 80, 80);
					if (squareColor){				g.setColor(Color.decode(boardColors[y+1]));
					} else {						g.setColor(Color.decode(boardColors[y]));	}
					g.fillRect(xc+1, yc+1, 79, 79);
					xc += 80;
				}
			}
			if (squareColor){ 						squareColor = false;}
			else { 									squareColor = true; }
		}

		/*Luo laatikon syödyille nappuloille*/
		g.setColor(Color.decode("#000000"));
		g.drawRect(739, 327, 521, 151);
		g.setColor(Color.decode("#6E7F80"));
	    	g.fillRect(740, 328, 520, 150);
	}

	public void addInto(JLabel l){
		this.add(l);
	}
}
