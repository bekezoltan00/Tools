package maze.model;

import static org.junit.Assert.*;

import java.awt.Color;

public class MazeModelTest {
	@org.junit.Test
	public void isOverIsTrueOrFalse(int x, int y){
		int size = 5;
		MazeModel maze = new MazeModel(size, Color.red, Color.white, Color.black);	
		assertEquals(maze.isOver(0, size-1), true);
	}
}
