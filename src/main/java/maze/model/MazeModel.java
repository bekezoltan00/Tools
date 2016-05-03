package maze.model;

import java.awt.Color;
import java.util.Random;

public class MazeModel {
    private Field[][] fields;
    private Color color1, color2, color3;
    private int size;
    
    public MazeModel(int size, Color color1, Color color2, Color color3){
        this.size = size;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        emptyMaze(fields);
        generateMaze(fields, size-1, 1);
        fields[size-1][0].setWall(false);
        fields[0][size-1].setWall(false);
    }
    
    private void emptyMaze(Field[][] fields){
        fields = new Field[size][size];
        this.fields = fields;
        Random rnd = new Random();
        for(int i=0; i < size; ++i) {
            for(int j=0; j < size; ++j){
                fields[i][j] = new Field(rnd);
                fields[i][j].setWall(true);
            }
        }
    }
    
    private void generateMaze(Field[][] fields, int x, int y){
        String[] directions = {"up", "down", "right", "left"};
        
        Random rnd = new Random();
        fields[x][y] = new Field(rnd);
        fields[x][y].changeWall();

        for (int i=0; i<4; ++i) {           
            int r = rnd.nextInt(3);             
            String temp = directions[i];    
            directions[i] = directions[r];
            directions[r] = temp;
        }
        
        for (int i=0; i<4; ++i){
            switch (directions[i]) {
                case "up":
                    if (x >= 2 && fields[x-2][y].getWall() == true ) { 
                        fields[x-1][y].setWall(false);         
                        generateMaze(fields, x-2, y); 
                    }
                    break;
                case "left":
                    if (y >= 2 && fields[x][y-2].getWall() == true) {
                        fields[x][y-1].setWall(false);
                        generateMaze(fields, x, y-2);
                    }
                    break;
                case "down":
                    if (y < size-2 && fields[x][y+2].getWall() == true) {
                        fields[x][y+1].setWall(false);
                        generateMaze(fields, x, y+2);
                    }
                    break;
                case "right":
                    if (x < size-2 && fields[x+2][y].getWall() == true) {
                        fields[x+1][y].setWall(false);
                        generateMaze(fields, x+2, y);
                    }
                    break;
            }
        }
    }
   
    public void setVisibilityNone(){
        for(int i=0; i < size; ++i) {
            for(int j=0; j < size; ++j){
                fields[i][j].setVisible(false);
            }
        }
     }

