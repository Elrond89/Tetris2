/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.Comparator;

/**
 *
 * @author Antonio
 */
public class ScoreComparator implements Comparator{

        @Override
        public int compare(Object t, Object t1) {
            TetrisPlayerResult tpr1= (TetrisPlayerResult) t;
            TetrisPlayerResult tpr2= (TetrisPlayerResult) t1;
            
            if (tpr1.getPlayerScore() > tpr2.getPlayerScore()){
                return 1;
            }
            else if (tpr1.getPlayerScore() < tpr2.getPlayerScore()){
                return -1;
            }
            return 0;
        }//compare
}//ScoreComparqtor
