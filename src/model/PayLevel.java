package model;

import static model.EmployeeType.CASHIER;
import static model.EmployeeType.LIFEGUARD_INSTRUCTOR;
import static model.EmployeeType.SUPERVISOR;

/**
 * Created by susanchen on 2017-06-21.
 */
public enum PayLevel {
    SUP1(SUPERVISOR, 16.94),
    SUP2(SUPERVISOR, 17.53),
    SUP3(SUPERVISOR, 18.10),
    LG1(LIFEGUARD_INSTRUCTOR, 14.87),
    LG2(LIFEGUARD_INSTRUCTOR, 15.47),
    LG3(LIFEGUARD_INSTRUCTOR, 16.07),
    CAS1(CASHIER, 13.15),
    CAS2(CASHIER, 13.75),
    CAS3(CASHIER, 13.33);

    private double wage;
    private EmployeeType type;


    PayLevel(EmployeeType type, double wage) {
        this.type = type;
        this.wage = wage;
    }

    public double getWage() {
        return this.wage;
    }

    public EmployeeType getType() {
        return type;
    }

}
