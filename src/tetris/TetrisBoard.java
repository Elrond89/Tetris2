/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author AntonioRuffolo
 */
public class TetrisBoard extends JPanel implements Runnable{
    private Thread animator;
    private final int DELAY = 50;
    private TetrisGameLogic tgl;
    private Tetramino t;
    private Quadrato[][] tetraMatrix;
    private Graphics bufferedGraphics;
    private Image offscreen, gameOverImage;
    private InputMap inputmap;
    private ActionMap actionmap;
    private String keyPressed;
    private Action action;
    private boolean gameRunning=true, hideGhost;
    
    public TetrisBoard(){
        tgl= new TetrisGameLogic();
        tetraMatrix= tgl.getTetraMatrix();
        gameOverImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GameOver.png"));
        action = new UpdateAction(this, tgl);
        setFocusable(true);
    	setDoubleBuffered(true);
        //addKeyListener(new TetrisBoardController(tgl));
        this.setBackground(Color.BLACK);
        setOpaque(false);
        /*inputmap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
        actionmap = getActionMap();
        actionmap.put("up", new TetrisBoardActionController(tgl, "up"));
        actionmap.put("down", new TetrisBoardActionController(tgl, "down"));
        actionmap.put("left", new TetrisBoardActionController(tgl, "left"));
        actionmap.put("right", new TetrisBoardActionController(tgl, "right"));
        actionmap.put("space", new TetrisBoardActionController(tgl, "space"));
        actionmap.put("pause", new TetrisBoardActionController(tgl, "pause"));*/  
    }
    
    public TetrisBoard(TetrisGameLogic tgl){
        
        this.tgl=tgl;
        tetraMatrix= tgl.getTetraMatrix();
        action = new UpdateAction(this, tgl);
        gameOverImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/GameOver.png"));
        setFocusable(true);
    	setDoubleBuffered(true);
        //addKeyListener(new TetrisBoardController(tgl));
        this.setBackground(Color.BLACK);
        setOpaque(false);
        tgl.start();
        /*inputmap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
        actionmap = getActionMap();
        actionmap.put("up", new TetrisBoardActionController(tgl, "up"));
        actionmap.put("down", new TetrisBoardActionController(tgl, "down"));
        actionmap.put("left", new TetrisBoardActionController(tgl, "left"));
        actionmap.put("right", new TetrisBoardActionController(tgl, "right"));
        actionmap.put("space", new TetrisBoardActionController(tgl, "space"));
        actionmap.put("pause", new TetrisBoardActionController(tgl, "pause"));*/  
    }
    
    public void setKeyPressed(String keyPressed){
        this.keyPressed=keyPressed;
    }
    
    public String getKeyPressed(){
        return this.keyPressed;
    }
    
    public Action getAction() {
            return action;
    }
    
    public TetrisGameLogic getTetralogic(){
        return tgl;
    }
    
    public void ridipingi(){
        repaint();
    }
    
    @Override
    public void paint (Graphics g){
        
        bufferedGraphics.clearRect(0, 0, 200, 400);
        bufferedGraphics.setColor(Color.BLACK);
        bufferedGraphics.fillRect(0, 0, 400, 400);
    	//Graphics2D g2d= (Graphics2D)bufferedGraphics;
        
        //Image invertedImage = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/CyanTInverted.png"));
        //bufferedGraphics.drawImage(invertedImage, 30, 40, this);
        if(tgl.isGameOver()){
            bufferedGraphics.drawImage(gameOverImage, 0, 180, this);
            g.drawImage(offscreen, 0, 0, this);
            gameRunning=false;
            return;
        }
        t= tgl.getTetramino();
        tetraMatrix= tgl.getTetraMatrix();
        
        if (!hideGhost){
            for (int i=0; i<4; i++){
                if(tgl.getTetraGhost()!=null && !t.isNotMoving()) {
                    bufferedGraphics.drawImage(tgl.getTetraGhost().getTetramino()[i].getImage(), tgl.getTetraGhost().getTetramino()[i].getX(), tgl.getTetraGhost().getTetramino()[i].getY(), this);
                }
            }
        }
        
        for (int i=0; i<t.getTetramino().length; i++){
            if (!t.isNotMoving()){
                bufferedGraphics.drawImage(t.getTetramino()[i].getGreyImage(), t.getTetramino()[i].getX(), t.getTetramino()[i].getY(), this);
                //System.out.println(t.isNotMoving());
            }
            //g2d.drawImage(t.getTetramino()[i].getImage(), t.getTetramino()[i].getX(), t.getTetramino()[i].getY(), this);
        }
        
        for(int i=0; i<tetraMatrix.length; i++){
            for (int j=0; j<tetraMatrix[i].length; j++){
                if (tetraMatrix[i][j].isVisible()){
                    //bufferedGraphics.drawImage(tetraMatrix[i][j].getGreyImage(), tetraMatrix[i][j].getX(), tetraMatrix[i][j].getY(), this);
                    if(tetraMatrix[i][j].useInvertedImage()){
                        bufferedGraphics.drawImage(tetraMatrix[i][j].getInvertedImage(), j*20, i*20, this);
                    } else {
                        bufferedGraphics.drawImage(tetraMatrix[i][j].getImage(), j*20, i*20, this);
                    }
                    
                    //System.out.println("a:"+i+" b:"+j+" x:"+tetraMatrix[i][j].getX()+" y:"+tetraMatrix[i][j].getY()+" visible:"+tetraMatrix[i][j].isVisible());
                    //System.out.println(tetraMatrix[i][j].getX()+" "+tetraMatrix[i][j].getY());
                }
            }
        }
        g.drawImage(offscreen, 0, 0, this);
    }//paint
    
