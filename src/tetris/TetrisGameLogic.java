/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import tetris.Quadrato.ImageColour;

/**
 *
 * @author AntonioRuffolo
 */
public class TetrisGameLogic implements Serializable {

    private Queue<Tetramino> tetraQueue = new ConcurrentLinkedQueue<Tetramino>(); //questa è la coda dei pezzi che dovranno scendere
    private Tetramino t;
    private Tetramino ghost;
    private Quadrato[][] tetraMatrix = new Quadrato[20][10];
    private boolean gameRunning, gameOver, linesAreReadyToBeDeleted;
    private int totalScore, linesCleanAtTheLevel, linesCleanTotal, level, speedCoefficient, minutes, seconds, secondsPeriod, flashCounter;

    private enum GAME_MODE {

        MATATHON, SPRINT, ULTRA
    };
    private GAME_MODE modeSelected = GAME_MODE.MATATHON;
    private long clockTicker, stopTicker, gameTicker, flashTicker;

    public TetrisGameLogic() {
        secondsPeriod = 1000;
        seconds = -1;
        gameRunning = true;
        initTetraQueue();
        initTetraMatrix();
    }

    public Tetramino getTetramino() {
        return t;
    }

    public Queue<Tetramino> getTetraQueue() {
        return tetraQueue;
    }

    public Quadrato[][] getTetraMatrix() {
        return tetraMatrix;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return totalScore;
    }

    public int getLines() {
        return linesCleanTotal;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getGameMode() {
        if (modeSelected == GAME_MODE.MATATHON) {
            return 0;
        }
        if (modeSelected == GAME_MODE.SPRINT) {
            return 1;
        }
        return 2;
    }//getGameMode

    public boolean isGameRunning() {
        return gameRunning;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Tetramino getTetraGhost() {
        return ghost;
    }

    public void setLevel(int level) {
        this.level = level;
        updateSpeedCoefficent();
    }

    /**
     * selezione del gamemode, 0 sta per marathon, 1 sta per sprint, 2 sta per
     * ultra
     *
     * @param i
     */
    public void setGameMode(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("int not valid");
        }

        if (i == 0) {
            modeSelected = GAME_MODE.MATATHON;
        } else if (i == 1) {
            modeSelected = GAME_MODE.SPRINT;
        } else if (i == 2) {
            modeSelected = GAME_MODE.ULTRA;
        }
    }

    public void start() {
        /*gameRunning=true;
         initTetraQueue();
         initTetraMatrix();*/
    }

    private void initTetraQueue() {
        int cont = 4;
        while (cont > 0) {
            int i = (int) (Math.random() * 6);
            switch (i) {
                case 0:
                    tetraQueue.offer(new TetraI());
                    break;
                case 1:
                    tetraQueue.offer(new TetraJ());
                    break;
                case 2:
                    tetraQueue.offer(new TetraL());
                    break;
                case 3:
                    tetraQueue.offer(new TetraO());
                    break;
                case 4:
                    tetraQueue.offer(new TetraS());
                    break;
                case 5:
                    tetraQueue.offer(new TetraT());
                    break;
                case 6:
                    tetraQueue.offer(new TetraZ());
                    break;
            }//switch
            cont--;
        }//while
        int i = (int) (Math.random() * 6);
        switch (i) {
            case 0:
                t = new TetraI();
                break;
            case 1:
                t = new TetraJ();
                break;
            case 2:
                t = new TetraL();
                break;
            case 3:
                t = new TetraO();
                break;
            case 4:
                t = new TetraS();
                break;
            case 5:
                t = new TetraT();
                break;
            case 6:
                t = new TetraZ();
                break;
        }//switch
        //ghost= t.getTetraGhost();
        //moveGhostToTheBottom();
    }//initTetraQueue

    private void initTetraMatrix() {
        for (int i = 0; i < tetraMatrix.length; i++) {
            for (int j = 0; j < tetraMatrix[i].length; j++) {
                Quadrato q = new Quadrato(0, 0, ImageColour.BLUE);
                q.setVisible(false);
                tetraMatrix[i][j] = q;
            }
        }
    }

    private void insertTetraminoInMatrix() {
        for (int i = 0; i < t.getTetramino().length; i++) {
            int a = t.getTetramino()[i].getX() / 20;
            int b = t.getTetramino()[i].getY() / 20;
            tetraMatrix[b][a].setX(a * 20);
            tetraMatrix[b][a].setY(b * 20);
            tetraMatrix[b][a].setImageSelected(t.getTetramino()[i].getImageSelected());
            tetraMatrix[b][a].setVisible(true);
        }
    }

    private void changeTetramino() {
        int i = (int) (Math.random() * 7);
        switch (i) {
            case 0:
                tetraQueue.offer(new TetraI());
                break;
            case 1:
                tetraQueue.offer(new TetraJ());
                break;
            case 2:
                tetraQueue.offer(new TetraL());
                break;
            case 3:
                tetraQueue.offer(new TetraO());
                break;
            case 4:
                tetraQueue.offer(new TetraS());
                break;
            case 5:
                tetraQueue.offer(new TetraT());
                break;
            case 6:
                tetraQueue.offer(new TetraZ());
                break;
        }//switch
        t = tetraQueue.poll();
        ghost = t.getTetraGhost();
        moveGhostToTheBottom();

        /*if (checkGameOver()) {
            gameOver = true;
            gameRunning = false;
        }*/
    }

    /**
     * ogni volta che devo inserire un nuovo tetramino verifico che il gioco
     * possa continuare
     *
     * @return
     */
    private boolean checkGameOver() {
        if (tetraMatrix[0][3].isVisible() || tetraMatrix[0][4].isVisible() || tetraMatrix[0][5].isVisible()
                || tetraMatrix[0][6].isVisible()) {
            return true;
        }
        if (modeSelected == GAME_MODE.SPRINT) {
            if (linesCleanTotal >= 40) {
                return true;
            }
        } else if (modeSelected == GAME_MODE.ULTRA) {
            if (minutes >= 2) {
                return true;
            }
        }

        return false;
    }

    public void updateTime(long gameTime) {
        if (gameRunning) {
            if (gameTime > clockTicker + secondsPeriod) {
                clockTicker = gameTime;
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    minutes++;
                }
            }
        }
    }

