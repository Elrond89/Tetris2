/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import tetris.Quadrato.ImageColour;

/**
 *
 * @author antonioruffolo
 */
public class TetraO implements Tetramino, Serializable {
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private boolean fermo;
    
    public TetraO(){
        tetraArray[0] = new Quadrato(80, 0, ImageColour.YELLOW);
        tetraArray[1] = new Quadrato(100, 0, ImageColour.YELLOW);
        tetraArray[2] = new Quadrato(80, 20, ImageColour.YELLOW);
        tetraArray[3] = new Quadrato(100, 20, ImageColour.YELLOW);
    }
    
    @Override
    public void setTetramino(Quadrato[]q){
        tetraArray=q;
    }
    
    @Override
    public int getXCentre() {
        return 0;
    }

    @Override
    public int getYCentre() {
        return 0;
    }

    @Override
    public Quadrato[] getTetramino() {
        return tetraArray;
    }
    
   @Override
    public Tetramino getTetraGhost(){
        Tetramino t= new TetraI();
        Quadrato [] qu= new Quadrato[4];
        for(int i=0; i<4; i++){
            Quadrato q= new Quadrato(tetraArray[i].getX(), tetraArray[i].getY(), ImageColour.YELLOW);
            qu[i]=q;
        }
        t.setTetramino(qu);
        
        return t;
    }

    @Override
    public void rotateCCW() {
        //La O ovviamente non ruota
    }

    @Override
    public void rotateCW() {
        //La O ovviamente non ruota
    }

    @Override
    public void stop() {
        this.fermo=true;
    }
    
    @Override
    public boolean isNotMoving(){
        return this.fermo;
    }
    
    @Override
    public void move() {
        for (int i=0; i<4; i++){
            tetraArray[i].setY(tetraArray[i].getY()+20);
        }
    }

    @Override
    public void moveLeft() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()-20);
        }
    }

    @Override
    public void moveRight() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()+20);
            }
    }
}
