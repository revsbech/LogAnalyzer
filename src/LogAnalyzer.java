
import java.io.IOException;
import java.util.*;

import java.io.*;
 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.hadoop.mapreduce.Reducer.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class LogAnalyzer {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length != 2 ) {
			System.err.println("Usage: LogAnalyzer <inputPath> <outputFile>");
		}
		
		Job job = new Job();
		job.setJarByClass(LogAnalyzer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(CalculateBytesMapper.class);
		job.setReducerClass(CalculateBytesReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
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
