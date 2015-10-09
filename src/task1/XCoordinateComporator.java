package task1;

import java.util.Comparator;

/**
 * Класс компаратор для сортировки по оси X.
 */
public class XCoordinateComporator implements Comparator<Point>{

    @Override
    public int compare(Point p1, Point p2) {

        return p1.getX() < p2.getX() ? -1 : p1.getX() == p2.getX() ? 0 : 1;
    }


}
