package colorgame.view;

import colorgame.model.ColorModel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ColorView extends JFrame {
    private static final int SIZE = 9;
    private final JButton[][] buttons;
    private final ColorModel model;
    
    public ColorView() {
        model = new ColorModel(SIZE, Color.yellow, Color.blue);
                
        setTitle("Color game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(SIZE, SIZE));
        add(panel);
        
        buttons = new JButton[SIZE][SIZE];
        for(int i=0; i<SIZE; ++i) {
            for(int j=0; j<SIZE; ++j) {
                JButton button = new JButton();
                buttons[i][j] = button;
                panel.add(button);
                final int x = i;
                final int y = j;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        model.action(x, y);
                        colorButtons();
                        if(model.isOver()) {
                            congratulate();
                        }
                    }
                });
            }
        }
        colorButtons();
    }
    
    private void colorButtons() {
        for(int i=0; i<SIZE; ++i) {
            for(int j=0; j<SIZE; ++j) {
                buttons[i][j].setBackground(model.getColor(i, j));
            }
        }
    }
    
    private void congratulate() {
        JOptionPane.showMessageDialog(this, "You won, congratulations!");
    }
}
