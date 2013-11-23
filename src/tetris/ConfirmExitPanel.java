/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Chiede conferma al "comrade" dell'eventuale uscita dal programma.
 * @author antonioruffolo
 */
public class ConfirmExitPanel extends JPanel {
    private TetraButton yesButton, noButton;
    
    private Image backgroundImage, yesStandard, yesHighLighted, yesInverted, noStandard, noHighLighted, noInverted;
    
    private TetrisPanelsController tetrisPanelsController;
    
    public ConfirmExitPanel(){
        setSize(800, 600);
        setDoubleBuffered(true);
        
        yesButton= new TetraButton();
        yesButton.addMouseListener(new MouseAdapter());
        noButton= new TetraButton();
        noButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=70;
        c.insets.right=400;
        c.insets.bottom=10;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(yesButton, c);
        this.add(yesButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=80;
        c.insets.right=400;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(noButton, c);
        this.add(noButton);
    }
    
    public ConfirmExitPanel(TetrisPanelsController tetrisPanelsController ){
        this.tetrisPanelsController= tetrisPanelsController;
        
        setSize(800, 600);
        setDoubleBuffered(true);
        
        yesButton= new TetraButton();
        yesButton.addMouseListener(new MouseAdapter());
        yesButton.setActionCommand("yesExitFromApplication");
        yesButton.addActionListener(this.tetrisPanelsController);
        noButton= new TetraButton();
        noButton.addMouseListener(new MouseAdapter());
        noButton.setActionCommand("noExitFromApplication");
        noButton.addActionListener(this.tetrisPanelsController);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=200;
        c.insets.top=200;
        c.insets.right=40;
        c.insets.bottom=80;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(yesButton, c);
        this.add(yesButton);
        
        c.gridx=1;
        c.gridy=0;
        c.insets.top=200;
        c.insets.left=10;
        c.insets.bottom=80;
        c.insets.right=625;
        c.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(noButton, c);
        this.add(noButton);
    }
    
    private void init(){
        backgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/SaintBasilExit.png"));
        
        yesStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/YesButtonStandard.png"));
        yesHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/YesButtonHighLighted.png"));
        yesInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/YesButtonInverted.png"));
        
        noStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/NoButtonStandard.png"));
        noHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/NoButtonHighLighted.png"));
        noInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/confirmExitImages/NoButtonInverted.png"));
        
        yesButton.getImageArray()[0]= new ImageIcon(yesStandard);
        yesButton.getImageArray()[1]= new ImageIcon(yesHighLighted);
        yesButton.getImageArray()[2]= new ImageIcon(yesInverted);
        yesButton.initImage();
        
        noButton.getImageArray()[0]= new ImageIcon(noStandard);
        noButton.getImageArray()[1]= new ImageIcon(noHighLighted);
        noButton.getImageArray()[2]= new ImageIcon(noInverted);
        noButton.initImage();
    }
    
    @Override
        public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }//paintComponent
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==yesButton){
                yesButton.mousePressed(me);
            }
            else if (me.getSource()==noButton){
                noButton.mousePressed(me);
            }     
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==yesButton){
                yesButton.mouseReleased(me);
            }
            else if (me.getSource()==noButton){
               noButton.mouseReleased(me);
            } 
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==yesButton){
                yesButton.mouseEntered(me);
            }
            else if (me.getSource()==noButton){
                noButton.mouseEntered(me);
            } 
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==yesButton){
                yesButton.mouseExited(me);
            }
            else if (me.getSource()==noButton){
                noButton.mouseExited(me);
            } 
        }
        
    }//MouseAdapter
}
