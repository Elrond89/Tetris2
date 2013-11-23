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
    private int x, y;
    private transient Image image, greyImage, invertedImage;
    private boolean isVisible, useInvertedImage;
    private String imagePath, greyImagePath, invertedImagePath;
    
    public Quadrato (int x, int y, Image image){
        this.x=x; this.y=y; this.image=image;
        isVisible=true;
    }
    
    public Quadrato (int x, int y, Image image, String imagePath){
        this.x=x; this.y=y; this.image=image;
        this.imagePath=imagePath;
        isVisible=true;
    }
    
    public Quadrato (int x, int y, Image image, String imagePath, Image greyImage, String greyImagePath, Image invertedImage, String invertedImagePath){
        this.x=x; this.y=y; 
        this.image=image;
        this.greyImage=greyImage;
        this.invertedImage = invertedImage;
        this.imagePath=imagePath;
        this.greyImagePath= greyImagePath;
        this.invertedImagePath = invertedImagePath;
        isVisible=true;
    }
    
    public Quadrato (Quadrato q){
        this.x=q.x;
        this.y=q.y;
        this.image=q.image;
        this.isVisible=q.isVisible;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public Image getGreyImage(){
        return this.greyImage;
    }
    
    public void setImage(Image image){
        this.image=image;
    }
    
    public void setGreyImage(Image greyImage){
        this.greyImage=greyImage;
    }

    public Image getInvertedImage() {
        return invertedImage;
    }

    public void setInvertedImage(Image invertedImage) {
        this.invertedImage = invertedImage;
    }

    public String getInvertedImagePath() {
        return invertedImagePath;
    }

    public void setInvertedImagePath(String invertedImagePath) {
        this.invertedImagePath = invertedImagePath;
    }
    
    
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public void setImagePath(String imagePath){
        this.imagePath=imagePath;
    }
    
    public void setGreyImagePath(String greyImagePath){
        this.greyImagePath=greyImagePath;
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
    
    public String getImagePath(){
        return this.imagePath;
    }
    
    public String getGreyImagePath(){
        return this.greyImagePath;
    }
    
    /**
     * Ricarica l'immagine, il metodo serve a ricare le immagini in memoria a seguito di un salvataggio
     */
    public void loadImages(){
        this.image=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(imagePath));
        this.invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(invertedImagePath));
        this.greyImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(greyImagePath));
    }
    
    public void loadImage(){
        this.image=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(imagePath));
    }
    
    public static void main (String args[]){
        Quadrato q= new Quadrato(2,3,null);
        System.out.println(q.getX()+" "+q.getY());
        Quadrato q2= new Quadrato(q);
        System.out.println(q2.getX()+" "+q2.getY());
    }
}//Quadrato
