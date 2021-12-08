package agh.ics.oop;



import javafx.util.Pair;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    SortedSet<Pair<Vector2d, IMapElement>> axisX = new TreeSet<>(new AxisXComparator());
    SortedSet<Pair <Vector2d, IMapElement>> axisY = new TreeSet<>(new AxisYComparator());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Pair<Vector2d, IMapElement> redundant = axisX.first();

        for (Pair <Vector2d, IMapElement> lifeform : axisX) {
            if (lifeform.getKey().equals(oldPosition) && lifeform.getValue() instanceof Animal){
                redundant = lifeform;
            }
        }

        axisX.remove(redundant);
        axisX.add(new Pair<>(newPosition, redundant.getValue()));

        axisY.remove(redundant);
        axisY.add(new Pair<>(newPosition, redundant.getValue()));
    }
}
