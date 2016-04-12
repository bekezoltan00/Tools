package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import motor.Motor;

public class Main extends JFrame{
	
	public Main() {
		
		JPanel panel = new JPanel() {};
		
		setTitle("Projekt eszközök beadandó");
        setSize(480, 270);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JButton button1 = new JButton("Fénymotor Párbaj");
        JButton button2 = new JButton("24x24");
        JButton button3 = new JButton("36x36");
        JButton button4 = new JButton("4");
        
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
					System.out.println("An error occured while starting Motor class!");
				}
			}
		};
		
		button1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new Thread(motorRun).start();
			}
		});
        
	}

	public static void main (String[] args) {
		
		Main main = new Main();
		main.setVisible(true);
	}
	
}
