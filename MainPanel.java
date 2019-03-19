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
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainPanel extends JPanel{

	public MainPanel() {}

	public Dimension getPreferredSize(){ return new Dimension(1600, 900); }

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String[] boardColors = {"#990033", "#BFC1C2", "#990033"};
		int xc; int yc;
		boolean squareColor = true;
		for(int x = 0; x < 8; x++){
			xc = 100; yc = 50+(x*100);
			for(int z = 0; z < 4; z++){
				for(int y = 0; y < 2; y++){
					g.setColor(Color.decode("#000000"));
					g.drawRect(xc,yc, 100, 100);
					if (squareColor){				g.setColor(Color.decode(boardColors[y+1]));
					} else {						g.setColor(Color.decode(boardColors[y]));	}
					g.fillRect(xc+1, yc+1, 99, 99);
					xc += 100;
				}
			}
			if (squareColor){ 						squareColor = false;}
			else { 									squareColor = true; }
		}
	}

	public void add(JLabel l){
		this.add(l);
	}
}
