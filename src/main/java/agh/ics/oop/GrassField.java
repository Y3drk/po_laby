package agh.ics.oop;

import javafx.util.Pair;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final int n; //ilosc kępek trawy
    private Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    private Map<Vector2d, Grass> grass = new LinkedHashMap<>();
    private MapBoundary boundaries = new MapBoundary();

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
                grass.put(pos,new Grass(pos));
                boundaries.axisX.add(new Pair<>(pos,new Grass(pos)));
                boundaries.axisY.add(new Pair<>(pos,new Grass(pos)));
                i++;
            }
        }
    }

    public boolean place(Animal animal) {
        if(super.place(animal)) {
            boundaries.axisX.add(new Pair<>(animal.getPosition(),animal));
            boundaries.axisY.add(new Pair<>(animal.getPosition(),animal));
            return true;
        }
        return false;
    }

    public String toString(){
        animals.putAll(super.animals);

        return super.toString();
    }

    public Vector2d[] getCorners(){
        Vector2d topRight = new Vector2d (0,0);
        Vector2d bottomLeft = new Vector2d (0,0);

        Vector2d smallestY = boundaries.axisY.first().getKey();
        Vector2d biggestY = boundaries.axisY.last().getKey();
        Vector2d smallestX = boundaries.axisX.first().getKey();
        Vector2d biggestX = boundaries.axisX.last().getKey();

        topRight = topRight.upperRight(biggestY);
        topRight = topRight.upperRight(biggestX);

        bottomLeft = bottomLeft.lowerLeft(smallestY);
        bottomLeft = bottomLeft.lowerLeft(smallestX);

        return new Vector2d[]{bottomLeft, topRight};
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition, newPosition);
        boundaries.positionChanged(oldPosition, newPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {  //mapa jest nieskończona i zakładam, ze zwierzę może wejść na trawkę
        Object something = objectAt(position);
        return (something == null) || (something instanceof Grass);
    }


    @Override
    public boolean isOccupied(Vector2d position) {  //narazie uznaję, że i zwierze i trawka mogą zając pole, jesli bedzie trzeba to to zmienie
        if (super.isOccupied(position)) return true; //wykorzystuje objectAt i metode isOccupied z AbstractWorldMap, ktora też korzysta z objectAt
                                                        //-> zmiana objectAt powinna tutaj zadziałać
        Object something = objectAt(position);
        if (something instanceof Grass) return true;

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) instanceof Animal) return super.objectAt(position);

        return grass.get(position);
    }
}
