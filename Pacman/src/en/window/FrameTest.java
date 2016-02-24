package en.window;

import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.Test;

public class FrameTest {
	/*Frame f;
	@Before
	public void setUp() throws Exception {
		f = new Frame();
	}

	@Test
	public void test() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		assertTrue((int) (screenSize.height * 0.9) == f.getHeight());
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public static void main (String[] args) {
		Frame f = new Frame();
		while (true);
}

}
