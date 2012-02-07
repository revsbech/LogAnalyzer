import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class CalculateBytesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterator<IntWritable> values, Context context) throws IOException, InterruptedException {
		int totalBytes = 0;
		while (values.hasNext()) {
			totalBytes += values.next().get();
		}
		context.write(key, new IntWritable(totalBytes));
	}
}
