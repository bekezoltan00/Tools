package labirintus.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import labirintus.model.LabirintusModel;

public class LabirintusView extends JFrame{
    private int size;
	private JPanel panel;
    private JButton[][] buttons;
    private LabirintusModel model;
    
    public LabirintusView(int s){
        size=s;
        setTitle("Labirintus");
        setSize(10*size+200,10*size+200);
        setLocationRelativeTo(null);
        
        panel = new JPanel();
        add(panel);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu newGame = new JMenu("Új játék");
        menuBar.add(newGame);
        
        JMenuItem x15 = new JMenuItem("15x15");
        JMenuItem x20 = new JMenuItem("20x20");
        JMenuItem x25 = new JMenuItem("25x25");
        JMenuItem x30 = new JMenuItem("30x30");
        
        x15.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent e) {
                size=15;
                new LabirintusView(size).setVisible(true);
                setVisible(false);
                
            }
        });
        
        x20.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent e) {
                size=20;
                new LabirintusView(size).setVisible(true);
                setVisible(false);
                
            }
        });
        
        x25.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent e) {
                size=25;
                new LabirintusView(size).setVisible(true);
                setVisible(false);
                
            }
        });
        
        x30.addActionListener(new ActionListener() {
            
            
            public void actionPerformed(ActionEvent e) {
                size=30;
                new LabirintusView(size).setVisible(true);
                setVisible(false);
                
            }
        });
        
        newGame.add(x15);
        newGame.add(x20);
        newGame.add(x25);
        newGame.add(x30);
        
        if(size!=0){
            drawbuttons(size);
            model = new LabirintusModel(size);
            drawLabirintus();
        }
        
        panel.setFocusable(true);
        panel.addKeyListener(new KeyListener() {
            
            public void keyTyped(KeyEvent e) {
            }

            
            public void keyPressed(KeyEvent e) {
            }

            
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    model.moveRight();
                    drawLabirintus();
                    if(model.isOver()){
                        congratulate();
                        panel.removeAll();
                        panel.removeKeyListener(this);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    model.moveLeft();
                    drawLabirintus();
                    if(model.isOver()){
                        congratulate();
                        panel.removeAll();
                        panel.removeKeyListener(this);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_UP){
                    model.moveUp();
                    drawLabirintus();
                    if(model.isOver()){
                        congratulate();
                        panel.removeAll();
                        panel.removeKeyListener(this);
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    model.moveDown();
                    drawLabirintus();
                    if(model.isOver()){
                        congratulate();
                        panel.removeAll();
                        panel.removeKeyListener(this);
                    }
                }
            }
        }
        );
    }
    
    private void drawLabirintus(){
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
               buttons[i][j].setBackground(model.getColor(i,j));
            }
        }
    }
    
    private void congratulate() {
        JOptionPane.showMessageDialog(this, "Gratulálunk, Ön Nyert!");
        
    }
    
    private void drawbuttons(int s){
        panel.setLayout(new GridLayout(s, s));
        buttons = new JButton[s][s];
        for(int i=0; i<s; ++i) {
            for(int j=0; j<s; ++j) {
                JButton button = new JButton();
                button.setEnabled(false);
                if(i==0 && j==s-1){
                    button.setBorder(new LineBorder(Color.red,2));
                }
                buttons[i][j] = button;
                panel.add(button);
            }
        }
    }
    
    public int getViewSize() {
		return size;
	}

	public JButton[][] getButtons() {
		return buttons;
	}
    
}