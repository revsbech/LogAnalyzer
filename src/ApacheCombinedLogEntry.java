import java.lang.Number;

public class ApacheCombinedLogEntry {

	private String clientIp;
	private String user;
	private String date;
	private String statusCode;
	private int totalBytes;
	
	public ApacheCombinedLogEntry(String data) {
		String[] temp = data.split(" ");
		clientIp = temp[0];
		try {
			totalBytes = Integer.parseInt(temp[9], 10);
		} catch (NumberFormatException e) {
			totalBytes = 0;
		}
	}
	
	public int getTotalBytes() {
		return totalBytes;
	}
}
