/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 *
 * @author antonioruffolo
 */
public class TetraS implements Tetramino, Serializable {
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private transient Image image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GreenT.png")),
            greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/GreenTGrey.png")), 
            invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GreenTInverted.png"));
    private transient Image ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    private boolean fermo;
    private int x_centre, y_centre;
    private enum DIRECTION {EAST, WEST, NORTH, SOUTH} //In realta' le direzioni sarebbero solo 2 visto che si alternano 
    //solo due blocchi ma per chiarezza di semantica ne metto 4
    private DIRECTION dir;
    
    public TetraS(){
        tetraArray[0]= new Quadrato(80, 0, image, "/tetris/images/GreenT.png", greyImage,"/tetris/images/greyImages/GreenTGrey.png", invertedImage, "/tetris/images/GreenTInverted.png");
        tetraArray[1]= new Quadrato(100, 0, image, "/tetris/images/GreenT.png", greyImage,"/tetris/images/greyImages/GreenTGrey.png", invertedImage, "/tetris/images/GreenTInverted.png");
        tetraArray[2]= new Quadrato(60, 20, image, "/tetris/images/GreenT.png", greyImage,"/tetris/images/greyImages/GreenTGrey.png", invertedImage, "/tetris/images/GreenTInverted.png");
        tetraArray[3]= new Quadrato(80, 20, image, "/tetris/images/GreenT.png", greyImage,"/tetris/images/greyImages/GreenTGrey.png", invertedImage, "/tetris/images/GreenTInverted.png");
        x_centre=80;
        y_centre=20;
        dir= DIRECTION.EAST;
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
    public int getXCentre() {
        return x_centre;
    }

    @Override
    public int getYCentre() {
        return y_centre;
    }
    
    @Override
    public Tetramino getTetraGhost(){
        Tetramino t= new TetraI();
        Quadrato [] qu= new Quadrato[4];
        for(int i=0; i<4; i++){
            Quadrato q= new Quadrato(tetraArray[i].getX(), tetraArray[i].getY(), ghostImage, "/tetris/images/GhostT.png");
            qu[i]=q;
        }
        t.setTetramino(qu);
        
        return t;
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
        
        if (dir == DIRECTION.EAST){
            dir = DIRECTION.NORTH;
            x_centre=tetraArray[0].getX();
            y_centre=tetraArray[0].getY();
        }
        else if (dir == DIRECTION.SOUTH){
            dir = DIRECTION.EAST;
            x_centre=tetraArray[3].getX();
            y_centre=tetraArray[3].getY();
        }
        else if (dir == DIRECTION.WEST){
            dir = DIRECTION.SOUTH;
            x_centre=tetraArray[0].getX();
            y_centre=tetraArray[0].getY();
        }
        else if (dir == DIRECTION.NORTH){
            dir = DIRECTION.WEST;
            x_centre=tetraArray[3].getX();
            y_centre=tetraArray[3].getY();
        }
        //System.out.println("centro; x:"+x_centro+" y:"+y_centro);
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
        for (int i=0; i<4; i++){
            tetraArray[i].setY(tetraArray[i].getY()+20);
        }
        if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
            x_centre=tetraArray[3].getX();
            y_centre=tetraArray[3].getY();
        }
        else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
            x_centre=tetraArray[0].getX();
            y_centre=tetraArray[0].getY();
        }
    }
    
    @Override
    public void moveLeft() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()-20);
            }
            if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[3].getX();
                y_centre=tetraArray[3].getY();
            }
            else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[0].getX();
                 y_centre=tetraArray[0].getY();
            }
    }

    @Override
    public void moveRight() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()+20);
            }
            if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[3].getX();
                y_centre=tetraArray[3].getY();
            }
            else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[0].getX();
                 y_centre=tetraArray[0].getY();
            }
    }
    
    @Override
    public void loadImages() {
        image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GreenT.png"));
        greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/GreenTGrey.png"));
        invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GreenTInverted.png"));
        ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    }
}
