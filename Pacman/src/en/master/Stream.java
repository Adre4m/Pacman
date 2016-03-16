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
import java.util.Iterator;
import java.util.LinkedList;

import en.window.NodeScore;

public class Stream {

	public String readConfig() {
		InputStream is = null;
		int a;
		char c;
		String s = "";
		boolean firstLaunch = false;

		try { // To find which file we need to open
			is = new FileInputStream("options/config.opt");
		} catch (FileNotFoundException fnfe) {
			try {
				is = new FileInputStream("options/default.opt");
				firstLaunch = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		try { // To read the file
				// reads till the end of the stream
			while ((a = is.read()) != -1) {
				// converts integer to character
				c = (char) a;
				s += c;
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

		if (firstLaunch) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						new File("options/config.opt")));
				// if the file doesn't exist, it is created in the root of the
				// project
				writer.write(s); // copy default in config
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	/**
	 * @author GRIGNON Lindsay
	 * @param path
	 *            The path to find the file to read
	 * @return LinkedList<NodeScore> The list which contain all scores
	 * 
	 *         Will read the file and add every line in a LinkedList <NodeScore>
	 */
	public LinkedList<NodeScore> readScore(String path) {
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
	 * @author GRIGNON Lindsay
	 * @param path
	 *            The path to find the file to read
	 * @param ls
	 *            The list which contain all scores
	 * 
	 *            Get the score list and write every element in the file
	 */
	public void writeScores(String path, LinkedList<NodeScore> ls) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(new File(path)));
			Iterator<NodeScore> it = ls.iterator();
			while (it.hasNext()) {
				NodeScore courant = it.next();
				bw.write(courant.getPseudo() + " " + courant.getScore()
						+ System.getProperty("line.separator"));
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String initiateLab(String path) {
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
}
