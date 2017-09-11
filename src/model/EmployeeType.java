package model;

/**
 * Created by susanchen on 2017-06-21.
 */
public enum EmployeeType {
    LIFEGUARD_INSTRUCTOR("Lifeguard/Instructor"),
    SUPERVISOR("Shift Supervisor"),
    CASHIER("Cashier Attendant");

    private String displayName;

    EmployeeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {return this.displayName;}
}
