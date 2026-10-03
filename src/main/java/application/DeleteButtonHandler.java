package application;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DeleteButtonHandler implements EventHandler<ActionEvent> {
	private final List<Employee> employeeList;
	private final int currentIndex;
	private final AlertTypes alert;

	public DeleteButtonHandler(List<Employee> employeeList, int currentIndex, AlertTypes alert) {
		this.employeeList = employeeList;
		this.currentIndex = currentIndex;
		this.alert = alert;
	}

	@Override
	public void handle(ActionEvent o) {
		if (employeeList.isEmpty()) {
			alert.ErrorAlert("Error", "No employee is available to delete.");
			return;
		}
		if (currentIndex < 0 || currentIndex >= employeeList.size()) {
			alert.ErrorAlert("Error", "The selected employee is no longer available.");
			return;
		}
		boolean confirmed = alert.ConfirmationAlert("Confirmation", "Are you sure you want to delete this employee?");

		if (!confirmed) {
			return;
		}
		employeeList.remove(currentIndex);
		alert.InfoAlert("Success", "Employee deleted successfully.");
	}

}
