package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, Animal> animals;

    public AbstractWorldMap() {
        this.animals = new LinkedHashMap<>();
    }


    public abstract Vector2d[] getCorners();

    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        Vector2d[] corners = this.getCorners();
        return drawing.draw(corners[0], corners[1]);
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        Object something = objectAt(pos);
        if (something instanceof Animal) {
            throw new IllegalArgumentException ("Field: " + pos + " is already occupied!");
        } else {
            animal.addObserver(this);
            animals.put(pos, animal);
            return true;
        }
    }

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animals.get(oldPosition);

        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }
}
