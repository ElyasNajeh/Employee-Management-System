package application;

import javafx.scene.image.Image;

public class HourlyEmployee extends Employee {
	private short hours;
	private float rate;

	public HourlyEmployee() {

	}

	public HourlyEmployee(String firstName, String lastName, String dateOfBirth, Address adress, String phoneNum,
			String email, String nationality, String designation, String education, Image empPhoto, short hours,
			float rate) {
		super(firstName, lastName, dateOfBirth, adress, phoneNum, email, nationality, designation, education, empPhoto);
		setHours(hours);
		setRate(rate);
	}

	public short getHours() {
		return hours;
	}

	public void setHours(short hours) {
		if (!validateHours(hours)) {
			return;
		}
		this.hours = hours;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		if (!validatenRate(rate)) {
			return;
		}
		this.rate = rate;
	}

	public boolean validateHours(short hours) {
		if (hours < 1 || hours > 288) {
			Alert.ErrorAlert("Error", "Hours must be between 1 and 288 ");
			return false;
		}
		return true;
	}

	public boolean validatenRate(float rate) {
		if (rate < 2.5 || rate > 6) {
			Alert.ErrorAlert("Error", "Rate must be between 2.5 and 6 JOD ");
			return false;
		}
		return true;
	}

	@Override
	public double Payment() {
		double monthSalary = hours * rate;
		if (hours > 140) {
			int extraHours = hours - 140;
			monthSalary += extraHours * rate * 0.7;
		}
		return monthSalary;
	}

	@Override
	public String toString() {
		return "HourlyEmployee [hours=" + hours + ", rate=" + rate + ", Payment()=" + Payment() + ", toString()="
				+ super.toString() + "]";
	}

}
