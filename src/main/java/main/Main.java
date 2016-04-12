package main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import knight.Knight;
import motor.Motor;

public class Main extends JFrame{
	
	public Main() {
		
		JPanel panel = new JPanel() {};
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		setTitle("Project Tools");
        setSize(480, 270);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JButton button1 = new JButton("Tron(ish) game");
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button2 = new JButton("Knight");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button3 = new JButton("36x36");
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button4 = new JButton("4");
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        
        add(panel);
		
		final Runnable motorRun = new Runnable() {
			public void run() {
				try {
					Motor.main(new String[0]);
				} catch (IOException e) {
					System.out.println("An error occured while starting Tron(ish) game!");
				}
			}
		};
		
		final Runnable knightRun = new Runnable() {
			public void run() {
				Knight.main(new String[0]);
			}
		};
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(motorRun).start();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(knightRun).start();
			}
		});
        
	}

	public static void main (String[] args) {
		
		Main main = new Main();
		main.setVisible(true);
	}
	
}
