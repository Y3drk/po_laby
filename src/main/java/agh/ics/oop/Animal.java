package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString() {
        return "Animal's position is: " + this.position.toString() + " and it's orientation is: " + this.orientation.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.x == position.x && this.position.y == position.y;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> this.orientation = orientation.previous();
            case RIGHT -> this.orientation = orientation.next();
            case FORWARD -> {
                if ((this.position.add(this.orientation.toUnitVector())).precedes(new Vector2d(4,4)) & (this.position.add(this.orientation.toUnitVector())).follows(new Vector2d(0,0)))
                {
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if ((this.position.subtract(this.orientation.toUnitVector())).precedes(new Vector2d(4, 4)) & (this.position.subtract(this.orientation.toUnitVector())).follows(new Vector2d(0, 0))) {
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                }
            }
        }
    }
}
