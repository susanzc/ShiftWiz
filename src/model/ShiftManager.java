package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by susanchen on 2017-06-21.
 */
public class ShiftManager {
    private static ShiftManager instance;

    private List<Employee> employees;
    private List<Shift> shifts;

    private ShiftManager() {
        this.employees = new ArrayList<>();
        this.shifts = new ArrayList<>();
    }

    public static ShiftManager getInstance() {
        if(instance == null) {
            instance = new ShiftManager();
        }

        return instance;
    }

    public void clearEmployees() {
        for (Employee e : employees)
            e.clearShifts();
        employees.clear();
    }

    public void addEmployee(Employee e) {
        if (!employees.contains(e))
        employees.add(e);
    }

    public void removeEmployee(Employee e) {
        if (employees.contains(e)) {
            employees.remove(e);
            e.clearShifts();
        }
    }

    // sorts employee list by seniority
    public List<Employee> getSortedList() {
        Collections.sort(employees);
        return employees;
    }

    public void clearShifts() {
        for (Shift s : shifts) {
            s.removeEmployee();
        }
        shifts.clear();
    }

    public void addShift(Shift s) {
        if (!shifts.contains(s))
        shifts.add(s);
    }

    public void removeShift(Shift s) {
        if (shifts.contains(s)) {
            s.removeEmployee();
            shifts.remove(s);
        }
    }

    public void approveAddRequest(Shift s) {
        List<Employee> pendingAdds = s.getPendingAdds();
        Collections.sort(pendingAdds);
        Employee e = pendingAdds.get(0);
        e.addShift(s);
        s.clearPendingAdds();
    }

    public double getEmployeePaycheck(Employee e, Date start, Date end) {
        double paycheck = 0.00;
        List<Employee> combined = new ArrayList<>();
        for (Employee next : employees) {
            if (next.nameEquals(e)) {
                combined.add(next);
            }
        }
        for (Employee next : combined) {
            paycheck = paycheck + next.getPay(start, end);
        }

        return paycheck;
    }

}
