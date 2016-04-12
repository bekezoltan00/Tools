package motor.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bekez_000
 */
public class MainMenu extends JFrame {
    
    public MainMenu() throws IOException {
        
        JPanel panel = new JPanel() {
            private Image img = ImageIO.read(new File("src/main/resources/109568.jpg"));
            @Override
            public void paintComponent(Graphics g) { 
                super.paintComponent(g);

                g.drawImage(img, 0,0, this);
            }
        };
        
        this.setIconImage(ImageIO.read(new File("src/main/resources/tron_256_icon.png")));
        
        setTitle("Fénymotor párbaj");
        setSize(480, 270);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JButton button12 = new JButton("12x12");
        JButton button24 = new JButton("24x24");
        JButton button36 = new JButton("36x36");
        
        button12.setSize(20, 10);
        button24.setSize(20, 10);
        button36.setSize(20, 10);
        
        panel.add(button12);
        panel.add(button24);
        panel.add(button36);
        
        add(panel);
        
        button12.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
                new GameWindow(12).setVisible(true);
                disableVisibility();
			}
        });
        
        button24.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new GameWindow(24).setVisible(true);
                disableVisibility();
            }
        });
        
        button36.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new GameWindow(36).setVisible(true);
                disableVisibility();
            }
        });
        
    }
    
    public void disableVisibility() {
        setVisible(false);
    }
    
}
