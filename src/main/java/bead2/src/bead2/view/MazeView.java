package bead2.view;

import bead2.model.MazeModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

/**
 *
 * @author shepy
 */
public class MazeView extends JFrame implements KeyListener{
    private int currentX;
    private int currentY;
    private int endX;
    private int endY;
    private JPanel map;
    private JPanel menu;
    private JButton reset;
    private ComboBoxModel<Integer> sizeModel;
    private static int SIZE = 13;
    private JButton[][] buttons;
    private MazeModel model;
    
    public MazeView(){       
        currentX = SIZE-1;
        currentY = 0;
        endX = 0;
        endY = SIZE-1;
        
        setTitle("Labirintus játék");
        setLayout(new BorderLayout());
        setSize(650,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menu = new JPanel();
        menu.setSize(new Dimension(650,40));
        menu.setBackground(Color.white);
        menu.setLayout(new FlowLayout());
        setupMenu();

        map = new JPanel();
        map.setLayout(new GridLayout(SIZE,SIZE));      
        setupMap();
    }
    
    public void setupMenu(){
        reset = new JButton();
        reset.setPreferredSize(new Dimension(100,30));
        reset.setName("új");
        reset.setText("Új játék");
        reset.setBackground(Color.white);
        reset.addActionListener(ResbuttonListener);
        menu.add(reset);
        
        JComboBox<Integer> combobox = new JComboBox<>();
        combobox.setActionCommand("Combobox");
        combobox.addItem(13);
        combobox.addItem(17);
        combobox.addItem(21);
        menu.add(combobox);
        combobox.addActionListener(ComboBoxListener);
        add(menu, BorderLayout.PAGE_START);
    }
    
    public void setupMap(){
        if(map == null){
            map = new JPanel();
        }else{
            map.removeAll();
            map.setVisible(false);
        }
        map.setVisible(true);
        model = new MazeModel(SIZE, Color.red, Color.white, Color.black);
        buttons = new JButton[SIZE][SIZE];
        currentX = SIZE-1;
        currentY = 0;
        endX = 0;
        endY = SIZE-1;
        map.setLayout(new GridLayout(SIZE,SIZE));
        
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(30,30));
                buttons[i][j] = button;
                button.addKeyListener(this);
                button.setBackground(Color.black);
                map.add(button);
            }
        }
        add(map, BorderLayout.CENTER);
        map.revalidate();
        refreshButtons();
    }
    
    private ActionListener ResbuttonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent action) {
            JButton b = (JButton)action.getSource();
            setupMap();
        }
    };
    
    private ActionListener ComboBoxListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent action) {
            SIZE = (Integer)((JComboBox)action.getSource()).getSelectedItem();
            setupMap();
        }
    };
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT){
            if(currentY > 0 && model.isWall(currentX, currentY-1) == false){
                if ( currentY % SIZE != 0 );
                    currentY--;
            }   
        }else if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
            if(currentY+1 < SIZE && model.isWall(currentX, currentY+1) == false){
                if (currentY % SIZE != SIZE-1){
                   currentY++;
                }
            }
        }else if (ke.getKeyCode() == KeyEvent.VK_DOWN){
            if(currentX + 1 < SIZE && model.isWall(currentX+1, currentY) == false){
                    currentX += 1;
            }
        }else if (ke.getKeyCode() == KeyEvent.VK_UP){
            if(currentX - 1 >= 0 && model.isWall(currentX-1, currentY) == false){
                    currentX -= 1;
            }
        }
        refreshButtons();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    private void refreshButtons(){
        model.setVisibility(currentX, currentY); 
        for(int i=0; i<SIZE; ++i) {
            for(int j=0; j<SIZE; ++j){
                buttons[i][j].setBackground(model.getBg(i, j));
                if(model.isVisible(endX, endY)){
                    buttons[endX][endY].setBackground(Color.blue);
                }
            }
        }
        buttons[currentX][currentY].setBackground(Color.green);
        if(model.isOver(currentX, currentY)){
            congratulate();
        }
    }
    
    private void congratulate() {
        JOptionPane.showMessageDialog(this, "Gratulálok, nyertél!");
        map.removeAll();
    }
}