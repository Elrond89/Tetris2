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
public class TetraZ implements Tetramino, Serializable{
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private transient Image image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/RedT.png")),
            greyImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/RedTGrey.png")),
            invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/RedTInverted.png"));
    private transient Image ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    private int x_centre, y_centre;
    private boolean fermo;
    private enum DIRECTION {EAST, WEST, NORTH, SOUTH} //In realta' le direzioni sarebbero solo 2 visto che si alternano 
    //solo due blocchi (come blocchi di rotazione) ma per chiarezza di semantica ne metto 4
    private DIRECTION dir;
    
    public TetraZ(){
        tetraArray[0]= new Quadrato(60, 0, image, "/tetris/images/RedT.png", greyImage, "/tetris/images/greyImages/RedTGrey.png", invertedImage, "/tetris/images/RedTInverted.png");
        tetraArray[1]= new Quadrato(80, 0, image, "/tetris/images/RedT.png", greyImage, "/tetris/images/greyImages/RedTGrey.png", invertedImage, "/tetris/images/RedTInverted.png");
        tetraArray[2]= new Quadrato(80, 20, image, "/tetris/images/RedT.png", greyImage, "/tetris/images/greyImages/RedTGrey.png", invertedImage, "/tetris/images/RedTInverted.png");
        tetraArray[3]= new Quadrato(100, 20, image, "/tetris/images/RedT.png", greyImage, "/tetris/images/greyImages/RedTGrey.png", invertedImage, "/tetris/images/RedTInverted.png");
        x_centre=80;
        y_centre=20;
        dir= DIRECTION.WEST;
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
            Quadrato q= new Quadrato(tetraArray[i].getX(), tetraArray[i].getY(), ghostImage, "/tetris/images/GhostT.png");
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
        
        if (dir == DIRECTION.WEST){
            dir = DIRECTION.SOUTH;
            x_centre=tetraArray[1].getX();
            y_centre=tetraArray[1].getY();
        }
        else if (dir == DIRECTION.SOUTH){
            dir = DIRECTION.EAST;
            x_centre=tetraArray[2].getX();
            y_centre=tetraArray[2].getY();
        }
        else if (dir == DIRECTION.EAST){
            dir = DIRECTION.NORTH;
            x_centre=tetraArray[1].getX();
            y_centre=tetraArray[1].getY();
        }
        else if (dir == DIRECTION.NORTH){
            dir = DIRECTION.WEST;
            x_centre=tetraArray[2].getX();
            y_centre=tetraArray[2].getY();
        }
    }

    @Override
    public void rotateCW() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*@Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT){
            for (int i=0; i<tetraArray.length; i++){
                if( tetraArray[i].getX()<20) {
                    return;
                }
            }//verifico che nessuno dei blocchi oltrepassi il muro sinistro
            
            for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()-20);
            }
            if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[2].getX();
                y_centre=tetraArray[2].getY();
            }
            else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[1].getX();
                 y_centre=tetraArray[1].getY();
            }
            
        }
        
        if (key == KeyEvent.VK_RIGHT){
            
            
            for (int i=0; i<tetraArray.length; i++){
                
                if( tetraArray[i].getX()>160) {
                    return;
                }
            }//verifico che nessuno dei blocchi oltrepassi il muro destro
            
            
            for (int i=0; i<tetraArray.length; i++){
                 tetraArray[i].setX(tetraArray[i].getX()+20);
            }
            if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[2].getX();
                y_centre=tetraArray[2].getY();
            }
            else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[1].getX();
                 y_centre=tetraArray[1].getY();
            }
            //System.out.println(tetraArray[3].getX()+20);
        }
        
        if (key == KeyEvent.VK_DOWN){
            for(int i=0; i<tetraArray.length; i++){
                if(tetraArray[i].getY()>400){
                    fermo=true;
                    animator.interrupt();
                    return;
                }
            }
            
            for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setY(tetraArray[i].getY()+20);
            }
            if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[2].getX();
                y_centre=tetraArray[2].getY();
            }
            else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                x_centre=tetraArray[1].getX();
                y_centre=tetraArray[1].getY();
            }
        } 
        
        if (key == KeyEvent.VK_UP){
            if(this.allowedToRotate())
                this.rotateCCW();
        }
    }//keyPressed*/
    
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
            x_centre=tetraArray[2].getX();
            y_centre=tetraArray[2].getY();
        }
        else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
            x_centre=tetraArray[1].getX();
            y_centre=tetraArray[1].getY();
        }
    }
    
    @Override
    public void moveLeft() {
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()-20);
        }
        if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[2].getX();
                y_centre=tetraArray[2].getY();
        }
        else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[1].getX();
                 y_centre=tetraArray[1].getY();
        }
    }

    @Override
    public void moveRight() {
        for (int i=0; i<tetraArray.length; i++){
                 tetraArray[i].setX(tetraArray[i].getX()+20);
        }
        if (dir == DIRECTION.EAST || dir == DIRECTION.WEST){
                x_centre=tetraArray[2].getX();
                y_centre=tetraArray[2].getY();
        }
        else if (dir == DIRECTION.SOUTH || dir == DIRECTION.NORTH){
                 x_centre=tetraArray[1].getX();
                 y_centre=tetraArray[1].getY();
        }
    }
    
    @Override
    public void loadImages() {
        image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/RedT.png"));
        greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/RedTGrey.png"));
        invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/RedTInverted.png"));
        ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    }
}
