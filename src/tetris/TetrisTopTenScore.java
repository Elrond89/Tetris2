/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.Serializable;

/**
 *
 * @author antonioruffolo
 */
public class TetrisTopTenScore implements Serializable {
    private TetrisPlayerResult[] array= new TetrisPlayerResult[17];
    private boolean timeScore= false;
    
    public void add(TetrisPlayerResult p){
        ScoreComparator sc= new ScoreComparator();
        TimeComparator tc= new TimeComparator();
        
        int i=0;
        boolean inserted=false;
        while (!inserted){
            if (array[i]==null){
                array[i]=p;
                break;
            }
            else if (sc.compare(p, array[i])>0 && !timeScore) {
                moveElementsDown(i);
                array[i]=p;
                break;
            }
            else if(tc.compare(p, array[i])<0 && timeScore){
                moveElementsDown(i);
                array[i]=p;
                break;
            }
            i++;
            if (i==17) return;
        }
    }//add
    
    public void setTimeScore(boolean timeScore){
        this.timeScore=timeScore;
    }
    
    public TetrisPlayerResult[] getArray(){
        return array;
    }
    
    private void moveElementsDown(int index){
        array[16]=null;
        
        for(int i=16; i>index; i--){
            array[i]=array[i-1];
        }
    }//moveElementsDown
    
    @Override
    public String toString(){
        StringBuilder sb= new StringBuilder(10);
        
        for (int i=0; i<17; i++){
            if (array[i]!= null){
                sb.append(array[i].toString());
                if (i != 16){
                    sb.append(", ");
                }
            }
                
            
        }
        return sb.toString();
    }//toString
    
    public static void main (String args[]){
        TetrisPlayerResult t1= new TetrisPlayerResult("player1", 10000, 2, 36);
        TetrisPlayerResult t2= new TetrisPlayerResult("player2", 2000, 5, 41);
        TetrisPlayerResult t3= new TetrisPlayerResult("player3", 5000, 2, 12);
        TetrisPlayerResult t4= new TetrisPlayerResult("player4", 5000, 2, 12);
        TetrisPlayerResult t5= new TetrisPlayerResult("player5", 4000, 2, 12);
        TetrisPlayerResult t6= new TetrisPlayerResult("player6", 7000, 2, 12);
        TetrisPlayerResult t7= new TetrisPlayerResult("player7", 2300, 2, 12);
        TetrisPlayerResult t8= new TetrisPlayerResult("player8", 1000, 2, 12);
        TetrisPlayerResult t9= new TetrisPlayerResult("player9", 9000, 2, 12);
        TetrisPlayerResult t10= new TetrisPlayerResult("player10", 65000, 2, 12);
        TetrisPlayerResult t11= new TetrisPlayerResult("player11", 5500, 2, 12);
        
        TetrisTopTenScore ttt= new TetrisTopTenScore();
        ttt.add(t1);
        ttt.add(t2);
        ttt.add(t3);
        ttt.add(t4);
        ttt.add(t5);
        ttt.add(t6);
        ttt.add(t7);
        ttt.add(t8);
        ttt.add(t9);
        ttt.add(t10);
        ttt.add(t11);
        
        
        System.out.println(ttt);
    }
    
}//TetristoptenScore
