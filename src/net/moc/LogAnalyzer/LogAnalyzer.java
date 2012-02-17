
package net.moc.LogAnalyzer;

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
		if(args.length != 3 ) {
			this.printHelp();
			System.exit(0);
		}
		
		Job job = new Job();
		job.setJarByClass(LogAnalyzer.class);
		
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		if (args[0].equalsIgnoreCase("bytes")) {
				job.setMapperClass(CalculateBytesMapper.class);
		} else if (args[0].equalsIgnoreCase("browser")) {
				job.setMapperClass(BrowserMapper.class);
		} else {
			System.out.println("Unknown option '" + args[2] + "' for argument <type>");
			this.printHelp();
			System.exit(0);
		}
		
		
		job.setReducerClass(LongSumReducer.class);
		job.setNumReduceTasks(1);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		return (job.waitForCompletion(true) ? 0 : 1);
	}
	
	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		int exitCode = ToolRunner.run(configuration, new LogAnalyzer(), args);
		System.exit(exitCode);
	}
	
	protected void printHelp() {
		System.err.println("Usage: LogAnalyzer <type> <inputPath> <outputFile>");
		System.err.println("");
		System.err.println(" * type: bytes, browser");	
	}
}
