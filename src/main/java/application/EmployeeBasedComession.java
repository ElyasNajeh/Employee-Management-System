package application;

import javafx.scene.image.Image;

public class EmployeeBasedComession extends CommessionEmployee {
	private double basicSalary;

	EmployeeBasedComession() {

	}

	public EmployeeBasedComession(String firstName, String lastName, String dateOfBirth, Address adress,
			String phoneNum, String email, String nationality, String designation, String education, Image empPhoto,
			double soldItems, double basicSalary) {
		super(firstName, lastName, dateOfBirth, adress, phoneNum, email, nationality, designation, education,
				empPhoto, soldItems);
		setBasicSalary(basicSalary);
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		if (basicSalary < 0) {
			Alert.ErrorAlert("Error", "Basic Salary cannot be negative ");
			return;
		}
		this.basicSalary = basicSalary;
	}

	@Override
	public double Payment() {
		return basicSalary + super.Payment();
	}

	@Override
	public String toString() {
		return "EmployeeBasedComession [basicSalary=" + basicSalary + ", Payment()=" + Payment() + ", toString()="
				+ super.toString() + "]";
	}

}
