package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    //public ArrayList<Animal> animals; //konieczna zmiana na HashMapę

    public Map<Vector2d, Animal> animals;

    public AbstractWorldMap() {
        this.animals = new LinkedHashMap<>();
    } //this.animals = new ArrayList<>()


    public abstract Vector2d[] getCorners();

    public String toString() {
        MapVisualizer drawing = new MapVisualizer(this);
        Vector2d[] corners = this.getCorners();
        return drawing.draw(corners[0], corners[1]);
    }

    public boolean place(Animal animal) { //trzeba zmienić .add na .put ze względu na syntaktykę HashMapy
        Vector2d pos = animal.getPosition();
        Object something = objectAt(pos);
        if (something instanceof Animal) {
            return false;
        } else {
            animal.addObserver(this);
            animals.put(pos, animal); //animals.add(animal);
            return true;
        }
    }

    public boolean isOccupied(Vector2d position) {  //wykorzystuje objectAt wiec po zmianie na hashMapę zmiana objectAt powinno wystarczy
        return animals.containsKey(position);
    }

    public Object objectAt(Vector2d position) { //for wynika z używania ArrayList - po zamianie na HashMapę trzeba bedzie to zmienić, trzeba uzywać .get();
        return animals.get(position);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){ //byc może tu są błędy, okaże się pózniej
        Animal animal = animals.get(oldPosition);
        //możliwe, że trzeba też zmienic pozycje wewnątrz zwierzaka - rather not
        animals.remove(oldPosition);

        animals.put(newPosition, animal);
    }
}

        /*for (Animal creature: animals)
        {
            Vector2d pos = creature.getPosition();
            if (pos.equals(position)) return creature;
        }*
        return null;
    }
}*/
