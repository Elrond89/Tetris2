/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author antonioruffolo
 */
public class HighScorePanel extends JPanel {
    
    private TetrisTopTenScore tetrisScore;
    private Image backgroundImage, backButtonStandard, backButtonHighLighted, backButtonPressed;
    private TetrisPanelsController tetrisPanelsController;
    private TetraButton backButton;
    
    public HighScorePanel (TetrisTopTenScore tetrisScore, TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController=tetrisPanelsController;
        this.tetrisScore=tetrisScore;
        backButton= new TetraButton();
        backButton.setFocusable(false);
        backButton.setActionCommand("backFromHighScore");
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
        c.insets.right=670;
        c.insets.top=400;
        layout.setConstraints(backButton, c);
        this.add(backButton);
        
    }//constructor
    
    private void init(){
        backgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/highScoreImages/HighScoreBackground.png"));
        backButtonStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/highScoreImages/BackStandard.png"));
        backButtonHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/highScoreImages/BackHighLighted.png"));
        backButtonPressed= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/highScoreImages/BackPressed.png"));
        
        backButton.getImageArray()[0]= new ImageIcon(backButtonStandard);
        backButton.getImageArray()[1]= new ImageIcon(backButtonHighLighted);
        backButton.getImageArray()[2]= new ImageIcon(backButtonPressed);
        backButton.initImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawImage(backgroundImage, 0, 0, this);
        
        if (tetrisScore == null) {
            return;
        }
        InputStream is= this.getClass().getResourceAsStream("/tetris/images/fonts/Synchro_LET.ttf");
        Font font;
        Font ttfReal = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            ttfReal = font.deriveFont(Font.PLAIN, 16);
            is.close();
        } catch (Exception ex) {
            Logger.getLogger(HighScorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Font font2= new Font("Synchro LET", Font.BOLD, 16);*/
        /*if (System.getProperty("os.name").startsWith("Windows")){
            font= new Font("OCR A Std", Font.BOLD, 12);
        }*/
        g2.setFont(ttfReal);
        g2.setColor(Color.white);
        int y=150;
        for (int i=0; i<tetrisScore.getArray().length; i++){
            if (tetrisScore.getArray()[i] != null){
                g2.drawString(tetrisScore.getArray()[i].getPlayerName().toUpperCase(), 271, y);
                g2.drawString((""+tetrisScore.getArray()[i].getPlayerScore()).toUpperCase(), 507, y);
                g2.drawString(tetrisScore.getArray()[i].timeInString().toUpperCase(), 630, y);
                y+=20;
            }
            else{
                return;
            }
        }
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
    
}//HighScorePanel
