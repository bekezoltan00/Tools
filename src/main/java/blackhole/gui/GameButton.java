package blackhole.gui;

import javax.swing.JButton;

/**
 *
 * @author Balázs
 */
public class GameButton extends JButton {

    private final int posX, posY;

    public GameButton(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
