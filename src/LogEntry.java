import java.io.IOException;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

public class LogEntry {
	protected String source;
	protected String sourceHost;
	protected String sourcePath;
	protected String type;
	protected String timestamp;
	protected String data;
	
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
			return true;
		} catch (Exception e) {
			System.err.println("Error when parsing Json into LogEntry");
			return false;
		}
	}
	
	public String getSource() {
		return source;
	}
	
	public String getSourceHost() {
		return sourceHost;
	}
	
	public String getSourcePath() {
		return sourcePath;
	}
	
	public String getType() {
		return type;
	}
	
	public String getData() {
		return data;
	}
	public String getTimestamp() {
		return timestamp;
	}
	
	
}