    public void updateGame(long gameTime) {
        if (gameRunning) {
            if (allowedToMoveDown() && !t.isNotMoving()) {
                stopTicker = gameTime;
                moveDownTetramino(gameTime);
            } else {
                shouldBeStopped(gameTime);
                flashLinesToBeDeleted(gameTime);
            }
            if (checkGameOver()) {
                gameOver = true;
                gameRunning = false;
            }
        }

        //printTetraMatrix();
    }//updateGame

    private void shouldBeStopped(long gameTime) {
        if (gameTime > stopTicker + 500 && !linesAreReadyToBeDeleted) {
            t.stop();
            insertTetraminoInMatrix();

            ArrayList<Integer> rowsToBeDeleted = findCompletedLines();
            if (rowsToBeDeleted.isEmpty()) {
                clearCompletedLines(rowsToBeDeleted);
                updateScores(rowsToBeDeleted);
                changeTetramino();
                linesAreReadyToBeDeleted = false;
            } else {
                flashTicker = gameTime;
                flashCounter = 0;
                linesAreReadyToBeDeleted = true;
            }

        }
    }

    private void flashLinesToBeDeleted(long gameTime) {
        ArrayList<Integer> rowsToBeDeleted = findCompletedLines();
        if (linesAreReadyToBeDeleted && gameTime > flashTicker + 110 && flashCounter < 6) {
            flashTicker = gameTime;
            flashCounter++;
            swapImageOfCompletedLines(rowsToBeDeleted);
        } else if (flashCounter >= 6 && linesAreReadyToBeDeleted) {//è arrivato il momento di eliminare le linee, aggiornare il punteggio e cambiare il tetramino
            flashCounter = 0;
            clearCompletedLines(rowsToBeDeleted);
            updateScores(rowsToBeDeleted);
            changeTetramino();
            linesAreReadyToBeDeleted = false;
        }
    }

    private void moveDownTetramino(long gameTime) {
        if (allowedToMoveDown() && !t.isNotMoving()) {
            if (gameTime > gameTicker + 1000 - speedCoefficient) {
                t.move();
                ghost = t.getTetraGhost();
                moveGhostToTheBottom();
                gameTicker = gameTime;
            }
        }
    }

