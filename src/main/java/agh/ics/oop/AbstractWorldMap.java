package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    public ArrayList<Animal> animals;

    public AbstractWorldMap(){
        this.animals = new ArrayList<>();
    }


    public abstract Vector2d[] getCorners();

    public String toString(){
        MapVisualizer drawing = new MapVisualizer(this);
        Vector2d[] corners = this.getCorners();
        return drawing.draw(corners[0],corners[1]);
    }

    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        Object something = objectAt(pos);
        if (something instanceof Animal) {
            return false;
        } else {
            animals.add(animal);
            return true;
        }
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal avatar : animals) {
            Vector2d pos = avatar.getPosition();
            if (pos.x == position.x && pos.y == position.y) {
                return true;
            }
        }

        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal creature: animals)
        {
            Vector2d pos = creature.getPosition();
            if (pos.equals(position)) return creature;
        }
        return null;
    }
}
