package PangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

	static KeyListener kl;
	static int playerType = 1;
	static Level1 level1 = new Level1();
	
	public static void main(String[] args) {
		
		GameFrame pangFrame = new GameFrame();
		pangFrame.setFocusable(true);
		
		pangFrame.add(level1);
		level1.setVisible(false);
		
		pangFrame.addKeyListener(kl);
		pangFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pangFrame.setSize(976,586);
		pangFrame.setResizable(false);
		pangFrame.setVisible(true);
	}

}
