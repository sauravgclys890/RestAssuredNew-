package com.restapi.JavaBean;

import java.util.List;

public class Employee {
	
	private String name;
	private int salary;
	private int age;
	private List<Employee> employeeobj;
	
	public Employee(){
		
	}
	
	public Employee(String name, int salary, int age){
		this.name=name;
		this.salary=salary;
		this.age=age;
	}
	
	public String getName(){
		return name;
	}
	
	public int getSalary(){
		return salary;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setEmployeeObject(List<Employee> employeeobj){
		this.employeeobj=employeeobj;
	}
	
	public List<Employee> getEmployeeObject(){
		return employeeobj;
	}
	
	
	public String toString(){
		return "{" +
                "name:" + name +
                ", salary:" + salary +
                ", age:" + age +
                "}";
		
		
	}

}
