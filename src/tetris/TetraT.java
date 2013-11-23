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
public class TetraT implements Tetramino, Serializable{
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private int x_centre, y_centre;
    private boolean fermo;
    
    public TetraT(){
        tetraArray[0]= new Quadrato(60, 0, ImageColour.PURPLE);
        tetraArray[1]= new Quadrato(80, 0, ImageColour.PURPLE);
        tetraArray[2]= new Quadrato(100, 0, ImageColour.PURPLE);
        tetraArray[3]= new Quadrato(80, 20, ImageColour.PURPLE);
        x_centre=80;
        y_centre=0;
    }
    
    @Override
    public void setTetramino(Quadrato[]q){
        tetraArray=q;
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
            Quadrato q= new Quadrato(tetraArray[i].getX(), tetraArray[i].getY(), ImageColour.PURPLE);
            qu[i]=q;
        }
        t.setTetramino(qu);
        
        return t;
    }
    
    @Override
    public int getXCentre() {
        return x_centre;
    }

    @Override
    public int getYCentre() {
        return y_centre;
    }

    @Override
    public void rotateCCW() {
        int tmpX= 0;
        int tmpY= 0;
        //sposto tutti i blocchi all'origine
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setX(tetraArray[i].getX()-x_centre);
            tetraArray[i].setY(tetraArray[i].getY()-y_centre);
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //System.out.println("Routo sull'origine");
        //ruoto tutti i blocchi
        for (int i=0; i<tetraArray.length; i++){
            tmpX=tetraArray[i].getX();
            tmpY=tetraArray[i].getY();
            tetraArray[i].setX(tmpY*(-1));
            tetraArray[i].setY(tmpX);
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }
        
        
        //riposiziono tutti i blocchi
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setX(tetraArray[i].getX()+x_centre);
            tetraArray[i].setY(tetraArray[i].getY()+y_centre);
        }
        
        x_centre=tetraArray[1].getX();
        y_centre=tetraArray[1].getY();
    }

    @Override
    public void rotateCW() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setY(tetraArray[i].getY()+20);
        }
        x_centre=tetraArray[1].getX();
        y_centre=tetraArray[1].getY();
    }

    @Override
    public void moveLeft() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()-20);
            }
        x_centre=tetraArray[1].getX();
        y_centre=tetraArray[1].getY();
    }

    @Override
    public void moveRight() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()+20);
        }
        x_centre=tetraArray[1].getX();
        y_centre=tetraArray[1].getY();
    }
}
