/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author AntonioRuffolo
 */
public class TetraButton extends JButton implements MouseListener {

    
    private ImageIcon [] imageArray= new ImageIcon[3];//In 0 c'e' l'immagine standard, in 1 quella highlighted e in 2 quella inverted
    private BufferedImage bufImg;
    
    public TetraButton(){
        //this.setIcon(new ImageIcon(imageArray[0]));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
    
    public ImageIcon [] getImageArray(){
        return this.imageArray;
    }
    
    public void setArray(ImageIcon [] imageArray){
        this.imageArray=imageArray;
    }
    
    public void setBufferedImage(BufferedImage bufImg){
        this.bufImg=bufImg;
    }
    
    public void initImage(){
        this.setIcon(imageArray[0]);
    }
    
    public BufferedImage getBufImg(){
        
        /*BufferedImage newImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB); //Create a new buffered image the right size
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(bufImg, 0, 0, this.getWidth(), this.getHeight(), null);
        g2d.dispose();

        return newImg;*/
        return bufImg;
        
    }//getBufImg
    
    private int clickAlphaValue(BufferedImage bufImg, int posX, int posY) {
        int alpha=0;
        //System.out.println("w:"+bufImg.getWidth()+" h:"+bufImg.getHeight()+" x:"+posX+" posY:"+posY);

        alpha = (bufImg.getRGB(posX, posY) >>24) & 0x000000FF; //Gets the bit that contains alpha information

        return alpha;
    }
    
    @Override
    public void paint(Graphics g) {
        /*if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            setContentAreaFilled(false);
            setBorder(null);
            } else {
            setOpaque(true);
            }*/
            super.paint(g);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) { 
        //if (clickAlphaValue(this.getBufImg(), me.getX(), me.getButton()) != 0)
            this.setIcon(imageArray[2]);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //if (clickAlphaValue(this.getBufImg(), me.getX(), me.getButton()) != 0)
            this.setIcon(imageArray[0]);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //if (clickAlphaValue(this.getBufImg(), me.getX(), me.getButton()) != 0)
            this.setIcon(imageArray[1]);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //if (clickAlphaValue(this.getBufImg(), me.getX(), me.getButton()) != 0)
            this.setIcon(imageArray[0]);
    }
    
}
