/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;

/**
 *
 * @author antonioruffolo
 */
public class Quadrato implements Serializable {
    public enum ImageColour {
        CYAN, BLUE, RED, YELLOW, PURPLE, ORANGE, GREEN;
    }
    private ImageColour imageSelected;
    private int x, y;
    private boolean isVisible, useInvertedImage;
    
    public Quadrato (int x, int y, ImageColour imageSelected){
        this.x=x; this.y=y;
        this.imageSelected = imageSelected;
        isVisible=true;
    }
    
    public Quadrato (Quadrato q){
        this.x=q.x;
        this.y=q.y;
        this.isVisible=q.isVisible;
        imageSelected = q.imageSelected;
    }

    public ImageColour getImageSelected() {
        return imageSelected;
    }

    public void setImageSelected(ImageColour imageSelected) {
        this.imageSelected = imageSelected;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
        
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public boolean isVisible(){
        return this.isVisible;
    }
    
    public void setVisible(boolean isVisible){
        this.isVisible=isVisible;
    }
    
    public boolean useInvertedImage() {
        return useInvertedImage;
    }

    public void setUseInvertedImage(boolean useInvertedImage) {
        this.useInvertedImage = useInvertedImage;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }
    
    public static void main (String args[]){
        Quadrato q= new Quadrato(2,3, ImageColour.BLUE);
        System.out.println(q.getX()+" "+q.getY());
        Quadrato q2= new Quadrato(q);
        System.out.println(q2.getX()+" "+q2.getY());
    }
}//Quadrato
