package PangGame;

import javax.swing.*;

public class Countdown extends JPanel implements Runnable {
    JLabel label = new JLabel("Time left: 90", SwingConstants.CENTER);
    int time = 90;

    public Countdown(JLayeredPane layeredpane, int layer) {
        super();
        layeredpane.add(this, Integer.valueOf(layer));
        setVisible(true);
        new Thread(this).start();
        repaint();
    }

    @Override
    public void run() {
        while (time > 0) {
            time--;
            label.setText("Time left: " + time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        label.setText("Time's up!");
    }
}