    private void moveToTheBottom() {
        while (allowedToMoveDown()) {
            t.move();
            totalScore += 2;
        }
        t.stop();
        insertTetraminoInMatrix();
        ArrayList<Integer> rowsToBeDeleted = findCompletedLines();
        if (rowsToBeDeleted.isEmpty()) {
            clearCompletedLines(rowsToBeDeleted);
            updateScores(rowsToBeDeleted);
            changeTetramino();
            linesAreReadyToBeDeleted = false;
        } else if(!linesAreReadyToBeDeleted) {
            flashTicker = System.currentTimeMillis();
            flashCounter = 0;
            linesAreReadyToBeDeleted = true;
        }
        //ArrayList<Integer> rowsToBeDeleted = findCompletedLines();
        //changeTetramino();
    }

    private void moveGhostToTheBottom() {
        while (ghostAllowedToMoveDown()) {
            ghost.move();
        }
    }//moveGhostToTheBottom

    /**
     * Swap the image of each square of the completed lines, it's used to flash
     * the lineas that are going to be deleted
     *
     * @param l
     */
    private void swapImageOfCompletedLines(ArrayList<Integer> l) {
        for (int i = 0; i < l.size(); i++) {
            int index = l.get(i);
            if (tetraMatrix[index][0].useInvertedImage()) {
                //System.out.println("Linee Swappate");
                tetraMatrix[index][0].setUseInvertedImage(false);
                tetraMatrix[index][1].setUseInvertedImage(false);
                tetraMatrix[index][2].setUseInvertedImage(false);
                tetraMatrix[index][3].setUseInvertedImage(false);
                tetraMatrix[index][4].setUseInvertedImage(false);
                tetraMatrix[index][5].setUseInvertedImage(false);
                tetraMatrix[index][6].setUseInvertedImage(false);
                tetraMatrix[index][7].setUseInvertedImage(false);
                tetraMatrix[index][8].setUseInvertedImage(false);
                tetraMatrix[index][9].setUseInvertedImage(false);
            } else {
                tetraMatrix[index][0].setUseInvertedImage(true);
                tetraMatrix[index][1].setUseInvertedImage(true);
                tetraMatrix[index][2].setUseInvertedImage(true);
                tetraMatrix[index][3].setUseInvertedImage(true);
                tetraMatrix[index][4].setUseInvertedImage(true);
                tetraMatrix[index][5].setUseInvertedImage(true);
                tetraMatrix[index][6].setUseInvertedImage(true);
                tetraMatrix[index][7].setUseInvertedImage(true);
                tetraMatrix[index][8].setUseInvertedImage(true);
                tetraMatrix[index][9].setUseInvertedImage(true);
            }
        }
    }

    /*
     * elimina le linee complete della matrice, invece che void potrebbe restituire
     * il numero di linee eliminate
     */
    private void clearCompletedLines(ArrayList<Integer> l) {
        for (int i = 0; i < l.size(); i++) {
            clearMatrixFromLine(l.get(i));
        }
    }

