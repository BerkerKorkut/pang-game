package PangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame {
	
	public GameFrame() {
		super("Pang");
		
		JMenuBar menuBar = new JMenuBar();
		setLayout(new BorderLayout());
		menuBar.setFocusable(false);
		
		JButton gameButton = new JButton("Game");
		gameButton.setFocusable(false);
		GameBar gameBar = new GameBar();
		gameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gameBar.show(gameButton, 0, 25);
			}
		});
		
		JButton optionsButton = new JButton("Options");
		optionsButton.setFocusable(false);
		OptionsBar optionsBar = new OptionsBar(optionsButton);
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				optionsBar.show(optionsButton, 0, 25);			
			}
		});
		
		JButton helpButton = new JButton("Help");
		helpButton.setFocusable(false);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JPopupMenu helpMenu = new JPopupMenu();
				helpMenu.setFocusable(false);
				JMenuItem aboutButton = new JMenuItem("About");
				aboutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						JTextArea about = new JTextArea("Berker KORKUT\n" + "20230702016\n" + "berker.korkut@std.yeditepe.edu.tr");
						about.setEditable(false);
						JOptionPane.showMessageDialog(null, about, "About", JOptionPane.INFORMATION_MESSAGE, null);
					}
				});
				helpMenu.add(aboutButton);
				helpMenu.show(helpButton, 0, 25);
			}
		});
		
		menuBar.add(gameButton);
		menuBar.add(optionsButton);
		menuBar.add(helpButton);
		add(menuBar, BorderLayout.NORTH);
		
	}
	
	
}
