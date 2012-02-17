package net.moc.LogAnalyzer;

import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import nl.bitwalker.useragentutils.*;



public class BrowserMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
				//@todo: Maybe we should try to see if this is really JSON encoded string or not. Perhaps some sort of factory
			ApacheCombinedLogEntryInterface logEntry = ApacheCombinedLogEntryFactory.getLogEntryFromString(value.toString());
			context.write(new Text(logEntry.getUserAgent().getBrowser().getName()) , new IntWritable(1));
			
		} catch ( Exception e) {
			System.err.println("Error parsing logstash outpu");
			context.write(new Text("Unknown (parse error in logentry") , new IntWritable(1));
		}
	}
	
}