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
public class TetraO implements Tetramino, Serializable {
    
    private Quadrato[] tetraArray= new Quadrato[4];
    private transient Image image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/YellowT.png")),
            greyImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/YellowTGrey.png")),
            invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/YellowTInverted.png"));
    private transient Image ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    private boolean fermo;
    
    public TetraO(){
        tetraArray[0] = new Quadrato(80, 0, image, "/tetris/images/YellowT.png", greyImage, "/tetris/images/greyImages/YellowTGrey.png", invertedImage, "/tetris/images/YellowTInverted.png");
        tetraArray[1] = new Quadrato(100, 0, image, "/tetris/images/YellowT.png", greyImage, "/tetris/images/greyImages/YellowTGrey.png", invertedImage, "/tetris/images/YellowTInverted.png");
        tetraArray[2] = new Quadrato(80, 20, image, "/tetris/images/YellowT.png", greyImage, "/tetris/images/greyImages/YellowTGrey.png", invertedImage, "/tetris/images/YellowTInverted.png");
        tetraArray[3] = new Quadrato(100, 20, image, "/tetris/images/YellowT.png", greyImage, "/tetris/images/greyImages/YellowTGrey.png", invertedImage, "/tetris/images/YellowTInverted.png");
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
            Quadrato q= new Quadrato(tetraArray[i].getX(), tetraArray[i].getY(), ghostImage, "/tetris/images/GhostT.png");
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

    @Override
    public void loadImages() {
        image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/YellowT.png"));
        greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/greyImages/YellowTGrey.png"));
        invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/YellowTInverted.png"));
        ghostImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GhostT.png"));
    }

}
