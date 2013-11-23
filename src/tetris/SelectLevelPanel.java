/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author antonioruffolo
 */
public class SelectLevelPanel extends JPanel {
    private Image backgroundImage, zeroStandard, zeroHighLighted, zeroInverted,
            oneStandard, oneHighLighted, oneInverted, twoStandard, twoHighLighted,
            twoInverted, threeStandard, threeHighLighted, threeInverted, fourStandard,
            fourHighLighted, fourInverted, fiveStandard, fiveHighLighted, fiveInverted,
            sixStandard, sixHighLighted, sixInverted, sevenStandard, sevenHighLighted,
            sevenInverted, eightStandard, eightHighLighted, eightInverted, nineStandard,
            nineHighLighted, nineInverted, startImageStandard, startImageHighLighted,
            startImageInverted;
    private BufferedImage startBufferedImageStandard, startBufferedImageHighLighted, startBufferedImageInverted;
    
    private TetraRadioButton zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
                             sixButton, sevenButton, eightButton, nineButton;
    private TetraButton  startButton;
    private int level;
    private TetrisPanelsController tetrisPanelsController;
    
    public SelectLevelPanel(){
        setSize(800,600);
        setDoubleBuffered(true);
        
        zeroButton= new TetraRadioButton();
        zeroButton.addMouseListener(new MouseAdapter());
        oneButton= new TetraRadioButton();
        oneButton.addMouseListener(new MouseAdapter());
        twoButton= new TetraRadioButton();
        twoButton.addMouseListener(new MouseAdapter());
        threeButton= new TetraRadioButton();
        threeButton.addMouseListener(new MouseAdapter());
        fourButton= new TetraRadioButton();
        fourButton.addMouseListener(new MouseAdapter());
        fiveButton= new TetraRadioButton();
        fiveButton.addMouseListener(new MouseAdapter());
        sixButton= new TetraRadioButton();
        sixButton.addMouseListener(new MouseAdapter());
        sevenButton= new TetraRadioButton();
        sevenButton.addMouseListener(new MouseAdapter());
        eightButton= new TetraRadioButton();
        eightButton.addMouseListener(new MouseAdapter());
        nineButton= new TetraRadioButton();
        nineButton.addMouseListener(new MouseAdapter());
        
        startButton= new TetraButton();
        startButton.addMouseListener(new MouseAdapter());
        
        init();
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c= new GridBagConstraints();
        
        PanelForLevelButtons p= new PanelForLevelButtons();
        c.anchor= GridBagConstraints.PAGE_START;
        c.gridx=2;
        c.gridy=0;
        c.insets.top=50;
        c.insets.left=10;
        c.insets.bottom= 30;
        layout.setConstraints(p, c);
        this.add(p);
        
        c.anchor= GridBagConstraints.PAGE_END;
        c.gridx=2;
        c.gridy=5;
        c.insets.top=100;
        c.insets.bottom=80;
        c.insets.left=30;
        
        layout.setConstraints(startButton, c);
        this.add(startButton);
        
        
        
    }//constructor
    
