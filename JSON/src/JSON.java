/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author EL.2021.T2D0G7
 */
public class JSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream studentInputFile = new FileInputStream("students.json");
        JsonParser jsonParser = Json.createParser(studentInputFile);
        Event parserEvent = null;
        
        while (jsonParser.hasNext()) {
            parserEvent = jsonParser.next();
            if (parserEvent == Event.KEY_NAME && "root".equals(jsonParser.getString())) {
                parserEvent = jsonParser.next();
                break;
            }
            
            while (parserEvent != Event.END_OBJECT) {
            switch (parserEvent) {
                case KEY_NAME: 
                    System.out.print(jsonParser.getString());
                    System.out.print(" = ");
                    break;

                case VALUE_NUMBER: 
                    if (jsonParser.isIntegralNumber()) {
                        System.out.println(jsonParser.getInt());
                    } else {
                        System.out.println(jsonParser.getBigDecimal());
                    }
                    break;
 
                case VALUE_STRING:
                    System.out.println(jsonParser.getString());
                    break;
            }
            parserEvent = jsonParser.next();
        }
        }
        
        
    }
}