    @Override
    public void addNotify() {
        super.addNotify();
        offscreen= createImage(200,400);
        bufferedGraphics=offscreen.getGraphics();
        tetraMatrix= tgl.getTetraMatrix();
        animator = new Thread(this);
        animator.start();
    }//addNotify
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    
    public void saveGameResult(){
        String name=null;
        
        int gameMode= tgl.getGameMode();
        File file = null;
        if (gameMode==0){
            file = new File("data/marathon_score.scores");
        }
        else if (gameMode==1){
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
            try {
                ObjectOutputStream objectOut = null;
                TetrisTopTenScore topScores= new TetrisTopTenScore();
                int score= tgl.getScore();
                int minutes= tgl.getMinutes();
                int seconds= tgl.getSeconds();
                TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, seconds);
                if (gameMode==0){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==1){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/sprint_score.scores")));
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                else if (gameMode==2){
                    objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/ultra_score.scores")));
                    topScores.setTimeScore(true);
                    topScores.add(playerResult);
                    objectOut.writeObject(topScores);
                }
                objectOut.close();
                
            } catch (Exception ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {//se era gia' presente
            try {
                ObjectInputStream objectIn = null;
                try {
                    int score= tgl.getScore();
                    int minutes= tgl.getMinutes();
                    int seconds= tgl.getSeconds();
                    if (gameMode==0){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                        if (score < topScores.getArray()[9].getPlayerScore()){
                            return;
                        }
                        else {
                            while(name == null){
                                name = JOptionPane.showInputDialog(null,"What is your name?", "Enter your name",JOptionPane.QUESTION_MESSAGE);
                            }
                            TetrisPlayerResult playerResult= new TetrisPlayerResult(name, score, minutes, seconds);
                            topScores.add(playerResult);
                            ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data/marathon_score.scores")));
                            objectOut.writeObject(topScores);
                            objectOut.close();
                        }
                    }
                    if (gameMode==1){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                    }
                    if (gameMode==2){
                        objectIn=new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                        Object ob= objectIn.readObject();
                        objectIn.close();
                        TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                    }
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }//saveGameResult
    
    public void switchGhost(){
        if (!hideGhost) hideGhost=true;
        else hideGhost=false;
    }//switchHideGhost
    
    public void start(){
        //tgl.start();//Questo va tolto, l'unico motore deve essere quello della TetrisBoard.
    }
    
    /*private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed (KeyEvent e){
           tgl.keyPressed(e);
        }
    }//TAdapter*/
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (gameRunning) {
            
            tgl.updateTime(System.currentTimeMillis());
            tgl.updateGame(System.currentTimeMillis());
            repaint();
            
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
    }
    
    
    private static class UpdateAction extends AbstractAction {

            private TetrisBoard panel;
            private TetrisGameLogic tgl;

            public UpdateAction(TetrisBoard  panel, TetrisGameLogic tgl) {
                this.panel = panel;
                this.tgl=tgl;
            }

            @Override
            public void actionPerformed(ActionEvent ae) {
                 if (panel.getKeyPressed().equals("RotateCCW") && tgl.isGameRunning()){
                    tgl.rotateCCWCurrentTetramino();
                 }       
                 if (panel.getKeyPressed().equals("MoveDown") && tgl.isGameRunning()){
                    tgl.pushDownCurrentTetramino();
                 }
                 if (panel.getKeyPressed().equals("MoveLeft") && tgl.isGameRunning()){
                    tgl.moveLeftCurrentTetramino();
                 }
                 if (panel.getKeyPressed().equals("MoveRight") && tgl.isGameRunning()){
                    tgl.moveRightCurrentTetramino();
                 }
                 if (panel.getKeyPressed().equals("MoveToTheBottom") && tgl.isGameRunning()){
                    tgl.moveToTheBottomCurrentTetramino();
                 }
                 if (panel.getKeyPressed().equals("Pause")) {
                    tgl.pauseTetraminoLogic();
                 }
                 if (panel.getKeyPressed().equals("HideGhost")) {
                    panel.switchGhost();
                 }
            }
    }//UpdateAction
}
