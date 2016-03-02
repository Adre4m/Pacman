package en.master.characters;

import org.junit.Before;
import org.junit.Test;

public class TestRadius {
	int[][] res = new int[17][17];

	@Before
	public void setUp() throws Exception {
		radius(res, res.length / 2, res[0].length / 2, 8);
	}

	@Test
	public void test() {
		int[][] test = new int[17][17];

		radius(test, test.length / 2 - 8, test.length / 2 - 8, 8);
		for (int i = 0; i < test.length; ++i) {
			for (int j = 0; j < test[0].length; ++j) {
				// assertEquals(test[i][j], res[i][j]);
				if (test[i][j] != 0)
					System.out.print(test[i][j]);
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public void radius(int[][] tab, int xCenter, int yCenter, int radius) {
		tab[xCenter][yCenter] = 0;
		for (int r = 1; r <= radius; ++r) {
			int x = 0;
			int y = r;
			int d = r - 1;
			while (x <= y) {
				if ((x + xCenter) < tab.length && (y + yCenter) < tab[0].length)
					tab[x + xCenter][y + yCenter] = r;
				if ((y + xCenter) < tab.length && (x + yCenter) < tab[0].length)
					tab[y + xCenter][x + yCenter] = r;
				if (0 <= (xCenter - x) && 0 <= (yCenter - y))
					tab[xCenter - x][yCenter - y] = r;
				if (0 <= (xCenter - y) && 0 <= (yCenter - x))
					tab[xCenter - y][yCenter - x] = r;
				if ((x + xCenter) < tab.length && 0 <= (yCenter - y))
					tab[x + xCenter][yCenter - y] = r;
				if ((y + xCenter) < tab.length && 0 <= (yCenter - x))
					tab[y + xCenter][yCenter - x] = r;
				if (0 <= (xCenter - x) && (y + yCenter) < tab[0].length)
					tab[xCenter - x][y + yCenter] = r;
				if (0 <= (xCenter - y) && (x + yCenter) < tab[0].length)
					tab[xCenter - y][x + yCenter] = r;
				if (2 * x <= d) {
					d -= 2 * x + 1;
					x++;
				} else if (d < 2 * (r - y)) {
					d += 2 * y - 1;
					y--;
				} else {
					d += 2 * (y - x - 1);
					y--;
					x++;
				}
			}
		}
	}

}
