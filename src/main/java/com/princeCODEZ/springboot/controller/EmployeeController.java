package com.princeCODEZ.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.princeCODEZ.springboot.model.Employee;
import com.princeCODEZ.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// list of employees method
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("employeeList", employeeService.getAllEmployees());
		return "index";
		
	}
	//Handler method for save employee
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model){
		//create a new employee model to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "newEmployee";
	}
	//save employee method
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//saveEmployee to db
		employeeService.saveEmployee(employee);
		return "redirect:/";	
		
	}
	//Handler method for update employee 
	@GetMapping("/showFormEmployeeUpdate/{id}")
	public String showFormEmployeeUpdate(@PathVariable("id") Long id, Model model) {
		//get employee from service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model to pre-populate the form
		model.addAttribute("employee", employee);
		return "updateEmployee";
		
	}
	
	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployeeUpdate(@PathVariable("id") Long id) {
		// call deleteEmployee from service layer
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
		
		
		
	}

}
