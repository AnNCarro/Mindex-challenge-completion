package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

    private String compensationId;
    private Employee employee;
    private Double salary;
    private Date effectiveDate;

    public Compensation() {}

    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    public String getCompensationId() {
        return this.compensationId;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
