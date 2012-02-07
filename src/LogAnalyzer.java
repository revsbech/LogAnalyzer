
import java.io.IOException;
import java.util.*;

import java.io.*;
//import org.apache.commons.io.IOUtils;

 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class LogAnalyzer {
		
	public static void main(String[] args) throws Exception { 
	
		System.out.println("Hello World!");
		String jsonTxt = readFile();
		

		LogEntry le = new LogEntry();
		le.initFromJson(jsonTxt);
		System.out.println("TEST: " + le.getData());
	}
	
	protected static String readFile() {
		File file = new File("sample.log");
		int ch;
		StringBuffer strContent = new StringBuffer("");
		FileInputStream fin = null;
		try {
		  fin = new FileInputStream(file);
		  while ((ch = fin.read()) != -1)
		    strContent.append((char) ch);
		  fin.close();
		} catch (Exception e) {
		  System.out.println(e);
		}		
		return strContent.toString();
	}
	
}
