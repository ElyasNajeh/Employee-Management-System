package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BackButtonHandler implements EventHandler<ActionEvent> {
	public Stage stagetoClose;

	public BackButtonHandler(Stage stagetoClose) {
		this.stagetoClose = stagetoClose;
	}

	@Override
	public void handle(ActionEvent o) {
		stagetoClose.close();

	}

}
