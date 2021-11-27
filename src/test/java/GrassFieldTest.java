import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldTest {
    @Test
    public void GrassFieldTest(){
        //test kolejnych metoda interfejsu IWorldMap,tak jak w RectangularClassTest połozymy sb parę zwierząt i sprawdzimy kilka rzeczy

        IWorldMap map = new GrassField(10);

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(6,1), new Vector2d(2,2)};

        //test place - sprawdzimy czy pierwsze 3 zwierzęta zostały umieszczone, a 4 nie
        ArrayList<Object> resPlace = new ArrayList<Object>();
        for (Vector2d posi: positions ) {
            Animal ani = new Animal(map,posi);
            boolean x = map.place(ani);
            resPlace.add(x);
        }

        assertEquals(resPlace.get(0),true);
        assertEquals(resPlace.get(1),true);
        assertEquals(resPlace.get(2),true);
        assertEquals(resPlace.get(3),false);

        //test isOccupied - sprawdzimy czy pola 3 zwierząt są zajęte
        for (int i = 0; i < positions.length-1; i++) {
            assertTrue(map.isOccupied(positions[i]));
        }

        //test canMoveTo() -sprawdzimy czy można wejść na pola na których stoją zwierzęta

        for (int i = 0; i < positions.length-1; i++) {
            assertFalse(map.canMoveTo(positions[i]));
        }

        //test Object at - sprawdzimy czy na kolejnych polach sa zwierzeta -> i czy sa one prirytetem nad trawą
        for (int i = 0; i < positions.length-1; i++) {
            Object something = map.objectAt(positions[i]);
            assertTrue(something instanceof Animal);
        }
    }
}
