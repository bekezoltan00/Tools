package knight.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import knight.model.KnightModel;

/**
 *
 * @author bekezoltan00
 */
public class KnightView extends JFrame {

	private KnightModel km;
	private JButton[][] buttons = new JButton[5][5];
	private JPanel panel;

	public KnightView() {
		setTitle("Knight");
		setSize(400, 400);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5, 1, 1));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				JButton button = new JButton();
				button.setBackground(Color.white);
				button.setVisible(true);
				getButtons()[i][j] = button;
				panel.add(button);
			}
		}

		JMenuBar menubar = new JMenuBar();
        JMenu newgame = new JMenu("File");
        menubar.add(newgame);

        JMenuItem restart = new JMenuItem("Restart");

        newgame.add(restart);
        
        restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				km = new KnightModel(getButtons());
			}
		});

        setJMenuBar(menubar);
		
		km = new KnightModel(getButtons());

		add(panel);
	}

	JButton[][] getButtons() {
		return buttons;
	}
	
	public void reset() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				buttons[i][j].setBackground(Color.WHITE);
			}
		}
	}
}