    private void clearMatrixFromLine(int line) {
        for (int i = line; i > 1; i--) {
            tetraMatrix[i][0].setVisible(tetraMatrix[i - 1][0].isVisible());
            tetraMatrix[i][0].setImageSelected(tetraMatrix[i - 1][0].getImageSelected());

            tetraMatrix[i][1].setVisible(tetraMatrix[i - 1][1].isVisible());
            tetraMatrix[i][1].setImageSelected(tetraMatrix[i - 1][1].getImageSelected());

            tetraMatrix[i][2].setVisible(tetraMatrix[i - 1][2].isVisible());
            tetraMatrix[i][2].setImageSelected(tetraMatrix[i - 1][2].getImageSelected());

            tetraMatrix[i][3].setVisible(tetraMatrix[i - 1][3].isVisible());
            tetraMatrix[i][3].setImageSelected(tetraMatrix[i - 1][3].getImageSelected());

            tetraMatrix[i][4].setVisible(tetraMatrix[i - 1][4].isVisible());
            tetraMatrix[i][4].setImageSelected(tetraMatrix[i - 1][4].getImageSelected());

            tetraMatrix[i][5].setVisible(tetraMatrix[i - 1][5].isVisible());
            tetraMatrix[i][5].setImageSelected(tetraMatrix[i - 1][5].getImageSelected());

            tetraMatrix[i][6].setVisible(tetraMatrix[i - 1][6].isVisible());
            tetraMatrix[i][6].setImageSelected(tetraMatrix[i - 1][6].getImageSelected());

            tetraMatrix[i][7].setVisible(tetraMatrix[i - 1][7].isVisible());
            tetraMatrix[i][7].setImageSelected(tetraMatrix[i - 1][7].getImageSelected());

            tetraMatrix[i][8].setVisible(tetraMatrix[i - 1][8].isVisible());
            tetraMatrix[i][8].setImageSelected(tetraMatrix[i - 1][8].getImageSelected());

            tetraMatrix[i][9].setVisible(tetraMatrix[i - 1][9].isVisible());
            tetraMatrix[i][9].setImageSelected(tetraMatrix[i - 1][9].getImageSelected());
        }
        tetraMatrix[0][0].setVisible(false);
        tetraMatrix[0][1].setVisible(false);
        tetraMatrix[0][2].setVisible(false);
        tetraMatrix[0][3].setVisible(false);
        tetraMatrix[0][4].setVisible(false);
        tetraMatrix[0][5].setVisible(false);
        tetraMatrix[0][6].setVisible(false);
        tetraMatrix[0][7].setVisible(false);
        tetraMatrix[0][8].setVisible(false);
        tetraMatrix[0][9].setVisible(false);
    }

    /*
     * questo metodo controlla le linee dove i quadrati sono visibili
     * se li trova li aggiunge ad un ArrayList che poi li passera' ad un altro metodo
     * che li eliminera'
     */
    private ArrayList<Integer> findCompletedLines() {
        ArrayList<Integer> rowToBeDeleted = new ArrayList<Integer>(4);
        int cont = 0;
        for (int i = 0; i < 20; i++) {
            if (tetraMatrix[i][0].isVisible()
                    && tetraMatrix[i][1].isVisible()
                    && tetraMatrix[i][2].isVisible()
                    && tetraMatrix[i][3].isVisible()
                    && tetraMatrix[i][4].isVisible()
                    && tetraMatrix[i][5].isVisible()
                    && tetraMatrix[i][6].isVisible()
                    && tetraMatrix[i][7].isVisible()
                    && tetraMatrix[i][8].isVisible()
                    && tetraMatrix[i][9].isVisible()) {

                rowToBeDeleted.add(cont, i);
                cont++;
                /*
                 * array contains the number of the line completed
                 * cont is used to increase the position in the array
                 * where the number line completed is stored
                 */
                //System.out.println("Linee trovate da eliminare:"+cont);
                //System.out.println("Size dell'array:"+rowToBeDeleted.size());
            }//if
        }//for

        /*System.out.println("Livello:"+level+"\nPunteggio totale:"+totalScore+
         "\nLinee eliminate al livello:"+linesCleanAtTheLevel+
         "\nLinee eliminate totalmente:"+linesCleanTotal+"\n");*/
        return rowToBeDeleted;

    }//findCompleteLines

    private int punteggioLineeEliminate(ArrayList<Integer> l) {
        int i = l.size();
        switch (i) {
            case 1:
                return level * 40 + 40;
            case 2:
                return level * 100 + 100;
            case 3:
                return level * 300 + 300;
            case 4:
                return level * 1200 + 1200;
        }//switch
        return 0;//non ritornera' mai 0, pero' java lo vuole lo stesso
    }

    private void updateScores(ArrayList<Integer> l) {
        int c = punteggioLineeEliminate(l);
        totalScore += c;
        int lines = l.size();
        linesCleanAtTheLevel += lines;
        linesCleanTotal += lines;
        if (linesCleanAtTheLevel >= (level + 1) * 5) {
            level++;
            linesCleanAtTheLevel = 0;
            updateSpeedCoefficent();
        }

    }

    private void updateSpeedCoefficent() {
        switch (level) {
            case 0:
                speedCoefficient = 0;
                break;
            case 1:
                speedCoefficient = 90;
                break;
            case 2:
                speedCoefficient = 180;
                break;
            case 3:
                speedCoefficient = 270;
                break;
            case 4:
                speedCoefficient = 395;
            case 5:
                speedCoefficient = 500;
                break;
            case 6:
                speedCoefficient = 590;
                break;
            case 7:
                speedCoefficient = 700;
                break;
            case 8:
                speedCoefficient = 820;
                break;
            case 9:
                speedCoefficient = 890;
                break;
        }//switch
    }

