/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightmotors.logic;

/**
 *
 * @author Kristóf
 */
public class Field {
    private int info;

    public Field(int szam){
        info=szam;
    }
            
    public int getInfo(){
        return info;
    }
    
    public void setInfo(int info) {
        this.info = info;
    }
 
    
}
