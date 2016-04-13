package labirintus.model;

import java.awt.Color;
import java.util.Random;

public class LabirintusModel {
    private final Field[][] fields;
    private int aktx;
    private int akty;
    private final int size;
    
    public LabirintusModel(int size){
        fields = new Field[size][size];
        Random rnd = new Random();
        fields[size-1][0] = new Field();
        fields[0][size-1] = new Field();        
        int x=size-1;
        int y=0;
        while(!(x==0 && y==size-1)){
            switch(rnd.nextInt(2)){
                case 0: if(x>0){
                            x-=1;
                            fields[x][y] = new Field();
                        }
                        break;
                case 1: if(y<size-1){
                            y+=1;
                            fields[x][y] = new Field();
                        }
                        break;
                default: 
                     break;
            }
        }
        
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                if(fields[i][j]==null){
                    fields[i][j] = new Field(rnd);
                }
            }
        }
        this.size=size;
        this.aktx=size-1;
        this.akty=0;
        on(aktx,akty);
    }
    
    public Color getColor(int x, int y){
        if(x==aktx && y==akty){
            return Color.yellow;
        }else{
            if(fields[x][y].isVisible()){
                if(fields[x][y].isWall()){
                    return Color.gray;
                }else{
                    return Color.white;
                }
            }else{
                return Color.black;
            }
        }
    }
    
    private void on(int x, int y){
        fields[x][y].unfold();
        if(x>0){
        fields[x-1][y].unfold();
        }
        if(x<size-1) {
            fields[x+1][y].unfold();
        }
        if(y>0) {
            fields[x][y-1].unfold();
        }
        if(y<size-1) {
            fields[x][y+1].unfold();
        }
        if(x>0 && y>0) {
            if(!(fields[x-1][y].isWall() && fields[x][y-1].isWall()))
                fields[x-1][y-1].unfold();
        }
        if(x<size-1 && y<size-1) {
            if(!(fields[x+1][y].isWall() && fields[x][y+1].isWall()))
                fields[x+1][y+1].unfold();
        }
        if(x<size-1 && y>0) {
            if(!(fields[x+1][y].isWall() && fields[x][y-1].isWall()))
            fields[x+1][y-1].unfold();
        }
        if(x>0 && y<size-1) {
            if(!(fields[x-1][y].isWall() && fields[x][y+1].isWall()))
            fields[x-1][y+1].unfold();
        }
//-----------kulso
        
        if(x>1 && y>0) {
            if(!(fields[x-1][y].isWall() || fields[x-1][y-1].isWall()))
                fields[x-2][y-1].unfold();
        }
        if(x>1) {
            if(!(fields[x-1][y].isWall()))
                fields[x-2][y].unfold();
        }
        if(x>1 && y<size-1) {
            if(!(fields[x-1][y].isWall() || fields[x-1][y+1].isWall()))
                fields[x-2][y+1].unfold();
        }
        if(x>0 && y>1) {
            if(!(fields[x-1][y-1].isWall() || fields[x][y-1].isWall()))
                fields[x-1][y-2].unfold();
        }
        if(x>0 && y<size-2) {
            if(!(fields[x-1][y+1].isWall() || fields[x][y+1].isWall()))
                fields[x-1][y+2].unfold();
        }
        if(y>1) {
            if(!(fields[x][y-1].isWall()))
                fields[x][y-2].unfold();
        }
        if(y<size-2) {
            if(!(fields[x][y+1].isWall()))
                fields[x][y+2].unfold();
        }
        if(x<size-1 && y<size-2) {
            if(!(fields[x+1][y+1].isWall() || fields[x][y+1].isWall()))
                fields[x+1][y+2].unfold();
        }
        if(x<size-1 && y>1) {
            if(!(fields[x+1][y-1].isWall() || fields[x][y-1].isWall()))
                fields[x+1][y-2].unfold();
        }
        if(x<size-2 && y>0) {
            if(!(fields[x+1][y-1].isWall() || fields[x+1][y].isWall()))
                fields[x+2][y-1].unfold();
        }
        if(x<size-2) {
            if(!(fields[x+1][y].isWall()))
                fields[x+2][y].unfold();
        }
        if(x<size-2 && y<size-1) {
            if(!(fields[x+1][y+1].isWall() || fields[x+1][y].isWall()))
                fields[x+2][y+1].unfold();
        }
        if(x<size-2 && y>1) {
            if(!(fields[x+1][y-1].isWall()) && fields[x+1][y-1].isVisible()
            && !(fields[x+2][y-1].isWall()) && fields[x+2][y-1].isVisible()
            && !(fields[x+1][y-2].isWall()) && fields[x+1][y-2].isVisible())
                fields[x+2][y-2].unfold();
        }
        if(x>1 && y>1) {
            if(!(fields[x-1][y-1].isWall()) && fields[x-1][y-1].isVisible()
            && !(fields[x-1][y-2].isWall()) && fields[x-1][y-2].isVisible()
            && !(fields[x-2][y-1].isWall()) && fields[x-2][y-1].isVisible())
                fields[x-2][y-2].unfold();
        }
        if(x<size-2 && y<size-2) {
            if(!(fields[x+1][y+1].isWall()) && fields[x+1][y+1].isVisible()
            && !(fields[x+2][y+1].isWall()) && fields[x+2][y+1].isVisible()
            && !(fields[x+1][y+2].isWall()) && fields[x+1][y+2].isVisible())
                fields[x+2][y+2].unfold();
        }
        if(x>1 && y<size-2) {
            if(!(fields[x-1][y+1].isWall()) && fields[x-1][y+1].isVisible()
            && !(fields[x-1][y+2].isWall()) && fields[x-1][y+2].isVisible()
            && !(fields[x-2][y+1].isWall()) && fields[x-2][y+1].isVisible())
                fields[x-2][y+2].unfold();       
        }
        
    }
    
    private void off(){
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                fields[i][j].hide();
            }
        }
    }
    
    public void moveUp(){
        if(aktx>0){
            if(!fields[aktx-1][akty].isWall()){
                off();
                aktx=aktx-1;
                on(aktx,akty);
            }
        }
    }
            
    public void moveDown(){
        if(aktx<size-1){   
            if(!fields[aktx+1][akty].isWall()){
                off();
                aktx=aktx+1;
                on(aktx,akty);
            }
        }
    }
    
    public void moveLeft(){
        if(akty>0){
            if(!fields[aktx][akty-1].isWall()){
                off();
                akty=akty-1;
                on(aktx,akty);
            }
        }
    }
    
    public void moveRight(){
        if(akty<size-1){
            if(!fields[aktx][akty+1].isWall()){
                off();
                akty=akty+1;
                on(aktx,akty);
            }
        }
    }
    
    public boolean isOver(){
        return (aktx==0 && akty==size-1);
    }
}
