package agh.ics.oop;

import javafx.util.Pair;


import java.util.Comparator;

public class AxisXComparator  implements Comparator< Pair <Vector2d, IMapElement>>{

    @Override
    public int compare(Pair<Vector2d, IMapElement> o1, Pair<Vector2d, IMapElement> o2) {
     Vector2d p1 = o1.getKey();
     Vector2d p2 = o2.getKey();
     IMapElement l1 = o1.getValue();

    if ( p1.equals(p2)) {
        if (l1 instanceof Animal) {
            return 1;
        } else return -1;
    } else if (p1.x > p2.x) {
        return 1;
    } else return -1;
    }
}
