
import agh.ics.oop.World.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void equalsTest(){
        assertTrue((new Vector2d(1, 1)).equals(new Vector2d(1, 1)));
    }

    @Test
    public void toStringTest(){
        assertEquals((new Vector2d(1,1)).toString(),"(1,1)");
    }

    @Test
    public void precedesTest(){
        assertTrue((new Vector2d(1,1).precedes(new Vector2d(2,2))));
    }

    @Test
    public void followsTest(){
        assertTrue((new Vector2d(1,1).follows(new Vector2d(-2,-2))));
    }

    @Test
    public void upperRightTest(){
        assertEquals(new Vector2d(0,2).upperRight(new Vector2d(2,0)), new Vector2d(2,2));
    }

    @Test
    public void lowerLeftTest(){
        assertEquals((new Vector2d(1,0)).lowerLeft(new Vector2d(0,1)),new Vector2d(0,0));
    }

    @Test
    public void addTest(){
        assertEquals((new Vector2d(1,0)).add(new Vector2d(0,1)), new Vector2d(1,1));
    }

    @Test
    public void substractTest(){
        assertEquals((new Vector2d(2,2)).subtract(new Vector2d(1,1)), new Vector2d(1,1));
    }

    @Test
    public void oppositeTest(){
        assertEquals((new Vector2d(2,2)).opposite(), new Vector2d(-2,-2));
    }

}
