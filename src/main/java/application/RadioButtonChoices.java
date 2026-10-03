package application;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RadioButtonChoices {
	public RadioButton b1;
	public RadioButton b2;
	public RadioButton b3;
	public RadioButton b4;
	public RadioButton b5;
	ToggleGroup educationGroup = new ToggleGroup();

	public RadioButtonChoices() {

		b1 = new RadioButton("PhD");
		b2 = new RadioButton("Master");
		b3 = new RadioButton("B.A");
		b4 = new RadioButton("Secondary School");
		b5 = new RadioButton("Primary School");

		b1.setToggleGroup(educationGroup);
		b2.setToggleGroup(educationGroup);
		b3.setToggleGroup(educationGroup);
		b4.setToggleGroup(educationGroup);
		b5.setToggleGroup(educationGroup);

	}

}
