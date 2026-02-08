package PangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class GameBar extends JPopupMenu {
	
	static Player currentPlayer;
	static boolean loggedIn = false;
	
	GameBar(){
		super();
		setLayout(new GridLayout(4,1));
		setFocusable(false);
		JMenuItem register = new JMenuItem("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Register r = new Register();
				int result = JOptionPane.showConfirmDialog(null, r, "Register", 2, JOptionPane.PLAIN_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					String name = r.nameField.getText();
					String password = new String(r.passwordField.getPassword());
					if(!name.equals("") && !password.equals("")) {
						try {
							FileWriter fileWriter = new FileWriter("UserData.txt", true);
				            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				            bufferedWriter.write(name + "," + password + ":");
				            bufferedWriter.newLine();
				            bufferedWriter.close();
				            
				        } catch (IOException ioe) {
				            ioe.printStackTrace();
				        }
					}
					else {
						JLabel l = new JLabel("Name and Password fields cannot be empty");
						JOptionPane.showMessageDialog(null, l, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JMenuItem login = new JMenuItem("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Login l = new Login();
				int result = JOptionPane.showConfirmDialog(null, l, "Login", 2, JOptionPane.PLAIN_MESSAGE);
				if(result == JOptionPane.OK_OPTION) {
					String name = l.nameField.getText();
					String password = new String(l.passwordField.getPassword());
					
					// User info will be stored as name,password:score,time,date;score,time,date;score,time,date;score,time,date; and so on
					
					try {
						FileReader fileReader = new FileReader("UserData.txt");
			            BufferedReader bufferedReader = new BufferedReader(fileReader);
			            String input = name + "," + password + ":";
			            boolean found = false;
			            String line;
			            while(!found && (line = bufferedReader.readLine()) != null) {
				            if(line.contains(input)) {  	
				            	found = true;
				            	String[] userLine = line.split(":");
				            	String[] userInfo = userLine[0].split(",");
				            	name = userInfo[0];
				            	password = userInfo[1];
					            currentPlayer = new Player(name, password);
					            if(userLine.length > 1) {
					            	String[] playerGames = userLine[1].split(";");
						            int i = 0;
						            while(i < playerGames.length) {
						            	String[] scoreTime = playerGames[i].split(",");
						            	Integer score = Integer.parseInt(scoreTime[0]);
						            	Integer time = Integer.parseInt(scoreTime[1]);
						            	String date = scoreTime[2];
						            	currentPlayer.addGame(score, time, date);
						            	i++;
						            }
					            }
					            loggedIn = true;
				            } 
			            }
			            if(found == false) {
			            	JLabel invalid = new JLabel("Invalid name or password");
			            	JOptionPane.showMessageDialog(null, invalid, "User Not Found", JOptionPane.ERROR_MESSAGE);
			            	
			            }
			            
			        } catch (IOException ioe) {
			            ioe.printStackTrace();
			        }
					
				}
					
			}
		});
		
		JMenuItem newGame = new JMenuItem("New");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(loggedIn == true) {
					if(OptionsBar.Difficulty.equals("None")) {
						JLabel l = new JLabel("You need to choose a difficulty from options first");
						JOptionPane.showMessageDialog(null, l, "Choose Difficulty First", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Main.level1.setVisible(true);
						repaint();
					}			
				}
				else {
					JLabel l = new JLabel("You need to log in before starting a game");
					JOptionPane.showMessageDialog(null, l, "Log In Before Playing", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		
		add(register);
		add(login);
		add(newGame);
		add(quit);
	}
	
}
