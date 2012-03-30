package net.moc.LogAnalyzer;

import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import nl.bitwalker.useragentutils.*;

/**
 * Mapper for mapping all Apache log files into Key, Values, with Browser being the Key, and 
 * the value being a LogEntryCount class summing up number of hits and total bytes served.
 * 
 * The actual parsing of lines is handled with the ApacheCombinedLogEntryFactory class, which
 * will check for different formats of each logine, and return a ApacheCombinedLogEntryInterface.
 * 
 * The grouping of different browsers is done in the implementation of ApacheCombinedLogEntryInterface
 * which using the UserAgentUtils library.
 * 
 * @author revsbech
 */
public class BrowserMapper extends Mapper<LongWritable, Text, Text, LogEntryCount> {
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		try {
			ApacheCombinedLogEntryInterface logEntry = ApacheCombinedLogEntryFactory.getLogEntryFromString(value.toString());
			long bytes = (long)logEntry.getTotalBytes();
			LogEntryCount count = new LogEntryCount(1L,bytes);
			context.write(new Text(logEntry.getUserAgent().getBrowser().getName()) , count);
		} catch ( Exception e) {
			System.err.println("Error parsing logstash output");
			context.write(new Text("Unknown (parse error in logentry") , new LogEntryCount(1L,0L));
		}
	}
	
}