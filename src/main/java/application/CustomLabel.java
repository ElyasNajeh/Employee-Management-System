package application;

import javafx.scene.control.Label;

public class CustomLabel extends Label {
	public CustomLabel(String text) {
		super(text);
		getStyleClass().add("custom-label");
	}
}
