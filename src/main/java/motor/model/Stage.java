package motor.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Stage extends JFrame {
    
	int n;
    private JButton[][] buttons;
    Color defaultColor;
    JPanel panel;
    
    Timer timer;
    
    public Stage(final int n, final JButton[][] buttons, final Color defaultColor, JPanel panel) {
    	
    	this.n = n;
    	this.setButtons(buttons);
    	this.defaultColor = defaultColor;
    	this.panel = panel;
    	
        final Player p1 = new Player(Color.CYAN, n / 2, 0, SwingConstants.RIGHT);
        final Player p2 = new Player(Color.MAGENTA, n / 2, n - 1, SwingConstants.LEFT);
        
        int delay = 200; //milliseconds
        final boolean gameover = false;

        timer = new Timer(delay, new ActionListener() {
        	
            public void actionPerformed(ActionEvent evt) {
                buttons[p1.getPosX()][p1.getPosY()].setBackground(p1.getColor());
                buttons[p2.getPosX()][p2.getPosY()].setBackground(p2.getColor());
                int p1pos = 0;
                int p2pos = 0;

                try {
                    if (p1.getDirection() == 1 && buttons[p1.getPosX() - 1][p1.getPosY()].getBackground() == defaultColor) {
                        p1.setPosX(p1.getPosX() - 1);
                    } else if (p1.getDirection() == 2 && buttons[p1.getPosX()][p1.getPosY() - 1].getBackground() == defaultColor) {
                        p1.setPosY(p1.getPosY() - 1);
                    } else if (p1.getDirection() == 3 && buttons[p1.getPosX() + 1][p1.getPosY()].getBackground() == defaultColor) {
                        p1.setPosX(p1.getPosX() + 1);
                    } else if (p1.getDirection() == 4 && buttons[p1.getPosX()][p1.getPosY() + 1].getBackground() == defaultColor) {
                        p1.setPosY(p1.getPosY() + 1);
                    } else {
                        ((Timer) evt.getSource()).stop();
                        p1pos = p1.getPosX() * n + p1.getPosY();
                        p2pos = p2.getPosX() * n + p2.getPosY();
                        if (p1pos != p2pos + 1 && p1pos != p2pos - 1 && p1pos != p2pos + n && p1pos != p2pos - n) {
                            JOptionPane.showMessageDialog(null, "Game Over! (Player 2 won!)");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ((Timer) evt.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Game Over! (Player 2 won!)");
                }

                try {
                    if (p2.getDirection() == 1 && buttons[p2.getPosX() - 1][p2.getPosY()].getBackground() == defaultColor) {
                        p2.setPosX(p2.getPosX() - 1);
                    } else if (p2.getDirection() == 2 && buttons[p2.getPosX()][p2.getPosY() - 1].getBackground() == defaultColor) {
                        p2.setPosY(p2.getPosY() - 1);
                    } else if (p2.getDirection() == 3 && buttons[p2.getPosX() + 1][p2.getPosY()].getBackground() == defaultColor) {
                        p2.setPosX(p2.getPosX() + 1);
                    } else if (p2.getDirection() == 4 && buttons[p2.getPosX()][p2.getPosY() + 1].getBackground() == defaultColor) {
                        p2.setPosY(p2.getPosY() + 1);
                    } else {
                        ((Timer) evt.getSource()).stop();
                        p1pos = p1.getPosX() * n + p1.getPosY();
                        p2pos = p2.getPosX() * n + p2.getPosY();
                        if (p1pos != p2pos + 1 && p1pos != p2pos - 1 && p1pos != p2pos + n && p1pos != p2pos - n) {
                            JOptionPane.showMessageDialog(null, "Game Over! (Player 1 won!)");
                        } else {
                            if (p1pos == p2pos + 1 || p1pos == p2pos - 1 || p1pos == p2pos + n || p1pos == p2pos - n) {
                                JOptionPane.showMessageDialog(null, "Game Over! (Tie)");
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ((Timer) evt.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Game Over! (Player 1 won!)");
                }
                
                buttons[p1.getPosX()][p1.getPosY()].setBackground(p1.getColor());
                buttons[p2.getPosX()][p2.getPosY()].setBackground(p2.getColor());
            }
        });
        timer.start();
        
        panel.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && p2.getDirection() != SwingConstants.BOTTOM) {
                    p2.setDirection(SwingConstants.TOP);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT && p2.getDirection() != SwingConstants.RIGHT) {
                    p2.setDirection(SwingConstants.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && p2.getDirection() != SwingConstants.TOP) {
                    p2.setDirection(SwingConstants.BOTTOM);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && p2.getDirection() != SwingConstants.LEFT) {
                    p2.setDirection(SwingConstants.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_W && p1.getDirection() != SwingConstants.BOTTOM) {
                    p1.setDirection(SwingConstants.TOP);
                } else if (e.getKeyCode() == KeyEvent.VK_A && p1.getDirection() != SwingConstants.RIGHT) {
                    p1.setDirection(SwingConstants.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_S && p1.getDirection() != SwingConstants.TOP) {
                    p1.setDirection(SwingConstants.BOTTOM);
                } else if (e.getKeyCode() == KeyEvent.VK_D && p1.getDirection() != SwingConstants.LEFT) {
                    p1.setDirection(SwingConstants.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (timer.isRunning()) {
                        timerStop();
                    } else {
                        timerStart();
                    }
                }
            }

            public void keyReleased(KeyEvent e) {
            }

        });
    }
    
    public void timerStart() {
        timer.start();
    }
    
    public void timerStop() {
        timer.stop();
    }

	JButton[][] getButtons() {
		return buttons;
	}

	void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}
}
