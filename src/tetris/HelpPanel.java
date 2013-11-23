/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author antonioruffolo
 */
public class HelpPanel extends JPanel {
    
    private Image helpBackgroundImage, backButtonStandard, backButtonHighLighted, backButtonPressed;
    private TetrisPanelsController tetrisPanelsController;
    private TetraButton backButton;
    
    public HelpPanel ( TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController=tetrisPanelsController;
        backButton= new TetraButton();
        backButton.setFocusable(false);
        backButton.setActionCommand("backFromHelp");
        backButton.addActionListener(this.tetrisPanelsController);
        backButton.addMouseListener(new MouseAdapter());
        
        setSize(800, 600);
        setDoubleBuffered(true);
        init();
        
        GridBagLayout layout= new GridBagLayout();
        
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 10;
        c.insets.right=600;
        c.insets.top=415;
        layout.setConstraints(backButton, c);
        this.add(backButton);
        
    }//constructor

    HelpPanel(TetrisPanelsController tetrisPanelsController, Image helpBackgroundImage, Image backButtonStandard, Image backButtonHighLighted, Image backButtonPressed) {
        this.tetrisPanelsController = tetrisPanelsController;
        this.backButtonHighLighted = backButtonHighLighted;
        this.helpBackgroundImage = helpBackgroundImage;
        this.backButtonStandard = backButtonStandard;
        this.backButtonPressed = backButtonPressed;
        
        backButton= new TetraButton();
        backButton.setFocusable(false);
        backButton.setActionCommand("backFromHelp");
        backButton.addActionListener(this.tetrisPanelsController);
        backButton.addMouseListener(new MouseAdapter());
        backButton.getImageArray()[0]= new ImageIcon(backButtonStandard);
        backButton.getImageArray()[1]= new ImageIcon(backButtonHighLighted);
        backButton.getImageArray()[2]= new ImageIcon(backButtonPressed);
        backButton.initImage();
        
        setSize(800, 600);
        setDoubleBuffered(true);
        
        
        GridBagLayout layout= new GridBagLayout();
        
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 10;
        c.insets.right=600;
        c.insets.top=415;
        layout.setConstraints(backButton, c);
        this.add(backButton);
    }
    
    private void init(){
        helpBackgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/highScoreImages/Help.png"));
        backButtonStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackStandard.png"));
        backButtonHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackHighLighted.png"));
        backButtonPressed= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackInverted.png"));
        
        backButton.getImageArray()[0]= new ImageIcon(backButtonStandard);
        backButton.getImageArray()[1]= new ImageIcon(backButtonHighLighted);
        backButton.getImageArray()[2]= new ImageIcon(backButtonPressed);
        backButton.initImage();
    }

    public Image getBackgroundImage() {
        return helpBackgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.helpBackgroundImage = backgroundImage;
    }

    public Image getBackButtonStandard() {
        return backButtonStandard;
    }

    public void setBackButtonStandard(Image backButtonStandard) {
        this.backButtonStandard = backButtonStandard;
    }

    public Image getBackButtonHighLighted() {
        return backButtonHighLighted;
    }

    public void setBackButtonHighLighted(Image backButtonHighLighted) {
        this.backButtonHighLighted = backButtonHighLighted;
    }

    public Image getBackButtonPressed() {
        return backButtonPressed;
    }

    public void setBackButtonPressed(Image backButtonPressed) {
        this.backButtonPressed = backButtonPressed;
    }
    
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(helpBackgroundImage, 0, 0, this);
    }//paintComponent
    
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==backButton){
                backButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==backButton){
                backButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==backButton){
                backButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==backButton){
                backButton.mouseExited(me);
            }
        }
        
    }//MouseAdapter
}
