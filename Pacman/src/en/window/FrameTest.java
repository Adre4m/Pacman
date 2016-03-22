package en.window;

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
		@SuppressWarnings("unused")
		Frame f = new Frame();
		while (true)
			System.out.println("test");
}

}
