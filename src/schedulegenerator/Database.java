/*
 */

package schedulegenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
            
            while ((line = br.readLine()) != null) {
                String COMMA_DELIMITER = ",";
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
            
            for (int i = 0; i < records.size(); i++){
                List<String> entry = records.get(i);

                //Parse out the CSV entries
                DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm");
                
                int id = Integer.parseInt(entry.get(0));
                
                if (id == targetId){
                    LocalTime startTime, endTime;
                    
                    startTime = LocalTime.parse(entry.get(1), format);
                    //startTime = localTime.toSecondOfDay() * 1000;
                    
                    endTime = LocalTime.parse(entry.get(2), format);
                    //endTime = localTime.toSecondOfDay() * 1000;
                    
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
