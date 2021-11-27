package agh.ics.oop;

import java.util.*;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final int n; //ilosc kępek trawy
    //private ArrayList<Animal> animals = new ArrayList<>(); //do zmiany na hash Mapę w lab6
    //private ArrayList<Grass> grass = new ArrayList<>(); //do zmiany na hash Mapę w lab6

    private Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    private Map<Vector2d, Grass> grass = new LinkedHashMap<>();

    public GrassField(int amountOfGrass) {
        this.n = amountOfGrass;
        placeTuftsOfGrass();
    }

    public void placeTuftsOfGrass() { //trzeba będzie zmienic .add na .put przez syntaktyke HashMapy
        Random generator = new Random();
        int i = 0;
        while (i < this.n) {
            Vector2d pos = new Vector2d(generator.nextInt((int) sqrt(10 * n)), generator.nextInt((int) sqrt(10 * n)));
            if (!isOccupied(pos)) {
                grass.put(pos,new Grass(pos));  //grass.add(new Grass(pos));
                i++;
            }
        }
    }

    public String toString(){
        animals.putAll(super.animals);  //zastanowić się jak to uaktualnić do HashMapy -> istnieje metoda .putAll()

        return super.toString();
    }

    public Vector2d[] getCorners(){ //psuje sie przez forEach ...???
        Vector2d topRight = new Vector2d (0,0);
        Vector2d bottomLeft = new Vector2d (0,0);

        Set<Map.Entry<Vector2d, Animal>> entrySetAnimals = animals.entrySet();
        Set<Map.Entry<Vector2d, Grass>> entrySetGrass = grass.entrySet();

        Iterator<Map.Entry<Vector2d, Animal>> itAnimals = entrySetAnimals.iterator();
        Iterator<Map.Entry<Vector2d, Grass>> itGrass = entrySetGrass.iterator();

        while (itAnimals.hasNext()){
            var pos = itAnimals.next().getKey();
            topRight = topRight.upperRight(pos);

            bottomLeft = bottomLeft.lowerLeft(pos);
        }

        while(itGrass.hasNext()){
            var pos = itGrass.next().getKey();
            topRight = topRight.upperRight(pos);

            bottomLeft = bottomLeft.lowerLeft(pos);
        }

        /*for (Animal lifeform: animals){
            Vector2d pos = lifeform.getPosition();

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
        }*/

        return new Vector2d[]{bottomLeft, topRight};
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
    public Object objectAt(Vector2d position) {  //ponieważ isOccupied bazuje na objectAt to po przejsciu na HashMape trzeba bedzie zmienić implementację tutaj

        if (super.objectAt(position) instanceof Animal) return super.objectAt(position);

        return grass.get(position);
        /*for (Grass tuft : grass) {
            Vector2d pos = tuft.getPosition();
            if (pos.equals(position)) return tuft;
        }*/

        //return null;
    }
}
