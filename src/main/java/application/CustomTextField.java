package application;

import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
	public CustomTextField() {
		getStyleClass().add("custom-text-field");
		this.setPrefSize(300, 40);
	}
}
