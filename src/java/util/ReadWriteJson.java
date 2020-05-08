/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mihir
 */
public class ReadWriteJson {
    
    public static JsonObject readJson(String jsonString){
        //parse JSON in String form into JsonObj
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        return jsonObject;
    }
    
    public static JsonObject readJsonFile(String path) throws FileNotFoundException{
        //parser JSON File into JSonObj
        String userDirectory = System.getProperty("user.dir");
        System.out.println("Reading Json from "+userDirectory);
        JsonReader obj = new JsonReader(new FileReader(path));
        JsonObject jsonObject = new JsonParser().parse(obj).getAsJsonObject();
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
