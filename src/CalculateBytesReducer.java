import java.io.IOException;
import java.util.*;
import java.math.BigInteger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class CalculateBytesReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		long totalBytes = 0L;
		for (IntWritable value : values) {
			totalBytes += (long)value.get();
		}
		context.write(key, new LongWritable(totalBytes));
	}
	
}
