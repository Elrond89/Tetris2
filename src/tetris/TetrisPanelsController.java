/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * "The controller to rule them all"
 * Questa classe cambia i pannelli che vanno visualizzati nel frame principale
 * @author antonioruffolo
 */
public class TetrisPanelsController implements ActionListener {
    private TetrisFrame tetrisFrame;
    private MainMenuPanel mainMenuPanel;
    private PlayerModePanel playerModePanel;
    private GamePanel gamePanel;
    private ChooseGameModePanel gameModePanel;
    private TwoPlayerGamePanel twoPlayersGamePanel;
    private SelectLevelPanel selectLevelPanel;
    private ConfirmExitPanel confirmExitPanel;
    private ChooseScoreModePanel scoreModePanel;
    private HighScorePanel scorePanel;
    private ResumeGamePlayerModePanel resumeGamePlayerModePanel;
    private ResumeGameModePanel resumeGameModePanel;
    private HelpPanel helpPanel;
    private Image helpBackgroundImage;
    private Image backButtonStandard;
    private Image backButtonHighLighted;
    private Image backButtonPressed;
    
    private enum GAME_MODE {MARATHON, ULTRA, SPRINT};
    private GAME_MODE gameMode= GAME_MODE.MARATHON;
    private enum PLAYER_MODE {SINGLE_PLAYER, TWO_PLAYERS};
    private PLAYER_MODE playerMode= PLAYER_MODE.SINGLE_PLAYER;
    private int level;
    
    
    public TetrisPanelsController(TetrisFrame tetrisFrame){
        this.tetrisFrame=tetrisFrame;
        mainMenuPanel=new MainMenuPanel(this);
        this.tetrisFrame.add(mainMenuPanel);
    }//constructor
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (command.equals("newGame")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            playerModePanel = new PlayerModePanel(this);
            tetrisFrame.getContentPane().add(playerModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("resumeGame")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            resumeGamePlayerModePanel = new ResumeGamePlayerModePanel(this);
            tetrisFrame.getContentPane().add(resumeGamePlayerModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("highScore")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            scoreModePanel = new ChooseScoreModePanel(this);
            tetrisFrame.getContentPane().add(scoreModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("help")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();        
            
            helpPanel = new HelpPanel(this);
            tetrisFrame.getContentPane().add(helpPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("exit")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            confirmExitPanel= new ConfirmExitPanel(this);
            tetrisFrame.getContentPane().add(confirmExitPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("singlePlayer")){
            playerMode= PLAYER_MODE.SINGLE_PLAYER;
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            gameModePanel= new ChooseGameModePanel(this);
            tetrisFrame.getContentPane().add(gameModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("twoPlayers")){
            playerMode= PLAYER_MODE.TWO_PLAYERS;
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            gameModePanel= new ChooseGameModePanel(this);
            tetrisFrame.getContentPane().add(gameModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("marathon")){
            gameMode= GAME_MODE.MARATHON;
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            selectLevelPanel= new SelectLevelPanel(this);
            tetrisFrame.getContentPane().add(selectLevelPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("ultra")){
            gameMode= GAME_MODE.ULTRA;
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            selectLevelPanel= new SelectLevelPanel(this);
            tetrisFrame.getContentPane().add(selectLevelPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("sprint")){
            gameMode= GAME_MODE.SPRINT;
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            selectLevelPanel= new SelectLevelPanel(this);
            tetrisFrame.getContentPane().add(selectLevelPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("start")){
            level= selectLevelPanel.getLevel();
            if (playerMode == PLAYER_MODE.SINGLE_PLAYER){
                if (gameMode == GAME_MODE.MARATHON){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    gamePanel= new GamePanel(this);
                    gamePanel.getTetrisGameLogic().setLevel(level);
                    gamePanel.getTetrisGameLogic().setGameMode(0);
                    
                    tetrisFrame.getContentPane().add(gamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    gamePanel.start();
                    
                }
                else if (gameMode == GAME_MODE.SPRINT){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    gamePanel= new GamePanel(this);
                    gamePanel.getTetrisGameLogic().setLevel(level);
                    gamePanel.getTetrisGameLogic().setGameMode(1);
                    tetrisFrame.getContentPane().add(gamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    gamePanel.start();
                }
                else if (gameMode == GAME_MODE.ULTRA) {//ULTRA
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    gamePanel= new GamePanel(this);
                    gamePanel.getTetrisGameLogic().setLevel(level);
                    gamePanel.getTetrisGameLogic().setGameMode(2);
                    
                    tetrisFrame.getContentPane().add(gamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    gamePanel.start();
                }
            }
            else{//TWO_PLAYERS
                if (gameMode == GAME_MODE.MARATHON){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    twoPlayersGamePanel= new TwoPlayerGamePanel(this);
                    twoPlayersGamePanel.getTetraLogicOne().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicOne().setGameMode(0);
                    
                    twoPlayersGamePanel.getTetraLogicTwo().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicTwo().setGameMode(0);
                    
                    
                    tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    twoPlayersGamePanel.start();
                }
                else if (gameMode == GAME_MODE.SPRINT){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    twoPlayersGamePanel= new TwoPlayerGamePanel(this);
                    twoPlayersGamePanel.getTetraLogicOne().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicOne().setGameMode(1);
                    
                    twoPlayersGamePanel.getTetraLogicTwo().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicTwo().setGameMode(1);
                    
                    
                    tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    twoPlayersGamePanel.start();
                }
                else{//ULTRA
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    twoPlayersGamePanel= new TwoPlayerGamePanel(this);
                    twoPlayersGamePanel.getTetraLogicOne().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicOne().setGameMode(2);
                    
                    twoPlayersGamePanel.getTetraLogicTwo().setLevel(level);
                    twoPlayersGamePanel.getTetraLogicTwo().setGameMode(2);
                    
                    
                    tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                    twoPlayersGamePanel.start();
                }
            }
        }
        else if (command.equals("yesExitFromApplication")){
            System.exit(0);
        }
        else if (command.equals("noExitFromApplication")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            mainMenuPanel= new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("backFromPlayerMode")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            mainMenuPanel = new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("backFromGameMode")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            playerModePanel = new PlayerModePanel(this);
            tetrisFrame.getContentPane().add(playerModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("quitFromSinglePlayerGame")){
            
            gamePanel.stopMusic();
            
            //Prima di essere salvato va messo in pausa
            if (gamePanel.getTetrisGameLogic().isGameRunning()){
                gamePanel.getTetrisGameLogic().pauseTetraminoLogic();
            }
            
            TetrisGameLogic tgl= gamePanel.getTetrisGameLogic();
            
            if ( !gamePanel.getTetrisGameLogic().isGameOver() ){
                if (tgl.getGameMode()==0){
                    saveGameLogic(tgl, "data/single_player_marathon.game" );
                }
                else if (tgl.getGameMode()==1){
                    saveGameLogic(tgl, "data/single_player_sprint.game" );
                }
                else {
                    saveGameLogic(tgl, "data/single_player_ultra.game" );
                }
            }
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            gamePanel=null;
            
            mainMenuPanel= new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("quitFromTwoPlayerGame")){
            
            twoPlayersGamePanel.stopMusic();
            
            if (twoPlayersGamePanel.getTetraLogicOne().isGameRunning()){
                twoPlayersGamePanel.getTetraLogicOne().pauseTetraminoLogic();
            }
            if (twoPlayersGamePanel.getTetraLogicTwo().isGameRunning()){
                twoPlayersGamePanel.getTetraLogicTwo().pauseTetraminoLogic();
            }
            
            if (twoPlayersGamePanel.getTetraLogicOne().getGameMode()==0){
                saveGameLogic(twoPlayersGamePanel.getTetraLogicOne(), twoPlayersGamePanel.getTetraLogicTwo(), "data/two_players_marathon.game" );
            }
            else if (twoPlayersGamePanel.getTetraLogicOne().getGameMode()==1){
                saveGameLogic(twoPlayersGamePanel.getTetraLogicOne(), twoPlayersGamePanel.getTetraLogicTwo(), "data/two_players_sprint.game" );
            }
            else {
                saveGameLogic(twoPlayersGamePanel.getTetraLogicOne(), twoPlayersGamePanel.getTetraLogicTwo(), "data/two_players_ultra.game" );
            }
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            twoPlayersGamePanel= null;
            
            mainMenuPanel= new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("marathonScore")){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
           
        }
        else if (command.equals("sprintScore")){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
        }
        else if (command.equals("ultraScore")){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
        }
        else if (command.equals("backFromScoreMode")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            mainMenuPanel= new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("backFromHighScore")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            scoreModePanel = new ChooseScoreModePanel(this);
            tetrisFrame.getContentPane().add(scoreModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("backFromResumePlayerMode")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            mainMenuPanel = new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("resumeSinglePlayer")){
            playerMode= PLAYER_MODE.SINGLE_PLAYER;
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            resumeGameModePanel = new ResumeGameModePanel(this, false);
            tetrisFrame.getContentPane().add(resumeGameModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
            
        }
        else if (command.equals("resumeTwoPlayers")){
            playerMode= PLAYER_MODE.TWO_PLAYERS;
            
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            resumeGameModePanel = new ResumeGameModePanel(this, true);
            tetrisFrame.getContentPane().add(resumeGameModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("backFromResumeGameMode")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            resumeGamePlayerModePanel= new ResumeGamePlayerModePanel(this);
            tetrisFrame.getContentPane().add(resumeGamePlayerModePanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
        else if (command.equals("resumeMarathon")){
            if (playerMode == PLAYER_MODE.SINGLE_PLAYER){
                TetrisGameLogic tgl= loadGameLogic("data/single_player_marathon.game");
                tgl.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                gamePanel= new GamePanel(this, tgl);
                tetrisFrame.getContentPane().add(gamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                gamePanel.getTetrisGameLogic().pauseTetraminoLogic();
            }else{
                TetrisGameLogic [] tglArray= loadGameLogics("data/two_players_marathon.game");
                TetrisGameLogic tgl1= tglArray[0];
                TetrisGameLogic tgl2= tglArray[1];
                tgl1.loadImages();
                tgl2.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                twoPlayersGamePanel= new TwoPlayerGamePanel(this, tgl1, tgl2);
                tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                
                if (! twoPlayersGamePanel.getTetraLogicOne().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicOne().pauseTetraminoLogic();
                }
                if (! twoPlayersGamePanel.getTetraLogicTwo().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicTwo().pauseTetraminoLogic();
                }
            }
        }
        else if (command.equals("resumeSprint")){
            if (playerMode == PLAYER_MODE.SINGLE_PLAYER){
                TetrisGameLogic tgl= loadGameLogic("data/single_player_sprint.game");
                tgl.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                gamePanel= new GamePanel(this, tgl);
                tetrisFrame.getContentPane().add(gamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                gamePanel.getTetrisGameLogic().pauseTetraminoLogic();
            }else{
                TetrisGameLogic [] tglArray= loadGameLogics("data/two_players_sprint.game");
                TetrisGameLogic tgl1= tglArray[0];
                TetrisGameLogic tgl2= tglArray[1];
                tgl1.loadImages();
                tgl2.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                twoPlayersGamePanel= new TwoPlayerGamePanel(this, tgl1, tgl2);
                tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                
                if (! twoPlayersGamePanel.getTetraLogicOne().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicOne().pauseTetraminoLogic();
                }
                if (! twoPlayersGamePanel.getTetraLogicTwo().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicTwo().pauseTetraminoLogic();
                }
            }
        }
        else if (command.equals("resumeUltra")){
            if (playerMode == PLAYER_MODE.SINGLE_PLAYER){
                TetrisGameLogic tgl= loadGameLogic("data/single_player_ultra.game");
                tgl.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                gamePanel= new GamePanel(this, tgl);
                tetrisFrame.getContentPane().add(gamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                gamePanel.getTetrisGameLogic().pauseTetraminoLogic();
            }else{
                TetrisGameLogic [] tglArray= loadGameLogics("data/two_players_ultra.game");
                TetrisGameLogic tgl1= tglArray[0];
                TetrisGameLogic tgl2= tglArray[1];
                tgl1.loadImages();
                tgl2.loadImages();
                
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                twoPlayersGamePanel= new TwoPlayerGamePanel(this, tgl1, tgl2);
                tetrisFrame.getContentPane().add(twoPlayersGamePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
                
                if (! twoPlayersGamePanel.getTetraLogicOne().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicOne().pauseTetraminoLogic();
                }
                if (! twoPlayersGamePanel.getTetraLogicTwo().isGameOver()){
                    twoPlayersGamePanel.getTetraLogicTwo().pauseTetraminoLogic();
                }
            }
        }
        else if (command.equals("backFromHelp")){
            tetrisFrame.getContentPane().removeAll();
            tetrisFrame.getContentPane().invalidate();
            
            mainMenuPanel= new MainMenuPanel(this);
            tetrisFrame.getContentPane().add(mainMenuPanel);
            tetrisFrame.getContentPane().validate();
            tetrisFrame.getContentPane().repaint();
        }
    }//actionPerformed
    
    /**
     * Salva il gioco, il metodo tuttavia non si preoccupa di salvare in modo corretto lo stato, il gioco
     * va prima messo in pausa, in quanto i Thread non sono serializzabili
     * @param tgl
     * @param fileName 
     */
    public void saveGameLogic(TetrisGameLogic tgl, String fileName){
        File file= new File(fileName);
        boolean fileExist=false;
        try {
           fileExist = file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fileExist){//il file e' stato creato
            try {
                ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                objectOut.writeObject(tgl);
                objectOut.close();
            } catch (IOException ex) {
                Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {//il file esisteva gia'
            try {
                ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                objectOut.writeObject(tgl);
                objectOut.close();
            } catch (IOException ex) {
                Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//saveGameLogic
    
    public void saveGameLogic(TetrisGameLogic tgl1, TetrisGameLogic tgl2, String fileName){
        File file= new File(fileName);
        boolean fileExist=false;
        try {
           fileExist = file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(TetrisBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fileExist){//il file e' stato creato
            try {
                ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                objectOut.writeObject(tgl1);
                objectOut.writeObject(tgl2);
                objectOut.close();
            } catch (IOException ex) {
                Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {//il file esisteva gia'
            try {
                ObjectOutputStream objectOut= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                objectOut.writeObject(tgl1);
                objectOut.writeObject(tgl2);
                objectOut.close();
            } catch (IOException ex) {
                Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//saveGameLogic
    
    /**
     * Carica la logica del gioco, il gioco appena caricato e' in pausa.
     * @param fileName
     * @return 
     */
    public TetrisGameLogic loadGameLogic(String fileName) {
        TetrisGameLogic tgl=null;
        ObjectInputStream objectIn;
        try {
            objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            tgl= (TetrisGameLogic) objectIn.readObject();
            objectIn.close();
        } catch (Exception ex) {
            Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tgl;
    }//loadGameLogic
    
     /**
     * Carica le logiche del gioco in modalita' two players, il gioco appena caricato e' in pausa.
     * @param fileName
     * @return 
     */
    public TetrisGameLogic[] loadGameLogics(String fileName) {
        TetrisGameLogic[] tgl= new TetrisGameLogic[2];
        ObjectInputStream objectIn;
        try {
            objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            tgl[0]= (TetrisGameLogic) objectIn.readObject();
            tgl[1]= (TetrisGameLogic) objectIn.readObject();
            objectIn.close();
        } catch (Exception ex) {
            Logger.getLogger(TetrisPanelsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tgl;
    }//loadGameLogics
    
    private void setPanelsToNull(){
        tetrisFrame=null;
        mainMenuPanel=null;
        playerModePanel=null;
        gamePanel=null;
        gameModePanel=null;
        twoPlayersGamePanel=null;
        selectLevelPanel=null;
        confirmExitPanel=null;
        scoreModePanel=null;
        scorePanel=null;
        resumeGamePlayerModePanel=null;
        resumeGameModePanel=null;
    }//setPanelsToNull
    
    public void loadScorePanel(int i){
        if (i==0){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/marathon_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
        }//marathon
        if (i==1){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/sprint_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
        }//sprint
        if (i==2){
            ObjectInputStream objectIn;
            try {
                objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data/ultra_score.scores")));
                Object ob= objectIn.readObject();
                objectIn.close();
                TetrisTopTenScore topScores= (TetrisTopTenScore) ob;
                tetrisFrame.getContentPane().removeAll();
                tetrisFrame.getContentPane().invalidate();
            
                scorePanel= new HighScorePanel(topScores, this);
                tetrisFrame.getContentPane().add(scorePanel);
                tetrisFrame.getContentPane().validate();
                tetrisFrame.getContentPane().repaint();
            } catch (Exception ex) {
                if (ex instanceof FileNotFoundException){
                    tetrisFrame.getContentPane().removeAll();
                    tetrisFrame.getContentPane().invalidate();
            
                    scorePanel= new HighScorePanel(null, this);
                    tetrisFrame.getContentPane().add(scorePanel);
                    tetrisFrame.getContentPane().validate();
                    tetrisFrame.getContentPane().repaint();
                }
            }
        }//ultra
        
    }//loadScorePanel
}
