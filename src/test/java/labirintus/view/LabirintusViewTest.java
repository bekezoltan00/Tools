package labirintus.view;

import static org.junit.Assert.*;

import javax.swing.JButton;

public class LabirintusViewTest {

	@org.junit.Test
	public void correctContructorSetSize() {
		int size=0;
		LabirintusView lv = new LabirintusView(size);
		assertEquals(lv.getViewSize(),size);
	}
	
	@org.junit.Test
	public void correctSizeOfButtonsArray() {
		int size=3;
		LabirintusView lv = new LabirintusView(size);
		int arraySize=0;
		for(int i=0; i<lv.getButtons().length; i++){
			arraySize += lv.getButtons()[i].length;
		}
		assertEquals(arraySize,size*size);
	}
	
	@org.junit.Test (expected=ArrayIndexOutOfBoundsException.class)
	public void catchArrayIndexOutOfBoundsException() {
		int size=3;
		LabirintusView lv = new LabirintusView(size);
		assertEquals(lv.getButtons()[size].length,size);
	}
}