package net.moc.LogAnalyzer;
import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class LogEntryCountSumReducer extends Reducer<Text, LogEntryCount, Text, LogEntryCount> {
	
	/**
	 * Simple sum up all the bytecounts in the Reducer input
	 * @param key
	 * @param values
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void reduce(Text key, Iterable<LogEntryCount> values, Context context) throws IOException, InterruptedException {
		long totalBytes = 0L;
		long totalCount = 0L;
		for (LogEntryCount value : values) {
			totalCount += (long)value.getCount();
			totalBytes += (long)value.getBytesCount();
		}
		context.write(key, new LogEntryCount(totalCount, totalBytes));
	}
	
}
