/*
 */

package schedulegenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    public Database(){
        
    }
    
    public Shift getShift(int targetId){
        List<List<String>> records = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("shifts.csv"))) {
            String line;
            
            //Skip first line of CSV
            br.readLine();
            
            //Read through each line
            while ((line = br.readLine()) != null) {
                String COMMA_DELIMITER = ",";
                String[] values = line.split(COMMA_DELIMITER);
                
                //If the ID of the row is our target ID
                if (Integer.parseInt(values[0]) == targetId){
                    //Parse out the CSV entry
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm");
                    int id;
                    LocalTime startTime, endTime;
                    
                    id = Integer.parseInt(values[0]);
                    startTime = LocalTime.parse(values[1], format);
                    endTime = LocalTime.parse(values[2], format);

                    Shift shift = new Shift(id, startTime, endTime);
                    
                    return shift;
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
