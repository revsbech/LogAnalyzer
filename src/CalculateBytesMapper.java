import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class CalculateBytesMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		LogEntry logEntry = new LogEntry();
		logEntry.initFromJson(value.toString());
		ApacheCombinedLogEntry apa = new ApacheCombinedLogEntry(logEntry.getData());
		if(apa.getTotalBytes() > 0) {
			context.write(new Text(logEntry.getSourcePath()), new IntWritable(apa.getTotalBytes()));
		}
	}
	
}