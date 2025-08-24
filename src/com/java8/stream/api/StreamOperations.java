package com.java8.stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamOperations {

	
	public static void main(String args[]) {
		List<Employee> employees=employeesList();
		
		//Stream Operations
		
		//Requirement: collect all employee's name as list 
		
		//Old method
		List<String> empNames=new ArrayList<>();
		for(Employee emp:employees) {
			empNames.add(emp.getName());
		}
		System.out.println(empNames);//Printing names list
		
		
		System.out.println("**************Sequential Stream************************");

		System.out.println("**************Stream() method************************");

		//Stream method
		Stream<Employee> empStream=employees.stream();
		/* 								Source->IntermediateOperation ->TerminalOperation    (Stream Pipeline)
											|		  				|      |
		    								V			   			V	   V             */
		List<String> listOfEmployeeNames=empStream.map(emp->emp.getName()).toList(); //added in java 16
		System.out.println(listOfEmployeeNames);
		
		//filter method in stream api
		//requirement: getting employee name list whose age is greater than 22
		System.out.println("**************filter() method************************");

		List<String> listOfEmployeeNames1=employees
										  .stream()//we are creating new stream api obj because we can't use one stream api object more than one time,if not do so you will get illegalArgumentException 
										  .filter(emp->emp.getAge()>22)//returns stream obj of employee type (Intermediate operation)
										  .map(emp->emp.getName())//returns stream obj of emplloyee type (Intermediate operation)
										  .toList();//return string list of employee name (Terminal operation)
		System.out.println(listOfEmployeeNames1);
		
		System.out.println("**************distinct() method & forEach() method************************");
		

		//Print all cities of employees
		employees.stream()//stream obj
				 .map(emp->emp.getCity())//intermediate operation
				 .distinct()//intermediate operation
				 .forEach(System.out::println);//terminal operation
		
		//Get count of employees whose salary >20000
		System.out.println("**************count() method************************");

		System.out.println(employees.stream()//stream obj
				 .filter(emp->emp.getSalary()>200000)//Intermediate operation
				 .count());//Terminal operation
		
		System.out.println("**************limit() method************************");

		//Get first three employees object as a list
		List<Employee> firstThreeEmp=employees.stream()
				 .limit(3)
				 .toList();
		
		System.out.println(firstThreeEmp);
		
		System.out.println("**************skip() method************************");

		
		//Get list of employee objects except first 3 employees
		List<Employee> SkipFirstThreeEmp=employees.stream()
										 		  .skip(3)//exclude first three emp objects 
										 		  .toList();
		SkipFirstThreeEmp.forEach(System.out::println);
		
		
		System.out.println("**************anyMatch() method************************");

		//anyMatch()
		//verify any employee<18
		System.out.println(employees.stream().anyMatch(emp->emp.getAge()<28));
		
		
		System.out.println("**************allMatch() method************************");

		//allMatch()
		//check every employee age is less than 28 if true returns true else false
		System.out.println(employees.stream().allMatch(emp->emp.getAge()<28));
		
		
		System.out.println("**************noneMatch() method************************");

		//noneMatch()
		//if anyone is matching returns false
		//if none is matching returns true
		System.out.println(employees.stream().noneMatch(emp->emp.getAge()<28));
		
		
		System.out.println("**************findAny() method************************");

		//findAny()
		//returns one value from out of all values
		Employee emp=employees.stream()
							  .findAny()
							  .get();
		System.out.println(emp);
		
		System.out.println("**************findFirst() method************************");

		//findFirst()
		//always returns first value out of all values
		Employee emp1=employees.stream()
							  .findFirst()
							  .get();
		System.out.println(emp1);
		
		System.out.println("**************sorted() method************************");

		//sorted()
		//req: get employees ids in sorted order
		List<String> empSorted=employees.stream()
										  .map(empl->empl.getName())
										  .sorted()//returns stream of integers in sorted order
										  .toList();
		empSorted.stream().forEach(System.out::println);
		
		
		System.out.println("**************sorted(Comparator) method************************");

		//sorted(): comparator arg
		//define sorting based on employees ids
		//sort employee objects
		List<Employee>employeesList= employees.stream().sorted((e1,e2)->e1.getId()-e2.getId()).toList();
		employeesList.stream().forEach(System.out::println);
		System.out.println();
		
		System.out.println("**************min(Comparator) method************************");

		//min()
		//minimum salary employees details
		Employee employeeSalaryMin=employees.stream().min((e1,e2)->(int)(e1.getSalary()-e2.getSalary())).get();
		System.out.println(employeeSalaryMin);
		System.out.println();
		System.out.println("**************max(Comparator) method************************");

		//max()
		//max salary employees details
		Employee employeeSalaryMax=employees.stream().max((e1,e2)->(int)(e1.getSalary()-e2.getSalary())).get();
		System.out.println(employeeSalaryMax);
		System.out.println();
		
		System.out.println("**************average()  & mapToDouble() method************************");

		//average()
		//average salary of employees
		//mapToDouble(): this doublestream contains only double values . 
		double avgSalary=employees.stream().mapToDouble(empl->empl.getSalary()).average().getAsDouble();
		System.out.println(avgSalary);
		
		//average()
		//average age of employees
		//mapToInt(): this intStream contains only integer values, similarly we have mapToLong() too
		double avgAge=employees.stream().mapToInt(emp2->emp2.getAge()).average().getAsDouble();
		System.out.println(avgAge);
	
		System.out.println("**************peek() method************************");

		//peek()
		List<Employee>empByYear= employees.stream()
										  .peek(System.out::println)//getting streamed data one by one and tracing it
										  .filter(emp3->emp3.getYearOfJoining()>2015)
										  .toList();
		System.out.println(empByYear);
	
		//Parallel Stream
		System.out.println("**************Parallel Stream************************");
		//peek()
		List<Employee>empByYear1= employees.parallelStream()
										  .peek(System.out::println)//getting streamed data one by one and tracing it
										  .filter(emp3->emp3.getYearOfJoining()>2015)
										  .toList();
		System.out.println(empByYear1);
	
	
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
