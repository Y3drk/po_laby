package agh.ics.oop;


import java.util.Scanner;


public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");

        String mvs;
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj ruchy zwierzaka: ");
        mvs = sc.nextLine();
        String[] mvsArray = mvs.split(" ");

        Direction[] mvs2 = change(mvsArray);

        run(mvs2);

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
}