package application;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

public abstract class Employee {
	AlertTypes Alert = new AlertTypes();
	private static int count = 1000;
	private String empNo;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private Address adress;
	private String phoneNum;
	private String email;
	private String nationality;
	private String designation;
	private String education;
	private Image empPhoto;
	private String photoPath;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String dateOfBirth, Address adress,
			String phoneNum, String email, String nationality, String designation, String education, Image empPhoto) {
		this.empNo = String.valueOf(count++);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setAddress(adress);
		setPhoneNum(phoneNum);
		setEmail(email);
		setNationality(nationality);
		setDesignation(designation);
		setEducation(education);
		setEmpPhoto(empPhoto);
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Employee.count = count;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.matches("[a-zA-Z]+")) {
			this.firstName = firstName;
		} else {
			Alert.ErrorAlert("Error", "First Name must be contain Characters only. ");
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {

		if (lastName.matches("[a-zA-Z]+")) {
			this.lastName = lastName;
		} else {
			Alert.ErrorAlert("Error", "Last Name must be contain Characters only. ");
		}
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(birthDate, currentDate).getYears();
		if (age >= 16) {
			this.dateOfBirth = dateOfBirth;
		} else {
			Alert.ErrorAlert("Error", "The Employee must be at least 16 years old. ");
		}
	}

	public Address getAdress() {
		return adress;
	}

	public void setAddress(Address adress) {
		this.adress = adress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		if (phoneNum.matches("^(059|056)\\d{6}$")) {
			this.phoneNum = phoneNum;
		} else {
			Alert.ErrorAlert("Error", "Phone Number must start with 059 or 056 and the length is 9. ");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
			this.email = email;
		} else {
			Alert.ErrorAlert("Error", "Invalid Email format. ");
		}

	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Image getEmpPhoto() {
		return empPhoto;
	}

	public void setEmpPhoto(Image empPhoto) {

		this.empPhoto = empPhoto;
	}
	public String getEmployeeType() {
		return getClass().getSimpleName();
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public abstract double Payment();

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", phoneNum=" + phoneNum + ", email=" + email + ", nationality=" + nationality
				+ ", designation=" + designation + ", education=" + education + ", empPhoto=" + empPhoto + "]";
	}

}
