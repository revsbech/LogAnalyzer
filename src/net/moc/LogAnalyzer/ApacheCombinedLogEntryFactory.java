package net.moc.LogAnalyzer;
import net.moc.LogAnalyzer.Logstash.*;

/**
 * Factory for parsing loglines into correct ApacheCombinedLogEntryInterface
 * implementations
 * 
 * The factory is relying on data being stored in a format
 * 
 * SOURCE\tAPACHELOGENTRY
 * 
 * @todo This is overkill, and having a factory serves no purpose, as we always
 * have the same logformat to parse. Should be removed entirely.
 * 
 * @author revsbech
 *
 */
public class ApacheCombinedLogEntryFactory {
	
	/**
	 * Given a string, it will try to detect what kind of logentry this string is
	 * and return an ApacheCombinedLogEtryInterface implementation
	 * @return ApacheCombindedLogEntryInterface
	 */
	public static ApacheCombinedLogEntryInterface getLogEntryFromString(String value) {
		try {
			String[] temp = value.split("\t", 2);
			ApacheCombinedRegexLogEntry logEntry = new ApacheCombinedRegexLogEntry(temp[1]);
			return logEntry;
		} catch (Exception e) {
			return null;
		}
		
	}
}
