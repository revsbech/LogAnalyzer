package net.moc.LogAnalyzer;

import java.io.IOException;

import net.moc.LogAnalyzer.Logstash.ApacheCombinedParsedJsonLogEntry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class CalculateBytesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		ApacheCombinedParsedJsonLogEntry logEntry = new ApacheCombinedParsedJsonLogEntry();
		if(logEntry.initFromJson(value.toString())) {
	
			if(logEntry.getTotalBytes() > 0) {
				context.write(new Text(logEntry.getSource()), new IntWritable(logEntry.getTotalBytes()));
			}
		}
	}
	
}