    public SelectLevelPanel(TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController=tetrisPanelsController;
        
        setSize(800,600);
        setDoubleBuffered(true);
        
        zeroButton= new TetraRadioButton();
        zeroButton.addMouseListener(new MouseAdapter());
        oneButton= new TetraRadioButton();
        oneButton.addMouseListener(new MouseAdapter());
        twoButton= new TetraRadioButton();
        twoButton.addMouseListener(new MouseAdapter());
        threeButton= new TetraRadioButton();
        threeButton.addMouseListener(new MouseAdapter());
        fourButton= new TetraRadioButton();
        fourButton.addMouseListener(new MouseAdapter());
        fiveButton= new TetraRadioButton();
        fiveButton.addMouseListener(new MouseAdapter());
        sixButton= new TetraRadioButton();
        sixButton.addMouseListener(new MouseAdapter());
        sevenButton= new TetraRadioButton();
        sevenButton.addMouseListener(new MouseAdapter());
        eightButton= new TetraRadioButton();
        eightButton.addMouseListener(new MouseAdapter());
        nineButton= new TetraRadioButton();
        nineButton.addMouseListener(new MouseAdapter());
        
        startButton= new TetraButton();
        startButton.setActionCommand("start");
        startButton.addActionListener(this.tetrisPanelsController);
        startButton.addMouseListener(new MouseAdapter());
        
        init();
        GridBagLayout layout= new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c= new GridBagConstraints();
        
        PanelForLevelButtons p= new PanelForLevelButtons();
        c.anchor= GridBagConstraints.PAGE_START;
        c.gridx=2;
        c.gridy=0;
        c.insets.top=50;
        c.insets.left=10;
        c.insets.bottom= 30;
        layout.setConstraints(p, c);
        this.add(p);
        
        c.anchor= GridBagConstraints.PAGE_END;
        c.gridx=2;
        c.gridy=5;
        c.insets.top=100;
        c.insets.bottom=80;
        c.insets.left=30;
        
        layout.setConstraints(startButton, c);
        this.add(startButton);
        
        
        
    }//constructor
    
    private void init(){
        backgroundImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/SelectLevelBackground.png"));
        
        startImageStandard=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/StartButton.png"));
        startImageHighLighted=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/StartButtonHighLighted.png"));
        startImageInverted=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/StartButtonInverted.png"));
        
        /*try {  
  
            startBufferedImageStandard = ImageIO.read(new File(this.getClass().getResource("/tetris/images/selectLevelImages/StartButton.png")));  
        } catch (Exception e) {  
            
        }*/
        startButton.setBufferedImage(startBufferedImageStandard);
        
        zeroStandard=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/0.png"));
        zeroHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/0HighLighted.png"));
        zeroInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/0Inverted.png"));
        
        oneStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/1.png"));
        oneHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/1HighLighted.png"));
        oneInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/1Inverted.png"));
        
        twoStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/2.png"));
        twoHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/2HighLighted.png"));
        twoInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/2Inverted.png"));
        
        threeStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/3.png"));
        threeHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/3HighLighted.png"));
        threeInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/3Inverted.png"));
        
        fourStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/4.png"));
        fourHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/4HighLighted.png"));
        fourInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/4Inverted.png"));
        
        fiveStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/5.png"));
        fiveHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/5HighLighted.png"));
        fiveInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/5Inverted.png"));
        
        sixStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/6.png"));
        sixHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/6HighLighted.png"));
        sixInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/6Inverted.png"));
        
        sevenStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/7.png"));
        sevenHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/7HighLighted.png"));
        sevenInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/7Inverted.png"));
        
        eightStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/8.png"));
        eightHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/8HighLighted.png"));
        eightInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/8Inverted.png"));
    
        nineStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/9.png"));
        nineHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/9HighLighted.png"));
        nineInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/selectLevelImages/9Inverted.png"));
    
        zeroButton.getImageArray()[0]= new ImageIcon(zeroStandard);
        zeroButton.getImageArray()[1]= new ImageIcon(zeroHighLighted);
        zeroButton.getImageArray()[2]= new ImageIcon(zeroInverted);
        zeroButton.initImage();
        zeroButton.setIsActivated(true);
        zeroButton.setIcon(zeroButton.getImageArray()[2]);
        
        oneButton.getImageArray()[0]= new ImageIcon(oneStandard);
        oneButton.getImageArray()[1]= new ImageIcon(oneHighLighted);
        oneButton.getImageArray()[2]= new ImageIcon(oneInverted);
        oneButton.initImage();
        
        twoButton.getImageArray()[0]= new ImageIcon(twoStandard);
        twoButton.getImageArray()[1]= new ImageIcon(twoHighLighted);
        twoButton.getImageArray()[2]= new ImageIcon(twoInverted);
        twoButton.initImage();
        
        threeButton.getImageArray()[0]= new ImageIcon(threeStandard);
        threeButton.getImageArray()[1]= new ImageIcon(threeHighLighted);
        threeButton.getImageArray()[2]= new ImageIcon(threeInverted);
        threeButton.initImage();
        
        fourButton.getImageArray()[0]= new ImageIcon(fourStandard);
        fourButton.getImageArray()[1]= new ImageIcon(fourHighLighted);
        fourButton.getImageArray()[2]= new ImageIcon(fourInverted);
        fourButton.initImage();
        
        fiveButton.getImageArray()[0]= new ImageIcon(fiveStandard);
        fiveButton.getImageArray()[1]= new ImageIcon(fiveHighLighted);
        fiveButton.getImageArray()[2]= new ImageIcon(fiveInverted);
        fiveButton.initImage();
        
        sixButton.getImageArray()[0]= new ImageIcon(sixStandard);
        sixButton.getImageArray()[1]= new ImageIcon(sixHighLighted);
        sixButton.getImageArray()[2]= new ImageIcon(sixInverted);
        sixButton.initImage();
        
        sevenButton.getImageArray()[0]= new ImageIcon(sevenStandard);
        sevenButton.getImageArray()[1]= new ImageIcon(sevenHighLighted);
        sevenButton.getImageArray()[2]= new ImageIcon(sevenInverted);
        sevenButton.initImage();
        
        eightButton.getImageArray()[0]= new ImageIcon(eightStandard);
        eightButton.getImageArray()[1]= new ImageIcon(eightHighLighted);
        eightButton.getImageArray()[2]= new ImageIcon(eightInverted);
        eightButton.initImage();
        
        nineButton.getImageArray()[0]= new ImageIcon(nineStandard);
        nineButton.getImageArray()[1]= new ImageIcon(nineHighLighted);
        nineButton.getImageArray()[2]= new ImageIcon(nineInverted);
        nineButton.initImage();
        
        startButton.getImageArray()[0]= new ImageIcon(startImageStandard);
        startButton.getImageArray()[1]= new ImageIcon(startImageHighLighted);
        startButton.getImageArray()[2]= new ImageIcon(startImageInverted);
        startButton.initImage();
    }
    
