package en.master;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stream {
	
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
