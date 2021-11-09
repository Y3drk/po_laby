package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private IWorldMap map;

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

    public boolean isAt(Vector2d position){
        return this.position.x == position.x && this.position.y == position.y;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> this.orientation = orientation.previous();
            case RIGHT -> this.orientation = orientation.next();
            case FORWARD -> {
                if (this.map.canMoveTo(this.position.add(this.orientation.toUnitVector())) && !this.map.isOccupied(this.position.add(this.orientation.toUnitVector())))
                {
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (this.map.canMoveTo(this.position.subtract(this.orientation.toUnitVector())) && !this.map.isOccupied(this.position.subtract(this.orientation.toUnitVector()))) {
                    this.position = this.position.subtract(this.orientation.toUnitVector());
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
}
