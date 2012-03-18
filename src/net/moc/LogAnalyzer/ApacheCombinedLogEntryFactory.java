package net.moc.LogAnalyzer;
import net.moc.LogAnalyzer.Logstash.*;

public class ApacheCombinedLogEntryFactory {

	
	/**
	 * Given a string, it will try to detect what kind of logentry this string is
	 * and return an ApacheCombinedLogEtryInterface implementation
	 * @return ApacheCombindedLogEntryInterface
	 */
	public static ApacheCombinedLogEntryInterface getLogEntryFromString(String value) {
		try {
			//ParsedJsonLogEntry logstashEntry = new ParsedJsonLogEntry();
			//logstashEntry.initFromJson(value);			
			//ApacheCombinedRegexLogEntry logEntry = new ApacheCombinedRegexLogEntry(logstashEntry.getData());
			String[] temp = value.split("\t", 2);
			ApacheCombinedRegexLogEntry logEntry = new ApacheCombinedRegexLogEntry(temp[1]);
			return logEntry;
		} catch (Exception e) {
			return null;
		}
		
	}
}
