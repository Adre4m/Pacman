package en.master;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author BOURGEOIS Adrien, GRIGON Lindsay, RIETZ Vincent
 *
 */
public class Stream {

	/**
	 * 
	 * Will read the file and add every line in a {@code LinkedList<NodeScore>}
	 * 
	 * @author GRIGNON Lindsay
	 * @param path
	 *            The path to find the file to read
	 * @return {@code LinkedList<NodeScore>} The list which contain all scores
	 */
	public static LinkedList<NodeScore> readScore(String path) {
		BufferedReader br;
		LinkedList<NodeScore> score = new LinkedList<NodeScore>();
		int i = 1;
		String alt;
		try {
			// open input stream test.txt for reading purpose.
			br = new BufferedReader(new FileReader(new File(path)));
			while ((alt = br.readLine()) != null) {
				String s = i + "° " + alt + " ";
				String[] split = s.split(" ");
				NodeScore ns = new NodeScore(split[0], split[1], split[2]);
				i++;
				score.add(ns);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return score;
	}

	/**
	 * 
	 * Get the score list and write every element in the file
	 * 
	 * @author GRIGNON Lindsay
	 * @param path
	 *            The path to find the file to read
	 * @param ls
	 *            The list which contain all scores
	 */
	public static void writeScores(String path, LinkedList<NodeScore> ls) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(path)));
			Iterator<NodeScore> it = ls.iterator();
			while (it.hasNext()) {
				NodeScore courant = it.next();
				bw.write(courant.getPseudo() + " " + courant.getScore() + System.getProperty("line.separator"));
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads the file and put the content in a String without the line breaks 
	 * and the spaces.
	 * 
	 * @author RIETZ Vincent
	 * @param path
	 *            The path to find the file to read
	 * @return The lab in a String
	 */
	public static String initiateLab(String path) {
		InputStream is = null;
		int a;
		char c;
		String s = "";

		try {
			// new input stream created
			is = new FileInputStream(path);
			// reads till the end of the stream
			while ((a = is.read()) != -1) {
				// converts integer to character
				c = (char) a;
				if (c != '\n') // avoid line breaks
					s += c;
				else {
					s = s.substring(0, s.length() - 1); // There is a space
														// which has nothing to
														// do here
				}

			}
		} catch (Exception e) {

			// if any I/O error occurs
			e.printStackTrace();
		} finally {

			// releases system resources associated with this stream
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return s;
	}

	/**
	 * 
	 * Read the file config.ini or if it doesn't exist yet the file default.ini.
	 * 
	 * @author BOURGEOIS Adrien
	 * @return a array of size 4. The array is fill with various values, each
	 *         values is specific to each cells. The configuration of the array
	 *         is :
	 *         <ol>
	 *         <li>According to the theme this cell can take four values:
	 *         <ul>
	 *         <li>Classic theme : 0</li>
	 *         <li>Star Wars theme : 1</li>
	 *         <li>The Legend Of Zelda : 2</li>
	 *         </ul>
	 *         </li>
	 *         <li>According to the controls this cell can take two values:
	 *         <ul>
	 *         <li>Keyboard : 0</li>
	 *         <li>Mouse : 1</li>
	 *         </ul>
	 *         </li>
	 *         <li>According to the difficulty this cell can take three values:
	 *         <ul>
	 *         <li>Easy : 0</li>
	 *         <li>Normal : 1</li>
	 *         <li>Hard : 2</li>
	 *         </ul>
	 *         </li>
	 *         <li>According to the sound this cell can take two values :
	 *         <ul>
	 *         <li>On : 0</li>
	 *         <li>Off : 1</li>
	 *         </ul>
	 *         </li>
	 *         </ol>
	 */
	public static int[] readOptions() {
		File f = new File("config.ini");
		if (!f.exists())
			f = new File("default.ini");
		int[] options = new int[4];
		try {
			Scanner in = new Scanner(f);
			while (in.hasNextLine()) {
				String s = in.nextLine();
				switch (s) {
				case "Classic":
					options[0] = 0;
					break;
				case "Star Wars":
					options[0] = 1;
					break;
				case "Zelda":
					options[0] = 2;
					break;
				case "Keyboard":
					options[1] = 0;
					break;
				case "Mouse":
					options[1] = 1;
					break;
				case "Easy":
					options[2] = 0;
					break;
				case "Normal":
					options[2] = 1;
					break;
				case "Hard":
					options[2] = 2;
					break;
				case "On":
					options[3] = 0;
					break;
				case "Off":
					options[3] = 1;
					break;
				}
			}
			in.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		return options;
	}

	/**
	 * 
	 * @param options
	 *            the array with specific values to write on the file.
	 */
	public static void writeOptions(int[] options) {
		File f = new File("config.ini");
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		try {
			PrintStream ps = new PrintStream(f);
			if (options.length == 4) {
				switch (options[0]) {
				case 0:
				default:
					ps.println("Classic");
					break;
				case 1:
					ps.println("Star Wars");
					break;
				case 2:
					ps.println("Zelda");
					break;
				}

				switch (options[1]) {
				case 0:
				default:
					ps.println("Keyboard");
					break;
				case 1:
					ps.println("Mouse");
					break;
				}

				switch (options[2]) {
				case 0:
					ps.println("Easy");
					break;
				case 1:
				default:
					ps.println("Normal");
					break;
				case 2:
					ps.println("Hard");
					break;
				}

				switch (options[3]) {
				case 0:
				default:
					ps.println("On");
					break;
				case 1:
					ps.println("Off");
				}
			} else {
				ps.println("Classic");
				ps.println("Keyboard");
				ps.println("Normal");
				ps.println("On");
			}
			ps.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
}
