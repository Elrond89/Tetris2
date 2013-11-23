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
 * @author AntonioRuffolo
 */
public class GamePanel extends JPanel implements Runnable {
    private Graphics bufferedGraphics;
    private Image offscreen;
    private TetrisBoard tetraBoard;
    private final int DELAY=50;
    private Thread animator;
    private int score, linesEliminated, seconds;
    private Tetramino t;
    private TetraButton quitButton;
    private Image image= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/flag.png"));
    private Image quitButtonStandard, quitButtonHighLighted, quitButtonInverted;
    private TetrisPanelsController tetrisPanelsController;
    private Action action;
    private TetrisAudioPlayer audioPlayer;
    private Font ttfReal, font;
    //private TetrisDialogFrame dialogFrame;
    //private Tetramino t= new TetraJ();
    private Image[] backgroundArray= {Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/UralLake.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/WinterPalace.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/NicholasHall.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/SovietMetro.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/MissileLaunch.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/SovietSatellite.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/StoneForest.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/SovietHockey.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/CosmonautSpaceWalk.png")),
                                      Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/backgroundImages/StPetersburgChurch.png"))
                                      };
    
    
    public GamePanel(){
        tetraBoard= new TetrisBoard();
        setDoubleBuffered(true);
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromSinglePlayerGame");
        quitButton.addMouseListener(new MouseAdapter());
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        //c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 190;
        c.ipady = 390;
        c.insets.left= 100;
        c.insets.right= 200;
        c.insets.top= 150;
        layout.setConstraints(tetraBoard, c);
        this.add(tetraBoard);

        
        c.gridx = 1;
        c.gridy = 2;
        c.anchor= GridBagConstraints.SOUTH;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=200;
        c.insets.top= 50;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("RotateCCW");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveDown");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveLeft");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveRight");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveToTheBottom");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("Pause");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("HideGhost");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
    }
    
    public GamePanel(TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController= tetrisPanelsController;
        tetraBoard= new TetrisBoard();
        setDoubleBuffered(true);
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromSinglePlayerGame");
        quitButton.addActionListener(this.tetrisPanelsController);
        quitButton.addMouseListener(new MouseAdapter());
        audioPlayer= new TetrisAudioPlayer("/tetris/songs/Tetris_MusicA2.mid");
        action= new UpdateAction(audioPlayer);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        //c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 190;
        c.ipady = 390;
        c.insets.left= 100;
        c.insets.right= 100;
        c.insets.top= 90;
        layout.setConstraints(tetraBoard, c);
        this.add(tetraBoard);

        
        c.gridx = 1;
        c.gridy = 2;
        c.anchor= GridBagConstraints.SOUTH;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=200;
        c.insets.right=0;
        c.insets.top= 50;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("RotateCCW");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveDown");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveLeft");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveRight");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveToTheBottom");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("Pause");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("HideGhost");
                tetraBoard.getAction().actionPerformed(e);
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
    
    public GamePanel(TetrisPanelsController tetrisPanelsController, TetrisGameLogic tgl){
        this.tetrisPanelsController= tetrisPanelsController;
        tetraBoard= new TetrisBoard(tgl);
        setDoubleBuffered(true);
        quitButton= new TetraButton();
        quitButton.setFocusable(false);
        quitButton.setActionCommand("quitFromSinglePlayerGame");
        quitButton.addActionListener(this.tetrisPanelsController);
        quitButton.addMouseListener(new MouseAdapter());
        audioPlayer= new TetrisAudioPlayer("/tetris/songs/Tetris_MusicA2.mid");
        action= new UpdateAction(audioPlayer);
        
        init();
        
        GridBagLayout layout= new GridBagLayout();
        
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        //c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 190;
        c.ipady = 390;
        c.insets.left= 100;
        c.insets.right= 100;
        c.insets.top= 90;
        layout.setConstraints(tetraBoard, c);
        this.add(tetraBoard);

        
        c.gridx = 1;
        c.gridy = 2;
        c.anchor= GridBagConstraints.SOUTH;
        c.ipadx = 0;
        c.ipady = 0;
        c.insets.left=200;
        c.insets.right=0;
        c.insets.top= 50;
        layout.setConstraints(quitButton, c);
        this.add(quitButton);
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        this.getActionMap().put("up", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("RotateCCW");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        this.getActionMap().put("down", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveDown");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        this.getActionMap().put("left", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveLeft");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        this.getActionMap().put("right", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveRight");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        this.getActionMap().put("space", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("MoveToTheBottom");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "p");
        this.getActionMap().put("p", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("Pause");
                tetraBoard.getAction().actionPerformed(e);
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
        this.getActionMap().put("h", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tetraBoard.setKeyPressed("HideGhost");
                tetraBoard.getAction().actionPerformed(e);
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
        font=null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            Logger.getLogger(HighScorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        t= tetraBoard.getTetralogic().getTetraQueue().peek();
    }
    
    public TetrisGameLogic getTetrisGameLogic(){
        return tetraBoard.getTetralogic();
    }
    
    public TetrisBoard getTetraBoard(){
        return tetraBoard;
    }
    
    public Action getAction(){
        return this.action;
    }
    
    public void start(){
        tetraBoard.start();
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        offscreen= createImage(800,600);
        bufferedGraphics=offscreen.getGraphics();
        animator= new Thread(this);
        animator.start();
        score=tetraBoard.getTetralogic().getScore();
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        bufferedGraphics.clearRect(0, 0, 800, 600);
        bufferedGraphics.drawImage(/*backgroundArray[1]*/image, 0, 0, this);
        if (tetraBoard.getTetralogic().getLevel()<10){
            bufferedGraphics.drawImage(backgroundArray[tetraBoard.getTetralogic().getLevel()], 9, 89, this);
        }else {
            bufferedGraphics.drawImage(backgroundArray[9], 9, 89, this);
        }
        //g2d.drawImage(scorePanel, 0, 0, null);
        for (int i=0; i<4; i++){
            
            if (t instanceof TetraI){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-28+658, t.getTetramino()[i].getY()+3+86, this);
            }
            else if (t instanceof TetraO) {
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-30+658, t.getTetramino()[i].getY()+4+86, this);
            }
            else if (t instanceof TetraT){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-20+658, t.getTetramino()[i].getY()+4+86, this);
            }
            else if (t instanceof TetraZ){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-20+658, t.getTetramino()[i].getY()+4+86, this);
            }
            else if (t instanceof TetraS){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-20+658, t.getTetramino()[i].getY()+4+86, this);
            }
            else if (t instanceof TetraL){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-18+658, t.getTetramino()[i].getY()+4+86, this);
            }
            else if (t instanceof TetraJ){
                bufferedGraphics.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX()-18+658, t.getTetramino()[i].getY()+4+86, this);
            }
        }
        
        /*Font infoFont= new Font("OCR A Std", Font.BOLD, 18);*/
        
        ttfReal = font.deriveFont(Font.PLAIN, 19);
        bufferedGraphics.setFont(ttfReal);
        bufferedGraphics.setColor(Color.yellow);
        Graphics2D g2 = (Graphics2D) bufferedGraphics;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        g2.drawString("Level", 699, 185);
        
        g2.drawString(""+tetraBoard.getTetralogic().getLevel(), 36+663, 63+122+20);
        
        
        g2.drawString("Lines", 699, 230);
        
        g2.drawString(""+tetraBoard.getTetralogic().getLines(), 36+663, 88+122+40);
        
        
        g2.drawString("Score", 699, 114+122+40);
        
        g2.drawString(""+tetraBoard.getTetralogic().getScore(), 36+663, 114+122+60);
        
        String minutes, stringSeconds;
        if (tetraBoard.getTetralogic().getMinutes()<10){
            minutes=("0"+tetraBoard.getTetralogic().getMinutes());
        }
        else {
            minutes=""+tetraBoard.getTetralogic().getMinutes();
        }
        
        if (tetraBoard.getTetralogic().getSeconds()<10){
            stringSeconds= ("0"+tetraBoard.getTetralogic().getSeconds());
        }
        else{
            stringSeconds= ""+tetraBoard.getTetralogic().getSeconds();
        }
        ttfReal = font.deriveFont(Font.PLAIN, 26);
        bufferedGraphics.setFont(ttfReal);
        g2.drawString(minutes+":"+stringSeconds, 480, 45);
        
        g.drawImage(offscreen, 0, 0, this);
    }//paint
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            if (seconds != tetraBoard.getTetralogic().getSeconds()){
                seconds = tetraBoard.getTetralogic().getSeconds();
                repaint();
            } else if (score != tetraBoard.getTetralogic().getScore()){
                score= tetraBoard.getTetralogic().getScore();
                repaint();
            } else if (linesEliminated != tetraBoard.getTetralogic().getLines()){
                linesEliminated = tetraBoard.getTetralogic().getLines();
                repaint();
            } else if (t != tetraBoard.getTetralogic().getTetraQueue().peek()){
                t= tetraBoard.getTetralogic().getTetraQueue().peek();
                repaint();
            }
            
            //va aggiunto il porssimo pezzo nella coda, potrebbe esserci un ritardo altrimenti
            
            if (tetraBoard.getTetralogic().isGameOver()){
                audioPlayer.stopSound();
                saveGameResult();
                break;
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
    
    public void saveGameResult(){
        String name=null;
        
        int gameMode= tetraBoard.getTetralogic().getGameMode();
        File file = null;
        if (gameMode==0){
            file = new File("data/marathon_score.scores");
        }
        else if (gameMode==1){
           if (tetraBoard.getTetralogic().getLines() < 40){
                return;
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
                name = JOptionPane.showInputDialog(null,"What is your name?", "Enter your name",JOptionPane.QUESTION_MESSAGE);
            }
            name = name.toUpperCase();
            try {
                ObjectOutputStream objectOut = null;
                TetrisTopTenScore topScores= new TetrisTopTenScore();
                int playerScore= tetraBoard.getTetralogic().getScore();
                int minutes= tetraBoard.getTetralogic().getMinutes();
                int playerSeconds= tetraBoard.getTetralogic().getSeconds();
                TetrisPlayerResult playerResult= new TetrisPlayerResult(name, playerScore, minutes, playerSeconds);
                if (gameMode==0){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                    objectOut.close();
                    this.tetrisPanelsController.loadScorePanel(0);
                }
                else if (gameMode==1){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                    topScores.setTimeScore(true);
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                    objectOut.close();
                    this.tetrisPanelsController.loadScorePanel(1);
                }
                else if (gameMode==2){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                    objectOut.close();
                    this.tetrisPanelsController.loadScorePanel(2);
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {//se era gia' presente
            try {
                ObjectInputStream objectIn = null;
                try {
                    int playerScore= tetraBoard.getTetralogic().getScore();
                    int minutes= tetraBoard.getTetralogic().getMinutes();
                    int playerSeconds= tetraBoard.getTetralogic().getSeconds();
                    if (gameMode==0){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && playerScore < topScores.getArray()[16].getPlayerScore()){
                            return;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"What is your name?", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, playerScore, minutes, playerSeconds);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            this.tetrisPanelsController.loadScorePanel(0);
                        }
                    }
                    if (gameMode==1){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null){
                            if (topScores.getArray()[16].getPlayerMinutes() < minutes){
                                return;
                            }
                            if (topScores.getArray()[16].getPlayerMinutes() == minutes ){
                                if (topScores.getArray()[16].getPlayerSeconds() < playerSeconds){
                                    return;
                                }
                            }
                            
                        }
                        if (tetraBoard.getTetralogic().getLines() < 40){
                                return;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"What is your name?", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, playerScore, minutes, playerSeconds);
                            topScores.setTimeScore(true);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            this.tetrisPanelsController.loadScorePanel(1);
                        }
                    }
                    if (gameMode==2){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (topScores.getArray()[16] != null && playerScore < topScores.getArray()[16].getPlayerScore()){
                            return;
                        }
                        else {
                            
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"What is your name?", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            name = name.toUpperCase();
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, playerScore, minutes, playerSeconds);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                            this.tetrisPanelsController.loadScorePanel(2);
                        }
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
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
