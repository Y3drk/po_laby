import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static agh.ics.oop.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {
@Test
    public void animalTest(){
    //lab3 testy
    /*Animal bobby = new Animal();
    //test interpretacji danych wejsciowych
    String[] inp = {"l", " ", "u", "forward", "uf", "b","right","left","Liverpool", "f","Honda", "r","backward"};
    ArrayList<MoveDirection> res = OptionParser.parser(inp);

    ArrayList<MoveDirection> test = new ArrayList<>();
    test.add(LEFT);
    test.add(FORWARD);
    test.add(BACKWARD);
    test.add(RIGHT);
    test.add(LEFT);
    test.add(FORWARD);
    test.add(RIGHT);
    test.add(BACKWARD);

    assertEquals(res,test);

    //test orientacji 1 - domyślnie zorientowany jest na NORTH
    bobby.move(LEFT);
    assertEquals(bobby.getOrientation(), MapDirection.WEST);

    //test orientacji 2
    bobby.move(RIGHT);
    bobby.move(RIGHT);
    assertEquals(bobby.getOrientation(), MapDirection.EAST);

    //test orientacji 3
    bobby.move(RIGHT);
    assertEquals(bobby.getOrientation(), MapDirection.SOUTH);

    //test orientacji 4
    bobby.move(LEFT);
    bobby.move(LEFT);
    assertEquals(bobby.getOrientation(), MapDirection.NORTH);

    //test przemieszczania 1
    bobby.move(FORWARD);
    assertEquals(bobby.getPosition(), new Vector2d(2,3));

    //test przemieszczania 2
    bobby.move(BACKWARD);
    assertEquals(bobby.getPosition(), new Vector2d(2,2));

    //testy wychodzenia poza mapę,
    // w kolejnych fazach bobby przejdzie do wymienionych pól i sprobuje opuścić plansze w wymienionym kierunku
    // faza1, (2,4), Północ
    // faza2, (0,4), Zachód
    // faza3, (0,0), Południe
    // faza4, (4,0), Wschód

    //faza1
    bobby.move(FORWARD);
    bobby.move(FORWARD); //jestemy na poz. (2,4)
    bobby.move(FORWARD); //proba przekroczenia
    assertEquals(bobby.getPosition(), new Vector2d(2,4));

    //faza2
    bobby.move(LEFT); //obrót aby isc na zachód
    bobby.move(FORWARD);
    bobby.move(FORWARD); //jestemy na poz. (0,4)
    bobby.move(FORWARD); //proba przekroczenia
    assertEquals(bobby.getPosition(), new Vector2d(0,4));

    //faza3
    bobby.move(LEFT); //obrót aby isc na południe
    bobby.move(FORWARD);
    bobby.move(FORWARD);
    bobby.move(FORWARD);
    bobby.move(FORWARD); //jestemy na poz. (0,0)
    bobby.move(FORWARD); //proba przekroczenia
    assertEquals(bobby.getPosition(), new Vector2d(0,0));

    bobby.move(LEFT); //obrót aby isc na wschód
    bobby.move(FORWARD);
    bobby.move(FORWARD);
    bobby.move(FORWARD);
    bobby.move(FORWARD); //jestemy na poz. (4,0)
    bobby.move(FORWARD); //proba przekroczenia
    assertEquals(bobby.getPosition(), new Vector2d(4,0));
    */
}
@Test
    public void l4AnimalsTest(){
    //część wykomentowana bo zmieniamy strukturę danych na hashMape
    //inicjalizacja próby
    try {
        ArrayList<MoveDirection> directions = OptionParser.parser(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "b", "f", "f", "b", "f", "l", "f", "f", "r"});
        //OptionParser.parser() był już sprawdzany na poprzednich laboratoriach stąd wiemy, że działa poprawnie
        IWorldMap map = new RectangularMap(8, 6);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(6, 1), new Vector2d(2, 2)};
        IEngine engine = new SimulationEngine(directions, map, positions);

        //2 kolejne sprawdzenia dotycza konstruktora SimulationEngine
        RectangularMap mapTest = (RectangularMap) map;
        ArrayList<Animal> testObjects = (ArrayList<Animal>) mapTest.getAnimalList();
        //sprawdzenie czy wszystkie zwierzęta zostały położone poprawnie (czy jedno które spadłoby na inne pozostało ideą :) )
        assertEquals(testObjects.size(), 3);

        // spr. czy wszystkie zwierzęta po położeniu na mapie mają orientację NORTH
        for (int i = 0; i < testObjects.size(); i++) {
            assertEquals(testObjects.get(i).getOrientation(), MapDirection.NORTH);
        }

        //Nastąpią teraz 2 testy metody run()
        engine.run();
        // pierwszy test polega na sprawdzeniu czy ostateczne pozycje zwierzat będa poprawne
        // drugi test sprawdzi poprawność ich orientacji
        //1
        assertEquals(testObjects.get(0).getPosition(), new Vector2d(2, 5));
        assertEquals(testObjects.get(1).getPosition(), new Vector2d(3, 4));
        assertEquals(testObjects.get(2).getPosition(), new Vector2d(8, 1));

        //2
        assertEquals(testObjects.get(0).getOrientation(), MapDirection.NORTH);
        assertEquals(testObjects.get(1).getOrientation(), MapDirection.EAST);
        assertEquals(testObjects.get(2).getOrientation(), MapDirection.EAST);
    }
    catch (IllegalArgumentException ex) {
        System.out.println(ex);
    }
}
}
