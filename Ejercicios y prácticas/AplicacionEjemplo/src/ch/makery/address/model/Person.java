package ch.makery.address.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty street;
	private SimpleStringProperty city;
	private SimpleIntegerProperty pc;
	private SimpleStringProperty birthday;
	
	public Person(String fName, String lName, String street, String city, int pc, String birthday) {
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.pc = new SimpleIntegerProperty(pc);
		this.birthday = new SimpleStringProperty(birthday);
	}
	public Person(String fName, String lName) {
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
	}
	public Person(){

	}
	public String getFirstName() {
        return firstName.get();
    }
	public void setFirstName(String fName) {
        firstName.set(fName);
    }
	public String getLastName() {
        return lastName.get();
    }
	public void setLastName(String lName) {
        lastName.set(lName);
    }
	public String getStreet() {
		return street.get();
	}
	public void setStreet(String street) {
        this.street.set(street);
    }
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
        this.city.set(city);
    }
	public int getPC() {
		return pc.get();
	}
	public void setPC(int pc) {
        this.pc.set(pc);
    }
	public String getBirthday() {
		return birthday.get();
	}
	public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }
	
}
