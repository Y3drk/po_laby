package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    public ArrayList<MoveDirection> mvs;
    public IWorldMap map;
    public Vector2d[] animals_to_place;
    private ArrayList<Animal> cubs = new ArrayList<>();

    public SimulationEngine(ArrayList<MoveDirection> dirs, IWorldMap map, Vector2d[] ideas){
        this.mvs = dirs;
        this.map = map;
        this.animals_to_place = ideas;
        addAnimals();
    }

    public void addAnimals(){
        for (Vector2d lifeform: animals_to_place) {
            Animal kitty = new Animal(map, lifeform);
            if (map.place(kitty)){
                cubs.add(kitty);
            }
        }
    }

    @Override
    public void run() {
        for (int i=0; i < mvs.size(); i++) {
            Animal lifeform = cubs.get(i % cubs.size());
            lifeform.move(this.mvs.get(i));
            cubs.set(i % cubs.size(), lifeform);
        }
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