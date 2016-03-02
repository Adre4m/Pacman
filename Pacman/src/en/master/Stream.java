package en.master;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Stream {
	
	public String readConfig(){
		InputStream is = null;
		int a;
		char c;
		String s = "";
		boolean firstLaunch = false;
		
		try { //To find which file we need to open
			is = new FileInputStream("options/config.opt");
		} catch (FileNotFoundException fnfe) {
		     try {
		    	 is = new FileInputStream("options/default.opt");
		    	 firstLaunch = true;
		     } catch (FileNotFoundException e) {
		         e.printStackTrace();
		     }
		}
		
		
		try { //To read the file
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
		
		if(firstLaunch){
			try{
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("options/config.opt")));
				// if the file doesn't exist, it is created in the root of the project
				writer.write(s); //copy default in config
				writer.close();
			}
			catch (IOException e)
			{
			e.printStackTrace();
			}
		}
		return s;
	}
	
	public String readScore(String path){
		InputStream is = null;
		int a;
		char c;
		String s = "";
		
		try {
			// new input stream created
			is = new FileInputStream(path);
			s="<html>";
			// reads till the end of the stream
			while ((a = is.read()) != -1) {
				// converts integer to character
				c = (char) a;
				if(c=='\n'){
					s += "<br>";
				}
				else s+=c;
			}
			s+="</html>";
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
	
	public String initiateLab(String path){
		InputStream is = null;
		int a;
		char c;
		String s = "";
		
		try {
			// new input stream created
			is = new FileInputStream(path);
			
			boolean b = true;
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
