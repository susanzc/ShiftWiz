package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by susanchen on 2017-06-21.
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private EmployeeType type;
    private double seniorityHours;
    private boolean isStudent;
    private List<Shift> shifts;

    // Constructor: creates employee with name, type, true if student and
    // false otherwise, with no seniority hours or shifts
    public Employee(String name, EmployeeType type, boolean isStudent) {
        this.name = name;
        this.type = type;
        this.isStudent = isStudent;
        seniorityHours = 0.0;
        shifts = new ArrayList<>();

    }

    public String getName() {return this.name; }

    public EmployeeType getType() {return this.type;}

    public double getSeniorityHours() {return this.seniorityHours;}

    public boolean isStudent() {return this.isStudent;}

    public void setStudentStatus(boolean status) {this.isStudent = status;}

    public void setName(String name) {this.name = name;}

    public void setType(EmployeeType type) {this.type = type;}

    public void addShift(Shift s) {
        if (type == s.getType()) {
            shifts.add(s);
            //seniorityHours = seniorityHours + s.getHoursWorked();
            s.addEmployee(this);
        }
    }

    public void dropShift(Shift s) {
        if (shifts.contains(s)) {
            shifts.remove(s);
            //seniorityHours = seniorityHours - s.getHoursWorked();
            s.removeEmployee();
        }
    }

    public void clearShifts() {
        for (Shift s : shifts) {
            s.removeEmployee();
            s.setAvailability(true);
        }
        shifts.clear();
    }

    public void requestShift(Shift s) {
        if (type == s.getType())
            s.addRequest(this);
    }

    public PayLevel getPayLevel() {
        if (type == EmployeeType.SUPERVISOR) {
            if (seniorityHours < 400.00)
                return PayLevel.SUP1;
            if (seniorityHours < 800.00)
                return PayLevel.SUP2;
            else return PayLevel.SUP3;
        }
        if (type == EmployeeType.LIFEGUARD_INSTRUCTOR) {
            if (seniorityHours < 400.00)
                return PayLevel.LG1;
            if (seniorityHours < 800.00)
                return PayLevel.LG2;
            else return PayLevel.LG3;
        }
        if (type == EmployeeType.CASHIER) {
            if (seniorityHours < 400.00)
                return PayLevel.CAS1;
            if (seniorityHours < 800.00)
                return PayLevel.CAS2;
            else return PayLevel.CAS3;
        }
        else return PayLevel.LG1;
    }

    public double getPay(Date start, Date end) {
        double pay = 0.00;
        for (Shift s : shifts) {
            Date d = s.getDate();
            if (d.after(start) || d.equals(start)
                    && (d.before(end) || d.equals(end))) {
                double wage = getPayLevel().getWage();
                double shiftPay = s.getHoursWorked() * wage;
                pay = pay + shiftPay;
            }
        }
        return pay;
    }

    public boolean equals(Employee e) {
        return (name.equals(e.getName())
                && type == e.getType());
    }

    public boolean nameEquals(Employee e) {
        return (name.equals(e.getName()));
    }

    /*
    public boolean hasHigherSeniority(Employee e) {
        if (isStudent == e.isStudent()) {
            return (seniorityHours > e.getSeniorityHours());
        }

        if (isStudent && !e.isStudent)
            return true;

        return false;
    } */


    @Override
    public int compareTo(Employee e) {
        int e1 = (int) Math.round(seniorityHours * 100);
        int e2 = (int) Math.round(e.getSeniorityHours() * 100);
        return Integer.compare(e1, e2);
    }
}
