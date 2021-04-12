/*
 */

package schedulegenerator;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleLogic {
    
    HashMap<Integer, Employee> employeesMap = new HashMap<Integer, Employee>();
    HashMap<Integer, Shift> shiftsMap = new HashMap<Integer, Shift>();
    Database db;
    
    public ScheduleLogic(Database db){
        this.db = db;
        
        for (int i = 1; i <= 6; i++){
            Employee employee = db.getEmployee(i);
            employeesMap.put(employee.getId(), employee);
        }
        
        for (int i = 1; i <= 5; i++){
            Shift shift = db.getShift(i);
            shiftsMap.put(shift.getId(), shift);
        }
        
    }
    
    public ArrayList<Employee> calcPotentialEmployees(int shiftId, ArrayList<Employee> currentEmployeesForDay){
        //For each shift in the day, find somebody to work it
        ArrayList<Employee> potentialEmployees = new ArrayList<Employee>(); 
        ArrayList<Employee> scheduleArray = currentEmployeesForDay; 

        //For each employee, choose the ones that can work the shift
        for (int i = 1; i <= employeesMap.size(); i++){

            //Get the current employee and his/her schedule
            Employee e = employeesMap.get(i);
            ArrayList<LocalTime[]> employeeSchedule = e.getSchedule();
            
            //Convert times into longs for comparisons
            long shiftStartTime = shiftsMap.get(shiftId).getStartTime().toSecondOfDay();
            long employeeStartTime = ((LocalTime)Array.get(employeeSchedule.get(0), 0)).toSecondOfDay();
            long shiftEndTime = shiftsMap.get(shiftId).getEndTime().toSecondOfDay();
            long employeeEndTime = ((LocalTime)Array.get(employeeSchedule.get(0), 1)).toSecondOfDay();
            
            //Choose employees who are available during that shift
            if ((employeeStartTime <= shiftStartTime) && (employeeEndTime >= shiftEndTime)){
                //Make sure to not include employees that already work that day
                if (!scheduleArray.contains(e)){
                    potentialEmployees.add(e);
                }
            
            }
        
        }

        return potentialEmployees;
    }
    
    public void convertScheduleToCSV(HashMap<Database.DAYOFWEEK, ArrayList<Employee>> schedule, String fileName){
        //Take the information from the hashmap and convert it to CSV format

        File csvOutputFile = new File(fileName);
        try (PrintWriter writer = new PrintWriter(csvOutputFile)){
            writer.println(" ,Shift 1,Shift 2,Shift 3,Shift 4,Shift 5");
            
            for (Database.DAYOFWEEK days : Database.DAYOFWEEK.values()) {
                String s = days.toString();
                
                for (Employee e : schedule.get(days)){
                    s += "," + e.getName();
                }
                
                writer.println(s);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
