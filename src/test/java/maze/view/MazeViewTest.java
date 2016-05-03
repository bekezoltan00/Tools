package maze.view;

import static org.junit.Assert.*;
import java.awt.Color;

public class MazeViewTest {
	@org.junit.Test
	public void setCorrectWallColor() {
		MazeView maze = new MazeView();
		assertEquals(maze.getWallColor(),Color.red);
	}
	
	@org.junit.Test (expected=ArrayIndexOutOfBoundsException.class)
	public void catchArrayIndexOutOfBoundsException() {
		int size=13;
		MazeView maze = new MazeView();
		assertEquals(maze.getButtons()[size].length,size);
	}
}
