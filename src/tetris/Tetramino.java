/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author antonioruffolo
 */
public interface Tetramino {
    
    public void move(); //mouve tutto il tetramino
    public Quadrato[] getTetramino();
    public Tetramino getTetraGhost();
    public void setTetramino (Quadrato[] q);
    public int getXCentre();
    public int getYCentre();
    public void rotateCCW();
    public void rotateCW();
    public void moveLeft();
    public void moveRight();
    public boolean isNotMoving();
    public void stop();
    public void loadImages();
}
