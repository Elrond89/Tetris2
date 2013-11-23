/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Antonio
 */
public class TetrisPlayerResult implements Serializable{
    private String playerName;
    private int playerScore, playerMinutes, playerSeconds;
    
    public TetrisPlayerResult(String playerName, int playerScore, int playerMinutes, int playerSeconds){
        if (playerName.length() > 22) throw new IllegalArgumentException("Stringa troppo lunga");
        this.playerName=playerName;
        this.playerScore=playerScore;
        this.playerMinutes=playerMinutes;
        this.playerSeconds=playerSeconds;
    }
    
    public String getPlayerName(){
        return playerName;
    }
    
    public int getPlayerScore(){
        return playerScore;
    }
    
    public int getPlayerMinutes(){
        return playerMinutes;
    }
    
    public int getPlayerSeconds(){
        return playerSeconds;
    }
    
    public String timeInString(){
        String s, m;
        if (playerSeconds<10){
            s="0"+playerSeconds;
        }
        else {
            s=""+playerSeconds;
        }
        if (playerMinutes<10){
            m="0"+playerMinutes;
        }
        else{
            m=""+playerMinutes;
        }
        return m+":"+s;
    }
    
    @Override
    public String toString(){
        return playerName+" "+playerScore+" "+timeInString();
    }//toString
    
    public static void main (String [] args ){
        TetrisPlayerResult tOne= new TetrisPlayerResult("PlayerOne", 10300, 2, 34);
        TetrisPlayerResult tTwo= new TetrisPlayerResult("PlayerTwo", 10200, 3, 02);
        TimeComparator tc= new TimeComparator();
        ScoreComparator sc= new ScoreComparator();
        
        System.out.println(sc.compare(tOne, tTwo));
        System.out.println(tOne);
    }//main
    
}//TetrisPlayerResult
