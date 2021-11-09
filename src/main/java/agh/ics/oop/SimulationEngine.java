package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    public ArrayList<MoveDirection> mvs;
    public IWorldMap map;
    public Vector2d[] animals_to_place;

    public SimulationEngine(ArrayList<MoveDirection> dirs, IWorldMap map, Vector2d[] ideas){
        this.mvs = dirs;
        this.map = map;
        this.animals_to_place = ideas;
        addAnimals();
    }

    public void addAnimals(){
        for (Vector2d lifeform: animals_to_place) {
                map.place(new Animal(map, lifeform));
            }
    }

    @Override
    public void run() {
        RectangularMap map2 = (RectangularMap) map;
        ArrayList<Animal> creatures = map2.getAnimalList();
        for (int i=0; i < mvs.size(); i++) {
            Animal lifeform = creatures.get(i % creatures.size());
            lifeform.move(this.mvs.get(i));
            creatures.set(i % creatures.size(), lifeform);
        }
        map = map2;
        }
}

            /* test poruszania sie do punktu 6
            System.out.println(i % animals_to_place.length);
            System.out.println(lifeform);
            System.out.println(lifeform.getPosition());
            System.out.println(lifeform.getOrientation());
            System.out.println("brrrr");

            System.out.println(lifeform.getPosition());
            System.out.println(lifeform.getOrientation());
            System.out.println(creatures.get(i % animals_to_place.length));
            System.out.println("-------");
            //System.out.print(map2);
            */