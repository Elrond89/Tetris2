/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

/**
 *
 * @author antonioruffolo
 */
public class TetraRadioButton extends JRadioButton implements MouseListener {
    private ImageIcon [] imageArray= new ImageIcon[3];//In 0 c'e' l'immagine standard, in 1 quella highlighted e in 2 quella inverted
    private boolean isActivated;
     private BufferedImage bufImg;
    
    public TetraRadioButton(){
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
    
    public boolean isActivated(){
        return isActivated;
    }
    
    public void setIsActivated(boolean isActivated){
        this.isActivated=isActivated;
    }
    
    public ImageIcon [] getImageArray(){
        return this.imageArray;
    }
    
    public void setArray(ImageIcon [] imageArray){
        this.imageArray=imageArray;
    }
    
    public void initImage(){
        this.setIcon(imageArray[0]);
        getBufImg();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        this.setIcon(imageArray[2]);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //this.setIcon(new ImageIcon(imageArray[2]));
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //this.setIcon(new ImageIcon(imageArray[2]));
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
        //if (clickAlphaValue(bufImg, me.getX(), me.getY()) != 0){
            if (!isActivated)
                this.setIcon(imageArray[1]);
        //}
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //if (clickAlphaValue(bufImg, me.getX(), me.getY()) != 0){
            if(!isActivated)
                this.setIcon(imageArray[0]);
        //}
    }
    
    
    private void getBufImg()   {
      bufImg = new BufferedImage(imageArray[0].getImage().getWidth(this), imageArray[0].getImage().getHeight(this), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
      Graphics2D g2d = bufImg.createGraphics();
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

      g2d.drawImage(imageArray[0].getImage(), null, null);
      //0, 0, imageArray[0].getImage().getWidth(this), imageArray[0].getImage().getHeight(this), null
      g2d.dispose();
    }
    
    private int clickAlphaValue(BufferedImage bufImg, int posX, int posY){
        int alpha;

        alpha = (bufImg.getRGB(posX, posY) >> 24) & 0x000000FF; //Gets the bit that contains alpha information

        return alpha;
    }
}
