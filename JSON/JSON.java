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
    public static void main(String[] args) {
        
        try {
            FileInputStream studentInputFile = new FileInputStream("students.json");
            JsonParser parser = Json.createParser(studentInputFile);
            Event event = null;

            while (parser.hasNext()) {
                event = parser.next();
                if (event == Event.KEY_NAME && "root".equals(parser.getString())) {
                    event = parser.next();
                    break;
                }
            }

            while (event != Event.END_OBJECT) {
                switch (event) {
                    case KEY_NAME: 
                        System.out.print(parser.getString());
                        System.out.print(" = ");
                        break;

                    case VALUE_NUMBER: 
                        if (parser.isIntegralNumber()) {
                            System.out.println(parser.getInt());
                        } else {
                            System.out.println(parser.getBigDecimal());
                        }
                        break;

                    case VALUE_STRING: 
                        System.out.println(parser.getString());
                        break;
                }
                event = parser.next();
            }
        } catch(FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
