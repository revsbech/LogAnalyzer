package net.moc.LogAnalyzer;
import net.moc.LogAnalyzer.ApacheCombinedRegexLogEntry;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class ApachCombinedRegexLogEntryTest extends TestCase {

	protected String dummyLogString = "127.0.0.1 - - [20/Oct/2010:21:25:20 +0200] \"GET /typo3 HTTP/1.1\" 301 371 \"-\" \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; da; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10\"";
	
	/**
	 * 
	 */
	protected ApacheCombinedRegexLogEntry logEntry;
	
	public void setUp() throws Exception {		
		logEntry = new ApacheCombinedRegexLogEntry(dummyLogString);
	}
	
	//@test
	public void testUserAgent() {
		assertEquals("Firefox 3", logEntry.getUserAgent().getBrowser().getName());
	}
	
	//@test
	public void testIpAddress() {
		assertEquals("127.0.0.1", logEntry.getClientIp());
	}
	
	//@test
	public void testTotalBytes() {
		assertEquals(371, logEntry.getTotalBytes());
	}
	
	public void testRequestPath() {
		assertEquals("/typo3", logEntry.getRequestPath());
	}
	
	//@test
	public void testEntryWithHostnameforClientIp() throws Exception {
		String logString = "volta.mocsystems.dk - - [20/Oct/2010:21:25:20 +0200] \"GET /typo3 HTTP/1.1\" 301 371 \"-\" \"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; da; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10\"";
		logEntry = new ApacheCombinedRegexLogEntry(logString);
		assertEquals("volta.mocsystems.dk", logEntry.getClientIp());
		
	}
	
	//@test
	public void testEntryWithDashForByteSize() throws Exception {
		String logString   = "127.0.0.1 - - [13/Feb/2012:14:38:10 +0100] \"GET /blog/2011/03/22/design-med-mening HTTP/1.1\" 301 - \"-\" \"Mozilla/5.0 (compatible; MJ12bot/v1.4.2; http://www.majestic12.co.uk/bot.php?+)\"";
		logEntry = new ApacheCombinedRegexLogEntry(logString);
		assertEquals(0, logEntry.getTotalBytes());

	}
	
	
}
