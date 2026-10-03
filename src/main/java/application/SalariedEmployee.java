package application;

import javafx.scene.image.Image;

public class SalariedEmployee extends Employee {
	private double annualSalary;

	SalariedEmployee() {

	}

	public SalariedEmployee(String firstName, String lastName, String dateOfBirth, Address adress,
			String phoneNum, String email, String nationality, String designation, String education, Image empPhoto,
			double annualSalary) {
		super(firstName, lastName, dateOfBirth, adress, phoneNum, email, nationality, designation, education,
				empPhoto);
		setAnnualSalary(annualSalary);
	}

	public double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(double annualSalary) {
		if (!ValidateSalary(annualSalary)) {
			return;
		}
		this.annualSalary = annualSalary;
	}

	public boolean ValidateSalary(double annualSalary) {
		if (annualSalary < 4075 || annualSalary > 25000) {
			Alert.ErrorAlert("Error", " Annual Salary must be between 4075 and 25000 ");
			return false;
		}
		return true;
	}

	@Override
	public double Payment() {
		return annualSalary / 12;
	}

	@Override
	public String toString() {
		return "SalariedEmployee [annualSalary=" + annualSalary + ", Payment()=" + Payment() + ", toString()="
				+ super.toString() + "]";
	}

}
