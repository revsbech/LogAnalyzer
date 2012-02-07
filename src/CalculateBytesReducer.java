
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import java.io.IOException;
import java.util.Iterator;

public class CalculateBytesReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		int totalBytes = 0;
		while (values.hasNext()) {
			totalBytes += values.next().get();
		}
		output.collect(key, new IntWritable(totalBytes));
	}
}
