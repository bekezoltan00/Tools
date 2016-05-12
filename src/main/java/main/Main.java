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
import labirintus.LabirintusGame;
import motor.Motor;
import colorgame.ColorGame;
import lightmotors.Lightmotors;
import maze.MazeDemo;

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
        JButton button3 = new JButton("Labyrinth");
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button4 = new JButton("4");
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button5 = new JButton("MazeRunner");
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button6 = new JButton("ColorGame");
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton button7 = new JButton("Lightmotor");
        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        
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
		
		final Runnable labyrinthRun = new Runnable() {
			public void run() {
				LabirintusGame.main(new String[0]);
			}
		};
		
		final Runnable mazeRun = new Runnable() {
			public void run() {
				MazeDemo.main(new String[0]);
			}
		};
		
		final Runnable colorGameRun = new Runnable() {
			public void run() {
				ColorGame.main(new String[0]);
			}
		};
		
		final Runnable lightMotorsRun = new Runnable() {
			public void run() {
				Lightmotors.main(new String[0]);
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
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(labyrinthRun).start();
			}
		});
		
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(mazeRun).start();
			}
		});
		
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(colorGameRun).start();
			}
		});
		
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(lightMotorsRun).start();
			}
		});
        
	}

	public static void main (String[] args) {
		Main main = new Main();
		main.setVisible(true);
	}
	
}
