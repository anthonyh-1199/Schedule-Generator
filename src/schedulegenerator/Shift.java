/*
 */

package schedulegenerator;

import java.time.LocalTime;

public class Shift {
    
    private int id;
    private LocalTime startTime, endTime;
    
    public Shift(int id, LocalTime startTime, LocalTime endTime){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    //Getter methods
    public int getId(){
        return id;
    }
    
    public LocalTime getStartTime(){
        return startTime;
    }
    
    public LocalTime getEndTime(){
        return endTime;
    }
    
    //Setter methods
    public void setId(int id){
        this.id = id;
    }
    
    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }
    
    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
    
    //toString method
    @Override
    public String toString(){
        String s = "Shift " + this.id + ": " + startTime + " - " + endTime;
        return s;
    }
}
