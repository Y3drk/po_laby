package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;


public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        //String mvs;
        //Scanner sc = new Scanner(System.in);
        //System.out.print("Podaj ruchy zwierzaka: ");
        //mvs = sc.nextLine();
        //String[] mvsArray = mvs.split(" ");

        //laby 1
        //Direction[] mvs2 = change(args);

        //run(mvs2);

        // laby 2
        /*Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(position1.opposite());
        System.out.println(position1.upperRight(position2));
        System.out.println(position1.lowerLeft(position2));
        System.out.println(position1.precedes(position2));
        System.out.println(position1.follows(position2));
        System.out.println(position1.subtract(position2));
        System.out.println(position1.equals(position2));

        MapDirection n = MapDirection.NORTH;
        MapDirection s = MapDirection.SOUTH;
        MapDirection e = MapDirection.EAST;
        MapDirection w = MapDirection.WEST;
        System.out.println(n.toString());
        System.out.println(s.next());
        System.out.println(e.previous());
        System.out.println(w.toUnitVector());*/

        //laby3

        /*punkt 5
        Animal animal = new Animal();
        animal.move(RIGHT);
        animal.move(FORWARD);
        animal.move(FORWARD);
        animal.move(FORWARD);
        animal.move(FORWARD);
        System.out.println(animal);
        System.out.println(animal.isAt(new Vector2d(4,2)));

        //punkt 7
        Animal animal = new Animal();
        ArrayList<MoveDirection> mvs = OptionParser.parser(args);
        System.out.println(mvs);
        for (MoveDirection dir : mvs) {
                animal.move(dir);
        }
        System.out.println(animal);*/

        //laby4
        /*
        ArrayList<MoveDirection> directions = OptionParser.parser(args); //ctrlV z treści labów tylko zmiast parsowania na tablice robimy to na ArrayList
        System.out.println(directions);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.print(map);
        */

        //laby5
        /*ArrayList<MoveDirection> directions = OptionParser.parser(args);
        System.out.println(directions);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.print(map);*/


        //lab6 - test działania po zmianach na HashMap
        // w zasadzie oczekujemy, że wydarzy się to samo
        /*
        ArrayList<MoveDirection> directions = OptionParser.parser(args);
        System.out.println(directions);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.print(map);
        */


        //laby7
        /*try {
        ArrayList<MoveDirection> directions = OptionParser.parser(args);
        System.out.println(directions);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2),new Vector2d(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();


        System.out.print(map);
*/
        Application.launch(App.class, args);
/*
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
            System.exit(0);

        }
        System.out.println("system zakończył działanie");
*/
    }

    //laby1
    public static Direction[] change(String[] arr) {
        Direction[] mvs2 = new Direction[arr.length];
        int i = 0;
        for (String comm : arr) {
            Direction message = switch (comm) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> Direction.OTHER;
            };
            mvs2[i] = message;
            i ++;
        }
        return mvs2;
    }

    public static void run(Direction[] array) {
        System.out.println("START");
        for (Direction comm: array) {
            String message = switch (comm){
                case FORWARD -> "zwierzak idzie do przodu\n";
                case BACKWARD -> "zwierzak idzie do tyłu\n";
                case LEFT -> "zwierzak skręca w lewo\n";
                case RIGHT -> "zwierzak skręca w prawo\n";
                default -> "";
            };
            System.out.print(message);

        }
        System.out.println("STOP");
    }

}
