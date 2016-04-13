package labirintus.model;

import java.util.Random;

public class Field {
    private final boolean wall;
    private boolean visible;
    
    public Field(Random rnd){
        wall=rnd.nextBoolean();
        visible=false;
    }
    
    public Field(){
        this.wall=false;
        visible=false;
    }
    
    public boolean isWall(){
        return wall;
    }
    
    public void hide(){
        visible=false;
    }
    
    public void unfold(){
        visible=true;
    }
    
    public boolean isVisible(){
        return visible;
    }
}
