/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mihir
 */
public class ReadWriteJson {
    
    public static JsonObject readJson(String jsonString){
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        return jsonObject;
    }
    
    public static void writeJson(JsonObject jsonString, String jsonFileName){
        try (FileWriter file = new FileWriter(jsonFileName)) {
            String userDirectory = System.getProperty("user.dir");
            System.out.println("Writing Json to "+userDirectory);
            file.write(jsonString.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
