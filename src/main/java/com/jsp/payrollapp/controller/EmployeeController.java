package com.jsp.payrollapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.employeepayroll.entity.Employee;
import com.jsp.employeepayroll.service.EmployeeService;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@GetMapping
	public List<Employee> all() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> byId(@PathVariable int id) {
		Employee e = service.getById(id);
		return e == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
	}

	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody Employee e) {
		if (service.getById(e.getId()) != null) {
			return ResponseEntity.status(409).build();
		}
		return ResponseEntity.ok(service.add(e));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee e) {
		Employee updated = service.update(id, e);
		return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		boolean removed = service.delete(id);
		return removed ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
	}

}
