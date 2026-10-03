package application;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class IconButton extends Button {
	public IconButton(String text, String imagePath) {
		super(text);
		Image icon = Main.loadImage(imagePath);
		ImageView iconView = new ImageView(icon);
		iconView.setFitWidth(25);
		iconView.setFitHeight(25);
		this.setGraphic(iconView);
		getStyleClass().add("icon-button");
		this.setEffect(new DropShadow(10, Color.BLACK));
	}
}
