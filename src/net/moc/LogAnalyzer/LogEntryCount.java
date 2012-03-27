package net.moc.LogAnalyzer;

import java.io.*;
import org.apache.hadoop.io.Writable;


public class LogEntryCount implements Writable {
	public long count = 0L;
	public long bytes = 0L;
	
	/**
	 * 
	 * @param long c
	 * @param long b
	 */
	public LogEntryCount(long c, long b) {
		count = c;
		bytes = b;
	}
	
	public LogEntryCount() {
		count = 0L;
		bytes = 0L;
	}

	public void write(DataOutput out) throws IOException {
		out.writeLong(count);
		out.writeLong(bytes);
	}	
	
	public void readFields(DataInput in) throws IOException {
		count = in.readLong();
		bytes = in.readLong();
	}	

	public String toString() {
		return Long.toString(count) + "," + Long.toString(bytes);
	}
	
	public long getCount() {
		return count;
	}
	
	public long getBytesCount() {
		return bytes;
	}
}
