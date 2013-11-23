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
public class TetraI implements Tetramino, Serializable {
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private transient Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/CyanT.png")),
            greyImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/CyanTGrey.png")),
            invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/CyanTInverted.png"));
    private transient Image ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    private int x_centre, y_centre;
    private boolean fermo;
    
    public TetraI(){
        tetraArray[0]= new Quadrato(60, 0, image, "/tetris/images/CyanT.png", greyImage, "/tetris/images/greyImages/CyanTGrey.png", invertedImage, "/tetris/images/CyanTInverted.png");
        tetraArray[1]= new Quadrato(80, 0, image, "/tetris/images/CyanT.png", greyImage, "/tetris/images/greyImages/CyanTGrey.png", invertedImage, "/tetris/images/CyanTInverted.png");
        tetraArray[2]= new Quadrato(100, 0, image, "/tetris/images/CyanT.png", greyImage, "/tetris/images/greyImages/CyanTGrey.png", invertedImage, "/tetris/images/CyanTInverted.png");
        tetraArray[3]= new Quadrato(120, 0, image, "/tetris/images/CyanT.png", greyImage, "/tetris/images/greyImages/CyanTGrey.png", invertedImage, "/tetris/images/CyanTInverted.png");
        x_centre=100;
        y_centre=0;
    }//costruttore
    
    @Override
    public void setTetramino(Quadrato[]q){
        tetraArray=q;
    }

    @Override
    public Quadrato[] getTetramino() {
        return tetraArray;
    }//getTetramino
    
    @Override
    public int getXCentre(){
        return x_centre;
    }
    
    @Override
    public int getYCentre(){
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
        
        x_centre=tetraArray[2].getX();
        y_centre=tetraArray[2].getY();
        //System.out.println("centro; x:"+x_centro+" y:"+y_centro);
    }//ruota
    
    @Override
    public void rotateCW(){
        int tmpX= 0;
        int tmpY= 0;
        //sposto tutti i blocchi all'origine
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setX(tetraArray[i].getX()-x_centre);
            tetraArray[i].setY(tetraArray[i].getY()-y_centre);
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //System.out.println();
        //ruoto tutti i blocchi
        for (int i=0; i<tetraArray.length; i++){
            tmpX=tetraArray[i].getX();
            tmpY=tetraArray[i].getY();
            tetraArray[i].setX(tmpY);
            tetraArray[i].setY(tmpX*(-1));
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }
        
        
        //riposiziono tutti i blocchi
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setX(tetraArray[i].getX()+x_centre);
            tetraArray[i].setY(tetraArray[i].getY()+y_centre);
        }
        x_centre=tetraArray[2].getX();
        y_centre=tetraArray[2].getY();
    }
    
    @Override
    public void moveLeft(){
        for (int i=0; i<tetraArray.length; i++){
                 tetraArray[i].setX(tetraArray[i].getX()-20);
            }
            x_centre=tetraArray[2].getX();
            y_centre=tetraArray[2].getY();
    }
    
    @Override
    public void moveRight(){
        for (int i=0; i<tetraArray.length; i++){
                tetraArray[i].setX(tetraArray[i].getX()+20);
            }
            x_centre=tetraArray[2].getX();
            y_centre=tetraArray[2].getY();
    }
    
    @Override
    public void move() {
        for (int i=0; i<tetraArray.length; i++){
            tetraArray[i].setY(tetraArray[i].getY()+20);
        }
        x_centre=tetraArray[2].getX();
        y_centre=tetraArray[2].getY();
    }//mouvi
    
    @Override
    public void stop() {
        this.fermo=true;
    }
    
    @Override
    public boolean isNotMoving(){
        return this.fermo;
    }
    
    @Override
    public void loadImages() {
        image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/CyanT.png"));
        greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/CyanTGrey.png"));
        invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/CyanTInverted.png"));
        ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    }
    
}//TetraL
