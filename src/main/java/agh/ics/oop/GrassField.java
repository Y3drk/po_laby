package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int n; //ilosc kępek trawy
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Grass> grass = new ArrayList<>();


    public GrassField(int amountOfGrass) {
        this.n = amountOfGrass;
        placeTuftsOfGrass();
    }

    public void placeTuftsOfGrass() {
        Random generator = new Random();
        int i = 0;
        while (i < this.n) {
            Vector2d pos = new Vector2d(generator.nextInt((int) sqrt(10 * n)), generator.nextInt((int) sqrt(10 * n)));
            if (!isOccupied(pos)) {
                grass.add(new Grass(pos));
                i++;
            }
        }
    }

    public String toString(){
        animals.addAll(super.animals);

        return super.toString();
    }

    public Vector2d[] getCorners(){
        Vector2d topRight = new Vector2d (0,0);
        Vector2d bottomLeft = new Vector2d (0,0);
        for (Animal lifeform: animals){
            Vector2d pos = lifeform.getPosition();
            for (Grass tuft: grass){ //sprawdzenie czy jakieś zwierzę i jakaś trawka nie są na jednym polu, jesli tak to narazie usuwamy trawke, zeby zapewnic zwierzęciu prio
                Vector2d chk = tuft.getPosition();
                if (chk.x == pos.x && chk.y == pos.y) {
                    grass.remove(tuft);
                }
            }
            if (pos.x > topRight.x){
                topRight= new Vector2d(pos.x, topRight.y);
            }
            if (pos.y > topRight.y){
                topRight= new Vector2d(topRight.x, pos.y);
            }
            if (pos.x < bottomLeft.x){
                bottomLeft = new Vector2d(pos.x, bottomLeft.y);
            }
            if (pos.y < bottomLeft.y){
                bottomLeft = new Vector2d(bottomLeft.x, pos.y);
            }
        }

        for (Grass tuft: grass){
            Vector2d pos = tuft.getPosition();
            if (pos.x > topRight.x){
                topRight= new Vector2d(pos.x, topRight.y);
            }
            if (pos.y > topRight.y){
                topRight= new Vector2d(topRight.x, pos.y);
            }
        }
        Vector2d[] corners = {bottomLeft, topRight};

        return corners;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {  //mapa jest nieskończona i zakładam, ze zwierzę może wejść na trawkę
        Object something = objectAt(position);
        return (something == null) || (something instanceof Grass);
    }

    /*@Override
    public boolean place(Animal animal) {  // zakładam, że animal może zrespić się na trawce
        Vector2d pos = animal.getPosition();
        Object something = objectAt(pos);
        if (something instanceof Animal) {
            return false;
        } else {
            animals.add(animal);
            return true;
        }
    }*/


    @Override
    public boolean isOccupied(Vector2d position) {  //narazie uznaję, że i zwierze i trawka mogą zając pole, jesli bedzie trzeba to to zmienie
        for (Grass tuft : grass) {
            Vector2d pos = tuft.getPosition();
            if (pos.x == position.x && pos.y == position.y) {
                return true;
            }
        }
        /*for (Animal avatar : animals) {
            Vector2d pos = avatar.getPosition();
            if (pos.x == position.x && pos.y == position.y) {
                return true;
            }
        }*/

        if (super.isOccupied(position)) return true;

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        /*for (Animal creature : animals) {
            Vector2d pos = creature.getPosition();
            if (pos.equals(position)) return creature;
        }*/
        if (super.objectAt(position) instanceof Animal) return super.objectAt(position);

        for (Grass tuft : grass) {
            Vector2d pos = tuft.getPosition();
            if (pos.equals(position)) return tuft;
        }


        return null;
    }
}
