package blackhole.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuAction extends AbstractAction {

    private int x, y;
    private final BlackHoleWindow blackHoleFrame;

    MenuAction(BlackHoleWindow blackHoleFrame) {
        this.blackHoleFrame = blackHoleFrame;
        setPosition(0, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int direction = Integer.parseInt(((JMenuItem) e.getSource()).getName());//menuitem nevéből meghatározza az irányt
        blackHoleFrame.logic.move(y, x, direction);                             //lép abba az irányba
        blackHoleFrame.changeColor();                                           //kirajzolja a változást

        String title;
        if (blackHoleFrame.logic.isGameWon() > 0) {//ha valaki győzött kiirja, hogy ki
            if (blackHoleFrame.logic.isGameWon() == 1) {
                title = "The blue Player";
            } else {
                title = "The red Player";
            }
            JOptionPane.showMessageDialog(blackHoleFrame, " Winner: " + title, "Game is over", JOptionPane.NO_OPTION);
            blackHoleFrame.newGame();                                           //új játék indul
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
