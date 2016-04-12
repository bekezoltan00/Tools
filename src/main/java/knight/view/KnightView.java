package knight.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import knight.model.KnightModel;

/**
 *
 * @author bekezoltan00
 */
public class KnightView extends JFrame {

	private JButton[][] buttons = new JButton[5][5];

	public KnightView() {
		setLocationRelativeTo(null);
		setTitle("Knight");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
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

		new KnightModel(getButtons());

		add(panel);
	}

	JButton[][] getButtons() {
		return buttons;
	}
}
