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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Antonio
 */
public class TwoPlayerGamePanel extends JPanel implements Runnable{
    private Graphics bufferedGraphics;
    private Image offscreen;
    private TetrisBoard tetraBoardOne, tetraBoardTwo;
    Tetramino t;
    Tetramino t2;
    private final int DELAY=50;
    private Thread animator;
    private Image image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/flag.png"));
    private Image quitButtonStandard, quitButtonHighLighted, quitButtonInverted;
    private List<TetrisBoard> list = Arrays.asList(tetraBoardOne, tetraBoardTwo);
    private TetraButton quitButton;
    private TetrisPanelsController tetrisPanelsController;
    private boolean playerOneOver, playerTwoOver;
    private Action action;
    private TetrisAudioPlayer audioPlayer;
    private Font font, ttfReal;
    private int scoreOne, linesEliminatedOne, levelOne, scoreTwo, linesEliminatedTwo, levelTwo, seconds;
    
    public TwoPlayerGamePanel(){
        this.setFocusable(true);
        setDoubleBuffered(true);
        
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromTwoPlayerGame");
        quitButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        
        GridBagConstraints c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.WEST;
        c.insets.top=60;
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right= 0;
        c.insets.left=260;//modifica questa
        layout.setConstraints(tetraBoardOne, c);
        this.add(tetraBoardOne);
        
        //c.anchor= GridBagConstraints.EAST;
        
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right=140;//modifica questa
        c.insets.left=0;
        layout.setConstraints(tetraBoardTwo, c);
        this.add(tetraBoardTwo);
        
        c.gridx= 1;
        c.gridheight=2;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=0;
        c.insets.right=460;//modifica questa
        c.insets.top=40;
        c.anchor= GridBagConstraints.SOUTH;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                
                tetraBoardTwo.setKeyPressed("RotateCCW");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
        this.getActionMap().put("w", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                tetraBoardOne.setKeyPressed("RotateCCW");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveDown");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");
        this.getActionMap().put("s", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveDown");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveLeft");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "a");
        this.getActionMap().put("a", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveLeft");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveRight");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "d");
        this.getActionMap().put("d", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveRight");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveToTheBottom");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "x");
        this.getActionMap().put("x", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveToTheBottom");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "g");
        this.getActionMap().put("g", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("HideGhost");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("HideGhost");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (TetrisBoard panel : list) {
                    panel.setKeyPressed("Pause");
                    panel.getAction().actionPerformed(e);
                }
            }
        });        
    }
    
    public TwoPlayerGamePanel(TetrisPanelsController tetrisPanelsController){
        this.setFocusable(true);
        setDoubleBuffered(true);
        this.tetrisPanelsController= tetrisPanelsController;
        tetraBoardOne= new TetrisBoard();
        tetraBoardTwo= new TetrisBoard();
        
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromTwoPlayerGame");
        quitButton.addActionListener(this.tetrisPanelsController);
        quitButton.addMouseListener(new MouseAdapter());
        audioPlayer= new TetrisAudioPlayer("/tetris/songs/Tetris_MusicA2.mid");
        action= new UpdateAction(audioPlayer);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        
        GridBagConstraints c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.WEST;
        c.gridx=0;
        c.gridy=0;
        c.insets.top=80;
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right= 0;
        c.insets.left=180;//modifica questa
        layout.setConstraints(tetraBoardOne, c);
        this.add(tetraBoardOne);
        
        //c.anchor= GridBagConstraints.EAST;
        
        c.gridx=2;
        c.gridy=0;
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right=150;//modifica questa
        c.insets.left=0;
        layout.setConstraints(tetraBoardTwo, c);
        this.add(tetraBoardTwo);
        
        c.gridx= 0;
        c.gridy=5;
        //c.gridheight=2;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=100;
        c.insets.right=0;//modifica questa
        c.insets.top=40;
        c.anchor= GridBagConstraints.SOUTH;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                
                tetraBoardTwo.setKeyPressed("RotateCCW");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
        this.getActionMap().put("w", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                tetraBoardOne.setKeyPressed("RotateCCW");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveDown");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");
        this.getActionMap().put("s", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveDown");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveLeft");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "a");
        this.getActionMap().put("a", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveLeft");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveRight");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "d");
        this.getActionMap().put("d", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveRight");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveToTheBottom");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "x");
        this.getActionMap().put("x", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveToTheBottom");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("Pause");
                    panel.getAction().actionPerformed(e);
                }*/
                tetraBoardOne.setKeyPressed("Pause");
                tetraBoardTwo.setKeyPressed("Pause");
                tetraBoardOne.getAction().actionPerformed(e);
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "g");
        this.getActionMap().put("g", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("HideGhost");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("HideGhost");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "m");
        this.getActionMap().put("m", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                action.actionPerformed(e);
            }
        });
        
        audioPlayer.playSound();
    }
    
    public TwoPlayerGamePanel(TetrisPanelsController tetrisPanelsController, TetrisGameLogic tgl1, TetrisGameLogic tgl2){
        this.setFocusable(true);
        setDoubleBuffered(true);
        this.tetrisPanelsController= tetrisPanelsController;
        tetraBoardOne= new TetrisBoard(tgl1);
        tetraBoardTwo= new TetrisBoard(tgl2);
        
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromTwoPlayerGame");
        quitButton.addActionListener(this.tetrisPanelsController);
        quitButton.addMouseListener(new MouseAdapter());
        audioPlayer= new TetrisAudioPlayer("/tetris/songs/Tetris_MusicA2.mid");
        action= new UpdateAction(audioPlayer);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        
        GridBagConstraints c = new GridBagConstraints();
        //c.anchor = GridBagConstraints.WEST;
        c.gridx=0;
        c.gridy=0;
        c.insets.top=80;
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right= 0;
        c.insets.left=180;//modifica questa
        layout.setConstraints(tetraBoardOne, c);
        this.add(tetraBoardOne);
        
        //c.anchor= GridBagConstraints.EAST;
        
        c.gridx=2;
        c.gridy=0;
        c.ipadx = 220;
        c.ipady = 390;
        c.insets.right=150;//modifica questa
        c.insets.left=0;
        layout.setConstraints(tetraBoardTwo, c);
        this.add(tetraBoardTwo);
        
        c.gridx= 0;
        c.gridy=5;
        //c.gridheight=2;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=100;
        c.insets.right=0;//modifica questa
        c.insets.top=40;
        c.anchor= GridBagConstraints.SOUTH;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                
                tetraBoardTwo.setKeyPressed("RotateCCW");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "w");
        this.getActionMap().put("w", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("RotateCCW");
                    panel.getAction().actionPerformed(e);
                }*/
                tetraBoardOne.setKeyPressed("RotateCCW");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveDown");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");
        this.getActionMap().put("s", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveDown");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveLeft");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "a");
        this.getActionMap().put("a", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveLeft");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveRight");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "d");
        this.getActionMap().put("d", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveRight");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("MoveToTheBottom");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "x");
        this.getActionMap().put("x", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("MoveToTheBottom");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (TetrisBoard panel : list) {
                    panel.setKeyPressed("Pause");
                    panel.getAction().actionPerformed(e);
                }*/
                tetraBoardOne.setKeyPressed("Pause");
                tetraBoardTwo.setKeyPressed("Pause");
                tetraBoardOne.getAction().actionPerformed(e);
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "g");
        this.getActionMap().put("g", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardOne.setKeyPressed("HideGhost");
                tetraBoardOne.getAction().actionPerformed(e);
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoardTwo.setKeyPressed("HideGhost");
                tetraBoardTwo.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "m");
        this.getActionMap().put("m", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                action.actionPerformed(e);
            }
        });
        
        audioPlayer.playSound();
    }
    
    private void init(){
        quitButtonStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/activeGameImages/QuitGameStandard.png"));
        quitButtonHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/activeGameImages/QuitGameHighLighted.png"));
        quitButtonInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/activeGameImages/QuitGameInverted.png"));
        
        quitButton.getImageArray()[0]= new ImageIcon(quitButtonStandard);
        quitButton.getImageArray()[1]= new ImageIcon(quitButtonHighLighted);
        quitButton.getImageArray()[2]= new ImageIcon(quitButtonInverted);
        quitButton.initImage();
        
        InputStream is= this.getClass().getResourceAsStream("/tetris/images/fonts/kremlin.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            Logger.getLogger(HighScorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t= tetraBoardOne.getTetralogic().getTetraQueue().peek();
        t2= tetraBoardTwo.getTetralogic().getTetraQueue().peek();
    }
    
    public void start(){
        tetraBoardOne.start();
        tetraBoardTwo.start();
    }
    
    public TetrisGameLogic getTetraLogicOne(){
        return tetraBoardOne.getTetralogic();
    }
    
    public TetrisGameLogic getTetraLogicTwo(){
        return tetraBoardTwo.getTetralogic();
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        offscreen= createImage(800,600);
        bufferedGraphics=offscreen.getGraphics();
        animator= new Thread(this);
        animator.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        bufferedGraphics.clearRect(0, 0, 800, 600);
        bufferedGraphics.drawImage(image, 0, 0, this);
        
        for (int i=0; i<4; i++){
            
            bufferedGraphics.drawImage(t.getTetramino()[i].getImage(),t.getTetramino()[i].getX()+22, t.getTetramino()[i].getY()+89, this);
            
            bufferedGraphics.drawImage(t2.getTetramino()[i].getImage(), t2.getTetramino()[i].getX()+588, t2.getTetramino()[i].getY()+89, this);
        }
        
        /*Font infoFont= new Font("OCR A Std", Font.BOLD, 18);*/
        ttfReal = font.deriveFont(Font.PLAIN, 19);
        bufferedGraphics.setFont(ttfReal);
        bufferedGraphics.setColor(Color.yellow);
        Graphics2D g2 = (Graphics2D) bufferedGraphics;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2.drawString("Level", 80, 170);
        g2.drawString(""+tetraBoardOne.getTetralogic().getLevel(), 80, 190);
        
        g2.drawString("Lines", 80, 220);
        g2.drawString(""+tetraBoardOne.getTetralogic().getLines(), 80, 240);
        
        g2.drawString("Score", 80, 270);
        g2.drawString(""+tetraBoardOne.getTetralogic().getScore(), 80, 290);
        
        
        g2.drawString("Level", 646, 170);
        g2.drawString(""+tetraBoardTwo.getTetralogic().getLevel(), 646, 190);
        
        g2.drawString("Lines", 646, 220);
        g2.drawString(""+tetraBoardTwo.getTetralogic().getLines(), 646, 240);
        
        g2.drawString("Score", 646, 270);
        g2.drawString(""+tetraBoardTwo.getTetralogic().getScore(), 646, 290);
        
        ttfReal = font.deriveFont(Font.PLAIN, 26);
        bufferedGraphics.setFont(ttfReal);
        
        String minutes="", secondsString="";
        if (!tetraBoardOne.getTetralogic().isGameOver()){
            if (tetraBoardOne.getTetralogic().getMinutes()<10){
                minutes=("0"+tetraBoardOne.getTetralogic().getMinutes());
            }
            else {
                minutes=""+tetraBoardOne.getTetralogic().getMinutes();
            }
        
            if (tetraBoardOne.getTetralogic().getSeconds()<10){
                secondsString= ("0"+tetraBoardOne.getTetralogic().getSeconds());
            }
            else{
                secondsString= ""+tetraBoardOne.getTetralogic().getSeconds();
            }
        }
        else {
            if (tetraBoardTwo.getTetralogic().getMinutes()<10){
                minutes=("0"+tetraBoardTwo.getTetralogic().getMinutes());
            }
            else {
                minutes=""+tetraBoardTwo.getTetralogic().getMinutes();
            }
        
            if (tetraBoardTwo.getTetralogic().getSeconds()<10){
                secondsString= ("0"+tetraBoardTwo.getTetralogic().getSeconds());
            }
            else{
                secondsString= ""+tetraBoardTwo.getTetralogic().getSeconds();
            }
        }
        g2.drawString(minutes+":"+secondsString, 361, 64);
        
        g.drawImage(offscreen, 0, 0, this);
    }//paintComponent
            
            
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            if (seconds != tetraBoardOne.getTetralogic().getSeconds()){
                seconds = tetraBoardOne.getTetralogic().getSeconds();
                repaint();
            }else if(scoreOne != tetraBoardOne.getTetralogic().getScore()){
                scoreOne= tetraBoardOne.getTetralogic().getScore();
                repaint();
            } else if (levelOne != tetraBoardOne.getTetralogic().getLevel()){
                levelOne = tetraBoardOne.getTetralogic().getLevel();
                repaint();
            } else if (linesEliminatedOne != tetraBoardOne.getTetralogic().getLines()){
                linesEliminatedOne = tetraBoardOne.getTetralogic().getLines();
                repaint();
            } else if(scoreTwo != tetraBoardTwo.getTetralogic().getScore()){
                scoreTwo= tetraBoardTwo.getTetralogic().getScore();
                repaint();
            } else if (levelTwo != tetraBoardTwo.getTetralogic().getLevel()){
                levelTwo = tetraBoardTwo.getTetralogic().getLevel();
                repaint();
            } else if (linesEliminatedTwo != tetraBoardTwo.getTetralogic().getLines()){
                linesEliminatedTwo = tetraBoardTwo.getTetralogic().getLines();
                repaint();
            } else if (t != tetraBoardOne.getTetralogic().getTetraQueue().peek()){
                 t = tetraBoardOne.getTetralogic().getTetraQueue().peek();
                 repaint();
            } else if (t2 != tetraBoardTwo.getTetralogic().getTetraQueue().peek()){
                t2 = tetraBoardTwo.getTetralogic().getTetraQueue().peek();
                repaint();
            }
            
            
            if (tetraBoardTwo.getTetralogic().isGameOver() && tetraBoardOne.getTetralogic().isGameOver() ){
                audioPlayer.stopSound();
            }
            
            if (tetraBoardOne.getTetralogic().isGameOver() && !playerOneOver){
                if (!tetraBoardTwo.getTetralogic().isGameOver()){
                    tetraBoardTwo.getTetralogic().pauseTetraminoLogic();
                    saveGameResultOne();
                    playerOneOver=true;
                    tetraBoardTwo.getTetralogic().pauseTetraminoLogic();
                }
                else {
                    playerOneOver=true;
                    if(saveGameResultOne()){
                        this.tetrisPanelsController.loadScorePanel(tetraBoardOne.getTetralogic().getGameMode());
                    }
                    
                }
            }
                
            
            if (tetraBoardTwo.getTetralogic().isGameOver() && !playerTwoOver){
                
                if (!tetraBoardOne.getTetralogic().isGameOver()){
                    tetraBoardOne.getTetralogic().pauseTetraminoLogic();
                    saveGameResultTwo();
                    playerTwoOver=true;
                    tetraBoardOne.getTetralogic().pauseTetraminoLogic();
                }
                else {
                    playerTwoOver=true;
                    if(saveGameResultTwo()){
                        this.tetrisPanelsController.loadScorePanel(tetraBoardTwo.getTetralogic().getGameMode());
                    }
                    
                }
            }
            
            //repaint();
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }//run
    
    public boolean saveGameResultOne(){
        String name=null;
        
        int gameMode= tetraBoardOne.getTetralogic().getGameMode();
        File file = null;
        if (gameMode==0){
            file = new File("data/marathon_score.scores");
        }
        else if (gameMode==1){
            if (tetraBoardOne.getTetralogic().getLines() < 40){
                return false;
           }
           file = new File("data/sprint_score.scores");
        }
        else if (gameMode==2){
            file = new File("data/ultra_score.scores");
        }
        boolean fileExist=false;
        try {
           fileExist = file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fileExist){//se e' stato creato
            while(name == null){
                name = JOptionPane.showInputDialog(null,"Left Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
            }
            try {
                name = name.toUpperCase();
                ObjectOutputStream objectOut = null;
                TetrisTopTenScore topScores= new TetrisTopTenScore();
                int score= tetraBoardOne.getTetralogic().getScore();
                int minutes= tetraBoardOne.getTetralogic().getMinutes();
                int secondsOne= tetraBoardOne.getTetralogic().getSeconds();
                TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsOne);
                if (gameMode==0){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==1){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                    topScores.setTimeScore(true);
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==2){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                objectOut.close();
                
            } catch (Exception ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        else {//se era gia' presente
            try {
                ObjectInputStream objectIn = null;
                try {
                    int score= tetraBoardOne.getTetralogic().getScore();
                    int minutes= tetraBoardOne.getTetralogic().getMinutes();
                    int secondsOne= tetraBoardOne.getTetralogic().getSeconds();
                    if (gameMode==0){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && score < topScores.getArray()[16].getPlayerScore()){
                            return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Left Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsOne);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    if (gameMode==1){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null){
                            if (topScores.getArray()[16].getPlayerMinutes() < minutes){
                                return false;
                            }
                            if (topScores.getArray()[16].getPlayerMinutes() == minutes ){
                                if (topScores.getArray()[16].getPlayerSeconds() < secondsOne){
                                    return false;
                                }
                            }
                            
                        }
                        if (tetraBoardOne.getTetralogic().getLines() < 40){
                                return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Left Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsOne);
                            topScores.setTimeScore(true);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    if (gameMode==2){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && score < topScores.getArray()[16].getPlayerScore()){
                            return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Left Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsOne);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    
    }//saveGameResult
    
    public boolean saveGameResultTwo(){
        String name=null;
        
        int gameMode= tetraBoardTwo.getTetralogic().getGameMode();
        File file = null;
        if (gameMode==0){
            file = new File("data/marathon_score.scores");
        }
        else if (gameMode==1){
            if (tetraBoardTwo.getTetralogic().getLines() < 40){
                return false;
           }
           file = new File("data/sprint_score.scores");
        }
        else if (gameMode==2){
            file = new File("data/ultra_score.scores");
        }
        boolean fileExist=false;
        try {
           fileExist = file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fileExist){//se e' stato creato
            while(name == null){
                name = JOptionPane.showInputDialog(null,"Right Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
            }
            try {
                name = name.toUpperCase();
                ObjectOutputStream objectOut = null;
                TetrisTopTenScore topScores= new TetrisTopTenScore();
                int score= tetraBoardTwo.getTetralogic().getScore();
                int minutes= tetraBoardTwo.getTetralogic().getMinutes();
                int secondsTwo= tetraBoardTwo.getTetralogic().getSeconds();
                TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsTwo);
                if (gameMode==0){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==1){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                    topScores.setTimeScore(true);
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==2){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                objectOut.close();
                
            } catch (Exception ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        else {//se era gia' presente
            try {
                ObjectInputStream objectIn = null;
                try {
                    int score= tetraBoardTwo.getTetralogic().getScore();
                    int minutes= tetraBoardTwo.getTetralogic().getMinutes();
                    int secondsTwo= tetraBoardTwo.getTetralogic().getSeconds();
                    if (gameMode==0){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && score < topScores.getArray()[16].getPlayerScore()){
                            return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Right Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsTwo);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    if (gameMode==1){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null){
                            if (topScores.getArray()[16].getPlayerMinutes() < minutes){
                                return false;
                            }
                            if (topScores.getArray()[16].getPlayerMinutes() == minutes ){
                                if (topScores.getArray()[16].getPlayerSeconds() < secondsTwo){
                                    return false;
                                }
                            }
                            
                        }
                        if (tetraBoardTwo.getTetralogic().getLines() < 40){
                                return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Right Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsTwo);
                            topScores.setTimeScore(true);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    if (gameMode==2){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && score < topScores.getArray()[16].getPlayerScore()){
                            return false;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"Right Player", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, secondsTwo);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            return true;
                        }
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }//saveGameResult
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==quitButton){
                quitButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==quitButton){
                quitButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==quitButton){
                quitButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==quitButton){
                quitButton.mouseExited(me);
            }
        }
        
    }//MouseAdapter
    
    private static class UpdateAction extends AbstractAction {
        
        private TetrisAudioPlayer audioPlayer;
        
        public UpdateAction(TetrisAudioPlayer audioPlayer){
            this.audioPlayer=audioPlayer;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (audioPlayer.playing()){
                audioPlayer.stopSound();
            }
            else{
                audioPlayer.playSound();
            }
        }
    }//UpdateAction
    
    public void stopMusic(){
        if (audioPlayer.playing()){
            audioPlayer.stopSound();
        }
    }
}
