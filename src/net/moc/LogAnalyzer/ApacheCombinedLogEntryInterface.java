package net.moc.LogAnalyzer;

import nl.bitwalker.useragentutils.UserAgent;

public interface ApacheCombinedLogEntryInterface {
	
	public String getClientIp();
	
	public String getRequestPath();
	
	public UserAgent getUserAgent();
	
	public int getTotalBytes();
	
}
