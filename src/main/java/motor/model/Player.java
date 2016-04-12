package motor.model;

import java.awt.Color;

/**
 *
 * @author bekez_000
 */
public class Player {
    
    Color color;
    int posx;
    int posy;
    int direction;
    
    public Player(Color color, int n, int pos, int direction) {
        this.color=color;
        this.posx = n;
        this.posy = pos;
        this.direction = direction;
    }
    
    public void setPosX(int x) {
        this.posx = x;
    }
    
    public void setPosY(int y) {
        this.posy = y;
    }
    
    public int getPosX() {
        return this.posx;
    }
    
    public int getPosY() {
        return this.posy;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public void setDirection(int d) {
        this.direction = d;
    }
    
}
