
import java.io.IOException;
import java.util.*;

import java.io.*;
 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class LogAnalyzer {
		
	public static void main(String[] args) throws Exception {
		
		if(args.length != 2 ) {
			System.err.println("Usage: LogAnalyzer <inputPath> <outputFile>");
		}
		
		JobConf conf = new JobConf("LogAnalyzer");
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setMapperClass(CalculateBytesMapper.class);
		conf.setReducerClass(CalculateBytesReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
		/**/
		/*
		System.out.println("Hello World!");
		String jsonTxt = readFile();
		
		
		LogEntry le = new LogEntry();
		le.initFromJson(jsonTxt);
		System.out.println("TEST: " + le.getData());
		
		
		ApacheCombinedLogEntry apa = new ApacheCombinedLogEntry(le.getData());
		System.out.printf("TEST: %d", apa.getTotalBytes());
	/**/	
	}
	
	protected static String readFile() {
		File file = new File("src/sample.log");
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
