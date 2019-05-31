package com.megatravel.DemoService.model;

public class Student {
	
	Long id;
	String firstName;
	String lastName;
	int age;
	
	public Student() { }

	public Student(Long id, String firstname, String lastName, int age) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
}
