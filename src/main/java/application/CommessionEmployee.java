package application;

import javafx.scene.image.Image;

public class CommessionEmployee extends Employee {
	private double soldItems;

	CommessionEmployee() {

	}

	public CommessionEmployee(String firstName, String lastName, String dateOfBirth, Address adress,
			String phoneNum, String email, String nationality, String designation, String education, Image empPhoto,
			double soldItems) {
		super(firstName, lastName, dateOfBirth, adress, phoneNum, email, nationality, designation, education,
				empPhoto);
		setSoldItems(soldItems);
	}

	public double getSoldItems() {
		return soldItems;
	}

	public void setSoldItems(double soldItems) {
		if (soldItems < 0) {
			Alert.ErrorAlert("Error", "Sold Items cannot be Negative ");
			return;
		}

		this.soldItems = soldItems;
	}

	@Override
	public double Payment() {
		if (soldItems >= 2800 && soldItems <= 3775) {
			return soldItems * 0.03;
		} else if (soldItems > 3775) {
			return soldItems * 0.05;
		} else {
			return soldItems * 0.015;
		}
	}

	@Override
	public String toString() {
		return "CommessionEmployee [soldItems=" + soldItems + ", Payment()=" + Payment() + ", toString()="
				+ super.toString() + "]";
	}

}
