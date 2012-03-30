package net.moc.LogAnalyzer;

import java.util.regex.*;
import nl.bitwalker.useragentutils.*;

/**
 * Parse an Apache combined log entry using a regular expression.
 * 
 * @author revsbech
 *
 */
public class ApacheCombinedRegexLogEntry implements ApacheCombinedLogEntryInterface {
	
	protected String ip;
	protected UserAgent userAgent;
	protected int totalBytes = 0;
	protected String requestPath;
	
	protected String patternString = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) ([\\d\\-]+) \"([^\"]+)\" \"([^\"]+)\""; 
	
	protected Pattern pattern;
	
	public ApacheCombinedRegexLogEntry(String logLine) throws Exception  {
		pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(logLine);
		if (matcher.matches()) {
			ip = matcher.group(1);
			userAgent = new UserAgent(matcher.group(9));
			String totalBytesString = matcher.group(7);
			
			if(!totalBytesString.equals("-")) {
				totalBytes = Integer.parseInt(matcher.group(7));
			}
			String[] items = matcher.group(5).split(" ");
			requestPath = items[1];
		} else {
			throw new Exception("Error matching!");
		}
	}
	
	public String getClientIp() {
		return ip;
	}
	
	public UserAgent getUserAgent() {
		return userAgent;
	}
	
	public int getTotalBytes() {
		return totalBytes;
	}
	
	public String getRequestPath() {
		return requestPath;
	}

}
