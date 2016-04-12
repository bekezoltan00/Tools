package motor.model;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.SwingConstants;

import motor.model.Player;

public class PlayerTest {
	
	@org.junit.Test
	public void shouldReturnTheCorrectConstructorDatasWhenPlayerCreatedWithGivenParameters() {
		Player p1 = new Player(Color.CYAN, 5, 5, SwingConstants.LEFT);
		assertTrue(Color.CYAN.equals(p1.getColor()));
		assertEquals(p1.getPosX(), 5);
		assertEquals(p1.getPosY(), 5);
		assertEquals(p1.getDirection(), 2);
	}
	
}
