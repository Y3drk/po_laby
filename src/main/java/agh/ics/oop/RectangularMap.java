package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    public static Vector2d bottomLeft = new Vector2d(0,0);
    public int width;
    public int height;
    public ArrayList<Animal> animals = new ArrayList<>();


    public RectangularMap(int w, int h){
        if (w < 0 || h < 0){
            throw new IllegalArgumentException("Sizes must be > 0" + w + h); //zamiast tego można zignorować
        }
        else {
            this.height = h;
            this.width = w;
        }
    }
    public Vector2d[] getCorners(){
        Vector2d[] corners = {bottomLeft, new Vector2d(width,height)};
        return corners;
    }

    /*@Override
    public String toString(){
        MapVisualizer drawing = new MapVisualizer(this);
        Vector2d[] corners = this.getCorners();
        return drawing.draw(corners[0],corners[1]);
    }*/

    public ArrayList<Animal> getAnimalList(){
        return this.animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.width >= position.x && this.height >= position.y && position.x >= 0 && position.y >= 0 && !isOccupied(position);
    }

    /*@Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        Object something = objectAt(pos);
        if (something instanceof Animal) {
            return false;
        } else {
            animals.add(animal);
            return true;
        }
    }*/

    /*@Override
    public boolean isOccupied(Vector2d position) {
        for (Animal avatar: animals) {
            Vector2d pos = avatar.getPosition();
            if(pos.x == position.x && pos.y == position.y) {
                return true;
            }
            }
        return false;
    }*/

    /*@Override
    public Object objectAt(Vector2d position) {
        for (Animal creature: animals)
        {
            Vector2d pos = creature.getPosition();
            if (pos.equals(position)) return creature;
        }
        return null;
    }*/
}
