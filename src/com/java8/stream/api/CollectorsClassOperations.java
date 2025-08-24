package com.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsClassOperations {
	public static void main(String args[]) {
		
	List<Employee> employees=employeesList();
	
	
	//Collectors class:	introduced in JDK 8 ,as part of Stream API operations
	//Collector() method
	
	System.out.println("**********************Collect all employee names whose age is greater than 25**********************");
	System.out.println();
	List<String>names=employees.stream()
							   .filter(emp->emp.getAge()>25)
							   .map(Employee::getName)
							   .collect(Collectors.toList());
	System.out.println(names);
	System.out.println();

	
	System.out.println("**********************get unique department names**********************");
	System.out.println();
	Set<String> dept=employees.stream()
							  .map(Employee::getDepartment)
							  .collect(Collectors.toSet());
	System.out.println(dept);
	System.out.println();

	
	
	System.out.println("**********************collect employee ids and their salaries as a Map**********************");
	System.out.println();
	Map<Integer,Double> empIdandSalary=employees.stream()
												 .collect(Collectors.toMap(
												  Employee::getId, Employee::getSalary
												   ));
	
	System.out.println(empIdandSalary);
	System.out.println();

	
	//groupingBy()  & averagingDouble() methods
	System.out.println("**********************get average salary of each department**********************");
	System.out.println();
	Map<String,Double>avgSalaryDeptWise=employees.stream().collect(Collectors.groupingBy(
				Employee::getDepartment,
				Collectors.averagingDouble(Employee::getSalary)
			));
	System.out.println(avgSalaryDeptWise);
	System.out.println();

	
	//count() methods
	//get count of genderwise employees
	System.out.println("**********************Counting Number of Employees Gender wise**********************");
	System.out.println();
	Map<String,Long> countOfEmployeesGenderWise=employees.stream()
						.collect(Collectors.groupingBy(
								Employee::getGender,
								Collectors.counting()
								));
	System.out.println(countOfEmployeesGenderWise);
	System.out.println();
	
	
	//Summing-> gives you sum of all values
	System.out.println("**********************Summing employees salary**********************");
	System.out.println();
	System.out.println(employees.stream().collect(Collectors.summingDouble(Employee::getSalary)));
	System.out.println();
	
	//Summarizing -> gives you all the info like min value, max value,sum,average ..... etc use summarizing
	System.out.println("**********************Summarizing employees salary**********************");
	System.out.println();
	System.out.println(employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary)));
	System.out.println();
	
	//maxBy() method
	System.out.println("**********************Finding Details of Employee whose age is max **********************");
	System.out.println();
	System.out.println(employees.stream().collect(Collectors.maxBy((emp1,emp2)->emp1.getAge()-emp2.getAge())).get());
	System.out.println();
	
	
	//joining(CharSequence delimiter)
	System.out.println("**********************Joining all department names with Delimiters ::: **********************");
	System.out.println();
	String employeeJoinsWithDelimiter=employees.stream().map(Employee::getDepartment).collect(Collectors.joining(":::"));
	System.out.println(employeeJoinsWithDelimiter);
	System.out.println();
	
	
	//joining()
	System.out.println("**********************Joining all department names without any Delimiters **********************");
	System.out.println();
	String employeeJoinsWithoutDelimiter=employees.stream().map(Employee::getDepartment).collect(Collectors.joining());
	System.out.println(employeeJoinsWithoutDelimiter);
	System.out.println();
		
		
	//joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
	System.out.println("**********************Joining all department names with Delimiters ::: Prefix < and Suffix > **********************");
	System.out.println();
	String employeeJoinsWithDelimiterandPrefixandSuffix=employees.stream().map(Employee::getDepartment).collect(Collectors.joining(":::", "<", ">"));
	System.out.println(employeeJoinsWithDelimiterandPrefixandSuffix);
	System.out.println();
	
}
	public static List<Employee> employeesList(){
		List<Employee> empList=new ArrayList<>();
		//(int id, String name, String gender, int age, String city, String department, int yearOfJoining,double salary)
		empList.add(new Employee(1,"Akash","male",21,"rbl","cs",2026,200000));
		empList.add(new Employee(3, "Rohan", "male", 23, "mumbai", "mech", 2024, 180000));
		empList.add(new Employee(2, "Sneha", "female", 22, "pune", "it", 2025, 220000));
		empList.add(new Employee(8, "Divya", "female", 30, "kolkata", "cs", 2026, 240000));
		empList.add(new Employee(5, "Amit", "male", 25, "bangalore", "cs", 2025, 250000));
		empList.add(new Employee(7, "Karan", "male", 26, "chennai", "civil", 2024, 170000));
		empList.add(new Employee(6, "Neha", "female", 22, "hydrabad", "it", 2023, 230000));
		empList.add(new Employee(4, "Priya", "female", 24, "delhi", "ece", 2026, 210000));
		
		return empList;
	}
}
