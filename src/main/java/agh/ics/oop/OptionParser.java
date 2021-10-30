package agh.ics.oop;

import java.util.ArrayList;

public class OptionParser {
    public static ArrayList<MoveDirection> parser(String[] dirs){
        ArrayList<MoveDirection> dirs_parsed = new ArrayList<>();
        for (String comm : dirs) {
             switch (comm) {
                case "f", "forward" -> dirs_parsed.add(MoveDirection.FORWARD);
                case "b", "backward" -> dirs_parsed.add(MoveDirection.BACKWARD);
                case "l", "left" -> dirs_parsed.add(MoveDirection.LEFT);
                case "r", "right" -> dirs_parsed.add(MoveDirection.RIGHT);
            }
        }
        return dirs_parsed;

    }
}
