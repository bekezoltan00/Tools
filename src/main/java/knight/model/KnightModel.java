package knight.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bekezoltan00
 */
public class KnightModel extends JFrame {
    
        
    JButton[][] buttons = new JButton[5][5];
    
    int posx;
    int posy;
    
    public KnightModel(final JButton[][] buttons) {
        this.buttons = buttons;
        
        posx = (int)(Math.random()*5);
        posy = (int)(Math.random()*5);
        /*
        buttons[2][2].setBackground(Color.blue);
        posx = 2;
        posy = 2;
        */
        /*
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].setBackground(Color.red);
            }
        }
        
        buttons[1][1].setBackground(Color.blue);
        posx = 1;
        posy = 1;
        buttons[3][2].setBackground(Color.white);
        */
        buttons[posx][posy].setBackground(Color.blue);
        coloring();
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                final int m = i;
                final int n = j;
                buttons[i][j].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if(buttons[m][n].getBackground()==Color.yellow) {
                            buttons[posx][posy].setBackground(Color.red);
                            posx = m;
                            posy = n;
                            buttons[posx][posy].setBackground(Color.blue);
                            unColoring();
                            coloring();
                            if(end()) JOptionPane.showMessageDialog(null, "Vége!!!");
                        }
                    }
                });
            }
        }
    }
    
    public void coloring() {
        if(posx<3 && posy<4 && buttons[posx+2][posy+1].getBackground()!=Color.red)
            buttons[posx+2][posy+1].setBackground(Color.yellow);
        if(posx<3 && posy>0 && buttons[posx+2][posy-1].getBackground()!=Color.red)
            buttons[posx+2][posy-1].setBackground(Color.yellow);
        
        if(posx>1 && posy<4 && buttons[posx-2][posy+1].getBackground()!=Color.red)
            buttons[posx-2][posy+1].setBackground(Color.yellow);
        if(posx>1 && posy>0 && buttons[posx-2][posy-1].getBackground()!=Color.red)
            buttons[posx-2][posy-1].setBackground(Color.yellow);
        
        if(posy<3 && posx<4 && buttons[posx+1][posy+2].getBackground()!=Color.red)
            buttons[posx+1][posy+2].setBackground(Color.yellow);
        if(posy<3 && posx>0 && buttons[posx-1][posy+2].getBackground()!=Color.red)
            buttons[posx-1][posy+2].setBackground(Color.yellow);
        
        if(posy>1 && posx<4 && buttons[posx+1][posy-2].getBackground()!=Color.red)
            buttons[posx+1][posy-2].setBackground(Color.yellow);
        if(posy>1 && posx>0 && buttons[posx-1][posy-2].getBackground()!=Color.red)
            buttons[posx-1][posy-2].setBackground(Color.yellow);
    }
    
    public void unColoring() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(buttons[i][j].getBackground()==Color.yellow) buttons[i][j].setBackground(Color.white);
            }
        }
    }
    
    public boolean end() {
        int szlalo = 0;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(buttons[i][j].getBackground()==Color.yellow) {
                    szlalo++;
                }
            }
        }
        if(szlalo==0) return true;
        return false;
    }
    
}
