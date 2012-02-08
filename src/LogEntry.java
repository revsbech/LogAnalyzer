import java.io.IOException;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;

public class LogEntry {
	private String source;
	private String sourceHost;
	private String sourcePath;
	private String type;
	private String timestamp;
	private String data;
	
	public void initFromJson(String jsonTxt) {
		try {
			JSONParser parser=new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(jsonTxt);
			source = obj.get("@source").toString();
			type = obj.get("@type").toString();;
			timestamp = obj.get("@timestamp").toString();;
			sourceHost = obj.get("@source_host").toString();;
			sourcePath = obj.get("@source_path").toString();;
			data = obj.get("@message").toString();;
		} catch (Exception e) {
			System.out.println("Error when parsing Json into LogEntry");
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
