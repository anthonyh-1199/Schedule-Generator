/*
 */

package schedulegenerator;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;

public class Employee {
    
    private int id;
    private String name;
    private ArrayList<LocalTime[]> schedule = new ArrayList<LocalTime[]>(); 
    
    public Employee(int id, String name, ArrayList<LocalTime[]> schedule){
        this.id = id;
        this.name = name;
        this.schedule = schedule;
    }
    
    //Getter methods
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public ArrayList<LocalTime[]> getSchedule(){
        return schedule;
    }
    
    //Returns the length of available time the employee can work during a week
    public long getScheduleLength(){
        long l = 0;
        
        for (LocalTime[] time : schedule){
            l += ((LocalTime)Array.get(time, 1)).toSecondOfDay() - ((LocalTime)Array.get(time, 0)).toSecondOfDay();
        }
        
        return l;
    }
    
    //Setter methods
    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setSchedule(ArrayList<LocalTime[]> schedule){
        this.schedule = schedule;
    }
    
    //toString method
    @Override
    public String toString(){
        String s = id + ". " + name + ": \n";
        s += "\tMonday: " + Array.get(schedule.get(0), 0) + " - " + Array.get(schedule.get(0), 1) + "\n";
        s += "\tTuesday: " + Array.get(schedule.get(1), 0) + " - " + Array.get(schedule.get(1), 1) + "\n";
        s += "\tWednesday: " + Array.get(schedule.get(2), 0) + " - " + Array.get(schedule.get(2), 1) + "\n";
        s += "\tThursday: " + Array.get(schedule.get(3), 0) + " - " + Array.get(schedule.get(3), 1) + "\n";
        s += "\tFriday: " + Array.get(schedule.get(4), 0) + " - " + Array.get(schedule.get(4), 1) + "\n";
        s += "\tSaturday: " + Array.get(schedule.get(5), 0) + " - " + Array.get(schedule.get(5), 1) + "\n";
        s += "\tSunday: " + Array.get(schedule.get(6), 0) + " - " + Array.get(schedule.get(6), 1) + "\n";
        
        return s;
    }
}
