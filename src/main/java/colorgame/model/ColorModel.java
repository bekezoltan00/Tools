package colorgame.model;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author vorosgy
 */
public class ColorModel {
    private Field[][] fields;
    private Color color1, color2;
    private int size;
    
    public ColorModel(int size, Color color1, Color color2) {
        fields = new Field[size][size];
        Random rnd = new Random();
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                fields[i][j] = new Field(rnd);
            }
        }
        this.size = size;
        this.color1 = color1;
        this.color2 = color2;
    }
    
    public void action(int x, int y) {
        fields[x][y].change();
        if(0<x) {
            fields[x-1][y].change();
        }
        if(x<size-1) {
            fields[x+1][y].change();
        }
        if(0<y) {
            fields[x][y-1].change();
        }
        if(y<size-1) {
            fields[x][y+1].change();
        }
        
    }
    
    public boolean isOver() {
        boolean first = fields[0][0].getState();
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                if(fields[i][j].getState() != first) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Color getColor(int x, int y) {
        boolean state = fields[x][y].getState();
        if(state) {
            return color1;
        }
        return color2;
    }
}
