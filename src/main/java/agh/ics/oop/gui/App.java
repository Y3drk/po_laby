package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;



public class App extends Application {
    protected GridPane board = new GridPane();
    protected Scene scene = new Scene(board, 400, 400);
    protected AbstractWorldMap mapInside;
    protected Vector2d bottomLeft;
    protected Vector2d upperRight;

    public void init(){

        try {
            var args = getParameters().getRaw().toArray(new String[0]);
            ArrayList<MoveDirection> directions = OptionParser.parser(args);
            System.out.println(directions);
            AbstractWorldMap map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2),new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            this.mapInside = map;
            System.out.println(map);

            Vector2d[] corners = mapInside.getCorners();
            this.upperRight = corners[1];
            this.bottomLeft = corners[0];

        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            System.exit(0);

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // !!!!!! TO DO
        board.getColumnConstraints().add(new ColumnConstraints(20));
        board.add( new Label ("y/x"), 0, 0);

        //System.out.println(bottomLeft);
        //System.out.println(upperRight);

        for (int i = 1; i <= upperRight.x - bottomLeft.x + 1; i++){
            Label axisLabel = new Label(Integer.toString(i + bottomLeft.x - 1));
            board.getColumnConstraints().add(new ColumnConstraints(20));
            board.add(axisLabel, i, 0);
            GridPane.setHalignment(axisLabel, HPos.CENTER);

        }

        for (int j = 1; j <= upperRight.y - bottomLeft.y + 1; j++){
            Label axisLabel = new Label(Integer.toString(upperRight.y - j + 1));
            board.add(axisLabel, 0, j);
            board.getRowConstraints().add(new RowConstraints(20));
            GridPane.setHalignment(axisLabel, HPos.CENTER);
        }
        for (int i = 1; i <= upperRight.x - bottomLeft.x + 1; i++) { //bład przeliczania
            for (int j = 1; j <= upperRight.y - bottomLeft.y + 1; j++){
                Vector2d testedPos = new Vector2d(i + bottomLeft.x - 1,upperRight.y - j + 1);
                if (mapInside.isOccupied(testedPos)) {
                    Label wow = new Label (mapInside.objectAt(testedPos).toString());
                    board.add(wow,i, j);
                    GridPane.setHalignment(wow, HPos.CENTER);

                } else board.add(new Label (" "),i, j);
            }
        }

        board.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
