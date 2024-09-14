package com.mindex.challenge.data;

import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportingStructure {

    @Autowired
    private EmployeeService employeeService;
    private Employee employee;
    private Integer numberOfReports;

    public ReportingStructure(Employee employee, Integer numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }
}
