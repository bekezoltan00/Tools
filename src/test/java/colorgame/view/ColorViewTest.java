package colorgame.view;

import static org.junit.Assert.assertEquals;

public class ColorViewTest {
	@org.junit.Test
	public void correctContructorSetSize() {
		int size=13;
		ColorView color = new ColorView();
		assertEquals(color.getViewSize(),size);
	}
	
	@org.junit.Test
	public void correctSizeOfButtonsArray() {
		ColorView color = new ColorView();
		int arraySize=0;
		for(int i=0; i< color.getButtons().length; i++){
			arraySize += color.getButtons()[i].length;
		}
		assertEquals(arraySize, 25);
	}
}
