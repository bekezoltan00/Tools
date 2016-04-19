package colorgame.model;

import java.util.Random;

public class Field {
    private boolean state;
    
    public Field(Random rnd) {
        state = rnd.nextBoolean();
    }
    
    public void change() {
        state = !state;
    }
    
    public boolean getState() {
        return state;
    }
}
