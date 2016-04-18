package labirintus.model;

import static org.junit.Assert.*;

import java.awt.Color;

public class LabirintusModelTest {
	
	@org.junit.Test
	public void correctStartFieldColor(){
		int size = 2;
		LabirintusModel lm = new LabirintusModel(size);
		assertEquals(lm.getColor(size-1, 0),Color.yellow);
	}
	
	@org.junit.Test
	public void correctMovedUp(){
		int size = 10;
		LabirintusModel lm = new LabirintusModel(size);
		Field[][] fields = lm.getFields();
		if(!fields[size-2][0].isWall()){
			lm.moveUp();
			assertEquals(lm.getColor(size-2, 0),Color.yellow);
		}else{
			lm.moveUp();
			assertEquals(lm.getColor(size-2, 0),Color.gray);
		}
		
	}
}
