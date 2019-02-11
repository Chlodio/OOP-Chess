import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class Lauta{
	public Lauta(){};
	public static void createGUI(){
		Lauta lauta=new Lauta();
		JFrame frame = new JFrame("Shakki v. 0.02");
		frame.setSize(1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainPanel());
				frame.pack();

		JButton exitButton=new JButton("X");
		ActionListener acLi = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		};
		exitButton.addActionListener(acLi);
	    exitButton.setBounds(25,25,45,40);
    	frame.add(exitButton);


		frame.setLayout(null);
		frame.setVisible(true);
	}
}
