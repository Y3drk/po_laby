package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        return switch (this) {
            case NORTH -> "północ";
            case SOUTH -> "południe";
            case EAST -> "wschód";
            case WEST -> "zachód";
        };
    }

    public MapDirection next(){
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }

    public MapDirection previous(){
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public World.Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new World.Vector2d(0,1);
            case SOUTH -> new World.Vector2d(0,-1);
            case EAST -> new World.Vector2d(1,0);
            case WEST -> new World.Vector2d(-1,0);
        };
    }

}
