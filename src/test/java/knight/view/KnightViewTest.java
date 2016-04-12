package knight.view;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;

public class KnightViewTest {

	@org.junit.Test
	public void constructorShouldCreateTheCorrectAmountOfButtons() {
		
		KnightView kv = new KnightView();
		assertEquals(kv.getButtons().length, new JButton[5][5].length);
	}
	
	@org.junit.Test
	public void constructorShouldSetTheBackgroundForEveryButton() {
		KnightView kv = new KnightView();
		assertTrue(kv.getButtons()[0][0].getBackground().equals(Color.WHITE));
		assertTrue(kv.getButtons()[4][4].getBackground().equals(Color.WHITE));
	}
	
	@org.junit.Test(expected=ArrayIndexOutOfBoundsException.class)
	public void shouldReturnArrayIndexOutOfBoundsExceptionWhenOutOfButtonArray() {
		KnightView kv = new KnightView();
		assertEquals(kv.getButtons()[5][5].getBackground(), Color.WHITE);
	}
	
}
