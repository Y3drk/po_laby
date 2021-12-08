package agh.ics.oop;

public class Grass implements IMapElement {
    private Vector2d gr_pos;

    public Grass(Vector2d pos){
        this.gr_pos=pos;
    }

    public Vector2d getPosition(){
        return this.gr_pos;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public String imageAddress(){ return "src/main/resources/grass.png";}
}
