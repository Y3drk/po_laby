package agh.ics.oop;


import static agh.ics.oop.MoveDirection.FORWARD;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        //String mvs;
        //Scanner sc = new Scanner(System.in);
        //System.out.print("Podaj ruchy zwierzaka: ");
        //mvs = sc.nextLine();
        //String[] mvsArray = mvs.split(" ");

        //Direction[] mvs2 = change(args);

        //run(mvs2);

        // laby 2
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(position1.opposite());
        System.out.println(position1.upperRight(position2));
        System.out.println(position1.lowerLeft(position2));
        System.out.println(position1.precedes(position2));
        System.out.println(position1.follows(position2));
        System.out.println(position1.subtract(position2));
        System.out.println(position1.equals(position2));

        MapDirection n = MapDirection.NORTH;
        MapDirection s = MapDirection.SOUTH;
        MapDirection e = MapDirection.EAST;
        MapDirection w = MapDirection.WEST;
        System.out.println(n.toString());
        System.out.println(s.next());
        System.out.println(e.previous());
        System.out.println(w.toUnitVector());


        System.out.println("system zakończył działanie");

    }

    public static Direction[] change(String[] arr) {
        Direction[] mvs2 = new Direction[arr.length];
        int i = 0;
        for (String comm : arr) {
            Direction message = switch (comm) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> Direction.OTHER;
            };
            mvs2[i] = message;
            i ++;
        }
        return mvs2;
    }

    public static void run(Direction[] array) {
        System.out.println("START");
        for (Direction comm: array) {
            String message = switch (comm){
                case FORWARD -> "zwierzak idzie do przodu\n";
                case BACKWARD -> "zwierzak idzie do tyłu\n";
                case LEFT -> "zwierzak skręca w lewo\n";
                case RIGHT -> "zwierzak skręca w prawo\n";
                default -> "";
            };
            System.out.print(message);

        }
        System.out.println("STOP");
    }

    //laby2

    public static class Vector2d {
        final int x;
        final int y;

        public Vector2d(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }

        public boolean precedes(Vector2d other) {
            return this.x <= other.x && this.y <= other.y;
        }

        public boolean follows(Vector2d other) {
            return this.x >= other.x && this.y >= other.y;
        }

        public Vector2d upperRight(Vector2d other) {
            int xn = max(this.x, other.x);
            int yn = max(this.y, other.y);
            return new Vector2d(xn, yn);
        }

        public Vector2d lowerLeft(Vector2d other) {
            int xn = min(this.x, other.x);
            int yn = min(this.y, other.y);
            return new Vector2d(xn, yn);
        }

        public Vector2d add(Vector2d other) {
            return new Vector2d((this.x + other.x), (this.y + other.y));
        }

        public Vector2d subtract(Vector2d other) {
            return new Vector2d((this.x - other.x), (this.y - other.y));
        }

        public boolean equals(Object other) {
            if (this == other)
                return true;
            if (other == null)
                return false;
            if (getClass() != other.getClass())
                return false;
            Vector2d temp = (Vector2d) other;
            return this.x == temp.x && this.y == temp.y;
            }


        public Vector2d opposite(){
            return new Vector2d(-this.x, -this.y);
        }





        }
}
