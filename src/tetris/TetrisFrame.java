/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javax.swing.JFrame;

/**
 *
 * @author antonioruffolo
 */
public class TetrisFrame extends JFrame {
    private TetrisPanelsController tetrisPanelController;
    private GamePanel gamePanel;

    public TetrisFrame() {
        
        tetrisPanelController= new TetrisPanelsController(this);
        //add(new TwoPlayerGamePanel());
        /*gamePanel= new GamePanel();
        add(gamePanel);
        gamePanel.getTetrisGameLogic().setGameMode(1);
        gamePanel.getTetrisGameLogic().setLevel(9);
        gamePanel.start();*/
        //add(new MainMenuPanel());
        //add(new SelectLevelPanel());
        //add(new ChooseGameModePanel());
        //add(new PlayerModePanel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(800, 622);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Tetris");
        setFocusable(true);
        this.getRootPane().setDoubleBuffered(true);
    }

    public static void main(String[] args) {
        TetrisFrame t = new TetrisFrame();
    }
}
