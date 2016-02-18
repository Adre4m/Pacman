package en.master;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Game {

	private char[][] lab;
	private int score;
	public Character[] characters = { new Pacman(), new Clyde(), new Inky(), new Blinky(), new Pinky() };
	private boolean restartNeed;

	public Game() {
		lab = new char[28][32];
		score = 0;
		restartNeed = false;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char[][] getLab() {
		return lab;
	}

	public void setLab(char[][] lab) {
		this.lab = lab;
	}

	public void init() {

	}

	public void initTest() {
		  InputStream is = null;
		       int a;
		       char c;
		       
		       try{
		          // new input stream created
		          is = new FileInputStream("labyrinths/test.txt");
		          
		          String s = "";
		          // reads till the end of the stream
		          while((a=is.read())!=-1)
		          {
		             // converts integer to character
		             c=(char)a;
		             
		             s+=c;
		             System.out.println(s);

		          }
		          int index=0;
		    for (int i = 0; i < lab.length; ++i)
		    for (int j = 0; j < lab[0].length; ++j){
		     lab[i][j] = s.charAt(index);
		     index++;
//		     System.out.println(lab[i][j]);
		    }
		//   int i = lab.length / 2;
		//   int j = lab[0].length / 2;
		//   lab[i][j - 4] = 'G';
		//   lab[i][j - 3] = 'G';
		//   lab[i][j - 2] = 'G';
		//   lab[i][j - 1] = 'G';
		//   lab[i - 1][j] = 'S';
		//   lab[i][j] = 'P';
		//   characters[0].position.setLocation(i, j);
		//   characters[1].position.setLocation(i, j - 1);
		//   characters[2].position.setLocation(i, j - 2);
		//   characters[3].position.setLocation(i, j - 3);
		//   characters[4].position.setLocation(i, j - 4);
		       }catch(Exception e){
		          
		          // if any I/O error occurs
		          e.printStackTrace();
		       }finally{
		          
		          // releases system resources associated with this stream
		          if(is!=null)
		    try {
		     is.close();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		       }
		 }

	public String toString() {
		String s = "";
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				s += lab[i][j];
			s += '\n';
		}
		s += "Score : " + score + '\n';
		return s;
	}

	public Ghost getGhost(int x, int y) {
		for (int i = 1; i < characters.length; ++i)
			if (characters[i].position.x == x && characters[i].position.y == y)
				return (Ghost) characters[i];
		return null;
	}

	public void restart() {
		System.out.println("RESTART !!!!!!");
	}

	public boolean isRestartNeed() {
		return restartNeed;
	}

	public void setRestartNeed(boolean restartNeed) {
		this.restartNeed = restartNeed;
	}

}
