package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by susanchen on 2017-06-21.
 */
public class Shift {
    private double hoursWorked;
    private Date date;
    private EmployeeType type;
    private double prepTime = 0.17;
    private Employee employee;
    private boolean isAvailable;
    private List<Employee> pendingAdds;

    public Shift(double length, double breakTime, Date date, EmployeeType type, boolean hasLessons) {
        hoursWorked = length - breakTime;
        if (hasLessons) {
            hoursWorked = hoursWorked + prepTime;
        }
        this.date = date;
        this.type = type;

        isAvailable = true;
        pendingAdds = new ArrayList<>();
    }

    public double getHoursWorked() {return hoursWorked;}

    public Date getDate() {return date;}

    public EmployeeType getType() {return type;}

    public Employee getEmployee() {return employee;}

    public boolean getAvailability() {return isAvailable;}

    public List<Employee> getPendingAdds() {return pendingAdds;}

    public void clearPendingAdds() {pendingAdds.clear();}

    public void setAvailability(boolean isAvailable) {this.isAvailable = isAvailable;}

    public void addEmployee(Employee e) {
        employee = e;
        isAvailable = false;
    }

    public void removeEmployee() {
        employee = null;
        isAvailable = true;
    }

    public void addRequest(Employee e) {
        if (isAvailable)
        pendingAdds.add(e);
    }
}
