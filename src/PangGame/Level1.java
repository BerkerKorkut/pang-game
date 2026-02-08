package PangGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Level1 extends JLayeredPane {

	Level1(){
		super();
		setFocusable(true);
		Random random = new Random();
		int backgroundNumber = random.nextInt(50) + 1;
		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/assets/Backgrounds/Background" + backgroundNumber + ".png"));
		JLabel background = new JLabel(backgroundImage);
		background.setBounds(0, 0, 960, 520);
		ImageIcon foregroundImage = new ImageIcon(getClass().getResource("/assets/Foreground/Foreground1.png"));
		JLabel foreground = new JLabel(foregroundImage);
		ImageIcon[] walkingRight = new ImageIcon[5];
		for(int i = 0; i < 5; i++) {
			ImageIcon ii = new ImageIcon(getClass().getResource("/assets/Player/player" + Main.playerType + "_" + (i+1) + ".png"));
			walkingRight[i] = ii;
		}
		ImageIcon[] walkingLeft = new ImageIcon[5];
		for(int i = 0; i < 5; i++) {
			ImageIcon ii = new ImageIcon(getClass().getResource("/assets/Player/player" + Main.playerType + "_" + (i+23) + ".png"));
			walkingLeft[i] = ii;
		}
		ImageIcon[] hooks = new ImageIcon[70];
		for(int i = 0; i < 70; i++) {
			ImageIcon ii = new ImageIcon(getClass().getResource("/assets/hooks/hook" + (i+1) + ".png"));
			hooks[i] = ii;
		}
		
		ImageIcon characterImage = new ImageIcon(getClass().getResource("/assets/Player/Player1_16.png"));
		JLabel character = new JLabel(characterImage);
		character.setBounds(20, 435, characterImage.getIconWidth(), characterImage.getIconHeight());
		ImageIcon hookImage = new ImageIcon(getClass().getResource("/assets/hooks/hook1.png"));
		JLabel hook = new JLabel(hookImage);
		hook.setBounds(40, 210, 18, 68);
		hook.setVisible(false);
		
		
		add(background, Integer.valueOf(0));
		add(foreground, Integer.valueOf(1));
		add(character, Integer.valueOf(2));
		add(hook, Integer.valueOf(2));
		Countdown countdown = new Countdown(this, 6);
		repaint();
		
									
		Main.kl = new KeyListener() {
			int x = 20;
			int y = 365;
			int initialY = 365;
			boolean animatingRight = false;
			boolean animatingLeft = false;
			boolean idleState = true;
			boolean hookAnimating = false;
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode() == KeyEvent.VK_RIGHT || ke.getKeyCode() == KeyEvent.VK_D) {
					if(x < 880) {
						x += 3;
						character.setBounds(x, 435, character.getWidth(), character.getHeight());
						if(!animatingRight) {
							animatingRight = true;
							Thread rightThread = new Thread() {
								int i = 0;
								@Override
								public void run() {
									while(i < 5) {
										character.setIcon(walkingRight[i]);
										i++;
										try {
							                Thread.sleep(200);
							            } catch (InterruptedException ie) {
							                ie.printStackTrace();
							            }
									}
									animatingRight = false;
								}
							};
							rightThread.start();
						}
					}
				}
				else if(ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_A) {
					if(x > 20) {
						x -= 3;
						character.setBounds(x, 435, character.getWidth(), character.getHeight());
						if(!animatingLeft) {
							animatingLeft = true;
							Thread leftThread = new Thread() {
								int i = 0;
								@Override
								public void run() {
									while(i < 5) {
										character.setIcon(walkingLeft[i]);
										i++;
										try {
							                Thread.sleep(200);
							            } catch (InterruptedException ie) {
							                ie.printStackTrace();
							            }
									}
									animatingLeft = false;	
								}
							};						
							leftThread.start();							
						}		
					}
				}
				else if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
					hook.setVisible(true);
					if(!hookAnimating) {
						hookAnimating = true;
						Thread hookThread = new Thread() {
							int i = 0;
							@Override
							public void run() {
								int xfrom = character.getX();
								while(i < 70) {
									hook.setIcon(hooks[i]);
									y = initialY - (hook.getHeight() - 68);
									hook.setBounds(xfrom + 20, y, hook.getWidth(), hooks[i].getIconHeight());
									i++;
									try {
						                Thread.sleep(15);
						            } catch (InterruptedException ie) {
						                ie.printStackTrace();
						            }
								}
								hook.setIcon(null);
								hookAnimating = false;
							}
						};
						hookThread.start();
					}
				}
			}
			public void keyReleased(KeyEvent ke) {
				while(animatingRight || animatingLeft) {
					try {
		                Thread.sleep(40);
		            } catch (InterruptedException ie) {
		                ie.printStackTrace();
		            }
				}
				character.setIcon(characterImage);
				character.setBounds(x, 435, character.getWidth(), character.getHeight());
			}
			public void keyTyped(KeyEvent ke) {}
		};
		
		if(OptionsBar.Difficulty.equals("Novice")) {
			ImageIcon baloonImage = new ImageIcon(getClass().getResource("/assets/baloons/green_baloon1.png"));
			JLabel baloon1 = new JLabel(baloonImage);
			baloon1.setBounds(40, 200, 48, 40);
			add(baloon1, Integer.valueOf(3));
			baloon1.setVisible(true);
			Thread firstBaloon = new Thread() {
				int x = 40;
				public void run() {
					while(true) {
						while(x < 870) {
							x += 5;
							baloon1.setBounds(x, 200, 48, 40);
						}
						while(x > 40) {
							x -= 5;
							baloon1.setBounds(x, 200, 48, 40);
						}
					}
				}
			};
			firstBaloon.start();
			repaint();
		}
		else if(OptionsBar.Difficulty.equals("Intermediate")) {
			ImageIcon baloonImage = new ImageIcon(getClass().getResource("/assets/baloons/blue_baloon1.png"));
			JLabel baloon1 = new JLabel(baloonImage);
			baloon1.setBounds(200, 5, baloon1.getWidth(), baloon1.getHeight());
			add(baloon1, Integer.valueOf(3));
			baloon1.setVisible(true);
			JLabel baloon2 = new JLabel(baloonImage);
			baloon2.setBounds(550, 5, baloon1.getWidth(), baloon1.getHeight());
			add(baloon2, Integer.valueOf(4));
			baloon2.setVisible(true);
			Thread firstBaloon = new Thread() {
				int y = 5;
				public void run() {
					while(true) {
						while(y < 500) {
							y += 5;
							baloon1.setBounds(200, y, 48, 40);
						}
						while(y > 5) {
							y -= 5;
							baloon1.setBounds(200, y, 48, 40);
						}
					}
				}
			};
			Thread secondBaloon = new Thread() {
				int y = 5;
				public void run() {	
					while(true) {
						while(y < 500) {
							y += 5;
							baloon2.setBounds(550, y, 48, 40);
						}
						while(y > 5) {
							y -= 5;
							baloon2.setBounds(550, y, 48, 40);
						}
					}
				}
			};
			firstBaloon.start();
			secondBaloon.start();
			repaint();
		}
		else if(OptionsBar.Difficulty.equals("Advanced")) {
			ImageIcon baloonImage = new ImageIcon(getClass().getResource("/assets/baloons/red_baloon1.png"));
			JLabel baloon1 = new JLabel(baloonImage);
			baloon1.setBounds(40, 200, baloon1.getWidth(), baloon1.getHeight());
			add(baloon1, Integer.valueOf(3));
			baloon1.setVisible(true);
			JLabel baloon2 = new JLabel(baloonImage);
			baloon2.setBounds(200, 5, baloon2.getWidth(), baloon2.getHeight());
			add(baloon2, Integer.valueOf(4));
			baloon2.setVisible(true);
			JLabel baloon3 = new JLabel(baloonImage);
			baloon3.setBounds(550, 5, baloon3.getWidth(), baloon3.getHeight());
			add(baloon3, Integer.valueOf(5));
			baloon3.setVisible(true);
			Thread firstBaloon = new Thread() {
				int x = 40;
				public void run() {
					while(true) {
						while(x < 870) {
							x += 5;
							baloon1.setBounds(x, 200, 48, 40);
						}
						while(x > 40) {
							x -= 5;
							baloon1.setBounds(x, 200, 48, 40);
						}
					}
				}
			};
			Thread secondBaloon = new Thread() {
				int y = 5;
				public void run() {
					while(true) {
						while(y < 500) {
							y += 5;
							baloon2.setBounds(200, y, 48, 40);
						}
						while(y > 5) {
							y -= 5;
							baloon2.setBounds(200, y, 48, 40);
						}
					}
				}
			};
			Thread thirdBaloon = new Thread() {
				int y = 5;
				public void run() {
					while(true) {
						while(y < 500) {
							y += 5;
							baloon3.setBounds(550, y, 48, 40);
						}
						while(y > 5) {
							y -= 5;
							baloon3.setBounds(550, y, 48, 40);
						}
					}
				}
			};
			firstBaloon.start();
			secondBaloon.start();
			thirdBaloon.start();
			repaint();
		}
	}
}
