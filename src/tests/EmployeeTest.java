package tests;

import model.Employee;
import model.EmployeeType;



/**
 * Created by susanchen on 2017-06-21.
 */
public class EmployeeTest {
    private Employee testEmployee;

    @Before
    public void runBefore() {
        testEmployee = new Employee("Susan", EmployeeType.LIFEGUARD_INSTRUCTOR, true);
    }
}
