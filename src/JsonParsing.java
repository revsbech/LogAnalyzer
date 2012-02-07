

import java.io.InputStream;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io;

public class JsonParsing {

    public static void main(String[] args) throws Exception {
    	
        /*InputStream is = 
                JsonParsing.class.getResourceAsStream( "sample-json.txt");
                */
    	
        String jsonTxt = "{'foo':'bar','coolness':2.0,'altitude':39000,'pilot':{'firstName':'Buzz','lastName':'Aldrin'},'mission':'apollo 11'}";
        
        JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );
        /*
        double coolness = json.getDouble( "coolness" );
        int altitude = json.getInt( "altitude" );
        JSONObject pilot = json.getJSONObject("pilot");
        String firstName = pilot.getString("firstName");
        String lastName = pilot.getString("lastName");
        
        System.out.println( "Coolness: " + coolness );
        System.out.println( "Altitude: " + altitude );
        System.out.println( "Pilot: " + lastName );
        */
    	System.out.println( "TEST");
    }
}