/*
 */

package schedulegenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    
    public Database(){
        
    }
    
    public static enum DAYOFWEEK {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    
    public Shift getShift(int targetId){
        
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

                    //Create Shift with parsed data
                    Shift shift = new Shift(id, startTime, endTime);
                    return shift;
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Employee getEmployee(int targetId){
        
        try (BufferedReader br = new BufferedReader(new FileReader("employeeschedules.csv"))) {
            String line;
            
            //Skip first line of CSV
            br.readLine();
            
            //Read through each other line
            while ((line = br.readLine()) != null) {
                String COMMA_DELIMITER = ",";
                String[] values = line.split(COMMA_DELIMITER);
                
                //If the ID of the row is our target ID
                if (Integer.parseInt(values[0]) == targetId){
                    //Parse out the CSV entry
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm");
                    ArrayList<LocalTime[]> schedule = new ArrayList<LocalTime[]>(); 
                    int id;
                    String name;
                    
                    //Employee ID
                    id = Integer.parseInt(values[0]);
                    
                    //Employee name
                    name = values[1];
                    
                    //Add daily start times to list
                    for (int i = 0; i < 7; i++){
                        LocalTime timeArray[] = new LocalTime[2];
                        timeArray[0] = LocalTime.parse(values[i + 2], format);
                        schedule.add(timeArray);
                    }
                    
                    //Read the next line to parse end times
                    line = br.readLine();
                    values = line.split(COMMA_DELIMITER);
                    
                    //Add daily end times to list
                    for (int i = 0; i < 7; i++){
                        LocalTime timeArray[] = new LocalTime[2];
                        timeArray = schedule.get(i);
                        timeArray[1] = LocalTime.parse(values[i + 2], format);
                        schedule.set(i, timeArray);
                    }

                    //Create Employee with parsed data
                    Employee employee = new Employee(id, name, schedule);
                    
                    return employee;
                }
                
                //Skip second line in entry
                br.readLine();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
