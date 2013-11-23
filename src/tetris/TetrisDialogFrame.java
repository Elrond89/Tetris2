/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author antonioruffolo
 */
public class TetrisDialogFrame extends JFrame implements ActionListener{
    
    private TetrisPanelsController tetrisPanelsController;
    private String name;
    private JTextField textField;
    private TetraButton okButton;
    
    public TetrisDialogFrame(TetrisPanelsController tetrisPanelsController){
        this.tetrisPanelsController= tetrisPanelsController;
        setSize(400, 200);
        setVisible(true);
    }
    public TetrisDialogFrame(){
        TetrisDialogPanel t= new TetrisDialogPanel();
        this.add(t);
        setSize(400, 200);
        setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        name= textField.getText();
    }
    
    private class TetrisDialogPanel extends JPanel{
        
        private Image background, okButtonStandard, okButtonHighLighted, okButtonInverted;
        private ActionListener actionDialog;
        
        public TetrisDialogPanel(){
            setSize(400, 400);
            textField= new JTextField(20);
            
            okButton= new TetraButton();
            okButton.addActionListener(TetrisDialogFrame.this);
            okButton.addMouseListener(new MouseAdapter());
            okButton.setFocusable(true);
            init();
            
            GridBagLayout layout= new GridBagLayout();
        
            this.setLayout(layout);
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets.top=50;
            layout.setConstraints(textField, c);
            add(textField);
            
            c.gridx = 0;
            c.gridy = 1;
            c.insets.top=0;
            layout.setConstraints(okButton, c);
            add(okButton);
        }
        
        private void init(){
            okButtonStandard= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/dialogImages/OkButtonStandard.png"));
            okButtonHighLighted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/dialogImages/OkButtonHighLighted.png"));
            okButtonInverted= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/tetris/images/dialogImages/OkButtonInverted.png"));
            
            okButton.getImageArray()[0]= new ImageIcon(okButtonStandard);
            okButton.getImageArray()[1]= new ImageIcon(okButtonHighLighted);
            okButton.getImageArray()[2]= new ImageIcon(okButtonInverted);
            okButton.initImage();
        }
        
        
    }//TetrisDialogPanel
    
    
    
    public String getStringName(){
        return name;
    }
    
    private class MouseAdapter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if (me.getSource()==okButton){
                okButton.mousePressed(me);
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (me.getSource()==okButton){
                okButton.mouseReleased(me);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource()==okButton){
                okButton.mouseEntered(me);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if (me.getSource()==okButton){
                okButton.mouseExited(me);
            }
        }
        
    }//MouseAdapter
    
    public static void main (String args[]){
        TetrisDialogFrame t= new TetrisDialogFrame();
    }
}//TetrisDialogFrame
