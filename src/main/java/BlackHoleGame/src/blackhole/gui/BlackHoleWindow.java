package blackhole.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import blackhole.logic.BlackHoleLogic;

public class BlackHoleWindow extends JFrame {

    BlackHoleLogic logic;
    private final JPanel gamePanel;
    private JPopupMenu popupMenu;
    private final String[] directions = {"Right", "Left", "Up", "Down"};
    private MenuAction menuAction;
    private ImageIcon imgPlayer1;
    private ImageIcon imgPlayer2;
    private Component[] components;

    public BlackHoleWindow() {

        super("BlackHole");

        this.logic = new BlackHoleLogic(5);
        setLocation(50, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        gamePanel = new JPanel();
        add(gamePanel, BorderLayout.CENTER);

        BufferedImage bufferimgPlayer2;
        BufferedImage bufferimgPlayer1;

        try {
            bufferimgPlayer1 = ImageIO.read(BlackHoleWindow.class.getResourceAsStream("blueSpaceShip.jpg"));
            bufferimgPlayer2 = ImageIO.read(BlackHoleWindow.class.getResourceAsStream("redSpaceShip.jpg"));
            imgPlayer1 = new ImageIcon(bufferimgPlayer1);
            imgPlayer2 = new ImageIcon(bufferimgPlayer2);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(gamePanel, ex.fillInStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        newGame();
    }

    public final void newGame() {
        gamePanel.removeAll();
        gamePanel.setLayout(new GridLayout(logic.getN(), logic.getN()));
        logic.newGame();
        for (int i = 0; i < logic.getN(); i++) {            //gombok tulajdonságainak beallitasa
            for (int j = 0; j < logic.getN(); j++) {
                GameButton jButton = makeGameButton(j, i);
                gamePanel.add(jButton);
            }
        }
        menuAction = new MenuAction(this);
        popupMenu = new JPopupMenu();                       //popupmenu
        for (int i = 0; i < 4; ++i) {
            popupMenu.add(makePopupMenuItem(i));
        }
        components = popupMenu.getComponents();

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("New Game");//menu
        for (int i = 5; i <= 9; i += 2) {
            menu.add(makeMenuItem(i));
            menu.addSeparator();
        }
        menubar.add(menu);                                  //menu hozzáadása
        setJMenuBar(menubar);
        pack();
    }

    private GameButton makeGameButton(int j, int i) {//játéktér mezőinek a beállítása
        GameButton jButton = new GameButton(j, i);
        jButton.setPreferredSize(new Dimension(77, 77));
        jButton.setBackground(Color.WHITE);
        coloring(i, j, jButton);
        jButton.addActionListener(clickAction);
        return jButton;
    }

    private JMenuItem makePopupMenuItem(int i) {     //popuomenu tulajdonságainak beállítása
        JMenuItem item = new JMenuItem();
        item.setName(i + "");
        item.setAction(menuAction);
        item.setText(directions[i]);
        return item;
    }

    private JMenuItem makeMenuItem(int i) {          //menuelemek létrehozása
        JMenuItem item = new JMenuItem(newGameaction);                              //menuitem neve "hordozza" az információt,
        item.setName(i + "");                                                       // hogy mekkora legyen a pálya
        item.setText(i + " × " + i + " board");
        return item;
    }
    
    private final AbstractAction clickAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameButton gb = (GameButton) e.getSource();
            int x = gb.getPosX();
            int y = gb.getPosY();
            if (logic.playerTurn(y, x)) {                                           //csak az éppen soronkövetkező játékos  
                menuAction.setPosition(x, y);                                       // űrhajóinál jön elő a popupmenu
                for (int i = 0; i < 4; i++) {
                    components[i].setEnabled(true);
                }
                for (Integer integer : logic.passableWays(y, x)) {                  //azokba az irányokba, amellyekbe nem mehet az űrhajó ott a
                    components[integer].setEnabled(false);                          //popupmenu megfelelő eleme(i) "kikapcsolásra" kerül(nek)
                }
                popupMenu.show(gb, x, y);
            }
        }
    };
    AbstractAction newGameaction = new AbstractAction() {//új játék létrehozása, a pálya megfelelő méretének a beállítása
        @Override
        public void actionPerformed(ActionEvent e) {
            logic = new BlackHoleLogic(Integer.parseInt(((JMenuItem) e.getSource()).getName()));
            newGame();
        }
    };

    public void changeColor() {
        for (Component component : gamePanel.getComponents()) {
            GameButton gb = (GameButton) component;
            int x = gb.getPosX();
            int y = gb.getPosY();
            coloring(y, x, gb);
        }
    }

    public void coloring(int i, int j, GameButton button) {

        switch (logic.getfield(i, j)) {
            case 0:
                button.setIcon(null);
                //button.setBackground(Color.WHITE);
                break;
            case 1:
                //button.setBackground(Color.BLUE);
                button.setIcon(imgPlayer1);
                break;
            case 2:
                //button.setBackground(Color.RED);
                button.setIcon(imgPlayer2);
                break;
            case 3:
                button.setBackground(Color.BLACK);
                break;
        }
    }
    
}
