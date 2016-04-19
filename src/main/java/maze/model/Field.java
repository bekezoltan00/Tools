package maze.model;

import java.util.Random;

/**
 *
 * @author shepy
 */
public class Field{
    private boolean wall;
    private boolean visible;
    
    public Field(Random rnd){
        wall = rnd.nextBoolean();
        visible = false;
    }
    
    public boolean getWall(){
        return wall;
    }
    
    public boolean getVisible(){
        return visible;
    }
    
    public void setWall(boolean b){
        wall = b;
    }
    
    public void setVisible(boolean b){
        visible = b;
    }
    
    public void changeWall(){
        wall = !wall;
    }
    
    public void changeVisible(){
        visible = !visible;
    }
    
}
