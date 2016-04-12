package motor.model;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import motor.model.Stage;

public class StageTest {

	@org.junit.Test(expected=ArrayIndexOutOfBoundsException.class)
	public void shouldReturnArrayIndexOutOfBoundsExceptionWhenOutOfArray() {
		Stage s = new Stage(20, new JButton[20][20], Color.GRAY, new JPanel());
		assertTrue(s.getButtons()[20][20].getBackground().equals(Color.GRAY));
	}
	
	@org.junit.Test
	public void shouldReturnCorrectDatasAfterCreatedAStageWithConstructor() {
		Stage s = new Stage(20, new JButton[20][20], Color.GRAY, new JPanel());
		assertEquals(s.n, 20);
		assertTrue(new JButton[20][20].length == s.getButtons().length);
		assertTrue(s.defaultColor.equals(Color.GRAY));
	}
	
}
