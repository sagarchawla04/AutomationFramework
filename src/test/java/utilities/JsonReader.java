package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.FileReader;

public class JsonReader {

    /**
     * Method is to read the data from Json file
     */
    public static String getValueByJPath(String JsonPath, String jpath) {
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(JsonPath));
            for (String s : jpath.split("/")) {
                if (!s.isEmpty()) {
                    if (!(s.contains("[") || s.contains("]")))
                        obj = ((JSONObject) obj).get(s);
                    else if (s.contains("[") || s.contains("]"))
                        obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
                }
            }
        } catch (Exception e) {
            Assert.fail("*****Error reading data from JSON file*****");
        }
        return obj.toString();
    }
}
