package application;

import javafx.scene.control.ComboBox;

public class EmployeeComboBox extends ComboBox<String> {
	public EmployeeComboBox() {
		this.getItems().addAll("Hourly Employee", "Salaried Employee", "Commession Employee", "Commession Based Employee");
		this.setPromptText("Select Employee Type :");
		this.setPrefWidth(300);
		this.setPrefHeight(20);
	}

}
