package lightmotors.view;

import lightmotors.logic.LightMotorModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author Kristóf
 */
public class LightMotorView extends JFrame implements KeyListener {

    private LightMotorModel model;
    private int SIZE = 26;
    private JButton[][] buttons;
    private int period;
    private boolean rright = false;
    private boolean rleft = true;
    private boolean rup = false;
    private boolean rdown = false;
    private boolean bright = true;
    private boolean bleft = false;
    private boolean bup = false;
    private boolean bdown = false;
    private JPanel panel;
    private int counter = 0;
    private Border emptyborder;
    private Timer t;

    public LightMotorView() {

        emptyborder = BorderFactory.createEmptyBorder();
        model = new LightMotorModel();
        setTitle("LightMotors");
        setLocation(30, 30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);

        panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        t = new Timer(120, update);
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("New Game");//menu
        for (int i = 12; i <= 36; i += 12) {
            menu.add(makeMenuItem(i));
            menu.addSeparator();
        }
        menubar.add(menu);
        setJMenuBar(menubar);
        setFocusable(true);
        newGame(SIZE);
    }

    private JMenuItem makeMenuItem(int i) {          //menuelemek létrehozása
        JMenuItem item = new JMenuItem(newGameaction);                              //menuitem neve "hordozza" az információt,
        item.setName(i + "");                                                       // hogy mekkora legyen a pálya
        item.setText(i + " × " + i);
        return item;
    }
    AbstractAction newGameaction = new AbstractAction() {//új játék létrehozása, a pálya megfelelő méretének a beállítása
        public void actionPerformed(ActionEvent e) {
            int size = Integer.parseInt(((JMenuItem) e.getSource()).getName());

            newGame(size);
        }
    };

    private void newGame(int size) {

        SIZE = size + 2;
        model.newGame(SIZE);
        panel.removeAll();
        panel.setLayout(new GridLayout(SIZE, SIZE));

        buttons = new JButton[SIZE][SIZE];
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                JButton button = new JButton();
                buttons[i][j] = button;
                button.setBackground(Color.white);
                button.addKeyListener(this);
               button.setBorder(emptyborder);
                button.setPreferredSize(new Dimension(20, 20));
                panel.add(button);

            }
        }
        rright = false;
        rleft = true;
        rup = false;
        rdown = false;
        bright = true;
        bleft = false;
        bup = false;
        bdown = false;
        drawColor();
        validate();
        repaint();
        t.stop();
        pack();
    }

    private void drawColor() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (model.getNumber(i, j) == 1) {
                    buttons[i][j].setBackground(Color.red);
                }
                if (model.getNumber(i, j) == 2) {
                    buttons[i][j].setBackground(Color.blue);
                }
                if (model.getNumber(i, j) == 3) {
                    buttons[i][j].setBackground(Color.gray);
                }
            }
        }
    }

    private void redFaild() {
        JOptionPane.showMessageDialog(this, "Red failed!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void blueFaild() {
        JOptionPane.showMessageDialog(this, "Blue failed!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    private void tie() {
        JOptionPane.showMessageDialog(this, "Tie", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
    ActionListener update = new AbstractAction() {
        public void actionPerformed(ActionEvent ae) {
            switch (model.move()) {
                case 0:
                    drawColor();
                    repaint();
                    break;
                case 1:
                    redFaild();
                    t.stop();
                    break;
                case 2:
                    blueFaild();
                    t.stop();
                    break;
                case 3:
                    tie();
                    t.stop();
                    break;
            }

        }
    };

    public void keyPressed(KeyEvent ke) {

        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            if (!t.isRunning()) {
                t.start();
            } else {
                t.stop();
            }
        }
        switch (key) {
            case KeyEvent.VK_RIGHT: {
                if (!rleft) {
                    model.changeDirictionRed(1);
                    rright = true;
                    rup = false;
                    rdown = false;
                }
            }
            break;
            case KeyEvent.VK_LEFT: {
                if (!rright) {
                    model.changeDirictionRed(3);
                    rleft = true;
                    rup = false;
                    rdown = false;
                }
            }
            break;
            case KeyEvent.VK_UP: {
                if(!rdown){
                    model.changeDirictionRed(0);
                    rup =true;
                    rright=false;
                    rleft=false;
                    
                }
   
            }
            break;
            case KeyEvent.VK_DOWN: {
                if (!rup) {
                    model.changeDirictionRed(2);
                    rdown = true;
                    rleft = false;
                    rright = false;
                }

            }
            break;
            case KeyEvent.VK_W: {
                if (!bdown) {
                    model.changeDirictionBlue(0);
                    bup = true;
                    bright = false;
                    bleft = false;
                }
            }
            break;
            case KeyEvent.VK_S: {
                if (!bup) {
                    model.changeDirictionBlue(2);
                    bdown = true;
                    bright = false;
                    bleft = false;
                }
            }
            break;
            case KeyEvent.VK_A: {
                if (!bright) {
                    model.changeDirictionBlue(3);
                    bleft = true;
                    bdown = false;
                    bup = false;
                }
            }
            break;
            case KeyEvent.VK_D: {
                if (!bleft) {
                    model.changeDirictionBlue(1);
                    bright = true;
                    bup = false;
                    bdown = false;
                }
            }
            break;
        }

    }

    
    public void keyTyped(KeyEvent ke) {
    }

    
    public void keyReleased(KeyEvent ke) {

    }

}
