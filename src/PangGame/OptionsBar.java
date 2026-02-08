package PangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OptionsBar extends JPopupMenu {

	static String Difficulty = "None";
	
	OptionsBar(JButton invoker){
		super();
		setLayout(new GridLayout(3,1));
		setFocusable(false);
		JMenuItem history = new JMenuItem("History");
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(GameBar.loggedIn == true) {
					String[] s = GameBar.currentPlayer.history().toArray(new String[0]);
					JList past = new JList(s);
					JOptionPane.showMessageDialog(null, past, "Player History", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JLabel l = new JLabel("You need to log in to see your account history");
					JOptionPane.showMessageDialog(null, l, "Not Logged In", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JMenuItem highScore = new JMenuItem("High Score");
		highScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ArrayList<String> highScores = new ArrayList();
				
				
				String[] highestScores = highScores.toArray(new String[0]);								
				JList l = new JList(highestScores);
				JOptionPane.showMessageDialog(null, l, "High Scores", JOptionPane.PLAIN_MESSAGE);
			}
		});
		JMenuItem difficulty = new JMenuItem("Difficulty");
		difficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JPopupMenu difficulties = new JPopupMenu();
				difficulties.setLayout(new GridLayout(3, 1));
				JMenuItem novice = new JMenuItem("Novice");
				novice.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Difficulty = "Novice";
					}
				});
				JMenuItem intermediate = new JMenuItem("Intermediate");
				intermediate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Difficulty = "Intermediate";
					}
				});
				JMenuItem advanced = new JMenuItem("Advanced");
				advanced.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Difficulty = "Advanced";
					}
				});
				difficulties.add(novice);
				difficulties.add(intermediate);
				difficulties.add(advanced);
				difficulties.show(invoker, 0, 25);
			}
		});
		
		add(history);
		add(highScore);
		add(difficulty);	
	}
}
