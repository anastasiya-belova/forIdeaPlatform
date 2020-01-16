/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import main.Ticket;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Anastasiya Belova
 */
public class JsonParser {
    private File file;
    
    private JsonParser() {}

    private static final JsonParser INSTANCE = new JsonParser();

    public static JsonParser getInstance() {
        return INSTANCE;
    }
    
    public void setFile(String fileName){
        this.file = new File(fileName);
    }
    
    public String getFileName(){
        return this.file.getPath();
    }
    
    public List<Ticket> readJson() throws IOException, JsonSyntaxException, NullPointerException {
        Logger logger = LoggerFactory.getLogger(JsonParser.class);
        String jsonString;
        jsonString = FileUtils.readFileToString(file, "UTF-8");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Ticket>>(){}.getType();
        List<Ticket> tickets = gson.fromJson(jsonString, type);
        logger.info("File " + file.getName() + " was read successfully!");
        return tickets;
    }
}