    public void setVisibility(int x, int y){
        try{
            this.setVisibilityNone();
            
            fields[x][y].setVisible(true);
            if(x>0){
                fields[x-1][y].setVisible(true);
            }
            if(x<size-1) {
                fields[x+1][y].setVisible(true);
            }
            if(y>0) {
                fields[x][y-1].setVisible(true);
            }
            if(y<size-1) {
                fields[x][y+1].setVisible(true);
            }
            if(x>0 && y>0) {
                if(!(this.isWall(x-1, y) && this.isWall(x, y-1)))
                    fields[x-1][y-1].setVisible(true);
            }
            if(x<size-1 && y<size-1) {
                if(!(this.isWall(x+1, y) && this.isWall(x, y+1)))
                    fields[x+1][y+1].setVisible(true);
            }
            if(x<size-1 && y>0) {
                if(!(this.isWall(x+1, y) && this.isWall(x, y-1)))
                fields[x+1][y-1].setVisible(true);
            }
            if(x>0 && y<size-1) {
                if(!(this.isWall(x-1, y) && this.isWall(x, y+1)))
                fields[x-1][y+1].setVisible(true);
            }

            if(x>1 && y>0) {
                if(!(this.isWall(x-1, y) || this.isWall(x-1, y-1)))
                    fields[x-2][y-1].setVisible(true);
            }
            if(x>1) {
                if(!(this.isWall(x-1, y)))
                    fields[x-2][y].setVisible(true);
            }
            if(x>1 && y<size-1) {
                if(!(this.isWall(x-1, y) || this.isWall(x-1, y+1)))
                    fields[x-2][y+1].setVisible(true);
            }
            if(x>0 && y>1) {
                if(!(this.isWall(x-1, y-1) || this.isWall(x, y-1)))
                    fields[x-1][y-2].setVisible(true);
            }
            if(x>0 && y<size-2) {
                if(!(this.isWall(x-1, y+1) || this.isWall(x, y+1)))
                    fields[x-1][y+2].setVisible(true);
            }
            if(y>1) {
                if(!(this.isWall(x, y-1)))
                    fields[x][y-2].setVisible(true);
            }
            if(y<size-2) {
                if(!(this.isWall(x, y+1)))
                    fields[x][y+2].setVisible(true);
            }
            if(x<size-1 && y<size-2) {
                if(!(this.isWall(x+1, y+1) || this.isWall(x, y+1)))
                    fields[x+1][y+2].setVisible(true);
            }
            if(x<size-1 && y>1) {
                if(!(this.isWall(x+1, y-1) || this.isWall(x, y-1)))
                    fields[x+1][y-2].setVisible(true);
            }
            if(x<size-2 && y>0) {
                if(!(this.isWall(x+1, y-1) || this.isWall(x+1, y)))
                    fields[x+2][y-1].setVisible(true);
            }
            if(x<size-2) {
                if(!(this.isWall(x+1, y)))
                    fields[x+2][y].setVisible(true);
            }
            if(x<size-2 && y<size-1) {
                if(!(this.isWall(x+1, y+1) || this.isWall(x+1, y)))
                    fields[x+2][y+1].setVisible(true);
            }
            if(x<size-2 && y>1) {
                if(!(this.isWall(x+1, y-1)) && this.isVisible(x+1, y-1)
                && !(this.isWall(x+2, y-1)) && this.isVisible(x+2, y-1)
                && !(this.isWall(x+1, y-2)) && this.isVisible(x+1, y-2))
                    fields[x+2][y-2].setVisible(true);
            }
            if(x>1 && y>1) {
                if(!(this.isWall(x-1, y-1)) && this.isVisible(x-1, y-1)
                && !(this.isWall(x-1, y-2)) && this.isVisible(x-1, y-2)
                && !(this.isWall(x-1, y-1)) && this.isVisible(x-2, y-1))
                    fields[x-2][y-2].setVisible(true);
            }
            if(x<size-2 && y<size-2) {
                if(!(this.isWall(x+1, y+1)) && this.isVisible(x+1, y+1)
                && !(this.isWall(x+2, y+1)) && this.isVisible(x+2, y+1)
                && !(this.isWall(x+1, y+2)) && this.isVisible(x+1, y+2))
                    fields[x+2][y+2].setVisible(true);
            }
            if(x>1 && y<size-2) {
                if(!(this.isWall(x-1, y+1)) && this.isVisible(x-1, y+1)
                && !(this.isWall(x-1, y+2)) && this.isVisible(x-1, y+2)
                && !(this.isWall(x-2, y+1)) && this.isVisible(x-2, y+1))
                    fields[x-2][y+2].setVisible(true);       
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Hiba");
        }
    }
    
    public boolean isWall(int x, int y){
        boolean wall = fields[x][y].getWall();
        if(wall) {
            return true;
        }
        return false;
    }
    
    public boolean isVisible(int x, int y){
        boolean visible = fields[x][y].getVisible();
        if(visible) {
            return true;
        }
        return false;
    }
    
    public Color getBg(int x, int y){
        boolean visible = fields[x][y].getVisible();
        if(visible){
            if (this.isWall(x, y)){
                return color1;
            }else{
                return color2;
            }
        }else{
            return color3;
        }
    }
    
    public boolean isOver(int x, int y){
        return (x == 0 && y == size-1);
    }
}
