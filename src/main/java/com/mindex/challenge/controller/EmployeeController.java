package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    /*
    I placed this in here as it doesn't make sense for me for ReportingStructure to have its own file
    ReportingStructure has only one functionality and that is tied entirely to employee.
    I understand the purpose for this test, but in my opinion
    directReports should be a functionality added to the Employee class.
    As for the logic of getting the NumberOfReports, due to the limitations of the json database,
    and me not wanting to perpetuate employee service logic into the object class, I placed it here as well
     */
    @GetMapping("/employee/{id}/reports")
    public ReportingStructure directReports(@PathVariable String id) {
        LOG.debug("Received employee direct report request for id [{}]", id);

        ArrayList<Employee> allReports = new ArrayList<>();

        Employee employee = employeeService.read(id);
        Integer numberOfReports = getNumberOfReports(employee);

        return new ReportingStructure(employee, numberOfReports);
    }

    public Integer getNumberOfReports(Employee employee) {

        ArrayList<Employee> allReports = new ArrayList<>();

        return getDirectReports(employee.getDirectReports(), allReports);
    }

    private Integer getDirectReports(List<Employee> reports, List<Employee> allReports) {

        if (reports != null) {
            allReports.addAll(reports);
            reports.forEach(report -> {
                Employee employee = employeeService.read(report.getEmployeeId());
                getDirectReports(employee.getDirectReports(), allReports);

            });

        }

        return allReports.size();
    }
}