    //questo va rivisto completamente
    public void rotateCCWCurrentTetramino() {
        if (allowedToRotate() && !t.isNotMoving()) {
            stopTicker = System.currentTimeMillis();
            t.rotateCCW();
            ghost = t.getTetraGhost();
            moveGhostToTheBottom();
        } else if (attemptToRotateCCW()) {
            stopTicker = System.currentTimeMillis();
            ghost = t.getTetraGhost();
            moveGhostToTheBottom();
        }
    }//rotteCurrentTetraminoCCW

    public void moveLeftCurrentTetramino() {
        if (allowedToMoveLeft() && !t.isNotMoving()) {
            stopTicker = System.currentTimeMillis();
            t.moveLeft();
            ghost = t.getTetraGhost();
            moveGhostToTheBottom();
        }
    }

    public void moveRightCurrentTetramino() {
        if (allowedToMoveRight() && !t.isNotMoving()) {
            stopTicker = System.currentTimeMillis();
            t.moveRight();
            ghost = t.getTetraGhost();
            moveGhostToTheBottom();
        }
    }

    /**
     * this method is called when the player push the button, if the tetramino
     * hits something it won't be possible to move it again
     */
    public void pushDownCurrentTetramino() {
        if (allowedToMoveDown() && !t.isNotMoving()) {
            t.move();
            ghost = t.getTetraGhost();
            moveGhostToTheBottom();
            totalScore++;
            if (!allowedToMoveDown()) {
                t.stop();
                insertTetraminoInMatrix();
                ArrayList<Integer> rowsToBeDeleted = findCompletedLines();
                if (rowsToBeDeleted.isEmpty()) {
                    clearCompletedLines(rowsToBeDeleted);
                    updateScores(rowsToBeDeleted);
                    changeTetramino();
                    linesAreReadyToBeDeleted = false;
                } else if(!linesAreReadyToBeDeleted) {
                    flashTicker = System.currentTimeMillis();
                    flashCounter = 0;
                    linesAreReadyToBeDeleted = true;
                }
            }
        }
    }

    public void moveToTheBottomCurrentTetramino() {
        if (allowedToMoveDown() && !t.isNotMoving()) {
            moveToTheBottom();
        }
    }

    public void pauseTetraminoLogic() {
        if (gameRunning) {
            gameRunning = false;
        } else {
            gameRunning = true;
        }
    }

    private boolean intersectOnMatrix(Quadrato[] q) {
        for (int i = 0; i < 4; i++) {
            int r = q[i].getY() / 20; //indice di riga della matrice
            int c = q[i].getX() / 20; //indice di colonna della matrice
            if (r >= 0 && c >= 0 && r < 20 && c < 10) {
                if (tetraMatrix[r][c].isVisible()) {
                    return true;
                }
            }
        }
        return false;
    }//intersectOnMatrix

