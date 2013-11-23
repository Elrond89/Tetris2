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
 *
 * @author antonioruffolo
 */
public class PlayerModePanel extends JPanel {
    
    private TetraButton singlePlayerButton, twoPlayersButton, backButton;
    private Image singlePlayerStandard, singlePlayerHighlighted, singlePlayerInverted, twoPlayersStandard, twoPlayersHighlighted,
            twoPlayersInverted, backStandard, backHighLighted, backInverted, backgroundImage;
    private TetrisPanelsController tetrisPanelsController;
    
    public PlayerModePanel(){
        setSize(800, 600);
        setDoubleBuffered(true);
        
        singlePlayerButton= new TetraButton();
        singlePlayerButton.addMouseListener(new MouseAdapter());
        twoPlayersButton= new TetraButton();
        twoPlayersButton.addMouseListener(new MouseAdapter());
        
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
        layout.setConstraints(singlePlayerButton, c);
        this.add(singlePlayerButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=80;
        c.insets.right=400;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(twoPlayersButton, c);
        this.add(twoPlayersButton);
        
    }//constructor
    
    public PlayerModePanel(TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController=tetrisPanelsController;
        
        setSize(800, 600);
        setDoubleBuffered(true);
        
        singlePlayerButton= new TetraButton();
        singlePlayerButton.setActionCommand("singlePlayer");
        singlePlayerButton.addActionListener(this.tetrisPanelsController);
        singlePlayerButton.addMouseListener(new MouseAdapter());
        twoPlayersButton= new TetraButton();
        twoPlayersButton.setActionCommand("twoPlayers");
        twoPlayersButton.addActionListener(this.tetrisPanelsController);
        twoPlayersButton.addMouseListener(new MouseAdapter());
        backButton= new TetraButton();
        backButton.setActionCommand("backFromPlayerMode");
        backButton.addActionListener(this.tetrisPanelsController);
        backButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=0;
        c.insets.right=550;
        c.insets.bottom=0;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(singlePlayerButton, c);
        this.add(singlePlayerButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=10;
        c.insets.left=10;
        c.insets.bottom=0;
        c.insets.right=550;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(twoPlayersButton, c);
        this.add(twoPlayersButton);
        
        c.gridx=0;
        c.gridy=2;
        c.insets.top=10;
        c.insets.left=10;
        c.insets.bottom=20;
        c.insets.right=550;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(backButton, c);
        this.add(backButton);
        
    }//constructor
    
    //this.getClass().getClassLoader().getResource("install.xml");
    
    private void init(){
        try{
        backgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/PlayerMode.png"));
        
        singlePlayerStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/SinglePlayerStandard.png"));
        singlePlayerHighlighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/SinglePlayerHighlighted.png"));
        singlePlayerInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/SinglePlayerInverted.png"));
        
        twoPlayersStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/TwoPlayersStandard.png"));
        twoPlayersHighlighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/TwoPlayersHighlighted.png"));
        twoPlayersInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/choosePlayersImages/TwoPlayersInverted.png"));
        
        backStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackStandard.png"));
        backHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackHighLighted.png"));
        backInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("tetris/images/backButtonImages/BackInverted.png"));
        }
        catch (Exception ex){
            System.err.println(ex);
        }
        
        singlePlayerButton.getImageArray()[0] = new ImageIcon(singlePlayerStandard);
        singlePlayerButton.getImageArray()[1] = new ImageIcon(singlePlayerHighlighted);
        singlePlayerButton.getImageArray()[2] = new ImageIcon(singlePlayerInverted);
        singlePlayerButton.initImage();
        
        twoPlayersButton.getImageArray()[0]= new ImageIcon(twoPlayersStandard);
        twoPlayersButton.getImageArray()[1]= new ImageIcon(twoPlayersHighlighted);
        twoPlayersButton.getImageArray()[2]= new ImageIcon(twoPlayersInverted);
        twoPlayersButton.initImage();
        
        backButton.getImageArray()[0]= new ImageIcon(backStandard);
        backButton.getImageArray()[1]= new ImageIcon(backHighLighted);
        backButton.getImageArray()[2]= new ImageIcon(backInverted);
        backButton.initImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
    
   private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==singlePlayerButton){
                singlePlayerButton.mousePressed(me);
            }
            else if (me.getSource()==twoPlayersButton){
                twoPlayersButton.mousePressed(me);
            }
            else if (me.getSource()== backButton){
                backButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==singlePlayerButton){
                singlePlayerButton.mouseReleased(me);
            }
            else if (me.getSource()==twoPlayersButton){
               twoPlayersButton.mouseReleased(me);
            }
            else if (me.getSource()== backButton){
                backButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==singlePlayerButton){
                singlePlayerButton.mouseEntered(me);
            }
            else if (me.getSource()==twoPlayersButton){
                twoPlayersButton.mouseEntered(me);
            }
            else if (me.getSource()== backButton){
                backButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==singlePlayerButton){
                singlePlayerButton.mouseExited(me);
            }
            else if (me.getSource()==twoPlayersButton){
                twoPlayersButton.mouseExited(me);
            }
            else if (me.getSource()== backButton){
                backButton.mouseExited(me);
            }
        }
        
    }//MouseAdapter
    
}
