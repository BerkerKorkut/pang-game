package PangGame;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.IOException;

public class Register extends JPanel {
	
	JTextField nameField;
	JPasswordField passwordField;
	
	Register(){
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
