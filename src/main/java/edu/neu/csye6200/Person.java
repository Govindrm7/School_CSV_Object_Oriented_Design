package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Person {
	static List<Student> studentList = new ArrayList<>();
	static List<Student> newStudent = new ArrayList<>();

	public int id;
	public int age;
	public String firstName;
	public String lastName;
	public String parentFirstName;
	public String parentLastName;

	public Person(int id, int age, String firstName, String lastName, String parentFirstName, String parentLastName) {
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.parentFirstName = parentFirstName;
		this.parentLastName = parentLastName;

	}

	public Person() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getParentFirstName() {
		return parentFirstName;
	}

	public void setParentFirstName(String parentFirstName) {
		this.parentFirstName = parentFirstName;
	}

	public String getParentLastName() {
		return parentLastName;
	}

	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}

	@Override
	public String toString() {
		return id + " - " + age + "-" + firstName + " - " + lastName + " - " + parentFirstName + " - " + parentLastName;
	}

	public static class Student extends Person implements Comparable<Student> {
		public int StudentID;
		public double gpa;

		public Student(int id, int age, String firstName, String lastName, String parentFirstName,
				String parentLastName, int StudentID, double gpa) {
			super(id, age, firstName, lastName, parentFirstName, parentLastName);
			this.StudentID = StudentID;
			this.gpa = gpa;
		}

		public Student() {
			// TODO Auto-generated constructor stub
		}

		public int getStudentID() {
			return StudentID;
		}

		public void setStudentID(int studentID) {
			StudentID = studentID;
		}

		public double getGpa() {
			return gpa;
		}

		public void setGpa(double gpa) {
			this.gpa = gpa;
		}

		@Override
		public int compareTo(Student o) {
			return Integer.compare(getStudentID(), o.getStudentID());
		}

		@Override
		public String toString() {
			return "ID: " + id + " - " + "Age: " + age + " - " + "First Name: " + firstName + " - " + "Last Name: "
					+ lastName + " - " + "Parent FirstName: " + parentFirstName + " - " + "Parent LastName: "
					+ parentLastName + " - " + "Student ID: " + StudentID + " - " + "GPA: " + gpa;
		}
	}

	public static  class Teacher extends Person {
		
		public int hourlyWage;
		public Teacher(String firstName,int age,int hourlyWage)
		{
			System.out.println("Firstname: " 
		+ firstName + "," 
					+ "Age: " + age + ","
		+ "HourlyWage: " + hourlyWage);
		}
		public int getHourlyWage() {
			return hourlyWage;
		}

		public void setHourlyWage(int hourlyWage) {
			this.hourlyWage = hourlyWage;
		}

		public Teacher(int hourlyWage) {
			this.hourlyWage = hourlyWage;
		}

		@Override
		public String toString() {
			return "Teacher [hourlyWage=" + hourlyWage + "]";
		}
	}

	@SuppressWarnings("static-access")
	public static void Demo() {
		//Use this for teacher and student data
//		Student s1=new Student();
//		System.out.println(s1.studentList);
//		System.out.println("Teacher:");
//		Teacher t1=new Teacher("Peter",100,100);

		new Person().new fileUtility().readstd();
		Person Person = new Person();

		Person.sortStudentById();
		System.out.println("Sorted by ID:");
		Person.studentList.forEach(System.out::println);
		for (int i = 0; i < studentList.size(); i++)
			newStudent.add(studentList.get(i));

		Person.sortStudentByStudentID();
		System.out.println("Sorted by StudentID:");
		Person.studentList.forEach(System.out::println);
		for (int i = 0; i < studentList.size(); i++)
			newStudent.add(studentList.get(i));

		Person.sortStudentByLastName();
		System.out.println("Sorted by LastName:");
		Person.studentList.forEach(System.out::println);
		for (int i = 0; i < studentList.size(); i++)
			newStudent.add(studentList.get(i));

		Person.sortStudentByFirstName();
		System.out.println("Sorted by FirstName:");
		Person.studentList.forEach(System.out::println);
		for (int i = 0; i < studentList.size(); i++)
			newStudent.add(studentList.get(i));

		Person.sortStudentByGpa();
		System.out.println("Sorted by GPA:");
		Person.studentList.forEach(System.out::println);
		for (int i = 0; i < studentList.size(); i++)
			newStudent.add(studentList.get(i));

		try {
			new Person().new fileUtility().writestd();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addStudent(Student student) {
		studentList.add(student);
	}

	public void sortStudentById() {
		Collections.sort(studentList, Comparator.comparing(Person::getId));
	}

	public void sortStudentByStudentID() {
		Collections.sort(studentList, Comparator.comparingInt(o -> ((Student) o).getStudentID()));
	}

	public void sortStudentByFirstName() {
		Collections.sort(studentList, Comparator.comparing(Person::getFirstName));

	}

	public void sortStudentByLastName() {
		Collections.sort(studentList, Comparator.comparing(Person::getLastName));

	}

	public void sortStudentByGpa() {
		Collections.sort(studentList, Comparator.comparingDouble(o -> ((Student) o).getGpa()));
	}

	public class fileUtility {
		public List<Student> readstd() {
			Person Person = new Person();
			Scanner line;
			try {
				String csvFileName = "src/main/java/edu/neu/csye6200/data1.txt";
				line = new Scanner(new BufferedReader(new FileReader(csvFileName)));
				while (line.hasNextLine()) {
					String readLine = line.nextLine();
					Scanner sc = new Scanner(readLine);
					sc.useDelimiter(",");
					int id = sc.nextInt();
					int age = sc.nextInt();
					String firstName = sc.next();
					String lastName = sc.next();
					String parentFirstName = sc.next();
					String parentLastName = sc.next();
					int StudentID = sc.nextInt();
					double gpa = sc.nextDouble();
					Person.addStudent(new Person.Student(id, age, firstName, lastName, parentFirstName, parentLastName,
							StudentID, gpa));
					sc.close();
				}
				line.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return studentList;
		}

		public void writestd() throws IOException {
			String FileName = "src/main/java/edu/neu/csye6200/newdata.txt";
			FileWriter fw = new FileWriter(FileName);
			BufferedWriter bw = new BufferedWriter(fw);
			try {
				for (int i = 0; i < newStudent.size(); i++) {
					bw.append(newStudent.get(i).toString());
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			bw.close();
		}
	}
}
