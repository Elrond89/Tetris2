/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author AntonioRuffolo
 */
public class MainMenuPanel extends JPanel {
    
    private TetraButton newGameButton, optionsButton, highScoreButton, helpButton, exitButton, resumeGameButton;
    private Image backgroundImage, newGameStandard, newGameHighLighted, newGameInverted, optionsStandard, optionsHighLighted, optionsInverted, highScoreStandard,
            highScoreHighLighted, highScoreInverted, helpStandard, helpHighLighted, helpInverted,
            exitStandard, exitHighLighted, exitInverted, resumeGameStandard, resumeGameHighLighted, resumeGameInverted;
    private TetrisPanelsController tetrisPanelController;
    private MouseAdapter mouseAdapter= new MouseAdapter();
    
    public MainMenuPanel(){
        setSize(800, 600);
        setDoubleBuffered(true);
        
        newGameButton= new TetraButton();
        newGameButton.addMouseListener(new MouseAdapter());
        optionsButton= new TetraButton();
        optionsButton.addMouseListener(new MouseAdapter());
        highScoreButton= new TetraButton();
        highScoreButton.addMouseListener(new MouseAdapter());
        helpButton= new TetraButton();
        helpButton.addMouseListener(new MouseAdapter());
        exitButton= new TetraButton();
        exitButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=70;
        c.insets.right=500;
        c.insets.bottom=10;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(newGameButton, c);
        this.add(newGameButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(optionsButton, c);
        this.add(optionsButton);
        
        c.gridx=0;
        c.gridy=2;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(highScoreButton, c);
        this.add(highScoreButton);
        
        c.gridx=0;
        c.gridy=3;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(helpButton, c);
        this.add(helpButton);
        
        c.gridx=0;
        c.gridy=4;
        c.insets.left=10;
        c.insets.bottom=30;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(exitButton, c);
        this.add(exitButton);
    }
    
    public MainMenuPanel(TetrisPanelsController tetrisPanelController){
        this.tetrisPanelController=tetrisPanelController;
        
        setSize(800, 600);
        setDoubleBuffered(true);
        
        newGameButton= new TetraButton();
        newGameButton.setActionCommand("newGame");
        newGameButton.addActionListener(this.tetrisPanelController);
        newGameButton.addMouseListener(mouseAdapter);
        resumeGameButton= new TetraButton();
        resumeGameButton.setActionCommand("resumeGame");
        resumeGameButton.addActionListener(this.tetrisPanelController);
        resumeGameButton.addMouseListener(mouseAdapter);
        optionsButton= new TetraButton();
        optionsButton.setActionCommand("options");
        optionsButton.addActionListener(this.tetrisPanelController);
        optionsButton.addMouseListener(mouseAdapter);
        highScoreButton= new TetraButton();
        highScoreButton.setActionCommand("highScore");
        highScoreButton.addActionListener(this.tetrisPanelController);
        highScoreButton.addMouseListener(mouseAdapter);
        helpButton= new TetraButton();
        helpButton.setActionCommand("help");
        helpButton.addActionListener(this.tetrisPanelController);
        helpButton.addMouseListener(mouseAdapter);
        exitButton= new TetraButton();
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this.tetrisPanelController);
        exitButton.addMouseListener(mouseAdapter);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;
        c.insets.left=10;
        c.insets.top=70;
        c.insets.right=500;
        c.insets.bottom=10;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(newGameButton, c);
        this.add(newGameButton);
        
        c.gridx=0;
        c.gridy=1;
        c.insets.top=0;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(resumeGameButton, c);
        this.add(resumeGameButton);
        
        c.gridx=0;
        c.gridy=2;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(highScoreButton, c);
        this.add(highScoreButton);
        
        c.gridx=0;
        c.gridy=3;
        c.insets.left=10;
        c.insets.bottom=10;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(helpButton, c);
        this.add(helpButton);
        
        c.gridx=0;
        c.gridy=4;
        c.insets.left=10;
        c.insets.bottom=30;
        c.insets.right=500;
        c.anchor = GridBagConstraints.WEST;
        layout.setConstraints(exitButton, c);
        this.add(exitButton);
    }
    
