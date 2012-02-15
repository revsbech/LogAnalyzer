package net.moc.LogAnalyzer;

import java.lang.Number;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import nl.bitwalker.useragentutils.UserAgent;

public class ApacheCombinedLogEntry extends ParsedJsonLogEntry implements ApacheCombinedLogEntryInterface {

	protected String clientIp;
	protected String user;
	protected String date;
	protected String statusCode;
	protected int totalBytes;
	protected UserAgent agent;
	protected String requestPath;
	
	public boolean initFromJson(String jsonTxt) {
		try {
			JSONParser parser=new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(jsonTxt);
			source = obj.get("@source").toString();
			type = obj.get("@type").toString();
			timestamp = obj.get("@timestamp").toString();
			sourceHost = obj.get("@source_host").toString();
			sourcePath = obj.get("@source_path").toString();
			data = obj.get("@message").toString();
			

			JSONObject fields = (JSONObject)obj.get("@fields");
			
			JSONArray agentArray=(JSONArray)fields.get("agent");
			agent = new UserAgent(agentArray.get(0).toString());
			
			JSONArray bytesArray=(JSONArray)fields.get("bytes");
			totalBytes = Integer.parseInt(bytesArray.get(0).toString(), 10);
			
			JSONArray requestArray=(JSONArray)fields.get("request");
			requestPath = requestArray.get(0).toString();
			
			return true;
		} catch (Exception e) {
			System.err.println("Error when parsing Json into ApacheCombinedLogEntry");
			return false;
		}				
	}
	
	/**
	 * @return String
	 */
	public String getClientIp() {
		return clientIp;
	}
	
	/** 
	 * @return UserAgent
	 */
	public UserAgent getUserAgent() {
		return agent;
	}
	
	public int getTotalBytes() {
		return totalBytes;
	}
	
	public String getRequestPath() {
		return requestPath;
	}
	
	
}