    private boolean allowedToMoveRight() {
        for (int i = 0; i < t.getTetramino().length; i++) {
            //System.out.println(tetraArray[i].getX());
            if (t.getTetramino()[i].getX() > 160) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il muro destro

        //qui controllo che nel prossimo movimento non tocchi altri pezzi
        Quadrato[] q = new Quadrato[4];
        for (int i = 0; i < 4; i++) {
            q[i] = new Quadrato(t.getTetramino()[i]);
        }
        for (int i = 0; i < 4; i++) {
            q[i].setX(q[i].getX() + 20);
        }
        if (intersectOnMatrix(q)) {
            return false;
        }


        return true;
    }

    private boolean allowedToMoveLeft() {
        for (int i = 0; i < t.getTetramino().length; i++) {
            //System.out.println(tetraArray[i].getX());
            if (t.getTetramino()[i].getX() < 20) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il muro sinistro

        //qui controllo che nel prossimo movimento non tocchi altri pezzi
        Quadrato[] q = new Quadrato[4];
        for (int i = 0; i < 4; i++) {
            q[i] = new Quadrato(t.getTetramino()[i]);
        }
        for (int i = 0; i < 4; i++) {
            q[i].setX(q[i].getX() - 20);
        }
        if (intersectOnMatrix(q)) {
            return false;
        }

        return true;
    }

    private boolean allowedToMoveDown() {
        for (int i = 0; i < t.getTetramino().length; i++) {
            if (t.getTetramino()[i].getY() > 360) {
                return false;
            }
        }

        //qui controllo che nel prossimo movimento non tocchi altri pezzi
        Quadrato[] q = new Quadrato[4];
        for (int i = 0; i < 4; i++) {
            q[i] = new Quadrato(t.getTetramino()[i]);
        }
        for (int i = 0; i < 4; i++) {
            q[i].setY(q[i].getY() + 20);
        }
        if (intersectOnMatrix(q)) {
            return false;
        }

        return true;
    }

    private boolean ghostAllowedToMoveDown() {
        for (int i = 0; i < ghost.getTetramino().length; i++) {
            if (ghost.getTetramino()[i].getY() > 360) {
                return false;
            }
        }

        //qui controllo che nel prossimo movimento non tocchi altri pezzi
        Quadrato[] q = new Quadrato[4];
        for (int i = 0; i < 4; i++) {
            q[i] = new Quadrato(ghost.getTetramino()[i]);
        }
        for (int i = 0; i < 4; i++) {
            q[i].setY(q[i].getY() + 20);
        }
        if (intersectOnMatrix(q)) {
            return false;
        }

        return true;
    }

    private boolean allowedToRotate() {
        int tmpX = 0;
        int tmpY = 0;
        int tmp_x_centre = t.getXCentre();
        int tmp_y_centre = t.getYCentre();
        Quadrato[] tmp = new Quadrato[4]; //questo sarà l'array su cui simulo la rotazione
        //copio le coordinate
        for (int i = 0; i < tmp.length; i++) {
            Quadrato q = new Quadrato(0, 0, null);
            tmp[i] = q;
            tmp[i].setX(t.getTetramino()[i].getX());
            tmp[i].setY(t.getTetramino()[i].getY());
        }

        //sposto tutti i blocchi all'origine
        for (int i = 0; i < tmp.length; i++) {
            tmp[i].setX(tmp[i].getX() - t.getXCentre());
            tmp[i].setY(tmp[i].getY() - t.getYCentre());
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //ruoto tutti i blocchi
        for (int i = 0; i < tmp.length; i++) {
            tmpX = tmp[i].getX();
            tmpY = tmp[i].getY();
            tmp[i].setX(tmpY * (-1));
            tmp[i].setY(tmpX);
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //riposiziono tutti i blocchi
        for (int i = 0; i < tmp.length; i++) {
            tmp[i].setX(tmp[i].getX() + tmp_x_centre);
            tmp[i].setY(tmp[i].getY() + tmp_y_centre);
        }

        tmp_x_centre = tmp[2].getX();
        tmp_y_centre = tmp[2].getY();

        //Ora ho completato la simulazione della rotazione, devo quindi verificare i vincoli con le pareti
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].getX() < 0) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il muro sinistro

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].getX() > 180) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il muro destro

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].getY() > 380) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il fondo

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].getY() < 0) {
                return false;
            }
        }//verifico che nessuno dei blocchi oltrepassi il tetto

        for (int i = 0; i < 4; i++) {
            int r = tmp[i].getY() / 20;
            int c = tmp[i].getX() / 20;
            if (tetraMatrix[r][c].isVisible()) {
                return false;
            }
        }//verifico che nessuno dei blocchi intersechi la matrice

        return true;
    }//allowedToRotate

    private boolean attemptToRotateCCW() {
        int tmpX = 0;
        int tmpY = 0;
        int tmp_x_centre = t.getXCentre();
        int tmp_y_centre = t.getYCentre();
        Quadrato[] tmp = new Quadrato[4]; //questo sarà l'array su cui simulo la rotazione
        //copio le coordinate
        for (int i = 0; i < tmp.length; i++) {
            Quadrato q = new Quadrato(0, 0, null);
            tmp[i] = q;
            tmp[i].setX(t.getTetramino()[i].getX());
            tmp[i].setY(t.getTetramino()[i].getY());
        }

        //sposto tutti i blocchi all'origine
        for (int i = 0; i < tmp.length; i++) {
            tmp[i].setX(tmp[i].getX() - t.getXCentre());
            tmp[i].setY(tmp[i].getY() - t.getYCentre());
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //ruoto tutti i blocchi
        for (int i = 0; i < tmp.length; i++) {
            tmpX = tmp[i].getX();
            tmpY = tmp[i].getY();
            tmp[i].setX(tmpY * (-1));
            tmp[i].setY(tmpX);
            //System.out.println("blocco:"+i+" x:"+getTetramino()[i].getX()+" y:"+getTetramino()[i].getY());
        }

        //riposiziono tutti i blocchi
        for (int i = 0; i < tmp.length; i++) {
            tmp[i].setX(tmp[i].getX() + tmp_x_centre);
            tmp[i].setY(tmp[i].getY() + tmp_y_centre);
        }

        tmp_x_centre = tmp[2].getX();
        tmp_y_centre = tmp[2].getY();

        if (intersectOnMatrix(tmp)) {
            return false;
        }

        if (outFromTheRight(tmp)) {
            return attemptToMoveFromTheRight(tmp);
        } else if (outFromTheLeft(tmp)) {
            return attemptToMoveFromTheLeft(tmp);
        } else if (outFromTheTop(tmp)) {
            return attemptToMoveFromTheTop(tmp);
        }
        return false;
    }

    private boolean attemptToMoveFromTheLeft(Quadrato[] tmp) {

        //finche' e' fuori dalla sinistra provo a muoverlo verso destra
        while (outFromTheLeft(tmp)) {
            for (int i = 0; i < 4; i++) {
                tmp[i].setX(tmp[i].getX() + 20);
            }
            if (intersectOnMatrix(tmp)) {
                return false;
            }
        }

        t.rotateCCW();

        //ora lo sposto del necessario a farlo stare dentro, 
        //non verifico più l'intersezione con pezzi gia' presenti visto che l'ho gia' fatto
        while (outFromTheLeft(t.getTetramino())) {
            t.moveRight();
        }

        return true;
    }

    private boolean attemptToMoveFromTheRight(Quadrato[] tmp) {

        //finche' e' fuori dalla destra provo a muoverlo verso destra
        while (outFromTheRight(tmp)) {
            for (int i = 0; i < 4; i++) {
                tmp[i].setX(tmp[i].getX() - 20);
            }
            if (intersectOnMatrix(tmp)) {
                return false;
            }
        }

        t.rotateCCW();

        //ora lo sposto del necessario a farlo stare dentro, 
        //non verifico più l'intersezione con pezzi gia' presenti visto che l'ho gia' fatto
        while (outFromTheRight(t.getTetramino())) {
            t.moveLeft();
        }

        return true;
    }

    private boolean attemptToMoveFromTheTop(Quadrato[] tmp) {

        //finche' e' fuori dalla cima provo a muoverlo verso destra
        while (outFromTheTop(tmp)) {
            for (int i = 0; i < 4; i++) {
                tmp[i].setY(tmp[i].getY() + 20);
            }
            if (intersectOnMatrix(tmp)) {
                return false;
            }
        }

        t.rotateCCW();

        //ora lo sposto del necessario a farlo stare dentro, 
        //non verifico più l'intersezione con pezzi gia' presenti visto che l'ho gia' fatto
        while (outFromTheTop(t.getTetramino())) {
            t.move();
        }

        return true;
    }

    private boolean outFromTheLeft(Quadrato[] q) {
        for (int i = 0; i < 4; i++) {
            if (q[i].getX() < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean outFromTheRight(Quadrato[] q) {
        for (int i = 0; i < 4; i++) {
            if (q[i].getX() > 180) {
                return true;
            }
        }
        return false;
    }

    private boolean outFromTheTop(Quadrato[] q) {
        for (int i = 0; i < 4; i++) {
            if (q[i].getY() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * stampa a schermo la matrice, il metodo serve a vedere e capire eventuali
     * bugs.
     */
    public void printTetraMatrix() {
        System.out.println();
        for (int i = 0; i < tetraMatrix.length; i++) {
            for (int j = 0; j < tetraMatrix[i].length; j++) {
                System.out.print(tetraMatrix[i][j].isVisible() + " ");
                if (j == tetraMatrix[i].length - 1) {
                    System.out.println();
                }
            }
        }
    }
}