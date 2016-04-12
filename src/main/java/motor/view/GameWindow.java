package motor.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import motor.model.*;

public class GameWindow extends JFrame {

    JButton[][] buttons;
    Color defaultColor;
    Stage st;
    
    public GameWindow(final int n) {

        buttons = new JButton[n][n];
        defaultColor = Color.DARK_GRAY;

        setTitle("Fénymotor párbaj");
        setSize(n * 20, n * 20);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(n, n));
        panel.setFocusable(true);
        panel.setBackground(Color.black);
        setResizable(false);
        setLocationRelativeTo(null);
        
        try {
            this.setIconImage(ImageIO.read(new File("src/main/resources/tron_256_icon.png")));
        } catch (IOException ex) {
            Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JMenuBar menubar = new JMenuBar();
        JMenu newgame = new JMenu("New");
        menubar.add(newgame);

        JMenuItem newgame12 = new JMenuItem("12x12");
        JMenuItem newgame24 = new JMenuItem("24x24");
        JMenuItem newgame36 = new JMenuItem("36x36");

        newgame.add(newgame12);
        newgame.add(newgame24);
        newgame.add(newgame36);
        
        st = new Stage(n, buttons, defaultColor, panel);
        
        newgame12.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                st.timerStop();
                new GameWindow(12).setVisible(true);
                disableVisibility();
            }
        });

        newgame24.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                st.timerStop();
                new GameWindow(24).setVisible(true);
                disableVisibility();
            }
        });

        newgame36.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                st.timerStop();
                new GameWindow(36).setVisible(true);
                disableVisibility();
            }
        });

        setJMenuBar(menubar);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                JButton button = new JButton();
                button.setBackground(defaultColor);
                button.setEnabled(false);
                button.setFocusable(false);
                buttons[i][j] = button;
                panel.add(button);
            }
        }
        add(panel);
    }

    public void disableVisibility() {
        setVisible(false);
    }
    
    public JButton[][] getButtons() {
        return this.buttons;
    }
    
    public Color getDefaultColor() {
        return this.defaultColor;
    }
}