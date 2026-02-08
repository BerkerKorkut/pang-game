package PangGame;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
	
	JTextField nameField;
	JPasswordField passwordField;
	
	Login(){
		super();
		
		nameField = new JTextField();
		passwordField = new JPasswordField();
		
		setLayout(new GridLayout(4,1));
		add(new JLabel("Name:"));
		add(nameField);
		add(new JLabel("Password:"));
		add(passwordField);
		
	}
	
	
}
