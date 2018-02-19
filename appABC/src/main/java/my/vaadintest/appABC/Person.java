package my.vaadintest.appABC;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable, Cloneable {

	public Person() {
		
	}
	
	public Person(String firstName, String lastName, String title) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}

	private String firstName = "";

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String lastName = "";
	
	private String title = "";
}
