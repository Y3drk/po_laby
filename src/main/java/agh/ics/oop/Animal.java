package agh.ics.oop;

import java.util.HashSet;
import java.util.Set;

public class Animal implements IMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private IWorldMap map;
    private Set<IPositionChangeObserver> observers = new HashSet<>();

    public Animal(IWorldMap map, Vector2d InitialPosition){
        this.position = InitialPosition;
        this.map = map;
    }

    @Override
    public String toString() {
        return switch (orientation){
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    @Override
    public String imageAddress(){
            return switch (orientation){
                case NORTH -> "src/main/resources/up.png";
                case EAST -> "src/main/resources/right.png";
                case SOUTH -> "src/main/resources/down.png";
                case WEST -> "src/main/resources/left.png";
            };
    }

    public boolean isAt(Vector2d position){
        return this.position.x == position.x && this.position.y == position.y;
    }

    public void move(MoveDirection direction){
        Vector2d oldPosition = this.position;
        Vector2d newPosition;
        switch (direction){
            case LEFT -> this.orientation = orientation.previous();
            case RIGHT -> this.orientation = orientation.next();
            case FORWARD -> {
                newPosition = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPosition))
                {
                    this.position = newPosition;
                    positionChanged(oldPosition, newPosition);
                }
            }
            case BACKWARD -> {
                newPosition = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(newPosition)) {
                    this.position = newPosition;
                    positionChanged(oldPosition, newPosition);
                }
            }
        }
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void addObserver(IPositionChangeObserver observer){
            this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
            this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver obs: observers) {
            obs.positionChanged(oldPosition, newPosition);
        }
    }
}
