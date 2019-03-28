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
import java.util.Random;

public class MainPanel extends JPanel{

	public MainPanel() {}

	public Dimension getPreferredSize(){ return new Dimension(1280, 720); }

	private static final String[][] boardCol = {{"#990033", "#BFC1C2"}, {"#43596e", "#b9dec0"}, {"#61462a", "#dedbb9"}, {"#e3c934", "#e8e6d7"}, {"#242259", "#f02d2d"}};
	private static String[] boardColors;
	private static String[] styleNames = {"Burgundian Field", "Underwater", "Traditional", "Kingin", "Plantagenet"};

	private static String styleName = "d";
	public void paintComponent(Graphics g){


		/*Rakentaa valkoisen taustan GUI:ille;
		vaikka ilmanan tätäkin pärjää, swing haluaa ohjeet miltä laudan pitäisi näyttää*/
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);

		/*Rakentaa shakki ruudut*/
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

		buildBox(g, 157, Color.LIGHT_GRAY);
		buildBox(g, 327, Color.DARK_GRAY);
	}

	public void addInto(JLabel l){
		this.add(l);
	}

	public void buildBox(Graphics g, int y, Color c){
		/*Luo laatikon syödyille nappuloille*/
		g.setColor(Color.GRAY);
		g.drawRect(739, y, 531, 151);
		g.setColor(c);
	    g.fillRect(740, y+1, 530, 150);
	}

	public static void randomizeSquares(){
		Random randy = new Random();
		int rbc = randy.nextInt(5); //random board square color
	 	//boardColors = {boardCol[rbc][0], boardCol[rbc][1], boardCol[rbc][0]};
		boardColors = new String[3];
		boardColors[0] = boardCol[rbc][0];
		boardColors[1] = boardCol[rbc][1];
		boardColors[2] = boardCol[rbc][0];
		styleName = styleNames[rbc];
	}

	public static String getStyleName(){
		return styleName;
	}
}
