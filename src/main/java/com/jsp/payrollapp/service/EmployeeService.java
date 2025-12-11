package com.jsp.payrollapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsp.employeepayroll.entity.Employee;

@Service
public class EmployeeService {
	private final List<Employee> employees = new ArrayList<>();

	public List<Employee> getAll() {
		return employees;
	}

	public Employee getById(int id) {
		return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	}

	public Employee add(Employee e) {
		employees.add(e);
		return e;
	}

	public Employee update(int id, Employee newE) {
		Employee existing = getById(id);
		if (existing != null) {
			existing.setName(newE.getName());
			existing.setSalary(newE.getSalary());
		}
		return existing;
	}

	public boolean delete(int id) {
		return employees.removeIf(e -> e.getId() == id);
	}
}
