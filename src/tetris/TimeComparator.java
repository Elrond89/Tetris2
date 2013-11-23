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
public class TimeComparator implements Comparator{

        @Override
        public int compare(Object t, Object t1) {
            TetrisPlayerResult tpr1= (TetrisPlayerResult) t;
            TetrisPlayerResult tpr2= (TetrisPlayerResult) t1;
            
            if (tpr1.getPlayerMinutes() > tpr2.getPlayerMinutes()){
                return 1;
            }
            else if (tpr1.getPlayerMinutes() < tpr2.getPlayerMinutes()){
                return -1;
            }
            else if (tpr1.getPlayerSeconds() > tpr2.getPlayerSeconds()){
                return 1;
            }
            else if (tpr1.getPlayerSeconds() < tpr2.getPlayerSeconds() ){
                return -1;
            }
            
            return 0;
        }//compare
    }//TimeComparator
