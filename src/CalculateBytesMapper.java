
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import java.io.*;

public class CalculateBytesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		LogEntry logEntry = new LogEntry();
		logEntry.initFromJson(value.toString());
		ApacheCombinedLogEntry apa = new ApacheCombinedLogEntry(logEntry.getData());
		if(apa.getTotalBytes() > 0) {
			output.collect(new Text(logEntry.getSourcePath()) , new IntWritable(apa.getTotalBytes()));
		}
	}
	
}
