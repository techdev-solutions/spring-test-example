package de.techdev.springtest.domain;

import de.techdev.springtest.AbstractDataOnDemand;

/**
 * @author Moritz Schulze
 */
public class EmployeeDataOnDemand extends AbstractDataOnDemand<Employee> {

    @Override
    protected int getExpectedElements() {
        return 2;
    }

    @Override
    public Employee getNewTransientObject(int i) {
        Employee employee = new Employee();
        employee.setName("name_" + i);
        employee.setSalary(i);
        return employee;
    }

}