    public int getLevel(){
        return level;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            if (me.getSource()== zeroButton){
                zeroButton.mouseClicked(me);
                zeroButton.setIsActivated(true);
                level=0;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== oneButton){
                oneButton.mouseClicked(me);
                oneButton.setIsActivated(true);
                level=1;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== twoButton){
                twoButton.mouseClicked(me);
                twoButton.setIsActivated(true);
                level=2;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== threeButton){
                threeButton.mouseClicked(me);
                threeButton.setIsActivated(true);
                level=3;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== fourButton){
                fourButton.mouseClicked(me);
                fourButton.setIsActivated(true);
                level=4;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== fiveButton){
                fiveButton.mouseClicked(me);
                fiveButton.setIsActivated(true);
                level=5;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== sixButton){
                sixButton.mouseClicked(me);
                sixButton.setIsActivated(true);
                level=6;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== sevenButton){
                sevenButton.mouseClicked(me);
                sevenButton.setIsActivated(true);
                level=7;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== eightButton){
                eightButton.mouseClicked(me);
                eightButton.setIsActivated(true);
                level=8;
                setOthersButtonsImage(me);
            }
            else if (me.getSource()== nineButton){
                nineButton.mouseClicked(me);
                nineButton.setIsActivated(true);
                level=9;
                setOthersButtonsImage(me);
            }
        }//mouseClicked

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()== zeroButton){
                zeroButton.mousePressed(me);
            }
            else if (me.getSource()== oneButton){
                oneButton.mousePressed(me);
            }
            else if (me.getSource()== twoButton){
                twoButton.mousePressed(me);
            }
            else if (me.getSource()== threeButton){
                threeButton.mousePressed(me);
            }
            else if (me.getSource()== fourButton){
                fourButton.mousePressed(me);
            }
            else if (me.getSource()== fiveButton){
                fiveButton.mousePressed(me);
            }
            else if (me.getSource()== sixButton){
                sixButton.mousePressed(me);
            }
            else if (me.getSource()== sevenButton){
                sevenButton.mousePressed(me);
            }
            else if (me.getSource()== eightButton){
                eightButton.mousePressed(me);
            }
            else if (me.getSource()== nineButton){
                nineButton.mousePressed(me);
            }
            else if (me.getSource()== startButton){
                startButton.mousePressed(me);
            }
        }//MousePressed

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()== zeroButton){
                zeroButton.mouseReleased(me);
            }
            else if (me.getSource()== oneButton){
                oneButton.mouseReleased(me);
            }
            else if (me.getSource()== twoButton){
                twoButton.mouseReleased(me);
            }
            else if (me.getSource()== threeButton){
                threeButton.mouseReleased(me);
            }
            else if (me.getSource()== fourButton){
                fourButton.mouseReleased(me);
            }
            else if (me.getSource()== fiveButton){
                fiveButton.mouseReleased(me);
            }
            else if (me.getSource()== sixButton){
                sixButton.mouseReleased(me);
            }
            else if (me.getSource()== sevenButton){
                sevenButton.mouseReleased(me);
            }
            else if (me.getSource()== eightButton){
                eightButton.mouseReleased(me);
            }
            else if (me.getSource()== nineButton){
                nineButton.mouseReleased(me);
            }
            else if (me.getSource()== startButton){
                startButton.mouseReleased(me);
            }
        }//mouseReleased

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()== zeroButton){
                zeroButton.mouseEntered(me);
            }
            else if (me.getSource()== oneButton){
                oneButton.mouseEntered(me);
            }
            else if (me.getSource()== twoButton){
                twoButton.mouseEntered(me);
            }
            else if (me.getSource()== threeButton){
                threeButton.mouseEntered(me);
            }
            else if (me.getSource()== fourButton){
                fourButton.mouseEntered(me);
            }
            else if (me.getSource()== fiveButton){
                fiveButton.mouseEntered(me);
            }
            else if (me.getSource()== sixButton){
                sixButton.mouseEntered(me);
            }
            else if (me.getSource()== sevenButton){
                sevenButton.mouseEntered(me);
            }
            else if (me.getSource()== eightButton){
                eightButton.mouseEntered(me);
            }
            else if (me.getSource()== nineButton){
                nineButton.mouseEntered(me);
            }
            else if (me.getSource()== startButton){
                startButton.mouseEntered(me);
            }
            
        }//mouseEntered

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()== zeroButton){
                zeroButton.mouseExited(me);
            }
            else if (me.getSource()== oneButton){
                oneButton.mouseExited(me);
            }
            else if (me.getSource()== twoButton){
                twoButton.mouseExited(me);
            }
            else if (me.getSource()== threeButton){
                threeButton.mouseExited(me);
            }
            else if (me.getSource()== fourButton){
                fourButton.mouseExited(me);
            }
            else if (me.getSource()== fiveButton){
                fiveButton.mouseExited(me);
            }
            else if (me.getSource()== sixButton){
                sixButton.mouseExited(me);
            }
            else if (me.getSource()== sevenButton){
                sevenButton.mouseExited(me);
            }
            else if (me.getSource()== eightButton){
                eightButton.mouseExited(me);
            }
            else if (me.getSource()== nineButton){
                nineButton.mouseExited(me);
            }
            else if (me.getSource()== startButton){
                startButton.mouseExited(me);
            }
            
            
        }//mouseExited
        
        private void setOthersButtonsImage(MouseEvent me){
            if (me.getSource() != zeroButton){
                zeroButton.setIcon(zeroButton.getImageArray()[0]);
                zeroButton.setIsActivated(false);
            }
            if (me.getSource()!= oneButton){
                oneButton.setIcon(oneButton.getImageArray()[0]);
                oneButton.setIsActivated(false);
            }
            if (me.getSource()!= twoButton){
                twoButton.setIcon(twoButton.getImageArray()[0]);
                twoButton.setIsActivated(false);
            }
            if (me.getSource() != threeButton){
                threeButton.setIcon(threeButton.getImageArray()[0]);
                threeButton.setIsActivated(false);
            }
            if (me.getSource() != fourButton){
               fourButton.setIcon(fourButton.getImageArray()[0]);
               fourButton.setIsActivated(false);
            }
            if (me.getSource() != fiveButton){
                fiveButton.setIcon(fiveButton.getImageArray()[0]);
                fiveButton.setIsActivated(false);
            }
            if (me.getSource() != sixButton){
                sixButton.setIcon(sixButton.getImageArray()[0]);
                sixButton.setIsActivated(false);
            }
            if (me.getSource() != sevenButton){
                sevenButton.setIcon(sevenButton.getImageArray()[0]);
                sevenButton.setIsActivated(false);
            }
            if (me.getSource() != eightButton){
                eightButton.setIcon(eightButton.getImageArray()[0]);
                eightButton.setIsActivated(false);
            }
            if (me.getSource() != nineButton){
                nineButton.setIcon(nineButton.getImageArray()[0]);
                nineButton.setIsActivated(false);
            }
        }//setOthersButtonImage
        
    }//MouseAdapter
    
    private class PanelForLevelButtons extends JPanel{
        public PanelForLevelButtons(){
            setSize(205, 82);
            GridLayout layout= new GridLayout(2, 5);
            layout.setHgap(0);
            layout.setVgap(0);
            PanelForLevelButtons.this.setLayout(layout);
            Insets buttons = new Insets(0,0,0,0);
            if (System.getProperty("os.name").startsWith("Windows")){
                zeroButton.setBorder(BorderFactory.createEmptyBorder());
                oneButton.setBorder(BorderFactory.createEmptyBorder());
                twoButton.setBorder(BorderFactory.createEmptyBorder());
                threeButton.setBorder(BorderFactory.createEmptyBorder());
                fourButton.setBorder(BorderFactory.createEmptyBorder());
                fiveButton.setBorder(BorderFactory.createEmptyBorder());
                sixButton.setBorder(BorderFactory.createEmptyBorder());
                sevenButton.setBorder(BorderFactory.createEmptyBorder());
                eightButton.setBorder(BorderFactory.createEmptyBorder());
                nineButton.setBorder(BorderFactory.createEmptyBorder());
            }
            zeroButton.setMargin(buttons);
            twoButton.setMargin(buttons);
            threeButton.setMargin(buttons);
            fourButton.setMargin(buttons);
            fiveButton.setMargin(buttons);
            sixButton.setMargin(buttons);
            sevenButton.setMargin(buttons);
            eightButton.setMargin(buttons);
            nineButton.setMargin(buttons);
            PanelForLevelButtons.this.add(zeroButton);
            PanelForLevelButtons.this.add(oneButton);
            PanelForLevelButtons.this.add(twoButton);
            PanelForLevelButtons.this.add(threeButton);
            PanelForLevelButtons.this.add(fourButton);
            PanelForLevelButtons.this.add(fiveButton);
            PanelForLevelButtons.this.add(sixButton);
            PanelForLevelButtons.this.add(sevenButton);
            PanelForLevelButtons.this.add(eightButton);
            PanelForLevelButtons.this.add(nineButton);
            
            this.setBackground(new Color(0,0,0,0));
            this.setMaximumSize(new Dimension(205,82));
        }//constructor
    }//Panel for level buttons
}
