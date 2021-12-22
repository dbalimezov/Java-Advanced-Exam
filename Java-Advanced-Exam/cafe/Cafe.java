package cafe;

import java.util.ArrayList;
import java.util.List;

public class Cafe {
    private List<Employee> employees;
    private String name;
    private int capacity;

    public Cafe(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employees.size() < capacity) {
            employees.add(employee);
        }
    }

    public boolean removeEmployee(String name) {
        return employees.removeIf(employee -> employee.getName().equals(name));
    }

    public Employee getOldestEmployee() {
        Employee employee = null;
        int max = Integer.MIN_VALUE;
        for (Employee e : employees) {
            if (e.getAge() > max) {
                max = e.getAge();
                employee = e;
            }
        }
        return employee;
    }

    public Employee getEmployee(String name) {
        return employees.stream().filter(e -> e.getName().equals(name))
                .findFirst().orElse(null);
    }

    public int getCount() {
        return employees.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Employees working at Cafe %s:", this.name)).append(System.lineSeparator());
        for (Employee employee : employees) {
            sb.append(employee.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
