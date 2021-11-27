package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    public static Vector2d bottomLeft = new Vector2d(0,0);
    public int width;
    public int height;
    public Map<Vector2d, Animal> animals = new LinkedHashMap<>();

    //public ArrayList<Animal> animals = new ArrayList<>(); //do zmiany na hash Mapę w lab6


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
        return new Vector2d[]{bottomLeft, new Vector2d(width,height)};
    }


    public Map<Vector2d, Animal> getAnimalList(){
        return this.animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return this.width >= position.x && this.height >= position.y && position.x >= 0 && position.y >= 0 && !isOccupied(position);
    }

}
