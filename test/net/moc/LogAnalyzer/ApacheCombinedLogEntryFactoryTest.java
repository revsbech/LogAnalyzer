package net.moc.LogAnalyzer;
import net.moc.LogAnalyzer.ApacheCombinedLogEntryFactory;
import net.moc.LogAnalyzer.ApacheCombinedLogEntryInterface;
import net.moc.LogAnalyzer.ApacheCombinedRegexLogEntry;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ApacheCombinedLogEntryFactoryTest extends TestCase {
	
	//@test
	public void testParseLogStashEntry() {
		String jsonLogEntry = "{\"@source\":\"file://doppler//home/dtupodcasts/logs/access.log\",\"@type\":\"apache-access\",\"@tags\":[],\"@fields\":{\"clientip\":[\"130.225.93.56\"],\"ident\":[\"-\"],\"auth\":[\"-\"],\"timestamp\":[\"13/Feb/2012:14:37:59 +0100\"],\"ZONE\":[\"+0100\"],\"verb\":[\"GET\"],\"request\":[\"/fileadmin/templates/javascripts/slider.js\"],\"httpversion\":[\"1.1\"],\"response\":[\"200\"],\"bytes\":[\"3029\"],\"referrer\":[\"http://podcast.llab.dtu.dk/feeds/learninglab\"],\"agent\":[\"\\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7\\\"\"]},\"@timestamp\":\"2012-02-13T13:38:11.854000Z\",\"@source_host\":\"doppler\",\"@source_path\":\"//home/dtupodcasts/logs/access.log\",\"@message\":\"130.225.93.56 - - [13/Feb/2012:14:37:59 +0100] \\\"GET /fileadmin/templates/javascripts/slider.js HTTP/1.1\\\" 200 3029 \\\"http://podcast.llab.dtu.dk/feeds/learninglab\\\" \\\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7\\\"\"}";
		ApacheCombinedLogEntryInterface logEntry = ApacheCombinedLogEntryFactory.getLogEntryFromString(jsonLogEntry);
		assertEquals("130.225.93.56", logEntry.getClientIp());
	}
	
	//@test
	public void testParseLogStashEntryReturnNullOnMalformedJson() {
		String jsonLogEntry = "TEST";
		ApacheCombinedLogEntryInterface logEntry = ApacheCombinedLogEntryFactory.getLogEntryFromString(jsonLogEntry);
		assertNull(logEntry);
		
	}
}
