package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertTypes {
	public void InfoAlert(String title, String messege) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle(title);
		infoAlert.setHeaderText(null);
		infoAlert.setContentText(messege);
		infoAlert.showAndWait();
	}

	public void ErrorAlert(String title, String messege) {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle(title);
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(messege);
		errorAlert.showAndWait();
	}

	public void WarningAlert(String title, String messege) {
		Alert warningAlert = new Alert(AlertType.WARNING);
		warningAlert.setTitle(title);
		warningAlert.setHeaderText(null);
		warningAlert.setContentText(messege);
		warningAlert.showAndWait();
	}

	public boolean ConfirmationAlert(String title, String messege) {
		Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
		confirmationAlert.setTitle(title);
		confirmationAlert.setHeaderText(null);
		confirmationAlert.setContentText(messege);
		Optional<ButtonType> result = confirmationAlert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}
}
