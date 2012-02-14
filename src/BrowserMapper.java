import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import nl.bitwalker.useragentutils.*;


public class BrowserMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		ApacheCombinedLogEntry logEntry = new ApacheCombinedLogEntry();
		if (logEntry.initFromJson(value.toString())) {
			context.write(new Text(logEntry.getUserAgent().getBrowser().getName()) , new IntWritable(1));
		}
	}
	
}