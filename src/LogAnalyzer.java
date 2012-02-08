
import java.io.IOException;
import java.util.*;

import java.io.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.Text.Comparator;	
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.Mapper.*;
import org.apache.hadoop.mapreduce.Reducer.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.*;


public class LogAnalyzer extends Configured implements Tool {
	
	public int run(String[] args) throws Exception {
		if(args.length != 2 ) {
			System.err.println("Usage: LogAnalyzer <inputPath> <outputFile>");
		}
		
		Job job = new Job();
		job.setJarByClass(LogAnalyzer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(CalculateBytesMapper.class);
		job.setReducerClass(CalculateBytesReducer.class);
		job.setNumReduceTasks(1);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//job.setOutputValueGroupingComparator(Comparator.class);
		
		return (job.waitForCompletion(true) ? 0 : 1);
	}
	
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		int exitCode = ToolRunner.run(configuration, new LogAnalyzer(), args);
		System.exit(exitCode);
		
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
