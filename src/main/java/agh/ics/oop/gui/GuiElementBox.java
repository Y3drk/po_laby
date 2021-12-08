package agh.ics.oop.gui;

import agh.ics.oop.Grass;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class GuiElementBox {
    Image image;
    ImageView imageView;
    VBox verticalBox;

    public GuiElementBox(IMapElement lifeform){

    try {
        image = new Image(new FileInputStream(lifeform.imageAddress()));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    imageView = new ImageView(image);
    imageView.setFitHeight(30);
    imageView.setFitWidth(30);
    Label label;

    if (lifeform instanceof Grass) {
        label = new Label("Trawa");
    } else label = new Label("Z" + lifeform.getPosition().toString());

    verticalBox = new VBox(imageView, label);
    verticalBox.setAlignment(Pos.CENTER);

    }

}
