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
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author antonioruffolo
 */
public class ResumeGameModePanel extends JPanel {
    
    private TetraButton marathonButton, sprintButton, ultraButton, backButton;
    private Image backgroundImage, marathonStandard, marathonHighlighted, marathonInverted, sprintStandard, sprintHighlighted, sprintInverted,
            ultraStandard, ultraHighlighted, ultraInverted, backStandard, backHighLighted, backInverted;
    private TetrisPanelsController tetrisPanelsController;
    private boolean twoPlayers;
    
    public ResumeGameModePanel(){
        setSize(800, 600);
        setDoubleBuffered(true);
        
        marathonButton= new TetraButton();
        marathonButton.addMouseListener(new MouseAdapter());
        sprintButton= new TetraButton();
        sprintButton.addMouseListener(new MouseAdapter());
        ultraButton= new TetraButton();
        ultraButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=70;
        c.insets.right=565;
        c.insets.bottom=10;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(marathonButton, c);
        this.add(marathonButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=565;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(sprintButton, c);
        this.add(sprintButton);
        
        c.gridx=0;
        c.gridy=2;
        c.insets.left=10;
        c.insets.bottom=100;
        c.insets.right=565;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(ultraButton, c);
        this.add(ultraButton);
    }//constructor
    
    public ResumeGameModePanel(TetrisPanelsController tetrisPanelsController, boolean twoPlayers){
        this.tetrisPanelsController= tetrisPanelsController;
        this.twoPlayers=twoPlayers;
        
        setSize(800, 600);
        setDoubleBuffered(true);
        
        marathonButton= new TetraButton();
        marathonButton.setActionCommand("resumeMarathon");
        marathonButton.addActionListener(this.tetrisPanelsController);
        marathonButton.addMouseListener(new MouseAdapter());
        sprintButton= new TetraButton();
        sprintButton.setActionCommand("resumeSprint");
        sprintButton.addActionListener(this.tetrisPanelsController);
        sprintButton.addMouseListener(new MouseAdapter());
        ultraButton= new TetraButton();
        ultraButton.setActionCommand("resumeUltra");
        ultraButton.addActionListener(this.tetrisPanelsController);
        ultraButton.addMouseListener(new MouseAdapter());
        backButton= new TetraButton();
        backButton.setActionCommand("backFromResumeGameMode");
        backButton.addActionListener(this.tetrisPanelsController);
        backButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=70;
        c.insets.right=565;
        c.insets.bottom=10;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(marathonButton, c);
        this.add(marathonButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=565;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(sprintButton, c);
        this.add(sprintButton);
        
        c.gridx=0;
        c.gridy=2;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=565;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(ultraButton, c);
        this.add(ultraButton);
        
        c.gridx=0;
        c.gridy=3;
        c.insets.left=10;
        c.insets.bottom=100;
        c.insets.right=565;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(backButton, c);
        this.add(backButton);
    }//constructor
    
    private void init(){
        backgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/ChooseYourChallenge.png"));
        
        marathonStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/MarathonStandard.png"));
        marathonHighlighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/MarathonHighlighted.png"));
        marathonInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/MarathonInverted.png"));
        
        sprintStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/SprintStandard.png"));
        sprintHighlighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/SprintHighlighted.png"));
        sprintInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/SprintInverted.png"));
        
        ultraStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/UltraStandard.png"));
        ultraHighlighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/UltraHighlighted.png"));
        ultraInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/chooseChallengeImages/UltraInverted.png"));
        
        backStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backButtonImages/BackStandard.png"));
        backHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backButtonImages/BackHighLighted.png"));
        backInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backButtonImages/BackInverted.png"));
        
        marathonButton.getImageArray()[0]= new ImageIcon(marathonStandard);
        marathonButton.getImageArray()[1]= new ImageIcon(marathonHighlighted);
        marathonButton.getImageArray()[2]= new ImageIcon(marathonInverted);
        marathonButton.initImage();
        if (!checkMarathonSaved()) marathonButton.setEnabled(false);
        
        sprintButton.getImageArray()[0]= new ImageIcon(sprintStandard);
        sprintButton.getImageArray()[1]= new ImageIcon(sprintHighlighted);
        sprintButton.getImageArray()[2]= new ImageIcon(sprintInverted);
        sprintButton.initImage();
        if (!checkSprintSaved()) sprintButton.setEnabled(false);
        
        ultraButton.getImageArray()[0]= new ImageIcon(ultraStandard);
        ultraButton.getImageArray()[1]= new ImageIcon(ultraHighlighted);
        ultraButton.getImageArray()[2]= new ImageIcon(ultraInverted);
        ultraButton.initImage();
        if (!checkUltraSaved()) ultraButton.setEnabled(false);
        
        backButton.getImageArray()[0]= new ImageIcon(backStandard);
        backButton.getImageArray()[1]= new ImageIcon(backHighLighted);
        backButton.getImageArray()[2]= new ImageIcon(backInverted);
        backButton.initImage();
        
    }//init
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
    
    private boolean checkMarathonSaved(){
        File marathonFile=null;
        if (!twoPlayers){
            marathonFile= new File("data/single_player_marathon.game");
        }
        else {
            marathonFile= new File("data/two_players_marathon.game");
        }
        if (marathonFile.exists()) return true;
        return false;
    }
    
    private boolean checkSprintSaved(){
        File sprintFile=null;
        
        if (!twoPlayers){
            sprintFile= new File("data/single_player_sprint.game");
        }
        else {
            sprintFile= new File("data/two_players_sprint.game");
        }
        if (sprintFile.exists()) return true;
        return false;
    }
    
    private boolean checkUltraSaved(){
        File ultraFile= null;
        if (!twoPlayers){
            ultraFile= new File("data/single_player_ultra.game");
        }
        else {
            ultraFile= new File("data/two_players_ultra.game");
        }
        if (ultraFile.exists()) return true;
        return false;
    }
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==marathonButton){
                marathonButton.mousePressed(me);
            }
            else if (me.getSource()==sprintButton){
                sprintButton.mousePressed(me);
            }
            else if (me.getSource()==ultraButton){
                ultraButton.mousePressed(me);
            }
            else if (me.getSource()==backButton){
                backButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==marathonButton){
                marathonButton.mouseReleased(me);
            }
            else if (me.getSource()==sprintButton){
                sprintButton.mouseReleased(me);
            }
            else if (me.getSource()==ultraButton){
                ultraButton.mouseReleased(me);
            }
            else if (me.getSource()==backButton){
                backButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==marathonButton){
                marathonButton.mouseEntered(me);
            }
            else if (me.getSource()==sprintButton){
                sprintButton.mouseEntered(me);
            }
            else if (me.getSource()==ultraButton){
                ultraButton.mouseEntered(me);
            }
            else if (me.getSource()==backButton){
                backButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==marathonButton){
                marathonButton.mouseExited(me);
            }
            else if (me.getSource()==sprintButton){
                sprintButton.mouseExited(me);
            }
            else if (me.getSource()==ultraButton){
                ultraButton.mouseExited(me);
            }
            else if (me.getSource()==backButton){
                backButton.mouseExited(me);
            }
            
        }
        
    }//MouseAdapter
}