    private void init(){
        super.addNotify();
        
        backgroundImage= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/SaintBasil.png"));
        
        newGameStandard=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/NewGameStandard.png"));
        newGameHighLighted=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/NewGameHighLighted.png"));
        newGameInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/NewGameInverted.png"));
        
        optionsStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/OptionsStandard.png"));
        optionsHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/OptionsHighLighted.png"));
        optionsInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/OptionsInverted.png"));
        
        highScoreStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HighScoreStandard.png"));
        highScoreHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HighScoreHighLighted.png"));
        highScoreInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HighScoreInverted.png"));
        
        helpStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HelpStandard.png"));
        helpHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HelpHighLighted.png"));
        helpInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/HelpInverted.png"));
        
        exitStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ExitStandard.png"));
        exitHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ExitHighLighted.png"));
        exitInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ExitInverted.png"));
        
        resumeGameStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ResumeGameStandard.png"));
        resumeGameHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ResumeGameHighLighted.png"));
        resumeGameInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/mainMenuImages/ResumeGameInverted.png"));
        
        
        newGameButton.getImageArray()[0]= new ImageIcon(newGameStandard);
        newGameButton.getImageArray()[1]= new ImageIcon(newGameHighLighted);
        newGameButton.getImageArray()[2]= new ImageIcon(newGameInverted);
        newGameButton.initImage();
        
        
        optionsButton.getImageArray()[0]= new ImageIcon(optionsStandard);
        optionsButton.getImageArray()[1]= new ImageIcon(optionsHighLighted);
        optionsButton.getImageArray()[2]= new ImageIcon(optionsInverted);
        optionsButton.initImage();
        
        
        if (checkSavedGames()){
            resumeGameButton.getImageArray()[0]= new ImageIcon(resumeGameStandard);
            resumeGameButton.getImageArray()[1]= new ImageIcon(resumeGameHighLighted);
            resumeGameButton.getImageArray()[2]= new ImageIcon(resumeGameInverted);
            resumeGameButton.initImage();
        }
        else {
            resumeGameButton.getImageArray()[0]= new ImageIcon(resumeGameStandard);
            resumeGameButton.getImageArray()[1]= new ImageIcon(resumeGameStandard);
            resumeGameButton.getImageArray()[2]= new ImageIcon(resumeGameStandard);
            resumeGameButton.initImage();
            resumeGameButton.setEnabled(false);
        }
        
        highScoreButton.getImageArray()[0]= new ImageIcon(highScoreStandard);
        highScoreButton.getImageArray()[1]= new ImageIcon(highScoreHighLighted);
        highScoreButton.getImageArray()[2]= new ImageIcon(highScoreInverted);
        highScoreButton.initImage();
        
        helpButton.getImageArray()[0]= new ImageIcon(helpStandard);
        helpButton.getImageArray()[1]= new ImageIcon(helpHighLighted);
        helpButton.getImageArray()[2]= new ImageIcon(helpInverted);
        helpButton.initImage();
        
        exitButton.getImageArray()[0]= new ImageIcon(exitStandard);
        exitButton.getImageArray()[1]= new ImageIcon(exitHighLighted);
        exitButton.getImageArray()[2]= new ImageIcon(exitInverted);
        exitButton.initImage();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
    
    private boolean checkSavedGames(){
        File singleMarathon= new File("data/single_player_marathon.game");
        File singleSprint= new File ("data/single_player_sprint.game");
        File singleUltra= new File ("data/single_player_ultra.game");
        File twoMarathon= new File ("data/two_players_marathon.game");
        File twoSprint= new File ("data/two_players_sprint.game");
        File twoUltra= new File ("data/two_players_ultra.game");
        
        if (!singleMarathon.exists() && !singleSprint.exists() && !singleUltra.exists()
                && !twoMarathon.exists() && !twoSprint.exists() && !twoUltra.exists()){
            return false;
        }
        
        return true;
    }
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==newGameButton){
                newGameButton.mousePressed(me);
            }
            else if (me.getSource()==resumeGameButton){
                resumeGameButton.mousePressed(me);
            }
            else if (me.getSource()==highScoreButton){
                highScoreButton.mousePressed(me);
            }
            else if (me.getSource()==helpButton){
                helpButton.mousePressed(me);
            }
            else if (me.getSource()==exitButton){
                exitButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==newGameButton){
                newGameButton.mouseReleased(me);
            }
            else if (me.getSource()==resumeGameButton){
                resumeGameButton.mouseReleased(me);
            }
            else if (me.getSource()==highScoreButton){
                highScoreButton.mouseReleased(me);
            }
            else if (me.getSource()==helpButton){
                helpButton.mouseReleased(me);
            }
            else if (me.getSource()==exitButton){
                exitButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==newGameButton){
                newGameButton.mouseEntered(me);
            }
            else if (me.getSource()==resumeGameButton){
                resumeGameButton.mouseEntered(me);
            }
            else if (me.getSource()==highScoreButton){
                highScoreButton.mouseEntered(me);
            }
            else if (me.getSource()==helpButton){
                helpButton.mouseEntered(me);
            }
            else if (me.getSource()==exitButton){
                exitButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==newGameButton){
                newGameButton.mouseExited(me);
            }
            else if (me.getSource()==resumeGameButton){
                resumeGameButton.mouseExited(me);
            }
            else if (me.getSource()==highScoreButton){
                highScoreButton.mouseExited(me);
            }
            else if (me.getSource()==helpButton){
                helpButton.mouseExited(me);
            }
            else if (me.getSource()==exitButton){
                exitButton.mouseExited(me);
            }
        }
        
    }
}
